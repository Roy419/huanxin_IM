package com.goodsure.frameworkdemo.em_message_presenter;

import com.goodsure.frameworkdemo.ui.view.IChartView;
import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by Administrator on 2018/6/1.
 */

public  class ChartPresenter extends MessagePresenter<IChartView> {

    public ChartPresenter(){

    }
      public void message(){
      }

    @Override
    public void messageReceived(List<EMMessage> messages) {
         //这里可以进行处理消息把需要的数据传出去

        ichartVeiw.messageProcessing(messages);
    }

    @Override
    public void cmdMessageReceived(List<EMMessage> messages) {

    }

    @Override
    public void messageRead(List<EMMessage> messages) {

    }

    @Override
    public void messageDelivered(List<EMMessage> message) {

    }

    @Override
    public void messageRecalled(List<EMMessage> messages) {

    }

    @Override
    public void messageChanged(EMMessage message, Object change) {

    }
}

