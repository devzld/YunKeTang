package com.emooc.yunketang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.emooc.yunketang.R;
import com.emooc.yunketang.common.utils.ImageLoader;

import java.util.ArrayList;

/**
 * Created by BZT on 2016/1/21.
 */
public class VpHeaderAdapter extends PagerAdapter {

    private static final String TAG = "VpHeaderAdapter";
    private String[] mImageIds;
    private Context mContext;
    private ArrayList<ImageView> list;

    public VpHeaderAdapter(Context context,String[] imageIds) {
        mImageIds = imageIds;
        Log.i(TAG, "VpHeaderAdapter: "+imageIds[0]);
        mContext =context;
        list = new ArrayList<>();

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

//        Log.i(TAG, "instantiateItem: "+container.getLayoutParams().width);
//        ImageView image = (ImageView) container.getChildAt(position);


//        image.setLayoutParams(params);
        String url = mImageIds[position];
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundColor(Color.RED);

        Log.i(TAG, "instantiateItem Width: " + imageView.getMeasuredWidth());
        Log.i(TAG, "instantiateItem Height " + imageView.getMeasuredHeight());
        /*ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(1200,600);
        imageView.setLayoutParams(params);*/
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.mipmap.ic_launcher);
/*        ViewGroup.LayoutParams lp= imageView.getLayoutParams();
        lp.width = 1200;
        lp.height = 600;
        imageView.setLayoutParams(lp);*/
       // Log.i(TAG, "instantiateItem: 123" + imageView.getLayoutParams().width);

        imageView.setTag(url);
        ImageLoader.getInstance().showBitmap(url, imageView);
        list.add(imageView);
        container.addView(imageView);
        return imageView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
