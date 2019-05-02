library(EMCluster)
set.seed(50)
setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/Assignment 2/Datasets/")
data = read.table("studentsNumeric.csv", sep=";", header=TRUE)
data.features = data
data.features$High = NULL
results = prcomp(data.features)
summary(results)
#                     PC1     PC2    PC3     PC4     PC5     PC6     PC7
#Standard deviation     6.2166 1.52700 1.4428 1.30820 1.16229 0.92602 0.88383
#Proportion of Variance 0.7388 0.04458 0.0398 0.03272 0.02583 0.01639 0.01493
#Cumulative Proportion  0.7388 0.78336 0.8232 0.85587 0.88170 0.89809 0.91302
#                           PC8     PC9   PC10    PC11    PC12    PC13    PC14
#Standard deviation     0.84016 0.73018 0.6705 0.61138 0.54227 0.51498 0.48898
#Proportion of Variance 0.01349 0.01019 0.0086 0.00715 0.00562 0.00507 0.00457
#Cumulative Proportion  0.92651 0.93671 0.9453 0.95245 0.95807 0.96314 0.96771
#                          PC15    PC16    PC17    PC18    PC19    PC20    PC21
#Standard deviation     0.46833 0.45589 0.44428 0.41708 0.38437 0.37126 0.34443
#Proportion of Variance 0.00419 0.00397 0.00377 0.00333 0.00282 0.00263 0.00227
#Cumulative Proportion  0.97190 0.97588 0.97965 0.98297 0.98580 0.98843 0.99070
#                          PC22    PC23    PC24    PC25    PC26    PC27
#Standard deviation     0.33559 0.30102 0.29463 0.29017 0.24847 0.22454
#Proportion of Variance 0.00215 0.00173 0.00166 0.00161 0.00118 0.00096
#Cumulative Proportion  0.99285 0.99459 0.99625 0.99786 0.99904 1.00000
#                            PC28
#Standard deviation     5.353e-16
#Proportion of Variance 0.000e+00
#Cumulative Proportion  1.000e+00
plot(results, type="l")
