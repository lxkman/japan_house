package com.example.administrator.japanhouse.activity;

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
import com.example.administrator.japanhouse.model.HouseRecordListBean;

import java.util.List;

/**
 * 作者：  admin on 2018/6/4 09:48
 * 邮箱：github.com
 */
public class MineRecordAdapter extends RecyclerView.Adapter {
    private Context context;

    private List<HouseRecordListBean.DatasBean> datas;

    public MineRecordAdapter(Context context, List<HouseRecordListBean.DatasBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_myfoot, null);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecordViewHolder) {
            RecordViewHolder viewHolder = (RecordViewHolder) holder;

            Glide.with(context)
                    .load(datas.get(position).getImageUrl())
                    .into(viewHolder.icon);
            viewHolder.title.setText(MyApplication.isJapanese() ? datas.get(position).getTitleJpn() : datas.get(position).getTitleCn());
            viewHolder.address.setText(MyApplication.isJapanese() ? datas.get(position).getAddressJpn() : datas.get(position).getAddressCn());
            viewHolder.price.setText(MyApplication.isJapanese() ? datas.get(position).getPriceJpn() + "/㎡" : datas.get(position).getPriceCn() + "/㎡");

            // TODO: 2018/6/4 缺时间的显示
//            viewHolder.time.setText(MyApplication.isJapanese() ? datas.get(position).get);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class RecordViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;

        ImageView icon;
        TextView title;
        TextView address;
        TextView price;
        TextView time;

        public RecordViewHolder(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.frag_mineList_layout);
            icon = (ImageView) itemView.findViewById(R.id.frag_mineList_icon);
            title = (TextView) itemView.findViewById(R.id.frag_mineList_title);
            address = (TextView) itemView.findViewById(R.id.frag_mineList_address);
            price = (TextView) itemView.findViewById(R.id.frag_mineList_price);
            time = (TextView) itemView.findViewById(R.id.frag_mineList_time);
        }
    }
}
