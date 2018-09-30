package work.mathwiki.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import work.mathwiki.core.logger.Logger;

public class NetworkUtil {
    public static int getNetworkState(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo active = manager.getActiveNetworkInfo();
            if(wifi.isAvailable()){
                return 0;
            }else if(mobile.isConnected()){
                return 1;
            }else if(active.isConnected()){
                return 2;
            }else{
                return -1;
            }
        }else{
            Logger.se("NetworkUtil: Failed to get ConnectivityManager instance...");
            return -2;
        }
    }
}
