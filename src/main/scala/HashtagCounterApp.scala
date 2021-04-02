import org.apache.spark.sql.SparkSession

object HashtagCounterApp extends App {

  // declaring spark session
  val spark = SparkSession
    .builder()
    .appName("Twitter-Analytics")
    .master("local[*]")
    .getOrCreate()

  // set log level
  spark.sparkContext.setLogLevel("ERROR")

  // Configuration to be able to use AWS s3a
  spark.sparkContext.hadoopConfiguration
    .set("fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
}
