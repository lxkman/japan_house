package com.example.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.activity.RentalDetailsActivity;
import com.example.administrator.japanhouse.bean.RentalDetailsBean;
import com.example.administrator.japanhouse.model.SellHouseBean;
import com.example.administrator.japanhouse.view.EasySwipeMenuLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/16.
 */

public class Rent_house_Adapter extends RecyclerView.Adapter<Rent_house_Adapter.Viewholder> {
    Context context;
    List<SellHouseBean.DatasBean> list;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Rent_house_Adapter(Context context, List<SellHouseBean.DatasBean> list) {
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
    public void onBindViewHolder(final Viewholder holder, final int position) {

        if (MyApplication.isJapanese()) {
            holder.stateZh.setVisibility(View.GONE);
            holder.stateJa.setVisibility(View.VISIBLE);
            //0审核中 1已拒绝 2已通过
            if (TextUtils.equals(list.get(position).getAuditState(), "0")) {
                holder.stateJa.setText(context.getString(R.string.activity_rental_details_audit));
            } else if (TextUtils.equals(list.get(position).getAuditState(), "1")) {
                holder.stateJa.setText(context.getString(R.string.activity_rental_details_refused));
            } else if (TextUtils.equals(list.get(position).getAuditState(), "2")) {

            }
        } else {
            holder.stateJa.setVisibility(View.GONE);
            holder.stateZh.setVisibility(View.VISIBLE);
            if (TextUtils.equals(list.get(position).getAuditState(), "0")) {
                holder.stateZh.setText(context.getString(R.string.activity_rental_details_audit));
                holder.stateZh.setTextColor(Color.GREEN);
            } else if (TextUtils.equals(list.get(position).getAuditState(), "1")) {
                holder.stateZh.setText(context.getString(R.string.activity_rental_details_refused));
                holder.stateZh.setTextColor(Color.RED);
            } else if (TextUtils.equals(list.get(position).getAuditState(), "2")) {

            }
        }

        holder.weizhi.setText(list.get(position).getHousingLocation());
        holder.juli.setText(list.get(position).getStationDistance() + "m");
        holder.mianji.setText(list.get(position).getArea() + "㎡");
        holder.chaoxiang.setText(list.get(position).getToward());

        if (!TextUtils.isEmpty(list.get(position).getVideoUrl())) {
            Glide.with(context)
                    .load(list.get(position).getVideoImageUrl())
                    .into(holder.sell_img);
            holder.start.setVisibility(View.VISIBLE);
        } else {
            Glide.with(context)
                    .load(list.get(position).getImgs())
                    .into(holder.sell_img);
            holder.start.setVisibility(View.GONE);
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转
                onItemClickListener.onClickListener(position, new RentalDetailsBean(
                        context.getString(R.string.sell_details),
                        list.get(position).getAuditState(),
                        list.get(position).getOperationText(),
                        list.get(position).getUserName(),
                        list.get(position).getUserContact(),
                        list.get(position).getHousingLocation(),
                        list.get(position).getStationDistance() + "",
                        list.get(position).getFloor() + "",
                        list.get(position).getArea() + "",
                        list.get(position).getPattern(),
                        list.get(position).getBathroomTogether() + "",
                        list.get(position).getToward(),
                        list.get(position).getSurroundingFacilities(),
                        getList(list.get(position).getImgs()),
                        list.get(position).getVideoUrl(),
                        list.get(position).getVideoImageUrl()));
            }
        });
    }

    private List<String> getList(String pic){
        String d[] = pic.split(",");
        List<String> picList = new ArrayList();

        for (int i = 0; i < d.length; i++) {
            picList.add(d[i]);
        }
        return picList;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public ImageView sell_img;
        public TextView chaoxiang;
        public TextView weizhi;
        public TextView juli;
        public TextView mianji;
        public RelativeLayout view;
        TextView stateZh;
        TextView stateJa;
        ImageView start;

        public Viewholder(View itemView) {
            super(itemView);
            view = (RelativeLayout) itemView.findViewById(R.id.content_ll);
            sell_img = (ImageView) itemView.findViewById(R.id.rent_house_icon);
            weizhi = (TextView) itemView.findViewById(R.id.weizhi);
            juli = (TextView) itemView.findViewById(R.id.juli);
            mianji = (TextView) itemView.findViewById(R.id.mianji);
            chaoxiang = (TextView) itemView.findViewById(R.id.chaoxiang);
            stateZh = (TextView) itemView.findViewById(R.id.sell_state_zh);
            stateJa = (TextView) itemView.findViewById(R.id.sell_state_ja);
            start = (ImageView) itemView.findViewById(R.id.rent_house_start);
        }
    }

    public interface OnItemClickListener {
        void onClickListener(int position, RentalDetailsBean bean);
    }
}
