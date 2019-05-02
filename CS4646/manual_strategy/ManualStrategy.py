from util import get_data
import datetime as dt
import pandas as pd
import numpy as np
import indicators as ind

def testPolicy(syms = ['NFLX'], startDate = dt.datetime(2003,01,01), endDate = dt.datetime(2004,12,31)):
	indicatorsDict = ind.computeIndicators(syms, startDate, endDate)
	dates = pd.date_range(startDate, endDate)
	pricesAll = get_data(syms, dates).ffill().bfill()
	pricesAll /= pricesAll.ix[0, :]
	pricesSpy = pricesAll['SPY']
	prices = pricesAll[syms]
	strategy = pd.DataFrame(index = prices.index, columns = syms)
	for symbol in syms:
		indicators = indicatorsDict[symbol]
		strategy[symbol] = 0
		strategy[symbol] += np.where(indicators['SMA-5'] < indicators['SMA-20'], 1, -1)
		strategy[symbol] += np.where((indicators['MACD'] > 0) & (indicators['MACD'].shift(1).fillna(0) < 0), 1, -1)
		strategy[symbol] += np.where((indicators['MACD'] < 0) & (indicators['MACD'].shift(1).fillna(0) > 0), -1, 1)
		strategy[symbol] += np.where(prices[symbol] > indicators['BBUpper'], -1, 0)
		strategy[symbol] += np.where(prices[symbol] > indicators['BBLower'], 1, 0)
		strategy[symbol] = np.where(strategy[symbol] > 0, 1000, -1000)
		strategy[symbol] = np.where(strategy[symbol].shift(1).fillna(0) == strategy[symbol], 0, 2 * strategy[symbol])
		strategy.iloc[0] /= 2
	return strategy