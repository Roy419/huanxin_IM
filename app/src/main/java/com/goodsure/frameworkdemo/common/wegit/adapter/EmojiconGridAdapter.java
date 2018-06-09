package com.goodsure.frameworkdemo.common.wegit.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.goodsure.frameworkdemo.R;
import com.goodsure.frameworkdemo.common.wegit.entity.EaseEmojicon;
import com.goodsure.frameworkdemo.common.wegit.entity.EaseEmojicon.Type;
import com.goodsure.frameworkdemo.common.wegit.utils.EaseSmileUtils;

import java.util.List;

public class EmojiconGridAdapter extends ArrayAdapter<EaseEmojicon> {

    private  Type emojiconType;


    public EmojiconGridAdapter(Context context, int textViewResourceId, List<EaseEmojicon> objects, EaseEmojicon.Type emojiconType) {
        super(context, textViewResourceId, objects);
        this.emojiconType = emojiconType;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            if(emojiconType == Type.BIG_EXPRESSION){
                convertView = View.inflate(getContext(), R.layout.ease_row_big_expression, null);
            }else{
                convertView = View.inflate(getContext(), R.layout.ease_row_expression, null);
            }
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_expression);
        TextView textView = (TextView) convertView.findViewById(R.id.tv_name);
        EaseEmojicon emojicon = getItem(position);
        if(textView != null && emojicon.getName() != null){
            textView.setText(emojicon.getName());
        }

        if(EaseSmileUtils.DELETE_KEY.equals(emojicon.getEmojiText())){
            imageView.setImageResource(R.mipmap.ease_delete_expression);
        }else{
            if(emojicon.getIcon() != 0){
                imageView.setImageResource(emojicon.getIcon());
            }else if(emojicon.getIconPath() != null){
                Glide.with(getContext()).load(emojicon.getIconPath()).placeholder(R.mipmap.ease_default_expression).into(imageView);
            }
        }
        
        
        return convertView;
    }
    
}
