package com.xiaofan.contentobseverdemo.observer;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import com.xiaofan.contentobseverdemo.util.LogUtil;

/**
 * @author: 范建海
 * @createTime: 2017/2/19 15:26
 * @className:  SMSContentObserver
 * @description: 用来观察系统里短消息的数据库变化  ”表“内容观察者,只要信息数据库发生变化，都会触发该ContentObserver 派生类 
 * @changed by:
 */
public class SMSContentObserver extends ContentObserver {

    private static int MSG_OUTBOXCONTENT = 2;

    private Context mContext;
    // 此Handler用来更新UI线程 
    private Handler mHandler;

    public SMSContentObserver(Context ctx,Handler handler) {
        super(handler);
        mContext = ctx;
        mHandler = handler;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        LogUtil.e("onchange: none");
        onChange();
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        LogUtil.e("SMS URI: " + uri.toString());
        onChange();
    }


    public void onChange() {
        LogUtil.e("the sms table has changed");
        Uri outSMSUri = Uri.parse("content://sms/sent");
        Cursor c = mContext.getContentResolver().query(outSMSUri,null,null,null,null);
        if(c != null) {
            LogUtil.e("the number of send is: " + c.getCount());

            StringBuilder sb = new StringBuilder();

            while(c.moveToNext()) {
                sb.append("发件人手机号码: "+c.getInt(c.getColumnIndex("address"))).append("信息内容: "+c.getString(c.getColumnIndex("body"))).append("\n");
            }
            c.close();

            mHandler.obtainMessage(MSG_OUTBOXCONTENT,sb.toString()).sendToTarget();
        }



    }
}
