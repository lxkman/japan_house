package com.example.administrator.japanhouse.fragment.home;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.bean.MoreCheckBean;
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.yyydjk.library.DropDownMenu;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

class MoreView implements View.OnClickListener {

    private Context context;
    private MyItemClickListener listener;
    private RecyclerView mrecycler;
    private Button btn_sure;
    private Button btn_reset;
    private DropDownMenu dropDownMenu;
    private LinearLayout ll_root;
    private List<MoreCheckBean> moreCheckBeanList = new ArrayList<>();
    private LiebiaoAdapter liebiaoAdapter;
    private String mtype;

    MoreView(Context context) {
        this.context = context;
    }

    void setListener(MyItemClickListener listener) {
        this.listener = listener;
    }

    View secView() {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_more, null);
        ll_root = (LinearLayout) view.findViewById(R.id.ll_root);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll_root.getLayoutParams();
        layoutParams.height = MyUtils.getScreenHeight(context) * 3 / 4;
        ll_root.setLayoutParams(layoutParams);
        mrecycler = (RecyclerView) view.findViewById(R.id.Mrecycler);
        mrecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mrecycler.setNestedScrollingEnabled(false);
        btn_sure = (Button) view.findViewById(R.id.btn_sure);
        btn_reset = (Button) view.findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(this);
        btn_sure.setOnClickListener(this);
        return view;
    }

    void insertData(List<MoreCheckBean> more, DropDownMenu dropDownMenu, String type) {
        this.dropDownMenu = dropDownMenu;
        moreCheckBeanList.clear();
        moreCheckBeanList = more;
        mtype = type;
        initData();
    }

    void insertData(List<MoreCheckBean> more, DropDownMenu dropDownMenu) {
        this.dropDownMenu = dropDownMenu;
        moreCheckBeanList.clear();
        moreCheckBeanList = more;
        initData();
    }

    private void initData() {
        liebiaoAdapter = new LiebiaoAdapter(R.layout.more_item, moreCheckBeanList);
        mrecycler.setAdapter(liebiaoAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reset:
                chongzhi();
                break;
            case R.id.btn_sure:
                dropDownMenu.closeMenu();
                listener.onMoreItemClick(v, getSelectedData());
                break;
        }
    }

    private void chongzhi() {
        if (moreCheckBeanList != null && moreCheckBeanList.size() > 0) {
            for (int i = 0; i < moreCheckBeanList.size(); i++) {
                MoreCheckBean moreCheckBean = moreCheckBeanList.get(i);
                List<OneCheckBean> checkBeanList = moreCheckBean.getCheckBeanList();
                if (checkBeanList != null && checkBeanList.size() > 0) {
                    for (int i1 = 0; i1 < checkBeanList.size(); i1++) {
                        checkBeanList.get(i1).setChecked(false);
                    }
                }
            }
            if (liebiaoAdapter != null) {
                liebiaoAdapter.notifyDataSetChanged();
                listener.onMoreItemClick(null, new ArrayList<List<String>>());
            }
        }
    }

    private List<List<String>> getSelectedData() {
        List<List<String>> selectedBeen = new ArrayList<>();
        if (moreCheckBeanList != null && moreCheckBeanList.size() > 0) {
            for (int i = 0; i < moreCheckBeanList.size(); i++) {
                List<String> selectedItemList = new ArrayList<>();
                MoreCheckBean moreCheckBean = moreCheckBeanList.get(i);
                List<OneCheckBean> checkBeanList = moreCheckBean.getCheckBeanList();
                if (checkBeanList != null && checkBeanList.size() > 0) {
                    for (int i1 = 0; i1 < checkBeanList.size(); i1++) {
                        OneCheckBean oneCheckBean = checkBeanList.get(i1);
                        if (oneCheckBean.isChecked()) {
                            selectedItemList.add(oneCheckBean.getId() + "");
                        }
                    }
                    selectedBeen.add(selectedItemList);
                }
            }
        }
        return selectedBeen;
    }


    class ItemAdapter extends BaseQuickAdapter<OneCheckBean, BaseViewHolder> {

        public ItemAdapter(@LayoutRes int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final OneCheckBean item) {
            helper.setText(R.id.rb_title, item.getName());
            final TextView textView = helper.getView(R.id.rb_title);
            if (item.isChecked()) {
                textView.setBackground(mContext.getResources().getDrawable(R.drawable.round_drakyellow));
                textView.setTextColor(mContext.getResources().getColor(R.color.white));
            } else {
                textView.setBackground(mContext.getResources().getDrawable(R.drawable.round_gray));
                textView.setTextColor(mContext.getResources().getColor(R.color.moreuncheck));
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (item.getName().equals(mContext.getResources().getString(R.string.huaquanzhaofang))) {
                        dropDownMenu.closeMenu();
                        if (!TextUtils.isEmpty(mtype)) {
                            if (mtype.equals("newhouse")) {
                                EventBus.getDefault().postSticky(new EventBean("drawcirclefindhouse_new"));
                            } else if (mtype.equals("oldhouse")) {
                                EventBus.getDefault().postSticky(new EventBean("drawcirclefindhouse_old"));
                            } else if (mtype.equals("zuhouse")) {
                                EventBus.getDefault().postSticky(new EventBean("drawcirclefindhouse_zu"));
                            }
                        }
                    }
                    item.setChecked(!item.isChecked());
                    liebiaoAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    class LiebiaoAdapter extends BaseQuickAdapter<MoreCheckBean, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<MoreCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, MoreCheckBean item) {
            helper.setText(R.id.rb_title, item.getName());
            RecyclerView recycler_item = helper.getView(R.id.recycler_item);
            recycler_item.setNestedScrollingEnabled(false);
//            recycler_item.setLayoutManager(new FlowLayoutManager());
            recycler_item.setLayoutManager(new GridLayoutManager(mContext, 4));
            recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item, item.getCheckBeanList()));
        }
    }

}
