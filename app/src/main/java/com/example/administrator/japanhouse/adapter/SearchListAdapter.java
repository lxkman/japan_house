package com.example.administrator.japanhouse.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;

import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {
    private Context context;
    List<String>list;
    private  Boolean isJa;

    public SearchListAdapter(Context context, List<String> list, Boolean isJa) {
        this.context = context;
        this.list = list;
        this.isJa = isJa;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_Search_list.setText(list.get(position));
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
