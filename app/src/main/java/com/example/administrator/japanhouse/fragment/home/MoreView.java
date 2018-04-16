package com.example.administrator.japanhouse.fragment.home;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.bean.OneCheckBean;

import java.util.ArrayList;
import java.util.List;

class MoreView {

    private Context context;
    private MyItemClickListener listener;
    private RecyclerView mrecycler;
    private LiebiaoAdapter mLiebiaoAdapter;
    private List<OneCheckBean> mList = new ArrayList();
    private List<OneCheckBean> mItemList = new ArrayList();
    private Button btn_sure;

    MoreView(Context context) {
        this.context = context;
    }

    void setListener(MyItemClickListener listener) {
        this.listener = listener;
    }

    View secView() {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_more, null);
        mrecycler = (RecyclerView) view.findViewById(R.id.Mrecycler);
        btn_sure = (Button) view.findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return view;
    }

    void insertData(List<OneCheckBean> list) {
        mList = list;
        mItemList=new ArrayList<>();
        initData();
    }

    private void initData() {
        if (mLiebiaoAdapter == null) {
            mLiebiaoAdapter = new LiebiaoAdapter(R.layout.more_item, mList);
        }
        mrecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setAdapter(mLiebiaoAdapter);
    }

    class LiebiaoAdapter extends BaseQuickAdapter<OneCheckBean, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, OneCheckBean item) {
            helper.setText(R.id.rb_title, item.getName());
            RecyclerView recycler_item = helper.getView(R.id.recycler_item);
//            recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item,));
        }
    }

    class ItemAdapter extends BaseQuickAdapter<OneCheckBean, BaseViewHolder> {

        public ItemAdapter(@LayoutRes int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, OneCheckBean item) {
            helper.setText(R.id.rb_title, item.getName());
            RecyclerView recycler_item = helper.getView(R.id.recycler_item);
        }
    }

    private class mClick implements View.OnClickListener {

        String string;

        private mClick(String string) {
            this.string = string;
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, 2, string);
        }
    }

}
