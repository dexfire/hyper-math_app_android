package work.mathwiki.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import work.mathwiki.R;
import work.mathwiki.utility.LocalWebViewClient;

/***
 *  A Fragment shows a Browser to the User
 *  the difference between normal Browsers, it shows the local pages
 */

public class BrowserFragment extends Fragment {

    public static final String TAG = "BrowserFragment";
    private WebView  mWebView;
    private EditText mAdressText;
    private Button mButtonGoto;
    public BrowserFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_browser,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWebView = view.findViewById(R.id.fragment_browser_webview);
        mAdressText = view.findViewById(R.id.fragment_browser_edittext_addr);
        mButtonGoto = view.findViewById(R.id.fragment_browser_btn_goto);
        mAdressText.setText("file://" + Environment.getExternalStorageDirectory()+"/MathWiki/index.html");
        mButtonGoto.setOnClickListener(v ->{
            mWebView.loadUrl(mAdressText.getText().toString());
        });
    }

    @Override
    public void setAllowReturnTransitionOverlap(boolean allow) {
        super.setAllowReturnTransitionOverlap(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initWebView();
        mWebView.loadUrl("file://" + Environment.getExternalStorageDirectory()+"/MathWiki/index.html");
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        LocalWebViewClient mWebClient = new LocalWebViewClient();
        mWebView.setWebViewClient(mWebClient);
        WebSettings webSettings = mWebView.getSettings();
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
