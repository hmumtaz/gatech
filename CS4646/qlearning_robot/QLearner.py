"""
Template for implementing QLearner  (c) 2015 Tucker Balch
"""

import numpy as np
import random as rand

class QLearner(object):

    def __init__(self, \
        num_states=100, \
        num_actions = 4, \
        alpha = 0.2, \
        gamma = 0.9, \
        rar = 0.5, \
        radr = 0.99, \
        dyna = 0, \
        verbose = False):

        self.verbose = verbose
        self.num_actions = num_actions
        self.s = 0
        self.a = 0

        self.rar = rar
        self.radr = radr
        self.alpha = alpha
        self.gamma = gamma

        self.q = np.zeros((num_states, num_actions))
        self.r = np.zeros((num_states, num_actions))

        self.t = np.zeros((num_states, num_actions, num_states))
        #DynaQ Helper: Keeps a copy of tranistions to help inform t update
        self.tCopy = self.t.copy()

        self.dyna = dyna
        self.num_states = num_states
        self.num_actions = num_actions

    def querysetstate(self, s):
        """
        @summary: Update the state without updating the Q-table
        @param s: The new state
        @returns: The selected action
        """
        maxQ = self.q[s, :].argmax()
        if np.random.uniform() < self.rar:
            action = rand.randint(0, self.num_actions-1)
        else:
            action = maxQ

        self.s = s
        self.a = action
        if self.verbose: 
            print "s =", s,"a =",action
        
        return action

    def query(self, s_prime, r):
        """
        @summary: Update the Q table and return an action
        @param s_prime: The new state
        @param r: The ne state
        @returns: The selected action
        """
        maxQ = self.q[s_prime, :].argmax()
        if np.random.uniform() < self.rar:
            action = rand.randint(0, self.num_actions-1)
        else:
            action = maxQ

        self.q[self.s, self.a] = ((1 - self.alpha) * self.q[self.s, self.a]) + (
            self.alpha * (r + (self.gamma * self.q[s_prime, maxQ])))
        self.rar = self.rar * self.radr

        if(self.dyna != 0):
            self.updateModel(self.s, self.a, s_prime, r)
            self.hallucinate()

        self.s = s_prime
        self.a = action
        if self.verbose:
            print "s =", s_prime,"a =",action,"r =",r
        return action

    #DynaQ Helper: Updates Model
    def updateModel(self, s, a, s_prime, r):
        self.tCopy[s, a, s_prime] = self.tCopy[s, a, s_prime] + 1
        self.t = self.tCopy / self.tCopy.sum(axis=2, keepdims=True)
        self.r[s, a] = ((1 - self.alpha) * self.r[s, a]) + (self.alpha * r)

    #DynaQ Helper: Hallucinates
    def hallucinate(self):
        for i in range(self.dyna):
            randS = rand.randint(0, self.num_states - 1)
            randA = rand.randint(0, self.num_actions - 1)
            tempT = self.t[randS, randA, :]
            tempR = self.r[randS, randA]

            s_prime = np.random.multinomial(100, tempT).argmax()
            maxQ = self.q[s_prime, :].argmax()

            self.q[randS, randA] = ((1 - self.alpha) * self.q[randS, randA]) + (
                self.alpha * (tempR + (self.gamma * self.q[s_prime, maxQ])))

    def author(self):
        return 'hmumtaz3'

if __name__=="__main__":
    print "Remember Q from Star Trek? Well, this isn't him"