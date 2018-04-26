package com.example.administrator.japanhouse.activity;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.japanhouse.R;

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

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class AgentViewHolder extends RecyclerView.ViewHolder {
        public AgentViewHolder(View itemView) {
            super(itemView);
        }
    }
}
