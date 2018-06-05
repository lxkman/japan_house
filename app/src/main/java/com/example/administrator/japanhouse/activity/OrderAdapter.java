package com.example.administrator.japanhouse.activity;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.model.OrderBean;

import java.util.List;

/**
 * 作者：  admin on 2018/6/5 17:41
 * 邮箱：github.com
 */
public class OrderAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<OrderBean.DatasBean> datas;

    public OrderAdapter(Activity activity, List<OrderBean.DatasBean> datas) {
        this.activity = activity;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_history_view, null);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OrderViewHolder) {
            OrderViewHolder viewHolder = (OrderViewHolder) holder;

            viewHolder.name.setText(MyApplication.isJapanese() ? datas.get(position).getTitleJpn() : datas.get(position).getTitleCn());
            viewHolder.address.setText(MyApplication.isJapanese() ? datas.get(position).getAddressJpn() : datas.get(position).getAddressCn());
            viewHolder.price.setText(MyApplication.isJapanese() ? datas.get(position).getPriceJpn() : datas.get(position).getPriceCn());
            viewHolder.room.setText(MyApplication.isJapanese() ? datas.get(position).getDoorTypeJpn() : datas.get(position).getDoorTypeCn());
            viewHolder.area.setText(MyApplication.isJapanese() ? datas.get(position).getAreaJpn() : datas.get(position).getAreaCn());
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;
        TextView address;
        TextView price;
        TextView room;
        TextView area;

        LinearLayout layout;
        TextView delete;

        public OrderViewHolder(View itemView) {
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
}
