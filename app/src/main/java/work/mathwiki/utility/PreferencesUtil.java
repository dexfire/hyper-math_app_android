package work.mathwiki.utility;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import work.mathwiki.core.logger.Logger;

/**
 * Created by Dexfire on 2018/8/28 0028.
 */

public class PreferencesUtil {

    private static final String Preferences_Name = "AppSettings";
    private static Logger log;
    private static PreferencesUtil instance = null;
    private Context savedContext = null;
    private SharedPreferences mPreferences = null;
    private SharedPreferences.Editor mEditor = null;

    public static PreferencesUtil getInstance() {
        if(instance!=null){
            return instance;
        }else{
            log.ee("You must run init() first.");
            return null;
        }
    }

    private PreferencesUtil() {
        log = Logger.build("PreferenceUtil")
    }

    public void init(Context context){
        savedContext = context;
        if (instance == null){
            synchronized (PreferencesUtil.class){
                if (instance==null){
                    instance = new PreferencesUtil();
                }
            }
        }
        if(mPreferences==null || mEditor==null){
            mPreferences =  context.getSharedPreferences(Preferences_Name,Context.MODE_PRIVATE);
            mEditor = mPreferences.edit();
        }
    }

    public String getString(Context context,String name){
        init(context);
        return mPreferences.getString(name,"");
    }

    public String getString(Context context,String name,String fb){
        init(context);
        return mPreferences.getString(name,fb);
    }

    public int getInt(Context context,String name){
        init(context);
        return mPreferences.getInt(name,0);
    }

    public int getInt(Context context,String name,int fb){
        init(context);
        return mPreferences.getInt(name,fb);
    }

    public Set<String> getStringSet(Context context,String name){
        init(context);
        return mPreferences.getStringSet(name,new HashSet<String>());
    }

    public boolean getBoolean(Context context, String name,boolean fb){
        init(context);
        return mPreferences.getBoolean(name,fb);
    }

    public void putInt(Context context,String name,int val){
        init(context);
        mEditor.putInt(name,val);
        mEditor.commit();
    }

    public void putString(Context context,String name,String val){
        init(context);
        mEditor.putString(name,val);
        mEditor.commit();
    }
}
