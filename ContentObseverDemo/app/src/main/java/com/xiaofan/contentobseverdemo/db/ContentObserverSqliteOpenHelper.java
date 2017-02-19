package com.xiaofan.contentobseverdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xiaofan.contentobseverdemo.greendao.DaoMaster;
import com.xiaofan.contentobseverdemo.greendao.MigrationHelper;
import com.xiaofan.contentobseverdemo.greendao.UserBeanDao;
import com.xiaofan.contentobseverdemo.util.LogUtil;


/**
 * @author: 范建海
 * @createTime: 2017/2/13 15:59
 * @className:  GreenDaoSqliteOpenHelper
 * @description: 数据库帮助类
 * @changed by:
 */

public class ContentObserverSqliteOpenHelper extends DaoMaster.OpenHelper  {

    public ContentObserverSqliteOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ContentObserverSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            LogUtil.e("数据库升级了...");
            //数据迁移模块
            MigrationHelper.migrate(db,UserBeanDao.class);
        }

    }
}
