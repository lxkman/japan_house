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
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.example.administrator.japanhouse.fragment.comment.NewHousedetailsActivity;
import com.example.administrator.japanhouse.fragment.comment.XiaoQuDetailsActivity;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhaoxiaoquActivity extends BaseActivity implements MyItemClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.img_dingwei)
    ImageView imgDingwei;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    @BindView(R.id.search_tv)
    TextView searchTv;
    private String headers[] = {"价格", "房龄", "房型"};
    private List<View> popupViews = new ArrayList<>();
    private RecyclerView mrecycler;
    private List<String> mList = new ArrayList();
    private LiebiaoAdapter liebiaoAdapter;
    private List<OneCheckBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhaoxiaoqu);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        /**
         * 第一个界面
         * */
        list = new ArrayList<>();
        list.add(new OneCheckBean(false, "不限"));
        list.add(new OneCheckBean(false, "3-10万"));
        list.add(new OneCheckBean(false, "6-15万"));
        list.add(new OneCheckBean(false, "10万以上"));
        FirstView firstView = new FirstView(ZhaoxiaoquActivity.this);
        popupViews.add(firstView.firstView());
        firstView.insertData(list, dropDownMenu);
        firstView.setListener(this);

        /**
         * 第二个界面
         * */
        List<OneCheckBean> list1 = new ArrayList<>();
        list1.add(new OneCheckBean(false, "不限"));
        list1.add(new OneCheckBean(false, "10年"));
        list1.add(new OneCheckBean(false, "20年"));
        list1.add(new OneCheckBean(false, "30年"));
        list1.add(new OneCheckBean(false, "40年"));
        list1.add(new OneCheckBean(false, "50年"));
        SecView secView = new SecView(ZhaoxiaoquActivity.this);
        popupViews.add(secView.secView());
        secView.setListener(this);
        secView.insertData(list1, dropDownMenu);

        /**
         * 第三个界面
         * */
        List<OneCheckBean> list2 = new ArrayList<>();
        list2.add(new OneCheckBean(false, "不限"));
        list2.add(new OneCheckBean(false, "一室一厅"));
        list2.add(new OneCheckBean(false, "两室一厅"));
        list2.add(new OneCheckBean(false, "三室一厅"));
        list2.add(new OneCheckBean(false, "四室一厅"));
        list2.add(new OneCheckBean(false, "单间"));
        SecView threeView = new SecView(ZhaoxiaoquActivity.this);
        popupViews.add(threeView.secView());
        threeView.insertData(list2, dropDownMenu);
        threeView.setListener(this);

        /**
         * Dropdownmenu下面的主体部分
         * */
        View fifthView = LayoutInflater.from(ZhaoxiaoquActivity.this).inflate(R.layout.activity_main_view, null);
        mrecycler = (RecyclerView) fifthView.findViewById(R.id.mrecycler);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, fifthView);

        initData();
    }

    private void initData() {
        if (mList.size() <= 0) {
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
        }
        liebiaoAdapter = new LiebiaoAdapter(R.layout.item_home_zhaoxiaoqu, mList);
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mrecycler.setAdapter(liebiaoAdapter);
        liebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(ZhaoxiaoquActivity.this, XiaoQuDetailsActivity.class));
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

    @OnClick({R.id.back_img, R.id.img_dingwei, R.id.img_message, R.id.search_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            //地图
            case R.id.img_dingwei:
                startActivity(new Intent(mContext,HomeMapActivity.class));
                break;
            //消息
            case R.id.img_message:
                setResult(2);
                finish();
                break;
            case R.id.search_tv:
                startActivity(new Intent(mContext,HomeSearchActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(View view, int postion, String string) {

    }
}
