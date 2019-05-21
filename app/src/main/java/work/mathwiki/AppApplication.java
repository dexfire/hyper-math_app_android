package work.mathwiki;

import android.provider.Settings;
import android.text.TextUtils;

import com.exyui.android.debugbottle.components.DTInstaller;
import com.exyui.android.debugbottle.ui.BlockCanaryContext;

import java.util.UUID;

import work.mathwiki.base.activities.BaseActivity;
import work.mathwiki.core.account.AccountHelper;
import work.mathwiki.utility.AppSharedPreference;
import work.mathwiki.utility.MD5;

public class AppApplication extends AppContext{

    public static void reInit() {
        ((AppApplication) AppApplication.getInstance()).init();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DTInstaller.install(this)
                .setBlockCanary(new BlockCanaryContext(getApplicationContext()))
                .enable()
                .run();
    }

    private void init() {
        BaseActivity.IS_ACTIVE = true;
        AppSharedPreference.init(this, "osc_update_sp");
        if (TextUtils.isEmpty(AppSharedPreference.getInstance().getDeviceUUID())) {
            String androidId = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
            if (TextUtils.isEmpty(androidId)) {
                androidId = UUID.randomUUID().toString().replaceAll("-", "");
            }
            AppSharedPreference.getInstance().putDeviceUUID(MD5.get32MD5Str(androidId));
        }
        // 初始化异常捕获类
        //AppCrashHandler.getInstance().init(this);
        // 初始化账户基础信息
        AccountHelper.init(this);

        // TODO:  初始化 插桩api @Application Context

        // 初始化网络请求
//        ApiHttpClient.init(this);
        //初始化百度地图
//        SDKInitializer.initialize(this);
//        DBManager.init(this);

        if (AppSharedPreference.getInstance().hasShowUpdate()) {//如果已经更新过
            //如果版本大于更新过的版本，就设置弹出更新
            if (BuildConfig.VERSION_CODE > AppSharedPreference.getInstance().getUpdateVersion()) {
                AppSharedPreference.getInstance().putShowUpdate(true);
            }
        }
    }

}
