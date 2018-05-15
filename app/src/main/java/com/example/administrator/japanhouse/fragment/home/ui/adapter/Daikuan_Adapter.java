package com.example.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.bean.LoansBean;
import com.example.administrator.japanhouse.fragment.home.ui.activity.DaikuanDetilsActivity;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/12.
 */

public class Daikuan_Adapter extends RecyclerView.Adapter<Daikuan_Adapter.ViewHolder> {
    Context context;
    List<LoansBean.DatasBean> list;

    public Daikuan_Adapter(Context context, List<LoansBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.daikuanadapter, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final LoansBean.DatasBean datasBean = list.get(position);
        final String rateCn = datasBean.getRateCn();
        double i = Integer.parseInt(rateCn)*1.0d;
        final String city = CacheUtils.get(Constants.COUNTRY);
        //点击跳转
      holder.view.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(context, DaikuanDetilsActivity.class);
              if(city!=null&&city.equals("ja")){
                  intent.putExtra("id",datasBean.getId());
                  intent.putExtra("name",datasBean.getNameJpn());
                  intent.putExtra("lixi",rateCn);
                  intent.putExtra("fanwei",datasBean.getLimitRangeJpn());
                  intent.putExtra("zhouqi",datasBean.getAgeLimitJpn());
                  intent.putExtra("fangshi",datasBean.getLendingWayJpn());
                  intent.putExtra("shijian",datasBean.getPaymentScheduleJpn());
                  intent.putExtra("huji",datasBean.getCensusRegisterJpn());
                  intent.putExtra("cailiao",datasBean.getMaterialsJpn());
              }else{
                  intent.putExtra("id",datasBean.getId());
                  intent.putExtra("name",datasBean.getNameCn());
                  intent.putExtra("lixi",rateCn);
                  intent.putExtra("fanwei",datasBean.getLimitRangeCn());
                  intent.putExtra("zhouqi",datasBean.getAgeLimitCn());
                  intent.putExtra("fangshi",datasBean.getLendingWayCn());
                  intent.putExtra("shijian",datasBean.getPaymentScheduleCn());
                  intent.putExtra("huji",datasBean.getCensusRegisterCn());
                  intent.putExtra("cailiao",datasBean.getMaterialsCn());
              }

              context.startActivity(intent);
          }
      });
        if(city!=null&&city.equals("ja")){
            //贷款名称
            holder.name.setText(datasBean.getNameJpn());
            //贷款范围
            holder.fanwei.setText("最高"+datasBean.getLimitRangeJpn()+"万");
            //周期
            holder.zhouqi.setText(datasBean.getAgeLimitJpn()+"个月");
            //月息
            holder.yuexi.setText("月息"+i+"%");
        }else{
            //贷款名称
            holder.name.setText(datasBean.getNameCn());
            //贷款范围
            holder.fanwei.setText("最高"+datasBean.getLimitRangeCn()+"万");
            //周期
            holder.zhouqi.setText(datasBean.getAgeLimitCn()+"个月");
            //月息
            holder.yuexi.setText("月息"+i+"%");
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView zhouqi;
        public TextView fanwei;
        public TextView yuexi;
        public TextView name;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = (TextView) itemView.findViewById(R.id.dai_name);
            yuexi = (TextView) itemView.findViewById(R.id.yuexi);
            fanwei = (TextView) itemView.findViewById(R.id.fanwei);
            zhouqi = (TextView) itemView.findViewById(R.id.zhouqi);
        }
    }
}
