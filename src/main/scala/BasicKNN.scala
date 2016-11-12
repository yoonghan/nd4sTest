import org.nd4s.Implicits._
/**
  * Created by Lee Wan on 11/9/2016.
  **/
object BasicKNN {
  def main(args: Array[String]): Unit = {
    println("This is a basic KNN, train the data and identify (0,0) belonged to label B")

    val creator = KnnCreator
    val result = creator.createDataSet()
    val dataSet = result._1
    val labels = result._2

    /**
      * Create dataset with labels of A and B
      * We represent A as Snow while B as Good Weather.
      * Each dataset (predefined) are classified with Humidity as column 1 and Temperature as column 2.
      * Since the return matrix is a 2X2, it can be plotted in a graph, with X=Column1 and Y=Column2
      **/
    println("DataSet:")
    println(dataSet(--->))
    println(s"Label: ${labels.mkString(",")}")
    println("------Label Print Completed-----")
    println()

    /**
      * Introduce a new data (0,0) without label.
      * Now we need the system to classify if (0,0) belongs to which category.
      * Based on the dataSet the new data should be classify as B(Good Weather).
    **/
    val newData = Array(0.0,0.0).asNDArray(1,2)
    val classifiedValue = creator.classify0(newData, dataSet, labels, 3)
    println(s"Closest match for: $newData is $classifiedValue");
    println("------Classifier Print Completed-----")
    println()
  }
}
