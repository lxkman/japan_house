package com.example.administrator.japanhouse.fragment.home;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.example.administrator.japanhouse.utils.TUtils;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.taobao.accs.ACCSManager.mContext;

class ThreeView implements View.OnClickListener {

    private Context context;
    private MyItemClickListener listener;
    private RecyclerView mrecycler;
    private LiebiaoAdapter mLiebiaoAdapter;
    private List<OneCheckBean> mList = new ArrayList();
    private Button btn_sure;
    private DropDownMenu dropDownMenu;
    private LinearLayout ll_root;
    private EditText et_zuidijia, et_zuigaojia;
    private TextView tv_wanoryuan;
    private boolean isYuan;

    ThreeView(Context context) {
        this.context = context;
    }

    void setListener(MyItemClickListener listener) {
        this.listener = listener;
    }

    View firstView() {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_three, null);
        ll_root = (LinearLayout) view.findViewById(R.id.ll_root);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll_root.getLayoutParams();
        layoutParams.height = MyUtils.getScreenHeight(context) / 2;
        ll_root.setLayoutParams(layoutParams);
        mrecycler = (RecyclerView) view.findViewById(R.id.Mrecycler);
        btn_sure = (Button) view.findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(this);
        et_zuidijia = (EditText) view.findViewById(R.id.et_zuidijia);
        et_zuigaojia = (EditText) view.findViewById(R.id.et_zuigaojia);
        tv_wanoryuan = (TextView) view.findViewById(R.id.tv_wanoryuan);
        return view;
    }

    void insertData(List<OneCheckBean> list, DropDownMenu dropDownMenu) {
        mList = list;
        this.dropDownMenu = dropDownMenu;
        initData();
    }

    void insertData2(List<OneCheckBean> list, DropDownMenu dropDownMenu, boolean isyuan) {
        isYuan = isyuan;
        tv_wanoryuan.setText(isYuan ? "元" : "万");
        mList = list;
        this.dropDownMenu = dropDownMenu;
        initData();
    }

    private void initData() {
        if (mLiebiaoAdapter == null) {
            mLiebiaoAdapter = new LiebiaoAdapter(R.layout.leixing_item, mList);
        }
        mrecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setAdapter(mLiebiaoAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure:
                if (!getCheckeditemText().equals("")) {
                    dropDownMenu.setTabText(getCheckeditemText());
                }
                if (TextUtils.isEmpty(et_zuidijia.getText().toString())
                        && TextUtils.isEmpty(et_zuigaojia.getText().toString())) {
                    return;
                }
                List<String> price = new ArrayList<>();
                String zuidijia = et_zuidijia.getText().toString();
                String zuigaojia = et_zuigaojia.getText().toString();
                if (isYuan) {
                    price.add(zuidijia);
                    price.add(zuigaojia);
                } else {
                    try {
                        price.add(Integer.parseInt(zuidijia) * 10000 + "");
                        price.add(Integer.parseInt(zuigaojia) * 10000 + "");
                    } catch (NumberFormatException e) {
                        TUtils.showFail(context,context.getResources().getString(R.string.qingshurushuzi));
                        return;
                    }
                }
                dropDownMenu.closeMenu();//这个要放在最后，不然文字不会改变
                if (listener != null) {
                    listener.onItemClick(v, 4, price);
                }
                break;
        }
    }

    class LiebiaoAdapter extends BaseQuickAdapter<OneCheckBean, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final OneCheckBean item) {
            helper.setText(R.id.rb_title, item.getName());
            helper.setChecked(R.id.rb_title, item.isChecked());
            helper.setVisible(R.id.img_isCheck, item.isChecked());
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
                    int position = helper.getAdapterPosition();//notify之后再取这个值就变成-1了，不知为何
                    mLiebiaoAdapter.notifyDataSetChanged();
                    if (!getCheckeditemText().equals("")) {
                        dropDownMenu.setTabText(getCheckeditemText());
                    }
                    dropDownMenu.closeMenu();//这个要放在最后，不然文字不会改变
                    if (listener != null) {
                        listener.onItemClick(v, 3, position);
                    }
                    et_zuidijia.setText("");
                    et_zuigaojia.setText("");
                }

            });
        }
    }

    private String getCheckeditemText() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).isChecked()) {
                return mList.get(i).getName();
            }
        }
        return "";
    }
}
