name := "twitter-analytics"
organization := "com.revature"
version := "0.1"
scalaVersion := "2.12.13"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % "3.0.1",
  "com.amazonaws" % "aws-java-sdk-bundle" % "1.11.991",
  "org.apache.hadoop" % "hadoop-aws" % "3.2.0",
  "com.typesafe" % "config" % "1.4.1"
)
