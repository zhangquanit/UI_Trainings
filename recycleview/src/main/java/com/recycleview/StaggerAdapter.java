package com.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 瀑布流
 *
 * @author 张全
 */
public class StaggerAdapter extends RecyclerView.Adapter<MyStaggerViewHolder> {
    private Context mCtx;
    private List<String> mDataList;
    private List<Integer> mHeights;

    public StaggerAdapter(Context ctx, List<String> dataList) {
        this.mCtx = ctx;
        this.mDataList = dataList;
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mDataList.size(); i++) {
            mHeights.add((int) (200 + Math.random() * 200));
        }
    }

    @Override
    public MyStaggerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mCtx).inflate(R.layout.stagger_item_textview, parent, false);
        MyStaggerViewHolder myStaggerViewHolder = new MyStaggerViewHolder(itemView);
        return myStaggerViewHolder;
    }

    // 将数据绑定至ViewHolder
    @Override
    public void onBindViewHolder(MyStaggerViewHolder holder, int position) {
        // 设置itemView的随机高度
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.itemView.setLayoutParams(layoutParams);

        holder.mTextView.setText(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}

class MyStaggerViewHolder extends RecyclerView.ViewHolder {
    public TextView mTextView;

    public MyStaggerViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.item_text);
    }
}
