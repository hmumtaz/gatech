“””Hussain Mumtaz
Jonathan Boutsicaris
Kavya Sengouttouvane
 
husmum@gatech.edu
jonboutsicaris@gatech.edu
skavya@gatech.edu

We worked on the homework assignment alone, using only this semester's course materials.”””
 
 



from Myro import *

init("/dev/tty.Fluke2-093E-Fluke2")

setPicSize("small")

def scene1():
    x=0
    outputList = []

    while x<60:

        picture = takePicture()
        outputList.append(picture)

        x= x+1

    savePicture(outputList, "scene1.gif")

scene1()


def scene2():
    x=0
    outputList = []

    while x<60:

        picture = takePicture()
        outputList.append(picture)

        x= x+1

    savePicture(outputList, "scene2.gif")

scene2()

