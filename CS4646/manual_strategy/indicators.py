from util import get_data, plot_data
import datetime as dt
import pandas as pd

def computeIndicators(syms = ['NFLX'], startDate = dt.datetime(2003,01,01), endDate = dt.datetime(2004,12,31), plot = False):
	dates = pd.date_range(startDate, endDate)
	pricesAll = get_data(syms, dates).ffill().bfill()
	pricesAll /= pricesAll.ix[0, :]
	pricesSpy = pricesAll['SPY']
	prices = pricesAll[syms]
	indicatorsDict = {}
	indicators = pd.DataFrame(index = prices.index, 
		columns = ['SMA-5', 'SMA-20' ,'MACD', 'BBLower', 'BBUpper'])
	for symbol in syms:
		indicators['SMA-5'] = prices[symbol].rolling(window = 5, min_periods = 5).mean().ffill().bfill()
		indicators['SMA-20'] = prices[symbol].rolling(window = 20, min_periods = 20).mean().ffill().bfill()
		indicators['MACD'] = (prices[symbol].ewm(span=26)).mean() - (prices[symbol].ewm(span=12)).mean()
		band = prices[symbol].rolling(window = 20, min_periods = 20).std().ffill().bfill()
		indicators['BBLower'] = indicators['SMA-20'] - (band *  2)
		indicators['BBUpper'] = indicators['SMA-20'] + (band *  2)
		indicatorsDict[symbol] = indicators
		if (plot == True):
			plot_data(indicators[['SMA-5', 'SMA-20']], title = "SMA-5 and SMA-20", xlabel = "Date", ylabel = "Value")
			graphData = pd.DataFrame(index = prices.index)
			graphData['BBUpper'] = indicators['BBUpper']
			graphData['BBLower'] = indicators['BBLower']
			graphData['Prices'] = prices[symbol]
			plot_data(graphData, title = "Bollinger Bands and Price")
			plot_data(indicators['MACD'], title = "MACD", ylabel = 'Value')
			plot_data(prices[symbol])
	return indicatorsDict