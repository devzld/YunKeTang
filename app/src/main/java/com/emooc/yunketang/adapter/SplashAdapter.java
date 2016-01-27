package com.emooc.yunketang.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.emooc.yunketang.R;
import com.emooc.yunketang.activity.HomeActivity;
import com.emooc.yunketang.activity.MainActivity;
import com.emooc.yunketang.common.constant.Constants;
import com.emooc.yunketang.common.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZT on 2016/1/12.
 */
public class SplashAdapter extends PagerAdapter{

    Context mContext;
    int[] layoutIds = new int[]{R.layout.guide0,R.layout.guide1,R.layout.guide2};
    List<View> views = new ArrayList<View>();
    private LayoutInflater inflater;
    public SplashAdapter(Context context){
        inflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(layoutIds[position],null);
        container.addView(view);
        if(position==2){
            Button button = (Button) view.findViewById(R.id.button_guide);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferencesUtils.putBoolean(mContext, Constants.IS_FIRST_INSTALL,false);
                    mContext.startActivity(new Intent(mContext, HomeActivity.class));
                    Activity activity = (Activity) mContext;
                    activity.finish();
                }
            });
        }
        views.add(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        views.remove(views.get(position));
    }

    @Override
    public int getCount() {
        return layoutIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
