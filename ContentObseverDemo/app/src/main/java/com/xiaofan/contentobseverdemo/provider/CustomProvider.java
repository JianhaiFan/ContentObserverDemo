package com.xiaofan.contentobseverdemo.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.xiaofan.contentobseverdemo.constant.SqlConstant;

/**
 * @author: 范建海
 * @createTime: 2017/2/20 10:41
 * @className:  CustomProvider
 * @description: 自定义内容提供者类
 * @changed by:
 */
public class CustomProvider extends ContentProvider {
    public static final int TYPE_MESSAGE = 1;
    public static final int TYPE_MESSAGE_ITEM = 2;

    public static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(SqlConstant.CUSTOM_AUTHORITY, "Message", TYPE_MESSAGE);
        uriMatcher.addURI(SqlConstant.CUSTOM_AUTHORITY, "Message/#", TYPE_MESSAGE_ITEM);
    }


    @Override
    public boolean onCreate() {
       return false;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
        return 0;
    }
}
