package com.goodsure.frameworkdemo.callback;

import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by Administrator on 2018/6/1.
 */

public interface IMessage {
    void messageReceived(List<EMMessage> messages); //收到消息

    void cmdMessageReceived(List<EMMessage> messages) ;//收到透传消息

    void messageRead(List<EMMessage> messages) ; //收到已读回执

    void messageDelivered(List<EMMessage> message)  ; //收到已送达回执

    void messageRecalled(List<EMMessage> messages);    //消息被撤回

    void messageChanged(EMMessage message, Object change) ; //消息状态变动

}
