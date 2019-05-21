package work.mathwiki.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import work.mathwiki.R;
import work.mathwiki.base.activities.BaseActivity;

public class SettingsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_settings;
    }
    public static void show(Context context) {
        IS_ACTIVE = true;
        context.startActivity(new Intent(context, SettingsActivity.class));
    }
}
