"""
Template for implementing StrategyLearner  (c) 2016 Tucker Balch
"""

import datetime as dt
import pandas as pd
import util as ut
import indicators as ind
from QLearner import QLearner
import random

class StrategyLearner(object):

    # constructor
    def __init__(self, verbose = False, impact=0.0):
        self.verbose = verbose
        self.impact = impact
        self.ql = QLearner(num_states = 10000, \
            num_actions = 3, alpha = 0.2, \
            gamma = 0.9, rar = 0.5, radr = 0.99, \
            dyna = 0, verbose = False)

    # this method should create a QLearner, and train it for trading
    def addEvidence(self, symbol = "IBM", \
        sd=dt.datetime(2008,1,1), \
        ed=dt.datetime(2009,1,1), \
        sv = 10000): 

        # add your code to do learning here
        dates = pd.date_range(sd, ed)
        pricesAll = ut.get_data([symbol], dates).ffill().bfill()
        pricesAll /= pricesAll.ix[0, :]
        prices = pricesAll[symbol]
        prices.index = prices.index.map(lambda t: t.strftime('%Y-%m-%d'))
        pricesSpy = pricesAll['SPY']

        percentChangeInPrices =  prices.diff().ffill().bfill()
        # indicatorsDict = { "IBM" : ['SMA-5', 'SMA-20' ,'MACD', 'BBLower', 'BBUpper']}
        indicatorsDict = ind.computeIndicators(syms= [symbol], \
            startDate = sd, endDate = ed, plot = False)
        indicatorsDict = self.descretize(indicatorsDict)

        indicators = indicatorsDict[symbol]
        indicators.index = indicators.index.map(lambda t: t.strftime('%Y-%m-%d'))
        
        initialState = indicators.iloc[0]['State']
        self.ql.querysetstate(initialState)

        orders = pd.DataFrame(0, index = prices.index, columns = ['Shares'])

        i = 0
        orders_copy = orders.copy()
        while i < 100:
            i += 1
            reward = 0
            holdings = 0

            if (i > 20) and (orders.equals(orders_copy)):
                break
            for index, row in prices.iteritems():
                reward = holdings * percentChangeInPrices.loc[index] * (1 - self.impact)
                a = self.ql.query(indicators.loc[index]['State'], reward)
                if (a == 1) and (holdings < 1000):
                    if (holdings == 0):
                        orders.loc[index]['Shares'] = 1000
                        holdings += 1000
                    else:
                        orders.loc[index]['Shares'] = 2000
                        holdings += 2000
                elif (a == 2) and (holdings > -1000):
                    if (holdings == 0):
                        orders.loc[index]['Shares'] = -1000
                        holdings -= 1000
                    else:
                        orders.loc[index]['Shares'] = -2000
                        holdings -= 2000

    def descretize(self, indicatorsDict):
        for symbol in indicatorsDict.keys():
            smaBins = [.3, .4, .5, .6, .7, .8, .9, 1, 1.1, 1.2, 1.3]
            macdBins = [-0.08, -0.064, -0.048, -0.032, -0.014, 0, 0.016, 0.031, 0.047, 0.063, 0.08]
            bbDiffBins = [0, .1, .2, .3, .4, .5, .6, .7, .8, .9, 1]
            labels = [0,1,2,3,4, 5, 6, 7, 8, 9]
            indicatorsDict[symbol]['SMA-5'] = pd.cut(
                indicatorsDict[symbol]['SMA-5'], smaBins, labels = labels)
            indicatorsDict[symbol]['SMA-20'] = pd.cut(
                indicatorsDict[symbol]['SMA-20'], smaBins, labels = labels)
            indicatorsDict[symbol]['MACD'] = pd.cut(
                indicatorsDict[symbol]['MACD'], macdBins, labels = labels)
            indicatorsDict[symbol]['BBDiff'] = indicatorsDict[symbol]['BBUpper'] - (
                indicatorsDict[symbol]['BBLower'])
            indicatorsDict[symbol]['BBDiff'] = pd.cut(
                indicatorsDict[symbol]['BBDiff'], bbDiffBins, labels = labels)
            indicatorsDict[symbol]['State'] = indicatorsDict[symbol]['SMA-5'].astype(int) * (
                1000) + indicatorsDict[symbol]['SMA-20'].astype(int) * (
                100) + indicatorsDict[symbol]['MACD'].astype(int) * (
                10) + indicatorsDict[symbol]['BBDiff'].astype(int)
            indicatorsDict[symbol] = indicatorsDict[symbol].drop('BBDiff', axis = 1)
            indicatorsDict[symbol] = indicatorsDict[symbol].drop('BBLower', axis = 1)
            indicatorsDict[symbol] = indicatorsDict[symbol].drop('BBUpper', axis = 1)
            indicatorsDict[symbol] = indicatorsDict[symbol].drop('SMA-5', axis = 1)
            indicatorsDict[symbol] = indicatorsDict[symbol].drop('SMA-20', axis = 1)
            indicatorsDict[symbol] = indicatorsDict[symbol].drop('MACD', axis = 1)
        return indicatorsDict
    # this method should use the existing policy and test it against new data
    def testPolicy(self, symbol = "IBM", \
        sd=dt.datetime(2009,1,1), \
        ed=dt.datetime(2010,1,1), \
        sv = 10000):

        dates = pd.date_range(sd, ed)
        pricesAll = ut.get_data([symbol], dates).ffill().bfill()
        pricesAll /= pricesAll.ix[0, :]
        prices = pricesAll[symbol]
        orders = pd.DataFrame(0, index = prices.index, columns = ['Shares'])
        prices.index = prices.index.map(lambda t: t.strftime('%Y-%m-%d'))
        pricesSpy = pricesAll['SPY']
        percentChangeInPrices =  prices.diff().ffill().bfill()
        # indicatorsDict = { "IBM" : ['SMA-5', 'SMA-20' ,'MACD', 'BBLower', 'BBUpper']}
        indicatorsDict = ind.computeIndicators(syms= [symbol], \
            startDate = sd, endDate = ed, plot = False)
        indicatorsDict = self.descretize(indicatorsDict)

        indicators = indicatorsDict[symbol]
        indicators.index = indicators.index.map(lambda t: t.strftime('%Y-%m-%d'))
        
        initialState = indicators.iloc[0]['State']
        self.ql.querysetstate(initialState)

        reward = 0
        holdings = 0
        for index, row in prices.iteritems():
            reward = holdings * percentChangeInPrices.loc[index] * (1 - self.impact)
            a = self.ql.query(indicators.loc[index]['State'], reward)
            if (a == 1) and (holdings < 1000):
                if (holdings == 0):
                    orders.loc[index]['Shares'] = 1000
                    holdings += 1000
                else:
                    orders.loc[index]['Shares'] = 2000
                    holdings += 2000
            elif (a == 2) and (holdings > -1000):
                if (holdings == 0):
                    orders.loc[index]['Shares'] = -1000
                    holdings -= 1000
                else:
                    orders.loc[index]['Shares'] = -2000
                    holdings -= 2000
        return orders


if __name__=="__main__":
    print "One does not simply think up a strategy"
