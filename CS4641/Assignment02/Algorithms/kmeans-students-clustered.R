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
table(pcaKMeans$cluster, data$High) #0.249 Error
#      0   1
#  1  28 199
#  2  61 756
plot(pca[c("PC1", "PC2")], col=pcaKMeans$cluster)
plot(pca[c("PC1", "PC2")], col=data$High)
icaKMeans = kmeans(ica, 2)
table(icaKMeans$cluster, data$high) #0.208 Error
#      0   1
#  1  62 800
#  2  27 155
plot(ica[,"Col1"], col=icaKMeans$cluster)
plot(ica[,"Col1"], col=data$High)
rcaKMeans = kmeans(rca, 2)
pcaKtable(rcaKMeans$cluster, data$high)#0.305 Error
#      0   1
#  1  85 314
#  2   4 641
plot(rca, col=data$High)
plot(rca, col=rcaKMeans$cluster)
library(EMCluster)
pcaEM = em.EM(pca, nclass=2)
table(pcaEM$class, data$High) #0.450 Error  
#     no yes
#  1 331  55
#  2 519 139
plotem(pcaEM, pca)
icaEM = em.EM(ica, nclass=2)
table(icaEM$class, data$High)#0.307 Error
#     no yes
#  1 207  80
#  2 643 114
plotem(icaEM, ica)
rcaEM = em.EM(rca, nclass=2)
table(rcaEM$class, data$High)#0.256 Error
#     no yes
#  1 743 160
#  2 107  34
plotem(rcaEM, rca)