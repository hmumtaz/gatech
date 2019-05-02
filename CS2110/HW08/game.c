//Hussain Mumtaz

#include <stdlib.h>
#include <stdio.h>
#include "mylib.h"
#include "game.h"
#include "text.h"
#include "start.h"
#include "ass.h"
#include "Zerio.h"

//Play Area
u16 board[HEIGHT][WIDTH];

static int score;
static int lines;
static int frame;
static int speed;
static char scoreString[41];
static char levelString[41];

//Game State
int state;

BLOCK current;
BLOCK next;

//Makes a new block
BLOCK makeBlock() {
	BLOCK block;
	setBlock(rand() % PIECES, &block, 0);
	//Starting Position on Board
	block.x = (WIDTH - block.size) / 2;
	block.y = 0;
	return block;
}

//Sets array and color for block
void setBlock(int type, BLOCK *block, int rotation) {
	//Clear cells
	DMA[3].src = EMPTY;
	DMA[3].dst = &block->cells[0][0];
	DMA[3].cnt = (DIM * DIM) | DMA_SOURCE_FIXED | DMA_32 | DMA_ON;


	block->size = DIM - 1; // used in collision detection
	block->rotation = rotation;
	for (int i = 0; i < DIM; i++) {
		int r = BLOCKSARR[type][rotation][i][0];
		int c = BLOCKSARR[type][rotation][i][1];
		block->cells[r][c] = 1;
	}
	if (type == BLOCK_I) {
		block->color = RED;
		block->size = DIM;
	} else if (type == BLOCK_O) {
		block->color = GREEN;
	} else if (type == BLOCK_T) {
		block->color = BLUE;
	} else if (type == BLOCK_S) {
		block->color = CYAN;
	} else if (type == BLOCK_Z) {
		block->color = MAGENTA;
	} else if (type == BLOCK_J) {
		block->color = YELLOW;
	} else if (type == BLOCK_L) {
		block->color = GRAY;
	}

	block->type = type;
}

//draws current on screen
void drawBlock() {
	for (int i = 0; i < DIM; i++) {
		for (int j = 0; j < DIM; j++) {
			//each cell effectively functions as a rectangle
			if (current.cells[i][j]) {
				drawRect3(SCALE * (i + current.y), SCOL + SCALE * (j + current.x),
					SCALE, SCALE, current.color);
			}
		}
	}
}

//draws next in preview area
void drawPrev() {
	for (int i = 0; i < DIM; i++) {
		for (int j = 0; j < DIM; j++) {
			//terniary expression helps clear previous block from preview
			u16 color = (next.cells[i][j]) ? next.color : BLACK;
			drawRect3(PROW + SCALE * (i + current.y), SCALE * (j + current.x),
				SCALE, SCALE, color);
		}
	}
}

//starts the game
void start() {
	state = GAME;
	//Clear background
	drawRect3(0,0,240,160, BLACK);
	//Draw board
	drawRect3(SROW, SCOL, SCALE * WIDTH, SCALE * HEIGHT, WHITE);
	//Draw cat
	drawImage3(10, 150, ZERIO_WIDTH/2, ZERIO_HEIGHT/2, Zerio_data);
	//Score + Level
	sprintf(scoreString, "Score: %d", 0);
	drawString(120, 150, scoreString, WHITE);

	sprintf(levelString, "Level: %d", 1);
	drawString(140, 150, levelString, WHITE);
	//Draw First Blocks
	current = makeBlock();
	drawBlock();

	next = makeBlock();
	drawPrev();

	frame = 0;
}

//end game, draw end screen
void end() {
	state = OVER;
	drawImage3(0, 0, ASS_WIDTH, ASS_HEIGHT, ass_data);
}


//reset the game
void reset() {
	state = INIT;
	score = 0;
	lines = 0;
	speed = SPEED;
	//clear board
	int clear = 0;
	DMA[3].src = &clear;
	DMA[3].dst = &board[0];
	DMA[3].cnt = SIZE | DMA_SOURCE_FIXED | DMA_16 | DMA_ON;
	drawImage3(0, 0, START_WIDTH, START_HEIGHT, start_data);
}

