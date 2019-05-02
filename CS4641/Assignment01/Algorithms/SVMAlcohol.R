setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/student")
library(e1071)
data = read.table("students.csv", sep=";", header=TRUE)
set.seed(36)
train = sample(1:nrow(data), nrow(data)/2)
test = -train
trainingData = data[train,]
testingData = data[test,]
svmModel = svm(High~., data = trainingData)
prediction = predict(svmModel, testingData, type="class")
mean(prediction != testingData$High) #0.137931
svmModel = svm(High~., data = trainingData, kernel="polynomial")
prediction = predict(svmModel, testingData, type="class")
mean(prediction != testingData$High) #0.1551724