package com.hypermath.markdown.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.vladsch.flexmark.ast.Document;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;

public class MarkdownConverter {

    public static String fromStream(InputStream ins){
        StringBuilder sb = new StringBuilder();

        try {
            InputStreamReader reader = new InputStreamReader(ins,"utf-8");
            char[] buffer = new char[10240];
            int count;
            while((count=reader.read(buffer))!=-1){
                sb.append(buffer,0,count);
            }
            System.out.println(sb.append("\n\n\n").toString());
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return fromString(sb.toString());
    }


    /***
     *  将Markdown 转化为 html 格式
     *
     * @param src Markdown 格式的文本
     * @return  html 格式的转换结果，不包含html 和 body 等tag
     *                  如果有需要请自行格式化
     */
    public static String fromString(String src){
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);

        Parser.Builder builder = Parser.builder(options);
        Parser parser = builder.build();
        Document doc = parser.parse(src);

        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        return renderer.render(doc);
    }

    public static String fromPath(String path){
        try {
            return fromStream(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String fromFile(File input){
        try {
            return fromStream(new FileInputStream(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Error occurs when converting .md file.";
        }
    }

}