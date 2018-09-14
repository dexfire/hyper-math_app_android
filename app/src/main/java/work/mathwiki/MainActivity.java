package work.mathwiki;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.io.File;

import work.mathwiki.activities.SettingsActivity;
import work.mathwiki.activities.GuideSplashActivity;
import work.mathwiki.activities.ShareAppActivity;
import work.mathwiki.core.content.ContentManager;
import work.mathwiki.core.content.ContentViewsEnum;
import work.mathwiki.core.content.LocalFileContentProvider;
import work.mathwiki.core.data.DataManager;
import work.mathwiki.core.logger.Logger;
import work.mathwiki.core.network.AppUpdateInfo;
import work.mathwiki.core.network.IDownloadManager;
import work.mathwiki.core.webview.LocalWebViewClient;
import work.mathwiki.updater.AppUpdateManager;
import work.mathwiki.utility.ConstFieleds;
import work.mathwiki.utility.NotificationUtility;
import work.mathwiki.utility.PermissionUtility;
import work.mathwiki.utility.WebViewSetup;

/***
 *  Hyper-Math
 *  ========================================
 *  Author: dexfire, Iris, chen
 *  Copyright: All Rights Reserved.
 *
 * 编程规范：
 *  1、<em>资源变量名<em/> 命名规则
 *      大类名 - 小类名 - 标识名
 *      （倒序 → 一致性不高）
 *  2、避免使用Fragment
 *      因为Fragment的bug实在比较多，而且使用效果的却不好。
 *  3、先搭框架，后期优化+美化
 */

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static final String Package_Name = "work.mathwiki";
    public static final String PRODUCT_NAME = "Hyper-Math";
    public static final String APP_TAG = "Hyper-Math";

    private FrameLayout mContainer;
    private DrawerLayout mDrawerLayout;
    private Fragment currentFragment;
    private long last_back_press = 0;
    private int mScreenWidth,mScreenHeight;
    private Logger log;
    private static Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 开启调试信息
        Logger.setDebug(true);
        log = Logger.build("MainActivity");
        handler = new Handler();
        // TODO : 启动浮窗、非全屏、意蕴&装逼向

        // 判断、启动介绍页
        SharedPreferences preferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        boolean firstStarted =  preferences.getBoolean(ConstFieleds.Preference_Showed_Welcome_Page,false);
        if(!firstStarted) {
            log.ii("Starting UserGuideSplashActivity");
            startActivity(new Intent(this, GuideSplashActivity.class));
        }

        // 布局 Views
        // 默认显示主页
        log.dd("Main Activity starting");
        ContentManager.addView(ContentViewsEnum.home,(ViewGroup) getLayoutInflater().inflate(R.layout.layout_home,null),mIndexCallBacks);
        ContentManager.addView(ContentViewsEnum.context,(ViewGroup) getLayoutInflater().inflate(R.layout.layout_context,null),mContextCallBacks);
        ContentManager.addView(ContentViewsEnum.toys,(ViewGroup) getLayoutInflater().inflate(R.layout.layout_toysbox,null),mToyBoxCallBacks);
        setContentView(R.layout.activity_main);
        mContainer = findViewById(R.id.activity_main_container);
        ContentManager.showContent(ContentViewsEnum.home,mContainer);
        getWindow().setBackgroundDrawableResource(R.drawable.fab__gradient);
        log.dd("MainActivity views loaded success!");

        // ActionBar 初始化
        Toolbar toolbar = findViewById(R.id.toolbar);

        //toolbar.setLogo(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);

        // 浮动按钮初始化
        FloatingActionButton fab =  findViewById(R.id.fab);
            fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        // 侧拉菜单初始化
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            mDrawerLayout.addDrawerListener(toggle);
            toggle.syncState();


        // 底部导航三键

        NavigationView navigationView = findViewById(R.id.bottom_navigation);
            navigationView.setNavigationItemSelectedListener(this);
        BottomNavigationView navigation = findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            navigation.setStateListAnimator(null);


         // 权限检查
        //TODO : check permission of storage usage
        PermissionUtility.checkAllNeededPermissions(this);
        //TODO : check update
        AppUpdateManager.getInstance().checkUpdates(this);

//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.add(R.id.main_container,new BrowserFragment(),BrowserFragment.TAG)
//                //.addToBackStack(null)
//                .commitNowAllowingStateLoss();
//        getSupportFragmentManager().executePendingTransactions();
//        showFragment(BrowserFragment.TAG);

