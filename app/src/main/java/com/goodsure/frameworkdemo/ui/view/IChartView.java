package com.goodsure.frameworkdemo.ui.view;

import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by Administrator on 2018/6/1.
 */

public interface IChartView  extends  BaseIChartView{
   void messageProcessing(List<EMMessage> messages);
}
