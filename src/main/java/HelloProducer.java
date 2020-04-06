import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.example.DataStreams.utils.KafkaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.zk.KafkaZkClient;

public class HelloProducer {
    private static final Logger log = LoggerFactory.getLogger(HelloProducer.class);

    public static void main(String[] args) {
        String topicName;
        int numEvents;
        
        topicName = "test";
        numEvents = 10;
        log.info("Starting HelloProducer...");
        log.debug("topicName= {}, numEvents= {}", topicName, numEvents);
        log.trace("Creating Kafka Producer...");
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "HelloProducer");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);
        log.trace("Start sending messages...");
        
        KafkaConsumer<Integer, String> consumer = KafkaUtils.createConsumer();
        
   
 
        try {
            for (int i = 1; i <= numEvents; i++) {
                producer.send(new ProducerRecord<>(topicName, i, "Simple Message-" + i));
            }
        } catch (KafkaException e) {
            log.error("Exception occurred – Check log for more details.", e);
            System.exit(-1);
        } finally {
            log.info("Finished HelloProducer – Closing Kafka Producer.");
            producer.close();
        }
        
           consumer.poll(Duration.ofSeconds(100)).forEach(longStringConsumerRecord -> {
               System.out.println(longStringConsumerRecord);
            log.error("111 {} {}", longStringConsumerRecord.key(), longStringConsumerRecord.value());
           });
           consumer.commitSync();
    }
}