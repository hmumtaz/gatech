setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/Assignment 2/Datasets/")   
data = read.table("trainingData.csv", sep=";", header=T)
data.features = data
data.features$Something = NULL
pca = prcomp(data.features)
library(fastICA)
ica = fastICA(data.features, n.comp=5)
ica = ica$S
colnames(ica) = list("Col1", "Col2", "Col3", "Col4", "Col5")
library(rsvd)
rca = rpca(data.features)
rca = predict(rca, data.features)
pca = data.frame(pca$x[,1:10])
pcaKMeans = kmeans(pca, 2)
table(pcaKMeans$cluster, data$Something) #0.499 Error
#      no  yes
#  1 6253 6231
#  2 6240 6286
plot(pca[c("PC1", "PC2")], col=pcaKMeans$cluster)
plot(pca[c("PC1", "PC2")], col=data$Something)
icaKMeans = kmeans(ica, 2)
table(icaKMeans$cluster, data$Something)#0.500 Error   
#      no  yes
#  1 6242 6256
#  2 6251 6261
plot(ica[,"Col1"], col=icaKMeans$cluster)
plot(ica[,"Col1"], col=data$Something)
rcaKMeans = kmeans(rca, 2)
table(rcaKMeans$cluster, data$Something)#0.499 Error 
#      no  yes
#  1 6261 6305
#  2 6232 6212
plot(rca, col=rcaKMeans$cluster)
plot(rca, col=data$Something)
library(EMCluster)
pcaEM = em.EM(pca, nclass=2)
table(pcaEM$class, data$Something)#0.499 Error   
#      no  yes
#  1 5862 5895
#  2 6631 6622
plotem(pcaEM, pca)
icaEM = em.EM(ica, nclass=2)
table(icaEM$class, data$Something)#0.497 Error   
#      no  yes
#  1 6671 6765
#  2 5822 5752
plotem(icaEM, ica)
rcaEM = em.EM(rca, nclass=2)
table(rcaEM$class, data$Something)#0.497 Error
#      no  yes
#  1 3112 3196
#  2 9381 9321
plotem(rcaEM, rca) 
