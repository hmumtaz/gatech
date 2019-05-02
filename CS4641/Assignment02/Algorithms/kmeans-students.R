setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/Assignment 2/Datasets/")
set.seed(50)
data = read.table("studentsNumeric.csv", sep=";", header=TRUE)
data.features = data
data.features$High = NULL
results = kmeans(data.features, 2)
table(results$cluster, data$High) #0.254 Error
#     no yes
#  1 117  46
#  2 733 148
plot(data[c("goout", "absences")], col=results$cluster)
plot(data[c("goout", "absences")], col=data$High)