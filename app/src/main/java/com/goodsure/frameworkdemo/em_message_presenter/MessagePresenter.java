package com.goodsure.frameworkdemo.em_message_presenter;

import com.goodsure.frameworkdemo.callback.IMessage;
import com.goodsure.frameworkdemo.ui.view.BaseIChartView;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by Administrator on 2018/6/1.
 */

public abstract class MessagePresenter<T extends BaseIChartView> implements IMessage {

    protected T ichartVeiw;

    public void bindView(T c){
          ichartVeiw = c;
    }

      public  EMMessageListener callBackMessage(){
          EMMessageListener msgListener = new EMMessageListener() {

              @Override
              public void onMessageReceived(List<EMMessage> messages) {
                  //收到消息
                 messageReceived(messages);
              }

              @Override
              public void onCmdMessageReceived(List<EMMessage> messages) {
                  //收到透传消息
                  cmdMessageReceived(messages);
              }

              @Override
              public void onMessageRead(List<EMMessage> messages) {
                  //收到已读回执
                  messageRead(messages);
              }

              @Override
              public void onMessageDelivered(List<EMMessage> message) {
                  //收到已送达回执
                  messageDelivered(message);
              }
              @Override
              public void onMessageRecalled(List<EMMessage> messages) {
                  //消息被撤回
                  messageRecalled(messages);
              }

              @Override
              public void onMessageChanged(EMMessage message, Object change) {
                  //消息状态变动
                  messageChanged(message,change);
              }
          };
          return msgListener;

      }
}
