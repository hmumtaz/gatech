library(fastICA)
set.seed(50)
setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/Assignment 2/Datasets/")
data = read.table("studentsNumeric.csv", sep=";", header=TRUE)
data.features = data
data.features$High = NULL
results = fastICA(data.features, n.comp=5)
newMatrix = results$S
colnames(newMatrix) = list("Col1", "Col2", "Col3", "Col4", "Col5")
newMatrix = cbind(newMatrix, High = data$High)
plot(newMatrix[,"High"], newMatrix[,"Col2"])
