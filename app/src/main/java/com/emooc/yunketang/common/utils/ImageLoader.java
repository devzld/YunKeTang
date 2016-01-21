package com.emooc.yunketang.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by BZT on 2016/1/19.
 */
public class ImageLoader {
    private static final String TAG = "ImageLoader";
    private static  ImageLoader imageLoader;
    private LruCache<String,Bitmap> cache;

    private ImageLoader(){
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory/4;
        Log.i(TAG, "ImageLoader: cacheSize"+cacheSize);
        cache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

    }

    public static ImageLoader getInstance(){
        if(imageLoader==null){
            imageLoader = new ImageLoader();
        }
        return imageLoader;
    }

    public void addBitmapToCahce(String url,Bitmap bitmap){
        if(getBitmapFromCache(url)==null){
            cache.put(url,bitmap);
        }
    }

    public Bitmap getBitmapFromCache(String url){
        return cache.get(url);
    }

    public void showBitmap(String urlString, ImageView imageView) {
        Bitmap bitmap = getBitmapFromCache(urlString);
        if(bitmap==null){
            new ImageAsycTask(urlString,imageView).execute(urlString);
        }else{
            imageView.setImageBitmap(bitmap);
        }
    }



    private Bitmap getBitmapByUrl(String urlString) {
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
           // Log.i(TAG, "getBitmapByUrl: connection"+connection.getResponseCode()+"");
            is = new BufferedInputStream(connection.getInputStream());
            Log.i(TAG, "getBitmapByUrl: is"+is);
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private class ImageAsycTask extends AsyncTask<String,Void,Bitmap>{
        private ImageView mImageView;
        private String mUrl;

        public ImageAsycTask(String url,ImageView imageView){
            mImageView = imageView;
            mUrl = url;
        }



        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = getBitmapByUrl(params[0]);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Log.i(TAG, "onPostExecute: bitmap=" + mUrl + "," + bitmap);
            if(bitmap!=null) {
                addBitmapToCahce(mUrl, bitmap);
            }
            if(mImageView.getTag().equals(mUrl)){
                mImageView.setImageBitmap(bitmap);
            }
        }
    }
}
