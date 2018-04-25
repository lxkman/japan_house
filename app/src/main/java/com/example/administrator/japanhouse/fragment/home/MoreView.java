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
    private List<OneCheckBean> mItemList0 = new ArrayList();
    private Button btn_sure;
    private Button btn_reset;
    private DropDownMenu dropDownMenu;
    private List<OneCheckBean> mItemList1;
    private List<OneCheckBean> mItemList2;
    private List<OneCheckBean> mItemList3;
    private List<OneCheckBean> mItemList4;
    private List<OneCheckBean> mItemList5;
    private List<OneCheckBean> mItemList6;
    private List<OneCheckBean> mItemList7;

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
        mItemList0 = new ArrayList<>();
        mItemList0.add(new OneCheckBean(false,"画圈找房"));
        mItemList1 = new ArrayList<>();
        mItemList1.add(new OneCheckBean(false,"砖木结构"));
        mItemList1.add(new OneCheckBean(false,"砖混结构"));
        mItemList1.add(new OneCheckBean(false,"钢结构"));
        mItemList1.add(new OneCheckBean(false,"近学校"));
        mItemList2 = new ArrayList<>();
        mItemList2.add(new OneCheckBean(false,"商业街"));
        mItemList2.add(new OneCheckBean(false,"办公区"));
        mItemList2.add(new OneCheckBean(false,"购物中心"));
        mItemList3 = new ArrayList<>();
        mItemList3.add(new OneCheckBean(false,"向南"));
        mItemList3.add(new OneCheckBean(false,"向北"));
        mItemList3.add(new OneCheckBean(false,"东南"));
        mItemList3.add(new OneCheckBean(false,"西南"));
        mItemList3.add(new OneCheckBean(false,"东北"));
        mItemList3.add(new OneCheckBean(false,"西北"));
        mItemList4 = new ArrayList<>();
        mItemList4.add(new OneCheckBean(false,"20"));
        mItemList4.add(new OneCheckBean(false,"20-40"));
        mItemList4.add(new OneCheckBean(false,"40-80"));
        mItemList4.add(new OneCheckBean(false,"100以上"));
        mItemList5 = new ArrayList<>();
        mItemList5.add(new OneCheckBean(false,"热水器"));
        mItemList5.add(new OneCheckBean(false,"电视"));
        mItemList5.add(new OneCheckBean(false,"空调"));
        mItemList5.add(new OneCheckBean(false,"冰箱"));
        mItemList6 = new ArrayList<>();
        mItemList6.add(new OneCheckBean(false,"3室1厅"));
        mItemList6.add(new OneCheckBean(false,"2室1厅"));
        mItemList6.add(new OneCheckBean(false,"1室1厅"));
        mItemList6.add(new OneCheckBean(false,"公寓"));
        mItemList7 = new ArrayList<>();
        mItemList7.add(new OneCheckBean(false,"地板"));
        mItemList7.add(new OneCheckBean(false,"榻榻米"));
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
            if (item.getName().equals("区域")){
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item,mItemList0));
            }else if (item.getName().equals("构造")){
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item,mItemList1));
            }else if (item.getName().equals("地段")){
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item,mItemList2));
            }else if (item.getName().equals("朝向")){
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item,mItemList3));
            }else if (item.getName().equals("面积（平米）")){
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item,mItemList4));
            }else if (item.getName().equals("格局")){
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item,mItemList6));
            }else if (item.getName().equals("洋室")){
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item,mItemList7));
            }else if (item.getName().equals("和室")){
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item,mItemList7));
            }else {
                recycler_item.setAdapter(new ItemAdapter(R.layout.more_item_item,mItemList5));
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
