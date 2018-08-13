package com.haiwai.administrator.japanhouse.activity.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.haiwai.administrator.japanhouse.fragment.comment.GaoerfuDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.HaiWaiDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.JiudianDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.NewHousedetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.OldHousedetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.ShangpuDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.TudidetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.XiaoQuDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.XiezilouDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.ZhongguoDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.ZuHousedetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.home.BieshudetailsActivity;
import com.haiwai.administrator.japanhouse.model.HouseRecordListBean;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_myfoot, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof RecordViewHolder) {
            RecordViewHolder viewHolder = (RecordViewHolder) holder;

            Glide.with(context)
                    .load(datas.get(position).getImageUrl())
                    .into(viewHolder.icon);
            viewHolder.title.setText(MyApplication.isJapanese() ? datas.get(position).getTitleJpn() : datas.get(position).getTitleCn());
            viewHolder.address.setText(MyApplication.isJapanese() ? datas.get(position).getAddressJpn() : datas.get(position).getAddressCn());
            viewHolder.price.setText(MyApplication.isJapanese() ? datas.get(position).getPriceJpn() + "/㎡" : datas.get(position).getPriceCn() + "/㎡");
            SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
            viewHolder.time.setText(sdf.format(new Date(datas.get(position).getCreateTime())));

            viewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(datas.get(position).getHType())) {
                        if (TextUtils.equals(datas.get(position).getHType(), "0")) { //二手房
                            Intent intent = new Intent(context, OldHousedetailsActivity.class);
                            intent.putExtra("houseId", datas.get(position).getId() + "");
                            context.startActivity(intent);
                        } else if (TextUtils.equals(datas.get(position).getHType(), "1")) { //新房
                            Intent intent = new Intent(context, NewHousedetailsActivity.class);
                            intent.putExtra("houseId", datas.get(position).getId() + "");
                            context.startActivity(intent);
                        } else if (TextUtils.equals(datas.get(position).getHType(), "2")) { //租房
                            Intent intent = new Intent(context, ZuHousedetailsActivity.class);

                            if (TextUtils.equals(datas.get(position).getShType(), "0")) { //办公室出租
                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                intent.putExtra("houseType", "bangongshi");
                            } else if (TextUtils.equals(datas.get(position).getShType(), "1")) { //商铺出租
                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                intent.putExtra("houseType", "shangpu");
                            } else if (TextUtils.equals(datas.get(position).getShType(), "2")) { //别墅
                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                intent.putExtra("houseType", "bieshu");
                            } else if (TextUtils.equals(datas.get(position).getShType(), "3")) { //二层公寓
                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                intent.putExtra("houseType", "erceng");
                            } else if (TextUtils.equals(datas.get(position).getShType(), "4")) { //学生公寓
                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                intent.putExtra("houseType", "xuesheng");
                            } else if (TextUtils.equals(datas.get(position).getShType(), "5")) { //多层公寓
                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                intent.putExtra("houseType", "duoceng");
                            }
                            context.startActivity(intent);
                        } else if (TextUtils.equals(datas.get(position).getHType(), "3")) {  //土地
                            Intent intent = new Intent(context, TudidetailsActivity.class);
                            intent.putExtra("houseId", datas.get(position).getId() + "");
                            context.startActivity(intent);
                        } else if (TextUtils.equals(datas.get(position).getHType(), "4")) {  //别墅
                            Intent intent = new Intent(context, BieshudetailsActivity.class);
                            intent.putExtra("houseId", datas.get(position).getId() + "");
                            context.startActivity(intent);
                        } else if (TextUtils.equals(datas.get(position).getHType(), "5")) {  //商业地产
                            if (TextUtils.equals(datas.get(position).getShType(), "0")) {    //酒店
                                Intent intent = new Intent(context, JiudianDetailsActivity.class);
                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                context.startActivity(intent);
                            } else if (TextUtils.equals(datas.get(position).getShType(), "1")) { //高尔夫球场
                                Intent intent = new Intent(context, GaoerfuDetailsActivity.class);
                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                context.startActivity(intent);
                            } else if (TextUtils.equals(datas.get(position).getShType(), "2")) { //写字楼
                                Intent intent = new Intent(context, XiezilouDetailsActivity.class);
                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                context.startActivity(intent);
                            } else if (TextUtils.equals(datas.get(position).getShType(), "3")) { //商铺
                                Intent intent = new Intent(context, ShangpuDetailsActivity.class);
                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                context.startActivity(intent);
                            }
                        } else if (TextUtils.equals(datas.get(position).getHType(), "6")) { //中国房源
                            Intent intent = new Intent(context, ZhongguoDetailsActivity.class);
                            intent.putExtra("houseId", datas.get(position).getId() + "");
                            context.startActivity(intent);
                        } else if (TextUtils.equals(datas.get(position).getHType(), "7")) {  //海外房源
                            Intent intent = new Intent(context, HaiWaiDetailsActivity.class);
                            intent.putExtra("houseId", datas.get(position).getId() + "");
                            context.startActivity(intent);
                        } else if (TextUtils.equals(datas.get(position).getHType(), "8")) {  //找团地
                            Intent intent = new Intent(context, XiaoQuDetailsActivity.class);
                            intent.putExtra("houseId", datas.get(position).getId() + "");
                            context.startActivity(intent);
                        }

                    }
                }
            });
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
