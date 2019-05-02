setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/pokerHands/")
trainingData = read.table("trainingData.csv", sep=";", header=T)
testingData = read.table("testingData.csv", sep=";", header=T)
set.seed(54)
train = sample(1:nrow(trainingData), nrow(trainingData)/10) #2501
test = sample(1:nrow(testingData), nrow(testingData)/10) #100000
trainingData = trainingData[train,]
testingData = testingData[test,]
testingLabels = testingData[,11]
trainingLabels = trainingData[,11]
trainingData$Something = NULL
testingData$Something = NULL
maxs = apply(testingData,2,max)
mins = apply(testingData,2,min)
trainingData = as.data.frame(scale(trainingData, center = mins, scale = maxs -mins))
testingData = as.data.frame(scale(testingData, center = mins, scale = maxs -mins))
library(class)
library(gmodels)
prediction = knn(train = trainingData, test = testingData, cl=trainingLabels, k=20) #0.45820 error
CrossTable(x = testingLabels, y = prediction, prop.chisq=F)
prediction2 = knn(train = trainingData, test = testingData, cl=trainingLabels, k=15)#0.45676 error
CrossTable(x = testingLabels, y = prediction2, prop.chisq=F)
prediction3 = knn(train = trainingData, test = testingData, cl=trainingLabels, k=10)#0.46023 error
CrossTable(x = testingLabels, y = prediction3, prop.chisq=F)
prediction4 = knn(train = trainingData, test = testingData, cl=trainingLabels, k=5)#0.46352 error
CrossTable(x = testingLabels, y = prediction4, prop.chisq=F)
prediction5 = knn(train = trainingData, test = testingData, cl=trainingLabels, k=1)#0.47045 error
CrossTable(x = testingLabels, y = prediction5, prop.chisq=F)