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
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.List;

class FirstView {

    private Context context;
    private MyItemClickListener listener;
    private RecyclerView mrecycler;
    private LiebiaoAdapter mLiebiaoAdapter;
    private List<OneCheckBean> mList=new ArrayList();
    private Button btn_sure;
    private DropDownMenu dropDownMenu;

    FirstView(Context context) {
        this.context = context;
    }

    void setListener(MyItemClickListener listener) {
        this.listener = listener;
    }

    View firstView() {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_first, null);
        mrecycler = (RecyclerView) view.findViewById(R.id.Mrecycler);
        btn_sure = (Button) view.findViewById(R.id.btn_sure);
        return view;
    }

    void insertData(List<OneCheckBean> list, DropDownMenu dropDownMenu) {
        mList = list;
        this.dropDownMenu = dropDownMenu;
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
            mLiebiaoAdapter = new LiebiaoAdapter(R.layout.leixing_item,mList);
        }
        mrecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setAdapter(mLiebiaoAdapter);
        mLiebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }
    class LiebiaoAdapter extends BaseQuickAdapter<OneCheckBean, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId,data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, OneCheckBean item) {
            helper.setText(R.id.rb_title,item.getName());
            helper.setChecked(R.id.rb_title, item.isChecked());
            helper.setVisible(R.id.img_isCheck,item.isChecked());
            helper.getView(R.id.rb_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //直接在外层用adapter的点击事件就不管用，真是邪门
                    for (int i = 0; i < mList.size(); i++) {
                        if (helper.getAdapterPosition() == i) {
                            mList.get(i).setChecked(true);
                        } else {
                            mList.get(i).setChecked(false);
                        }
                    }
                    mLiebiaoAdapter.notifyDataSetChanged();
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
            listener.onItemClick(v, 1, string);
        }
    }

}
