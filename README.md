# ND4S and KNN Prototype
A K-NN classification/classifier in scala re-written from python.
A class to test if ND4S is included! 

Pre Installation
=======
1. Java (JDK 1.8)
2. SBT
3. Scala version 2.11.8 (for different version, modify the build.sbt)
4. (Optional) Unzip the files located in `src\main\resources\number_image\*`. Remove the `*.zip` file in the folders afterwards.

Execution
=======
1. Open a command line/terminal and Change to the checkout main folder
2. Execute
	```bash
 	   sbt
 	   run
	```
3. Select the number and key in the test you'll want to perform

Programs
=======
 - Basic_ND4S_Test  - very basic test to see if ND4S is running
 - BasicKNN - a basic kNN algorithm to show how classification works
 - DatingPartnerPredictor - Train and Test if classification for Dating is working
 - NumberRecognition - Train and Test for predefined files to recognize numbers. To run this, please unzip the files located in src\main\resources\number_image\*. Then remove the *.zip file.

Have fun!
