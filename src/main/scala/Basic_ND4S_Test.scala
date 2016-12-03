import org.nd4s.Implicits._
/**
* To test if nd4s is working on the required platform.
* It prints a 3X3 matrix and another 2X2 matrix.
**/

object Basic_ND4S_Test {
  def main(args: Array[String]) {
    val arr = (1 to 9)                //Creates an array 1,2,3...9
    val mat = arr.asNDArray(3, 3)     //Converts array into matrix, with 3 rows X 3 columns
    val sub = mat(0->2, 1->3)         //Duplicate row 1st - 3rd and 2nd - 4th column
    println(mat(0,0))                 //Print the first column and row
    println(mat(--->))                //Prints everything
    println(sub(--->))                //Prints everything
  }
}
