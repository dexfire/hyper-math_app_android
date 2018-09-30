package com.hypermath.markdown.util;

import android.util.Log;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    private static final String TAG  = FileUtil.class.getPackage() + ":";

    public static boolean createFolder(File file){
        if (file != null){
            if (!file.exists()){
                return file.mkdirs();
            }else{
                return file.isDirectory();
            }
        }else{
            Log.e(TAG,"createFolder() : the folder to be created is null!");
            return false;
        }
    }
}
