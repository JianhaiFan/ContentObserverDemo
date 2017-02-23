package com.xiaofan.contentobseverdemo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.xiaofan.contentobseverdemo.constant.SqlConstant;
import com.xiaofan.contentobseverdemo.util.LogUtil;

/**
 * @author: 范建海
 * @createTime: 2017/2/20 10:23
 * @className:  ProviderMainActivity
 * @description: 内容提供者演示界面
 * @changed by:
 */
public class ProviderMainActivity extends Activity {

    private static int i = 0;

    private TextView tv_content;
    // 友盟消息代理
    private PushAgent mPushAgent;
    // 上下文
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        mContext = ProviderMainActivity.this;

        tv_content = (TextView) findViewById(R.id.tv_content);

        mPushAgent = PushAgent.getInstance(mContext);
        String deviceToken = mPushAgent.getRegistrationId();

        if (!TextUtils.isEmpty(deviceToken)) {
            // 设置别名
            mPushAgent.addAlias(SqlConstant.DEFAULT_PARTY_ID, "SINA_WEIBO", new UTrack.ICallBack() {
                @Override
                public void onMessage(boolean isSuccess, String message) {
                    if (isSuccess) {
                        Log.e("yms","ActYxckMain alias is success set! " + message );
                    }
                }
            });
        }
        // 注册监听
        getContentResolver().registerContentObserver(SqlConstant.PROVIDER_MSG_URI,true,observer);
    }

    public void onInsert(View v) {
//        ContentValues values = new ContentValues();
//        values.put("title","推送题目" + ++i);
//        values.put("content","推送内容" + i);
//        getContentResolver().insert(SqlConstant.PROVIDER_MSG_URI,values);
    }

    public void onUpdate(View v) {

        tv_content.setText("更新数据...");
    }

    public void onDelete(View v) {

        tv_content.setText("删除数据...");
    }

    public void onQuery(View v) {
        Uri uri = Uri.parse(SqlConstant.PROVIDER_MSG_SCHEME_AUTHORITY + "/" + SqlConstant.TABLE_MESSAGE_NAME);
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        if (cursor != null) {
            while(cursor.moveToNext()) {
                String title =  cursor.getString(cursor.getColumnIndex("title"));
                String content =  cursor.getString(cursor.getColumnIndex("content"));
                LogUtil.e("title: " + title + ",content: " + content);
            }
        }
    }


    ContentObserver observer = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            onChange();
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            onChange();
        }

        public void onChange() {
            LogUtil.e("==========================");
            LogUtil.e("消息表发生了变化...");
            LogUtil.e("---------------------------");
        }
    };



}
