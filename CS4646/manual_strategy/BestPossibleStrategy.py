from util import get_data
import datetime as dt
import pandas as pd
import numpy as np

def testPolicy(syms = ['NFLX'], startDate = dt.datetime(2003,01,01), endDate = dt.datetime(2006,12,31)):
	dates = pd.date_range(startDate, endDate)
	pricesAll = get_data(syms, dates).ffill().bfill()
	pricesAll /= pricesAll.ix[0, :]
	pricesSpy = pricesAll['SPY']
	prices = pricesAll[syms]
	strategy = pd.DataFrame(index = prices.index, columns = syms)
	strategy[syms] = np.where(prices[syms].shift(-1).fillna(0) >= prices[syms], 1000, -1000)
	strategy[syms] = np.where((strategy[syms].shift(1).fillna(0) == strategy[syms]), 0, 2 * strategy)
	strategy.iloc[0] /= 2
	return strategy