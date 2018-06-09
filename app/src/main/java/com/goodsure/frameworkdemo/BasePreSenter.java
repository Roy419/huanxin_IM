package com.goodsure.frameworkdemo;

import android.widget.Toast;

/**
 * Created by Administrator on 2018/5/31.
 */

public class BasePreSenter {
    protected  void initFlag(){
        Toast.makeText(MyApplication.context, "来了", Toast.LENGTH_SHORT).show();
    }

    private void error(){

    }
}
