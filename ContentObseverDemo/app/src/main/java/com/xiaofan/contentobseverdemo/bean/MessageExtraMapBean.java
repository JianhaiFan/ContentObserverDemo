package com.xiaofan.contentobseverdemo.bean;

import java.io.Serializable;

/**
 * @author: 范建海
 * @createTime: 2016/12/13 11:49
 * @className:  ExtraMessageMapBean
 * @description: 友盟消息推送extra里面对应的map实体Bean
 * @changed by:
 */
public class MessageExtraMapBean implements Serializable {

    // 1.审核文章时候的文章ID
    // 2.审核编委/审核编辑时对应产生消息的用户ID
    public String messageId;

    public String newsId;
}
