package com.emooc.yunketang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.emooc.yunketang.R;
import com.emooc.yunketang.view.WrapListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

/**
 * Created by BZT on 2016/1/13.
 */
public class WellCourse extends Fragment {

    private PullToRefreshScrollView refreshScrollView;
    private ViewPager viewPager;
    private GridView gridView;
    private WrapListView wrapListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_well_course,null);
        refreshScrollView = (PullToRefreshScrollView) rootView.findViewById(R.id.refresh_sv);
        viewPager = (ViewPager) rootView.findViewById(R.id.vp_frg1);
        gridView = (GridView) rootView.findViewById(R.id.gv_frg1_cate1);
        wrapListView = (WrapListView) rootView.findViewById(R.id.wlv_frg1_list);


        return rootView;
    }
}
