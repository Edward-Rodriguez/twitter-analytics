import org.apache.spark.sql.SparkSession
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.sql.functions.{explode, col}

object TwitterAnalyticsApp extends App {
  // Load configuration into Settings class
  val conf: Config = ConfigFactory.load()
  val settings: Settings = Settings(conf)

  // spark session
  val spark = SparkSession
    .builder()
    .appName("Twitter-Analytics")
    // .master("local[*]")
    .getOrCreate()

  // set log level
  spark.sparkContext.setLogLevel("ERROR")

  spark.sparkContext.hadoopConfiguration
    .set("fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")

  // Path to data in datalake folder on s3
  val inputData = settings.dataLake + settings.inputFile

  // local test file
  // val inputData = "spark/data/batch/30.json.bz2"

  // load custom schema for hashtags
  val hashtagUtil = HashtagsUtil
  val hashtagSchema = hashtagUtil.loadHashtagSchema()

  // Load file from s3 and convert to df (only select hashtags field)
  val df = spark.read
    .format("json")
    .option("mode", "PERMISSIVE")
    .schema(hashtagSchema)
    .load(inputData)
    .select(explode(col("entities.hashtags.text")) as "hashtags")
    .cache()

  val topHashtagsDF = hashtagUtil.getTopNHashtags(df, 20)
  topHashtagsDF.show(false)
}
