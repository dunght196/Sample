package com.example.dzung.gridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by dzung on 3/27/2018.
 */

public class GalleryApdater extends BaseAdapter {

    private Context context;
    private int[] listImg;

    public GalleryApdater(Context context, int[] listImg) {
        this.context = context;
        this.listImg = listImg;
    }

    @Override
    public int getCount() {
        return listImg.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(listImg[position]);
        imageView.setLayoutParams(new Gallery.LayoutParams(200,200));
        return imageView;
    }
}
