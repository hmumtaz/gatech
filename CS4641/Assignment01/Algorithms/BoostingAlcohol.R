setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/student")
data = read.table("students.csv", sep=";", header=TRUE)
set.seed(36)
train = sample(1:nrow(data), nrow(data)/2)
test = -train
trainingData = data[train,]
testingData = data[test,]
library(adabag)
library(tree)
boostModel = boosting(High~., data=trainingData)
p = predict(boostModel, testingData)
p$error #0.1072797
plot(errorevol(boostModel,testingData))