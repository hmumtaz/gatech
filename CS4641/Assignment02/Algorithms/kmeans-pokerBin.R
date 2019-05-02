setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/Assignment 2/Datasets/")   
set.seed(50)
data = read.table("trainingData.csv", sep=";", header=T)
data.features = data
data.features$Something = NULL
results = kmeans(data.features, 2)
table(results$cluster, data$Something) #0.497 Error
#      no  yes
#  1 6232 6201
#  2 6261 6316
plot(data[c("C1", "C2")], col=results$cluster)
plot(data[c("C1", "C2")], col=data$Something)