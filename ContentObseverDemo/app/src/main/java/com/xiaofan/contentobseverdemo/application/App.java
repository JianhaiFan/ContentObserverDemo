package com.xiaofan.contentobseverdemo.application;

import android.app.Application;
import android.text.TextUtils;

import com.xiaofan.contentobseverdemo.constant.CommonConstant;
import com.xiaofan.contentobseverdemo.constant.SqlConstant;
import com.xiaofan.contentobseverdemo.db.ContentObserverSqliteOpenHelper;
import com.xiaofan.contentobseverdemo.greendao.DaoMaster;
import com.xiaofan.contentobseverdemo.greendao.DaoSession;
import com.xiaofan.contentobseverdemo.greendao.GreenDaoContext;
import com.xiaofan.contentobseverdemo.util.ExternalStorageUtil;


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
    // DAO管理器实例
    private static DaoMaster daoMaster;
    // DAOsession
    private static DaoSession daoSession;
    // 数据库帮助类
    private static ContentObserverSqliteOpenHelper helper;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
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
    public static DaoSession getDaoSession(String partyId) {
        if(!TextUtils.isEmpty(partyId)) {
            // 数据库名称
            String dbName = SqlConstant.SQLITE_FILE_NAME + "_" + partyId + SqlConstant.DB_EXTENSION_NAME;
            // 实例为空，或者数据库文件不存在，需要重新创建数据库
            if (daoMaster == null || !ExternalStorageUtil.exists(CommonConstant.DB_PATH + dbName) ) {
                helper = new ContentObserverSqliteOpenHelper(new GreenDaoContext(instance), dbName);
                if (helper != null) {
                    daoMaster = new DaoMaster(helper.getWritableDatabase());
                }
            }

            if(daoMaster != null) {
                daoSession = daoMaster.newSession();
            }
        }

        return daoSession;
    }

}
