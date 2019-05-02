setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/student")
data = read.table("students.csv", sep=";", header=TRUE)
set.seed(36)
train = sample(1:nrow(data), nrow(data)/2)
test = -train
trainingData = data[train,]
testingData = data[test,]
testingHigh = data$High[test]
library(tree)
treeModel = tree(trainingData$High~., trainingData)
plot(treeModel)
text(treeModel, pretty=0)
prediction = predict(treeModel, testingData, type="class")
mean(prediction != testingHigh) #0.1609195
cvTree = cv.tree(treeModel, FUN = prune.misclass)
plot(cvTree$size, cvTree$dev, type="b")
prunedTree = prune.misclass(treeModel, best=4)
plot(prunedTree)
text(prunedTree, pretty=0)
prunedPrediction = predict(prunedTree, testingData, type="class")
mean(prunedPrediction != testingHigh) #0.1475096