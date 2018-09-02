package work.mathwiki.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.preference.Preference;

import java.util.HashSet;
import java.util.Set;
import java.util.prefs.Preferences;

/**
 * Created by Dexfire on 2018/8/28 0028.
 */

public class PreferencesUtil {

    private static final String Preferences_Name = "AppSettings";

    private static PreferencesUtil instance = null;
    private SharedPreferences mPreferences = null;
    private SharedPreferences.Editor mEditor = null;

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

    private void init(Context context){
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