//refresh screen, updates blocks and frame
void refresh() {
	if (state == GAME && frame == speed) {
		moveBlock(0,1);
		frame = 0;
	} else {
		frame ++;
	}
}

//collision checking
int collides(BLOCK block) {
	//iterate through cells
	for (int i = 0; i < DIM; i++) {
		for (int j = 0; j < DIM; j++) {
			if (block.cells[i][j]) {
				if ((block.x + j < 0) || 
					(block.x + j >= WIDTH) ||
					(block.y + i >= HEIGHT) ||
					(board[block.y + i][block.x + j])) {
					return 1;
				}
			}
		}
	}
	return 0;
}

//moving tetris blocks
void moveBlock(int dx, int dy) {
	BLOCK check = current;
	check.x += dx;
	check.y += dy;
	int collision = collides(check);
	if (!collision) {
		blockMoved();
		current = check;
		drawBlock();
	} else if (collision && dy == 1) { //Touchdown Condition
		
		//draw current to screen
		for (int i = 0; i < current.size; i++) {
			for (int j = 0; j < current.size; j++) {
				if (current.cells[i][j]) {
					board[current.y + i][current.x + j] = current.color;
				}
			}
		}

		int s = current.y;
		int e = s + current.size;
		current = next;
		
		//end condition
		if (collides(current))
		{
			end();
		} else {
			drawBlock();
			next = makeBlock();
			drawPrev();
			frame = 0;
			
			//Remove Filled Rows + Calc Score + Level
			int filled = rowsRemoved(s, e);
			if (filled > 0) 
			{
				updateLevel(filled);
			}
			updateScore(filled);
		}
	}
}

//Checks rows to remove after a block has fallen
//s = top of landed block
//e = bottom of landed block
//e-s = rows we need to check
int rowsRemoved(int s, int e) {
	int filled = 0;
	int isFull;
	//if row is filled, clear row
	for (int i = s; i < e; i++) {
		isFull = 1;
		for (int j = 0; j < WIDTH; j++) {
			if (board[i][j] == EMPTY) {
				isFull = 0;
				break;
			}
		}
		if (isFull) {
			for (int x = 0; x < WIDTH; x++) {
				for (int y = i; y >= 0; y--) {
					u16 prev = board[y][x];
					// replace the top row with empty cells
					board[y][x] = (y == 0) ? EMPTY : board[y - 1][x]; 
					if (prev != board[y][x]) {
						redraw(y, x);
					}
				}	
			}		
		
			filled++;
		}
	}
	return filled;
}

void updateScore(int filled) {
	score += SPEED * (filled + 1);
	drawRect3(120, 150, 140, 8, BLACK);
	sprintf(scoreString, "Score: %d", score);
	drawString(120, 150, scoreString, WHITE);
}

void updateLevel(int filled) {
	lines += filled;
	speed = SPEED - (10 * (lines / 10));
	drawRect3(140, 150, 140, 8, BLACK);
	sprintf(levelString, "Level: %d", (lines/10) + 1);
	drawString(140, 150, levelString, WHITE);
}


//rotate block based on direction
void rotateBlock(int direction) {
	//Creating seperate block for collision checking
	BLOCK new;
	new.x = current.x;
	new.y = current.y;
	int rotate;
	if (direction < 0 && current.rotation == 0) {
		rotate = 3;
	} else {
		rotate = (current.rotation + direction) % ROTATIONS;
	}
	//create rotated block
	setBlock(current.type, &new, rotate);

	if (!collides(new))
	{
		blockMoved();
		current = new;
		drawBlock();
	}
}

//Erase old pixels
void blockMoved() {
	for (int i = 0; i < DIM; i++) {
		for(int j = 0; j < DIM; j++) {
			if (current.cells[i][j])
			{
				drawRect3(SCALE * (i + current.y), SCOL + SCALE * (j + current.x),
					SCALE, SCALE, WHITE);
			}
		}
	}
}

//redraw lines not filled
void redraw(int x, int y) {
	u16 color = (board[x][y]) ? board[x][y] : WHITE;
	drawRect3(SCALE * x, SCOL + SCALE * y, SCALE, SCALE, color);
}