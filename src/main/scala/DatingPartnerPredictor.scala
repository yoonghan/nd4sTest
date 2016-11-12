import org.nd4s.Implicits._
/**
  * Created by Lee Wan on 11/9/2016.
  */
object DatingPartnerPredictor {
  def main(args: Array[String]): Unit = {
    println("This is a basic Dating partner website, based on the statistic gathered the system is to train and test if the assumption are equals")

    val creator = KnnCreator
    //    val fileResult = creator.file2Matrix
    //    println(fileResult._1(0,->) + " .vs. " + fileResult._2(0))
    //    println(fileResult._1(999,->) + " .vs. " + fileResult._2(999))
    //    println("------Read classifier from file-----")
    //    println()
    //    val (normDataSetRange, ranges, minVals) = creator.autoNorm(fileResult._1)
    //    println("ranges:" + ranges)
    //    println("minval:" + minVals)
    //    println("dataSet:" + normDataSetRange(--->))
    //
    //Test and Traing dating with datas of col-1=playing video game, col-2=freq flyer and col-3=ice cream eating
    creator.datingClassTest
  }
}
