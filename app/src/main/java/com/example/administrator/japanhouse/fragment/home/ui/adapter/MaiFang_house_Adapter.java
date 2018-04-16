package com.example.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/16.
 */

public class MaiFang_house_Adapter extends RecyclerView.Adapter<MaiFang_house_Adapter.Viewholder> {
    Context context;
    List<String>list=new ArrayList<>();

    public MaiFang_house_Adapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sell_house_adapter, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private final ImageView sell_img;
        private final TextView sell_state;

        public Viewholder(View itemView) {
            super(itemView);
            sell_img = (ImageView) itemView.findViewById(R.id.sell_img);
            sell_state = (TextView) itemView.findViewById(R.id.sell_state);

        }
    }
}
