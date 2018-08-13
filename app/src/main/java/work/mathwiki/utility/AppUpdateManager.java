package work.mathwiki.utility;

/**
 * Created by Dexfire on 2018/8/13 0013.
 */

public class AppUpdateManager {
    private static AppUpdateManager instance;

    public static AppUpdateManager get(){
        if(instance==null){
            instance=new AppUpdateManager();
        }
        return instance;
    }

    public void checkUpdates(){
        if(checkAppUpdate()){

        }
    }

    public boolean checkAppUpdate(){
        //TODO : not implemented method
        return true;
    }

    public boolean checkPluginsUpdate(){
        return true;
    }

    public boolean checkDataUpdate(){
        return true;
    }
}
