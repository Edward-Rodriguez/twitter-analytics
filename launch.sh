# script to launch app to local spark cluster on docker
# and copy necessary jar files to local spark cluster on docker

# sbt assembly
sbt package

# this copies the jar files into spark/jars folder
docker cp lib/config-1.4.1.jar spark-master:/spark/jars/config-1.4.1.jar

# copy application jar to docker
docker cp target/scala-2.12/twitter-analytics_2.12-0.1.jar spark-master:/spark/jars/twitter-analytics_2.12-0.1.jar

# submit spark job locally:
docker exec spark-master bash -c "./spark/bin/spark-submit --class "HashtagCounterApp" --master local[*] /spark/jars/twitter-analytics_2.12-0.1.jar"


