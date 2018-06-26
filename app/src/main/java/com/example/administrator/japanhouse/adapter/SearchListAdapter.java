package com.example.administrator.japanhouse.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.model.ChatSearchBean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {
    private Context context;
    List<ChatSearchBean.DatasBean>list;
    private  Boolean isJa;
    private String searchContent;

    public SearchListAdapter(Context context, List<ChatSearchBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void setSearchContent(String searchContent){
        this.searchContent = searchContent;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (list != null && list.size() > 0 && searchContent != null) {
            int indexOf = list.get(position).getBrokerName().indexOf(searchContent);

            if (indexOf != -1) {
                SpannableStringBuilder builder = new SpannableStringBuilder(list.get(position).getBrokerName());
                ForegroundColorSpan span1 = new ForegroundColorSpan(Color.parseColor("#FE972A"));
                builder.setSpan(span1, indexOf, indexOf + searchContent.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.tv_Search_list.setText(builder);
            } else {
                holder.tv_Search_list.setText(list.get(position).getBrokerName());
            }

        }


    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public View view;
        public TextView tv_Search_list;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tv_Search_list = (TextView) itemView.findViewById(R.id.tv_Search_list);
        }
    }
}
