package work.mathwiki.core.network;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class CustomWebChromeClient extends WebChromeClient{
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
    }

    @Override
    public void onPermissionRequest(PermissionRequest request) {
        super.onPermissionRequest(request);
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
    }
}
