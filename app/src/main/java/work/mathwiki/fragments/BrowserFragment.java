package work.mathwiki.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import work.mathwiki.R;
import work.mathwiki.base.fragments.BaseFragment;
import work.mathwiki.core.webview.LocalWebViewClient;
import work.mathwiki.utility.WebViewUtil;

/***
 *  A Fragment shows a Browser to the User
 *  the difference between normal Browsers, it shows the local pages
 */

public class BrowserFragment extends BaseFragment {

    public static final String TAG = "BrowserFragment";

    @BindView(R.id.webview)
    WebView  mWebView;
    @BindView(R.id.home_addr)
    EditText mAdressText;
    @BindView(R.id.fragment_browser_btn_goto)
    TextView mButtonGoto;

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        WebViewUtil.initializeWebView(mWebView);
        mAdressText.setText("content://work.mathwiki.data"+"/index.html");
        mButtonGoto.setOnClickListener(v ->{
            mWebView.loadUrl(mAdressText.getText().toString());
        });
    }

    @Override
    public void setAllowReturnTransitionOverlap(boolean allow) {
        super.setAllowReturnTransitionOverlap(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_browser;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initWebView();
        mWebView.loadUrl("content://work.mathwiki.data"+"/index.html");
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        LocalWebViewClient mWebClient = new LocalWebViewClient();
        mWebView.setWebViewClient(mWebClient);
        //mWebView.setOnScrollChangeListener(mWebViewScollListener);
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

//    View.OnScrollChangeListener mWebViewScollListener = new View.OnScrollChangeListener() {
//        @Override
//        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//            //TODO: 自动隐藏底部导航和顶部Tab
//        }
//    };

    public boolean canGoBack(){
        return mWebView.canGoBack();
    }

    public void goBack(){
        mWebView.goBack();
    }

    public boolean canGoForward(){
        return mWebView.canGoForward();
    }

    public void goForward(){
        mWebView.goForward();
    }
}
