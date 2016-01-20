package com.emooc.yunketang.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by BZT on 2016/1/15.
 */
public class MyImageView extends ImageView {
    private static final String TAG = "MyImageView";
    private int type = 0;
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setType(int value){
        type = value;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       // int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //Log.i(TAG, "onMeasure: widthSize="+widthSize+",heightSize="+heightSize);
        if(type==0){
            setMeasuredDimension(widthSize,widthSize/2);
        }else if(type==1){
            setMeasuredDimension(widthSize,  heightSize);
            Log.i(TAG, "onMeasure: widthSize="+widthSize+",heightSize="+heightSize);

        }

    }
}
