package com.xiaofan.contentobseverdemo.constant;


import com.xiaofan.contentobseverdemo.util.ExternalStorageUtil;

import java.io.File;

/**
 * @author: 范建海
 * @createTime: 2017/2/13 17:54
 * @className:  CommonConstant
 * @description: 常量类
 * @changed by:
 */
public class CommonConstant {

    // 项目名称(在SDcard卡根目录的名称)
    public static final String PROJECT_NAME = "content_observer_demo" + File.separator;
    // 应用名称
    public static final String APP_NAME = "ContentObserverDemo" + File.separator;
    // 应用外部存储的根路径
    public static final String APP_LOCAL_PATH_ROOT = ExternalStorageUtil.getExternalStoragePath() + File.separator +  PROJECT_NAME + APP_NAME;
    // 奔溃日志信息路径
    public static final String CRASH_PATH = APP_LOCAL_PATH_ROOT + "crash" + File.separator;
    // 缓存信息路径
    public static final String CACHE_PATH = APP_LOCAL_PATH_ROOT + "cache" + File.separator;
    // 音频路径
    public static final String AUDIO_PATH = CACHE_PATH + "audio" + File.separator;
    // 视频路径
    public static final String VIDEO_PATH = CACHE_PATH + "video" + File.separator;
    // 数据库路径
    public static final String DB_PATH = APP_LOCAL_PATH_ROOT + "database" + File.separator;
    // 城市和疾病库路径
    public static final String CITY_AND_DIS_PATH = DB_PATH + "city_and_disease" + File.separator;
    // 个人信息库路径
    public static final String PERSONAL_INFO_PATH = DB_PATH + "personal_info" + File.separator;
    // 图片路径
    public static final String IMAGE_PATH = CACHE_PATH + "image" + File.separator;
    // ImageLoader缓存图片路径
    public static final String IMAGE_LOADER_CACHE_PATH = IMAGE_PATH + "loaderImage" + File.separator;
    // 压缩图片路径
    public static final String IMAGE_COMPRESS_PATH = IMAGE_PATH + "compress" + File.separator;
    // 群聊天文件路径
    public static final String CHAT_FILE_PATH = CACHE_PATH + "file" + File.separator;
}
