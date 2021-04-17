package com.effortapp.business.adapter.test;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.effortapp.business.R;
import com.effortapp.corelib.app.BaseApplication;
import com.effortapp.corelib.utils.DensityUtils;
import com.noober.background.drawable.DrawableCreator;

import java.util.List;

public class HomeRVadapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private int[] colorArrays={R.color.color_green1, R.color.color_green1, R.color.color_red, R.color.grey,
            R.color.color_head_blue,R.color.color_orange,R.color.color_red,R.color.colorAccent};

    private int[] imageColors;

    public HomeRVadapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        if(data!=null){
            imageColors=new int[data.size()];
            for(int i=0;i<imageColors.length;i++){
                int index= (int) (Math.random()*colorArrays.length);
                imageColors[i]=colorArrays[index];//生成随机颜色
            }
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.nameTV, item);
        View photoView = helper.getView(R.id.photo);
        if(photoView!=null && imageColors!=null){
            Drawable drawable = new DrawableCreator.Builder()
                    .setSolidColor(Color.parseColor(BaseApplication.getContext().getResources().getString(imageColors[helper.getAdapterPosition()])))
                    .setCornersRadius(DensityUtils.dip2px(8))
                    .build();
            photoView.setBackground(drawable);
        }
    }
}
