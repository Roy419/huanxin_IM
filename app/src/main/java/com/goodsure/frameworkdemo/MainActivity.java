package com.goodsure.frameworkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cly.greendaotest.gen.ChatItemDao;
import com.goodsure.frameworkdemo.common.greendao.DbManager;
import com.goodsure.frameworkdemo.common.wegit.EaseChatExtendMenu;
import com.goodsure.frameworkdemo.common.wegit.emojicon.EaseChatPrimaryMenu;
import com.goodsure.frameworkdemo.common.wegit.emojicon.EaseChatPrimaryMenuBase;
import com.goodsure.frameworkdemo.common.wegit.emojicon.EaseEmojiconMenu;
import com.goodsure.frameworkdemo.common.wegit.emojicon.EaseEmojiconMenuBase;
import com.goodsure.frameworkdemo.common.wegit.entity.EaseDefaultEmojiconDatas;
import com.goodsure.frameworkdemo.common.wegit.entity.EaseEmojicon;
import com.goodsure.frameworkdemo.common.wegit.entity.EaseEmojiconGroupEntity;
import com.goodsure.frameworkdemo.common.wegit.entity.EmojiconExampleGroupData;
import com.goodsure.frameworkdemo.common.wegit.utils.EaseSmileUtils;
import com.goodsure.frameworkdemo.em_message_presenter.ChartPresenter;
import com.goodsure.frameworkdemo.model.ChatItem;
import com.goodsure.frameworkdemo.ui.view.IChartView;
import com.goodsure.frameworkdemo.utils.SPUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.goodsure.frameworkdemo.MyApplication.context;

