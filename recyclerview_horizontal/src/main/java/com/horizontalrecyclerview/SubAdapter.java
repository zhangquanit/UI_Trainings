package com.horizontalrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author 张全
 */

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.ViewHolder> {
    List<String> strList;

    public void setDataList(List<String> strList) {
        this.strList = strList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_item2, parent, false);
        return new SubAdapter.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_data.setText(strList.get(position));
    }

    @Override
    public int getItemCount() {
        return strList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_data;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_data = (TextView) itemView.findViewById(R.id.item_data);
        }
    }
}
