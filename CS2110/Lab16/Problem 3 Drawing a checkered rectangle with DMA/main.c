#include <stdlib.h>
#include "mylib.h"

void drawCheckeredRect(int x, int y, int width, int height, vu16 color1, vu16 color2);

int main(void)
{
    REG_DISPCNT = MODE3 | BG2_ENABLE;
			
    while (1)
    {
    	waitForVblank();
		  drawCheckeredRect(50, 50, 10, 10, RGB(0, 0, 31), RGB(31, 0, 0));
    }
}

/* Draws a checkered rectangle
   Each adjacent pixel will be in alternating colors (eg. pixel 0 is red, pixel 1 is blue, pixel 2 is red, pixel 3 is blue and so on

   x is guaranteed to be even, width is guaranteed to be even

   You may not allocate any memory (via malloc and friends) or declare an array in this function

   Hints 
     Use DMA_32 to copy 32 bits at a time. Look in the header file.
     However you will need a 32 bit source to use, note that, color1 and color2 are only 16 bits. Also look in the header file.
     Because you will be combining two colors to one, you may need to adjust the amount to copy in the DMA loop.
     Perhaps, write it with a for loop first if you need help getting started.
*/
void drawCheckeredRect(int x, int y, int width, int height, vu16 color1, vu16 color2)
{
  vu32 bothColors = color1;
  bothColors = (bothColors << 16) + color2;
  for (int i = 0; i < height; i++)
  {
    DMA[3].src = &bothColors;
    DMA[3].dst = &(videoBuffer[((y * 120) + (x/2)) + (i * 240)]);
    DMA[3].cnt = (width/2) | DMA_32 | DMA_ON | DMA_SOURCE_FIXED;
  }
}

