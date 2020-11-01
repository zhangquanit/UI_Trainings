package com.horizontalrecyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author 张全
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<MyData> dataList;

    public void setDataList(List<MyData> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_item1, parent, false);
        return new MyAdapter.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyData myData = dataList.get(position);
        holder.tv_name.setText(myData.data);
        SubAdapter subAdapter = new SubAdapter();
        subAdapter.setDataList(myData.dataList);
        holder.subRecycleView.setAdapter(subAdapter);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public RecyclerView subRecycleView;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.item_name);
            subRecycleView = (RecyclerView) itemView.findViewById(R.id.subListView);
            //  创建线性布局管理器（默认是垂直方向）
            final LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            //  为RecyclerView指定布局管理对象
            subRecycleView.setLayoutManager(layoutManager);
        }
    }
}
