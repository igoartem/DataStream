package org.example.DataStreams.service;

import org.example.DataStreams.domain.Event;
import org.example.DataStreams.domain.StringEvent;

import lombok.Builder;

@Builder
public class TextHandler extends Handler<Event, StringEvent> {
    private String pathFile;
}
