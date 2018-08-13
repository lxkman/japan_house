package com.haiwai.administrator.japanhouse.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.haiwai.administrator.japanhouse.R;

import java.util.List;

/**
 * admin  2018/6/7
 */
public class RentDetailsVideoBitmapAdapter extends BaseAdapter {
    private Activity activity;
    private List<String> list;
    private int mWidth;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RentDetailsVideoBitmapAdapter(Activity activity, List<String> list) {
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

        vh.img.setImageBitmap(getLocalVideoBitmap(list.get(position)));

        vh.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onBitmapClickListener();
            }
        });

        return convertView;
    }

    public static Bitmap getLocalVideoBitmap(String localPath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据文件路径获取缩略图
            retriever.setDataSource(localPath);
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    class PicViewHolder {
        ImageView img;
        ImageView start;
    }

    public interface OnItemClickListener{
        void onBitmapClickListener();
    }
}
