package com.effortapp.corelib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.effortapp.corelib.R;

public class MyBadge extends LinearLayout {
    private TextView numTV;

    public MyBadge(Context context) {
        super(context);
        init(context);
    }

    public MyBadge(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyBadge(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.mybadge, this, true);
        numTV = view.findViewById(R.id.numTV);
    }

    public void setNum(int num){
        if(num>0){
            if(num>100){
                numTV.setText("···");
            }else {
                numTV.setText(String.valueOf(num));
            }
            numTV.setVisibility(VISIBLE);
        }else {
            numTV.setVisibility(GONE);
        }
    }
}
