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
    // 消息表名称
    public static final String TABLE_MESSAGE_NAME = "tb_message";
    // 消息内容提供者的消息标识
    public static final String PROVIDER_MSG_AUTHORITY = "com.xiaofan.contentobseverdemo.provider.messageprovider";
    // 消息内容提供者的标准前缀 + 消息标识
    public static final String PROVIDER_MSG_SCHEME_AUTHORITY = "content://" + PROVIDER_MSG_AUTHORITY;
    // 消息内容提供者的URI
    public static final Uri PROVIDER_MSG_URI = Uri.parse(PROVIDER_MSG_SCHEME_AUTHORITY + "/" + TABLE_MESSAGE_NAME);

    /**
     * Provider uri 响应类型
     */
    // 消息表相应类型
    public static final int TYPE_MESSAGE = 1;
    // 消息表单条记录相应类型
    public static final int TYPE_MESSAGE_ITEM = 2;
    // 创建消息表Sql
    public static final String SQL_TABLE_MESSAGE_CREATE = "create table "+ TABLE_MESSAGE_NAME +" (title text primary key,content text)";

}
