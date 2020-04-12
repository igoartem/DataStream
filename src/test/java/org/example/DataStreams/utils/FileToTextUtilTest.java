package org.example.DataStreams.utils;

import java.io.IOException;
import java.util.stream.Stream;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FileToTextUtilTest {

    @Test
    public void readTest() {

        String pathInputFile = getClass().getClassLoader().getResource("text_test.txt").getFile();
        Stream<String> out = null;
        try {
            out = FileToTextUtil.read(pathInputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(out);
    }
}
