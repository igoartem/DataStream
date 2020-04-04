package org.example.DataStreams.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.DataStreams.domain.StringEvent;
import org.example.DataStreams.domain.SymbolEvent;
import org.example.DataStreams.utils.WordToSymbolsUtil;

import lombok.Builder;
import lombok.SneakyThrows;

@Builder
public class StringHandler extends Handler<StringEvent, SymbolEvent> {
    private static final Logger log = LogManager.getLogger(StringHandler.class);
    
    @SneakyThrows
    public void run() {
        log.error("!!!!!!!!!!!!!!!!!!!dich");
        StringEvent currentMessage;
        currentMessage = getInputQueue().poll();
        log.error("cur {}", currentMessage);
        while (true) {
            Thread.sleep(100L);
            currentMessage = getInputQueue().poll();
            log.error("cur {} {}", currentMessage, getInputQueue().size());
            if (ObjectUtils.isNotEmpty(currentMessage)) {
                Collection<Character> characters = WordToSymbolsUtil.convert(currentMessage.getValue());
                Collection<SymbolEvent> symbolEvents = new ArrayList<>();
                characters.forEach(character -> {
                    symbolEvents.add(SymbolEvent.builder().value(character).build());
                });
                getOutputQueue().addAll(symbolEvents);
                log.error("ss {}", getOutputQueue().size());
            }
        }
    }
    
}
