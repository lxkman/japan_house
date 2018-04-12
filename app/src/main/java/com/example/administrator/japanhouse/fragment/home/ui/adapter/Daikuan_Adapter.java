package com.example.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.fragment.home.ui.activity.DaikuanDetilsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/12.
 */

public class Daikuan_Adapter extends RecyclerView.Adapter<Daikuan_Adapter.ViewHolder> {
    Context context;
    List<String>list=new ArrayList<>();

    public Daikuan_Adapter(Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.daikuanadapter, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      holder.view.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(context, DaikuanDetilsActivity.class);
              context.startActivity(intent);
          }
      });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView zhouqi;
        public TextView fanwei;
        public TextView yuexi;
        public TextView name;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = (TextView) itemView.findViewById(R.id.dai_name);
            yuexi = (TextView) itemView.findViewById(R.id.yuexi);
            fanwei = (TextView) itemView.findViewById(R.id.fanwei);
            zhouqi = (TextView) itemView.findViewById(R.id.zhouqi);
        }
    }
}