//        View statusBar = findViewById(android.R.id.statusBarBackground);
//        statusBar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(getSupportFragmentManager().getBackStackEntryCount()>0){
            getSupportFragmentManager().popBackStack();
        }else if(ContentManager.getCurrentCallback()!=null){
            ContentManager.getCurrentCallback().onBackPressed();
        }else{
            if(System.currentTimeMillis() - last_back_press < 900 ){
                MainActivity.this.finish();
            }else{
                last_back_press = System.currentTimeMillis();
                NotificationUtility.makeShortToast(this,getResources().getString(R.string.press_again_to_exit));
            }
        }
    }

    public static Handler getHandler(){
        return handler;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    // region 底部导航栏 点击事件
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle activity_main_navigation view item clicks here.
        int id = item.getItemId();
        switch(id){
            case R.id.nav_home:
                ContentManager.showContent(ContentViewsEnum.home,mContainer);
                break;
            case R.id.nav_context:
                ContentManager.showContent(ContentViewsEnum.context,mContainer);
                break;
            case R.id.nav_toybox:
                ContentManager.showContent(ContentViewsEnum.toys,mContainer);
                break;
            case R.id.nav_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.nav_goto:
                NotificationUtility.makeStatusBarNotification(this,"Hyper-Math 通知测试","这是通知内容","这个我也不知道是个什么鬼",R.drawable.ic_info);
                break;
            case R.id.nav_share:
                startActivity(new Intent(MainActivity.this, ShareAppActivity.class));
                break;
            case R.id.nav_exit:
                System.exit(0);
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    // endregion

    // region Fragment 辅助
    private Fragment getFragment(String tag){
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    private void showFragment(String tag){
        showFragment(getSupportFragmentManager().findFragmentByTag(tag));
    }

    private void showFragment(Fragment fragment){
        if(fragment!=null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if(currentFragment!=null&&currentFragment.isAdded()&&!currentFragment.isHidden()) transaction.hide(currentFragment);
            if(fragment.isAdded()){
                transaction.show(fragment);
            }else{
                transaction.add(R.id.container,fragment,fragment.getTag()).show(fragment);
            }
            currentFragment = fragment;
            //transaction.addToBackStack(null);
            transaction.commitNow();
        }
    }
    // endregion

    // 底部导航键 选择事件

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        //TODO : 跳转逻辑
                        ContentManager.showContent(ContentViewsEnum.home,mContainer);
                        return true;
                    case R.id.navigation_context:
                        ContentManager.showContent(ContentViewsEnum.context,mContainer);
                        return true;
                    case R.id.navigation_toybox:
                        ContentManager.showContent(ContentViewsEnum.toys,mContainer);
                        return true;
                }
                return false;
            };

    // region Content Callbacks
    /***
     *      主页
     */
    private ContentManager.ContentCallback mIndexCallBacks = new ContentManager.ContentCallback() {
        private WebView webView;
        private EditText addr_text;

        private View.OnClickListener onClickListener = v -> {
            webView.loadUrl(addr_text.getText().toString());
        };

        @Override
        public void onInit(ContentViewsEnum key, ViewGroup view) {
            webView = view.findViewById(R.id.layout_index_webview);
            WebViewSetup.initializeWebView(webView);
            webView.setWebViewClient(new LocalWebViewClient());
            addr_text = view.findViewById(R.id.home_addr);
            addr_text.setText(LocalFileContentProvider.URI_PREFIX + File.separator + "index.html");
            webView.loadUrl(LocalFileContentProvider.URI_PREFIX + File.separator + "index.html");
            view.findViewById(R.id.fragment_browser_btn_goto).setOnClickListener(onClickListener);
        }

        @Override
        public void onShow(ContentViewsEnum key, ViewGroup view) {
            // 再次点击，回主页
            ActionBar actionBar =   getSupportActionBar();
            if (actionBar != null) {
                actionBar.setCustomView(new EditText(MainActivity.this));
            }else{
                log.e("ERROR: getSupportActionBar() returns null!");
            }
            if(ContentManager.getCurrent() == ContentViewsEnum.home){
                log.ii("showing homepage...");
                webView.loadUrl(LocalFileContentProvider.URI_PREFIX + File.separator + "index.html");
            }

        }

        @Override
        public void onHide(ContentViewsEnum key, ViewGroup view) {

        }

        @Override
        public boolean onBackPressed() {
            if(webView.canGoBack()){
                webView.goBack();
                return true;
            }else{
                return false;
            }
        }
    };
    /***
     *      目录页
     *
     */
    private ContentManager.ContentCallback mContextCallBacks = new ContentManager.ContentCallback() {
        WebView webView;
        LocalWebViewClient client;
        private View.OnClickListener onClickListener = v -> {

        };
        @Override
        public void onInit(ContentViewsEnum key, ViewGroup view) {
            webView = view.findViewById(R.id.layout_context_webview);

            WebViewSetup.initializeWebView(webView);
            client = new LocalWebViewClient();
            webView.setWebViewClient(client);
        }

        @Override
        public void onShow(ContentViewsEnum key, ViewGroup view) {
            ActionBar actionBar =   getSupportActionBar();
            if (actionBar != null) {
                actionBar.setCustomView(new EditText(MainActivity.this));
            }else{
                log.e("ERROR: getSupportActionBar() returns null!");
            }

            if(webView!=null){
                String url = DataManager.getInstance().getContextUrl();
                log.i("Context Load Page: " + url);
                webView.loadUrl(url);
            }else
                log.e(" ERROR: Can't find WebView. ");
        }

        @Override
        public void onHide(ContentViewsEnum key, ViewGroup view) {

        }

        @Override
        public boolean onBackPressed() {
            if(webView.canGoBack()){
                webView.goBack();
                return true;
            }else{
                return false;
            }
        }
    };

    /***
     *    Toybox
     */

    private ContentManager.ContentCallback mToyBoxCallBacks = new ContentManager.ContentCallback() {
        @Override
        public void onInit(ContentViewsEnum key, ViewGroup view) {

        }

        @Override
        public void onShow(ContentViewsEnum key, ViewGroup view) {
            //IDownloadManager.downloadOverHttps(MainActivity.this);
            AppUpdateManager.getInstance().checkUpdates(MainActivity.this);
        }

        @Override
        public void onHide(ContentViewsEnum key, ViewGroup view) {

        }

        /***
         *  不拦截按键事件
         * @return false
         */
        @Override
        public boolean onBackPressed() {
            return false;
        }
    };
    //endregion
}
