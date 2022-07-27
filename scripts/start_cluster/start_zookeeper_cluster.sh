#!/bin/bash
for user_host in `cat ../clusters`
do
ssh $user_host << remote 
	/zookeeper/zookeeper-3.8.0/bin/zkServer.sh start
	exit
remote
done
