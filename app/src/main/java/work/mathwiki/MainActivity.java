package work.mathwiki;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.exyui.android.debugbottle.components.DTInstaller;
import com.exyui.android.debugbottle.ui.BlockCanaryContext;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;
import work.mathwiki.activities.SettingsActivity;
import work.mathwiki.activities.GuideSplashActivity;
import work.mathwiki.activities.ShareAppActivity;
import work.mathwiki.base.activities.BaseActivity;
import work.mathwiki.core.settings.SettingsManager;
import work.mathwiki.core.viewmodel.ContentManager;
import work.mathwiki.core.viewmodel.ContentViewsEnum;
import work.mathwiki.core.webview.LocalFileContentProvider;
import work.mathwiki.core.data.DataManager;
import work.mathwiki.core.logger.Logger;
import work.mathwiki.core.webview.LocalWebViewClient;
import work.mathwiki.fragments.NotesFragment;
import work.mathwiki.updater.AppUpdateManager;
import work.mathwiki.utility.ConstFieleds;
import work.mathwiki.utility.NotificationUtility;
import work.mathwiki.utility.PermissionUtility;
import work.mathwiki.utility.SimpleListAdapterUtil;
import work.mathwiki.utility.WebViewUtil;

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
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, EasyPermissions.PermissionCallbacks{

    public static final String Package_Name = "work.mathwiki";
    public static final String PRODUCT_NAME = "Hyper-Math";
    public static final String APP_TAG = "Hyper-Math";

    @BindView(R.id.main_viewpager)
    ViewPager mPager;
    @BindView(R.id.activity_main_container)
    FrameLayout mContainer;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.bottom_navigation)
    NavigationView mNavigationView;

    private Fragment currentFragment;
    private long last_back_press = 0;
    private WindowManager mWindowManager;
    private int mScreenWidth,mScreenHeight;
    private Logger log;
    private static Handler handler;
    private WebView mWebView;

    public static void show(Context context){
        IS_ACTIVE = true;
        context.startActivity(new Intent(context,MainActivity.class));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main_ui;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler();
        initDebugConfig();
        SettingsManager.init(this);
        // TODO : 启动浮窗、非全屏、意蕴&装逼向
        checkStartWelcomeSplash();        // 判断、启动介绍页
        initConstsField();        // 获取一些常用的全局变量
        initUI();        // 布局 Views
        initFragments(savedInstanceState);
        PermissionUtility.checkAllNeededPermissions(this);        // check permission of storage usage

        AppUpdateManager.getInstance().autoCheckUpdates(this);        // check updates

        log.ii_toast(getBaseContext(),"MainActivity.OnCreate() invoked okay ..");
    }

    private void initFragments(Bundle savedInstanceState) {
        if (savedInstanceState == null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out)
                    .add(new NotesFragment(),NotesFragment.TAG)
                    .commit();
        }
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.add(R.id.main_container,new BrowserFragment(),BrowserFragment.TAG)
//                //.addToBackStack(null)
//                .commitNowAllowingStateLoss();
//        getSupportFragmentManager().executePendingTransactions();
//        showFragment(BrowserFragment.TAG);

