package com.example.viewmodelsample.master;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewmodelsample.R;

import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class MasterFragment extends Fragment implements LifecycleOwner, MasterAdapter.OnItemClickListenner {

    private MasterViewModel mViewModel;
    private MasterAdapter mAdapter;

    public MasterFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(MasterViewModel.class);
        mViewModel.getData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                mAdapter.addData(strings);
            }
        });
    }

    private void initViews() {
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerview);
        mAdapter = new MasterAdapter();
        mAdapter.setListenner(this);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), VERTICAL);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_master, container, false);
    }


    @Override
    public void onClick(String result) {
        mViewModel.setSelectedItem(result);
    }
}
