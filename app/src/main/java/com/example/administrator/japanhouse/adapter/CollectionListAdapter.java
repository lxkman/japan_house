package com.example.administrator.japanhouse.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.more.CollectionListBean;

import java.util.List;

/**
 * Created by   admin on 2018/5/31.
 */

public class CollectionListAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<CollectionListBean.DatasBean> datas;

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public CollectionListAdapter(Context context, List<CollectionListBean.DatasBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_zuijin, null);
        return new CollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CollectionViewHolder) {
            CollectionViewHolder viewHolder = (CollectionViewHolder) holder;

            Glide.with(context)
                    .load(datas.get(position))
                    .into(viewHolder.img_house);

            viewHolder.tv_house_name.setText(MyApplication.isJapanese() ? datas.get(position).getTitleJpn() : datas.get(position).getTitleCn());
            viewHolder.tv_house_address.setText(MyApplication.isJapanese() ? datas.get(position).getAddressJpn() : datas.get(position).getAddressCn());
            viewHolder.tv_price.setText(MyApplication.isJapanese() ? datas.get(position).getPriceJpn() + "万" : datas.get(position).getPriceCn() + "万");
            viewHolder.tv_house_room.setText(MyApplication.isJapanese() ? datas.get(position).getDoorModelJpn() : datas.get(position).getDoorModelCn());
            viewHolder.tv_house_area.setText(MyApplication.isJapanese() ? datas.get(position).getAreaJpn() : datas.get(position).getAreaCn());
            viewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClickListener(datas.get(position).getHType(), datas.get(position).getShType(), datas.get(position).getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class CollectionViewHolder extends RecyclerView.ViewHolder {
        ImageView img_house;
        TextView tv_house_name;
        TextView tv_house_address;
        TextView tv_price;
        TextView tv_house_room;
        TextView tv_house_area;
        LinearLayout layout;
        public CollectionViewHolder(View itemView) {
            super(itemView);
            img_house = (ImageView) itemView.findViewById(R.id.img_house);
            tv_house_name = (TextView) itemView.findViewById(R.id.tv_house_name);
            tv_house_address = (TextView) itemView.findViewById(R.id.tv_house_address);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_house_room = (TextView) itemView.findViewById(R.id.tv_house_room);
            tv_house_area = (TextView) itemView.findViewById(R.id.tv_house_area);
            layout = (LinearLayout) itemView.findViewById(R.id.item_zuijin_layout);
        }
    }

    public interface OnItemClickListener{
        void itemClickListener(String hType, String ShType, int houseId);
    }
}
