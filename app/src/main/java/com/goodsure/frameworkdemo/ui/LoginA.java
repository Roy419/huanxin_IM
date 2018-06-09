package com.goodsure.frameworkdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goodsure.frameworkdemo.BaseActivity;
import com.goodsure.frameworkdemo.R;
import com.goodsure.frameworkdemo.common.ErrorContent;
import com.goodsure.frameworkdemo.common.ThreadCommonUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.goodsure.frameworkdemo.common.greendao.DbManager.setManagerNull;

public class LoginA extends BaseActivity {

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
    @InjectView(R.id.bt)
    Button bt;
    @InjectView(R.id.bt_t)
    Button but;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        bt.setVisibility(View.GONE);
        tvLogin.setText("登录");
        but.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.tv_login,R.id.bt_t})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.bt_t:
                exitLogin();
                break;
            case R.id.tv_login:
                login();
                break;
        }


    }

    private void exitLogin() {
        cpd.show();
        ThreadCommonUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().logout(true);
                ThreadCommonUtils.runonuiThread(new Runnable() {
                    @Override
                    public void run() {
                        cpd.dismiss();
                    }
                });
            }
        });
        setManagerNull();
    }

    private void login() {
        final String name = etPhone.getText().toString().trim();
        final String pwd = etPwd.getText().toString().trim();
        cpd.show();
        new Thread(){
            @Override
            public void run() {
                super.run();
                EMClient.getInstance().login(name,pwd,new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        dismiss();
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        Log.d("main", "登录聊天服务器成功！");
                        Intent intent = new Intent(LoginA.this,ChatA.class);
                         intent.putExtra("loginName",name);
                        startActivity(intent);
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(final int code, String message) {
                        Log.d("main", "登录聊天服务器失败！");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginA.this, ErrorContent.showError(code), Toast.LENGTH_SHORT).show();
                            }
                        });
                        dismiss();
                    }
                });
            }
        }.start();
    }

    public  void dismiss(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cpd.dismiss();
            }
        });
    }
}
