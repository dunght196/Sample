package com.example.viewmodelsample;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class CountNumberWithoutViewModelActivity extends AppCompatActivity implements View.OnClickListener {

    private MutableLiveData<Integer> mScoreTeamA = new MutableLiveData<>();
    private MutableLiveData<Integer> mScoreTeamB = new MutableLiveData<>();
    private TextView mTextScoreTeamA, mTextScoreTeamB;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_number_with_view_model);
        registerLiveDataListener();
        initViews();
        setTitle("Count without ViewModel");
    }

    private void registerLiveDataListener() {
        // Init live data
        mScoreTeamA.setValue(0);
        mScoreTeamB.setValue(0);

        mScoreTeamA.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                mTextScoreTeamA.setText(String.valueOf(integer));
            }
        });

        mScoreTeamB.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                mTextScoreTeamB.setText(String.valueOf(integer));
            }
        });
    }

    private void initViews() {
        mTextScoreTeamA = findViewById(R.id.text_score_a);
        mTextScoreTeamB = findViewById(R.id.text_score_b);

        findViewById(R.id.button_plus_a_1).setOnClickListener(this);
        findViewById(R.id.button_plus_a_2).setOnClickListener(this);
        findViewById(R.id.button_plus_a_3).setOnClickListener(this);
        findViewById(R.id.button_plus_b_1).setOnClickListener(this);
        findViewById(R.id.button_plus_b_2).setOnClickListener(this);
        findViewById(R.id.button_plus_b_3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_plus_a_1:
                increaseScoreTeamA(1);
                break;

            case R.id.button_plus_a_2:
                increaseScoreTeamA(2);
                break;

            case R.id.button_plus_a_3:
                increaseScoreTeamA(3);
                break;

            case R.id.button_plus_b_1:
                increaseScoreTeamB(1);
                break;

            case R.id.button_plus_b_2:
                increaseScoreTeamB(2);
                break;

            case R.id.button_plus_b_3:
                increaseScoreTeamB(3);
                break;
        }
    }

    private void increaseScoreTeamA(int score) {
        mScoreTeamA.setValue(mScoreTeamA.getValue() + score);
    }

    private void increaseScoreTeamB(int score) {
        mScoreTeamB.setValue(mScoreTeamB.getValue() + score);
    }
}
