package com.example.administrator.japanhouse.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.model.LinkmanBean;
import com.example.administrator.japanhouse.view.CircleImageView;

import java.util.List;

/**
 * admin  2018/6/13
 */
public class LinkmanViewAdapter extends RecyclerView.Adapter {

    private Context context;

    private List<LinkmanBean.DatasBean.ListBean> datas;

    public LinkmanViewAdapter(Context context, List<LinkmanBean.DatasBean.ListBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lianxiren, null);
        return new LinkmanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (datas.size() > 0) {
            if (holder instanceof LinkmanViewHolder) {
                LinkmanViewHolder viewHolder = (LinkmanViewHolder) holder;
                LinkmanBean.DatasBean.ListBean bean = (LinkmanBean.DatasBean.ListBean) datas.get(position);
                viewHolder.nickName.setText(bean.getBrokerName());
                Glide.with(context)
                        .load(bean.getPic())
                        .into(viewHolder.icon);

            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class LinkmanViewHolder extends RecyclerView.ViewHolder {
        TextView nickName;
        CircleImageView icon;

        public LinkmanViewHolder(View itemView) {
            super(itemView);
            nickName = (TextView) itemView.findViewById(R.id.item_linkman_nickName);
            icon = (CircleImageView) itemView.findViewById(R.id.item_linkman_icon);
        }
    }
}
