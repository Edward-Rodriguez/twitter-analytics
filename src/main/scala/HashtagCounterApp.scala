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

  // Configuration to be able to use AWS s3a
  spark.sparkContext.hadoopConfiguration
    .set("fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")

  // Load file from s3
  val df = spark.read.json(settings.inputFile)
  df.show()
}
