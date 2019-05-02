#define PIECES 7
#define DIM 4
#define ROTATIONS 4
#define SCALE 8
#define WIDTH 10
#define HEIGHT 20
#define SIZE 200
#define SCOL 60
#define PROW 20
#define SPEED 30
#define EMPTY 0

#define	BLOCK_I 0
#define	BLOCK_L 1
#define	BLOCK_J 2
#define	BLOCK_O 3
#define	BLOCK_S 4
#define	BLOCK_T 5
#define	BLOCK_Z 6

#define INIT 0
#define GAME 1
#define OVER 2

typedef struct {
	u16 color;
	int cells[DIM][DIM]; //Each piece is a 4*4 array
	int rotation;
	int x;
	int y;
	int size;
	int type;

} BLOCK;

extern int state;
extern const int BLOCKSARR[PIECES][ROTATIONS][DIM][2];
extern u16 board[HEIGHT][WIDTH];
extern BLOCK current;
extern BLOCK next;

BLOCK makeBlock();
void setBlock(int type, BLOCK *block, int rotation);
void drawBlock();
void drawPrev();
void start();
void end();
void reset();
void refresh();
int collides(BLOCK newBlock);
void moveBlock(int dx, int dy);
int rowsRemoved(int s, int e);
void updateScore(int filled);
void updateLevel(int filled);
void rotateBlock(int direction);
void blockMoved();
void redraw(int x, int y);