setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/student")
data = read.table("studentsNumeric.csv", sep=";", header=TRUE)
set.seed(36)
train = sample(1:nrow(data), nrow(data)/2)
test = -train
trainingData = data[train,]
testingData = data[test,]
testingLabels = testingData[,29]
trainingLabels = trainingData[,29]
trainingData$guardian = NULL #Issue
testingData$guardian = NULL #Issue
trainingData$High = NULL
testingData$High = NULL
maxs = apply(testingData,2,max)
mins = apply(testingData,2,min)
trainingData = as.data.frame(scale(trainingData, center = mins, scale = maxs -mins))
testingData = as.data.frame(scale(testingData, center = mins, scale = maxs -mins))
library(class)
library(gmodels)
prediction = knn(train = trainingData, test = testingData, cl=trainingLabels, k=20) #0.15900383 error
CrossTable(x = testingLabels, y = prediction, prop.chisq=F)
prediction2 = knn(train = trainingData, test = testingData, cl=trainingLabels, k=15)#0.17432950 error
CrossTable(x = testingLabels, y = prediction2, prop.chisq=F)
prediction3 = knn(train = trainingData, test = testingData, cl=trainingLabels, k=10)#0.16475096 error
CrossTable(x = testingLabels, y = prediction3, prop.chisq=F)
prediction4 = knn(train = trainingData, test = testingData, cl=trainingLabels, k=5) #0.16475096 error
CrossTable(x = testingLabels, y = prediction4, prop.chisq=F)
prediction5 = knn(train = trainingData, test = testingData, cl=trainingLabels, k=1) #0.15134010 error
CrossTable(x = testingLabels, y = prediction5, prop.chisq=F)
