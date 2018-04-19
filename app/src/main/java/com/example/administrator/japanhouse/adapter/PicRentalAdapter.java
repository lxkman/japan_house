package com.example.administrator.japanhouse.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.activity.RentalActivity;
import com.example.administrator.japanhouse.view.SquareImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by   admin on 2018/4/18.
 */

public class PicRentalAdapter extends RecyclerView.Adapter {

    private Activity activity;

    private List<String> list;

    public PicRentalAdapter(Activity activity, List<String> list) {
        this.activity = activity;
        this.list = list;
    }

    private onItemClickListener clickListener;

    public void setOnItemClickListener(onItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_pic_rental, null);
        return new PicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof PicViewHolder) {
            PicViewHolder picViewHolder = (PicViewHolder) viewHolder;
            if (list != null && list.size() != 0 && i < list.size()) {
                Glide.with(activity)
                        .load(list.get(i))
                        .into(picViewHolder.ivImg);
                picViewHolder.ivDel.setVisibility(View.VISIBLE);
                picViewHolder.ivImg.setOnClickListener(null);
                picViewHolder.ivDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.remove(i);
                        notifyDataSetChanged();
//                        clickListener.onClickDel(i);
                    }
                });
            } else {
                picViewHolder.ivImg.setImageDrawable(activity.getResources().getDrawable(R.drawable.add_pic));
                picViewHolder.ivDel.setVisibility(View.GONE);

                picViewHolder.ivImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onClickAdd();
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        if (list.size() >= 9) {
            return list.size();
        }
        return list.size() + 1;
    }

    class PicViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImg;
        ImageView ivDel;

        public PicViewHolder(View itemView) {
            super(itemView);
            ivImg = (ImageView) itemView.findViewById(R.id.item_pic_rental_img);
            ivDel = (ImageView) itemView.findViewById(R.id.item_pic_rental_imgDel);

        }
    }

    public interface onItemClickListener {
        void onClickAdd();

        void onClickDel(int position);
    }
}
