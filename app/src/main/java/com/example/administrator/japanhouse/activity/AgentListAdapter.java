package com.example.administrator.japanhouse.activity;

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
import com.example.administrator.japanhouse.utils.Constant;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;

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
                SharedPreferencesUtils.getInstace(activity).setStringPreference(Constant.CHAT, Constant.CHAT_TALK);
                if (RongIM.getInstance() != null) {
                    Log.e("MainActivity", "创建单聊");
                    RongIM.getInstance().startPrivateChat(activity, "123456", activity.getString(R.string.act_chat_title));
                }
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
        public AgentViewHolder(View itemView) {
            super(itemView);
            wChat = (TextView) itemView.findViewById(R.id.item_agent_list_chat);
            layout = (LinearLayout) itemView.findViewById(R.id.item_agent_list_item);
        }
    }
}
