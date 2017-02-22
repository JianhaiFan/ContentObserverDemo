package com.xiaofan.contentobseverdemo.constant;

import android.net.Uri;

/**
 * @author: 范建海
 * @createTime: 2016/11/18 15:35
 * @className:  SqlConstant
 * @description: 数据库相关的常量类
 * @changed by:
 */
public class SqlConstant {
    // 数据库版本号
    public static final int DATABASE_VERSION = 1;
    // 数据库扩展名
    public static final String DB_EXTENSION_NAME = ".sqlite";
    // 数据库文件名前缀
    public static final String SQLITE_FILE_NAME = "ContentObserver";
    // 默认用户的partyId 唯一标识
    public static final String DEFAULT_PARTY_ID = "1314520";
    // 数据库文件名（默认）
    public static final String SQLITE_FILE_NAME_DEFAULT = SQLITE_FILE_NAME + DB_EXTENSION_NAME;
    public static final Uri CUSTOM_CONTENT_URI = Uri.parse("content://com.xiaofan.contentobseverdemo.provider.customprovider");
    // 自定义内容提供者的标识符
    public static final String CUSTOM_AUTHORITY = "com.xiaofan.contentobseverdemo.provider.customprovider";








}
