package com.haiwai.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.haiwai.administrator.japanhouse.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/17.
 */

public class DingDan_Adapter extends RecyclerView.Adapter<DingDan_Adapter.ViewHolder> {
    private Context context;
    List<String> list = new ArrayList<>();

    public DingDan_Adapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dingdan_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position))
                .into(holder.imag);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imag;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imag = (ImageView) itemView.findViewById(R.id.img_dingdan);

        }
    }
}
