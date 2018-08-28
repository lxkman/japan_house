package com.haiwai.administrator.japanhouse.fragment.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.bean.MoreCheckBean;
import com.haiwai.administrator.japanhouse.bean.OneCheckBean;
import com.haiwai.administrator.japanhouse.utils.MyUtils;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/18.
 */

public class FirstView implements View.OnClickListener {
    private Context context;
    private MyItemClickListener listener;
    private RecyclerView mrecycler1;
    private RecyclerView mrecycler2;
    private RecyclerView mrecycler3;
    private OneAdapter oneAdapter;
    private ThreeAdapter threeAdapter;
    private List<OneCheckBean> mList1 = new ArrayList();
    private List<OneCheckBean> mList3 = new ArrayList();
    private Button btn_sure;
    private Button btn_reset;
    private DropDownMenu dropDownMenu;
    private LinearLayout ll_root;
    private List<MoreCheckBean> mQuyuListBean = new ArrayList<>();
    private List<MoreCheckBean> mDitieListBean = new ArrayList<>();
    private int adapterPosition = 0;
    private QuYuAdapter quYuAdapter;
    private DiTieAdapter diTieAdapter;
    private int adapterPosition2;

    FirstView(Context context) {
        this.context = context;
    }

    void setListener(MyItemClickListener listener) {
        this.listener = listener;
    }

