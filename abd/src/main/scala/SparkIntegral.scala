import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object SparkIntegral {

  def main(args: Array[String]) {
  
    val conf = new SparkConf().setAppName("Integral Application")
    val sc = new SparkContext(conf)
    
    val rectangleNumber = if (args.length > 0) args(0).toFloat else 9
    val slices = if (args.length > 0) args(1).toInt else 2
    val areaLength = 9
    val rectangleLength = areaLength.toFloat / rectangleNumber.toFloat
    
    var start = System.currentTimeMillis();
    for( j <- 1 to 10) {
      val result = sc.parallelize(0 until rectangleNumber.toInt, slices).map { i =>
        rectangleLength.toFloat / (1 + i.toFloat*rectangleLength.toFloat)
      }.reduce(_ + _)
      //println("Result : " + result)
    }
    var end = System.currentTimeMillis();
    var tempsMoy = (end-start)/10
    
    println("Temps moyen de calcul : " + tempsMoy)
  }
}