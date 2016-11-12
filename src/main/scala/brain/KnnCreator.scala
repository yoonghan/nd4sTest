import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import org.nd4s.Implicits._
import scala.io.Source
import java.io._
/**
  * Created by Lee Wan on 11/9/2016.
  */
object KnnCreator {

  val dataSourceFile = "dataSet.txt"        //Data set for dating!
  val numbersSourceFolder = "number_image/" //Training and Test data set for number recognition

  /**
    * Test only basic information
    * @return create mock data
    */
  def createDataSet(): (INDArray, Array[String]) = {
    val group = Array(Array(1.0,1.1), Array(1.0,1.0), Array(0.0,0.0), Array(0.0,0.1)).toNDArray
    val label = Array("A", "A", "B", "B")
    (group, label)
  }

  /**
    * Core program to do the classification using kNN classification, based ib euclidean algorithm.
    * @param inX A input as matrix without label that needs to be classified.
    * @param dataSet The dataset of all statistic gathered
    * @param labels The labels used to mapped to the dataset
    * @param k No of closest match
    * @return Identify the Label for inX
    */
  def classify0(inX:INDArray, dataSet:INDArray, labels:Array[String], k:Int): String = {
    val dataSetSize = dataSet.shape()(0) //retrieve no of rows
    val distancesWithLabel = (0 until dataSetSize).map(x => {
        labels(x) -> (dataSet(x, ->).distance2(inX))
    })

    val sortedResult = distancesWithLabel.sortBy(_._2)
    val kNNResult = (0 until k).map(v => sortedResult(v)._1 -> 1)
    val closestMatchByK = foldLeftSum(kNNResult.toList)

    val matchWithLargestCount = closestMatchByK.sortWith(_._2 < _._2)
    matchWithLargestCount.last._1
  }

  /**
    * Function to calculate group and calculate the sum of all labels
    * @param tuples
    * @tparam A
    * @return
    */
  def foldLeftSum[A](tuples: List[(A, Int)]) = tuples.foldLeft(Map.empty[A, Int])({
    case (acc, (k, v)) => acc + (k -> (v + acc.get(k).getOrElse(0)))
  }).toList

  /**
    * Turn file into matrix for dating file
    * @return matrix
    */
  def file2Matrix(): (INDArray, Array[String]) = {
println(getClass.getResource(dataSourceFile))
    val dataRecords = Source.fromInputStream(getClass.getResourceAsStream(dataSourceFile)).getLines().toArray
    val numOfLine = dataRecords.length
    val returnMatrix = Nd4j.zeros(numOfLine,3)
    var index = 0
    val classLabelVector = dataRecords.map( eachRec => {
      val listFromLine = eachRec.split('\t')
      val matLine = Array(listFromLine(0).toDouble, listFromLine(1).toDouble, listFromLine(2).toDouble).asNDArray(1,3)
      returnMatrix(index, ->) = matLine
      index += 1
      val result = listFromLine(3)
      result
    })
    return (returnMatrix, classLabelVector)
  }

  /**
    * Normalize all the inputs in matrix to set the range only from 0 to 1
    * Important to keep the values small, hence rather than realnumbers, it is best to kept below 1
    * @param dataSet matrix to be normalized
    * @return all output of the passed matrix will contains a range between 0 to 1
    */
  def autoNorm(dataSet:INDArray): (INDArray, INDArray, INDArray) = {
    val minVals = dataSet.min(0)
    val maxVals = dataSet.max(0)
    val ranges = maxVals - minVals
    val normDataSet = Nd4j.zeros(dataSet.shape()(0), dataSet.shape()(1))
    val m = dataSet.shape()(0)
    val normDataSetRow = dataSet - Nd4j.tile(minVals, m, 1)
    val normDataSetRange = normDataSetRow/Nd4j.tile(ranges, m, 1)
    return (normDataSetRange, ranges, minVals)
  }

  /**
    * Train and test the dating program
    */
  def datingClassTest() = {
    val hoRatio = 0.50
    val (datingDataMat, datingLabels) = file2Matrix()

    val (normMat, ranges, minVals) = autoNorm(datingDataMat)
    val m = normMat.shape()(0)
    val numTestVecs = (m*hoRatio).toInt
    var errorCount = 0.0
    for(i <- 0 until numTestVecs) {
      val classifierResult = classify0(normMat(i,->), normMat(numTestVecs -> m,->), datingLabels.slice(numTestVecs, m), 3)
      println("the classifier came back with:" + classifierResult + ", the real answer is: " + datingLabels(i))
      if(classifierResult != datingLabels(i)) {
        errorCount += 1.0
      }
    }
    println("the total error rate is: " + (errorCount / numTestVecs.toFloat))
  }

  /**
    * Turning the image number file into matrix
    * @param fileName
    * @return
    */
  def img2vector(fileName: String): INDArray = {
    val returnVect = Nd4j.zeros(1, 1024)
    val fr = Source.fromInputStream(getClass.getResourceAsStream(numbersSourceFolder +fileName)).getLines().toArray
    for(i <- 0 until 32) {
      val lineStr = fr(i)
      for(j <- 0 until 32) {
        returnVect(0, 32*i+j) = lineStr(j).asDigit
      }
    }
    returnVect
  }

  /**
    * List all files within the folder
    * @param folderName
    * @return
    */
  def listFilesInDir(folderName: String): Array[File] = {
    new File(getClass.getResource(numbersSourceFolder + folderName).getFile).listFiles()
  }

  /**
    * Test and train image numbers
    */
  def handwritingClassTest(): Unit = {
    val trainingFileList = listFilesInDir("trainingDigits")
    val m = trainingFileList.length
    val trainingMat = Nd4j.zeros(m, 1024)
    val hwLabels = for(i <- 0 until m) yield {
      val fileNameStr = trainingFileList(i).getName
      val fileStr = fileNameStr.split('.')(0)
      val classNumStr = fileStr.split('_')(0)
      trainingMat(i,->) = img2vector("trainingDigits/" + fileNameStr)
      classNumStr
    }
    val testFileList = listFilesInDir("testDigits/")
    var errorCount = 0.0
    val mTest = testFileList.length
    for(i <- 0 until mTest ) {
      val fileNameStr = testFileList(i).getName
      val fileStr = fileNameStr.split('.')(0)
      val classNumStr = fileStr.split('_')(0)
      val vectorUnderTest = img2vector("testDigits/" + fileNameStr)
      val classifierResult = classify0(vectorUnderTest, trainingMat, hwLabels.toArray, 3)
      println("the classifier came back with:" + classifierResult + ", the real answer is: " + classNumStr)
      if(classifierResult != classNumStr) errorCount += 1
    }
    println("the total number of errors is:" + errorCount)
    println("the total error rate is:" + (errorCount/mTest.toFloat))
  }
}
