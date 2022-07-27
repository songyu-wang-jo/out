#!/bin/bash
for user_host in `cat clusters`
do
ssh $user_host << remote 
	/zookeeper/zookeeper-3.8.0/bin/zkServer.sh start
	exit
remote
done

echo wating for zookeeper starting................

sleep 10

for user_host in `cat clusters`
do
ssh $user_host << remote 
	nohup /kafka/kafka_2.13-3.2.0/bin/kafka-server-start.sh /kafka/kafka_2.13-3.2.0/config/server.properties > /dev/null 2>&1 &
	exit
remote
done
