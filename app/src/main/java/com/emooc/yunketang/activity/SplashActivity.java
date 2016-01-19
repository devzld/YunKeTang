package com.emooc.yunketang.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

import com.emooc.yunketang.R;
import com.emooc.yunketang.adapter.SplashAdapter;

/**
 * 第一次安装时的引导页
 */
public class SplashActivity extends Activity {

    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        viewPager = (ViewPager) findViewById(R.id.viewpager_guide);
        viewPager.setAdapter(new SplashAdapter(SplashActivity.this));
//        Button button = (Button) viewPager.getChildAt(2).findViewById(R.id.button_guide);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(SplashActivity.this,MainActivity.class));
//                SplashActivity.this.finish();
//            }
//        });
    }
}
