package work.mathwiki.activities;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import work.mathwiki.R;
import work.mathwiki.utility.ConstFieleds;

public class GuideSplashActivity extends AppCompatActivity {

    ViewPager pager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome_guide);

        pager = findViewById(R.id.viewpager);

        getSharedPreferences("work.mathwiki",MODE_PRIVATE).edit().putBoolean(ConstFieleds.Preference_Showed_Welcome_Page,true).apply();
    }

}
