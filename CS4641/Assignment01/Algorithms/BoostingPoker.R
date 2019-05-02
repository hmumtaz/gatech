setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/pokerHands")
trainingData = read.table("trainingData.csv", sep=";", header=T)
testingData = read.table("testingData.csv", sep=";", header=T)
set.seed(54)
train = sample(1:nrow(trainingData), nrow(trainingData)/10) #2501
test = sample(1:nrow(testingData), nrow(testingData)/10) #100000
trainingData = trainingData[train,]
testingData = testingData[test,]
library(adabag)
library(tree)
boostModel = boosting(Something~., data=trainingData)
p = predict(boostModel, testingData)
p$error #0.39349
plot(errorevol(boostModel,testingData))