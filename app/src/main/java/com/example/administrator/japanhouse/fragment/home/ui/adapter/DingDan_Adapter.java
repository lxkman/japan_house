package com.example.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.administrator.japanhouse.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/17.
 */

public class DingDan_Adapter extends RecyclerView.Adapter<DingDan_Adapter.ViewHolder> {
    private Context context;
   List<String> list=new ArrayList<>();
    public DingDan_Adapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {
        for (int i=0;i<5;i++){
            list.add("");
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dingdan_adapter, parent, false);
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

        public ImageView imag;
        public View view;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imag = (ImageView) itemView.findViewById(R.id.img_dingdan);

        }
    }
}
