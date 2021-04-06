import org.apache.spark.sql.types.{StructType, StructField}
import org.apache.spark.sql.types.{ArrayType, StringType}
import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions.{col, desc}

object HashtagsUtil {

  /** groups by hashtags and counts, returns top 'n'
    *
    * @param hashtagsDF ungrouped df with hashtag field
    * @param n num to limit by
    * @return sorted (desc) df on hashtags
    */
  def getTopNHashtags(hashtagsDF: DataFrame, n: Int = 1): DataFrame = {
    val topHashtagsDF =
      hashtagsDF
        .groupBy(col("hashtags"))
        .count()
        .orderBy(desc("count"))
        .limit(n)

    topHashtagsDF
  }

  /** Create custom schema representing hashtag field from twitter
    *
    * @return schema
    */
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
