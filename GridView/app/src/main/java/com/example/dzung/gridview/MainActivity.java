package com.example.dzung.gridview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.dzung.gridview.R.id.gridview;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private int[] listImg;
    private MyImageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listImg = new int[]{R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_4, R.drawable.sample_5};

        gridView = (GridView) findViewById(gridview);
        adapter = new MyImageAdapter(this, listImg);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("image", listImg[i]);
                intent.putExtra("position", i);
                intent.putExtra("list", listImg);
                startActivity(intent);
            }
        });


    }

}
