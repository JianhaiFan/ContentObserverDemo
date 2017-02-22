package com.xiaofan.contentobseverdemo.observer;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

import com.xiaofan.contentobseverdemo.util.LogUtil;

/**
 * Created by fanjianhai on 2017/2/19.
 */
public class CustomContentObserver extends ContentObserver{

    private static int MSG_CUSTOME_MSG = 3;

    private Context mContext;

    private Handler mHander;

    public CustomContentObserver(Context ctx,Handler handler) {
        super(handler);
        mContext = ctx;
        mHander = handler;
    }


    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        onChange();
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        LogUtil.e("SMS URI: " + uri.toString());
        onChange();
    }


    private void onChange() {

    }
}
