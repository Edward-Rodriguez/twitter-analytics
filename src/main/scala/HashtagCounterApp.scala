import org.apache.spark.sql.SparkSession
import com.typesafe.config.{Config, ConfigFactory}

object HashtagCounterApp extends App {
  // Load configuration into Settings class
  val conf: Config = ConfigFactory.load()
  val settings: Settings = Settings(conf)

  // spark session
  val spark = SparkSession
    .builder()
    .appName("Twitter-Analytics")
    .master("local[*]")
    .getOrCreate()

  // set log level
  spark.sparkContext.setLogLevel("ERROR")

  spark.sparkContext.hadoopConfiguration
    .set("fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")

  // Path to data in datalake folder on s3
  val inputData = settings.dataLake + settings.inputFile
  println("Bucket path =  " + inputData)

  // Load file from s3
  val df = spark.read.option("multiline", "true").json(inputData)
  df.printSchema()
}
