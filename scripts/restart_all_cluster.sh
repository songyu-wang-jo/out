#!/bin/bash
for user_host in `cat clusters`
do
ssh $user_host << remote 
	/zookeeper/zookeeper-3.8.0/bin/zkServer.sh restart 
	exit
remote
done

for user_host in `cat clusters`
do
ssh $user_host << remote
	sh /kafka/kafka_2.13-3.2.0/bin/kafka-server-stop.sh
        nohup /kafka/kafka_2.13-3.2.0/bin/kafka-server-start.sh /kafka/kafka_2.13-3.2.0/config/server.properties > /dev/null 2>&1 &
	exit
remote
echo $user_host wa done......................
done
