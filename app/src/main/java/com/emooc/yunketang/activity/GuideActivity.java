package com.emooc.yunketang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.emooc.yunketang.R;
import com.emooc.yunketang.common.constant.Constants;
import com.emooc.yunketang.common.utils.PreferencesUtils;

/**
 * 过渡页
 */
public class GuideActivity extends Activity {

    private AlphaAnimation animation;
    private RelativeLayout rl_guide;
    private ProgressBar pb_guide;
    private ImageView iv_logo;
    int progress = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x11:
                    boolean isFirstInstall = PreferencesUtils.getBoolean(GuideActivity.this, Constants.IS_FIRST_INSTALL, true);

                    if (isFirstInstall) {
                        startActivity(new Intent(GuideActivity.this, SplashActivity.class));
                        GuideActivity.this.finish();
                    }

                    iv_logo.setImageResource(R.mipmap.logo_white);
                    break;
                case 0x12:
                    pb_guide.setVisibility(View.VISIBLE);
                    pb_guide.setMax(40);
                    rl_guide.setBackgroundResource(R.mipmap.guide);
                    rl_guide.startAnimation(animation);

                    break;
                case 0x13:
                    progress += 1;
                    pb_guide.setProgress(progress);
                    break;
                case 0x14:
                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                    GuideActivity.this.finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();

        new Thread(r).start();


    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                handler.sendEmptyMessage(0x11);

                boolean isFirstInstall = PreferencesUtils.getBoolean(GuideActivity.this, Constants.IS_FIRST_INSTALL, true);

                if (!isFirstInstall) {
                    Thread.sleep(500);
                    handler.sendEmptyMessage(0x12);
                    for (int i = 0; i < 40; i++) {
                        Thread.sleep(50);
                        handler.sendEmptyMessage(0x13);
                    }
                    handler.sendEmptyMessage(0x14);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };


    private void initView() {
        iv_logo = (ImageView) findViewById(R.id.iv_logo);
        pb_guide = (ProgressBar) findViewById(R.id.pb_guide);
        rl_guide = (RelativeLayout) findViewById(R.id.rl_guide);
        animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(500);
    }
}
