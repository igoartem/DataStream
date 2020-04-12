package org.example.DataStreams.utils;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.errors.TopicExistsException;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.utils.Time;
import org.apache.kafka.connect.json.JsonDeserializer;
import org.apache.kafka.connect.json.JsonSerializer;
import org.apache.logging.log4j.core.appender.mom.kafka.DefaultKafkaProducerFactory;
import org.apache.logging.log4j.core.appender.mom.kafka.KafkaProducerFactory;
import org.example.DataStreams.domain.Event;
import org.example.DataStreams.domain.StringEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import kafka.admin.RackAwareMode;
import kafka.utils.Json;
import kafka.zk.AdminZkClient;
import kafka.zk.KafkaZkClient;

@PropertySource("classpath:app.properties")
public abstract class KafkaUtils {
    private static final Logger log = LoggerFactory.getLogger(KafkaUtils.class);



   
    public static KafkaConsumer<String, String> createConsumer(String topicName) {
        //        final Properties props = new Properties();
        //        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        //        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer");
        //        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        //        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        //        // Create the consumer using props.
        //        final KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(props);
        //        // Subscribe to the topic.
        //        consumer.subscribe(Collections.singletonList());
        //        return consumer;
        return null;

    }

    //Можно написать тест
    public static void createTopic(KafkaZkClient kafkaZkClient, AdminZkClient adminZkClient, String topicName, int partitions, int replication) {

        if (kafkaZkClient.topicExists(topicName)) {
            log.debug("Topic with name {} exist", topicName);
            return;
        }
        Properties topicConfig = new Properties();
        log.debug("Creating topic { name: {}, partitions: {}, replication: {}, config: {} }", topicName, partitions, replication, topicConfig);
        try {
            adminZkClient.createTopic(topicName, partitions, replication, topicConfig, RackAwareMode.Disabled$.MODULE$);
        } catch (TopicExistsException e) {
            log.error("", e);
        }
    }


}
