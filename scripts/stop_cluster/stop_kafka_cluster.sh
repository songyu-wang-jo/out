#!/bin/bash
for user_host in `cat ../clusters`
do
ssh $user_host << remote 
	/kafka/kafka_2.13-3.2.0/bin/kafka-server-stop.sh
	exit
remote
done
