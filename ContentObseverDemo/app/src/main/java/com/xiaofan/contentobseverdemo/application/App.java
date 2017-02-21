package com.xiaofan.contentobseverdemo.application;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.xiaofan.contentobseverdemo.db.ContentObserverSqliteOpenHelper;


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
    }
    /**
     * 获取应用实例
     */
    public static App getInstance() {
        return instance;
    }

    /**
     * 获取DAO session
     * @param partyId 当前用户的唯一标识
     * @return
     */
//    public static DaoSession getDaoSession(String partyId) {
//        if(!TextUtils.isEmpty(partyId)) {
//            // 数据库名称
//            String dbName = SqlConstant.SQLITE_FILE_NAME + "_" + partyId + SqlConstant.DB_EXTENSION_NAME;
//            // 实例为空，或者数据库文件不存在，需要重新创建数据库
//            if (daoMaster == null || !ExternalStorageUtil.exists(CommonConstant.DB_PATH + dbName) ) {
//                helper = new ContentObserverSqliteOpenHelper(new GreenDaoContext(instance), dbName);
//                if (helper != null) {
//                    daoMaster = new DaoMaster(helper.getWritableDatabase());
//                }
//            }
//
//            if(daoMaster != null) {
//                daoSession = daoMaster.newSession();
//            }
//        }
//        return daoSession;
//    }

}
