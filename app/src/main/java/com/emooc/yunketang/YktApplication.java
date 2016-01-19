package com.emooc.yunketang;

import android.app.Application;

/**
 * Created by BZT on 2016/1/12.
 */
public class YktApplication extends Application {
    private static final String TAG = "YktApplication";

    @Override
    public void onCreate() {

        super.onCreate();

    }



    /**
     * "http://site.com/image.png" // from Web
     * "file:///mnt/sdcard/image.png" // from SD card
     * "file:///mnt/sdcard/video.mp4" // from SD card (video thumbnail)
     * "content://media/external/images/media/13" // from content provider
     * "content://media/external/video/media/13" // from content provider (video thumbnail)
     * "assets://image.png" // from assets
     * "drawable://" + R.drawable.img // from drawables (non-9patch images)
     */





    /****************************************
    ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
// Load image, decode it to Bitmap and display Bitmap in ImageView (or any other view
//  which implements ImageAware interface)
imageLoader.displayImage(imageUri, imageView);
// Load image, decode it to Bitmap and return Bitmap to callback
imageLoader.loadImage(imageUri, new SimpleImageLoadingListener() {
    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        // Do whatever you want with Bitmap
    }
});
// Load image, decode it to Bitmap and return Bitmap synchronously
Bitmap bmp = imageLoader.loadImageSync(imageUri);
     */

    /*********************************************
    // Load image, decode it to Bitmap and display Bitmap in ImageView (or any other view
//  which implements ImageAware interface)
imageLoader.displayImage(imageUri, imageView, options, new ImageLoadingListener() {
    @Override
    public void onLoadingStarted(String imageUri, View view) {
        ...
    }
    @Override
    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
        ...
    }
    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        ...
    }
    @Override
    public void onLoadingCancelled(String imageUri, View view) {
        ...
    }
}, new ImageLoadingProgressListener() {
    @Override
    public void onProgressUpdate(String imageUri, View view, int current, int total) {
        ...
    }
});
// Load image, decode it to Bitmap and return Bitmap to callback
ImageSize targetSize = new ImageSize(80, 50); // result Bitmap will be fit to this size
imageLoader.loadImage(imageUri, targetSize, options, new SimpleImageLoadingListener() {
    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        // Do whatever you want with Bitmap
    }
});
// Load image, decode it to Bitmap and return Bitmap synchronously
ImageSize targetSize = new ImageSize(80, 50); // result Bitmap will be fit to this size
Bitmap bmp = imageLoader.loadImageSync(imageUri, targetSize, options);

     */
}
