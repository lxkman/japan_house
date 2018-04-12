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
 * Created by Mr赵 on 2018/4/11.
 */

public class WenDa_Detil_Adapter extends RecyclerView.Adapter<WenDa_Detil_Adapter.ViewHolder> {
    Context context;
    List<String>list=new ArrayList<>();
    public WenDa_Detil_Adapter(Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.toutiao_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView touxiang;
        public TextView name;
        public TextView time;
        public TextView neirong;
        public TextView zanperson;
        public ImageView zan;

        public ViewHolder(View itemView) {
            super(itemView);
            touxiang = (ImageView) itemView.findViewById(R.id.touxiang);
            name = (TextView) itemView.findViewById(R.id.name);
            time = (TextView) itemView.findViewById(R.id.time);
            neirong = (TextView) itemView.findViewById(R.id.neirong);
            zanperson = (TextView) itemView.findViewById(R.id.zan_person);
            zan = (ImageView) itemView.findViewById(R.id.zan);
        }
    }
}
