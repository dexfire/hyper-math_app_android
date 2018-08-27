package work.mathwiki.utility;

import android.net.Uri;
import android.os.Environment;
import android.webkit.WebResourceRequest;

public class LocalSocketServer {
    public String mExternalStorage;
    public String mServerRootDir;

    public LocalSocketServer(){
        this(new StringBuilder("file://").append(Environment.getExternalStorageDirectory().getAbsolutePath()).toString());
    }

    public LocalSocketServer(String root){
        mServerRootDir = root;
        mExternalStorage = Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public WebResourceRequest redirRequest(WebResourceRequest req){
        Uri uri = req.getUrl();
        if(uri.getScheme().equals("file")){
            if(uri.getPath().startsWith(mExternalStorage)){
                
            }else{

            }
        }else{
            return req;
        }
        return req;
    }

}
