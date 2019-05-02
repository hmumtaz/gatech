set.seed(50)
setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/Assignment 2/Datasets/")
data = read.table("studentsNumeric.csv", sep=";", header=TRUE)
data.features = data
data.features$High = NULL
pca = prcomp(data.features)
library(fastICA)
ica = fastICA(data.features, n.comp=5)
ica = ica$S
colnames(ica) = list("Col1", "Col2", "Col3", "Col4", "Col5")
data.features$guardian = NULL
library(rsvd)
rca = rpca(data.features)
rca = predict(rca, data.features)
pca = data.frame(pca$x[,1:10])
pcaKMeans = kmeans(pca, 2)
pcaKMeans = pcaKMeans$cluster
pcaKMeans = cbind(pcaKMeans, data$High)
colnames(pcaKMeans)[2] = "High"
library(EMCluster)
pcaEM = em.EM(pca, nclass=2)
pcaEM = pcaEM$class
pcaEM = cbind(pcaEM, data$High)
colnames(pcaEM)[2] = "High"
icaKMeans = kmeans(ica, 2)
icaKMeans = icaKMeans$cluster
icaKMeans = cbind(icaKMeans, data$High)
colnames(icaKMeans)[2] = "High"
icaEM = em.EM(ica, nclass=2)
icaEM = icaEM$class
icaEM = cbind(icaEM, data$High)
colnames(icaEM)[2] = "High"
rcaKMeans = kmeans(rca, 2)
rcaKMeans = rcaKMeans$cluster
rcaKMeans = cbind(rcaKMeans, data$High)
colnames(rcaKMeans)[2] = "High"
rcaEM = em.EM(rca, nclass=2)
rcaEM = rcaEM$class
rcaEM = cbind(rcaEM, data$High)
colnames(rcaEM)[2] = "High"
train = sample(1:nrow(data), nrow(data)/2)
test = -train
trainingData = pcaKMeans[train,]
testingData = pcaKMeans[test,]
library(neuralnet)
dataNet = neuralnet("High~pcaKMeans", data=trainingData, lifesign="full")#49 error: 39.80233 time: 0.02 secs
plot(dataNet)
prediction = compute(dataNet, testingData[,0:1])
prediction = prediction$net.result
results = data.frame(actual = testingData[,"High"], prediction = prediction)
results$prediction = round(results$prediction)
mean(results$prediction != results$actual) #0.183908046
trainingData = pcaEM[train,]
testingData = pcaEM[test,]
dataNet = neuralnet("High~pcaEM", data=trainingData, lifesign="full") #228 error: 39.11425 time: 0.25 secs
plot(dataNet)
prediction = compute(dataNet, testingData[,0:1])
prediction = prediction$net.result
results = data.frame(actual = testingData[,"High"], prediction = prediction)
results$prediction = round(results$prediction)
mean(results$prediction != results$actual) #0.183908046
trainingData = icaKMeans[train,]
testingData = icaKMeans[test,]
dataNet = neuralnet("High~icaKMeans", data=trainingData, lifesign="full") #103 error: 39.63202 time: 0.03 secs
plot(dataNet)
prediction = compute(dataNet, testingData[,0:1])
prediction = prediction$net.result
results = data.frame(actual = testingData[,"High"], prediction = prediction)
results$prediction = round(results$prediction)
mean(results$prediction != results$actual) #0.183908046
trainingData = icaEM[train,]
testingData = icaEM[test,]
dataNet = neuralnet("High~icaEM", data=trainingData, lifesign="full") #63 error: 39.07061 time: 0.03 secs
plot(dataNet)
prediction = compute(dataNet, testingData[,0:1])
prediction = prediction$net.result
results = data.frame(actual = testingData[,"High"], prediction = prediction)
results$prediction = round(results$prediction)
mean(results$prediction != results$actual) #0.183908046
trainingData = rcaKMeans[train,]
testingData = rcaKMeans[test,]
dataNet = neuralnet("High~rcaKMeans", data=trainingData, lifesign="full") #78 error: 39.80267 time: 0.04 secs
plot(dataNet)
prediction = compute(dataNet, testingData[,0:1])
prediction = prediction$net.result
results = data.frame(actual = testingData[,"High"], prediction = prediction)
results$prediction = round(results$prediction)
mean(results$prediction != results$actual) #0.183908046
trainingData = rcaEM[train,]
testingData = rcaEM[test,]
dataNet = neuralnet("High~rcaEM", data=trainingData, lifesign="full") #177 error: 39.59025 time: 0.05 secs
plot(dataNet)
prediction = compute(dataNet, testingData[,0:1])
prediction = prediction$net.result
results = data.frame(actual = testingData[,"High"], prediction = prediction)
results$prediction = round(results$prediction)
mean(results$prediction != results$actual) #0.183908046