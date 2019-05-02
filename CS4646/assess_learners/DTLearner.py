import numpy as np
import pandas as pd
from scipy.stats import pearsonr

class DTLearner(object):

    def __init__(self, leaf_size=1, verbose=False, tree=None):
        self.leaf_size = leaf_size
        self.verbose = verbose
        self.tree = tree
        if verbose:
            self.verbosity()
        pass # move along, these aren't the drones you're looking for

    def recursiveTreeBuilder(self, dataX, dataY):
        sampleSize = dataX.shape[0]
        featuresSize = dataX.shape[1]
        tree = np.array([-1, np.median(dataY), np.nan, np.nan])

        if sampleSize <= self.leaf_size or len(set(dataY)) == 1:
            return tree

        splittable = list(range(featuresSize))
        r = []
        for i in range(featuresSize):
            abs_r = abs(pearsonr(dataX[:, i], dataY)[0])
            if np.isnan(abs_r):
                abs_r = 0.0
            r.append((i, abs_r))
        r.sort(key = lambda x: x[1], reverse = True)
        r_i = 0
        while len(splittable) > 0:
            best = r[r_i][0]
            split = np.median(dataX[:, best])
            li = dataX[:, best] <= split
            ri = dataX[:, best] > split
            if len(np.unique(li)) != 1:
                break
            splittable.remove(best)
            r_i += 1
        if len(splittable) == 0:
            return tree                  
        lt = self.recursiveTreeBuilder(dataX[li], dataY[li])
        rt = self.recursiveTreeBuilder(dataX[ri], dataY[ri])
        if lt.ndim == 1:
            rt_start = 2
        elif lt.ndim > 1:
            rt_start = lt.shape[0] + 1
        root = np.array([best, split, 1, rt_start])
        return np.vstack((root, lt, rt))
    

    def singleQuery(self, point, row):
        feat, split = self.tree[row, 0:2]
        if feat == -1:
            return split
        elif point[int(feat)] <= split:
            pred = self.singleQuery(point, row + int(self.tree[row, 2]))
        else:
            pred = self.singleQuery(point, row + int(self.tree[row, 3]))
        return pred


    def addEvidence(self, dataX, dataY):
        if self.tree is None:
            self.tree = self.recursiveTreeBuilder(dataX, dataY)
        else:
            self.tree = np.vstack((self.tree, self.recursiveTreeBuilder(dataX, dataY)))
        if len(self.tree.shape) == 1:
            self.tree = np.expand_dims(self.tree, axis=0)
        if self.verbose:
            self.verbosity()
        
        
    def query(self, points):
        preds = []
        for point in points:
            preds.append(self.singleQuery(point, row=0))
        return np.array(preds)


    def verbosity(self):
        print ("leaf_size :", self.leaf_size)
        if self.tree is not None:
            print ("tree shape :", self.tree.shape)
            print ("tree :")
            df_tree = pd.DataFrame(self.tree, 
                columns=["factor", "split", "left", "right"])
            df_tree.index.name = "node"
            print (df_tree)
        else:
            print ("Tree has no data")