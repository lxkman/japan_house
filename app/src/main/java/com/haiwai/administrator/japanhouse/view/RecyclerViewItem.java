package com.haiwai.administrator.japanhouse.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.activity.adapter.LinkmanViewAdapter;
import com.haiwai.administrator.japanhouse.model.LinkmanBean;

import java.util.ArrayList;
import java.util.List;

/**
 * admin  2018/6/13
 */
public class RecyclerViewItem extends View {
    private RecyclerView mRecyclerView;

    private TextView title;

    private LinkmanViewAdapter adapter;

    private List<LinkmanBean.DatasBean.ListBean> mList;

    public RecyclerViewItem(Context context) {
        super(context);
        initView(context);
    }

    public RecyclerViewItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_recyclerview_item, null);

        title = (TextView) view.findViewById(R.id.item_linkman_title);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.item_linkman_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mList = new ArrayList<>();
        adapter = new LinkmanViewAdapter(context, mList);
        mRecyclerView.setAdapter(adapter);
    }

    public void setDatas(LinkmanBean.DatasBean datasBean){
        requestLayout();
        title.setText(datasBean.getKey());
        mList.addAll(datasBean.getList());
        adapter.notifyDataSetChanged();
    }
}
