// bmptoc.c
// Name: Hussain Mumtaz

#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <ctype.h>

char data_arr[0x36 + 240 * 160 * 4];

int main(int argc, char *argv[]) {	
	// DO NOT EDIT CODE BELOW THIS LINE
	
	// Code to perform file IO necesscary for bmptoc. Given a bmp file as
	// a command line argument it creates the .c and .h files that will
	// contain the pixel data in a format that can be used by the GBA.
	// Our header file will contain constants for our images dimensions
	// and the name of the array of shorts we will use. The .c file will
	// contain the array of shorts of pixel data.
	//
	// The header file generation has been completed in its entirety. Some
	// of the .c file has been as well, but you need to read the data
	// out of the bmp file and write it to the .c file to create
	// a syntactically valid array of shorts that correspond to the 
	// pixel values. It might also be a good idea to familarize
	// yourself with the file IO code here as file IO is something
	// that appears in all sorts of programs. 

	if(argc != 2) {
        // (OPTIONAL) Incorrect invocation of program
        printf("Incorrect number of arguments.\n");
        printf("Usage: ./bmptoc <file.bmp>\n");
        return 1;
    } else {

        // (OPTIONAL) Verify that it is a .bmp file
        if(strcmp(argv[1] + strlen(argv[1]) - 4, ".bmp")) {
            printf("Not a BMP file\n");
            return 1;
        }

        // (OPTIONAL) Turn full path into local path
        const char* fname_begin = argv[1] + strlen(argv[1]) - 1;
        while(fname_begin - argv[1] > 0 && *fname_begin != '/') {
            --fname_begin;
        }
        if(fname_begin != argv[1]) {
            argv[1] = (char*)(fname_begin + 1);
        }

        // Open and read file
        FILE *infile, *outheader, *outfile;
        if(!(infile = fopen(argv[1], "r"))) {
            printf("File not found: %s\n", argv[1]);
            return 1;
        }

        // Get data and image size
        fread(data_arr, 1, 0x36 + 240 * 160 * 4, infile);
        fclose(infile);
        unsigned int width = *(unsigned int*) (data_arr + 0x12);
        unsigned int height = *(unsigned int*) (data_arr + 0x16);

        // (OPTIONAL) Make sure the bitmap is not corrupt
        if(strncmp(data_arr, "BM", 2)) {
            printf("BMP file is incorrect format\n");
            return 1;
        }

        // Output header information
        char filename[100], buffer[100];
        strcpy(filename, argv[1]);
        int fname_len = strlen(filename);
        filename[fname_len - 3] = 'h';
        filename[fname_len - 2] = '\0'; // file.bmp -> file.h
        if(!(outheader = fopen(filename, "w"))) {
            printf("Error creating file %s: %s\n", filename, strerror(errno));
        }
        filename[fname_len - 4] = '\0';
        char *ptr = filename;
        while(*ptr) {
            *ptr = toupper(*ptr); // file.h -> FILE
            ++ptr;
        }

        /* File format:
         *
         *  #define FILE_WIDTH 100
         *  #define FILE_HEIGHT 150
         *  const unsigned short file_data[15000];
         */

        sprintf(buffer, "#define %s_WIDTH %d\n", filename, width);
        fwrite(buffer, 1, strlen(buffer), outheader);
	    sprintf(buffer, "#define %s_HEIGHT %d\n", filename, height);
        fwrite(buffer, 1, strlen(buffer), outheader);
        strcpy(filename, argv[1]);
        filename[fname_len - 4] = '\0';
        sprintf(buffer, "const unsigned short %s_data[%d];\n", filename, width * height);
        fwrite(buffer, 1, strlen(buffer), outheader);
        fclose(outheader);

        // Output pixel array
        strcpy(filename, argv[1]);
        filename[fname_len - 3] = 'c';
        filename[fname_len - 2] = '\0';
        if(!(outfile = fopen(filename, "w"))) {
            printf("Error creating file %s: %s\n", filename, strerror(errno));
        }
        filename[fname_len - 4] = '\0';

        /* File format:
         *
         *  const unsigned short file_data[15000] = {
         *    0x4521, 0x5B2F, 0x1F69, ...
         *  };
         */

        sprintf(buffer, "const unsigned short %s_data[%d] = {\n", filename, width * height);
        fwrite(buffer, 1, strlen(buffer), outfile);

	    // DO NOT EDIT CODE ABOVE THIS LINE

        // Loop through all the pixel data - YOUR CODE GOES UNDER HERE
	    // Useful variables:
	    // data_arr - array of bytes that contains all the data in the bmp file
	    // outfile - .c file we want to write our array to
	    // buffer - buffer of 100 bytes used to format string before fwrites 
	    // height/width - self explanatory, just so you're aware they're there
        char *colorPtr;
        char color;
        short pixel = 0;
        int len = width * height;
        unsigned int w = 0;
        int h = height - 1;
        short pixelArr[len];
        short *pixelPtr;
        colorPtr = &(data_arr[0x36]);
        for (unsigned int i = 1; i <= (width * height * 4); i++)
        {
            
            if (i % 4 != 0)
            {
                color = colorPtr[i - 1];
                color = color >> 3;
                color = color & 0x1F;
                pixel = pixel << 5;
                pixel = pixel | color;
            }
            else
            {
                if (w == width)
                {
                    h --;
                    w = 0;
                }
                pixel = pixel & 0x7FFF;
                pixelPtr = &pixelArr[(h * width) + w];
                *pixelPtr = pixel;
                w++;
            }
        }
        for (int k = 0; k < len; k++)
        {
            sprintf(buffer, "0x%04x, ", pixelArr[k]);
            fwrite(buffer, 1, strlen(buffer), outfile);
        }
	    // Write closing brace of the array and close the file.
	    fwrite("};\n", 1, 3, outfile);
	    fclose(outfile);
	}
	return 0;
}