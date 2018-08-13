package com.haiwai.administrator.japanhouse.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.haiwai.administrator.japanhouse.R;

import java.util.List;

/**
 * admin  2018/6/7
 */
public class RentDetailsVideoAdapter extends BaseAdapter{
    private Activity activity;
    private List<String> list;
    private int mWidth;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RentDetailsVideoAdapter(Activity activity, List<String> list) {
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
            vh.start = (ImageView) convertView.findViewById(R.id.item_rental_details_start);

            convertView.setTag(vh);
        } else {
            vh = (PicViewHolder) convertView.getTag();
        }

        vh.start.setVisibility(View.VISIBLE);

        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) vh.img.getLayoutParams();
        linearParams.height = (mWidth - 46) / 3 * 22 / 15;
        vh.img.setLayoutParams(linearParams);
        Glide.with(activity)
                .load(list.get(position))
                .into(vh.img);

        vh.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClickItemListener();
            }
        });

        return convertView;
    }

    class PicViewHolder {
        ImageView img;
        ImageView start;
    }

    public interface OnItemClickListener{
        void onClickItemListener();
    }
}
