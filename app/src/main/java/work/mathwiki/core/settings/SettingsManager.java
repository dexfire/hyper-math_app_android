package work.mathwiki.core.settings;

import android.content.Context;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import work.mathwiki.utility.PreferencesUtil;

/**
 * Created by Dexfire on 2018/8/29 0029.
 *
 *  全App的设置管理器，使用getter & setter 来获取和修改设置。
 *
 *  修改设置时会同时写入 preference 中。
 *
 */

public class SettingsManager {


        /* General Section */
        public static final String General_First_Welcome_Splash_Showed = "General_First_Welcome_Splash_Showed";
        public static final String General_Allow_Download_via_Mobile = "General_Allow_Download_via_Mobile";
        /* Markdown Associated */
        public static final String Markdown_Editor_Auto_Changeline = "Markdown_Editor_Auto_Changeline";
        public static final String Markdown_Editor_Highlight_Level = "Markdown_Editor_Highlight_Level";
        public static final String Markdown_Editor_AutoComplete= "Markdown_Editor_AutoComplete";
        public static final String Markdwon_Editor_Space_Indent= "Markdwon_Editor_Space_Indent";
//        public static final String  = "";
//        public static final String  = "";
//        public static final String  = "";
//        public static final String  = "";
//        public static final String  = "";
//        public static final String  = "";
//        public static final String  = "";
//        public static final String  = "";


    private static SettingsManager instance = new SettingsManager();

    //endregion

    public static SettingsManager getInstance() {
        if (instance == null){
            synchronized(SettingsManager.class){
                if (instance == null)
                    instance = new SettingsManager();
            }
        }
        return instance;
    }

    public static void init(Context context){
        instance = getInstance();
        // TODO: load preferences
        //PreferencesUtil pu = PreferencesUtil.getInstance();

        //fields[1]
        //instance.General_Allow_Download_via_Mobile =
    }

}
