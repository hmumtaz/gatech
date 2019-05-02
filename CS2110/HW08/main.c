//Hussain Mumtaz
#include <stdlib.h>
#include "mylib.h"
#include "game.h"

int main() {
	REG_DISPCTL = MODE3 | BG2_ENABLE;

	int init = 1;
	while(1) {
		//update key presses, functions as a lock
		keyOld = keyNew;
		keyNew = ~BUTTONS & BUTTON_MASK;
		waitForVblank();
		if (KEY_PRESS(BUTTON_START)) {
			if (state == INIT) {
				state = GAME;
				start(); //start game
			} else if (state == OVER) {
				reset();
			}
		} else if (KEY_PRESS(BUTTON_SELECT) || init) {
			reset();
			init = 0;
		} else if (state == GAME) {
			if (KEY_DOWN_NOW(BUTTON_DOWN)) {
				moveBlock(0, 1); // move down
			} else if (KEY_PRESS(BUTTON_LEFT)) {
				moveBlock(-1, 0); // move left
			} else if (KEY_PRESS(BUTTON_RIGHT)) {
				moveBlock(1, 0); // move right
			} else if (KEY_PRESS(BUTTON_L)) {
				rotateBlock(-1); // rotate counterclockwise
			} else if (KEY_PRESS(BUTTON_R)) {
				rotateBlock(1); // rotate clockwise
			}
		}
		//make necessary updates to screen
		refresh();
	}
}