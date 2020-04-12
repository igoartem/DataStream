package org.example.DataStreams.service;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.DataStreams.domain.StringEvent;
import org.example.DataStreams.domain.WordEvent;

import lombok.Builder;

@Builder
public class WordsHandler extends Handler<StringEvent, WordEvent> {
    private static final Logger log = LogManager.getLogger(WordsHandler.class);

    private KafkaConsumer<String, StringEvent> consumer;
    private KafkaProducer<String, WordEvent> producer;

    public void run() {
        producer = createProducer(getProducerId());
//        consumer = 
        StringEvent currentMessage;
        //        currentMessage = getInputQueue().poll();
        //        log.error("cur {}", currentMessage);
        //        while (true) {
        //            Thread.sleep(100L);
        //            currentMessage = getInputQueue().poll();
        //            log.error("cur {} {}", currentMessage, getInputQueue().size());
        //            if (ObjectUtils.isNotEmpty(currentMessage)) {
        //                Collection<Character> characters = WordToSymbolsUtil.convert(currentMessage.getValue());
        //                Collection<SymbolEvent> symbolEvents = new ArrayList<>();
        //                characters.forEach(character -> {
        //                    symbolEvents.add(SymbolEvent.builder().value(character).build());
        //                });
        //                getOutputQueue().addAll(symbolEvents);
        //                log.error("ss {}", getOutputQueue().size());
        //            }
        //        }
    }

    @Override
    public KafkaProducer<String, WordEvent> getProducer() {
        return producer;
    }

    @Override
    public KafkaConsumer<String, StringEvent> getConsumer() {
        return consumer;
    }
}
