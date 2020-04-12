package org.example.DataStreams.domain;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class EventDeserializer<T extends Event> implements Deserializer<T> {

    private Class<T> tClass;

    public EventDeserializer(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        T value = null;
        try {
            value = mapper.readValue(data, tClass);
        } catch (Exception e) {

        }
        return value;
    }
}
