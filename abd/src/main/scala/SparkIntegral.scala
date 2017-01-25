import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object SparkIntegral {

  def main(args: Array[String]) {
  
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    
    val rectangleNumber = if (args.length > 0) args(0).toInt else 9
    val slices = if (args.length > 0) args(1).toInt else 2
    val areaLength = 9
    val rectangleLength = areaLength / rectangleNumber
    
    val result = sc.parallelize(0 until rectangleNumber, slices).map { i =>
      val rectangleHeight = 1 / (1 + i*rectangleLength)
      rectangleHeight * rectangleLength
    }.reduce(_ + _)

    println("Integral is roughly " + result)
  }
}