package com.haiwai.administrator.japanhouse.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.adapter.CollectionListAdapter;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.GaoerfuDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.HaiWaiDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.JiudianDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.NewHousedetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.OldHousedetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.ShangpuDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.TudidetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.XiaoQuDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.XiezilouDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.ZhongguoDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.ZuHousedetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.home.BieshudetailsActivity;
import com.haiwai.administrator.japanhouse.login.LoginActivity;
import com.haiwai.administrator.japanhouse.more.CollectionListBean;
import com.haiwai.administrator.japanhouse.presenter.CollectionPresenter;
import com.haiwai.administrator.japanhouse.utils.TUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.model.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShouCangActivity extends BaseActivity implements CollectionPresenter.CollectionCallBack, CollectionListAdapter.OnItemClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.mrecycler)
    SwipeMenuRecyclerView mrecycler;
    private List<CollectionListBean.DatasBean> mList = new ArrayList();
    private CollectionListAdapter liebiaoAdapter;

    private CollectionPresenter presenter;
    private SpringView springView;
    private int page = 1;

    private TextView state;
    private boolean isRefresh = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_cang);
        ButterKnife.bind(this);

        presenter = new CollectionPresenter(this, this);
        presenter.getCollectionHouseList(page, MyApplication.getUserToken());

        state = (TextView) findViewById(R.id.no_more_data);

        springView = (SpringView) findViewById(R.id.act_collection_springView);

        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        mList.clear();
                        page = 1;
                        presenter.getCollectionHouseList(page, MyApplication.getUserToken());
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = false;
                        page++;
                        presenter.getCollectionHouseList(page, MyApplication.getUserToken());
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }
        });
        springView.setFooter(new DefaultFooter(this));
        springView.setHeader(new DefaultHeader(this));

        initData();
    }

    private void initData() {

        liebiaoAdapter = new CollectionListAdapter(this, mList);
        liebiaoAdapter.setOnItemClickListener(this);
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mrecycler.setAdapter(liebiaoAdapter);

    }

    @Override
    public void getCollectionHouseList(Response<CollectionListBean> response) {
        if (TextUtils.equals(response.body().getCode(), "201")) {
            startActivity(new Intent(this, LoginActivity.class));
            MyApplication.logOut();
            return;
        }

        if (isRefresh) {
            TUtils.showFail(this, getString(R.string.refresh_success));
        }
        state.setText(getString(R.string.no_more_collection_data));
        if (response != null && response.body() != null && response.body().getDatas() != null) {
            if (page == 1) {
                if (response.body().getDatas().size() > 0) {
                    state.setVisibility(View.GONE);
                } else {
                    state.setVisibility(View.VISIBLE);
                }
            }

            if (response.body().getDatas().size() > 0) {
                mList.addAll(response.body().getDatas());
            } else {
                page--;
                if (!isRefresh) {
                    TUtils.showFail(this, getString(R.string.refresh_no_data));
                }
            }

            liebiaoAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void collectionNetwork() {
        TUtils.showFail(this, getString(R.string.refresh_fail));
        if (!MyApplication.isNetworkAvailable()) {
            state.setVisibility(View.VISIBLE);
            state.setText(getString(R.string.no_network));
        }
    }

    @Override
    public void itemClickListener(String hType, String ShType, int houseId) {
        if (!TextUtils.isEmpty(hType)) {
            if (TextUtils.equals(hType, "0")) { //二手房
                Intent intent = new Intent(this, OldHousedetailsActivity.class);
                intent.putExtra("houseId", houseId + "");
                startActivity(intent);
            } else if (TextUtils.equals(hType, "1")) { //新房
                Intent intent = new Intent(this, NewHousedetailsActivity.class);
                intent.putExtra("houseId", houseId + "");
                startActivity(intent);
            } else if (TextUtils.equals(hType, "2")) { //租房
                Intent intent = new Intent(this, ZuHousedetailsActivity.class);

                if (TextUtils.equals(ShType, "0")) { //办公室出租
                    intent.putExtra("houseId", houseId + "");
                    intent.putExtra("houseType", "bangongshi");
                } else if (TextUtils.equals(ShType, "1")) { //商铺出租
                    intent.putExtra("houseId", houseId + "");
                    intent.putExtra("houseType", "shangpu");
                } else if (TextUtils.equals(ShType, "2")) { //别墅
                    intent.putExtra("houseId", houseId + "");
                    intent.putExtra("houseType", "bieshu");
                } else if (TextUtils.equals(ShType, "3")) { //二层公寓
                    intent.putExtra("houseId", houseId + "");
                    intent.putExtra("houseType", "erceng");
                } else if (TextUtils.equals(ShType, "4")) { //学生公寓
                    intent.putExtra("houseId", houseId + "");
                    intent.putExtra("houseType", "xuesheng");
                } else if (TextUtils.equals(ShType, "5")) { //多层公寓
                    intent.putExtra("houseId", houseId + "");
                    intent.putExtra("houseType", "duoceng");
                }
                startActivity(intent);
            } else if (TextUtils.equals(hType, "3")) {  //土地
                Intent intent = new Intent(this, TudidetailsActivity.class);
                intent.putExtra("houseId", houseId + "");
                startActivity(intent);
            } else if (TextUtils.equals(hType, "4")) {  //别墅
                Intent intent = new Intent(this, BieshudetailsActivity.class);
                intent.putExtra("houseId", houseId + "");
                startActivity(intent);
            } else if (TextUtils.equals(hType, "5")) {  //商业地产
                if (TextUtils.equals(ShType, "0")) {    //酒店
                    Intent intent = new Intent(this, JiudianDetailsActivity.class);
                    intent.putExtra("houseId", houseId + "");
                    startActivity(intent);
                } else if (TextUtils.equals(ShType, "1")) { //高尔夫球场
                    Intent intent = new Intent(this, GaoerfuDetailsActivity.class);
                    intent.putExtra("houseId", houseId + "");
                    startActivity(intent);
                } else if (TextUtils.equals(ShType, "2")) { //写字楼
                    Intent intent = new Intent(this, XiezilouDetailsActivity.class);
                    intent.putExtra("houseId", houseId + "");
                    startActivity(intent);
                } else if (TextUtils.equals(ShType, "3")) { //商铺
                    Intent intent = new Intent(this, ShangpuDetailsActivity.class);
                    intent.putExtra("houseId", houseId + "");
                    startActivity(intent);
                }
            } else if (TextUtils.equals(hType, "6")) { //中国房源
                Intent intent = new Intent(this, ZhongguoDetailsActivity.class);
                intent.putExtra("houseId", houseId + "");
                startActivity(intent);
            } else if (TextUtils.equals(hType, "7")) {  //海外房源
                Intent intent = new Intent(this, HaiWaiDetailsActivity.class);
                intent.putExtra("houseId", houseId + "");
                startActivity(intent);
            } else if (TextUtils.equals(hType, "8")) {  //找团地
                Intent intent = new Intent(this, XiaoQuDetailsActivity.class);
                intent.putExtra("houseId", houseId + "");
                startActivity(intent);
            }

        }
    }

    @Override
    public void itemDeleteClickListener(int position, CollectionListBean.DatasBean datasBean) {
        presenter.deteleCollectionHouse(datasBean.getHType(), datasBean.getShType(), datasBean.getId());
        mList.remove(position);
        liebiaoAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.back_img)
    public void onClick() {
        finish();
    }
}
