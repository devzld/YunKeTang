package com.emooc.yunketang.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.emooc.yunketang.R;
import com.emooc.yunketang.common.entity.CateEntity;
import com.emooc.yunketang.common.utils.ImageLoader;

import java.util.ArrayList;

/**
 * Created by BZT on 2016/1/20.
 */
public class GvHeaderAdapter extends BaseAdapter {
    private static final String TAG = "GvHeaderAdapter";
    LayoutInflater inflater;
    ArrayList<CateEntity> mList;
    public  GvHeaderAdapter(Context context,ArrayList<CateEntity> list){
        mList = list;
        inflater = LayoutInflater.from(context);
        Log.i(TAG, "GvHeaderAdapter: size"+mList.size());
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView: position"+position);
        View view = inflater.inflate(R.layout.gv_item,null);
        CateEntity entity = mList .get(position);
        TextView textView = (TextView) view.findViewById(R.id.gv_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.gv_image);
       // Log.i(TAG, "getView: "+imageView+","+entity.getImageUrl());
        imageView.setTag(entity.getImageUrl());
        textView.setText(entity.getText());
        ImageLoader.getInstance().showBitmap(entity.getImageUrl(),imageView);
        return view;
    }
}
