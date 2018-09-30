package work.mathwiki.utility;

import android.os.Environment;

import java.io.File;

/**
 * Created by s2751 on 2018/8/22 0022.
 */

public class ConstFieleds {
    public static final String Product_Name = "Hyper-Math";
    public static final String Preference_Showed_Welcome_Page = "Preference_Showed_Welcome_Page";
    public static final String External_Storage_Path = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String Default_Notes_Path = External_Storage_Path + File.separator + Product_Name;
    public static final String Index_Html = "index.html";
    public static final String Context_Html = "context.html";
    public static final String http = "http";
    public static final String https = "https";

    public static int Screen_Width = 1080;
    public static int Screen_Height = 1920;

    public void init(){

    }
}
