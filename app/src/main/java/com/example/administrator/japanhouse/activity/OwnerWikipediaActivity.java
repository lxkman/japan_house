package com.example.administrator.japanhouse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.adapter.OwnerWikipediaAdapter;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.model.OwnerDetailsBean;
import com.example.administrator.japanhouse.model.OwnerListBean;
import com.example.administrator.japanhouse.presenter.OwnerPresenter;
import com.example.administrator.japanhouse.utils.TUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *  业主百科
 * Created by   admin on 2018/4/16.
 */

public class OwnerWikipediaActivity extends BaseActivity implements OwnerWikipediaAdapter.onItemClickListener, OwnerPresenter.OwnerCallBack{

    private RecyclerView mRecyclerView;
    private OwnerPresenter presenter;
    private SpringView springView;
    private int pageNo = 1;
    private List<OwnerListBean.DatasBean> datas;
    private OwnerWikipediaAdapter adapter;

    private TextView state;
    private boolean isRefresh = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_wikipedia);

        state = (TextView) findViewById(R.id.no_more_data);

        mRecyclerView = (RecyclerView) findViewById(R.id.act_owner_wikipedia_recyclerView);
        springView = (SpringView) findViewById(R.id.act_owner_wikipedia_springView);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        datas = new ArrayList<>();
        adapter = new OwnerWikipediaAdapter(this, datas);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView = (RecyclerView) findViewById(R.id.act_owner_wikipedia_recyclerView);
        findViewById(R.id.act_owner_wikipedia_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        datas.clear();
                        pageNo = 1;
                        presenter.getOwnerList(pageNo);
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
                        pageNo++;
                        presenter.getOwnerList(pageNo);
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }
        });
        springView.setFooter(new DefaultFooter(this));
        springView.setHeader(new DefaultHeader(this));

        presenter = new OwnerPresenter(this, this);
        presenter.getOwnerList(pageNo);
    }

    public static void invoke(Context context){
        Intent intent = new Intent(context, OwnerWikipediaActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onItemClick(int itemId) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("title", getString(R.string.details));
        if (MyApplication.isJapanese()) {
            intent.putExtra("result", "http://39.106.131.61:8080/hwdch5/info/paranText.html?id=" + itemId);
        } else {
            intent.putExtra("result", "http://39.106.131.61:8080/hwdch5/info/paranTextCn.html?id=" + itemId);
        }

        startActivity(intent);
    }

    @Override
    public void getOwnerList(Response<OwnerListBean> response) {
        if (isRefresh) {
            TUtils.showFail(this, getString(R.string.refresh_success));
        }
        state.setText(getString(R.string.no_more_data));

        if (response != null && response.body() != null && response.body().getDatas() != null) {
            if (pageNo == 1) {
                if (response.body().getDatas().size() > 0) {
                    state.setVisibility(View.GONE);
                } else {
                    state.setVisibility(View.VISIBLE);
                }
            }

            if (response.body().getDatas().size() > 0) {
                datas.addAll(response.body().getDatas());
            } else {
                pageNo --;
                if (!isRefresh) {
                    TUtils.showFail(this, getString(R.string.refresh_no_data));
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getOwnerDetails(Response<OwnerDetailsBean> response) {

    }

    @Override
    public void ownerListNetwork() {
        TUtils.showFail(this, getString(R.string.refresh_fail));
        if (!MyApplication.isNetworkAvailable()) {
            state.setVisibility(View.VISIBLE);
            state.setText(getString(R.string.no_network));
        }
    }
}
