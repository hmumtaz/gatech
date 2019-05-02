""" Hussain Mumtaz
    Caera Baage
    husmum@gatech.edu

    We worked on the homework assignment alone,
    using only this semester's course materials."""



def quesoForFishy (balance):

    """ On entering balance function, returns
        whole number of queso jars that can
        be purchased """


    division = balance/2.38 #Queso Jars are $2.38
    jars= int(division)
    return jars



def mailboxes (number):

    """On entering number of mailboxes hit
       function returns the range of the
       decrease in lifespan"""


    low = number * 2 #minimum months removed

    high = number * 6 #maximum months removed

    print ("Because you have run into", number, "Mailbox(es),",
           "your car's lifespan has been shortened anywhere" ,
           "from", low, "to", high, "months")



def concertTicket():

    """Calculates the total number of hours of work
       required to pay off a concert ticket"""


    p =(input ("What is the ticket price?"))
    price = float (p)

    w = (input ("What is your hourly wage?"))
    wage = float (w)

    h = price/wage
    hours = round(h,2)

    print ("You need to work", hours, "hours to buy your",
            "ticket")



def boringInterlude (radiusIn):

    """Boring function that finds the volume of a sphere
       in cubic feet given the radius in inches"""


    import math
    volIn = (4/3) * math.pi * (radiusIn ** 3)
    vol = volIn/ 1728
    return vol


def trafficJam(lanes,miles):

    """Gives an estimate for the total number of cars
       in a traffic jam, depending on number of lanes and
       miles"""


    feet = miles * 5280 #Feet in a mile
    total_feet = feet * lanes #Total feet in all lanes
    cars = total_feet / 15 #Average feet a car takes

    return cars



def timeLeft(minUsed):

    """Given the minutes used, the function asks for
       battery life and prints the battery percentage
       and returns the minutes of battery life left"""


    h = (input("How many hours does the battery last?"))
    hours = int(h)
    minutes = float(hours * 60)

    minsLeft = (minutes - minUsed)

    p = (minsLeft / minutes) * 100
    percent = int(p)

    print (percent)

    return minsLeft



def daffodils(flNeeded,amtPaid, dzCost):

    """Given the flowers needed, the neighbor's
       contribution, and the cost of a dozen flowers
       the function outputs the amount you will need
       to contribute"""


    import math

    dz = flNeeded / 12
    dozens = math.ceil (dz) #Rounds up to the nearest dozen

    totCost = dzCost * dozens
    toPay = totCost - amtPaid

    print ("You will need to contribute", toPay)
