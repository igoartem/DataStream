package org.example.DataStreams.service;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Stream;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.DataStreams.domain.Event;
import org.example.DataStreams.domain.StringEvent;
import org.example.DataStreams.utils.FileToTextUtil;
import org.example.DataStreams.utils.TextToWordsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

//@Builder
public class TextHandler extends Handler<Event, StringEvent> {
    private static final Logger log = LoggerFactory.getLogger(TextHandler.class);
    private String pathFile;
    private KafkaProducer<String, StringEvent> stringEventKafkaProducer;

    public TextHandler(String pathFile) {
        this.pathFile = pathFile;
    }

    //    @Override
    public void run() {
        stringEventKafkaProducer = createProducer(getProducerId());
        try {
            Stream<String> stream = FileToTextUtil.read(pathFile);
            stream.forEach(text -> {
                Collection<String> words = TextToWordsUtil.convert(text);
                words.forEach(word -> {
                    StringEvent stringEvent = new StringEvent(word);
                    final ProducerRecord<String, StringEvent> record = new ProducerRecord<>(getNameOutputTopic(), null, stringEvent);
                    sendRecord(record);
                });
            });

        } catch (IOException e) {
            log.error("", e);
        }
    }

    @Override
    public KafkaProducer<String, StringEvent> getProducer() {
        return stringEventKafkaProducer;
    }

    @Override
    public KafkaConsumer<String, Event> getConsumer() {
        throw new UnsupportedOperationException();
    }
}
