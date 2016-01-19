package com.emooc.yunketang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.emooc.yunketang.R;
import com.emooc.yunketang.common.entity.WellCourseEntity;
import com.emooc.yunketang.view.ItemHeadView;
import com.emooc.yunketang.view.ItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZT on 2016/1/14.
 */
public class WellCourseAdapter extends BaseAdapter {

    private static final String TAG = "WellCourseAdapter";
    int[][] ids = new int[][]{{R.id.h1,R.id.b1,R.id.item11,R.id.item21,R.id.item31,R.id.item41,R.id.item51},
                               {R.id.h2,R.id.b2,R.id.item12,R.id.item22,R.id.item32,R.id.item42,R.id.item52},
                               {R.id.h3,R.id.b3,R.id.item13,R.id.item23,R.id.item33,0,0},
                               {R.id.h4,R.id.b4,R.id.item14,R.id.item24,R.id.item34,R.id.item44,R.id.item54}};
    LayoutInflater inflater;

    Context mContext;
    private List<WellCourseEntity> mList = new ArrayList<>();

    public WellCourseAdapter(Context context) {

        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData( List<WellCourseEntity> list){
        mList.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getLayoutType();
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public int getCount() {
        //Log.i("TAG","size===>"+mList.size());
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
        ViewHolder viewHolder;

        int type = getItemViewType(position);
        WellCourseEntity entity = mList.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();

            switch (type) {
                case 1:
                    convertView = inflater.inflate(R.layout.list_item1, null);

                    break;
                case 2:
                    convertView = inflater.inflate(R.layout.list_item2, parent,false);

                    break;
                case 3:
                    convertView = inflater.inflate(R.layout.list_item3, null);
                    break;
                case 4:
                    convertView = inflater.inflate(R.layout.list_item4, null);

                    break;

            }


            viewHolder.itemHeadView = (ItemHeadView) convertView.findViewById(ids[type-1][0]);

            RelativeLayout rlbottom = (RelativeLayout) convertView.findViewById(ids[type-1][1]);
            viewHolder.bottomTv = (TextView) rlbottom.findViewById(R.id.bottom_tv);

            viewHolder.itemView1 = (ItemView) convertView.findViewById(ids[type-1][2]);
            viewHolder.itemView2 = (ItemView) convertView.findViewById(ids[type-1][3]);
            viewHolder.itemView3 = (ItemView) convertView.findViewById(ids[type-1][4]);

            viewHolder.itemView4 = (ItemView) convertView.findViewById(ids[type-1][5]);
            viewHolder.itemView5 = (ItemView) convertView.findViewById(ids[type-1][6]);

            viewHolder.itemView1.getImageView().setTag(entity.list.get(0).getImageUrl());
            viewHolder.itemView2.getImageView().setTag(entity.list.get(1).getImageUrl());
            viewHolder.itemView3.getImageView().setTag(entity.list.get(2).getImageUrl());
            if(type!=3) {
                viewHolder.itemView4.getImageView().setTag(entity.list.get(3).getImageUrl());
                viewHolder.itemView5.getImageView().setTag(entity.list.get(4).getImageUrl());
            }


            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }
//            WellCourseEntity entity = mList.get(position);


            viewHolder.itemView1.setData(entity.list.get(0));
            viewHolder.itemView2.setData(entity.list.get(1));
            viewHolder.itemView3.setData(entity.list.get(2));

            viewHolder.itemHeadView.setData(entity.getHeadName(), entity.getHeadDes());

            viewHolder.bottomTv.setText(entity.getBottomDes());
            switch (type) {
                case 1:
                    viewHolder.itemView4.setData(entity.list.get(3));
                    viewHolder.itemView5.setData(entity.list.get(4));
                    break;
                case 2:
                    viewHolder.itemView4.setData(entity.list.get(3));
                    viewHolder.itemView5.setData(entity.list.get(4));
                    break;
                case 3:
                    break;
                case 4:
                    viewHolder.itemView4.setData(entity.list.get(3));
                    viewHolder.itemView5.setData(entity.list.get(4));
                    break;

            }


        return convertView;
    }

    class ViewHolder {
        ItemHeadView itemHeadView;
        TextView bottomTv;

        ItemView itemView1;
        ItemView itemView2;
        ItemView itemView3;
        ItemView itemView4;
        ItemView itemView5;
        Button textView;
        //ItemView itemView6;

    }

//    public void resetViewHolder(int type){
//        viewHolder.itemHeadView.clearData();
//        viewHolder.itemView1.clearData();
//        viewHolder.itemView2.clearData();
//        viewHolder.itemView3.clearData();
//        if(type!=3) {
//            viewHolder.itemView4.clearData();
//            viewHolder.itemView5.clearData();
//        }
//    }

}
