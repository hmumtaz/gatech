"""Hussain Mumtaz
husmum@gatech.edu

I worked on the homework assignment alone,
using only this semester's course materials.
"""


def machtoFPS():
    #Convert Mach speeds to Feet Per Second
    a = input ("Enter speed in Mach")
    mach = float(a)
    fps = mach * 1116.4370079
    print (fps, "feet/second")


machtoFPS()


def sqPyramidVolume():
    #Calculate the volume of a square pyramid
    a = input ("Enter the length of one side of the base in inches")
    side = float(a)
    b = input ("Enter the height of the pyramid in inches")
    height = float(b)
    volume = side * side * height
    print ("The volume of the square pyramid is", volume,
                "inches-cubed")


sqPyramidVolume()


def makeChange():
    a = input("Please enter your exact change")
    change = int(a)
    dollars = change // 100
    change2 = change % 100
    quarters = change2 // 25
    change3 = change2 % 25
    dimes = change3 // 10
    change4 = change3 % 10
    nickels = change4 // 5
    pennies = change4 % 5
    print ("You have", dollars, "dollars", quarters,
             "quarters", dimes, "dimes", nickels, "nickels",
              pennies, "pennies")


makeChange()


def PPIIndex():
    a = input("Please enter your weight in pounds")
    weight = float(a)
    b = input ("Please enter your height in inches")
    height = float(b)
    PPI = weight / height * 1.125
    print ("Your corrected PPI is", PPI)

PPIIndex()