package com.goodsure.frameworkdemo.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.goodsure.frameworkdemo.R;

import static com.goodsure.frameworkdemo.MyApplication.context;


public class CustomProgressDialog extends ProgressDialog
{

	private AnimationDrawable mAnimation;
	  private Context mContext;
	private ProgressBar mImageView;
	private String mLoadingTip;
	private TextView mLoadingTv;
	private int mResid;
    private static CustomProgressDialog customProgressDialog;

	public CustomProgressDialog(Context context, String content)
	{
		 super(context);
		 this.mContext = context;
		 this.mLoadingTip = content;
		setCanceledOnTouchOutside(true);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		initView();
        ;
	}



	//设置加载文字
	public void setCustomPd(String message){
		if(mLoadingTv != null) {
			mLoadingTv.setText(message);
		}
	}



	private void initView()
	{
		Window mWindow = getWindow();
		WindowManager.LayoutParams lp = mWindow.getAttributes();
		lp.dimAmount =0.5f;

		lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
		lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		lp.gravity = Gravity.CENTER;

		View inflate = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null);
		setContentView(inflate);
		mLoadingTv = (TextView) findViewById(R.id.loadingTv);
		mImageView = (ProgressBar) findViewById(R.id.loading_pb);

	}

	public void unContext(){
		mContext = null;
	}



}
