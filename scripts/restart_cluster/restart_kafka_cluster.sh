#!/bin/bash
for user_host in `cat ../clusters`
do
ssh $user_host << remote 
	sh /kafka/kafka_2.13-3.2.0/bin/kafka-server-stop.sh
	nohup /kafka/kafka_2.13-3.2.0/bin/kafka-server-start.sh /kafka/kafka_2.13-3.2.0/config/server.properties > /dev/null 2>&1 &
	exit
remote
echo $user_host was done..................
done
