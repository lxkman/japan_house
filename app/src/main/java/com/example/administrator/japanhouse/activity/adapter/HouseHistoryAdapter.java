package com.example.administrator.japanhouse.activity.adapter;

import android.content.Context;
import android.media.Image;
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
import com.example.administrator.japanhouse.model.HouseRecordListBean;

import java.util.List;

/**
 * 作者：  admin on 2018/6/1 14:59
 * 邮箱：github.com
 */
public class HouseHistoryAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<HouseRecordListBean.DatasBean> datas;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public HouseHistoryAdapter(Context context, List<HouseRecordListBean.DatasBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history_view, null);
        return new HouseHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HouseHistoryViewHolder) {

            HouseHistoryViewHolder viewHolder = (HouseHistoryViewHolder) holder;

            Glide.with(context)
                    .load(datas.get(position).getImageUrl())
                    .into(viewHolder.icon);
            viewHolder.name.setText(MyApplication.isJapanese() ? datas.get(position).getTitleJpn() : datas.get(position).getTitleCn());
            viewHolder.address.setText(MyApplication.isJapanese() ? datas.get(position).getAddressJpn() : datas.get(position).getAddressCn());
            viewHolder.price.setText(MyApplication.isJapanese() ? datas.get(position).getPriceJpn() + "万" : datas.get(position).getPriceCn() + "万");
            viewHolder.room.setText(MyApplication.isJapanese() ? datas.get(position).getDoorModelJpn() : datas.get(position).getDoorModelCn());
            viewHolder.area.setText(MyApplication.isJapanese() ? datas.get(position).getAreaJpn() + "㎡" : datas.get(position).getAreaCn() + "㎡");

            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemDeleteClickListener(position, datas.get(position));
                }
            });

            viewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClickListener(datas.get(position).getHType(), datas.get(position).getShType(), datas.get(position).getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class HouseHistoryViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;
        TextView address;
        TextView price;
        TextView room;
        TextView area;

        LinearLayout layout;
        TextView delete;

        public HouseHistoryViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.img_house);
            name = (TextView) itemView.findViewById(R.id.tv_house_name);
            address = (TextView) itemView.findViewById(R.id.tv_house_address);
            price = (TextView) itemView.findViewById(R.id.tv_price);
            room = (TextView) itemView.findViewById(R.id.tv_house_room);
            area = (TextView) itemView.findViewById(R.id.tv_house_area);
            layout = (LinearLayout) itemView.findViewById(R.id.content_ll);
            delete = (TextView) itemView.findViewById(R.id.shachu_tv);
        }
    }

    public interface OnItemClickListener{
        void onItemClickListener(String hType, String shType, int itemId);
        void onItemDeleteClickListener(int position, HouseRecordListBean.DatasBean datasBean);
    }
}
