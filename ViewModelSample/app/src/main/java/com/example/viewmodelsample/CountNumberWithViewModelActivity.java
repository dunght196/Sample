package com.example.viewmodelsample;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CountNumberWithViewModelActivity extends AppCompatActivity implements LifecycleOwner, View.OnClickListener {

    private CountNumerViewModel mViewModel;
    private TextView mTextScoreTeamA, mTextScoreTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_number_with_view_model);
        mViewModel = ViewModelProviders.of(this).get(CountNumerViewModel.class);
        registerLiveDataListener();
        initViews();
        setTitle("Count with ViewModel");
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

    private void registerLiveDataListener() {
        mViewModel.getScoreTeamA().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                mTextScoreTeamA.setText(String.valueOf(integer));
            }
        });

        mViewModel.getScoreTeamB().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                mTextScoreTeamB.setText(String.valueOf(integer));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_plus_a_1:
                mViewModel.increaseScoreTeamA(1);
                break;

            case R.id.button_plus_a_2:
                mViewModel.increaseScoreTeamA(2);
                break;

            case R.id.button_plus_a_3:
                mViewModel.increaseScoreTeamA(3);
                break;

            case R.id.button_plus_b_1:
                mViewModel.increaseScoreTeamB(1);
                break;

            case R.id.button_plus_b_2:
                mViewModel.increaseScoreTeamB(2);
                break;

            case R.id.button_plus_b_3:
                mViewModel.increaseScoreTeamB(3);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        Log.d("dz","onDestroy");
        super.onDestroy();
    }
}
