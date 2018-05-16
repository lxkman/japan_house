package com.example.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.bean.Bay_baike_Bean;
import com.example.administrator.japanhouse.fragment.home.ui.activity.BaikeDetailActivity;
import com.example.administrator.japanhouse.fragment.home.ui.activity.TouDetailActivity;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class BaikeAdapter extends RecyclerView.Adapter<BaikeAdapter.ViewHolder> {
    private Context context;
    List<Bay_baike_Bean.DatasBean>list;
    private  Boolean isJa;

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
             Intent intent = new Intent(context, BaikeDetailActivity.class);
             intent.putExtra("wid",datasBean.getId());
              context.startActivity(intent);
         }
     });
        String city = CacheUtils.get(Constants.COUNTRY);
        if(city!=null&&city.equals("ja")){
            isJa=true;
        }else{
            isJa=false;
        }
        //赋值
        if(isJa){
            holder.neirong.setText(datasBean.getTitleJpn());
        }else{
            holder.neirong.setText(datasBean.getTitleCn());
        }
        holder.person.setText(datasBean.getReadNum()+"人查看");
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

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
