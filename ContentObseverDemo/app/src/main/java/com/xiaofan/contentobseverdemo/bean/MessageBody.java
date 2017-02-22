package com.xiaofan.contentobseverdemo.bean;

import java.io.Serializable;

/**
 * @author: 范建海
 * @createTime: 2016/10/14 11:52
 * @className:  MessageBody
 * @description: 友盟主体消息实体Bean
 * @changed by:
 */
public class MessageBody implements Serializable {

	private static final long serialVersionUID = 5152026516937263047L;
	// 消息标题
	public String title;
	// 自定义消息
	public String custom;
	// 消息内容
	public String text;

	public String ticker;
	public String after_open;
	public String play_vibrate;
	public String play_sound;
	public String play_lights;

}
