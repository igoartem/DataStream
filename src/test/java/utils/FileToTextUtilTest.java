package utils;

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
        String out = FileToTextUtil.read(pathInputFile);
        Assert.assertTrue(StringUtils.isNoneEmpty(out));

    }
}
