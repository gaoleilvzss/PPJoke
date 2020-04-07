package com.vinsuan.libcommon.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vinsuan.libcommon.R;

public class EmptyView extends LinearLayout {
    private final ImageView icon;
    private final TextView title;
    private final Button action;

    public EmptyView(@NonNull Context context) {
        this(context,null);
    }

    public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_empty_view,this,true);

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);

         icon = findViewById(R.id.empty_icon);
         title = findViewById(R.id.empty_text);
         action = findViewById(R.id.empty_action);

    }

    public void setEmptyIcon(@DrawableRes int iconRes){
        icon.setImageResource(iconRes);
    }

    public void setTitle(String text){
        if(!TextUtils.isEmpty(text)){
            title.setText(text);
            title.setVisibility(View.VISIBLE);
        }else{
            title.setVisibility(View.GONE);
        }
    }

    public void setButton(String text,View.OnClickListener listener){
        if(!TextUtils.isEmpty(text)){
            action.setText(text);
            action.setVisibility(View.VISIBLE);
            action.setOnClickListener(listener);
        }else{
            action.setVisibility(View.GONE);
        }
    }
}
