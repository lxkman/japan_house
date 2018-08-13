package com.haiwai.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.fragment.comment.ZuHousedetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/16.
 */

public class Myhouse_Adapter extends RecyclerView.Adapter<Myhouse_Adapter.Viewholder> {
     Context context;
     List<String> list=new ArrayList<>();

    public Myhouse_Adapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fangjia_adapter, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
          holder.view.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  context.startActivity(new Intent(context, ZuHousedetailsActivity.class));
              }
          });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public TextView fj_time;
        public ImageView fj_img;
        public TextView fj_title;
        public View view;

        public Viewholder(View itemView) {
            super(itemView);
            view = itemView;
            fj_img = (ImageView) itemView.findViewById(R.id.fj_img);
            fj_title = (TextView) itemView.findViewById(R.id.fj_title);
            fj_time = (TextView) itemView.findViewById(R.id.fj_time);
        }
    }
}
