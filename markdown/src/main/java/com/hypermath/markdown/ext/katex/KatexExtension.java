package com.hypermath.markdown.ext.katex;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataHolder;

public class KatexExtension implements Parser.ParserExtension,HtmlRenderer.HtmlRendererExtension {


    // region Html Render Extension
    @Override
    public void rendererOptions(MutableDataHolder options) {

    }

    @Override
    public void extend(HtmlRenderer.Builder rendererBuilder, String rendererType) {

    }
    // endregion

    // region Pharser Extension
    @Override
    public void parserOptions(MutableDataHolder options) {

    }

    @Override
    public void extend(Parser.Builder parserBuilder) {

    }
    // endregion
}
