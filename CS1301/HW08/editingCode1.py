“””Hussain Mumtaz
Jonathan Boutsicaris
Kavya Sengouttouvane
 
husmum@gatech.edu
jonboutsicaris@gatech.edu
skavya@gatech.edu

We worked on the homework assignment alone, using only this semester's course materials.”””
 

from Myro import *

piclist = []

piclist.append(makePicture("frame_001.gif"))
piclist.append(makePicture("frame_002.gif"))
piclist.append(makePicture("frame_003.gif"))
piclist.append(makePicture("frame_004.gif"))
piclist.append(makePicture("frame_005.gif"))
piclist.append(makePicture("frame_006.gif"))
piclist.append(makePicture("frame_007.gif"))
piclist.append(makePicture("frame_008.gif"))
piclist.append(makePicture("frame_009.gif"))
piclist.append(makePicture("frame_010.gif"))
piclist.append(makePicture("frame_011.gif"))
piclist.append(makePicture("frame_012.gif"))
piclist.append(makePicture("frame_013.gif"))
piclist.append(makePicture("frame_014.gif"))
piclist.append(makePicture("frame_015.gif"))
piclist.append(makePicture("frame_016.gif"))
piclist.append(makePicture("frame_017.gif"))
piclist.append(makePicture("frame_018.gif"))
piclist.append(makePicture("frame_019.gif"))
piclist.append(makePicture("frame_020.gif"))
piclist.append(makePicture("frame_021.gif"))
piclist.append(makePicture("frame_022.gif"))
piclist.append(makePicture("frame_023.gif"))
piclist.append(makePicture("frame_024.gif"))
piclist.append(makePicture("frame_025.gif"))
piclist.append(makePicture("frame_026.gif"))
piclist.append(makePicture("frame_027.gif"))
piclist.append(makePicture("frame_028.gif"))
piclist.append(makePicture("frame_029.gif"))
piclist.append(makePicture("frame_030.gif"))
piclist.append(makePicture("frame_031.gif"))
piclist.append(makePicture("frame_032.gif"))
piclist.append(makePicture("frame_033.gif"))
piclist.append(makePicture("frame_034.gif"))
piclist.append(makePicture("frame_035.gif"))
piclist.append(makePicture("frame_036.gif"))
piclist.append(makePicture("frame_037.gif"))
piclist.append(makePicture("frame_038.gif"))
piclist.append(makePicture("frame_039.gif"))
piclist.append(makePicture("frame_040.gif"))
piclist.append(makePicture("frame_041.gif"))
piclist.append(makePicture("frame_042.gif"))
piclist.append(makePicture("frame_043.gif"))
piclist.append(makePicture("frame_044.gif"))
piclist.append(makePicture("frame_045.gif"))
piclist.append(makePicture("frame_046.gif"))
piclist.append(makePicture("frame_047.gif"))
piclist.append(makePicture("frame_048.gif"))
piclist.append(makePicture("frame_049.gif"))
piclist.append(makePicture("frame_050.gif"))
piclist.append(makePicture("frame_051.gif"))
piclist.append(makePicture("frame_052.gif"))
piclist.append(makePicture("frame_053.gif"))
piclist.append(makePicture("frame_054.gif"))
piclist.append(makePicture("frame_055.gif"))
piclist.append(makePicture("frame_056.gif"))
piclist.append(makePicture("frame_057.gif"))
piclist.append(makePicture("frame_058.gif"))
piclist.append(makePicture("frame_059.gif"))

savePicture(piclist[10:50],'middle.gif')


fadeOutList = piclist[50:]
index = 0
fadedOutList = []
while index < len(fadeOutList):
    for pix in getPixels(fadeOutList[index]):
        r,g,b = getRGB(pix)
        setRGB(pix,(r-(255//(10-index)),g-(255//(10-index)),b-(255//(10-index))))
    fadedOutList.append(fadeOutList[index])
    index = index + 1
savePicture(fadedOutList,'fadeOut.gif')


fadeInList = piclist[:10]
index = 9
fadedInList = []
while index > 0:
    for pix in getPixels(fadeInList[index]):
        r,g,b = getRGB(pix)
        setRGB(pix,(r-(255//(10-index)),g-(255//(10-index)),b-(255//(10-index))))
    fadedInList.append(fadeInList[index])
    index = index - 1
savePicture(fadedInList,'fadeIn.gif')
