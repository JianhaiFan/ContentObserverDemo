package com.xiaofan.contentobseverdemo.bean;

import java.io.Serializable;
/**
 * @author: 范建海
 * @createTime: 2016/10/14 11:45
 * @className:  UmengMessageBean
 * @description: 友盟消息实体Bean
 * @changed by:
 */
public class UmengMessageBean implements Serializable {
	
	private static final long serialVersionUID = 3111328894393488316L;

	public String msg_id;
	public String random_min;
	public String display_type;


	// 消息实体
	public MessageBody body;
	// 附加消息
	public MessageExtra extra;

}
