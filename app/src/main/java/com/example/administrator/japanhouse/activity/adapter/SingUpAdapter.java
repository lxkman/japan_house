package com.example.administrator.japanhouse.activity.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.model.SingUpBean;

import java.util.ArrayList;
import java.util.List;

/**
 * admin  2018/6/6
 */
public class SingUpAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<SingUpBean.DatasBean> datas;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public SingUpAdapter(Activity activity, List<SingUpBean.DatasBean> datas) {
        this.activity = activity;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_singup, null);
        return new SingUpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof SingUpViewHolder) {
            SingUpViewHolder viewHolder = (SingUpViewHolder) holder;


            Glide.with(activity)
                    .load(getList(datas.get(position).getImages()).get(0))
                    .into(viewHolder.icon);
            viewHolder.title.setText(MyApplication.isJapanese() ? datas.get(position).getActivityNameJpn() : datas.get(position).getActivityNameCn());
            viewHolder.time.setText(new SpannableStringBuilder(
                    String.format(activity.getString(R.string.singUp_time), "时间")));


            viewHolder.detele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemDeteleListener(position, datas.get(position).getId() + "");
                }
            });

            viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClickListener(datas.get(position));
                }
            });
        }
    }

    private List<String> getList(String pic) {
        String d[] = pic.split(",");
        List<String> picList = new ArrayList();

        for (int i = 0; i < d.length; i++) {
            picList.add(d[i]);
        }
        return picList;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class SingUpViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;
        TextView time;
        TextView detele;
        LinearLayout mView;
        public SingUpViewHolder(View itemView) {
            super(itemView);
            mView = (LinearLayout) itemView.findViewById(R.id.content_ll);
            icon = (ImageView) itemView.findViewById(R.id.item_singUp_icon);
            title = (TextView) itemView.findViewById(R.id.item_singUp_title);
            time = (TextView) itemView.findViewById(R.id.item_singUp_time);
            detele = (TextView) itemView.findViewById(R.id.shachu_tv);
        }
    }

    public interface OnItemClickListener{
        void onItemDeteleListener(int position, String sId);
        void onItemClickListener(SingUpBean.DatasBean bean);
    }
}
