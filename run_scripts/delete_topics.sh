#!/bin/bash
name=${1}
echo "Delete topic with name: ${name}"
sh /home/kafka/kafka/bin/kafka-topics.sh --zookeeper localhost:2181 --topic ${name} --delete