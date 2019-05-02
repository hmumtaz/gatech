library(EMCluster)
setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/Assignment 2/Datasets/")   
data = read.table("trainingData.csv", sep=";", header=T)
data.features = data
data.features$Something = NULL
results = em.EM(data.features, nclass = 2)
summary(results)
#Method: em.EM
# n = 25010, p = 10, nclass = 2, flag = 0, total parameters = 131,
# logL = -532083.3774, AIC = 1064428.7548, BIC = 1065493.3958.
#nc: 
#[1] 13522 11488
#pi: 
#[1] 0.5639 0.4361
plotem(results, data.features)
table(results$class, data$Something) #0.497 Error
#      no  yes
#  1 6720 6802
#  2 5773 5715