    View firstView() {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_first, null);
        ll_root = (LinearLayout) view.findViewById(R.id.ll_root);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll_root.getLayoutParams();
        layoutParams.height = MyUtils.getScreenHeight(context) / 2;
        ll_root.setLayoutParams(layoutParams);
        mrecycler1 = (RecyclerView) view.findViewById(R.id.Mrecycler1);
        mrecycler2 = (RecyclerView) view.findViewById(R.id.Mrecycler2);
        mrecycler3 = (RecyclerView) view.findViewById(R.id.Mrecycler3);
        mrecycler1.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mrecycler1.setNestedScrollingEnabled(false);
        mrecycler2.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mrecycler2.setNestedScrollingEnabled(false);
        mrecycler3.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mrecycler3.setNestedScrollingEnabled(false);
        btn_sure = (Button) view.findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(this);
        btn_reset = (Button) view.findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(this);
        mList1.clear();
        mList1.add(new OneCheckBean(true, context.getString(R.string.quyu)));
        mList1.add(new OneCheckBean(false, context.getString(R.string.ditie)));
        return view;
    }

    public void insertData(List<MoreCheckBean> quyuListBean, List<MoreCheckBean> ditieListBean, DropDownMenu dropDownMenu) {
        mQuyuListBean.clear();
        mDitieListBean.clear();
        mQuyuListBean = quyuListBean;
        mDitieListBean = ditieListBean;
        this.dropDownMenu = dropDownMenu;
        initData();
    }

    private void initData() {
        if (oneAdapter == null) {
            oneAdapter = new OneAdapter(R.layout.leixing_item2, mList1);
            mrecycler1.setAdapter(oneAdapter);
        }
        if (adapterPosition == 0) {
            if (quYuAdapter == null) {
                quYuAdapter = new QuYuAdapter(R.layout.leixing_item2, mQuyuListBean);
                mrecycler2.setAdapter(quYuAdapter);
            }
            mList3 = mQuyuListBean.get(adapterPosition2).getCheckBeanList();
        } else if (adapterPosition == 1) {
            if (diTieAdapter == null) {
                diTieAdapter = new DiTieAdapter(R.layout.leixing_item2, mDitieListBean);
                mrecycler2.setAdapter(diTieAdapter);
            }
            mList3 = mDitieListBean.get(adapterPosition2).getCheckBeanList();
        }
        threeAdapter = new ThreeAdapter(R.layout.leixing_item2, mList3);
        mrecycler3.setAdapter(threeAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reset:
                reset();
                break;
            case R.id.btn_sure:
                List<String> allCheckedItem = isCheckedItemOne(adapterPosition2);
                if (allCheckedItem != null && allCheckedItem.size() == 1) {
                    dropDownMenu.setTabText(allCheckedItem.get(0));
                } else if (allCheckedItem != null && allCheckedItem.size() > 1) {
                    dropDownMenu.setTabText(context.getResources().getString(R.string.duoxuan));
                }
                dropDownMenu.closeMenu();//这个要放在最后，不然文字不会改变
                if (adapterPosition == 0) {
                    if (listener != null) {
                        listener.onItemClick(v, 1, getAllCheckedItem(adapterPosition2));
                    }
                } else {
                    if (listener != null) {
                        listener.onItemClick(v, 2, getAllCheckedItem(adapterPosition2));
                    }
                }
                break;
        }
    }

    class OneAdapter extends BaseQuickAdapter<OneCheckBean, BaseViewHolder> {

        public OneAdapter(@LayoutRes int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, OneCheckBean item) {
            helper.setText(R.id.rb_title, item.getName());
            helper.setChecked(R.id.rb_title, item.isChecked());
            helper.setVisible(R.id.img_isCheck, false);
            helper.getView(R.id.rb_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapterPosition == helper.getAdapterPosition()) {
                        return;
                    }
                    adapterPosition = helper.getAdapterPosition();
                    for (int i = 0; i < mList1.size(); i++) {
                        if (adapterPosition == i) {
                            mList1.get(i).setChecked(true);
                        } else {
                            mList1.get(i).setChecked(false);
                        }
                    }
                    adapterPosition2 = 0;
                    setFirstChecked(mQuyuListBean);
                    setFirstChecked(mDitieListBean);
                    oneAdapter.notifyDataSetChanged();
                    initData();
                }
            });
        }
    }

    class QuYuAdapter extends BaseQuickAdapter<MoreCheckBean, BaseViewHolder> {

        public QuYuAdapter(@LayoutRes int layoutResId, @Nullable List<MoreCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, MoreCheckBean item) {
            helper.setText(R.id.rb_title, item.getName());
            helper.setChecked(R.id.rb_title, item.isChecked());
            helper.setVisible(R.id.img_isCheck, false);
            helper.getView(R.id.rb_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapterPosition2 == helper.getAdapterPosition()) {
                        return;
                    }
                    quYuAdapter.notifyItemChanged(adapterPosition2);
                    adapterPosition2 = helper.getAdapterPosition();
                    for (int i = 0; i < mQuyuListBean.size(); i++) {
                        if (adapterPosition2 == i) {
                            mQuyuListBean.get(i).setChecked(true);
                        } else {
                            mQuyuListBean.get(i).setChecked(false);
                        }
                    }
                    setFirstCheckedItem(mQuyuListBean, adapterPosition2);
                    quYuAdapter.notifyItemChanged(adapterPosition2);
                    initData();
                }

            });
        }
    }

    private List<String> getAllCheckedItem(int adapterPosition2) {
        List<String> list = new ArrayList<>();
        if (adapterPosition == 0) {
            if (mQuyuListBean != null && mQuyuListBean.size() > 0) {
                if (adapterPosition2 == 0) {
                    return list;
                }
                List<OneCheckBean> checkBeanList = mQuyuListBean.get(adapterPosition2).getCheckBeanList();
                if (checkBeanList != null && checkBeanList.size() > 0) {
                    for (int i1 = 0; i1 < checkBeanList.size(); i1++) {
                        if (checkBeanList.get(0).isChecked()) {
                            list.add(checkBeanList.get(i1).getId() + "");
                        } else if (i1 != 0 && checkBeanList.get(i1).isChecked()) {
                            list.add(checkBeanList.get(i1).getId() + "");
                        }
                    }
                }
            }
        } else {
            if (mDitieListBean != null && mDitieListBean.size() > 0) {
                if (adapterPosition2 == 0) {
                    return list;
                }
                List<OneCheckBean> checkBeanList = mDitieListBean.get(adapterPosition2).getCheckBeanList();
                if (checkBeanList != null && checkBeanList.size() > 0) {
                    for (int i1 = 0; i1 < checkBeanList.size(); i1++) {
                        if (checkBeanList.get(0).isChecked()) {
                            list.add(checkBeanList.get(i1).getId() + "");
                        } else if (i1 != 0 && checkBeanList.get(i1).isChecked()) {
                            list.add(checkBeanList.get(i1).getId() + "");
                        }
                    }
                }
            }
        }
        return list;
    }


    private void reset() {
        dropDownMenu.setTabText(context.getResources().getString(R.string.quyu));
        adapterPosition = 0;
        adapterPosition2 = 0;
        setFirstChecked(mQuyuListBean);
        setFirstChecked(mDitieListBean);
        oneAdapter.notifyDataSetChanged();
        if (quYuAdapter != null) {
            quYuAdapter.notifyDataSetChanged();
        }
        if (diTieAdapter != null) {
            diTieAdapter.notifyDataSetChanged();
        }
        initData();
    }

    private List<String> isCheckedItemOne(int adapterPosition2) {
        List<String> list = new ArrayList<>();
        if (adapterPosition == 0) {
            if (mQuyuListBean != null && mQuyuListBean.size() > 0) {
                if (adapterPosition2 == 0) {
                    return list;
                }
                List<OneCheckBean> checkBeanList = mQuyuListBean.get(adapterPosition2).getCheckBeanList();
                if (checkBeanList != null && checkBeanList.size() > 0) {
                    for (int i1 = 0; i1 < checkBeanList.size(); i1++) {
                        if (checkBeanList.get(0).isChecked()) {
                            list.add(checkBeanList.get(i1).getName() + "");
                        } else if (i1 != 0 && checkBeanList.get(i1).isChecked()) {
                            list.add(checkBeanList.get(i1).getName() + "");
                        }
                    }
                }
            }
        } else {
            if (mDitieListBean != null && mDitieListBean.size() > 0) {
                if (adapterPosition2 == 0) {
                    return list;
                }
                List<OneCheckBean> checkBeanList = mDitieListBean.get(adapterPosition2).getCheckBeanList();
                if (checkBeanList != null && checkBeanList.size() > 0) {
                    for (int i1 = 0; i1 < checkBeanList.size(); i1++) {
                        if (checkBeanList.get(0).isChecked()) {
                            list.add(checkBeanList.get(i1).getName() + "");
                        } else if (i1 != 0 && checkBeanList.get(i1).isChecked()) {
                            list.add(checkBeanList.get(i1).getName() + "");
                        }
                    }
                }
            }
        }
        return list;
    }

    private void setFirstChecked(List<MoreCheckBean> list) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    list.get(i).setChecked(true);
                } else {
                    list.get(i).setChecked(false);
                }
                List<OneCheckBean> checkBeanList = list.get(i).getCheckBeanList();
                if (checkBeanList != null && checkBeanList.size() > 0) {
                    for (int i1 = 0; i1 < checkBeanList.size(); i1++) {
                        OneCheckBean oneCheckBean = checkBeanList.get(i1);
                        if (i1 == 0) {
                            oneCheckBean.setChecked(true);
                        } else {
                            oneCheckBean.setChecked(false);
                        }
                    }
                }
            }
        }
    }

    /*点击朝阳区的时候清除其他区域所有item的选中状态*/
    private void setFirstCheckedItem(List<MoreCheckBean> list, int adapterPosition) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                List<OneCheckBean> checkBeanList = list.get(i).getCheckBeanList();
                if (checkBeanList != null && checkBeanList.size() > 0) {
                    if (i != adapterPosition) {
                        for (int i1 = 0; i1 < checkBeanList.size(); i1++) {
                            OneCheckBean oneCheckBean = checkBeanList.get(i1);
                            if (i1 == 0) {
                                oneCheckBean.setChecked(true);
                            } else {
                                oneCheckBean.setChecked(false);
                            }
                        }
                    }
                }
            }
        }
    }

    class DiTieAdapter extends BaseQuickAdapter<MoreCheckBean, BaseViewHolder> {

        public DiTieAdapter(@LayoutRes int layoutResId, @Nullable List<MoreCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, MoreCheckBean item) {
            helper.setText(R.id.rb_title, item.getName());
            helper.setChecked(R.id.rb_title, item.isChecked());
            helper.setVisible(R.id.img_isCheck, false);
            helper.getView(R.id.rb_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapterPosition2 == helper.getAdapterPosition()) {
                        return;
                    }
                    diTieAdapter.notifyItemChanged(adapterPosition2);
                    adapterPosition2 = helper.getAdapterPosition();
                    for (int i = 0; i < mDitieListBean.size(); i++) {
                        if (adapterPosition2 == i) {
                            mDitieListBean.get(i).setChecked(true);
                        } else {
                            mDitieListBean.get(i).setChecked(false);
                        }
                    }
                    setFirstCheckedItem(mDitieListBean, adapterPosition2);
                    diTieAdapter.notifyItemChanged(adapterPosition2);
                    initData();
                }
            });
        }
    }


    class ThreeAdapter extends BaseQuickAdapter<OneCheckBean, BaseViewHolder> {

        public ThreeAdapter(@LayoutRes int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, OneCheckBean item) {
            helper.setText(R.id.rb_title, item.getName());
            helper.setChecked(R.id.rb_title, item.isChecked());
            helper.setVisible(R.id.img_isCheck, item.isChecked());
            final int adapterPosition = helper.getAdapterPosition();
            helper.getView(R.id.rb_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapterPosition == 0) {
                        for (int i = 0; i < mList3.size(); i++) {
                            if (i == 0) {
                                mList3.get(0).setChecked(true);
                            } else {
                                mList3.get(i).setChecked(false);
                            }
                        }
                    } else {
                        mList3.get(adapterPosition).setChecked(!mList3.get(adapterPosition).isChecked());
                        if (getList3Checked()) {
                            mList3.get(0).setChecked(false);
                        } else {
                            mList3.get(0).setChecked(true);
                        }
                    }
                    threeAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    private boolean getList3Checked() {
        for (int i = 1; i < mList3.size(); i++) {
            if (mList3.get(i).isChecked()) {
                return true;
            }
        }
        return false;
    }
}
