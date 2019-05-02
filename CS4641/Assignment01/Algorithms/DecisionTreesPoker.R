setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/pokerHands/")
trainingData = read.table("trainingData.csv", sep=";", header=T)
testingData = read.table("testingData.csv", sep=";", header=T)
set.seed(58)
train = sample(1:nrow(trainingData), nrow(trainingData)/60) #417 datapoints
trainingData = trainingData[train,]
library(tree)
treeModel = tree(Something~., trainingData)
plot(treeModel)
text(treeModel, pretty=0)
prediction = predict(treeModel, testingData, type="class")
mean(prediction != testingData$Something) #0.490002
cvTree = cv.tree(treeModel, FUN = prune.misclass)
plot(cvTree$size, cvTree$dev, type="b")
pruneTree = prune.misclass(treeModel, best=4)
plot(pruneTree)
text(pruneTree, pretty=0)
prunedPrediction = predict(pruneTree, testingData, type="class")
mean(prunedPrediction != testingData$Something) #0.510575