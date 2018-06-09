package com.goodsure.frameworkdemo.ui;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goodsure.frameworkdemo.BaseActivity;
import com.goodsure.frameworkdemo.MainActivity;
import com.goodsure.frameworkdemo.MyApplication;
import com.goodsure.frameworkdemo.R;
import com.goodsure.frameworkdemo.common.ThreadCommonUtils;
import com.goodsure.frameworkdemo.common.greendao.DbManager;
import com.goodsure.frameworkdemo.common.wegit.utils.EaseSmileUtils;
import com.goodsure.frameworkdemo.em_message_presenter.ChartPresenter;
import com.goodsure.frameworkdemo.em_message_presenter.ChatPresenter;
import com.goodsure.frameworkdemo.model.ChatItem;
import com.goodsure.frameworkdemo.ui.view.IChartView;
import com.goodsure.frameworkdemo.utils.SPUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.goodsure.frameworkdemo.MyApplication.context;
import static com.goodsure.frameworkdemo.MyApplication.list;

public class ChatA extends BaseActivity<ChatPresenter,ChartPresenter>  implements IChartView {

    private RecyclerView rlv;
    private ChatAdapter chatAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        rlv = findViewById(R.id.rlv);
        Intent intent = getIntent();
        String loginName = intent.getStringExtra("loginName");
        SPUtils.putString(this,"loginName",loginName);
        DbManager.DB_NAME = loginName+".db";
        baseMessagePresenter.bindView(this);
        MyApplication.list.clear();

        List<ChatItem> chatItems = queryAll(this);
        for (int i = 0; i <chatItems.size() ; i++) {
            ChatItem chatItem = chatItems.get(i);
            if(list.size()>0) {

                if (list.contains(chatItem)) {

                    ChatItem chat = list.get(list.size()-1);
                    chat.setContent(chat.getContent());
                    chat.setFromName(chat.getFromName());
                    chat.setToName(chat.getToName());
                    chat.setDate(chat.getDate());
                    chat.setIsWhether(false);
                } else {
                    list.add(chatItem);
                }
            }
            if(list.size()==0){
                list.add(chatItem);
            }

        }


        //添加消息监听器
        EMClient.getInstance().chatManager().addMessageListener(baseMessagePresenter.callBackMessage());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rlv.setLayoutManager(linearLayoutManager);
        chatAdapter = new ChatAdapter(R.layout.item_chat, MyApplication.list);
        chatAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        chatAdapter.setEnableLoadMore(false);
        rlv.setAdapter(chatAdapter);

         chatAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
             @Override
             public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

              Intent intent=     new Intent(ChatA.this, MainActivity.class);
                 intent.putExtra("name", list.get(position).getFromName());
                 startActivity(intent);
             }
         });
    }

    /**
     *
     * 判断mainactivity是否处于栈顶
     * @return  true在栈顶false不在栈顶
     */
    private boolean isMainActivityTop(){
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        String name = manager.getRunningTasks(1).get(0).topActivity.getClassName();
        return name.equals(ChatA.class.getName());
    }




    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<ChatItem> queryAll(Context context) {
        try {
          //  QueryBuilder<ChatItem> builder = DbManager.getDaoSession(context).getChatItemDao().queryBuilder().orderDesc(ChatItemDao.Properties.Date).limit(20);
            QueryBuilder<ChatItem> builder = DbManager.getDaoSession(context).getChatItemDao().queryBuilder();
            return builder.build().list();

        }catch (Exception e){

        }
            return  new ArrayList<>();
    }



    @Override
    public void messageProcessing(List<EMMessage> messages) {
        for (EMMessage em :
                messages) {
            EMTextMessageBody emTextMessageBody = (EMTextMessageBody) em.getBody();
                   ChatItem chatItem = new ChatItem(em.getFrom(), em.getTo(), emTextMessageBody.getMessage(), "", em.getMsgTime());
                   chatItem.setIsWhether(false);
            for ( ChatItem chat:  MyApplication.list
                 ) {
                 if(em.getFrom().equals(chat.getFromName())){
                     chat.setContent(emTextMessageBody.getMessage());
                     chat.setFromName(em.getFrom());
                     chat.setToName(em.getTo());
                     chat.setDate(em.getMsgTime());
                     chat.setIsWhether(false);
                 }
            }
                   DbManager.getDaoSession(context).getChatItemDao().insertOrReplaceInTx(chatItem);

               }

        Log.i("aaabb",Thread.currentThread().getName());
        ThreadCommonUtils.runonuiThread(new Runnable() {
            @Override
            public void run() {
                chatAdapter.notifyDataSetChanged();
            }
        });

    }

    public class ChatAdapter extends BaseQuickAdapter<ChatItem, BaseViewHolder> {
        public ChatAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ChatItem item) {
            TextView view = helper.getView(R.id.tv_toName);
            TextView tv_content = helper.getView(R.id.tv_content);
            TextView tv_count =     helper.getView(R.id.tv_count);
            tv_count.setText(item.getCount()+"");
            view.setText(item.getFromName());
            tv_content.setText(EaseSmileUtils.getSmiledText(ChatA.this,item.getContent()));
        }
    }

}
