package com.emooc.yunketang.activity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.emooc.yunketang.R;
import com.emooc.yunketang.adapter.WellCourseAdapter;
import com.emooc.yunketang.common.constant.Constants;
import com.emooc.yunketang.common.entity.WellCourseEntity;
import com.emooc.yunketang.common.entity.WellCourseItem;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    public String TAG = "MainActivity";
    private PullToRefreshListView refresh;
    private List<WellCourseEntity> mList = new ArrayList<>();
    private WellCourseAdapter adapter;
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            //super.handleMessage(msg);
//            refresh.onRefreshComplete();
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "initImageLoader: ===========>>>" + Environment.getExternalStorageDirectory().getAbsolutePath());
       refresh = (PullToRefreshListView) findViewById(R.id.refresh_lv);

        refresh.setEnabled(false);

        initData();
        adapter = new WellCourseAdapter(MainActivity.this);

        refresh.setAdapter(adapter);
        adapter.setData(mList);

       /* refresh .setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("MainActivity",position+"");
            }
        });*/
    }

    private void initData() {
        int t=0;
        int count = 1;
        int cc = 0;
        for(int k=0;k<8;k++) {
            count++;
            t++;
            if(count==5){
                count=1;
            }
            List<WellCourseItem> list = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                cc++;
                WellCourseItem item = new WellCourseItem("云课堂子项标题", Constants.IMAGES[cc], "http://www.baidu.com", "￥100.00");
                list.add(item);
            }
            WellCourseEntity entity = new WellCourseEntity(count, "免费好课"+t, "简笔画速成", "免费排行榜", list);
            Log.i(TAG, "initData-hash: "+entity.getHashValue());
            mList.add(entity);
        }
    }
}
