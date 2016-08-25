#!/bin/bash
clear
date
echo "TESTING WORKLOAD"
operLectEscritura="read"
date=$(date +"%Y%m%d-%H%M")
#operLectEscritura="write"
workload="read.txt"
db="KVClienteSO"
host="KVClienteSO.host=127.0.0.1"
port="KVClienteSO.port=1234"
for i in 1 8
do
	for j in 1 2 3 4 5
	do
		./bin/ycsb load $db -s -P workloads/$workload -p "$host" -p "$port" -threads $i > ./pruebasEVE/h$i$operLectEscritura20162525969936.txt
	        ./bin/ycsb run $db -s -P workloads/$workload -p "$host" -p "$port" -threads $i> ./pruebasEVE/h$i$operLectEscritura20162525969937.txt
		
		echo "=================EJECUCION TERMINADA EN HILO: " $i "ITERACION: " $j"==========================="
	done
done
