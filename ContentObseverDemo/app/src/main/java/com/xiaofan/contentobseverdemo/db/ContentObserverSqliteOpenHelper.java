package com.xiaofan.contentobseverdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xiaofan.contentobseverdemo.constant.SqlConstant;


/**
 * @author: 范建海
 * @createTime: 2017/2/13 15:59
 * @className:  GreenDaoSqliteOpenHelper
 * @description: 数据库帮助类
 * @changed by:
 */

public class ContentObserverSqliteOpenHelper extends SQLiteOpenHelper {


    public ContentObserverSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SqlConstant.SQL_TABLE_MESSAGE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
