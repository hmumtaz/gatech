"""Hussain Mumtaz
husmum@gatech.edu

I worked on the homework assignment alone,
using only this semester's course materials."""

def tallEnough(height):

    convFactor = 1/0.39370

    cmHeight = height * convFactor

    if cmHeight < 120:
        return False

    elif cmHeight > 190:
        return False

    else:
        return True



def whereIsWaldo(x,y):

    guessX = int(input("Guess Waldo's x-coordinate"))
    guessY = int(input("Guess Waldo's y-coordinate"))

    if guessX == x and guessY == y:
        print("You found Waldo")

    else:
        print("Couldn't find Waldo. Better luck next time")



def allLetters (userString):

    import string

    aString = ""
    for letter in userString:
        if (letter in string.ascii_letters):
            aString += letter
    return aString



def replaceLetter(aString,aLetter):

    toReplace = input("Input a letter")

    newString = ""

    if toReplace not in aString:
        print ("Letter not in string")
        replaceLetter(aString,aLetter)

    for x in aString:

        if x in toReplace:
            newString += aLetter

        if x not in toReplace:
            newString += x

    print (newString)



def countUp (start,end,increment):

    while start < end:
        print (start)
        start += increment

    print (end)
    print ("Done!")



def numMountainRange(X):

    for i in range(1,X+1):
        value = str(i) * i
        space = "  " * (X-1)
        mountain = value + space + value
        print (mountain)
        X -= 1



def printTimestable ():

    title = "times:"
    for number in range(1,10):
        title += "{:4}".format (number) # Space between header numbers
    print (title)

    for x in range(1,10):
        line = "{:6}".format(x) # Initial Space
        for y in range(1,10):
            line += "{:4}".format(x * y) # Space between individual numbers
        print(line)



def printTimes (N):
    return None

    title = "times:"

    for number in range(1,N+1):
        title += "{:4}".format (number) # Space between header numbers
    print(title)



    for x in range(1,N+1):
        line = "{:6}".format(x) # Initial Space
        for y in range(1,N+1):
            line += "{:4}".format(x * y) # Space between individual numbers
        print(line)