import org.nd4s.Implicits._
/**
* To test if nd4s is working on the required platform.
* It prints a 3X3 matrix and another 2X2 matrix.
**/

object Basic_ND4S_Test {
  def main(args: Array[String]) {
    val arr = (1 to 9).asNDArray(3, 3)
    val sub = arr(0->2, 1->3)
    println(arr(0,0))
    println(arr(--->))
    println(sub(--->))
  }
}
