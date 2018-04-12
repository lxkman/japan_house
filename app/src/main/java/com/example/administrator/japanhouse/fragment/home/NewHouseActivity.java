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
import com.example.administrator.japanhouse.fragment.comment.NewHousedetailsActivity;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewHouseActivity extends BaseActivity implements MyItemClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.img_dingwei)
    ImageView imgDingwei;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    private String headers[] = {"售价", "楼层","建筑年份","更多"};
    private List<View> popupViews = new ArrayList<>();
    private RecyclerView mrecycler;
    private List<String> mList=new ArrayList();
    private LiebiaoAdapter liebiaoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_house);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {

        /**
         * 第一个界面
         * */
        FirstView firstView = new FirstView(NewHouseActivity.this);
        firstView.setListener(this);
        popupViews.add(firstView.firstView());
        /**
         * 第二个界面
         * */
        SecView secView = new SecView(NewHouseActivity.this);
        secView.setListener(this);
        popupViews.add(secView.secView());

        /**
         * 第三个界面
         * */
        FirstView threeView = new FirstView(NewHouseActivity.this);
        threeView.setListener(this);
        popupViews.add(threeView.firstView());
        /**
         * 第四个界面
         * */
        SecView fourView = new SecView(NewHouseActivity.this);
        fourView.setListener(this);
        popupViews.add(fourView.secView());
        /**
         * Dropdownmenu下面的主体部分
         * */
        View fifthView = LayoutInflater.from(NewHouseActivity.this).inflate(R.layout.activity_main_view, null);
        mrecycler = (RecyclerView) fifthView.findViewById(R.id.mrecycler);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, fifthView);
        initData();
    }

    private void initData() {
        if (mList.size()<=0){
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
        }
        liebiaoAdapter = new LiebiaoAdapter(R.layout.item_zuijin,mList);
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mrecycler.setAdapter(liebiaoAdapter);
        liebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(NewHouseActivity.this, NewHousedetailsActivity.class));
            }
        });

    }
    class LiebiaoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId,data);
        }

        @Override
        protected void convert(BaseViewHolder helper,String item) {

        }
    }
    @OnClick({R.id.back_img, R.id.img_dingwei, R.id.img_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            //地图
            case R.id.img_dingwei:
                break;
            //消息
            case R.id.img_message:
                break;
        }
    }

    @Override
    public void onItemClick(View view, int postion, String string) {

    }
}
