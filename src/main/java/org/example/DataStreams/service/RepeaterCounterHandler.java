package org.example.DataStreams.service;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.example.DataStreams.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Builder;

@Builder
public class RepeaterCounterHandler extends Handler<Event, Event> {
    private static final Logger log = LoggerFactory.getLogger(RepeaterCounterHandler.class);
    private Long timeOut;

    public void run(Long time) {
        while (true) {
            //
            //            Event event = getInputQueue().poll();
            //            log.error("cur {} {}", event, getInputQueue().size());
            //
            //            try {
            //                Thread.sleep(time);
            //            } catch (InterruptedException e) {
            //                e.printStackTrace();
            //            }
            //            if (ObjectUtils.isNotEmpty(currentMessage)) {
            //                Collection<Character> characters = WordToSymbolsUtil.convert(currentMessage.getValue());
            //                Collection<SymbolEvent> symbolEvents = new ArrayList<>();
            //                characters.forEach(character -> {
            //                    symbolEvents.add(SymbolEvent.builder().value(character).build());
            //                });
            //                getOutputQueue().addAll(symbolEvents);
            //                log.error("ss {}", getOutputQueue().size());
            //        }
        }
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
