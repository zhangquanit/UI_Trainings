package com.horizontalrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleView = (RecyclerView) findViewById(R.id.recycleview);

        //  创建线性布局管理器（默认是垂直方向）
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //  为RecyclerView指定布局管理对象
        recycleView.setLayoutManager(layoutManager);

        MyAdapter myAdapter = new MyAdapter();
        ArrayList<MyData> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String data = "item->" + i;
            MyData myData = new MyData();
            myData.data = data;
            myData.dataList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                myData.dataList.add(i + "&sub->" + j);
            }
            dataList.add(myData);
        }
        myAdapter.setDataList(dataList);

        recycleView.setAdapter(myAdapter);
    }
}
