package org.example.DataStreams;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.example.DataStreams.domain.Event;
import org.example.DataStreams.domain.StringEvent;
import org.example.DataStreams.domain.SymbolEvent;
import org.example.DataStreams.service.RepeaterHeandler;
import org.example.DataStreams.service.StringHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataStreamApp {

    private static final Logger log = LoggerFactory.getLogger(DataStreamApp.class);

    public static void main(String[] args) {
//        Queue<StringEvent> inputStringQueue = new ConcurrentLinkedQueue();
//        Queue<SymbolEvent> outputStringQueue = new ConcurrentLinkedQueue();

        StringHandler stringHandler = StringHandler.builder().build();
        Runnable runnable = () -> stringHandler.run();

        Thread thread = new Thread(runnable);
        thread.start();

        Runnable runnable2 = () -> {
            int i = 0;
            Boolean exit = false;
            while (!exit) {
                try {
                    Thread.sleep(50l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stringHandler.getInputQueue().add(new StringEvent("test" + i++));
                //                        log.error("{}", inputStringQueue);
                if (i == 1000) {
                    exit = true;
                }
            }
        };

        Thread thread2 = new Thread(runnable2);
        thread2.start();

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
}
