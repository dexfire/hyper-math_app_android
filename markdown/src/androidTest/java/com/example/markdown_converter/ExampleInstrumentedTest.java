package com.example.markdown_converter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.hypermath.markdown.converter.MarkdownConverter;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();



        String html = null;
        try {
            html = MarkdownConverter.fromStream(new FileInputStream("F:\\CreatingSpace\\KaTeX\\README.md"
            ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(html);
        assertEquals("com.example.markdown_converter.test", appContext.getPackageName());
    }
}
