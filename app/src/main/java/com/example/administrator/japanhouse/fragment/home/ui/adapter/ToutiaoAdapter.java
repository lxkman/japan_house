package com.example.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.example.administrator.japanhouse.utils.ToastUtils;
import com.example.administrator.japanhouse.view.CircleImageView;

import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class ToutiaoAdapter extends RecyclerView.Adapter<ToutiaoAdapter.ViewHolder> {
    Context context;
    List<com.example.administrator.japanhouse.bean.TouListBean.DatasBean> datas;
    public ToutiaoAdapter(Context context,List<com.example.administrator.japanhouse.bean.TouListBean.DatasBean> datas) {
        this.context = context;
        this.datas=datas;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.toutiao_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Glide.with(context).load(datas.get(viewType).getHwdcUser().getPic()).into(viewHolder.touxiang);
        viewHolder.name.setText(datas.get(viewType).getHwdcUser().getNickname()+"");
        viewHolder.time.setText(MyUtils.getDateToStringH(datas.get(viewType).getHwdcUser().getCreateTime()+"")+"");
        viewHolder.pinglun.setText(datas.get(viewType).getContent()+"");
        viewHolder.zanperson.setText(datas.get(viewType).getZanNum()+"");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.zan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtils.getToast(context,"点赞");
                }
            });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView touxiang;
        public TextView name;
        public TextView time;
        public TextView pinglun;
        public TextView zanperson;
        public ImageView zan;

        public ViewHolder(View itemView) {
            super(itemView);
            touxiang = (CircleImageView) itemView.findViewById(R.id.touxiang);
            name = (TextView) itemView.findViewById(R.id.name);
            time = (TextView) itemView.findViewById(R.id.time);
            pinglun = (TextView) itemView.findViewById(R.id.pinglun);
            zanperson = (TextView) itemView.findViewById(R.id.zan_person);
            zan = (ImageView) itemView.findViewById(R.id.zan);
        }
    }
}
