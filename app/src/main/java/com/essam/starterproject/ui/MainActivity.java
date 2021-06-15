package com.essam.starterproject.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.essam.starterproject.R;
import com.essam.starterproject.base.BaseActivity;
import com.essam.starterproject.data.APIResponce.Operators;
import com.essam.starterproject.utils.Resource;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    ProgressBar progressBarLoading;
    ProgressBar progressBarScroll;
    MainActivityViewModel viewModel;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeContainer;
    RecyclerViewAdapter adapter = new RecyclerViewAdapter();
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setupRecyclerView();
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        getData();
        showHideLoader(false,progressBarScroll);
        handlePollToRefresh();
    }

    private void handlePollToRefresh() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                viewModel.reset();
                getData();
            }
        });
    }

    private void getData() {
        isLoading = true;
        viewModel.getItems().observe(this, new Observer<Resource<List<Operators>>>() {
            public void onChanged(@Nullable Resource<List<Operators>> itemsList) {
                showHideLoader(false,progressBarLoading);
                showHideLoader(false,progressBarScroll);
                swipeContainer.setRefreshing(false);
                if(itemsList.getStatus() == Resource.Status.SUCCESS){
                    if (itemsList.getData().isEmpty()){
                        Toast.makeText(getApplicationContext(),"No more items to load",Toast.LENGTH_SHORT).show();
                    }
                    else if (itemsList.getData() != null) {
                        adapter.insertData(itemsList.getData());
                    }

                }else{
                    showErrorMessage(itemsList.getErrorMsg());
                }
                isLoading = false;
            }
        });
    }

    void setupRecyclerView(){
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (! recyclerView.canScrollVertically(1) && !isLoading){ //1 for down
                    showHideLoader(true,progressBarScroll);
                    getData();
                }
            }
        });
    }

    void bindViews (){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        progressBarLoading = (ProgressBar) findViewById(R.id.progressBarLoading);
        progressBarScroll = (ProgressBar) findViewById(R.id.progressBarScroll);

    }
}