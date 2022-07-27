#!/bin/bash
sh stop_cluster/stop_all_cluster.sh
for user_host in `cat clusters`
do
ssh $user_host << remote 
poweroff
remote
done
poweroff
