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


/**
 * Created by Dexfire on 2018/8/10 0010.
 */

public class LocalWebViewClient extends WebViewClient {

    private static final String TAG = "LocalWebViewClient";
    private static final String baseLocalUrl = "file://"+Environment.getExternalStorageDirectory() + "/MathWiki/";
    private static final String basePath = Environment.getExternalStorageDirectory() + "/MathWiki/";

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Uri url = request.getUrl();
        // @Debug mode
        android.util.Log.i(TAG,"overloadedOriginURL: "+url.toString());
        android.util.Log.i(TAG,"url.getScheme: : "+url.getScheme());
        android.util.Log.i(TAG,"url.getPathSegments: "+url.getPathSegments());
        if(url.getScheme().equals("file") && !url.toString().startsWith(baseLocalUrl)){
            view.loadUrl(baseLocalUrl +url.getPath());
        } else if(!url.getPath().endsWith(".html")){
            if(url.getPath().endsWith("/"))
                view.loadUrl(baseLocalUrl +url.toString()+"/home.html");
            else
                view.loadUrl(baseLocalUrl +url.toString()+"home.html");
        }

        return true;
        //return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }

    @Override
    public void onFormResubmission(WebView view, Message dontResend, Message resend) {
        super.onFormResubmission(view, dontResend, resend);
    }

    @Override
    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
        super.doUpdateVisitedHistory(view, url, isReload);
    }

    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        return super.shouldOverrideKeyEvent(view, event);
    }

    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        super.onScaleChanged(view, oldScale, newScale);
    }

    @Override
    public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
        return super.onRenderProcessGone(view, detail);
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
