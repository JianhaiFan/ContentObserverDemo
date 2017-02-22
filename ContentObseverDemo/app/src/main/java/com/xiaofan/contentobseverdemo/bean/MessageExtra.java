package com.xiaofan.contentobseverdemo.bean;

import java.io.Serializable;

/**
 * @author: 范建海
 * @createTime: 2016/10/14 13:49
 * @className:  MessageExtra
 * @description: 友盟推送消息 附加消息实体Bean
 * @changed by:
 */
public class MessageExtra implements Serializable {

	private static final long serialVersionUID = 5719421190289510658L;
	// 消息类型
	public String messageType;
	// 附加内容(这个map中包含了多个字段)
	public String map;


}
