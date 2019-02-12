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
    private static Logger log = Logger.build(PreferencesUtil.class.getName());
    private static PreferencesUtil instance = null;
    private SharedPreferences mPreferences = null;
    private SharedPreferences.Editor mEditor = null;

    public static PreferencesUtil getInstance() {
        if(instance==null){
            log.ee("You must run init() first.");
        }
        return instance;
    }

    private PreferencesUtil() {
        log = Logger.build("PreferenceUtil");
    }

    public void init(Context context){
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

    public String getString(String name){
        return mPreferences.getString(name,"");
    }

    public String getString(String name,String fb){
        return mPreferences.getString(name,fb);
    }

    public int getInt(String name){
        return mPreferences.getInt(name,0);
    }

    public int getInt(String name,int fb){
        return mPreferences.getInt(name,fb);
    }

    public Set<String> getStringSet(String name){
        return mPreferences.getStringSet(name,new HashSet<String>());
    }

    public boolean getBoolean(Context context, String name,boolean fb){
        return mPreferences.getBoolean(name,fb);
    }

    public void putInt(String name,int val){
        mEditor.putInt(name,val);
        mEditor.commit();
    }

    public void putString(String name,String val){
        mEditor.putString(name,val);
        mEditor.commit();
    }
    
    public void putBoolean(String name,Boolean val){
        mEditor.putBoolean(name,val);
        mEditor.commit();
    }
}
