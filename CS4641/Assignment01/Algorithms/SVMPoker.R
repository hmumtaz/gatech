setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/pokerHands")
trainingData = read.table("trainingData.csv", sep=";", header=T)
testingData = read.table("testingData.csv", sep=";", header=T)
set.seed(54)
train = sample(1:nrow(trainingData), nrow(trainingData)/10) #2501
test = sample(1:nrow(testingData), nrow(testingData)/10) #100000
trainingData = trainingData[train,]
testingData = testingData[test,]
library(e1071)
svmModel = svm(Something~., data = trainingData)
prediction = predict(svmModel, testingData, type="class")
mean(prediction != testingData$Something) #0.43775
svmModel = svm(Something~., data = trainingData, kernel="polynomial")
prediction = predict(svmModel, testingData, type="class")
mean(prediction != testingData$Something) #0.49739