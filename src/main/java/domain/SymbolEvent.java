package domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SymbolEvent extends Event<Character> {
    private Character value;
    
}
