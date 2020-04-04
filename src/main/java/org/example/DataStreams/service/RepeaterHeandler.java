package org.example.DataStreams.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.lang3.ObjectUtils;
import org.example.DataStreams.domain.Event;
import org.example.DataStreams.domain.SymbolEvent;
import org.example.DataStreams.utils.WordToSymbolsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Builder;

@Builder
public class RepeaterHeandler extends Handler<Event, Event> {
    private static final Logger log = LoggerFactory.getLogger(RepeaterHeandler.class);

    private Queue<Event> inputQueue = new LinkedList();
    private Queue<Event> outputQueue = new LinkedList();

    public void run(Long time) {
        while (true) {

            Event event = getInputQueue().poll();
            log.error("cur {} {}", event, getInputQueue().size());

            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
}
