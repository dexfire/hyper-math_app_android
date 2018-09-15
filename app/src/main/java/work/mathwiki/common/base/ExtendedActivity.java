package work.mathwiki.common.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import work.mathwiki.core.logger.Logger;

/**
 * Created by Dexfire on 2018/8/29 0029.
 */

public class ExtendedActivity extends AppCompatActivity {
    private Handler handler;
    private WindowManager mWindowManager;
    private Logger logger;
    private int mScreenHeight;
    private int mScreenWidth;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        mScreenHeight = displayMetrics.heightPixels;
//        mScreenWidth = displayMetrics.widthPixels;
//
//        logger = Logger.build(getClass().getName());
//        logger.i("=======Activity onCreate()=======");
//        handler =  new Handler();
//
//        // 沉浸式状态栏
//        if (Build.VERSION.SDK_INT >= 21) {
//            Window window = getWindow();
//            window.clearFlags(0x4000000);
//            window.getDecorView().setSystemUiVisibility(0x100);
//            window.addFlags(Integer.MIN_VALUE);
//            //TODO 夜间模式 状态栏颜色切换
////            if (LocalUserSettingUtils.isNight() && this.isNeedNightModel) {
////                if (SharedPreferencesUtils.getInstance().getInt(this, SharedPreferencesConstant.STATUSBAR_COLOR) == -1) {
////                    SharedPreferencesUtils.getInstance().putInt(this, SharedPreferencesConstant.STATUSBAR_COLOR, window.getStatusBarColor());
////                }
////                window.getDecorView();
////                window.setStatusBarColor(getResources().getColor(R.color.gray_night));
////            }
//        }
//
//
//    }

    public int getScreenWidthinPixel(){
        return mScreenWidth;
    }

    public int getScreenHeightinPixel(){
        return mScreenHeight;
    }

    public void setStatusBarMode(Activity activity, boolean mode){
        if (Build.VERSION.SDK_INT >= 23) {
            View decorView = activity.getWindow().getDecorView();
            if (decorView != null) {
                int systemUiVisibility = decorView.getSystemUiVisibility();
                if (mode) {
                    systemUiVisibility |= 8192;
                } else {
                    systemUiVisibility &= -8193;
                }
                decorView.setSystemUiVisibility(systemUiVisibility);
            }
        }
    }

    public Handler getHandler(){
        return this.handler;
    }

    public void post(Runnable runnable){
        getBaseContext();
        this.handler.post(runnable);
    }

    public WindowManager getWindowManager(){
        return mWindowManager;
    }

    public void setScreenHeight(int mScreenHeight) {
        this.mScreenHeight = mScreenHeight;
    }

    public void setScreenWidth(int mScreenWidth) {
        this.mScreenWidth = mScreenWidth;
    }
}
