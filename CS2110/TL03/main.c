#include "mylib.h"
#include "rle.h"

void drawDecompressedImage(const u16* data);

int main(void)
{
    REG_DISPCNT = 1027;
  
    // Change this function to use rle2 or rle1 if you want.
    // These are HARDER test cases for you to ensure it works.
    // It doesn't matter which one of the three you've set it to when submitting.
    drawDecompressedImage(rle1);
    
    while(1);
}

/*
    Draws an RLE encoded image decompressed onto the videoBuffer.
    Remember you may only call setPixel when the run length is 2 or less
    You must use DMA if the run length is 3 or greater! (Heavy penalty if you don't follow these rules)
*/
void drawDecompressedImage(const u16* data)
{
	// TODO
    int pixelCtr = 0;
    int i = 0;
    while (pixelCtr < 38400)
    {
        if (data[i] <= 2)
        {
            for (int j = 0; j < data[i]; j++)
            {
                videoBuffer[pixelCtr] = data[i + 1];
                pixelCtr++;
            }
        } else {
            DMA[3].src = &(data[i+1]);
            DMA[3].dst = &(videoBuffer[pixelCtr]);
            DMA[3].cnt = data[i] | DMA_ON | DMA_SOURCE_FIXED;
            pixelCtr += data[i];
        }
        i += 2;
    }
}