package com.example.dzung.gridview;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by dzung on 10/12/2017.
 */

public class MyImageAdapter extends BaseAdapter {

    private int[] listImg;
    private Context mContext;

    public MyImageAdapter(Context c, int[] listImg) {
        this.mContext = c;
        this.listImg = listImg;
    }

    @Override
    public int getCount() {
        return listImg.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
//        int width = mContext.getApplicationContext().getResources().getDisplayMetrics().widthPixels - 10;
//        int hight = mContext.getApplicationContext().getResources().getDisplayMetrics().widthPixels * view.getHeight()/view.getWidth();

        if (view == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) view;
        }

        imageView.setImageResource(listImg[i]);
        return imageView;
    }
}
