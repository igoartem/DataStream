package org.example.DataStreams.service;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.example.DataStreams.domain.Event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Handler<T extends Event, U extends Event> {
    
    @Getter
    public enum HandlerType {
        TEXT("Файл по словам"), REPEATER_COUNTER("Счетчик событий-ретранстлятор"), WORDS("Слово по символам"), REPEATER_PAUSE("Ретранслятор с задержкой");
        private String name;

        HandlerType(String name) {
            this.name = name;
        }
    }

    private KafkaProducer<String, T> producer;
    private KafkaConsumer<String, U> consumer;
    
}
