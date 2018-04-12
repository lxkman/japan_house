package com.example.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.fragment.home.ui.activity.TouDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class TouAdapter extends RecyclerView.Adapter<TouAdapter.ViewHolder> {
    private Context context;
    List<String>list=new ArrayList<>();
    public TouAdapter(Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.touadapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
     holder.view.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(context, TouDetailActivity.class);
              context.startActivity(intent);
         }
     });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView tou;
        public View view;
        public TextView neirong;
        public TextView time;
        public TextView person;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tou = (ImageView) itemView.findViewById(R.id.imag_tou);
            neirong = (TextView) itemView.findViewById(R.id.tou_neirong);
            time = (TextView) itemView.findViewById(R.id.tou_time);
            person = (TextView) itemView.findViewById(R.id.person);
        }
    }
}
