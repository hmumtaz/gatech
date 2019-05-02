#include <stdlib.h>
#include "mylib.h"
#include "mystery.h"

extern const u16 mystery[38400];

int main(void)
{
	REG_DISPCNT = MODE3 | BG2_ENABLE;

	/* 1. Hey fix this Draw the image with DMA instead ;D */
	DMA[3].src = &mystery;
	DMA[3].dst = videoBuffer;
	DMA[3].cnt = 38400 | DMA_ON;
			//setPixel(j, i, mystery[240 * i + j]);
		
	while (1)
	{
		waitForVblank();
		/* 2. Clear the screen here using DMA */
		/* Note in a real game you would not do this for performance issues, but just as an exercise do it here */
	}
}
