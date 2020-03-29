package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FileToTextUtil {
    private static final Logger log = LoggerFactory.getLogger(FileToTextUtil.class);

    private FileToTextUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String read(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            log.error("", e);
        }
        return contentBuilder.toString();
    }

}
