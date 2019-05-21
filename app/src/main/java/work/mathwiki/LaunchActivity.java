package work.mathwiki;

import work.mathwiki.introduce.IntroduceActivity;
import work.mathwiki.base.activities.BaseActivity;
import work.mathwiki.core.app.AppOperator;
import work.mathwiki.utility.AppSharedPreference;

public class LaunchActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        return R.layout.layout_app_start;
    }

    @Override
    protected void initData() {
        super.initData();
        // 在这里我们检测是否是新版本安装，如果是则进行老版本数据迁移工作
        // 该工作可能消耗大量时间所以放在自线程中执行
        AppOperator.runOnThread(new Runnable() {
            @Override
            public void run() {
                doMerge();
            }
        });
    }

    // TODO: 数据迁移检测与执行
    private void doMerge(){
        /*
            // 判断是否是新版本
            if (Setting.checkIsNewVersion(this)) {
                // Cookie迁移
                String cookie = OSCApplication.getInstance().getProperty("cookie");
                if (!TextUtils.isEmpty(cookie)) {
                    OSCApplication.getInstance().removeProperty("cookie");
                    User user = AccountHelper.getUser();
                    user.setCookie(cookie);
                    AccountHelper.updateUserCache(user);
                    OSCApplication.reInit();
                }
            }

            // 栏目相关数据合并操作
            DynamicTabFragment.initTabPickerManager();

            // Delay...
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 完成后进行跳转操作
            redirectTo();
        }
    */
        redirectTo();
    }

    private void redirectTo() {
        if (AppSharedPreference.getInstance().isFirstInstall()) {
            IntroduceActivity.show(this);
            finish();
        } else {
            MainActivity.show(this);
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
