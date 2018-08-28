package work.mathwiki.preferences;

/**
 * Created by Dexfire on 2018/8/28 0028.
 */

public class PreferencesUtil {
    private static PreferencesUtil instance = null;

    public static PreferencesUtil getInstance() {
        if(instance==null){
            synchronized (PreferencesUtil.class){
                if(instance==null)
                    instance = new PreferencesUtil();
            }
        }
        return instance;
    }

    private PreferencesUtil() {
    }


}
