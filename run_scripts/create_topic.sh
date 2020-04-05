#!/bin/bash
name=${1}
echo "Create topic with name: ${name}"
/home/kafka/kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic ${name}
