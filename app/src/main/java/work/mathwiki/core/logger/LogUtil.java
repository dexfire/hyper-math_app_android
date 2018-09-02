package work.mathwiki.core.logger;

import android.util.Log;

/**
 * Created by s2751 on 2018/8/22 0022.
 */

public class LogUtil {
    private static final String APP_TAG = "Hyper-Math";

    public static void loge(String modName,String msg){
        Log.e(APP_TAG+" #"+modName,msg);
    }

    public static void logi(String modName,String msg){
        Log.i(APP_TAG+" #"+modName,msg);
    }

    public static void logw(String modName,String msg){
        Log.w(APP_TAG+" #"+modName,msg);
    }

}
