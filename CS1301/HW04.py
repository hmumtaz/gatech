from Myro import *

"""
Hussain Mumtaz
husmum@gatech.edu

I worked on the homework assignment alone,
using only this semester's course materials."""

c0 = 16.35
d0 = 17.32
e0 = 20.60
f0 = 21.83
g0 = 24.50
a0 = 27.50
b0 = 30.87
c1 = 2 * c0
d1 = 2 * d0
e1 = 2 * e0
f1 = 2 * f0
g1 = 2 * g0
a1 = 2 * a0
b1 = 2 * b0
c2 = 2 * c1
d2 = 2 * d1
e2 = 2 * e1
f2 = 2 * f1
g2 = 2 * g1
a2 = 2 * a1
b2 = 2 * b1
c3 = 2 * c2
d3 = 2 * d2
e3 = 2 * e2
f3 = 2 * f2
g3 = 2 * g2
a3 = 2 * a2
b3 = 2 * b2
c4 = 2 * c3
d4 = 2 * d3
e4 = 2 * e3
f4 = 2 * f3
g4 = 2 * g3
a4 = 2 * a3
b4 = 2 * b3
c5 = 2 * c4
d5 = 2 * d4
e5 = 2 * e4
f5 = 2 * f4
g5 = 2 * g4
a5 = 2 * a4
b5 = 2 * b4
c6 = 2 * c5
d6 = 2 * d5
e6 = 2 * e5
f6 = 2 * f5
g6 = 2 * g5
a6 = 2 * a5
b6 = 2 * b5
c7 = 2 * c6
d7 = 2 * d6
e7 = 2 * e6
f7 = 2 * f6
g7 = 2 * g6
a7 = 2 * a6
b7 = 2 * b6
c8 = 2 * c7
d8 = 2 * d7
e8 = 2 * e7
f8 = 2 * f7
g8 = 2 * g7
a8 = 2 * a7
b8 = 2 * b7



def obladiOblada():
    beep(.3,a4)
    beep(.3,a4)
    beep(.3,a4)
    beep(.3,g4)
    beep(.3,f4)
    beep(.3,f4)
    beep(.6,a4)
    beep(.3,a4)
    beep(1.2,f4)
    beep(.3,d4)
    beep(.3,d4)
    beep(.3,d4)
    beep(.3,a4)
    beep(.3,g4)
    beep(1.7,f4)
    beep(.3,c5)
    beep(.3,c5)
    beep(.3,c5)
    beep(.3,b4)
    beep(.3,a4)
    beep(.3,b4)
    beep(.3,c5)
    beep(.3,d5)
    beep(.3,d5)
    beep(.3,d5)
    beep(.3,c5)
    beep(.3,b4)
    beep(.3,a4)
    beep(.3,a4)
    beep(.3,b4)
    beep(.3,a4)
    beep(.3,g4)
    beep(.3,b4)
    beep(.3,a4)
    beep(.3,g4)
    beep(1.2,f4)



def twistAndShout():
    beep(.2,e4)
    beep(.2,e4)
    beep(.2,e5)
    beep(.2,e5)
    beep(.2,e4)
    beep(.2,e4)
    beep(.2,e5)
    beep(.2,c4)
    beep(.2,c4)
    beep(.2,f4)
    beep(.2,f4)
    beep(.1,c4)
    beep(.1,g5)
    beep(.2,c4)
    beep(.2,g5)
    beep(.2,e5)
    beep(.2,d5)
    beep(.2,c4)
    beep(.2,c4)
    beep(.2,c4)
    beep(.2,f4)
    beep(.2,c4)
    beep(.2,c4)
    beep(.2,f4)
    beep(.2,c4)
    beep(.2,c4)
    beep(.2,f4)
    beep(.2,f4)
    beep(.2,c4)
    beep(.2,c4)



def ring():
    beep(.8,g4)
    beep(.8,g4)
    beep(.8,e4)
    beep(.8,a4)
    beep(.8,g4)
    beep(.8,e4)
    beep(.8,g4)
    beep(.8,g4)
    beep(.8,e4)
    beep(.8,a4)
    beep(.8,g4)
    beep(.8,e4)


def shuffle():
    beep(.6,f5)
    wait(.1)
    beep(.6,f5)
    wait(.1)
    beep(.2,f5)
    beep(.2,c5)
    beep(.2,b4)
    beep(.2,b4)
    beep(.4,d5)
    beep(.4,c5)
    beep(.4,e5)
    beep(.6,e5)
    wait(.1)
    beep(.6,e5)
    wait(.1)
    beep(.2,d5)
    beep(.2,a4)
    beep(.2,g4)
    beep(.2,g4)
    beep(.2,b4)
    beep(.2,a4)
    beep(.2,c5)


def swerve():
    forward(1,1)
    rotate(.6,2)
    backward(1,1)
    forward(1,2)
    obladiOblada()



def swing():
    move(-1,1)
    wait(2.5)
    backward(1,1)
    twistAndShout()


def circle():
    for t in timer(6.8):
        forward(1)
        turnLeft(1)
    stop()
    ring()



def cupidShuffle():
    x = 0
    shuffle()
    while x < 4:
        turnLeft(1,.7)
        forward(1,.2)
        forward(1,.2)
        turnRight(1,1.4)
        forward(1,.2)
        forward(1,.2)
        turnLeft(1,.8)
        forward(1,.5)
        backward(1,.5)
        turnRight(1,.2)
        forward(1,.5)
        backward(1,.5)
        turnLeft(1,.7)
        x += 1



def morningRoutine(x):
    if x == 0:
        stop()
    if x == 1:
        circle()
    if x == 2:
        circle()
        swing()
    if x == 3:
        circle()
        swing()
        swerve()
    if x == 4:
        circle()
        swing()
        swerve()
        cupidShuffle()


def greetMenu():

    print ("1. Tiny Treats")
    print ("2. OK Treats")
    print ("3. Jumbo Treats")
    print ("0. Exit")

    treat = int(input("Which option would you like?"))

    if treat > 3:
        print("I'm sorry I cannot accept that.")
        greetMenu()

    elif treat == 1:
        setS2Volume(50)
        morningRoutine(2)
        setS2Volume(100)
        greetMenu()

    elif treat == 2:
        setS2Volume(75)
        morningRoutine(3)
        setS2Volume(100)
        greetMenu()

    elif treat == 3:
        morningRoutine(4)
        greetMenu()

    elif treat == 0:
        print ("Bye, bye! Have a good day!")




