package org.example.DataStreams.utils;

import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TextToWordsUtilTest {

    private static final String TEXT = "qwer sasf, sdfsdfsdf. wewerwer... ";

    @Test
    public void convertTest() {
        Collection<String> out = TextToWordsUtil.convert(TEXT);
        Assert.assertTrue(CollectionUtils.isNotEmpty(out));
        Assert.assertEquals(4, out.size());

        out = TextToWordsUtil.convert("");
        Assert.assertTrue(CollectionUtils.isEmpty(out));
    }

}