//        View statusBar = findViewById(android.R.id.statusBarBackground);
//        statusBar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    private void initDebugConfig() {
        // 开启调试信息
        Logger.setDebug(true);
        log = Logger.build("MainActivity");

        // 启动 debug-bottle
        if(Logger.DEBUG){
            DTInstaller.install(getApplication())
                    .setBlockCanary(new BlockCanaryContext(this))
                    //.setOkHttpClient(httpClient)
                    //.setPackageName("your.package")
                    .enable()
                    .run();
        }
    }

    private void initUI() {
        log.dd("initing UI...");
        setContentView(R.layout.activity_main);
        // 绑定 View
        ButterKnife.bind(this);
//        ArrayList<Fragment> list = new ArrayLi        // 默认显示主页
//        mContainer = findViewById(R.id.activity_main_container);
//        //ContentManager.addView(ContentViewsEnum.home,(ViewGroup) getLayoutInflater().inflate(R.layout.layout_home,null), mMainCallBacks);
////        ContentManager.addView(ContentViewsEnum.content,(ViewGroup) getLayoutInflater().inflate(R.layout.layout_context,mContainer,false),mContentCallBacks);
//        ContentManager.addView(ContentViewsEnum.toys,(ViewGroup) getLayoutInflater().inflate(R.layout.layout_toysbox,mContainer,false),mToyBoxCallBacks);
//        ContentManager.addView(ContentViewsEnum.content,(ViewGroup) getLayoutInflater().inflate(R.layout.layout_home,mContainer,false), mContentCallBacks);
//        ContentManager.addView(ContentViewsEnum.home,(ViewGroup)getLayoutInflater().inflate(R.layout.layout_home_list,mContainer,false),mMain2CallBacks);
//        ContentManager.showContent(ContentViewsEnum.home,mContainer);
//        getWindow().setBackgroundDrawableResource(R.drawable.fab__gradient);
//        log.dd("MainActivity views loaded success!");st<>(3);
//        list.add(new HomeFragment());
//        list.add(new NotesFragment());
//        list.add(new ToyBoxFragment());
//        mPager.setAdapter(new FragmentPagesAdapter(getSupportFragmentManager(),list));

        // ActionBar 初始化
        Toolbar toolbar = findViewById(R.id.toolbar);

        //toolbar.setLogo(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);

//        // 浮动按钮初始化
//        FloatingActionButton fab =  findViewById(R.id.fab);
//            fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show());

        // 侧拉菜单初始化
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 底部导航三键
        mNavigationView.setNavigationItemSelectedListener(this);
        BottomNavigationViewEx bnv = findViewById(R.id.navigation);
        bnv.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bnv.setStateListAnimator(null);
    }

    private void openPage(ContentViewsEnum which){

    }

    private void checkStartWelcomeSplash() {
        SharedPreferences preferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        boolean firstStarted =  preferences.getBoolean(ConstFieleds.Preference_Showed_Welcome_Page,false);
        if(!firstStarted) {
            log.ii("Starting UserGuideSplashActivity");
            startActivity(new Intent(this, GuideSplashActivity.class));
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(ConstFieleds.Preference_Showed_Welcome_Page,true).apply();
        }
    }

    private void initConstsField() {
        Point point = new Point();
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getSize(point);
        ConstFieleds.Screen_Width = point.x;
        ConstFieleds.Screen_Height = point.y;
    }

    public static Handler getHandler(){
        return handler;
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
            case R.id.nav_note:
                ContentManager.showContent(ContentViewsEnum.content,mContainer);
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

    private BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        //TODO : 跳转逻辑
                        ContentManager.showContent(ContentViewsEnum.home,mContainer);
                        return true;
                    case R.id.navigation_note:
//                       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                       transaction.replace();
                        ContentManager.showContent(ContentViewsEnum.content,mContainer);
                        if(mWebView!=null)
                        mWebView.loadUrl("content://work.mathwiki.data/index.html");
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
    private ContentManager.ContentCallback mMainCallBacks = new ContentManager.ContentCallback() {
        private WebView webView;
        private EditText addr_text;

        private View.OnClickListener onClickListener = v -> {
            webView.loadUrl(addr_text.getText().toString());
        };

        @Override
        public void onInit(ContentViewsEnum key, ViewGroup view) {
            webView = view.findViewById(R.id.layout_index_webview);
            WebViewUtil.initializeWebView(webView);
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
    private ContentManager.ContentCallback mMain2CallBacks = new ContentManager.ContentCallback() {
        @BindView(R.id.layout_home_list)
        ListView mListView;

        @Override
        public void onInit(ContentViewsEnum key, ViewGroup view) {
            mListView = view.findViewById(R.id.layout_home_list);
            ArrayList<HashMap<String,Object>> data = new ArrayList<>();
            String[] keys = new String[]{"icon","title"};
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_play,getString(R.string.content_start)}));
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_toc,getString(R.string.content_context)}));
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_file,getString(R.string.content_limit)}));
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_file,getString(R.string.content_differential)}));
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_file,getString(R.string.content_differential_equation)}));
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_file,getString(R.string.content_differential_multivariable)}));
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_file,getString(R.string.content_integration)}));
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_file,getString(R.string.content_integration_definite)}));
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_file,getString(R.string.content_integration_indefinite)}));
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_file,getString(R.string.content_integration_multivariable)}));
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_file,getString(R.string.content_integration_vector)}));
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_file,getString(R.string.content_integration_tips)}));
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_file,getString(R.string.content_infinite_series)}));
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_file,getString(R.string.content_formula)}));
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_file,getString(R.string.content_alphaset)}));
            data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_info,getString(R.string.content_about)}));
            mListView.setAdapter(new SimpleAdapter(MainActivity.this,data,R.layout.list_item_simple_icon_with_text,keys,new int[]{R.id.icon,R.id.title}));
            mListView.setOnItemClickListener(onclick);
        }

        @Override
        public void onShow(ContentViewsEnum key, ViewGroup view) {


        }

        @Override
        public void onHide(ContentViewsEnum key, ViewGroup view) {

        }

        @Override
        public boolean onBackPressed() {
            return false;
        }

        private AdapterView.OnItemClickListener onclick = (parent, view, position, id) -> {
            switch (position){
                case 0:
                    break;
                case 1:
                    break;
                default:
                    if (mWebView!=null)
                    mWebView.loadUrl("content://work.mathwiki.data/index.html");
            }
        };
    };

    /***
     *      目录页
     *
     */
    private ContentManager.ContentCallback mContentCallBacks = new ContentManager.ContentCallback() {
        LocalWebViewClient client;
        EditText addr_text;
        private View.OnClickListener onClickListener = v -> {

        };

        public void go(String url){
            if(mWebView ==null) return;
            mWebView.loadUrl(url);
        }

        @Override
        public void onInit(ContentViewsEnum key, ViewGroup view) {
            mWebView = view.findViewById(R.id.layout_index_webview);
            WebViewUtil.initializeWebView(mWebView);
            mWebView.setWebViewClient(new LocalWebViewClient());
            addr_text = view.findViewById(R.id.home_addr);
            addr_text.setText(LocalFileContentProvider.URI_PREFIX + File.separator + "index.html");
            mWebView.loadUrl(LocalFileContentProvider.URI_PREFIX + File.separator + "index.html");
            view.findViewById(R.id.fragment_browser_btn_goto).setOnClickListener(onClickListener);

//            WebViewUtil.initializeWebView(mWebView);
//            client = new LocalWebViewClient();
//            mWebView.setWebViewClient(client);
        }

        @Override
        public void onShow(ContentViewsEnum key, ViewGroup view) {
//            ActionBar actionBar =   getSupportActionBar();
//            if (actionBar != null) {
//                actionBar.setCustomView(new EditText(MainActivity.this));
//            }else{
//                log.e("ERROR: getSupportActionBar() returns null!");
//            }

            if(mWebView !=null){
                if(ContentManager.getCurrent() == ContentViewsEnum.content){
                    log.ii("showing homepage...");
                    mWebView.loadUrl(LocalFileContentProvider.URI_PREFIX + File.separator + "index.html");
                }
//                String url = DataManager.getInstance().getContextUrl();
//                log.i("Context Load Page: " + url);
//                mWebView.loadUrl(url);
            }else
                log.e(" ERROR: Can't find WebView. ");
        }

        @Override
        public void onHide(ContentViewsEnum key, ViewGroup view) {

        }

        @Override
        public boolean onBackPressed() {
            if(mWebView.canGoBack()){
                mWebView.goBack();
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
            //IDownloadManager.downloadOverHttp(MainActivity.this);
            AppUpdateManager.getInstance().autoCheckUpdates(MainActivity.this);
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

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
    //endregion

}
