package domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StringEvent extends Event<String> {
    private String value;
}
