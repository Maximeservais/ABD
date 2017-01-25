import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object SparkDigrams {

  def main(args: Array[String]) {
  
    val conf = new SparkConf().setAppName("Digrams Application")
    val sc = new SparkContext(conf)
      
    var start = System.currentTimeMillis();
    for( j <- 1 to 10) {
      val textFile = sc.textFile("Miserables.txt")
      val digram = textFile.map(line => line.trim.split(" ")).flatMap(wordList => wordList.sliding(2).map{case Array(x, y) => ((x,y), 1)}).reduceByKey(_+_).sortBy(_._2).collect()
      digram.foreach(x => println(x))
    }
    var end = System.currentTimeMillis();
    var tempsMoy = (end-start)/10
    
    println("Temps moyen de calcul : " + tempsMoy)
  }
}