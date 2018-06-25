package com.example.administrator.japanhouse.fragment.home.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.example.administrator.japanhouse.view.CircleImageView;

import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class ToutiaoAdapter extends RecyclerView.Adapter<ToutiaoAdapter.ViewHolder> {
    Context context;
    List<com.example.administrator.japanhouse.bean.TouListBean.DatasBean> datas;

    private OnItemPraiseListener onItemPraiseListener;

    public void setOnItemPraiseListener(OnItemPraiseListener onItemPraiseListener){
        this.onItemPraiseListener = onItemPraiseListener;
    }

    public ToutiaoAdapter(Context context, List<com.example.administrator.japanhouse.bean.TouListBean.DatasBean> datas) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.toutiao_adapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Glide.with(context).load(datas.get(position).getHwdcUser().getPic()).into(holder.touxiang);
        holder.name.setText(datas.get(position).getHwdcUser().getNickname() + "");
        holder.time.setText(MyUtils.getDateToStringH(datas.get(position).getHwdcUser().getCreateTime() + "") + "");
        holder.pinglun.setText(datas.get(position).getContent() + "");
//        holder.zan.setParisView(datas.get(position).getIsZan(), datas.get(position).getZanNum(), datas.get(position).getId());
        holder.zan.setText(datas.get(position).getZanNum() + "");
        if (datas.get(position).getIsZan() == 1) {
            Drawable drawable = context.getResources().getDrawable(R.drawable.dwon_praise);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.zan.setCompoundDrawables(drawable, null, null, null);
        } else {
            Drawable drawable = context.getResources().getDrawable(R.drawable.up_praise);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.zan.setCompoundDrawables(drawable, null, null, null);
        }

        holder.zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyUtils.isLogin(context)) {
                    if (datas.get(position).getIsZan() == 1) {
                        Drawable drawable = context.getResources().getDrawable(R.drawable.up_praise);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        holder.zan.setCompoundDrawables(drawable, null, null, null);
                        datas.get(position).setIsZan(0);
                        datas.get(position).setZanNum(datas.get(position).getZanNum() - 1);
                        holder.zan.setText(datas.get(position).getZanNum() + "");
                        onItemPraiseListener.onItemDwonListener(datas.get(position).getId());
                    } else {
                        Drawable drawable = context.getResources().getDrawable(R.drawable.dwon_praise);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        holder.zan.setCompoundDrawables(drawable, null, null, null);
                        datas.get(position).setIsZan(1);
                        datas.get(position).setZanNum(datas.get(position).getZanNum() + 1);
                        holder.zan.setText(datas.get(position).getZanNum() + "");
                        onItemPraiseListener.onItemUpListener(datas.get(position).getId());
                    }
                } else {
                    Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
                    MyUtils.StartLoginActivity(context);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView touxiang;
        public TextView name;
        public TextView time;
        public TextView pinglun;
        public TextView zan;

        public ViewHolder(View itemView) {
            super(itemView);
            touxiang = (CircleImageView) itemView.findViewById(R.id.touxiang);
            name = (TextView) itemView.findViewById(R.id.name);
            time = (TextView) itemView.findViewById(R.id.time);
            pinglun = (TextView) itemView.findViewById(R.id.pinglun);
            zan = (TextView) itemView.findViewById(R.id.view_comment_praiseView);
        }
    }

    public interface OnItemPraiseListener{
        void onItemUpListener(int commentId);
        void onItemDwonListener(int commentId);
    }
}
