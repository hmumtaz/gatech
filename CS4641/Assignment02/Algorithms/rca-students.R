library(rsvd)
set.seed(50)
setwd("C:/Users/hmumt/Desktop/Classes/CS 4641/Assignment 2/Datasets/")
data = read.table("studentsNumeric.csv", sep=";", header=TRUE)
data.features = data
data.features$High = NULL
data.features$guardian = NULL
result = rpca(data.features)
summary(result)
#                             PC1        PC2        PC3        PC4        PC5
#Explained variance     3.0208886 1.86338385 1.62793824 1.46305304 1.35443362
#Standard deviations    1.7380704 1.36505818 1.27590683 1.20956729 1.16380137
#Proportion of variance 0.1118848 0.06901422 0.06029401 0.05418715 0.05016421
#Cumulative proportion  0.1118848 0.18089898 0.24119299 0.29538014 0.34554435
#Eigenvalues            3.0208886 1.86338385 1.62793824 1.46305304 1.35443362
#                              PC6        PC7        PC8        PC9       PC10
#Explained variance     1.25945114 1.21573590 1.13107930 1.06905925 1.05008376
#Standard deviations    1.12225271 1.10260414 1.06352212 1.03395322 1.02473595
#Proportion of variance 0.04664634 0.04502726 0.04189183 0.03959479 0.03889199
#Cumulative proportion  0.39219069 0.43721794 0.47910977 0.51870455 0.55759655
#Eigenvalues            1.25945114 1.21573590 1.13107930 1.06905925 1.05008376
#                            PC11       PC12       PC13       PC14       PC15
#Explained variance     1.0187369 0.92396559 0.89726039 0.88043455 0.83785234
#Standard deviations    1.0093250 0.96123129 0.94723830 0.93831474 0.91534275
#Proportion of variance 0.0377310 0.03422095 0.03323187 0.03260869 0.03103157
#Cumulative proportion  0.5953275 0.62954849 0.66278036 0.69538904 0.72642061
#Eigenvalues            1.0187369 0.92396559 0.89726039 0.88043455 0.83785234
#                             PC16       PC17      PC18       PC19       PC20
#Explained variance     0.82410625 0.80987792 0.7354396 0.69560761 0.67619907
#Standard deviations    0.90780298 0.89993218 0.8575778 0.83403094 0.82231324
#Proportion of variance 0.03052245 0.02999548 0.0272385 0.02576324 0.02504441
#Cumulative proportion  0.75694307 0.78693854 0.8141770 0.83994029 0.86498470
#Eigenvalues            0.82410625 0.80987792 0.7354396 0.69560761 0.67619907
#                             PC21       PC22       PC23       PC24       PC25
#Explained variance     0.64796401 0.63164299 0.57582916 0.53961779 0.51119689
#Standard deviations    0.80496212 0.79475971 0.75883408 0.73458682 0.71498034
#Proportion of variance 0.02399867 0.02339418 0.02132701 0.01998584 0.01893322
#Cumulative proportion  0.88898337 0.91237755 0.93370456 0.95369040 0.97262362
#Eigenvalues            0.64796401 0.63164299 0.57582916 0.53961779 0.51119689
#                             PC26       PC27
#Explained variance     0.43339566 0.30576652
#Standard deviations    0.65832792 0.55296159
#Proportion of variance 0.01605169 0.01132469
#Cumulative proportion  0.98867531 1.00000000
#Eigenvalues            0.43339566 0.30576652
plot(result$sdev, type="l")
