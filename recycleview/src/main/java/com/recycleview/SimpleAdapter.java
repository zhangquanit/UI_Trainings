package com.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author 张全
 */
public class SimpleAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context mCtx;
    private List<String> mDataList;
    private OnItemClickListener onItemClickListener;

    public SimpleAdapter(Context ctx, List<String> dataList) {
        this.mCtx = ctx;
        this.mDataList = dataList;
    }

    //--------------------------------添加或删除
    public void add(int pos) {
        mDataList.add(pos, "add one");
        notifyItemInserted(pos);//通知item添加  注意别调用   notifyDataSetChanged();
    }

    public void delete(int pos) {
        mDataList.remove(pos);
        notifyItemRemoved(pos);//通知item 移除  注意别调用   notifyDataSetChanged();
    }

    //--------------------------------点击事件回调
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);

        void onItemLongClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    //创建ViewHolder ,RecycleView会复用ViewHolder，类似BaseAdaper的getView
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mCtx).inflate(R.layout.item_textview, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        System.out.println("onCreateViewHolder-------viewType=" + viewType);
        return myViewHolder;
    }

    // 将数据绑定至ViewHolder
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        System.out.println("onBindViewHolder ################# position=" + position);

        holder.mTextView.setText(mDataList.get(position));

        //事件回调
        if (null != onItemClickListener) {
            holder.itemView.setTag(holder);
            holder.itemView.setOnClickListener(onClickLIstener);
            holder.itemView.setOnLongClickListener(onLongClickListener);
        }

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    View.OnClickListener onClickLIstener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            MyViewHolder holder = (MyViewHolder) v.getTag();
            int pos = holder.getLayoutPosition(); //通过notifyItemInserted 添加的item  其他view的位置不会刷新
            onItemClickListener.onItemClick(v, pos);
        }
    };
    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
            MyViewHolder holder = (MyViewHolder) v.getTag();
            int pos = holder.getLayoutPosition(); //通过notifyItemInserted 添加的item  其他view的位置不会刷新
            onItemClickListener.onItemLongClick(v, pos);
            return true;//事件不传给onClick
        }
    };
}

class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView mTextView;

    public MyViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.item_text);
    }
}