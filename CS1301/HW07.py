#===============================================
#   Filename: hw7.py
#   Program by: Brandon Hill and Hussain Mumtaz
#   Email: hillbp@gatech.edu and husmum@gatech.edu
#   Date: 11/5/2014
#       We worked on the homework assignment
#     alone, using only this semester's course
#     materials.
#===============================================

from Myro import *

init("COM4")
setPictureSize('small')
darkenCamera(0)

def seeingRed():
    pic = takePicture()
    savePicture(pic,"tintedPictureBefore.gif")
    for pix in getPixels(pic):
        setRed(pix,255)
    savePicture(pic,"tintedPictureAfter.gif")
    return pic

def multipleExposure():
    picList = []
    for i in range(5):
        picList.append(takePicture())
    pixelDict1 = {}
    for pix in getPixels(picList[0]):
        pixelDict1[getX(pix),getY(pix)] = getRGB(pix)
    pixelDict2 = {}
    for pix in getPixels(picList[1]):
        pixelDict2[getX(pix),getY(pix)] = getRGB(pix)
    pixelDict3 = {}
    for pix in getPixels(picList[2]):
        pixelDict3[getX(pix),getY(pix)] = getRGB(pix)
    pixelDict4 = {}
    for pix in getPixels(picList[3]):
        pixelDict4[getX(pix),getY(pix)] = getRGB(pix)
    pixelDict5 = {}
    for pix in getPixels(picList[4]):
        pixelDict5[getX(pix),getY(pix)] = getRGB(pix)
    p = takePicture()
    for pix in getPixels(p):
        avgRed = (pixelDict1[getX(pix),getY(pix)][0] + pixelDict2[getX(pix),getY(pix)][0] + pixelDict3[getX(pix),getY(pix)][0] + pixelDict4[getX(pix),getY(pix)][0] + pixelDict5[getX(pix),getY(pix)][0])//5
        avgGreen = (pixelDict1[getX(pix),getY(pix)][1] + pixelDict2[getX(pix),getY(pix)][1] + pixelDict3[getX(pix),getY(pix)][1] + pixelDict4[getX(pix),getY(pix)][1] + pixelDict5[getX(pix),getY(pix)][1])//5
        avgBlue = (pixelDict1[getX(pix),getY(pix)][2] + pixelDict2[getX(pix),getY(pix)][2] + pixelDict3[getX(pix),getY(pix)][2] + pixelDict4[getX(pix),getY(pix)][2] + pixelDict5[getX(pix),getY(pix)][2])//5
        setRGB(pix,(avgRed,avgGreen,avgBlue))
    savePicture(p,"multipleExposure.gif")
    return p

def splitScreen():
    picListLeft = []
    for i in range(10):
        picListLeft.append(takePicture())
        wait(0.1)
        turnLeft(0.1,0.1)
    cont = input("Continue?")
    if cont == "y":
        picListRight = []
        for i in range(10):
            picListRight.append(takePicture())
            wait(0.1)
            turnRight(0.1,0.1)
    else:
        return
    picListFinal = []
    index = 0
    while index < 10:
        pixL = getPixels(picListLeft[index])
        pixR = getPixels(picListRight[index])
        for pix in pixL:
            if getX(pix)>getWidth(picListLeft[index])/2:
                setRGB(pix,getRGB(getPixel(picListRight[index],getX(pix),getY(pix))))
        picListFinal.append(picListLeft[index])
        index = index + 1
    savePicture(picListFinal,"splitScreen.gif")
    finalPic = makePicture("splitScreen.gif")
    return finalPic

def fade():
    picList = []
    for i in range(10):
        picList.append(takePicture())
    index = 0
    newPicList = []
    while index < 10:
        for pix in getPixels(picList[index]):
            r,g,b = getRGB(pix)
            setRGB(pix,(r-(255//(10-index)),g-(255//(10-index)),b-(255//(10-index))))
        newPicList.append(picList[index])
        index = index + 1
    savePicture(newPicList,'fade.gif')
    return makePicture('fade.gif')

def screenSwipe():
    p1 = takePicture()
    ask = input("Continue?")
    if ask == "y":
        x = 0
        y = 0
        pList =[] 
        p2 = takePicture()
        while y < getHeight(p1):
            pix1 = getPixel(p1,x,y)
            pix2 = getPixel(p2,x,y)
            setRGB(pix1,getRGB(pix2))
            x += 1
            if x == getWidth(p1):
                x = 0
                y += 1
                if y%7 == 0:
                    pList.append(copyPicture(p1))
        savePicture(pList,"screenSwipe.gif")
    else:
        return

def textSwipe():
    p1 = takePicture()
    x = 0
    y = 0 
    pList=[]
    p2 = makePicture("text.gif")
    while x < getWidth(p1):
        pix1 = getPixel(p1,x,y)
        pix2 = getPixel(p2,x,y)
        if getRGB(pix2) != (255,255,255):
            setRGB(pix1,getRGB(pix2))
        y += 1
        if y == getHeight(p1):
            y = 0
            x += 1
            if x%7 == 0:
                pList.append(copyPicture(p1))
    savePicture(pList,"textSwipe.gif")
