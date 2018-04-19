package com.example.administrator.japanhouse.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.japanhouse.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by   admin on 2018/4/17.
 */

public class RentalDetailsPicAdapter extends BaseAdapter {
    private Activity activity;
    private List<String> list;
    private int mWidth;

    public RentalDetailsPicAdapter(Activity activity, List<String> list) {
        this.activity = activity;
        this.list = list;

        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        float density = dm.density;
        mWidth = (int) (width/density);
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

        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) vh.img.getLayoutParams();
        linearParams.height = (mWidth - 46) / 3 * 22 / 15;
        vh.img.setLayoutParams(linearParams);

        Glide.with(activity)
                .load(list.get(position))
                .into(vh.img);

        return convertView;
    }

    class PicViewHolder {
        ImageView img;
    }
}
