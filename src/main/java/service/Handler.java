package service;

import java.util.Queue;

import domain.Event;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Handler<T extends Event, U extends Event> {

    private String name;
    private String description;
    
    abstract Queue<T> getInputQueue();
    abstract Queue<U> getOutputQueue();

}
