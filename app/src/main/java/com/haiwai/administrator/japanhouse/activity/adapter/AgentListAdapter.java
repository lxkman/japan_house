package com.haiwai.administrator.japanhouse.activity.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.fragment.chat.ManagerActivity;
import com.haiwai.administrator.japanhouse.im.ImManager;
import com.haiwai.administrator.japanhouse.login.LoginActivity;
import com.haiwai.administrator.japanhouse.model.AgentListBean;
import com.haiwai.administrator.japanhouse.view.RatingBarView;

import java.util.List;

/**
 * Created by   admin on 2018/4/26.
 */

public class AgentListAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<AgentListBean.DatasBean> datas;

    public AgentListAdapter(Activity activity, List<AgentListBean.DatasBean> datas) {
        this.activity = activity;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_agent_list, null);
        return new AgentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AgentViewHolder viewHolder = (AgentViewHolder) holder;

        Glide.with(activity)
                .load(datas.get(position).getPic())
                .into(viewHolder.icon);

        viewHolder.name.setText(datas.get(position).getBrokerName());

        viewHolder.main.setText(MyApplication.isJapanese() ? activity.getString(R.string.manager_main) + datas.get(position).getAreaNameJpn() : activity.getString(R.string.manager_main) + datas.get(position).getAreaNameCn());

        viewHolder.ratingBarView.setSelectedCount(Math.round(datas.get(position).getAvgStar()));

        viewHolder.wChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MyApplication.isLogin()) {
                    activity.startActivity(new Intent(activity, LoginActivity.class));
                    return;
                }

                ImManager.enterChat(activity, datas.get(position).getId() + "", datas.get(position).getBrokerName(), datas.get(position).getPic());
            }
        });

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ManagerActivity.class);
                intent.putExtra("ManagerId", datas.get(position).getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class AgentViewHolder extends RecyclerView.ViewHolder {
        TextView wChat;
        LinearLayout layout;
        RatingBarView ratingBarView;

        ImageView icon;
        TextView name;
        TextView main;

        public AgentViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.item_agent_list_icon);
            name = (TextView) itemView.findViewById(R.id.item_agent_list_name);
            main = (TextView) itemView.findViewById(R.id.item_agent_list_main);

            wChat = (TextView) itemView.findViewById(R.id.item_agent_list_chat);
            layout = (LinearLayout) itemView.findViewById(R.id.item_agent_list_item);
            ratingBarView = (RatingBarView) itemView.findViewById(R.id.item_agent_list_ratingbar);

            ratingBarView.setRatingCount(5);//设置RatingBarView总数
            ratingBarView.setSelectedCount(3);//设置RatingBarView选中数
            ratingBarView.setSelectedIconResId(R.drawable.start_check);//设置RatingBarView选中的图片id
            ratingBarView.setNormalIconResId(R.drawable.start_nocheck);//设置RatingBarView正常图片id
            ratingBarView.setClickable(false);//设置RatingBarView是否可点击
            ratingBarView.setChildPadding(1);//设置RatingBarView的子view的padding
            ratingBarView.setChildMargin(2);//设置RatingBarView的子view左右之间的margin
            ratingBarView.setChildDimension(20);//设置RatingBarView的子view的宽高尺寸
        }
    }
}
