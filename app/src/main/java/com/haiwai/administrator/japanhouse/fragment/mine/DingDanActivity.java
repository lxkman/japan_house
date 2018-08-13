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
import com.haiwai.administrator.japanhouse.activity.adapter.OrderAdapter;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.login.LoginActivity;
import com.haiwai.administrator.japanhouse.model.OrderBean;
import com.haiwai.administrator.japanhouse.presenter.OrderPresenter;
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

public class DingDanActivity extends BaseActivity implements OrderPresenter.OrderCallBack, OrderAdapter.OnItemClickListener {
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.mrecycler)
    SwipeMenuRecyclerView mrecycler;
    private List<OrderBean.DatasBean> mList = new ArrayList();
    private OrderAdapter liebiaoAdapter;

    private OrderPresenter presenter;
    private int page = 1;
    private SpringView springView;

    private TextView state;
    private boolean isRefresh = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan);
        ButterKnife.bind(this);

        state = (TextView) findViewById(R.id.no_more_data);

        liebiaoAdapter = new OrderAdapter(this, mList);
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mrecycler.setAdapter(liebiaoAdapter);
        liebiaoAdapter.setOnItemClickListener(this);

        presenter = new OrderPresenter(this, this);
        presenter.getOrderList(MyApplication.getUserToken(), page);

        springView = (SpringView) findViewById(R.id.act_order_springView);

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
                        presenter.getOrderList(MyApplication.getUserToken(), page);
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
                        presenter.getOrderList(MyApplication.getUserToken(), page);
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }
        });
        springView.setFooter(new DefaultFooter(this));
        springView.setHeader(new DefaultHeader(this));

    }

    @Override
    public void onItemClickListener(OrderBean.DatasBean bean) {
        Intent intent = new Intent(DingDanActivity.this, DingDan_DetilsActivity.class);
        intent.putExtra("datas", bean);
        startActivity(intent);
    }

    @Override
    public void onItemDeteleListener(int position) {
        mList.remove(position);
        liebiaoAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.back_img)
    public void onClick() {
        finish();
    }

    @Override
    public void getOrderList(Response<OrderBean> response) {
        if (TextUtils.equals(response.body().getCode(), "201")) {
            startActivity(new Intent(this, LoginActivity.class));
            MyApplication.logOut();
        }

        if (isRefresh) {
            TUtils.showFail(this, getString(R.string.refresh_success));
        }
        state.setText(getString(R.string.no_more_order_data));
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
                page --;
                if (!isRefresh) {
                    TUtils.showFail(this, getString(R.string.refresh_no_data));
                }
            }

            liebiaoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void orderNetwork() {
        TUtils.showFail(this, getString(R.string.refresh_fail));
        if (!MyApplication.isNetworkAvailable()) {
            state.setVisibility(View.VISIBLE);
            state.setText(getString(R.string.no_network));
        }
    }
}
