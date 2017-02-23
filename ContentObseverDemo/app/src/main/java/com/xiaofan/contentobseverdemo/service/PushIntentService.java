package com.xiaofan.contentobseverdemo.service;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.umeng.message.UmengMessageService;
import com.xiaofan.contentobseverdemo.MessageListActivity;
import com.xiaofan.contentobseverdemo.bean.MessageBody;
import com.xiaofan.contentobseverdemo.bean.MessageExtra;
import com.xiaofan.contentobseverdemo.bean.MessageExtraMapBean;
import com.xiaofan.contentobseverdemo.bean.UmengMessageBean;
import com.xiaofan.contentobseverdemo.constant.SqlConstant;
import com.xiaofan.contentobseverdemo.util.GsonUtil;
import com.xiaofan.contentobseverdemo.util.LogUtil;
import com.xiaofan.contentobseverdemo.util.ManifestUtil;
import com.xiaofan.contentobseverdemo.util.NotifacationUtil;

import org.android.agoo.common.AgooConstants;

/**
 * @author: FanJH
 * @ClassName: PushIntentService
 * @date: 2016年6月3日 下午2:09:35
 * @Description: 友盟消息推送
 * @Beauty: FanJH
 */
public class PushIntentService extends UmengMessageService {

    /**
     * 消息推送通知栏显示的标题
     **/
    public static final String UMENG_PUSH_TITLE = "推送一条龙";

    @Override
    public void onMessage(Context context, Intent intent) {
        try {
            //可以通过MESSAGE_BODY取得消息体
            String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
            Log.e("yms", "message：" + message);
            // 转换消息实体Bean
            UmengMessageBean info = GsonUtil.json2Object(message, UmengMessageBean.class);
            if (info != null) {
                // 如果应用在前台，没有通知栏提示

//                MessageExtraMapBean mapBean = GsonUtil.json2Object(info.extra.map, MessageExtraMapBean.class);
//                handlerPushMsg(context, info.body, info.extra, mapBean);
                handlerPushMsg(context, info.body, info.extra, null);
                // 保存数据到数据库中
                ContentValues values = new ContentValues();
                values.put("title",info.body.title + " : " + SystemClock.currentThreadTimeMillis());
                values.put("content",info.body.text +" : " + SystemClock.currentThreadTimeMillis());
                getContentResolver().insert(SqlConstant.PROVIDER_MSG_URI,values);

            }
        } catch (Exception e) {
            LogUtil.e("友盟消息解析异常：" + e.toString());
        } finally {
            // TODO 提取消息类型保存，供消息处理中心同步该类型的消息
        }
    }

    /**
     * 处理消息
     *
     * @param context      上下文
     * @param body         消息体
     * @param extraMessage 其他消息
     * @param mapBean      其他消息中维护了一个实体Bean(后台以字符串的形式返回，需要再次解析)
     */
    private void handlerPushMsg(Context context, MessageBody body, MessageExtra extraMessage, MessageExtraMapBean mapBean) {
        // 开启界面的意图
        Intent intent = null;
//        String newsType = extraMessage.messageType;
        String title = UMENG_PUSH_TITLE;
        String text = body.text;

        if (ManifestUtil.isTheForeground(context)) {
            // 在前台
            intent = new Intent();
            intent.setClass(context, MessageListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        } else {
            // 在后台
            intent = context.getPackageManager().getLaunchIntentForPackage(ManifestUtil.getPackageName(context));
            // 传数据的篮子
            Bundle bundle = new Bundle();
            bundle.putString("startAppParamKey","msg_list_activity");
            intent.putExtra("startAppBundleKey", bundle);
            // 相应的跳转逻辑没有处理
        }

        NotifacationUtil.sendNotifacation(title, text, intent);

    }

}
