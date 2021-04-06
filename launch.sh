# script to launch app to local spark cluster on docker
# and copy necessary jar files to local spark cluster on docker

# sbt assembly
sbt package

# this copies the jar files into spark/jars folder
# only copy over if not in spark/jars folder on cluster
# docker cp lib/config-1.4.1.jar spark-master:/spark/jars/config-1.4.1.jar
# docker cp lib/aws-java-sdk-bundle-1.11.991.jar spark-master:/spark/jars/aws-java-sdk-bundle-1.11.991.jar

# copy application jar to docker
docker cp target/scala-2.12/twitter-analytics_2.12-0.1.jar spark-master:/spark/jars/twitter-analytics_2.12-0.1.jar

# submit spark job locally (spark cluster on docker)
docker exec spark-master bash -c "./spark/bin/spark-submit --class "TwitterAnalyticsApp" --master local[*] /spark/jars/twitter-analytics_2.12-0.1.jar"


