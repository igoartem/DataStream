package service;

import java.util.Collection;
import java.util.Queue;

import org.apache.commons.lang3.ObjectUtils;

import domain.StringEvent;
import domain.SymbolEvent;
import utils.WordToSymbolsUtil;

public class StringHandler extends Handler<StringEvent, SymbolEvent> {

    private boolean run = true;

    private Queue<StringEvent> stringEvents;
    private Queue<SymbolEvent> symbolEvents;

    public void run() {
        StringEvent currentMessage;
        while (run) {
            currentMessage = getInputQueue().poll();
            if (ObjectUtils.isNotEmpty(currentMessage)) {
                Collection<Character> characters = WordToSymbolsUtil.convert(currentMessage.getValue());
                QueueMessages.addAllQueueSymbol(characters);
            }
        }
    }

    @Override
    Queue<StringEvent> getInputQueue() {
        return stringEvents;
    }

    @Override
    Queue<SymbolEvent> getOutputQueue() {
        return symbolEvents;
    }
}
