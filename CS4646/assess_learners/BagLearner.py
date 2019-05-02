import numpy as np

class BagLearner(object):

    def __init__(self, learner, kwargs, bags, boost, verbose):
        self.verbose = verbose
        self.kwargs = kwargs
        self.bags = bags
        self.boost = boost
        self.learner = learner
        pass # move along, these aren't the drones you're looking for
        
           
    def addEvidence(self, dataX, dataY):
        self.learners = []
        for i in range(self.bags):
            self.learners.append(self.learner(**self.kwargs))
        for l in self.learners:
            a = np.random.randint(0, high = dataX.shape[0], size = dataX.shape[0])
            l.addEvidence(dataX, dataY)   
        if self.verbose:
            print self.learners
        return self.learners


    
    def query(self, points):
        Result=[]
        for l in self.learners:
            Result.append(l.query(points))                  
        if self.verbose:
            print np.mean(Result, axis=0)
        return np.mean(Result, axis=0)