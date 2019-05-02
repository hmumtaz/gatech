setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/pokerHands/")
trainingData = read.table("trainingData.csv", sep=";", header=T)
testingData = read.table("testingData.csv", sep=";", header=T)
trainingData$Something = ifelse(trainingData$Something == "yes", 1, 0)
testingData$Something = ifelse(testingData$Something == "yes", 1, 0)
maxs = apply(testingData,2,max)
mins = apply(testingData,2,min)
trainingData = as.data.frame(scale(trainingData, center = mins, scale = maxs -mins))
testingData = as.data.frame(scale(testingData, center = mins, scale = maxs -mins))
library(neuralnet)
dataNet = neuralnet(Something~S1+C1+S2+C2+S3+C3+S4+C4+S5+C5, data=trainingData, hidden=c(6,4), lifesign="full", threshold=0.1, stepmax=1e+07) #652666     error: 2540.02635       time: 7.1 hours
prediction = compute(dataNet, testingData[,0:10])
prediction = prediction$net.result
results = data.frame(actual = testingData$Something, prediction = prediction)
results$prediction = round(results$prediction)
mean(results$prediction != results$actual) #0.34266