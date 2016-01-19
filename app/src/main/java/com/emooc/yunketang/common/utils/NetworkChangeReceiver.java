package com.emooc.yunketang.common.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by BZT on 2016/1/12.
 */
public class NetworkChangeReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, NetworkUtil.getNetworkType(context)+" connected", Toast.LENGTH_LONG).show();
    }
}
