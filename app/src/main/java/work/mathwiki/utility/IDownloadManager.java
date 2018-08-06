package work.mathwiki.utility;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

/**
 * Created by Dexfire on 2018/8/5 0005.
 */

public class IDownloadManager {


    private static IDownloadManager singleInstance = null;
    private static Context context;
    public static IDownloadManager getInstance(Context context) {
        if(singleInstance==null){
            IDownloadManager.context = context;
            singleInstance = new IDownloadManager(context);
        }
        return singleInstance;
    }

    private IDownloadManager(Context context) {
    }

    public static void downloadWithSystemDM(String link){
        Uri uri =  Uri.parse(link);
//        if(uri.isHierarchical())
//        DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//        dm.enqueue(new DownloadManager.Request());
        //TODO : not implemented
    }
}
