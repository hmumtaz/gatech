import pandas as pd
import numpy as np
import datetime as dt
import os
from util import get_data, plot_data
import ManualStrategy as ms

def compute_Portvals(strategy = ms, sv = 100000, commission = 0.00, impact = 0.00, **kwargs):

	orders = strategy.testPolicy(**kwargs)
	syms = list(orders)
	dates = pd.date_range(orders.index.values[0], orders.index.values[-1])
	prices_all = get_data(syms, dates).ffill().bfill()
	prices = prices_all[syms]
	portfolio = pd.DataFrame(index = orders.index, columns = syms)
	portfolio['Cash'] = 0
	portfolio[syms] = orders[syms]
	portfolio = portfolio[syms].fillna(0)
	portfolio *= prices * (1 + (impact * np.sign(portfolio)))
	portfolio['Cash'] = -portfolio.sum(axis = 1) - ((len(syms) - portfolio.eq(0).sum(axis = 1)) * commission)
	portfolio = portfolio.fillna(0)
	portfolio['Cash'][0] += sv
	portfolio['Cash'] = portfolio['Cash'].cumsum()
	portfolio[syms] = orders[syms].cumsum()
	portfolio[syms] *= prices
	portfolio['Net Value'] = portfolio.sum(axis = 1)
	portfolio['ChangeInValue'] = portfolio['Net Value'] / portfolio['Net Value'].ix[0, :]
	cr, adr, sddr, sr = [0.25, 0.001, 0.0005, 2.1]
	cr = (portfolio['Net Value'][-1] / portfolio['Net Value'][0]) - 1
	dr = (portfolio['Net Value'] / portfolio['Net Value'].shift(1)) - 1
	adr = dr.mean()
	sddr = dr.std()
	print cr, adr, sddr
	return portfolio