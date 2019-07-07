package com.example.rxandroid;

import android.database.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class OperatorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);


    }

    Observable<List<String>> query(String text) {
        return null;
    }
}
