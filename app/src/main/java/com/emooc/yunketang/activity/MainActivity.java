package com.emooc.yunketang.activity;

import android.os.Bundle;
import android.util.Log;

import com.emooc.yunketang.R;
import com.emooc.yunketang.adapter.WellCourseAdapter;
import com.emooc.yunketang.common.constant.Constants;
import com.emooc.yunketang.common.entity.CateEntity;
import com.emooc.yunketang.common.entity.WellCourseEntity;
import com.emooc.yunketang.common.entity.WellCourseItem;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    public String TAG = "MainActivity";
     private PullToRefreshListView refresh;
   // private WrapListView refresh;

    private List<WellCourseEntity> mList = new ArrayList<>();
    private WellCourseAdapter adapter;
    private ArrayList<CateEntity> gvList;
    private String[] imageUrl;

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

       refresh = (PullToRefreshListView) findViewById(R.id.refresh_lv);
    //    refresh = (WrapListView) findViewById(R.id.refresh_lv);

        refresh.setEnabled(false);

        initData();
        adapter = new WellCourseAdapter(MainActivity.this);

        //View headerView = initHeaderView(MainActivity.this);


        //refresh.addHeaderView(headerView);
        refresh.setAdapter(adapter);
        adapter.setData(mList,gvList,imageUrl);

       /* refresh .setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("MainActivity",position+"");
            }
        });*/
    }

//    private View initHeaderView(Context context) {
//        View headerView = LayoutInflater.from(MainActivity.this).inflate(R.layout.listview_header,null);
//        GridView gv_header = (GridView) headerView.findViewById(R.id.gv_list_header);
//        gv_header.setAdapter(new GvHeaderAdapter(MainActivity.this,gvList));
//        return headerView;
//    }

    private void initData() {
        int t=0;
        int count = 1;
        int cc = 0;
        //加入空的实体，布局类型为0.
        WellCourseEntity firstEntity = new WellCourseEntity(0,null,null,null,null);
        mList.add(firstEntity);
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

        gvList = new ArrayList<CateEntity>();
        int gvc = 0;
        for(int i=0;i<8;i++){
            CateEntity entity = new CateEntity("编程语言"+gvc,"http://i4.tietuku.com/14cd1581980fdf94s.png");
            gvList.add(entity);
            gvc++;
        }

        imageUrl = new String[]{
                "http://i12.tietuku.com/b25bd37028a74bd2s.jpg",
                "http://i12.tietuku.com/1882ae0853b5f57bs.jpg",
                "http://i12.tietuku.com/621975cd2a7141a8s.jpg",
                "http://i12.tietuku.com/9e9dc7bbaa5e72bas.jpg",
                "http://i4.tietuku.com/a10860af465a833bs.jpg",
                "http://i4.tietuku.com/cf7e1de91740e6ebs.jpg",
                "http://i11.tietuku.com/8ee2b444f781a40fs.jpg",
                "http://i4.tietuku.com/cdd7ab00ef8e0567s.jpg",
                "http://i4.tietuku.com/0b46b2a694b6851as.jpg"
        };
    }
}
