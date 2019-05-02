import numpy as np
import random as rd

class RTLearner(object):

    def __init__(self, leaf_size=1, verbose = False, tree = None):
        self.verbose = verbose
        self.leaf_size = leaf_size
        self.tree = tree
        if verbose:
            self.verbosity()
        pass # move along, these aren't the drones you're looking for

    def recursiveTreeBuilder(self, dataX, dataY):
        sampleSize = dataX.shape[0]
        featuresSize = dataX.shape[1]
        tree = np.array([-1, np.mean(dataY), np.nan, np.nan])
        if sampleSize <= self.leaf_size or len(set(dataY)) == 1:
            self.tree = np.array([[-1, np.mean(dataY), np.nan, np.nan]])
            return self.tree
        randomList = rd.sample(list(range(featuresSize)), featuresSize)
        i = randomList[0]
        x = 1
        while len(set(dataX[:, i])) == 1 and x <= dataX.shape[1]:
            i = randomList[x]
            x += 1
        if len(set(dataX[:,i])) == 1:
            return [[-1, np.mean(dataY), np.nan, np.nan]]
        else:                    
            split = np.mean(np.random.choice(dataX[:, i], size = 2, replace = False))
            while split == np.amax(dataX[:,i]):
                split = np.mean(np.random.choice(dataX[:, i], size = 2, replace = False))
            li = dataX[:, i] <= split
            ri = dataX[:, i] > split
            lt = self.recursiveTreeBuilder(dataX[li], dataY[li])
            rt = self.recursiveTreeBuilder(dataX[ri], dataY[ri])
            root=[i, split,1,len(lt)+1]
            self.tree=np.vstack((root, lt, rt))
            return  self.tree
        return self.recursiveTreeBuilder(X,Y)

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