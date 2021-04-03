name := "twitter-analytics"
organization := "com.revature"
version := "0.1"
scalaVersion := "2.12.10"

// Spark Information
val sparkVersion = "3.1.1"
val awsSdkS3Version = "1.11.990"
val hadoopAWSVersion = "3.3.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  // https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk
  "com.amazonaws" % "aws-java-sdk-s3" % awsSdkS3Version,
  "org.apache.hadoop" % "hadoop-aws" % hadoopAWSVersion,
  "com.typesafe" % "config" % "1.4.1"
)
