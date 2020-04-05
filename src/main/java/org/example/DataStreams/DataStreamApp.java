package org.example.DataStreams;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.common.errors.TopicExistsException;
import org.apache.kafka.common.utils.Time;
import org.example.DataStreams.menu.Menu;
import org.example.DataStreams.menu.MenuItem;
import org.example.DataStreams.service.Handler;
import org.example.DataStreams.service.RepeaterCounterHandler;
import org.example.DataStreams.service.RepeaterPauseHandler;
import org.example.DataStreams.service.TextHandler;
import org.example.DataStreams.service.WordsHandler;
import org.example.DataStreams.utils.ConsoleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.admin.RackAwareMode;
import kafka.zk.AdminZkClient;
import kafka.zk.KafkaZkClient;
import scala.Option;

public class DataStreamApp {

    private static final Logger log = LoggerFactory.getLogger(DataStreamApp.class);

    public static void main(String[] args) {

        new MenuMain().mainMenu();

        String zookeeperHost = "127.0.0.1:2181";
        Boolean isSucre = false;
        int sessionTimeoutMs = 200000;
        int connectionTimeoutMs = 15000;
        int maxInFlightRequests = 10;
        Time time = Time.SYSTEM;
        String metricGroup = "myGroup";
        String metricType = "myType";
        Option<String> name = Option.apply("Name");

        KafkaZkClient zkClient = KafkaZkClient
                .apply(zookeeperHost, isSucre, sessionTimeoutMs, connectionTimeoutMs, maxInFlightRequests, time, metricGroup, metricType, name);
        AdminZkClient adminZkClient = new AdminZkClient(zkClient);

        String topicName1 = "myTopic111";
        int partitions = 3;
        int replication = 1; // you should have replication factor less than or equal to number of nodes in Kafka cluster
        Properties topicConfig = new Properties(); // you can pass topic configurations while creating topic

        try {
            adminZkClient.createTopic(topicName1, partitions, replication, topicConfig, RackAwareMode.Disabled$.MODULE$);
        } catch (TopicExistsException e) {
            log.error("", e);
        }

        log.error("{}", adminZkClient.getAllTopicConfigs().get(topicName1));
        log.error("{}", adminZkClient.getAllTopicConfigs());
        zkClient.topicExists(topicName1);

        //        StringHandler stringHandler = StringHandler.builder().build();
        //        Runnable runnable = () -> stringHandler.run();
        //
        //        Thread thread = new Thread(runnable);
        //        thread.start();
        //
        //        Runnable runnable2 = () -> {
        //            int i = 0;
        //            Boolean exit = false;
        //            while (!exit) {
        //                try {
        //                    Thread.sleep(50l);
        //                } catch (InterruptedException e) {
        //                    e.printStackTrace();
        //                }
        //                stringHandler.getInputQueue().add(new StringEvent("test" + i++));
        //                //                        log.error("{}", inputStringQueue);
        //                if (i == 1000) {
        //                    exit = true;
        //                }
        //            }
        //        };
        //
        //        Thread thread2 = new Thread(runnable2);
        //        thread2.start();

        //        RepeaterHeandler repeaterHeandler = RepeaterHeandler.builder().inputQueue((Queue<Event>) stringHandler.getOutputQueue()).build();
        //
        //        Runnable runnablee3 = new Runnable() {
        //            @Override
        //            public void run() {
        //                repeaterHeandler.run(1000L);
        //            }
        //        };
        //        Thread thread3 = new Thread(runnablee3);
        //        thread3.start();

    }

    
    public static class MenuMain {
    
        private List<Handler> handlers = new ArrayList<>();

        private void mainMenu() {
            Menu menu = new Menu();
            menu.setTitle("*** Menu ***");
            Arrays.asList(Handler.HandlerType.values()).forEach(handlerType -> {
                menu.addItem(new MenuItem(handlerType.getName(), this, handlerType.name().toLowerCase()));
            });
            menu.addItem(new MenuItem("Закончить ввод обработчиков", this, "run"));
            menu.execute();
        }

        public void text() {
            System.out.println("Ввведите параметры: ");
            boolean work = true;
            String path;
            while (work) {
                path = ConsoleUtils.readString("Путь до файла: ");
                File file = new File(path);
                if (file.exists()) {
                    TextHandler stringHandler = TextHandler.builder().pathFile(path).build();
                    handlers.add(stringHandler);
                    work = false;
                } else {
                    System.out.println("Файл не существует!");
                }
            }
            System.out.println("Обработчик добавлен");
        }

        public void repeater_counter() {
            System.out.println("Ввведите параметры: ");
            long sec = ConsoleUtils.readNumbers("Количество секунд через сколько выводить на экран:", input -> Long.valueOf(input));
            handlers.add(RepeaterCounterHandler.builder().timeOut(sec * 1000L).build());
            System.out.println("Обработчик добавлен");
        }

        public void words() {
            handlers.add(WordsHandler.builder().build());
            System.out.println("Обработчик добавлен");
        }

        public void repeater_pause() {
            System.out.println("Ввведите параметры: ");
            int sec = ConsoleUtils.readNumbers("Количество секунд паузы:", input -> Integer.valueOf(input));
            handlers.add(RepeaterPauseHandler.builder().timeOut(sec * 1000L).build());
            System.out.println("Обработчик добавлен");
        }

        public void run() {
        }

    }
}
