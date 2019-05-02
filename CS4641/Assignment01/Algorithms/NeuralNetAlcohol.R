setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/student")
data = read.table("studentsNumeric.csv", sep=";", header=TRUE)
data$guardian = NULL
data$High = ifelse(data$High == "yes", 1, 0)
maxs = apply(data,2,max)
mins = apply(data,2,min)
data = as.data.frame(scale(data, center=mins, scale=maxs-mins))
set.seed(36)
train = sample(1:nrow(data), nrow(data)/2)
test = -train
trainingData = data[train,]
testingData = data[test,]
library(neuralnet)
n = names(trainingData)
f = as.formula(paste("High ~", paste(n[!n %in% "High"], collapse = " + ")))
dataNet = neuralnet(f, data=trainingData, hidden=c(6,4,2), lifesign="full") #5108 error: 3.92323  time: 11.77 secs 
plot(dataNet)
prediction = compute(dataNet, testingData[,0:27])
prediction = prediction$net.result
results = data.frame(actual = testingData$High, prediction = prediction)
results$prediction = round(results$prediction)
mean(results$prediction != results$actual) #0.1743295019
