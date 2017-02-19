package com.xiaofan.contentobseverdemo.observer;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;

import com.xiaofan.contentobseverdemo.util.LogUtil;

/**
 * @author: 范建海
 * @createTime: 2017/2/19 14:52
 * @className:  AirplaneContentObserver
 * @description: 用来观察system表里飞行模式所在行是否发生变化 ， “行”内容观察者 
 * @changed by:
 */
public class AirplaneContentObserver extends ContentObserver {

    private static int MSG_AIRPLANE = 1;

    private Context mContext;
    // 此Handler用来更新UI线程 
    private Handler mHandler;

    public AirplaneContentObserver(Context ctx,Handler handler) {
        super(handler);
        mContext = ctx;
        mHandler = handler;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        onChange();
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        LogUtil.e("URI: " + uri.toString());
        onChange();
    }


    public void onChange() {
        LogUtil.e("-------------the airplane mode has changed-------------");
        try {
            int isAirplaneOpen = Settings.System.getInt(mContext.getContentResolver(),Settings.System.AIRPLANE_MODE_ON);
            mHandler.obtainMessage(MSG_AIRPLANE,isAirplaneOpen).sendToTarget();
        }catch(Settings.SettingNotFoundException e) {

        }
    }
}
