setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/Assignment 2/Datasets/")   
data = read.table("trainingData.csv", sep=";", header=T)
data.features = data
data.features$Something = NULL
results = prcomp(data.features)
summary(results)
#Importance of components:
#                          PC1    PC2    PC3    PC4    PC5     PC6     PC7
#Standard deviation     3.8136 3.7790 3.7722 3.7602 3.6228 1.14330 1.12838
#Proportion of Variance 0.1899 0.1865 0.1858 0.1846 0.1714 0.01707 0.01663
#Cumulative Proportion  0.1899 0.3764 0.5622 0.7468 0.9182 0.93530 0.95193
#                          PC8     PC9  PC10
#Standard deviation     1.1273 1.12315 1.072
#Proportion of Variance 0.0166 0.01647 0.015
#Cumulative Proportion  0.9685 0.98500 1.000
plot(results, type="l")