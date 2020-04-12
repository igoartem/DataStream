package org.example.DataStreams.service;

import java.util.Properties;

import javax.annotation.PreDestroy;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.connect.json.JsonSerializer;
import org.example.DataStreams.domain.Event;
import org.example.DataStreams.domain.EventDeserializer;
import org.example.DataStreams.domain.EventSerializer;
import org.example.DataStreams.utils.KafkaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import kafka.zk.AdminZkClient;
import kafka.zk.KafkaZkClient;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@PropertySource("classpath:app.properties")
public abstract class Handler<T extends Event, U extends Event> {
    private static final Logger log = LoggerFactory.getLogger(Handler.class);

    @Value("${bootstrap.servers}")
    public String bootstrapServerConnection;

        private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    private String nameInputTopic;
    private String nameOutputTopic;
    private String producerId;
    private String consumerId;

    private AdminZkClient adminZkClient;
    private KafkaZkClient kafkaZkClient;

    public abstract KafkaProducer<String, U> getProducer();

    public abstract KafkaConsumer<String, T> getConsumer();

    /**
     * Общий метод для отправки записей
     * @param record
     */
    protected void sendRecord(ProducerRecord<String, U> record) {
        getProducer().send(record, (metadata, exception) -> {
            if (metadata != null) {
                log.debug("sent record(key={} value={}) \" + \"meta(partition={}, offset=%d) time=%d", record.key(), record.value(), metadata.partition(),
                        metadata.offset());
            } else {
                log.error("", exception);
            }
        });
    }

    @Getter
    public enum HandlerType {
        TEXT("Файл по словам"), REPEATER_COUNTER("Счетчик событий-ретранстлятор"), WORDS("Слово по символам"), REPEATER_PAUSE("Ретранслятор с задержкой");
        private String name;

        HandlerType(String name) {
            this.name = name;
        }
    }

    public void initHandler() {
        initTopic();
        initConsumer();
    }

    private void initTopic() {
        KafkaUtils.createTopic(kafkaZkClient, adminZkClient, nameInputTopic, 3, 1);
        KafkaUtils.createTopic(kafkaZkClient, adminZkClient, nameOutputTopic, 3, 1);
    }
    

    private void initConsumer() {
    }

    @PreDestroy
    public void preDestroy() {

    }

    protected KafkaProducer createProducer(String producerId) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, producerId);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EventSerializer.class.getName());
        props.put(ProducerConfig.RETRIES_CONFIG, 2);
        return new KafkaProducer<>(props);
    }

}
