package work.mathwiki.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import work.mathwiki.R;
import work.mathwiki.utility.WebViewSetup;

/**
 * Created by Dexfire on 2018/8/28 0028.
 */

public class DocsViewerActivity extends AppCompatActivity {
    private WebView mWebView;
    private ViewGroup mHeaderContainer;
    private ViewGroup mBottomContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docs_viewer);
        mWebView = findViewById(R.id.activity_docs_viewer_webview);
        mHeaderContainer = findViewById(R.id.activity_docs_viewer_titlebar);
        mBottomContainer = findViewById(R.id.activity_docs_viewer_bottombar);
        WebViewSetup.initializeWebView(mWebView);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Called after {@link #onStop} when the current activity is being
     * re-displayed to the user (the user has navigated back to it).  It will
     * be followed by {@link #onStart} and then {@link #onResume}.
     * <p>
     * <p>For activities that are using raw {link Cursor} objects (instead of
     * creating them through
     * {@link #managedQuery(Uri, String[], String, String[], String)},
     * this is usually the place
     * where the cursor should be requeried (because you had deactivated it in
     * {@link #onStop}.
     * <p>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onStop
     * @see #onStart
     * @see #onResume
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * @param view
     * @param menu
     * @hide
     */
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        return true;
    }

    private void initHeader(){
        ImageView icon =  mHeaderContainer.findViewById(R.id.titlebar_ac_docs_viewer_icon);
    }
}
