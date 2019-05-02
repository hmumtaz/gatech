library(fastICA)
setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/Assignment 2/Datasets/")   
data = read.table("trainingData.csv", sep=";", header=T)
data.features = data
data.features$Something = NULL
results = fastICA(data.features, n.comp=5)
newMatrix = results$S
colnames(newMatrix) = list("Col1", "Col2", "Col3", "Col4", "Col5")
newMatrix = cbind(newMatrix, Something = data$Something)
plot(newMatrix[,"Something"], newMatrix[,"Col2"])
