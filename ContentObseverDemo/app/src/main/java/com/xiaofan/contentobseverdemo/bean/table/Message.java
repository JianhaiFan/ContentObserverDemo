package com.xiaofan.contentobseverdemo.bean.table;

import java.io.Serializable;

/**
 * Created by fanjianhai on 2017/2/20.
 */
public class Message implements Serializable{

    private String msgId;

    private String msgContent;

    private String msgType;

    public Message() {}

    public Message(String msgId, String msgContent, String msgType) {
        this.msgId = msgId;
        this.msgContent = msgContent;
        this.msgType = msgType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgId='" + msgId + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", msgType='" + msgType + '\'' +
                '}';
    }
}
