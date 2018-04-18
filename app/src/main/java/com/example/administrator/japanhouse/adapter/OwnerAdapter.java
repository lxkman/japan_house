package com.example.administrator.japanhouse.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;

/**
 * Created by   admin on 2018/4/16.
 */

public class OwnerAdapter extends RecyclerView.Adapter{

    private Activity activity;

    public OwnerAdapter(Activity activity) {
        this.activity = activity;
    }

    private onClickItemListener listener;

    public void setOnClickItemListener(onClickItemListener listener){
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_owner, null);
        return new OwnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof OwnerViewHolder) {
            OwnerViewHolder holder = (OwnerViewHolder) viewHolder;
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class OwnerViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvMess;
        LinearLayout layout;

        public OwnerViewHolder(View itemView) {
            super(itemView);
            layout= (LinearLayout) itemView.findViewById(R.id.item_owner_lt);
            tvTitle = (TextView) itemView.findViewById(R.id.item_owner_title);
            tvMess = (TextView) itemView.findViewById(R.id.item_owner_mess);
        }
    }

    public interface onClickItemListener{
        void onItemClick();
    }
}
