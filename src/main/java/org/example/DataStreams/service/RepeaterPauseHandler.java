package org.example.DataStreams.service;

import org.example.DataStreams.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Builder;

@Builder
public class RepeaterPauseHandler extends Handler<Event, Event> {
    private static final Logger log = LoggerFactory.getLogger(RepeaterPauseHandler.class);

    //    private Queue<Event> inputQueue = new LinkedList();
    //    private Queue<Event> outputQueue = new LinkedList();

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
}