"""

Hussain Mumtaz
husmum@gatech.edu

I (we for pair programming assignments) worked on the
homework assignment alone, using only this semester's
course materials."""

#Sorry about the delay, I thought getting our name signed
# in recitation was equivilent to submission.

from Myro import *

init("sim")


def getValues(numSamples):
    valueList = []
    while numSamples > 0:
        a = getLight('center')
        valueList.append(a)
        turnLeft(1,.25)
        numSamples -= 1
    return valueList


def printStatistics (numList = []):

    average = sum(numList)/(len(numList))

    minimum = min(numList)

    maximum = max(numList)

    even = 0
    for n in numList:
        if n%2 == 0:
            even += 1

    print ("You gave me a list of", len(numList),
            "numbers. Their average was", average,
            ". The largest was", maximum, ", the smallest was",
            minimum, ". Only", even, "of them were even numbers.")

a = getValues(5)

printStatistics (a)
