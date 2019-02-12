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
import work.mathwiki.core.webview.LocalWebViewClient;

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
        //Activity activity =  getActivity();
        //if(activity!=null) activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.layout_home,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWebView = view.findViewById(R.id.webview);
        mAdressText = view.findViewById(R.id.home_addr);
        mButtonGoto = view.findViewById(R.id.fragment_browser_btn_goto);
        mAdressText.setText("file://" + Environment.getExternalStorageDirectory()+"/MathWiki/home.html");
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
        mWebView.loadUrl("file://" + Environment.getExternalStorageDirectory()+"/MathWiki/home.html");
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
