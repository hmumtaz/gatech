//Hussain Mumtaz (& mostly Bill Leahy)
#include "mylib.h"

unsigned short *videoBuffer = (unsigned short *)0x6000000;
u16 keyNew = 0;
u16 keyOld = 0;

void setPixel(int row, int col, unsigned short color)
{
	videoBuffer[OFFSET(row, col, 240)] = color;
}

void drawRect(int row, int col, int height, int width, u16 color)
{
	for(int r=0; r<height; r++)
	{
		for(int c=0; c<width; c++)
		{
			setPixel(row+r, col+c, color);
		}
	}
}

void drawRect3(int r, int c, int width, int height, u16 color) {
	for (int x = 0; x < height; x++) {
		DMA[3].src = &color;
		DMA[3].dst = &videoBuffer[OFFSET(r + x, c, 240)];
		DMA[3].cnt = (width) | DMA_SOURCE_FIXED | DMA_ON;
	}
}

void drawImage3(int r, int c, int width, int height, const unsigned short* image) {
	for (int x = 0; x < height; x++) {
		DMA[3].src = &image[OFFSET(x, 0, width)];
		DMA[3].dst = &videoBuffer[OFFSET(r + x, c, 240)];
		DMA[3].cnt = (width) | DMA_ON;
	}
}


void waitForVblank()
{
	while(SCANLINECOUNTER > 160);
	while(SCANLINECOUNTER < 160);
}