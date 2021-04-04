# script to launch app to local spark cluster on docker
# set -e

# create fat jar
# sbt assembly
sbt package

# file to set credentials
# source keys.sh


# this copies the jar files into spark/jars folder
docker cp lib/aws-java-sdk-bundle-1.11.991.jar spark-master:/spark/jars/aws-java-sdk-bundle-1.11.991.jar
docker cp lib/config-1.4.1.jar spark-master:/spark/jars/config-1.4.1.jar

# copy jar to docker
docker cp target/scala-2.12/twitter-analytics_2.12-0.1.jar spark-master:/spark/jars/twitter-analytics_2.12-0.1.jar

# submit spark job locally:
# docker exec -e AWS_ACCESS_KEY_ID -e AWS_SECRET_ACCESS_KEY spark-master bash -c "./spark/bin/spark-submit --class "HashtagCounterApp" --master local[*] /spark/jars/twitter-analytics-assembly-fatjar-1.0.jar"
# docker exec spark-master bash -c "./spark/bin/spark-submit --class "HashtagCounterApp" --master local[*] /spark/jars/twitter-analytics-assembly-fatjar-1.0.jar"

docker exec spark-master bash -c "./spark/bin/spark-submit --class "HashtagCounterApp" --master local[*] /spark/jars/twitter-analytics_2.12-0.1.jar"
# docker exec -e AWS_ACCESS_KEY_ID -e AWS_SECRET_ACCESS_KEY spark-master bash -c "./spark/bin/spark-submit --class "HashtagCounterApp" --master local[*] /spark/jars/twitter-analytics_2.12-0.1.jar"


