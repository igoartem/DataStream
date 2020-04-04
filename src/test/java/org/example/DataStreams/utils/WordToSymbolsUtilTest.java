package org.example.DataStreams.utils;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class WordToSymbolsUtilTest {

    @Test
    public void testConvert() {
        String input = "";

        Collection<Character> output = WordToSymbolsUtil.convert(input);
        Assert.assertNotNull(output);
        Assert.assertTrue(output.isEmpty());

        input = "test";
        output = WordToSymbolsUtil.convert(input);
        Assert.assertNotNull(output);
        Assert.assertFalse(output.isEmpty());
        Assert.assertEquals(output.size(), input.length());

    }
}
