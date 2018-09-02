package work.mathwiki.utility;

import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by Dexfire on 2018/8/28 0028.
 *  设置WebView的通用属性的 Tool class
 */

public class WebViewSetup {
    public static void initializeWebView(WebView webView){
        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        // 可能引发跨站脚本攻击(XSS)
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setUseWideViewPort(true);
        webSettings.setDisplayZoomControls(false);
    }
}