public class MainActivity extends BaseActivity<NewAppHomePresenter,ChartPresenter> implements IChartView {
    List<EaseEmojiconGroupEntity> emojiconGroupList;
    private EaseEmojiconMenu easeEmojiconMenu;
    private FrameLayout primaryMenuContainer;
    private FrameLayout emojiconMenuContainer;
    private FrameLayout chatExtendMenuContainer;
    private EaseChatExtendMenu chatExtendMenu;
    private EaseChatPrimaryMenu chatPrimaryMenu;
    private Handler handler = new Handler();
    List<ChatItem> list = new ArrayList<>();
    private RecyclerView rl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ease_widget_chat_input_menu);
        setContentView(R.layout.chat_recyclerview);
        TextView tv_title = findViewById(R.id.tv_title);
        rl = findViewById(R.id.rl);
        FrameLayout viewById = findViewById(R.id.fl);
        View inflate = LayoutInflater.from(this).inflate(R.layout.ease_widget_chat_input_menu, null);
        viewById.addView(inflate);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
         tv_title.setText("与"+name+"聊天");
         //查询数据库
        list.clear();
        List<ChatItem> list2 = DbManager.getDaoMaster(this).newSession().getChatItemDao().queryBuilder().where(ChatItemDao.Properties.FromName.eq(name)).list();
       if(list2 !=null){
           list.addAll(list2);
       }

        primaryMenuContainer =  findViewById(R.id.primary_menu_container);
        emojiconMenuContainer =   findViewById(R.id.emojicon_menu_container);
        chatExtendMenuContainer =   findViewById(R.id.extend_menu_container);
        // extend menu
        chatExtendMenu =  findViewById(R.id.extend_menu);


        basePreSenter.initFlag();
        basePreSenter.getLast();
        if (baseMessagePresenter != null) {
            baseMessagePresenter.bindView(this);
            //添加消息监听器
            EMClient.getInstance().chatManager().addMessageListener(baseMessagePresenter.callBackMessage());
            ;
        }
        emojincon();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rl.setLayoutManager(linearLayoutManager);
        ChatItemAdapter chatItemAdapter= new ChatItemAdapter(R.layout.item_chat_recycler,list);
         rl.setAdapter(chatItemAdapter);
    }



    private void emojincon() {
        // primary menu, use default if no customized one
        chatPrimaryMenu = (EaseChatPrimaryMenu)  LayoutInflater.from(this).inflate(R.layout.ease_layout_chat_primary_menu, null);
        primaryMenuContainer.addView(chatPrimaryMenu);
        easeEmojiconMenu = (EaseEmojiconMenu) LayoutInflater.from(this).inflate(R.layout.ease_layout_emojicon_menu, null);
        emojiconMenuContainer.addView(easeEmojiconMenu);

        if(emojiconGroupList == null){
            emojiconGroupList = new ArrayList<EaseEmojiconGroupEntity>();
            emojiconGroupList.add(new EaseEmojiconGroupEntity(R.mipmap.ee_1,  Arrays.asList(EaseDefaultEmojiconDatas.getData())));

        }
        easeEmojiconMenu.init(emojiconGroupList);
        easeEmojiconMenu.addEmojiconGroup(EmojiconExampleGroupData.getData());
        // emojicon menu
        easeEmojiconMenu.setEmojiconMenuListener(new EaseEmojiconMenuBase.EaseEmojiconMenuListener() {

            @Override
            public void onExpressionClicked(EaseEmojicon emojicon) {
                if(emojicon.getType() != EaseEmojicon.Type.BIG_EXPRESSION){
                    if(emojicon.getEmojiText() != null){
                       // chatPrimaryMenu.onEmojiconInputEvent(EaseSmileUtils.getSmiledText(context,emojicon.getEmojiText()));
                        Toast.makeText(MainActivity.this, "cc", Toast.LENGTH_SHORT).show();
                        chatPrimaryMenu.onEmojiconInputEvent(EaseSmileUtils.getSmiledText(context,emojicon.getEmojiText()));
                    }
                }else{
                    Toast.makeText(MainActivity.this, "bb", Toast.LENGTH_SHORT).show();
                    /*if(listener != null){
                        listener.onBigExpressionClicked(emojicon);
                    }*/
                }
            }

            @Override
            public void onDeleteImageClicked() {
                Toast.makeText(MainActivity.this, "点了删除", Toast.LENGTH_SHORT).show();
                chatPrimaryMenu.onEmojiconDeleteEvent();
            }
        });
        processChatMenu();
    }


    protected void processChatMenu() {
        // send message button
        chatPrimaryMenu.setChatPrimaryMenuListener(new EaseChatPrimaryMenuBase.EaseChatPrimaryMenuListener() {

            @Override
            public void onSendBtnClicked(String content) {
              //发送内容
                Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
                ;

            }

            @Override
            public void onToggleVoiceBtnClicked() {
                hideExtendMenuContainer();
            }

            @Override
            public void onToggleExtendClicked() {
                toggleMore();
            }

            @Override
            public void onToggleEmojiconClicked() {
                toggleEmojicon();
            }

            @Override
            public void onEditTextClicked() {
                hideExtendMenuContainer();
            }


            @Override
            public boolean onPressToSpeakBtnTouch(View v, MotionEvent event) {

                return false;
            }
        });



    }

    /**
     * show or hide extend menu
     *
     */
    protected void toggleMore() {
        if (chatExtendMenuContainer.getVisibility() == View.GONE) {
            hideKeyboard();
            handler.postDelayed(new Runnable() {
                public void run() {
                    chatExtendMenuContainer.setVisibility(View.VISIBLE);
                    chatExtendMenu.setVisibility(View.VISIBLE);
                    easeEmojiconMenu.setVisibility(View.GONE);
                }
            }, 50);
        } else {
            if (easeEmojiconMenu.getVisibility() == View.VISIBLE) {
                easeEmojiconMenu.setVisibility(View.GONE);
                chatExtendMenu.setVisibility(View.VISIBLE);
            } else {
                chatExtendMenuContainer.setVisibility(View.GONE);
            }
        }
    }

    /**
     * show or hide emojicon
     */
    protected void toggleEmojicon() {
        if (chatExtendMenuContainer.getVisibility() == View.GONE) {
            hideKeyboard();
            handler.postDelayed(new Runnable() {
                public void run() {
                    chatExtendMenuContainer.setVisibility(View.VISIBLE);
                    chatExtendMenu.setVisibility(View.GONE);
                    easeEmojiconMenu.setVisibility(View.VISIBLE);
                }
            }, 50);
        } else {
            if (easeEmojiconMenu.getVisibility() == View.VISIBLE) {
                chatExtendMenuContainer.setVisibility(View.GONE);
                easeEmojiconMenu.setVisibility(View.GONE);
            } else {
                chatExtendMenu.setVisibility(View.GONE);
                easeEmojiconMenu.setVisibility(View.VISIBLE);
            }

        }
    }

    /**
     * hide keyboard
     */
    private void hideKeyboard() {
        chatPrimaryMenu.hideKeyboard();
    }


    /**
     * hide extend menu
     */
    public void hideExtendMenuContainer() {
        chatExtendMenu.setVisibility(View.GONE);
        easeEmojiconMenu.setVisibility(View.GONE);
        chatExtendMenuContainer.setVisibility(View.GONE);
        chatPrimaryMenu.onExtendMenuContainerHide();
    }
    @Override
    public void messageProcessing(List<EMMessage> messages) {
        //收到消息
        for (int i = 0; i < messages.size(); i++) {
               EMMessage emMessage = messages.get(i);
                EMTextMessageBody body = (EMTextMessageBody) emMessage.getBody();
                body.getMessage();

            emMessage.getChatType();
        }
    }


    public class ChatItemAdapter extends BaseQuickAdapter<ChatItem,BaseViewHolder>{

        public ChatItemAdapter(@LayoutRes int layoutResId, @Nullable List<ChatItem> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ChatItem item) {
            TextView tv_to_content = helper.getView(R.id.tv_to_content);
            TextView tv_from_content = helper.getView(R.id.tv_from_content);
            LinearLayout ll_bottom_left_to = helper.getView(R.id.ll_bottom_left_to);
            LinearLayout ll_top_right_from = helper.getView(R.id.ll_top_right_from);
            String loginName = SPUtils.getString(MainActivity.this, "loginName");
            if(item.getFromName().equals(loginName)){
                ll_bottom_left_to.setVisibility(View.GONE);
                ll_top_right_from.setVisibility(View.VISIBLE);
                tv_from_content.setText(EaseSmileUtils.getSmiledText(MainActivity.this,item.getContent()));
            }else{
                ll_bottom_left_to.setVisibility(View.VISIBLE);
                ll_top_right_from.setVisibility(View.GONE);
                tv_to_content.setText(EaseSmileUtils.getSmiledText(MainActivity.this,item.getContent()));
            }

        }
    }
}
