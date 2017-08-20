package com.ricky.newschannel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ricky.newschannel.R;
import com.ricky.newschannel.api.bean.NewsInfo;
import com.ricky.newschannel.module.activity.NewsDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener {

    public OnRecycleViewItemClickListener itemListener;
    private List<NewsInfo.NewsResult.NewsData> mlist;
    private List<String> urlList = new ArrayList<>();
    private Context mContext;

    public RecycleViewAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * 更新数据
     * @param list
     */
    public void updateRecycleViewItem(List<NewsInfo.NewsResult.NewsData> list) {
        if (urlList.size() > 0){
            urlList.clear();
        }
        mlist = list;
        notifyDataSetChanged();
    }

    /**
     * 加载更多item
     * @param list
     */
    public void loadMoreRecycleItem(List<NewsInfo.NewsResult.NewsData> list) {
        for (int i = 0; i < list.size(); i++) {
            mlist.add(list.get(i));
        }
        notifyDataSetChanged();
    }

    public void launchNewsDetail(int position){
        String url = urlList.get(position);
        NewsDetailActivity.newsDetailInstance(mContext,url);
    }


    //创建childView
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.news_list_item, parent, false);
        layout.setOnClickListener(this);
        return new MyViewHolder(layout);
    }

    //将数据绑定到每一个chidview中
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            setOnlyOneImage(mlist.get(position).getThumbnail_pic_s(), ((MyViewHolder) holder).itemImageView);
            ((MyViewHolder) holder).title_text.setText(mlist.get(position).getTitle());
            ((MyViewHolder) holder).author_text.setText(mlist.get(position).getAuthor_name());
            ((MyViewHolder) holder).data_text.setText(mlist.get(position).getDate());
            urlList.add(mlist.get(position).getUrl());
            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        if (mlist != null) {
            return mlist.size();
        } else {
            return 0;
        }
    }

    @Override
    public void onClick(View v) {
        itemListener.onClickRecycleViewItem(v, (int) v.getTag());
    }

    public interface OnRecycleViewItemClickListener {
        void onClickRecycleViewItem(View view, int position);
    }

    public void setOnRecycleViewItemClickListener(OnRecycleViewItemClickListener listener) {
        this.itemListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title_text, author_text, data_text;
        public ImageView itemImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemImageView = (ImageView) itemView.findViewById(R.id.item_image_area);
            title_text = (TextView) itemView.findViewById(R.id.item_title_area);
            author_text = (TextView) itemView.findViewById(R.id.item_author_area);
            data_text = (TextView) itemView.findViewById(R.id.item_data_area);
        }
    }

    /**
     * 仅有一张图的显示
     *
     * @param url
     * @param view
     */

    private void setOnlyOneImage(String url, final ImageView view) {
        Picasso.with(mContext)
                .load(url)
                .noFade()
//                .fit()
                .resize(80, 80)
                .centerCrop()
                .into(view);
    }

}
