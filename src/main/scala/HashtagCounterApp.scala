import org.apache.spark.sql.SparkSession
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.sql.types.{StructType, StructField}
import org.apache.spark.sql.types.{ArrayType, StringType}
import org.apache.spark.sql.functions.{explode, col}

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

  // load custom schema for hashtags
  val hashtagSchema = loadHashtagSchema()
  // Load file from s3
  val df = spark.read
    .format("json")
    .option("mode", "FAILFAST")
    .schema(hashtagSchema)
    .load(inputData)
    .select(explode(col("entities.hashtags.text")) as "hashtags")
    .printSchema()

  def loadHashtagSchema(): StructType = {
    val hashtag = StructType(Array(StructField("text", StringType, false)))
    val schema = StructType(
      Array(
        StructField(
          "entities",
          StructType(
            Array(StructField("hashtags", ArrayType(hashtag, false), false))
          ),
          false
        )
      )
    )
    schema
  }
}
