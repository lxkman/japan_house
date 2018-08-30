package com.haiwai.administrator.japanhouse.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.model.HouseRecordListBean;
import com.haiwai.administrator.japanhouse.utils.MyUtils;

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
            String area;
            if (MyApplication.isJapanese()) {
                area = datas.get(position).getAddressJpn();
            } else {
                area = datas.get(position).getAddressCn();
            }
            String price=MyApplication.isJapanese() ? datas.get(position).getPriceJpn() : datas.get(position).getPriceCn();
            viewHolder.name.setText(MyApplication.isJapanese() ? datas.get(position).getTitleJpn() : datas.get(position).getTitleCn());
            viewHolder.price.setText(MyApplication.isJapanese() ? datas.get(position).getPriceJpn() : datas.get(position).getPriceCn());
            viewHolder.address.setText(getSubText(area, price));
            viewHolder.room.setText(MyApplication.isJapanese() ? datas.get(position).getDoorModelJpn() : datas.get(position).getDoorModelCn());
            viewHolder.area.setText(MyApplication.isJapanese() ? datas.get(position).getAreaJpn(): datas.get(position).getAreaCn());

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

    public  String getSubText(String area, String price) {
        if (TextUtils.isEmpty(price)) {
            return area;
        }
        if (area.length() >= 14 - price.length()) {
            area = area.substring(0, 14 - price.length()) + "...";
        }
        return area;
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
