package utils;

import java.util.Collection;
import java.util.stream.Collectors;

public final class WordToSymbolsUtil {

    private WordToSymbolsUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Collection<Character> convert(String input) {
        return input.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
    }

}
