package com.example.administrator.japanhouse.fragment.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.activity.adapter.LinkmanAdapter;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.fragment.chat.SearchManagerActivity;
import com.example.administrator.japanhouse.fragment.comment.GaoerfuDetailsActivity;
import com.example.administrator.japanhouse.model.LinkmanBean;
import com.example.administrator.japanhouse.presenter.LinkmanPresenter;
import com.lzy.okgo.model.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

public class LianxirenActivity extends BaseActivity implements LinkmanPresenter.LinkmanCallBack, LinkmanAdapter.OnItemClickListener {

    @BindView(R.id.act_linkman_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.tv_add_people)
    TextView tvAddPeople;

    private LinkmanPresenter presenter;
    private List<Object> mList = new ArrayList<>();
    private LinkmanAdapter adapter;

    private TextView state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lianxiren);
        ButterKnife.bind(this);

        presenter = new LinkmanPresenter(this, this);
        presenter.getLinkmanList();

        state = (TextView) findViewById(R.id.no_more_data);
        state.setText(getString(R.string.no_more_linkman));
        initData();
    }

    private void initData() {
        adapter = new LinkmanAdapter(mList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @OnClick({R.id.back_img, R.id.tv_add_people})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            case R.id.tv_add_people:
                startActivity(new Intent(LianxirenActivity.this, SearchManagerActivity.class));
                break;
        }
    }

    @Override
    public void getLinkmanList(Response<LinkmanBean> response) {
        mList.clear();

        if (response != null && response.body() != null && response.body().getDatas() != null) {
            if (response.body().getDatas().size() > 0) {
                for (int i = 0; i < response.body().getDatas().size(); i++) {
                    if (response.body().getDatas().get(i).getList() != null && response.body().getDatas().get(i).getList().size() > 0) {
                        mList.add(response.body().getDatas().get(i));
                        for (int j = 0; j < response.body().getDatas().get(i).getList().size(); j++) {
                            mList.add(response.body().getDatas().get(i).getList().get(j));
                        }
                    }
                }
            }

            adapter.notifyDataSetChanged();
        }

        if (mList.size() > 0) {
            state.setVisibility(View.GONE);
        } else {
            state.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getLinkmanListFail() {
        if (!MyApplication.isNetworkAvailable()) {
            state.setVisibility(View.VISIBLE);
            state.setText(getString(R.string.no_network));
        }
    }

    @Override
    public void onItemClickListener(LinkmanBean.DatasBean.ListBean bean) {
        if (RongIM.getInstance() != null) {
            Log.e("MainActivity", "创建单聊");
            RongIM.getInstance().startPrivateChat(this, bean.getId() + "", bean.getBrokerName());
        }
    }

    @Override
    public void onItemDeleteListener(int position, String userId) {
        mList.remove(position);
        adapter.notifyDataSetChanged();
        presenter.deteleLinkman(userId);
    }
}
