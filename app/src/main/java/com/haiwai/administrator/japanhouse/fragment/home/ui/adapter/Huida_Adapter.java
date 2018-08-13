package com.haiwai.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.bean.HuiFu_Bean;
import com.haiwai.administrator.japanhouse.fragment.home.ui.activity.WenDa_Detils_Activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class Huida_Adapter extends RecyclerView.Adapter<Huida_Adapter.ViewHolder> {
    private Context context;
    List<HuiFu_Bean.DatasBean> list=new ArrayList<>();

    public Huida_Adapter(Context context, List<HuiFu_Bean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.huida_adapter, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, WenDa_Detils_Activity.class);
                intent.putExtra("askid",list.get(position).getAskId());
                intent.putExtra("title",list.get(position).getDescription());
                context.startActivity(intent);
            }
        });
        HuiFu_Bean.DatasBean datasBean = list.get(position);
        long updateTime = datasBean.getUpdateTime();
        String dateToString = getDateToString(String.valueOf(updateTime / 1000));
        holder.text_time.setText(dateToString);
        holder.text_title.setText(datasBean.getDescription());
        holder.text_neirong.setText(datasBean.getContent());
        holder.person.setText(datasBean.getAnswerNum()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text_title;
        public TextView text_time;
        public TextView text_neirong;
        public TextView person;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            text_title = (TextView) itemView.findViewById(R.id.text_title);
            text_time = (TextView) itemView.findViewById(R.id.text_time);
            text_neirong = (TextView) itemView.findViewById(R.id.text_neirong);
            person = (TextView) itemView.findViewById(R.id.person);

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
