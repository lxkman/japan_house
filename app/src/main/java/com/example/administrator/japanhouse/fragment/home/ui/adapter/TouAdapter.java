package com.example.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.bean.TouTiaoListBean;
import com.example.administrator.japanhouse.fragment.home.ui.activity.TouDetailActivity;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class TouAdapter extends RecyclerView.Adapter<TouAdapter.ViewHolder> {
    private Context context;
    List<TouTiaoListBean.DatasBean>list=new ArrayList<>();
    private boolean isJa;

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
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.neirong.setText(isJa ? list.get(viewType).getTitleJpn() : list.get(viewType).getTitleCn());
        viewHolder.time.setText(MyUtils.getDateToStringH(list.get(viewType).getCreateTime()+""));
        viewHolder.person.setText(list.get(viewType).getReadNum()+context.getResources().getString(R.string.renchakan));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
     holder.view.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(context, TouDetailActivity.class);
             intent.putExtra("Tid",list.get(position).getId()+"");
              context.startActivity(intent);
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
