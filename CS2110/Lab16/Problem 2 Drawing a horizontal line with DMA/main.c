#include <stdlib.h>
#include "mylib.h"

u16 junk[240];
void drawJunk(int y);
void fillJunk();
void fillJunkBadNum(const int junkSize);

int main(void)
{
	int i;
	REG_DISPCNT = MODE3 | BG2_ENABLE;
	
	/* Initialization */	
	for (i = 0; i < 240; i++)
		junk[i] = i;
	
	fillJunkBadNum(7);		
	while (1)
	{
		waitForVblank();
		for (i = 0; i < 240; i++)
			drawJunk(i);

		fillJunk();
		fillJunkBadNum(7);
	}
}

/* Copy everything that is in the junk array on the videobuffer
at the y position that is after this finishes the row of the 
videoBuffer at the indicated y should contain whatever is in
junk and if this is correct you should see 240 vertical lines
kinda like a gradient going from black to red */
/* i.e. if y is zero then only the first row of the videoBuffer should be filled*/
/* i.e. if y is one then only the second row of the videoBuffer should be filled*/
void drawJunk(int y)
{
	DMA[3].src = &junk;
	DMA[3].dst = &(videoBuffer[y * 240]);
	DMA[3].cnt = 240 | DMA_ON;
}

/*
Instead of the u16 junk[240] array, how about if I make it
u16 junk[120] and fill it with random values?
The goal is to fill the whole screen.
Now of course you will need a for loop!
But how many times will this loop need to be ran?
*/
void fillJunk()
{
	/* Declare some junk */
	u16 myJunk[120];
	int i;
	
	for (i = 0; i < 120; i++)
		myJunk[i] = rand() % 32767; /* Hey random color here */
		
	/* Your code here repeatedly DMA myJunk to fill the screen */
	for (int j = 0; j < 320; j++)
	{
		DMA[3].src = myJunk;
		DMA[3].dst = &(videoBuffer[120 * j]);
		DMA[3].cnt = 120 | DMA_ON;
	}
	/* When you go to test it, don't forget to remove the comment in the 
	while loop up top! */
}


/* What if the number of entries in junk was not a nice number
say 7 now rewrite fillJunk for this case 
We still want to fill the whole screen! 
Some has been done for you below but is commented out*/
void fillJunkBadNum(const int junkSize)
{
	u16 myJunk[junkSize];
	
	for (int i = 0; i < junkSize; i++)
		myJunk[i] = rand() % 32767;
	
	/* How many complete myJunk can we insert into videobuffer? */
	int wholeJunk = 38400 / junkSize; 
	for (int j = 0; j < wholeJunk; j++)
	{
		DMA[3].src = myJunk;
		DMA[3].dst = &(videoBuffer[junkSize * j]);
		DMA[3].cnt = 120 | DMA_ON;
	}
	/* Since the junk size might be a "bad number",
	 we could have some leftovers */
	int leftover = 38400 % junkSize;
		DMA[3].src = myJunk;
		DMA[3].dst = &(videoBuffer[wholeJunk * junkSize]);
		DMA[3].cnt = leftover | DMA_ON;

	/* Your code here */


}
