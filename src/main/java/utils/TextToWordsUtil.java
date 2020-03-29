package utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TextToWordsUtil {
    private static final Logger log = LoggerFactory.getLogger(TextToWordsUtil.class);

    private TextToWordsUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Collection<String> convert(String input) {
        log.debug("Convert input {} to collection strings", input);
        Set<String> words = new HashSet<>();
        Pattern pattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            words.add(matcher.group().toLowerCase());
        }
        return words;
    }
}
