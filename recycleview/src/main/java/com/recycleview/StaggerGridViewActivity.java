package com.recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 瀑布流
 */
public class StaggerGridViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        StaggerAdapter simpleAdapter = new StaggerAdapter(this, mDataList);
        mRecyclerView.setAdapter(simpleAdapter);
        // 设置垂直显示
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 'A'; i <= 'Z'; i++) {
            mDataList.add("" + (char) i);
        }
    }
}
