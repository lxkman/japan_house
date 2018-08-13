package com.haiwai.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.bean.TouTiaoListBean;
import com.haiwai.administrator.japanhouse.fragment.home.ui.activity.TouDetailActivity;
import com.haiwai.administrator.japanhouse.fragment.home.ui.activity.ToutiaoActivity;
import com.haiwai.administrator.japanhouse.utils.CacheUtils;
import com.haiwai.administrator.japanhouse.utils.Constants;
import com.haiwai.administrator.japanhouse.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class TouAdapter extends RecyclerView.Adapter<TouAdapter.ViewHolder> {
    private Context context;
    List<TouTiaoListBean.DatasBean>list=new ArrayList<>();
    private boolean isJa;
    private ViewHolder viewHolder;

    public TouAdapter(Context context, List<TouTiaoListBean.DatasBean>list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.touadapter, parent, false);
        String city = CacheUtils.get(Constants.COUNTRY);
        if (city != null && city.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
      ViewHolder  viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.neirong.setText(isJa ? list.get(position).getTitleJpn() : list.get(position).getTitleCn());
        holder.time.setText(MyUtils.getTimeFromMillisecond(list.get(position).getCreateTime()));
        holder.person.setText(list.get(position).getReadNum()+context.getResources().getString(R.string.renchakan));
     holder.view.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(context, TouDetailActivity.class);
             intent.putExtra("Tid",list.get(position).getId()+"");
             ToutiaoActivity activity = (ToutiaoActivity) context;
             activity.startActivityForResult(intent,1);
         }
     });
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
