package com.example.dzung.gridview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private Gallery gallery;
    private ImageView selectImageView;
    private int[] listImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        listImg = intent.getIntArrayExtra("list");
        gallery = (Gallery)findViewById(R.id.simpleGallery);
        selectImageView = (ImageView)findViewById(R.id.iv_gridview);
        selectImageView.setImageResource(intent.getIntExtra("image",0));
        GalleryApdater galleryApdater = new GalleryApdater(getApplicationContext(), listImg);
        gallery.setAdapter(galleryApdater);
        gallery.setSpacing(10);
        gallery.setSelection(intent.getIntExtra("position", 0));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectImageView.setImageResource(listImg[position]);
            }
        });
    }
}
