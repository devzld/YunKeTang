package com.emooc.yunketang.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.emooc.yunketang.R;

/**
 * Created by BZT on 2016/1/15.
 */
public class ItemHeadView extends RelativeLayout {

    private static final String TAG = "main";
    TextView textView1;
    TextView textView2;

    public ItemHeadView(Context context) {
        this(context, null);
    }

    public ItemHeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_header,null);
        textView1 = (TextView) view.findViewById(R.id.headname);
        textView2 = (TextView) view.findViewById(R.id.headdes);
        this.addView(view);
    }

    public void  setData(String value1,String value2){
       // Log.i(TAG, "setData: value"+value1);
        textView1.setText(value1);
        textView2.setText(value2);
    }

    public TextView getTextViewName(){
        return textView1;
    }
    public TextView getTextViewDes(){
        return textView2;
    }
    public void  clearData(){
        // Log.i(TAG, "setData: value"+value1);
        textView1.setText("");
        textView2.setText("");
    }
}
