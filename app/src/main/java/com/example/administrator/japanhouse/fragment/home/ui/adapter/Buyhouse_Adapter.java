package com.example.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class Buyhouse_Adapter extends RecyclerView.Adapter<Buyhouse_Adapter.ViewHolderer> {
    private Context context;
    private List<String>list=new ArrayList<>();
    public Buyhouse_Adapter(Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.buy_adapter, parent, false);
        ViewHolderer holderer = new ViewHolderer(view);
        return holderer;
    }

    @Override
    public void onBindViewHolder(ViewHolderer holder, int position) {
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

        public ViewHolderer(View itemView) {
            super(itemView);
            text_title = (TextView) itemView.findViewById(R.id.text_title);
            text_time = (TextView) itemView.findViewById(R.id.text_time);
            text_neirong = (TextView) itemView.findViewById(R.id.text_neirong);
            person = (TextView) itemView.findViewById(R.id.person);
        }
    }
}
