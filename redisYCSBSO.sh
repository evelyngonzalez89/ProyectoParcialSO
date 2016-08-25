#!/bin/bash
unit="MB"
date=$(date +"%Y%m%d-%H%M")
for (( memsize=1; memsize<=100; memsize+=50 )); do
  echo "#########################################"
  echo "#########################################"
  echo "#########################################"
  bytes=$(($memsize*1024*1024))
  date
  echo "   Set max memory... $memsize"
  redis-cli -h 127.0.0.1 -r 1 CONFIG SET MAXMEMORY "$bytes"
  echo "   Flush redis..."
  redis-cli -h 127.0.0.1 -r 1 flushall
  echo "   Start YCSB test..."
  ./bin/ycsb run redis -s -P workloads/workloadc -p "KVClienteSO.host=127.0.0.1" -p "KVClienteSO.port=6379" > h$i$load$date.log   

  #./bin/ycsb run redis -s -P workloads/workloadc -p "KVClienteSO.host=127.0.0.1" -p "KVClienteSO.port=6379" > h$i$write$date.log   
  date
done

