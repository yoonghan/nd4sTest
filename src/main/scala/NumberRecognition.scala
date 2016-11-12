import org.nd4s.Implicits._
  /**
   * Look into resources/number_image folders, you'll see numbers built from 32 X 32 0's and 1.
   * Each file are named as (the label) _ (the sequence). Sequence just meant it's different way of writing the same label.
  **/
object NumberRecognition {
  def main(args: Array[String]): Unit = {
    println("Train the system to recognize numbers from a 32*32 characters of 0/1 number that made up a number")
    println("Program will execute very slow!")
    val creator = KnnCreator
    creator.handwritingClassTest
  }
}
