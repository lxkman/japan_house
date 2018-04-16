package com.example.administrator.japanhouse.fragment.home;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.yyydjk.library.DropDownMenu;

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
    private Button btn_reset;
    private DropDownMenu dropDownMenu;

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
        btn_reset = (Button) view.findViewById(R.id.btn_reset);
        return view;
    }

    void insertData(List<OneCheckBean> list, DropDownMenu dropDownMenu) {
        mList = list;
        this.dropDownMenu = dropDownMenu;
        mItemList = new ArrayList<>();
        mItemList.add(new OneCheckBean(false,"测试数据"));
        mItemList.add(new OneCheckBean(false,"测试数据"));
        mItemList.add(new OneCheckBean(false,"测试数据"));
        mItemList.add(new OneCheckBean(false,"测试数据"));
        mItemList.add(new OneCheckBean(false,"测试数据"));
        initData();
    }

    private void initData() {
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropDownMenu.closeMenu();
            }
        });
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
            recycler_item.setNestedScrollingEnabled(false);
            recycler_item.setLayoutManager(new GridLayoutManager(mContext,4));
            recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item,mItemList));
        }
    }

    class ItemAdapter extends BaseQuickAdapter<OneCheckBean, BaseViewHolder> {

        public ItemAdapter(@LayoutRes int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final OneCheckBean item) {
            helper.setText(R.id.rb_title, item.getName());
            final TextView textView = helper.getView(R.id.rb_title);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.round_drakyellow));
                    textView.setTextColor(mContext.getResources().getColor(R.color.white));
                    item.setChecked(true);
                }
            });
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
