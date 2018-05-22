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
    private LiebiaoAdapter mLiebiaoAdapter;
    private List<OneCheckBean> mList = new ArrayList();
    private List<OneCheckBean> mItemList0 = new ArrayList();
    private Button btn_sure;
    private Button btn_reset;
    private DropDownMenu dropDownMenu;
    private LinearLayout ll_root;
    private String mtype;
    private List<OneCheckBean> mItemList1;
    private List<OneCheckBean> mItemList2;
    private List<OneCheckBean> mItemList3;
    private List<OneCheckBean> mItemList4;
    private List<OneCheckBean> mItemList5;
    private List<OneCheckBean> mItemList6;
    private List<OneCheckBean> mItemList7;
    private List<MoreCheckBean> moreCheckBeanList = new ArrayList<>();
    private boolean ruleData;
    private LiebiaoAdapter2 liebiaoAdapter2;

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
        btn_sure = (Button) view.findViewById(R.id.btn_sure);
        btn_reset = (Button) view.findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(this);
        btn_sure.setOnClickListener(this);
        return view;
    }

    void insertData3(List<MoreCheckBean> more, DropDownMenu dropDownMenu) {
        this.dropDownMenu = dropDownMenu;
        moreCheckBeanList.clear();
        moreCheckBeanList = more;
        ruleData = true;
        initData();
    }

    void insertData(List<OneCheckBean> list, DropDownMenu dropDownMenu) {
        mList = list;
        this.dropDownMenu = dropDownMenu;
        mItemList1 = new ArrayList<>();
        mItemList1.add(new OneCheckBean(false, "砖木结构"));
        mItemList1.add(new OneCheckBean(false, "砖混结构"));
        mItemList1.add(new OneCheckBean(false, "钢结构"));
        mItemList2 = new ArrayList<>();
        mItemList2.add(new OneCheckBean(false, "商业街"));
        mItemList2.add(new OneCheckBean(false, "办公区"));
        mItemList2.add(new OneCheckBean(false, "购物中心"));
        mItemList3 = new ArrayList<>();
        mItemList3.add(new OneCheckBean(false, "向南"));
        mItemList3.add(new OneCheckBean(false, "向北"));
        mItemList3.add(new OneCheckBean(false, "东南"));
        mItemList3.add(new OneCheckBean(false, "西南"));
        mItemList3.add(new OneCheckBean(false, "东北"));
        mItemList3.add(new OneCheckBean(false, "西北"));
        mItemList4 = new ArrayList<>();
        mItemList4.add(new OneCheckBean(false, "20"));
        mItemList4.add(new OneCheckBean(false, "20-40"));
        mItemList4.add(new OneCheckBean(false, "40-80"));
        mItemList4.add(new OneCheckBean(false, "100以上"));
        mItemList5 = new ArrayList<>();
        mItemList5.add(new OneCheckBean(false, "热水器"));
        mItemList5.add(new OneCheckBean(false, "电视"));
        mItemList5.add(new OneCheckBean(false, "空调"));
        mItemList5.add(new OneCheckBean(false, "冰箱"));
        mItemList6 = new ArrayList<>();
        mItemList6.add(new OneCheckBean(false, "3室1厅"));
        mItemList6.add(new OneCheckBean(false, "2室1厅"));
        mItemList6.add(new OneCheckBean(false, "1室1厅"));
        mItemList6.add(new OneCheckBean(false, "公寓"));
        mItemList7 = new ArrayList<>();
        mItemList7.add(new OneCheckBean(false, "地板"));
        mItemList7.add(new OneCheckBean(false, "榻榻米"));
        initData();
    }

    void insertData2(String type, List<OneCheckBean> list, DropDownMenu dropDownMenu) {
        mList = list;
        mtype = type;
        this.dropDownMenu = dropDownMenu;
        mItemList0 = new ArrayList<>();
        mItemList0.add(new OneCheckBean(false, "画圈找房"));
        mItemList1 = new ArrayList<>();
        mItemList1.add(new OneCheckBean(false, "砖木结构"));
        mItemList1.add(new OneCheckBean(false, "砖混结构"));
        mItemList1.add(new OneCheckBean(false, "钢结构"));
        mItemList2 = new ArrayList<>();
        mItemList2.add(new OneCheckBean(false, "商业街"));
        mItemList2.add(new OneCheckBean(false, "办公区"));
        mItemList2.add(new OneCheckBean(false, "购物中心"));
        mItemList3 = new ArrayList<>();
        mItemList3.add(new OneCheckBean(false, "向南"));
        mItemList3.add(new OneCheckBean(false, "向北"));
        mItemList3.add(new OneCheckBean(false, "东南"));
        mItemList3.add(new OneCheckBean(false, "西南"));
        mItemList3.add(new OneCheckBean(false, "东北"));
        mItemList3.add(new OneCheckBean(false, "西北"));
        mItemList4 = new ArrayList<>();
        mItemList4.add(new OneCheckBean(false, "20"));
        mItemList4.add(new OneCheckBean(false, "20-40"));
        mItemList4.add(new OneCheckBean(false, "40-80"));
        mItemList4.add(new OneCheckBean(false, "100以上"));
        mItemList5 = new ArrayList<>();
        mItemList5.add(new OneCheckBean(false, "热水器"));
        mItemList5.add(new OneCheckBean(false, "电视"));
        mItemList5.add(new OneCheckBean(false, "空调"));
        mItemList5.add(new OneCheckBean(false, "冰箱"));
        mItemList6 = new ArrayList<>();
        mItemList6.add(new OneCheckBean(false, "3室1厅"));
        mItemList6.add(new OneCheckBean(false, "2室1厅"));
        mItemList6.add(new OneCheckBean(false, "1室1厅"));
        mItemList6.add(new OneCheckBean(false, "公寓"));
        mItemList7 = new ArrayList<>();
        mItemList7.add(new OneCheckBean(false, "地板"));
        mItemList7.add(new OneCheckBean(false, "榻榻米"));
        initData();
    }

    private void initData() {
        mrecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mrecycler.setNestedScrollingEnabled(false);
        if (!ruleData && mLiebiaoAdapter == null) {
            mLiebiaoAdapter = new LiebiaoAdapter(R.layout.more_item, mList);
            mrecycler.setAdapter(mLiebiaoAdapter);
        }
        if (ruleData) {
            liebiaoAdapter2 = new LiebiaoAdapter2(R.layout.more_item, moreCheckBeanList);
            mrecycler.setAdapter(liebiaoAdapter2);
        }
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

    private void chongzhi(){
        if (moreCheckBeanList != null && moreCheckBeanList.size() > 0) {
            for (int i = 0; i < moreCheckBeanList.size(); i++) {
                MoreCheckBean moreCheckBean = moreCheckBeanList.get(i);
                List<OneCheckBean> checkBeanList = moreCheckBean.getCheckBeanList();
                if (checkBeanList!=null && checkBeanList.size()>0){
                    for (int i1 = 0; i1 < checkBeanList.size(); i1++) {
                        checkBeanList.get(i1).setChecked(false);
                    }
                }
            }
            if (liebiaoAdapter2!=null){
                liebiaoAdapter2.notifyDataSetChanged();
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

    class LiebiaoAdapter extends BaseQuickAdapter<OneCheckBean, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, OneCheckBean item) {
            helper.setText(R.id.rb_title, item.getName());
            RecyclerView recycler_item = helper.getView(R.id.recycler_item);
            recycler_item.setNestedScrollingEnabled(false);
            recycler_item.setLayoutManager(new GridLayoutManager(mContext, 4));
            if (item.getName().equals("区域")) {
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item, mItemList0));
            } else if (item.getName().equals("构造")) {
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item, mItemList1));
            } else if (item.getName().equals("地段")) {
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item, mItemList2));
            } else if (item.getName().equals("朝向")) {
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item, mItemList3));
            } else if (item.getName().equals("面积(平米)")) {
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item, mItemList4));
            } else if (item.getName().equals("格局")) {
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item, mItemList6));
            } else if (item.getName().equals("洋室")) {
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item, mItemList7));
            } else if (item.getName().equals("和室")) {
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item, mItemList7));
            } else {
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item, mItemList5));
            }
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
                    if (item.getName().equals("画圈找房")) {
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
                    if (!ruleData) {
                        mLiebiaoAdapter.notifyDataSetChanged();
                    } else {
                        liebiaoAdapter2.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    class LiebiaoAdapter2 extends BaseQuickAdapter<MoreCheckBean, BaseViewHolder> {

        public LiebiaoAdapter2(@LayoutRes int layoutResId, @Nullable List<MoreCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, MoreCheckBean item) {
            helper.setText(R.id.rb_title, item.getName());
            RecyclerView recycler_item = helper.getView(R.id.recycler_item);
            recycler_item.setNestedScrollingEnabled(false);
            recycler_item.setLayoutManager(new GridLayoutManager(mContext, 4));
            recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item, item.getCheckBeanList()));
        }
    }

}
