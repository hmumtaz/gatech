#===============================================
#   Filename: hw6.py
#   Program by: Brandon Hill and Hussain Mumtaz
#   Email: hillbp@gatech.edu and husmum@gatech.edu
#   Date: 10/22/2014
#       We worked on the homework assignment
#     alone, using only this semester's course
#     materials.
#===============================================
from Myro import *

init()

setPictureSize('small')
darkenCamera(0)

def findColor(pic):
    pHeight = getHeight(pic)
    pWidth = getWidth(pic)
    #If all pixels are required, change number below to 1
    #Pixel skipping was added for efficiency; iterating through all pixels is very slow
    pixelSkip = 10
    for y in range(pHeight//pixelSkip):
        for x in range(pWidth//pixelSkip):
            x2 = x*pixelSkip
            y2 = y*pixelSkip
            pix = getPixel(pic,x2,y2)
            red,green,blue = getRGB(pix)
            diffR = 20
            diffG = 10
            diffY = 60
            diffW = 10
            if red - green > diffR and red - blue > diffR:
                setRGB(pix,(255,0,0))
            elif green - red > diffG and green - blue > diffG:
                setRGB(pix,(0,255,0))
            elif red + green - (2 * blue) > diffY:
                setRGB(pix,(255,255,0))
            elif abs(red - green) < diffW and abs(green-blue) < diffW and abs(red-blue) < diffW and red > 190:
                setRGB(pix,(0,0,0))
    #uncomment below for testing
    #return pic
    total = 0
    redP,greenP,yellowP,whiteP = 0,0,0,0
    for y in range(pHeight//pixelSkip):
        for x in range(pWidth//pixelSkip):
            x2 = x*pixelSkip
            y2 = y*pixelSkip
            pix = getPixel(pic,x2,y2)
            total = total + 1
            col = getRGB(pix)
            if col == (255,0,0):
                redP = redP + 1
            elif col == (0,255,0):
                greenP = greenP + 1
            elif col == (0,255,255):
                yellowP = yellowP + 1
            elif col == (0,0,0):
                whiteP = whiteP + 1
    if redP/total > 0.3:
        return 'red'#,pic
    elif greenP/total > 0.3:
        return 'green'#,pic
    elif yellowP/total > 0.3:
        return 'yellow'#,pic
    elif whiteP/total > 0.3:
        return 'white'#,pic
    else:
        return None#,pic

#uncomment below for testing
#show(takePicture(),'original')
#show(findColor(takePicture()),'modified')


def turn():
    condition = heads()
    if condition:
        for i in range(4):
            setLED('left',1)
            wait(0.2)
            setLED('left',0)
            wait(0.2)
        turnRight(1,0.8)
    elif not condition:
        for i in range(4):
            setLED('right',1)
            wait(0.2)
            setLED('right',0)
            wait(0.2)
        turnLeft(1,0.8)


def stopLight():
    while True:
        p = takePicture()
        color = findColor(p)
        #color,photo = findColor(p)
        #show(photo)
        #print(color)
        if color == 'red':
            stop()
            beep(2,1260)
            return
        elif color == 'green':
            forward(1,2)
        elif color == 'yellow':
            forward(0.5,2)
        elif color == 'white':
            turn()