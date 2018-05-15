package com.example.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.bean.WenDa_Details_Pinglun_Bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class WenDa_Detil_Adapter extends RecyclerView.Adapter<WenDa_Detil_Adapter.ViewHolder> {
    Context context;
    List<WenDa_Details_Pinglun_Bean.DatasBean>list;

    public WenDa_Detil_Adapter(Context context, List<WenDa_Details_Pinglun_Bean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.toutiao_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        long updateTime = list.get(position).getUpdateTime();
        String dateToString = getDateToString(String.valueOf(updateTime / 1000));
        holder.time.setText(dateToString);
       holder.content.setText(list.get(position).getContent());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView touxiang;
        public TextView name;
        public TextView time;
        public TextView content;
        public TextView zanperson;
        public ImageView zan;

        public ViewHolder(View itemView) {
            super(itemView);
            touxiang = (ImageView) itemView.findViewById(R.id.touxiang);
            name = (TextView) itemView.findViewById(R.id.name);
            time = (TextView) itemView.findViewById(R.id.time);
            content = (TextView) itemView.findViewById(R.id.pinglun);
            zanperson = (TextView) itemView.findViewById(R.id.zan_person);
            zan = (ImageView) itemView.findViewById(R.id.zan);
        }
    }

    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }
}
