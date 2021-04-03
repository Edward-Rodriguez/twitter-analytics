import com.typesafe.config.Config
import scala.collection.JavaConverters._

/** class that will load the configuration and hold the parameter variables
  *
  * @param config
  */
class Settings(config: Config) {
  val s3Path = config.getString("s3.endpoint")
  val dataLake = config.getString("s3.datalake")
  val dataWarehouse = config.getString("s3.datawarehouse")
  val inputFile = config.getString("file.input")
  val outputFile = config.getString("file.output")
}

object Settings extends Serializable {
  def apply(config: Config): Settings = {
    val settings = new Settings(config)
    settings
  }
}
