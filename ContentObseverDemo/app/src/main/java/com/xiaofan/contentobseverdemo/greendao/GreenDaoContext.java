package com.xiaofan.contentobseverdemo.greendao;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import com.xiaofan.contentobseverdemo.constant.CommonConstant;
import com.xiaofan.contentobseverdemo.util.ExternalStorageUtil;

import java.io.File;
import java.io.IOException;

/**
 * @author: 范建海
 * @createTime: 2017/2/13 17:39
 * @className:  GreenDaoContext
 * @description: GreenDao数据库帮助类
 * @changed by:
 */
public class GreenDaoContext extends ContextWrapper {

    public GreenDaoContext(Context ctx) {
        super(ctx);
    }

    /**
     * 获取数据库文
     * @param dbName
     * @return
     */
    @Override
    public File getDatabasePath(String dbName) {
        // 全路径数据库文件
        File dbFile = null;

        if (ExternalStorageUtil.isExternalStorageWritable()) {
            // 数据库所在路径
            File directory = new File(CommonConstant.DB_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            dbFile = new File(CommonConstant.DB_PATH + dbName);

            if(!dbFile.exists()) {

                try {
                    dbFile.createNewFile();
                } catch (IOException e) {
                    dbFile = null;
                }
            }
        }

        return dbFile;
    }

    /**
     * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
     *
     * @param name
     * @param mode
     * @param factory
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
                                               SQLiteDatabase.CursorFactory factory) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
    }



    /**
     * Android 4.0会调用此方法获取数据库。
     *
     * @param name
     * @param mode
     * @param factory
     * @param errorHandler
     * @see ContextWrapper#openOrCreateDatabase(String, int,
     * SQLiteDatabase.CursorFactory,
     * DatabaseErrorHandler)
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
    }


}
