import org.nd4s.Implicits._
/**
  * Created by Lee Wan on 11/9/2016.
  */
object NumberRecognition {
  def main(args: Array[String]): Unit = {
    println("Train the system to recognize numbers from a 32*32 characters of 0/1 number that made up a number")
    println("Program will execute very slow!")
    val creator = KnnCreator
    creator.handwritingClassTest
  }
}
