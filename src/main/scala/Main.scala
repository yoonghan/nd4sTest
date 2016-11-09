import org.nd4s.Implicits._

object Hi {
  def main(args: Array[String]) {
    val arr = (1 to 9).asNDArray(3, 3)
    val sub = arr(0->2, 1->3)
    println(arr(0,0))
    println(arr(--->))
    println(sub(--->))
  }
}
