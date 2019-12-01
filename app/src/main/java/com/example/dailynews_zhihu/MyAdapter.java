package com.example.dailynews_zhihu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final static int ITEM_TYPE_HEADER = 0;
    private final static int ITEM_TYPE_TEXT = 1;
    public static String URL;
    MyViewHolder holder = null;
    // 要在Item上显示的数据
    List<ItemBean.StoriesBean> mDataSet;
    Context mContext;

    public MyAdapter(ArrayList data, Context context) {
        this.mDataSet = data;
        this.mContext = context;
    }

    // 用于获取ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headView != null && viewType == TYPE_HEADER) return new MyViewHolder(headView);
        holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.info_item, parent, false));
        return holder;
    }

    // 将数据与ViewHolder绑定
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(holder);
        holder.textView.setText(mDataSet.get(pos).getTitle());
        holder.title.setText(mDataSet.get(pos).getHint());
        Glide.with(mContext).load(mDataSet.get(pos).getImages()).into(holder.img);
        //Stories的点击跳转
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL = mDataSet.get(pos).getUrl();
                Intent intent = new Intent();
                intent.setClass(mContext, Main2Activity.class);
                mContext.startActivity(intent);
            }
        });
    }

    // 获取Item的数量
    @Override
    public int getItemCount() {
        return headView == null ? mDataSet.size() : mDataSet.size() + 1;
    }

    // 以下五个方法是我自定义的，用来对数据进行一系列操作
    public void refreshItems(List<ItemBean.StoriesBean> items) {
        mDataSet.clear();
        mDataSet.addAll(items);
        notifyDataSetChanged();
    }

    public void addItems(List<ItemBean.StoriesBean> items) {
        mDataSet.addAll(items);
    }

    // 获取当前位置item的类型
    public int getItemViewType0(int position) {
        return position == 0 ? ITEM_TYPE_HEADER : ITEM_TYPE_TEXT;
    }

    public void addItem(ItemBean.StoriesBean item) {
        mDataSet.add(item);
    }

    public void deleteItem(int position) {
        mDataSet.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, mDataSet.size() - 1);
    }

    // ViewHolder用于获取Item上的控件
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView, title;
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_title);
            title = (TextView) itemView.findViewById(R.id.data);
            img = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }

    public static final int TYPE_HEADER = 0;//显示headvuiew
    public static final int TYPE_NORMAL = 1;//显示普通的item
    private View headView;//这家伙就是Banner

    public void setHeadView(View headView) {
        this.headView = headView;
        notifyItemInserted(0);
    }

    public View getHeadView() {
        return headView;
    }

    @Override
    public int getItemViewType(int position) {
        if (headView == null)
            return TYPE_NORMAL;
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headView == null ? position : position - 1;
    }
}
