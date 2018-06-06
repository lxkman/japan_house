package com.example.administrator.japanhouse.fragment.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.activity.adapter.SingUpAdapter;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.model.NoDataBean;
import com.example.administrator.japanhouse.model.SingUpBean;
import com.example.administrator.japanhouse.presenter.SingUpPresenter;
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

public class SingUpActivity extends BaseActivity implements SingUpPresenter.SingUpCallBack, SingUpAdapter.OnItemClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.mrecycler)
    SwipeMenuRecyclerView mrecycler;
    private List<SingUpBean.DatasBean> mList = new ArrayList();
    private SingUpAdapter adapter;

    private SingUpPresenter presenter;
    private SpringView springView;
    private int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        ButterKnife.bind(this);

        presenter = new SingUpPresenter(this, this);

        adapter = new SingUpAdapter(this, mList);
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mrecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        springView = (SpringView) findViewById(R.id.act_singUp_springView);

        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mList.clear();
                        page = 1;
                        presenter.getSingUpList(MyApplication.getUserToken(), page);
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        presenter.getSingUpList(MyApplication.getUserToken(), page);
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }
        });
        springView.setFooter(new DefaultFooter(this));
        springView.setHeader(new DefaultHeader(this));

        presenter.getSingUpList(MyApplication.getUserToken(), page);
    }

    @Override
    public void getSingUpList(Response<SingUpBean> response) {
        if (response != null && response.body() != null && response.body().getDatas() != null) {
            if (response.body().getDatas().size() > 0) {
                mList.addAll(response.body().getDatas());
            } else {
                page--;
            }

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void deteleSingUp(Response<NoDataBean> response) {

    }

    @Override
    public void onItemDeteleListener(int position, String sId) {
        mList.remove(position);
        adapter.notifyDataSetChanged();
        presenter.deteleSingUp(sId, MyApplication.getUserToken());
    }

    @Override
    public void onItemClickListener(SingUpBean.DatasBean bean) {
        Intent intent = new Intent(SingUpActivity.this, SingUpDetailsActivity.class);
        intent.putExtra("datas", bean);
        startActivity(intent);
    }

    @OnClick(R.id.back_img)
    public void onClick() {
        finish();
    }
}
