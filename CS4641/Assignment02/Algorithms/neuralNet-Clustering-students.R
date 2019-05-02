set.seed(50)
setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/Assignment 2/Datasets/")
data = read.table("studentsNumeric.csv", sep=";", header=TRUE)
data$guardian = NULL
data$High = ifelse(data$High == "yes", 1, 0)
data.features = data
data.features$High = NULL
pca = prcomp(data.features)
library(fastICA)
ica = fastICA(data.features, n.comp=5)
ica = ica$S
library(rsvd)
rca = rpca(data.features)
rca = predict(rca, data.features)
pca = data.frame(pca$x[,1:10])
pca = cbind(pca, data$High)
colnames(pca)[11] = "High"
ica = cbind(ica, data$High)
colnames(ica) = list("Col1", "Col2", "Col3", "Col4", "Col5", "High")
rca = cbind(rca, data$High)
colnames(rca)[28] = "High"
train = sample(1:nrow(data), nrow(data)/2)
test = -train
trainingData = pca[train,]
testingData = pca[test,]
dataNet = neuralnet(High~PC1+PC2+PC3+PC4+PC5+PC6+PC7+PC8+PC9+PC10, data=trainingData, hidden=c(4,2), lifesign="full")#52489      error: 13.67318 time: 1.79 mins
plot(dataNet)
prediction = compute(dataNet, testingData[,0:10])
prediction = prediction$net.result
results = data.frame(actual = testingData$High, prediction = prediction)
results$prediction = round(results$prediction)
mean(results$prediction != results$actual)#0.224137931
trainingData = ica[train,]
testingData = ica[test,]
dataNet = neuralnet(High~Col1+Col2+Col3+Col4+Col5, data=trainingData, hidden=c(4,2), lifesign="full")#5292		error: 27.56839 time: 9.38 secs
plot(dataNet)
prediction = compute(dataNet, testingData[,0:5])
prediction = prediction$net.result
results = data.frame(actual = testingData[,"High"], prediction = prediction)
results$prediction = round(results$prediction)
mean(results$prediction != results$actual)#0.1992337165
trainingData = rca[train,]
testingData = rca[test,]
f = as.formula(paste("High ~", paste(colnames(rca)[!colnames(rca) %in% "High"], collapse = " + ")))
dataNet = neuralnet(f, data=trainingData, hidden=c(4,2), lifesign="full")#1014	error: 10.23293 time: 2.73 secs
plot(dataNet)
prediction = compute(dataNet, testingData[,0:27])
prediction = prediction$net.result
results = data.frame(actual = testingData[,"High"], prediction = prediction)
results$prediction = round(results$prediction)
mean(results$prediction != results$actual)#0.1858237548