package org.example.DataStreams.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class SymbolEvent extends Event<Character> {
    private Character value;

}
