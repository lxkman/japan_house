package com.example.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.fragment.home.ui.activity.WenDa_Detils_Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class Tiwen_Adapter extends RecyclerView.Adapter<Tiwen_Adapter.ViewHolder> {
    private Context context;
    private List<String> list=new ArrayList<>();
    public Tiwen_Adapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {
        for (int i=0;i<10;i++){
            list.add("");
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tiwen_adapter, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, WenDa_Detils_Activity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text_title;
        public TextView text_time;
        public TextView text_neirong;
        public TextView person;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            text_title = (TextView) itemView.findViewById(R.id.text_title);
            text_time = (TextView) itemView.findViewById(R.id.text_time);
            text_neirong = (TextView) itemView.findViewById(R.id.text_neirong);
            person = (TextView) itemView.findViewById(R.id.person);

        }
    }
}
