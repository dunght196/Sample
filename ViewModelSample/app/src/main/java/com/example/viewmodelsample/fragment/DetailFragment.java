package com.example.viewmodelsample.fragment;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viewmodelsample.R;
import com.example.viewmodelsample.master.MasterViewModel;

public class DetailFragment extends Fragment implements LifecycleOwner {

    private MasterViewModel mViewModel;
    private TextView mUserName;

    public DetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(MasterViewModel.class);
        mViewModel.getSelectedItem().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mUserName.setText(s);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUserName = getView().findViewById(R.id.text_user_name);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
}
