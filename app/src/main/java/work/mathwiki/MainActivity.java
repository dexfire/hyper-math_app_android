package work.mathwiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Browser;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.widget.TextView;

import work.mathwiki.activities.SettingsActivity;
import work.mathwiki.activities.WelcomeGuideActivity;
import work.mathwiki.fragments.BrowserFragment;
import work.mathwiki.utility.AppUpdateManager;
import work.mathwiki.utility.ConstFieleds;
import work.mathwiki.utility.PermissionUtility;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String APP_TAG = "Hyper-Math";
    private DrawerLayout mDrawerLayout;
    private Fragment currentFragment;
    private long last_back_press = 0;
    private int mScreenWidth,mScreenHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;

        SharedPreferences preferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        boolean firstStarted =  preferences.getBoolean(ConstFieleds.Preference_Showed_Welcome_Page,false);
        if(!firstStarted) startActivity(new Intent(getApplicationContext(), WelcomeGuideActivity.class));
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab);
            fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            mDrawerLayout.addDrawerListener(toggle);
            toggle.syncState();

        NavigationView navigationView = findViewById(R.id.bottom_navigation);
            navigationView.setNavigationItemSelectedListener(this);
        BottomNavigationView navigation = findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            navigation.setStateListAnimator(null);

        //TODO : check permission of storage usage
        PermissionUtility.checkAllNeededPermissions(this);
        //TODO : check update
        AppUpdateManager.get();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_container,new BrowserFragment(),BrowserFragment.TAG)
                //.addToBackStack(null)
                .commitNowAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
        showFragment(BrowserFragment.TAG);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(getSupportFragmentManager().getBackStackEntryCount()>0){
            getSupportFragmentManager().popBackStack();
        } else if( currentFragment instanceof  BrowserFragment && ((BrowserFragment)getFragment(BrowserFragment.TAG)).canGoBack()){
            ((BrowserFragment)getFragment(BrowserFragment.TAG)).goBack();
        }else{
            if(System.currentTimeMillis() - last_back_press < 900 ){
                MainActivity.this.finish();
            }else{
                last_back_press = System.currentTimeMillis();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch(id){
            case R.id.nav_home:
                showFragment(BrowserFragment.TAG);

                break;
            case R.id.nav_context:

                break;
            case R.id.nav_toybox:
                break;
            case R.id.nav_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.nav_goto:

                break;
            case R.id.nav_share:
                break;
            case R.id.nav_exit:
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        //TODO : 跳转逻辑
                        return true;
                    case R.id.navigation_context:
                        return true;
                    case R.id.navigation_toybox:
                        return true;
                }
                return false;
            };
}
