import org.nd4s.Implicits._
/**
  * Created by Lee Wan on 11/9/2016.
  */
object BasicKNN {
  def main(args: Array[String]): Unit = {
    println("This is a basic KNN train the data and identify (0,0) belonged to label B")

    val creator = KnnCreator
    val result = creator.createDataSet()
    val dataSet = result._1
    val labels = result._2

    //Create dataset and label it as A or B
    println("DataSet:")
    println(dataSet(--->))
    println("Label:" + labels.mkString(","))
    println("------Label Print Completed-----")
    println()

    //Basic test to see 0,0 are categorized as A or B
    val selector = Array(0.0,0.0).asNDArray(1,2)
    val classifiedValue = creator.classify0(selector, dataSet, labels, 3)
    println("Closest match for:" + selector + ": is :" + classifiedValue);
    println("------Classifier Print Completed-----")
    println()
  }
}
