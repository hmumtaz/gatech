library(rsvd)
setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/Assignment 2/Datasets/")   
data = read.table("trainingData.csv", sep=";", header=T)
data.features = data
data.features$Something = NULL
result = rpca(data.features)
summary(result)
#                             PC1       PC2       PC3       PC4       PC5
#Explained variance     1.0510525 1.0370919 1.0292531 1.0234770 1.0180126
#Standard deviations    1.0252085 1.0183771 1.0145211 1.0116704 1.0089661
#Proportion of variance 0.1051052 0.1037092 0.1029253 0.1023477 0.1018013
#Cumulative proportion  0.1051052 0.2088144 0.3117397 0.4140874 0.5158887
#Eigenvalues            1.0510525 1.0370919 1.0292531 1.0234770 1.0180126
#                             PC6        PC7        PC8        PC9       PC10
#Explained variance     1.0036444 0.99750922 0.99124094 0.93361871 0.91509967
#Standard deviations    1.0018205 0.99875384 0.99561084 0.96623947 0.95660842
#Proportion of variance 0.1003644 0.09975092 0.09912409 0.09336187 0.09150997
#Cumulative proportion  0.6162531 0.71600407 0.81512816 0.90849003 1.00000000
#Eigenvalues            1.0036444 0.99750922 0.99124094 0.93361871 0.91509967
plot(result$sdev, type="l")