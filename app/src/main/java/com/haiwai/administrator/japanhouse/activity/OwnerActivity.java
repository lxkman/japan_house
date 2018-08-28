package com.haiwai.administrator.japanhouse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.adapter.OwnerAdapter;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.bean.EventBean;
import com.haiwai.administrator.japanhouse.fragment.home.FangjiadituActivity;
import com.haiwai.administrator.japanhouse.login.LoginActivity;
import com.haiwai.administrator.japanhouse.model.OwnerDetailsBean;
import com.haiwai.administrator.japanhouse.model.OwnerListBean;
import com.haiwai.administrator.japanhouse.presenter.OwnerPresenter;
import com.haiwai.administrator.japanhouse.utils.Constants;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我是业主
 * <p>
 * Created by   admin on 2018/4/16.
 */

public class OwnerActivity extends BaseActivity implements OwnerAdapter.onClickItemListener, OwnerPresenter.OwnerCallBack {


    @BindView(R.id.act_owner_back)
    ImageView back;
    @BindView(R.id.act_owner_message)
    ImageView ivMessage;
    @BindView(R.id.act_owner_rental)
    RelativeLayout tvRental;
    @BindView(R.id.act_owner_wikipedia)
    RelativeLayout tvWikipedia;
    @BindView(R.id.act_owner_prices)
    RelativeLayout tvPrices;
    @BindView(R.id.act_owner_recyclerView)
    RecyclerView recyclerview;
    private OwnerAdapter ownerAdapter;
    private OwnerPresenter ownerPresenter;
    private List<OwnerListBean.DatasBean> datas;

    private NestedScrollView mScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        mScrollView = (NestedScrollView) findViewById(R.id.act_owner_scrollView);

        ButterKnife.bind(this);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(manager);
        recyclerview.setNestedScrollingEnabled(false);
        datas = new ArrayList<>();
        ownerAdapter = new OwnerAdapter(this, datas);
        ownerAdapter.setOnClickItemListener(this);
        recyclerview.setAdapter(ownerAdapter);

        ownerPresenter = new OwnerPresenter(this, this);
        ownerPresenter.getOwnerList(1);
    }


    public static void invoke(Context context) {
        Intent intent = new Intent(context, OwnerActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onItemClick(int itemId) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("title", getString(R.string.details));
        if (MyApplication.isJapanese()) {
            intent.putExtra("result", "http://www.flcjapan.com/hwdch5/info/paranText.html?id=" + itemId);
        } else {
            intent.putExtra("result", "http://www.flcjapan.com/hwdch5/info/paranTextCn.html?id=" + itemId);
        }

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == 3) {
            setResult(4);
            finish();
        }
    }

    @OnClick({R.id.act_owner_back, R.id.act_owner_message, R.id.act_owner_rental, R.id.act_owner_wikipedia, R.id.act_owner_prices})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_owner_back:
                finish();
                break;

            case R.id.act_owner_message:
                EventBus.getDefault().post(new EventBean(Constants.EVENT_CHAT));
                finish();
                break;

            case R.id.act_owner_rental:
                if (!MyApplication.isLogin()) {
                    startActivity(new Intent(this, LoginActivity.class));
                    return;
                }

                Intent intent1 = new Intent(this, RentalActivity.class);
                startActivityForResult(intent1, 10);
                break;

            case R.id.act_owner_prices:
                //跳转房价地图
                startActivity(new Intent(this, FangjiadituActivity.class));
                break;

            case R.id.act_owner_wikipedia:
                OwnerWikipediaActivity.invoke(this);
                break;
        }
    }

    @Override
    public void getOwnerList(Response<OwnerListBean> response) {
        if (response != null && response.body() != null && response.body().getDatas() != null) {
            if (response.body().getDatas().size() > 0) {
                datas.addAll(response.body().getDatas());
            }
            ownerAdapter.notifyDataSetChanged();
            mScrollView.scrollTo(0, 0);
        }
    }

    @Override
    public void getOwnerDetails(Response<OwnerDetailsBean> response) {

    }

    @Override
    public void ownerListNetwork() {

    }
}
