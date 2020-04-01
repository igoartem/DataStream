package service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public abstract class QueueMessages {

    private static Queue<String> queueString = new LinkedList<>();

    private static Queue<Character> queueSymbol = new LinkedList<>();

    public static String getMessage() {
        return queueString.poll();
    }

    public static void addMessage(String message) {
        queueString.add(message);
    }

    public static void addAllQueueSymbol(Collection<Character> characters) {
        queueSymbol.addAll(characters);
    }
}
