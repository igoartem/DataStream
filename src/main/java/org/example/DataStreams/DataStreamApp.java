package org.example.DataStreams;

import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.DataStreams.domain.Event;
import org.example.DataStreams.domain.StringEvent;
import org.example.DataStreams.menu.MenuMain;
import org.example.DataStreams.service.TextHandler;
import org.example.DataStreams.utils.KafkaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kafka.zk.AdminZkClient;
import kafka.zk.KafkaZkClient;

@SpringBootApplication
public class DataStreamApp implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataStreamApp.class);

    public static void main(String[] args) {
        SpringApplication.run(DataStreamApp.class, args);
    }

    @Autowired
    private MenuMain menuMain;

    @Autowired
    private KafkaZkClient kafkaZkClient;

    @Autowired
    private AdminZkClient adminZkClient;

    @Override
    public void run(String... args) throws Exception {
        log.debug("Running Spring Boot Application");

        KafkaUtils.createTopic(kafkaZkClient, adminZkClient, "test1", 2, 1);
        TextHandler textHandler = new TextHandler("/home/iaa/proj/test.txt");

        textHandler.setNameInputTopic("test2");
        textHandler.setNameOutputTopic("test1");
        textHandler.setProducerId("ttttt");
        textHandler.run();

//        textHandler.postCreate();

//        try {
//            for (long index = 0; index < 100; index++) {
//                StringEvent stringEvent = new StringEvent("test" + index);
//
//                final ProducerRecord<String, Event> record = new ProducerRecord<>("test1", "" + index, stringEvent);
//                kafkaProducer.send(record, (metadata, exception) -> {
//                    long elapsedTime = System.currentTimeMillis();
//                    if (metadata != null) {
//                        System.out.printf("sent record(key=%s value=%s) " + "meta(partition=%d, offset=%d) time=%d\n", record.key(), record.value(),
//                                metadata.partition(), metadata.offset(), elapsedTime);
//                    } else {
//                        exception.printStackTrace();
//                    }
//
//                });
//            }
//
//        } finally {
//            kafkaProducer.flush();
//            kafkaProducer.close();
//        }

        //        menuMain.mainMenu();
    }

}
