package com.emooc.yunketang.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emooc.yunketang.R;
import com.emooc.yunketang.common.entity.WellCourseItem;
import com.emooc.yunketang.common.utils.ImageLoader;


/**
 * Created by BZT on 2016/1/14.
 */
public class ItemView extends LinearLayout {
    private static final String TAG = "ItemView";
    // private AspectRatioImageView imageView;
    private MyImageView imageView;
    private TextView textView;
    private TextView textView2;
    private LinearLayout ll;
    private int itemType = 0;
    private int imageType = 0;

    public ItemView(Context context) {
        this(context, null);
    }

    public ItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        initView(context);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ItemView);
        itemType = a.getInt(R.styleable.ItemView_itemType, 0);
        imageType = a.getInt(R.styleable.ItemView_imageType,0);
        a.recycle();
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, null);
        ll = (LinearLayout) view.findViewById(R.id.ll);


        // imageView = (AspectRatioImageView) view.findViewById(R.id.imageview);
        imageView = (MyImageView) view.findViewById(R.id.imageview);
        textView = (TextView) view.findViewById(R.id.textview1);
        textView2 = (TextView) view.findViewById(R.id.textview2);
        if (itemType == 1) {
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.CENTER_HORIZONTAL;
//        textView.setLayoutParams(params);
//        textView2.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView2.setGravity(Gravity.CENTER_HORIZONTAL);

        }
        if(imageType==1){
            imageView.setType(1);

            this.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        }


        this.addView(view);
    }

//    public AspectRatioImageView getImageView(){
//        return this.imageView;
//    }

    public MyImageView getImageView() {
        return this.imageView;
    }

    public TextView getTextView() {
        return this.textView;
    }

    public TextView getTextView2() {
        return this.textView2;
    }

    public void setData(WellCourseItem item) {

        textView.setText(item.getTitle());
        textView2.setText(item.getPrice());
        ImageLoader.getInstance().showBitmap(item.getImageUrl(), imageView);

    }


    public void clearData() {

        textView.setText("");
        textView2.setText("");
        //ImageLoader.getInstance().displayImage(item.getImageUrl(), imageView);

    }


}
