package org.example.DataStreams.utils;

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

    public static Stream<String> read(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath), StandardCharsets.UTF_8);
    }

}
