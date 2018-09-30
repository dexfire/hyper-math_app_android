package com.hypermath.markdown.util;

public class MarkdownConfig {
    // Instance used for default options
    private static MarkdownConfig defaultConfig;

    private boolean isReady = false;
    private String CSS_Path;
    // indent size
    private int indent = 4;

    public MarkdownConfig getDefaultConfig(){
        if (defaultConfig!=null) return defaultConfig;
        defaultConfig = new MarkdownConfig();
        return defaultConfig;
    }


}
