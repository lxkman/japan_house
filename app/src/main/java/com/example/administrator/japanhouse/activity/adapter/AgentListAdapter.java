package com.example.administrator.japanhouse.activity.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.fragment.chat.ManagerActivity;
import com.example.administrator.japanhouse.im.ImManager;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.example.administrator.japanhouse.view.RatingBarView;

import io.rong.imkit.RongIM;

/**
 * Created by   admin on 2018/4/26.
 */

public class AgentListAdapter extends RecyclerView.Adapter {

    private Activity activity;

    public AgentListAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_agent_list, null);
        return new AgentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AgentViewHolder viewHolder = (AgentViewHolder) holder;
        viewHolder.wChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImManager.enterChat(activity, "userid", "name", "pic");
            }
        });

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, ManagerActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class AgentViewHolder extends RecyclerView.ViewHolder {
        TextView wChat;
        LinearLayout layout;
        RatingBarView ratingBarView;
        public AgentViewHolder(View itemView) {
            super(itemView);
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
