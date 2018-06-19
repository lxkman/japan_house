package com.example.administrator.japanhouse.fragment.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.activity.adapter.ChatLinkmanAdapter;
import com.example.administrator.japanhouse.activity.adapter.LinkmanAdapter;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.im.ImManager;
import com.example.administrator.japanhouse.login.LoginActivity;
import com.example.administrator.japanhouse.model.LinkmanBean;
import com.example.administrator.japanhouse.presenter.LinkmanPresenter;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

public class StartWechatActivity extends BaseActivity  implements LinkmanPresenter.LinkmanCallBack, ChatLinkmanAdapter.OnItemClickListener{

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.Mrecycler)
    RecyclerView mRecyclerView;

    private LinkmanPresenter presenter;
    private List<Object> mList = new ArrayList<>();
    private ChatLinkmanAdapter adapter;

    private TextView state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_wechat);
        ButterKnife.bind(this);
        presenter = new LinkmanPresenter(this, this);
        presenter.getLinkmanList();

        state = (TextView) findViewById(R.id.no_more_data);
        state.setText(getString(R.string.no_more_linkman));
        initData();
    }

    private void initData() {
        adapter = new ChatLinkmanAdapter(mList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @OnClick(R.id.back_img)
    public void onClick() {
        finish();
    }


    @Override
    public void getLinkmanList(Response<LinkmanBean> response) {
        if (TextUtils.equals(response.body().getCode(), "201")) {
            startActivity(new Intent(this, LoginActivity.class));
            MyApplication.logOut();
            return;
        }

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
        ImManager.enterChat(this, bean.getId() + "", bean.getBrokerName(), bean.getPic());
    }
}
