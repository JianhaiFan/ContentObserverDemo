package com.xiaofan.contentobseverdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.xiaofan.contentobseverdemo.constant.SqlConstant;

/**
 * @author: 范建海
 * @createTime: 2017/2/20 10:23
 * @className:  ProviderMainActivity
 * @description: 内容提供者演示界面
 * @changed by:
 */
public class ProviderMainActivity extends Activity {

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
    }

    public void onInsert(View v) {

        tv_content.setText("插入数据...");
    }

    public void onUpdate(View v) {

        tv_content.setText("更新数据...");
    }

    public void onDelete(View v) {

        tv_content.setText("删除数据...");
    }

    public void onQuery(View v) {

        tv_content.setText("查询数据...");
    }

}
