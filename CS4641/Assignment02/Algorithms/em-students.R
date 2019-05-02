library(EMCluster)
setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/Assignment 2/Datasets/")
set.seed(50)
data = read.table("studentsNumeric.csv", sep=";", header=TRUE)
data.features = data
data.features$High = NULL
results = em.EM(data.features, nclass = 2)
summary(results)
# Method: em.EM
# n = 1044, p = 28, nclass = 2, flag = 0, total parameters = 869,
# logL = 332792.1451, AIC = -663846.2903, BIC = -659544.0323.
#nc: 
#[1] 522 522
#pi: 
#[1] 0.8707 0.1293
plotem(results, data.features)
table(results$class, data$High)# 0.498 Error  
#     no yes
#  1 426  96
#  2 424  98