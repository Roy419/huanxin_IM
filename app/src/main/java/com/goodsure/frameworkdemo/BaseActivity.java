package com.goodsure.frameworkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.goodsure.frameworkdemo.common.CustomProgressDialog;
import com.goodsure.frameworkdemo.em_message_presenter.MessagePresenter;
import com.goodsure.frameworkdemo.ui.view.IChartView;

/**
 * Created by Administrator on 2018/5/31.
 */

public class BaseActivity<T extends BasePreSenter, Y extends MessagePresenter<IChartView>> extends AppCompatActivity {
    protected  T basePreSenter;
    protected   Y baseMessagePresenter;
    protected CustomProgressDialog cpd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basePreSenter = TUtil.getT(this,0);
        baseMessagePresenter = TUtil.getT(this,1);  //Y这个子类不要室abstract 不然 创建不了对象
        cpd = new CustomProgressDialog(this, "加载中...");
        cpd.setCancelable(false);//设置进度条不可以按退回键取消
        cpd.setCanceledOnTouchOutside(false);//设置点击进度对话框外的区域对话框不消失
    }





}
