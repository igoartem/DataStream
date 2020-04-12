package org.example.DataStreams.service;

import java.util.Collections;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.example.DataStreams.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Builder;

@Builder
public class RepeaterPauseHandler extends Handler<Event, Event> {
    private static final Logger log = LoggerFactory.getLogger(RepeaterPauseHandler.class);

    private Long timeOut;

    public void run(Long time) {
       
    }

    void run() {
        
    }

    @Override
    public KafkaProducer<String, Event> getProducer() {
        return null;
    }

    @Override
    public KafkaConsumer<String, Event> getConsumer() {
        return null;
    }
}
