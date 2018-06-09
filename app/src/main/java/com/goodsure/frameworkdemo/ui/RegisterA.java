package com.goodsure.frameworkdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goodsure.frameworkdemo.BaseActivity;
import com.goodsure.frameworkdemo.MyApplication;
import com.goodsure.frameworkdemo.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.goodsure.frameworkdemo.common.ErrorContent.showError;

public class RegisterA extends BaseActivity {

    @InjectView(R.id.iv_logo)
    ImageView ivLogo;
    @InjectView(R.id.et_phone)
    EditText etPhone;
    @InjectView(R.id.et_pwd)
    EditText etPwd;
    @InjectView(R.id.tv_login)
    Button tvLogin;
    @InjectView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @InjectView(R.id.activity_login)
    RelativeLayout activityLogin;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.tv_login)
    public void onViewClicked() {
        final String name = etPhone.getText().toString().trim();
        final String pwd = etPwd.getText().toString().trim();
        //注册失败会抛出HyphenateException
            cpd.show();
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                       EMClient.getInstance().createAccount(name, pwd);//同步方法
                    } catch (final HyphenateException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              Toast.makeText(MyApplication.context, showError( e.getErrorCode()), Toast.LENGTH_SHORT).show();  ;
                          }
                      });
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cpd.dismiss();
                        }
                    });
                }
            }.start();

    }

    public void  login(View view){
         startActivity(new Intent(this,LoginA.class));
    }

}
