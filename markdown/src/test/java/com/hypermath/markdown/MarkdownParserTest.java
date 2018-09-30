package com.hypermath.markdown;

import com.hypermath.markdown.converter.MarkdownConverter;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MarkdownParserTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testMd(){
        String html = null;
        try {
            html = MarkdownConverter.fromStream(new FileInputStream("F:\\CreatingSpace\\KaTeX\\README.md"
            ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(html);
    }

}