package com.emooc.yunketang.activity;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emooc.yunketang.R;
import com.emooc.yunketang.adapter.FragmentAdapter;
import com.emooc.yunketang.fragment.AllCourseFragment;
import com.emooc.yunketang.fragment.MyCourseFragment;
import com.emooc.yunketang.fragment.WellCourseFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends FragmentActivity {
    private static final String TAG = "HomeActivity";
    private List<Fragment> mFragmentList = new ArrayList<>();
    private FragmentAdapter mFragmentAdapter;
    private ViewPager mViewPager;
    private TextView mTab1, mTab2, mTab3;
    private ImageView mTabLineIv;
    private LinearLayout id_tab_item1_ll,id_tab_item2_ll,id_tab_item3_ll;

    private WellCourseFragment wellCourseFragment;
    private AllCourseFragment allCourseFragment;
    private MyCourseFragment myCourseFragment;
    int topstatecolor;
    private int currentIndex;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        init();
        initTabLineWidth();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                return true;
            case R.id.action_message:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init() {
        wellCourseFragment = new WellCourseFragment();
        allCourseFragment = new AllCourseFragment();
        myCourseFragment = new MyCourseFragment();
        mFragmentList.add(wellCourseFragment);
        mFragmentList.add(allCourseFragment);
        mFragmentList.add(myCourseFragment);

        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv.getLayoutParams();
                Log.i(TAG, "onPageScrolled: positionOffset"+positionOffset);
                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记3个页面,
                 * 从左到右分别为0,1,2
                 * 0->1; 1->2; 2->1; 1->0
                 */
                if (currentIndex == 0 && position == 0) {//0->1
                    lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 3)) + currentIndex * (screenWidth / 3);
                } else if (currentIndex == 1 && position == 0) {//1->0
                    lp.leftMargin = (int) (-(1 - positionOffset) * (screenWidth * 1.0 / 3) + currentIndex * (screenWidth / 3));

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 3) + currentIndex * (screenWidth / 3));
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - positionOffset) * (screenWidth * 1.0 / 3) + currentIndex * (screenWidth / 3));
                }

                mTabLineIv.setLayoutParams(lp);

            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        mTab1.setTextColor(topstatecolor);
                        break;
                    case 1:
                        mTab2.setTextColor(topstatecolor);
                        break;
                    case 2:
                        mTab3.setTextColor(topstatecolor);
                        break;
                }
                currentIndex = position;

            }

            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth(){
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 3;
        mTabLineIv.setLayoutParams(lp);

    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        mTab1.setTextColor(Color.BLACK);
        mTab2.setTextColor(Color.BLACK);
        mTab3.setTextColor(Color.BLACK);
    }
    private void initView() {
        topstatecolor = getResources().getColor(R.color.topstate);
        mTab1 = (TextView) this.findViewById(R.id.id_item1_tv);
        mTab2 = (TextView) this.findViewById(R.id.id_item2_tv);
        mTab3 = (TextView) this.findViewById(R.id.id_item3_tv);
        mTabLineIv = (ImageView) this.findViewById(R.id.iv_line);
        mViewPager = (ViewPager) this.findViewById(R.id.vp_main);
        id_tab_item1_ll = (LinearLayout) this.findViewById(R.id.id_tab_item1_ll);
        id_tab_item2_ll = (LinearLayout) this.findViewById(R.id.id_tab_item2_ll);

        id_tab_item3_ll = (LinearLayout) this.findViewById(R.id.id_tab_item3_ll);
        TabClickListener listener = new TabClickListener();
        id_tab_item1_ll.setOnClickListener(listener);
        id_tab_item2_ll.setOnClickListener(listener);
        id_tab_item3_ll.setOnClickListener(listener);

    }

    private class  TabClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.id_tab_item1_ll:
                    mViewPager.setCurrentItem(0);
                    break;
                case R.id.id_tab_item2_ll:
                    mViewPager.setCurrentItem(1);
                    break;
                case R.id.id_tab_item3_ll:
                    mViewPager.setCurrentItem(2);
                    break;
            }
        }
    }
}
