package org.example.DataStreams.domain;

public class StringEventDeserializer extends EventDeserializer<StringEvent> {
    public StringEventDeserializer() {
        super(StringEvent.class);
    }
}
