package utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class WordToSymbolsUtil {

    public static Collection<Character> convert(String input) {
        List<Character> charList = input.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        return charList;
    }

}
