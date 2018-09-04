package work.mathwiki.core.network;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;

import work.mathwiki.core.data.DataManager;


/**
 * Created by Dexfire on 2018/8/10 0010.
 */

public class LocalWebViewClient extends WebViewClient {
    private static final String File_Scheme = "file://";
    private static final String TAG = "LocalWebViewClient";
    private static final String baseLocalUrl = "file://"+Environment.getExternalStorageDirectory() + "/MathWiki/";
    private static final String basePath = Environment.getExternalStorageDirectory() + "/MathWiki/";



    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }



    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//        Uri url = request.getUrl();
//        // @Debug mode
//        android.util.Log.i(TAG,"overloadedOriginURL: "+url.toString());
//        android.util.Log.i(TAG,"url.getScheme: : "+url.getScheme());
//        android.util.Log.i(TAG,"url.getPathSegments: "+url.getPathSegments());
//        if(url.getScheme().equals("file") && !url.toString().startsWith(baseLocalUrl)){
//            view.loadUrl(baseLocalUrl +url.getPath());
//        } else if(!url.getPath().endsWith(".html")){
//            if(url.getPath().endsWith("/"))
//                view.loadUrl(baseLocalUrl +url.toString()+"/home.html");
//            else
//                view.loadUrl(baseLocalUrl +url.toString()+"home.html");
//        }

        return false;
        //return super.shouldOverrideUrlLoading(view, request);
    }

    /***
     * 实现类似本地服务器的功能
     * 如果访问的是一个目录，则跳转到这个目录下的index.html
     * 如果访问的文件不存在，跳转到404.html
     * 如果文件是其他类型，
     * @param url
     * @return 重定向后的url
     */
    private String urlRedirect(Uri url){
        // @Debug mode
        android.util.Log.i(TAG,"overloadedOriginURL: "+url.toString());
        android.util.Log.i(TAG,"url.getScheme: : "+url.getScheme());
        android.util.Log.i(TAG,"url.getPathSegments: "+url.getPathSegments());
        if(url.getScheme().equals("file")){
            File file = new File(basePath + url.getPath());
            if(file.exists()){
                if(file.isDirectory()){
                    return "file://"+file.getAbsolutePath()+"home.html";
                }else{
                    return "file://"+file.getAbsolutePath();
                }
            }else{
                return baseLocalUrl+"home.html";
            }
        }

//        if(url.getScheme().equals("file") && !url.toString().startsWith(baseLocalUrl)){
//            view.loadUrl(baseLocalUrl +url.getPath());
//        } else if(!url.getPath().endsWith(".html")){
//            if(url.getPath().endsWith("/"))
//                view.loadUrl(baseLocalUrl +url.toString()+"/home.html");
//            else
//                view.loadUrl(baseLocalUrl +url.toString()+"home.html");
//        }
        return null;
    }
}
