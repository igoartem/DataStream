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

    private String name;
    private String description;
    private HandlerType handlerType;

    @Getter
    public enum HandlerType {
        TEXT("Файл по словам"), REPEATER_COUNTER("Счетчик событий-ретранстлятор"), WORDS("Слово по символам"), REPEATER_PAUSE("Ретранслятор с задержкой");
        private String name;

        HandlerType(String name) {
            this.name = name;
        }
    }

    private KafkaProducer producer;
    private KafkaConsumer consumer;

    //    private Queue<T> inputEvents = new LinkedList<>();
    //    private Queue<U> outputEvents = new LinkedList<>();

    //    abstract Queue<T> getInputQueue();
    //
    //    abstract Queue<U> getOutputQueue();

    //    public Queue<T> getInputQueue() {
    //        return inputEvents;
    //    }
    //
    //    public Queue<U> getOutputQueue() {
    //        return outputEvents;
    //    }

}
