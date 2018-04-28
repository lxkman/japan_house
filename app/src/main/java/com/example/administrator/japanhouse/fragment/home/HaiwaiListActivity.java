package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.example.administrator.japanhouse.fragment.comment.HaiWaiDetailsActivity;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HaiwaiListActivity extends BaseActivity implements MyItemClickListener{

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    private String headers[] = {"城市", "价格", "建筑构造", "房型"};
    private List<View> popupViews = new ArrayList<>();
    private RecyclerView mrecycler;
    private List<String> mList = new ArrayList();
    private LiebiaoAdapter liebiaoAdapter;
    private List<OneCheckBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haiwai_list);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        /**
         * 第一个界面
         * */
        list = new ArrayList<>();
        list.add(new OneCheckBean(false, "不限"));
        list.add(new OneCheckBean(false, "奥克兰"));
        list.add(new OneCheckBean(false, "皇后镇"));
        list.add(new OneCheckBean(false, "基督城"));
        list.add(new OneCheckBean(false, "曼努考"));
        list.add(new OneCheckBean(false, "威灵顿"));
        SecView firstView = new SecView(HaiwaiListActivity.this);
        popupViews.add(firstView.secView());
        firstView.insertData(list, dropDownMenu);
        firstView.setListener(this);

        /**
         * 第二个界面
         * */
        List<OneCheckBean> list1 = new ArrayList<>();
        list1.add(new OneCheckBean(false, "不限"));
        list1.add(new OneCheckBean(false, "3-10万"));
        list1.add(new OneCheckBean(false, "6-15万"));
        list1.add(new OneCheckBean(false, "10万以上"));
        SecView secView = new SecView(HaiwaiListActivity.this);
        popupViews.add(secView.secView());
        secView.setListener(this);
        secView.insertData(list1, dropDownMenu);

        /**
         * 第三个界面
         * */
        List<OneCheckBean> list2 = new ArrayList<>();
        list2.add(new OneCheckBean(false, "不限"));
        list2.add(new OneCheckBean(false, "钢结构"));
        list2.add(new OneCheckBean(false, "框架结构"));
        list2.add(new OneCheckBean(false, "砖混结构"));
        list2.add(new OneCheckBean(false, "木结构"));
        list2.add(new OneCheckBean(false, "钢型-混凝土结构"));
        SecView threeView = new SecView(HaiwaiListActivity.this);
        popupViews.add(threeView.secView());
        threeView.insertData(list2, dropDownMenu);
        threeView.setListener(this);
        /**
         * 第四个界面
         * */
        List<OneCheckBean> list3 = new ArrayList<>();
        list3.add(new OneCheckBean(false, "不限"));
        list3.add(new OneCheckBean(false, "1室1厅"));
        list3.add(new OneCheckBean(false, "2室1厅"));
        list3.add(new OneCheckBean(false, "3室1厅"));
        list3.add(new OneCheckBean(false, "4室1厅"));
        list3.add(new OneCheckBean(false, "单间"));
        SecView fourView = new SecView(HaiwaiListActivity.this);
        popupViews.add(fourView.secView());
        fourView.insertData(list3, dropDownMenu);
        fourView.setListener(this);

        /**
         * Dropdownmenu下面的主体部分
         * */
        View fifthView = LayoutInflater.from(HaiwaiListActivity.this).inflate(R.layout.activity_main_view, null);
        mrecycler = (RecyclerView) fifthView.findViewById(R.id.mrecycler);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, fifthView);

        initData();
    }

    private void initData() {
        final String type = getIntent().getStringExtra("type");

        if (mList.size() <= 0) {
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
        }
        liebiaoAdapter = new LiebiaoAdapter(R.layout.item_haiwai_layout, mList);
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mrecycler.setAdapter(liebiaoAdapter);
        liebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, HaiWaiDetailsActivity.class));
                /*switch (type){
                    case "0":
                        startActivity(new Intent(mContext, ShangpuDetailsActivity.class));
                        break;
                    case "1":
                        startActivity(new Intent(mContext, ShangpuDetailsActivity.class));
                        break;
                    case "2":
                        startActivity(new Intent(mContext, XiezilouDetailsActivity.class));
                        break;
                    case "3":
                        startActivity(new Intent(mContext, ShangpuDetailsActivity.class));
                        break;
                    case "4":
                        startActivity(new Intent(mContext, GaoerfuDetailsActivity.class));
                        break;
                    case "5":
                        startActivity(new Intent(mContext, JiudianDetailsActivity.class));
                        break;
                }*/
            }
        });

    }

    class LiebiaoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    @OnClick({R.id.back_img, R.id.img_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            //消息
            case R.id.img_message:
                removeAllActivitys();
                MyUtils.startMain(this);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int postion, String string) {

    }
}
