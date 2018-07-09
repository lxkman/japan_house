package com.example.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.bean.QueandansBean;
import com.example.administrator.japanhouse.fragment.home.ui.activity.WenDa_Detils_Activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class Buyhouse_Adapter extends RecyclerView.Adapter<Buyhouse_Adapter.ViewHolderer> {
    private Context context;
    List<QueandansBean.DatasBean>list;

    private int type;

    public void setType(int type){
        this.type = type;
    }

    public Buyhouse_Adapter(Context context, List<QueandansBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolderer onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.buy_adapter, parent, false);
        ViewHolderer holderer = new ViewHolderer(view);
        return holderer;
    }

    @Override
    public void onBindViewHolder(ViewHolderer holder, final int position) {
        //点击跳转详情
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, WenDa_Detils_Activity.class);
                 intent.putExtra("askid", list.get(position).getId());
                intent.putExtra("title",list.get(position).getTitle());
                intent.putExtra("type", type);
                context.startActivity(intent);
            }
        });
        //赋值
        long updateTime = list.get(position).getUpdateTime();
        String dateToString = getDateToString(String.valueOf(updateTime / 1000));
        holder.text_time.setText(dateToString);
        holder.text_title.setText(list.get(position).getTitle());
        holder.text_neirong.setText(list.get(position).getDescription());
        holder.person.setText(list.get(position).getAnswerNum()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderer extends RecyclerView.ViewHolder {

        public TextView text_title;
        public TextView text_time;
        public TextView text_neirong;
        public TextView person;
        public View view;

        public ViewHolderer(View itemView) {
            super(itemView);
            view = itemView;
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
