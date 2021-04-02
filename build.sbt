name := "twitter-analytics"
organization := "com.revature"
version := "0.1"
scalaVersion := "2.12.10"

// Spark Information
val sparkVersion = "3.1.1"
val awsSDKVersion = "1.11.990"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  // https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk
  "com.amazonaws" % "aws-java-sdk" % awsSDKVersion
)
