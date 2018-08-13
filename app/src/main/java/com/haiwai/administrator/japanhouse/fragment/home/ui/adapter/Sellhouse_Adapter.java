package com.haiwai.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.fragment.home.ui.activity.WenDa_Detils_Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class Sellhouse_Adapter extends RecyclerView.Adapter<Sellhouse_Adapter.ViewHolderer> {
    private Context context;
    private List<String>list=new ArrayList<>();
    public Sellhouse_Adapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {
        for (int i=0;i<10;i++){
            list.add("");
        }
    }

    @Override
    public ViewHolderer onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sell_adapter, parent, false);
        ViewHolderer holderer = new ViewHolderer(view);
        return holderer;
    }

    @Override
    public void onBindViewHolder(ViewHolderer holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 context.startActivity(new Intent(context, WenDa_Detils_Activity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderer extends RecyclerView.ViewHolder {

        public TextView text_title;
        public TextView text_time;
        public TextView text_neirong;
        public TextView person;
        public View view;

        public ViewHolderer(View itemView) {
            super(itemView);
            view = itemView;
            text_title = (TextView) itemView.findViewById(R.id.text_title);
            text_time = (TextView) itemView.findViewById(R.id.text_time);
            text_neirong = (TextView) itemView.findViewById(R.id.text_neirong);
            person = (TextView) itemView.findViewById(R.id.person);
        }
    }
}
