"""Hussain Mumtaz
   Anissa Sexton
   husmum@gatech.edu

   We worked on this homework assignment alone,
   using only this semester's course materials"""


from Myro import *
from Graphics import *

init()

def sensorValue():
    a = getLight("left")
    b = getLight("right")
    c = getObstacle("right")
    v = a/(b+c)
    value = round(v,3)
    return (value)

win= Window("A",250,250)
win.mode = "manual"

def move(win,event):
    log = open("myMovements.txt","w") #Creates the file
    log.close
    log = open("myMovements.txt", "a")
    """Each additional movement must be appended,
       otherwise the file will be overwritten each time"""

    if event.key == "Up":
        forward (1,.1)
        s = sensorValue()
        a = " ".join (("forward .1", str(s), "\n"))
        log.write (a)

    if event.key == "Down":
        backward(1,.1)
        s = sensorValue()
        a = " ".join (("backward .1", str(s), "\n"))
        log.write (a)

    if event.key == "Left":
        turnLeft(1,.1)
        s = sensorValue()
        a = " ".join (("turnLeft .1", str(s), "\n"))
        log.write (a)

    if event.key == "Right":
        turnRight(1,.1)
        s = sensorValue()
        a = " ".join (("turnRight .1", str(s), "\n"))
        log.write (a)

    if event.key == "b":
        beep (1,800)
        s = sensorValue()
        a = " ".join (("beep 1", str(s), "\n"))
        log.write (a)

    if event.key == "B":
        beep (1,800)
        s = sensorValue()
        a = " ".join (("beep 1", str(s), "\n"))
        log.write (a)

    log.close()

def collectData (myFile, direction):
    toRead = open(myFile,"r")
    time = 0
    beep = 0
    timesDirection = 0
    while True:
        line = toRead.readline()

        words = line.split()
        if len(line) != 0:
            margTime = int(words[1])
            time += margTime

        if "beep" in line:
            beep += 1

        if direction in line:
            timesDirection += 1
        if len(line) == 0:
            break

    print ("The robot traveled for", time,
            "seconds total, beeping", beep, "times. This"
             " robot moved", direction, "a total of",
                timesDirection, "times.")


def replay (myFile):
    toRead = open(myFile,"r")
    while True:
        line = toRead.readline()

        words = line.split()
        if len(line) != 0:
            direction = words[0]
            time = int (words[1])
            if direction != "beep":
                eval(direction) (1,time)
            elif direction == "beep":
                eval(direction) (time,800)
        if len(line) == 0:
            break

onKeyPress(move)
