package com.hypermath.markdown.util;

import android.support.annotation.Nullable;

public class TextUtil {
    public static boolean isEmpty(@Nullable CharSequence str){
        return str == null || str.equals("");
    }
}
