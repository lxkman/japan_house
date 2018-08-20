package com.haiwai.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.activity.WebActivity;
import com.haiwai.administrator.japanhouse.bean.Bay_baike_Bean;
import com.haiwai.administrator.japanhouse.utils.CacheUtils;
import com.haiwai.administrator.japanhouse.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class BaikeAdapter extends RecyclerView.Adapter<BaikeAdapter.ViewHolder> {
    private Context context;
    List<Bay_baike_Bean.DatasBean> list;
    private Boolean isJa;

    public BaikeAdapter(Context context, List<Bay_baike_Bean.DatasBean> list, Boolean isJa) {
        this.context = context;
        this.list = list;
        this.isJa = isJa;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.touadapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Bay_baike_Bean.DatasBean datasBean = list.get(position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("title", context.getString(R.string.details));
                if (MyApplication.isJapanese()) {
                    intent.putExtra("result", "http://www.flcjapan.com/hwdch5/info/paranText.html?id=" + datasBean.getId());
                } else {
                    intent.putExtra("result", "http://www.flcjapan.com/hwdch5/info/paranTextCn.html?id=" + datasBean.getId());
                }

                context.startActivity(intent);
            }
        });
        String city = CacheUtils.get(Constants.COUNTRY);
        if (city != null && city.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        //赋值
        if (isJa) {
            holder.neirong.setText(datasBean.getTitleJpn());
        } else {
            holder.neirong.setText(datasBean.getTitleCn());
        }
        holder.person.setText(datasBean.getReadNum() + context.getString(R.string.people_readNum));
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
        String format = sdf.format(new Date(datasBean.getCreateTime()));
        holder.time.setText(format);
        Glide.with(context)
                .load(datasBean.getImageUrl())
                .into(holder.tou);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView tou;
        public View view;
        public TextView neirong;
        public TextView time;
        public TextView person;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tou = (ImageView) itemView.findViewById(R.id.imag_tou);
            neirong = (TextView) itemView.findViewById(R.id.tou_neirong);
            time = (TextView) itemView.findViewById(R.id.tou_time);
            person = (TextView) itemView.findViewById(R.id.person);
        }
    }
}
