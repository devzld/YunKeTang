package com.emooc.yunketang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emooc.yunketang.R;
import com.emooc.yunketang.view.WrapListView;

/**
 * Created by BZT on 2016/1/22.
 */
public class AllCourseFragment extends Fragment {
    private WrapListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_course,null);
        listView = (WrapListView) rootView.findViewById(R.id.ac_list);


        return rootView;
    }
}
