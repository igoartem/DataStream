package org.example.DataStreams.service;

import java.util.LinkedList;
import java.util.Queue;

import org.example.DataStreams.domain.Event;
import org.example.DataStreams.domain.StringEvent;
import org.example.DataStreams.domain.SymbolEvent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Handler<T extends Event, U extends Event> {

    //    private String name;
    //    private String description;
    private Queue<T> inputEvents = new LinkedList<>();
    private Queue<U> outputEvents = new LinkedList<>();

    //    abstract Queue<T> getInputQueue();
    //
    //    abstract Queue<U> getOutputQueue();

    public Queue<T> getInputQueue() {
        return inputEvents;
    }

    public Queue<U> getOutputQueue() {
        return outputEvents;
    }

}
