package com.example.administrator.japanhouse.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.japanhouse.R;

import java.util.List;

/**
 * Created by   admin on 2018/4/17.
 */

public class RentalDetailsPicAdapter extends BaseAdapter{
    private Activity activity;
    private List<String> list;

    public RentalDetailsPicAdapter(Activity activity, List<String> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PicViewHolder vh = null;
        if (convertView == null) {
            vh = new PicViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_rental_details_pic, null);
            vh.img = (ImageView) convertView.findViewById(R.id.item_rental_details_picImg);
            convertView.setTag(vh);
        } else {
            vh = (PicViewHolder) convertView.getTag();
        }

        Glide.with(activity)
                .load(list.get(position))
                .into(vh.img);

        return convertView;
    }

    class PicViewHolder {
        ImageView img;
    }
}
