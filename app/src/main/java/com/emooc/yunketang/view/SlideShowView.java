package com.emooc.yunketang.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.emooc.yunketang.R;
import com.emooc.yunketang.common.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class SlideShowView extends FrameLayout {

    private static final String TAG = "SlideShowView";
    private ImageLoader imageLoader = ImageLoader.getInstance();

    private final static int IMAGE_COUNT = 5;
    private final static int TIME_INTERVAL = 5;
    private final static boolean isAutoPlay = true;
    
    private String[] imageUrls;
    private List<ImageView> imageViewsList;
    private List<View> dotViewsList;
    
    private ViewPager viewPager;
    private int currentItem  = 0;
    private ScheduledExecutorService scheduledExecutorService;
    
    private Context context;
    
    //Handler
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            viewPager.setCurrentItem(currentItem);
        }
        
    };

    int widthSize;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        widthSize = MeasureSpec.getSize(widthMeasureSpec);
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);


        //setMeasuredDimension(widthSize, widthSize / 2);

    }

//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        Log.i(TAG, "onMeasure: widthSize=" + getChildCount());
//        if(getChildCount()>0){
//            RelativeLayout r = (RelativeLayout) getChildAt(0);
//            ViewPager v = (ViewPager) r.getChildAt(0);
//            v.layout(0,0,widthSize,widthSize/2);
//            Log.i(TAG, "onLayout: getChildCount="+r.getChildAt(0));
//        }
//       // Log.i(TAG, "onLayout: getChildCount="+getChildAt(0));
////        View child = getChildAt(0);
////        child.layout(0,0,widthSize,widthSize/2);
//    }

    public SlideShowView(Context context) {
        this(context,null);
        // TODO Auto-generated constructor stub
    }
    public SlideShowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }
    public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;


		
        initData();
        if(isAutoPlay){
            startPlay();
        }
        
    }

    private void startPlay(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
    }
    /**
     * ֹͣ�ֲ�ͼ�л�
     */
    private void stopPlay(){
        scheduledExecutorService.shutdown();
    }
    /**
     * ��ʼ�����Data
     */
    private void initData(){
        imageViewsList = new ArrayList<ImageView>();
        dotViewsList = new ArrayList<View>();

        // һ�������ȡͼƬ
        new GetListTask().execute("");
    }
    /**
     * ��ʼ��Views��UI
     */
    private void initUI(Context context){
    	if(imageUrls == null || imageUrls.length == 0)
    		return;
    	
        LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);
        
        LinearLayout dotLayout = (LinearLayout)findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();
        
        // �ȵ������ͼƬ�������
        for (int i = 0; i < imageUrls.length; i++) {
        	ImageView view =  new ImageView(context);

            view.setTag(imageUrls[i]);
        	if(i==0)//��һ��Ĭ��ͼ
        		view.setBackgroundResource(R.mipmap.ic_launcher);
        	view.setScaleType(ScaleType.FIT_XY);
        	imageViewsList.add(view);
        	
        	ImageView dotView =  new ImageView(context);
        	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        	params.leftMargin = 4;
			params.rightMargin = 4;
			dotLayout.addView(dotView, params);
        	dotViewsList.add(dotView);
		}
        
        viewPager = (ViewPager) findViewById(R.id.slide_viewpager);
        viewPager.setFocusable(true);
        
        viewPager.setAdapter(new MyPagerAdapter());
        ViewGroup.LayoutParams lp = viewPager.getLayoutParams();
        Log.i("height", "" + widthSize);
        lp.height = widthSize/2;
        viewPager.setLayoutParams(lp);
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }

    /**
     * ���ViewPager��ҳ��������
     *
     */
    private class MyPagerAdapter  extends PagerAdapter{

        @Override
        public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            //((ViewPag.er)container).removeView((View)object);
            ((ViewPager)container).removeView(imageViewsList.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
        	ImageView imageView = imageViewsList.get(position);

			ImageLoader.getInstance().showBitmap(imageView.getTag()+"",imageView);

                    ((ViewPager) container).addView(imageViewsList.get(position));
            return imageViewsList.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imageViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }
        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub
            
        }
        
    }
    /**
     * ViewPager�ļ�����
     * ��ViewPager��ҳ���״̬�����ı�ʱ����
     * 
     */
    private class MyPageChangeListener implements OnPageChangeListener{

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
            case 1:// ���ƻ�����������
                isAutoPlay = false;
                break;
            case 2:// �����л���
                isAutoPlay = true;
                break;
            case 0:// �������������л���ϻ��߼������
                // ��ǰΪ���һ�ţ���ʱ�������󻬣����л�����һ��
                if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                    viewPager.setCurrentItem(0);
                }
                // ��ǰΪ��һ�ţ���ʱ�������һ������л������һ��
                else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                    viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                }
                break;
        }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub
            
            currentItem = pos;
            for(int i=0;i < dotViewsList.size();i++){
                if(i == pos){
                    ((View)dotViewsList.get(pos)).setBackgroundResource(R.mipmap.icon_recommend_indicator_active);
                }else {
                    ((View)dotViewsList.get(i)).setBackgroundResource(R.mipmap.icon_recommend_indicator);
                }
            }
        }
        
    }
    
    /**
     *ִ���ֲ�ͼ�л�����
     *
     */
    private class SlideShowTask implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (viewPager) {
                currentItem = (currentItem+1)%imageViewsList.size();
                handler.obtainMessage().sendToTarget();
            }
        }
        
    }
    
    /**
     * ����ImageView��Դ�������ڴ�
     * 
     */
    private void destoryBitmaps() {

        for (int i = 0; i < IMAGE_COUNT; i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                //���drawable��view������
                drawable.setCallback(null);
            }
        }
    }
 

	/**
	 * �첽����,��ȡ����
	 * 
	 */
	class GetListTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			try {
				// ����һ����÷���˽ӿڻ�ȡһ���ֲ�ͼƬ�������ǴӰٶ��ҵļ���ͼƬ
				
				imageUrls = new String[]{
						"http://i12.tietuku.com/b25bd37028a74bd2s.jpg",
                        "http://i12.tietuku.com/1882ae0853b5f57bs.jpg",
                        "http://i12.tietuku.com/621975cd2a7141a8s.jpg",
                        "http://i12.tietuku.com/9e9dc7bbaa5e72bas.jpg",
                        "http://i4.tietuku.com/a10860af465a833bs.jpg",
                        "http://i4.tietuku.com/cf7e1de91740e6ebs.jpg"
				};
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (result) {
		        initUI(context);
			}
		}
	}
	

}