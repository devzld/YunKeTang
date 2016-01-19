package com.emooc.yunketang.activity;

import android.app.Activity;
import android.os.Bundle;

import com.emooc.yunketang.common.entity.ActivityCollector;

/**
 * Created by BZT on 2016/1/12.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
