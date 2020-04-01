package service;

import java.util.LinkedList;
import java.util.Queue;

public class RepeaterHeandler extends Handler {

    private Queue inputQueue = new LinkedList();
    private Queue outputQueue = new LinkedList();

    @Override
    public Queue getInputQueue() {
        return inputQueue;
    }

    @Override
    public Queue getOutputQueue() {
        return outputQueue;
    }
}
