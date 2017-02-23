package com.xiaofan.contentobseverdemo.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.xiaofan.contentobseverdemo.application.App;
import com.xiaofan.contentobseverdemo.constant.CommonConstant;
import com.xiaofan.contentobseverdemo.constant.SqlConstant;
import com.xiaofan.contentobseverdemo.db.ContentObserverSqliteOpenHelper;
import com.xiaofan.contentobseverdemo.db.DbContext;
import com.xiaofan.contentobseverdemo.util.ExternalStorageUtil;

/**
 * @author: 范建海
 * @createTime: 2017/2/20 10:41
 * @className:  CustomProvider
 * @description: 自定义内容提供者类
 * @changed by:
 */
public class MessageProvider extends ContentProvider {
    // uri匹配器
    public static final UriMatcher uriMatcher;
    // 数据库
    private SQLiteDatabase db;
    // 数据库打开标志
    private boolean isOpen;


    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(SqlConstant.PROVIDER_MSG_AUTHORITY, SqlConstant.TABLE_MESSAGE_NAME, SqlConstant.TYPE_MESSAGE);
        uriMatcher.addURI(SqlConstant.PROVIDER_MSG_AUTHORITY, SqlConstant.TABLE_MESSAGE_NAME + "/#", SqlConstant.TYPE_MESSAGE_ITEM);
    }


    @Override
    public boolean onCreate() {
        // 如果数据库和每个用户不相关，则在这里进行数据库的初始化，否则不需要
        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
        isOpen = openDatabase(SqlConstant.DEFAULT_PARTY_ID);
        // 数据库查询的游标
        Cursor cursor = null;

        if (isOpen) {
            switch(uriMatcher.match(uri)) {
                case SqlConstant.TYPE_MESSAGE:
                case SqlConstant.TYPE_MESSAGE_ITEM:
                    // 在这里我们没有兼容分组查询功能,设置为null
                    cursor = db.query(SqlConstant.TABLE_MESSAGE_NAME,null,selection,selectionArgs,null,null,sortOrder);
                    break;
            }
            isOpen = false;
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        isOpen = openDatabase(SqlConstant.DEFAULT_PARTY_ID);
        if (isOpen) {
            switch(uriMatcher.match(uri)) {
                case SqlConstant.TYPE_MESSAGE:
                case SqlConstant.TYPE_MESSAGE_ITEM:
                    // 操作的行ID
                    long rowId = db.insert(SqlConstant.TABLE_MESSAGE_NAME,null,contentValues);
                    if (rowId != -1) {
                        uri = ContentUris.withAppendedId(uri,rowId);
                        getContext().getContentResolver().notifyChange(uri,null);
                    }

                    break;
            }
            isOpen = false;
        }
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        isOpen = openDatabase(SqlConstant.DEFAULT_PARTY_ID);
        // 受影响的行数
        int affectedRowCount = 0;
        if (isOpen) {
            switch(uriMatcher.match(uri)) {
                case SqlConstant.TYPE_MESSAGE:
                case SqlConstant.TYPE_MESSAGE_ITEM:
                    affectedRowCount = db.delete(SqlConstant.TABLE_MESSAGE_NAME,selection,selectionArgs);
                    if(affectedRowCount > 0) {
                        getContext().getContentResolver().notifyChange(uri,null);
                    }
                    break;
            }

            isOpen = false;
        }
        return affectedRowCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
        isOpen = openDatabase(SqlConstant.DEFAULT_PARTY_ID);
        // 受影响的行数
        int affectedRowCount = 0;
        if (isOpen) {
            switch(uriMatcher.match(uri)) {
                case SqlConstant.TYPE_MESSAGE:
                case SqlConstant.TYPE_MESSAGE_ITEM:
                    affectedRowCount = db.update(SqlConstant.TABLE_MESSAGE_NAME,values,selection,selectionArgs);
                    if(affectedRowCount > 0) {
                        getContext().getContentResolver().notifyChange(uri,null);
                    }
                    break;
            }
            isOpen = false;
        }
        return affectedRowCount;
    }

    /**
     * 打开数据库
     * @param partyId 当前用户的唯一标识
     * @return 成功返回true 失败返回false
     */
    public boolean openDatabase(String partyId) {
        // 判断数据库打开的标志
        boolean isOpen = false;
        if(!TextUtils.isEmpty(partyId)) {
            // 数据库名称
            String dbName = SqlConstant.SQLITE_FILE_NAME + "_" + partyId + SqlConstant.DB_EXTENSION_NAME;
            // 实例为空，或者数据库文件不存在，需要重新创建数据库
            if (db == null || !ExternalStorageUtil.exists(CommonConstant.DB_PATH + dbName) || !db.isOpen()) {
                ContentObserverSqliteOpenHelper helper = new ContentObserverSqliteOpenHelper(new DbContext(App.getInstance()), dbName,null,1);

                if (helper != null) {
                    db = helper.getWritableDatabase();
                    // 打开数据库成功
                    isOpen = (db != null) ? true : false;
                }
            } else {
                // 数据库实例存在
                isOpen = true;
            }
        }

        return isOpen;
    }


}
