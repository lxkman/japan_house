package com.haiwai.administrator.japanhouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.activity.adapter.AgentListAdapter;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.login.LoginActivity;
import com.haiwai.administrator.japanhouse.model.AgentListBean;
import com.haiwai.administrator.japanhouse.presenter.AgentPresenter;
import com.haiwai.administrator.japanhouse.utils.CacheUtils;
import com.haiwai.administrator.japanhouse.utils.TUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by   admin on 2018/4/26.
 */

public class AgentListActivity extends BaseActivity implements AgentPresenter.AgentCallBack{

    private AgentListAdapter adapter;

    @BindView(R.id.act_agentList_back)
    ImageView back;
    @BindView(R.id.act_agentList_recyclerView)
    RecyclerView mRecyclerView;

    private AgentPresenter presenter;
    private SpringView springView;
    private int page = 1;

    private TextView state;

    private boolean isRefresh = true;

    private List<AgentListBean.DatasBean> mList;

    private int cityId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agentlist);
        ButterKnife.bind(this);

        cityId = CacheUtils.get("cityId");

        mList = new ArrayList<>();

        presenter = new AgentPresenter(this, this);

        presenter.getAgentList(cityId, page);
        state = (TextView) findViewById(R.id.no_more_data);

        springView = (SpringView) findViewById(R.id.act_singUp_springView);

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
                        presenter.getAgentList(cityId, page);
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
                        presenter.getAgentList(cityId, page);
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }
        });
        springView.setFooter(new DefaultFooter(this));
        springView.setHeader(new DefaultHeader(this));

        adapter = new AgentListAdapter(this, mList);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void getAgentList(Response<AgentListBean> response) {
        if (TextUtils.equals(response.body().getCode(), "201")) {
            startActivity(new Intent(this, LoginActivity.class));
            MyApplication.logOut();
            return;
        }

        if (isRefresh) {
            TUtils.showFail(this, getString(R.string.refresh_success));
        }
        state.setText(getString(R.string.no_more_data));
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

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getAgentListFail() {
        TUtils.showFail(this, getString(R.string.refresh_fail));
        if (!MyApplication.isNetworkAvailable()) {
            state.setVisibility(View.VISIBLE);
            state.setText(getString(R.string.no_network));
        }
    }
}
