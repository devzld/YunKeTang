package com.emooc.yunketang.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by BZT on 2016/1/12.
 */
public class NetworkUtil {
    private static ConnectivityManager connMgr;
    public static final String TYPE_WIFI = "Wifi";
    public static final String TYPE_MOBILE = "Mobile";
    public static final String TYPE_NOT_CONNECTED = "No Network";

    public static boolean isNetworkConnected(Context context)
    {
        if(connMgr == null)
        {
            connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }

        NetworkInfo wifiInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifiInfo.isConnected()){
            return true;
        }else if(mobileInfo.isConnected()) {
            return true;
        }else {
            return false;
        }

    }

    public static String getNetworkType(Context context){
        if(connMgr == null)
        {
            connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }

        NetworkInfo netInfo = connMgr.getActiveNetworkInfo();

        if(netInfo != null)
        {
            if(netInfo.getType() == ConnectivityManager.TYPE_WIFI){
                return TYPE_WIFI;
            }else if(netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return TYPE_MOBILE;
            }
        }
        return TYPE_NOT_CONNECTED;
    }
}
