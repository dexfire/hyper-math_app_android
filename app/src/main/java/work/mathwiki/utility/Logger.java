package work.mathwiki.utility;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by s2751 on 2018/8/25 0025.
 */

public class Logger {
    private  static final String APP_NAME = "Hyper-Math";
    private String mModuleName;

    private Logger(String moduleName){
        moduleName = moduleName;
    }

    @NonNull
    public static Logger build(String moduleName){
        return new Logger(moduleName);
    }

    public void lge(String msg){
        Log.e(APP_NAME +" #"+mModuleName,msg);
    }

    public void lgi(String msg){
        Log.i(APP_NAME +" #"+mModuleName,msg);
    }

    public void lgw(String msg){
        Log.w(APP_NAME +" #"+mModuleName,msg);
    }
}
