package com.example.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.activity.RentalDetailsActivity;
import com.example.administrator.japanhouse.bean.RentalDetailsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/16.
 */

public class Rent_house_Adapter extends RecyclerView.Adapter<Rent_house_Adapter.Viewholder> {
    Context context;
    List<String>list=new ArrayList<>();

    public Rent_house_Adapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rent_house_adapter, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转
                RentalDetailsActivity.invoke(context,new RentalDetailsBean(context.getString(R.string.rent_details),0,null,null,null,holder.weizhi.getText().toString(),holder.juli.getText().toString(),null,holder.mianji.getText().toString(),null,null,holder.chaoxiang.getText().toString(),null,null,null));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        public ImageView sell_img;
        public TextView sell_state;
        public TextView chaoxiang;
        public TextView weizhi;
        public TextView juli;
        public TextView mianji;
        public View view;

        public Viewholder(View itemView) {
            super(itemView);
            view = itemView;
            sell_img = (ImageView) itemView.findViewById(R.id.sell_img);
            sell_state = (TextView) itemView.findViewById(R.id.sell_state);
            weizhi = (TextView) itemView.findViewById(R.id.weizhi);
            juli = (TextView) itemView.findViewById(R.id.juli);
            mianji = (TextView) itemView.findViewById(R.id.mianji);
            chaoxiang = (TextView) itemView.findViewById(R.id.chaoxiang);
        }
    }
}
