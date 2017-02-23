package com.xiaofan.contentobseverdemo.bean.table;

import java.io.Serializable;

/**
 * @author: 范建海
 * @createTime: 2017/2/23 14:13
 * @className:  Message
 * @description: 消息实体Bean
 * @changed by:
 */
public class Message implements Serializable {

    private String title;

    private String content;

    public Message() {}

    public Message(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return content;
    }

    public void setText(String text) {
        this.content = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
