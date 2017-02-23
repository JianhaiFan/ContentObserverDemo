package com.xiaofan.contentobseverdemo.application;

import android.app.Application;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.xiaofan.contentobseverdemo.db.ContentObserverSqliteOpenHelper;
import com.xiaofan.contentobseverdemo.service.PushIntentService;
import com.xiaofan.contentobseverdemo.util.LogUtil;


/**
 * @author: 范建海
 * @createTime: 2017/2/13 15:02
 * @className:  GreenDaoApplication
 * @description: 应用程序类
 * @changed by:
 */
public class App extends Application {
    // 应用实例
    private static App instance;
    // 数据库帮助类
    private static ContentObserverSqliteOpenHelper helper;
    // 友盟推送代理
    private PushAgent mPushAgent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // 初始化ImageLoader
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                // 采用软引用
                .memoryCache(new WeakMemoryCache())
                // 输出Log日志
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);

        initUmengMessage();
    }

    /**
     * 初始化友盟相关组件
     */
    private void initUmengMessage() {
        /**
         * 友盟消息别名设置思路：
         * 			在设置页面退出的时候清空当前登录人的别名
         * 			在进入到主页的时候进行设置别名
         */
        mPushAgent = PushAgent.getInstance(instance);
        // 调试默认，正式发布要取消这个
        mPushAgent.setDebugMode(true);

        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                Log.e("yms","application deviceToken:" + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                LogUtil.e("yms","注册失败...");
                LogUtil.e("yms","s: " + s +",s1: " + s1);
            }
        });
        // 完全自定义消息处理
        mPushAgent.setPushIntentServiceClass(PushIntentService.class);
    }

    /**
     * 获取应用实例
     */
    public static App getInstance() {
        return instance;
    }

}
