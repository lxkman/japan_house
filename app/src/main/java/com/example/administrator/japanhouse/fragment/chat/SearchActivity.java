package com.example.administrator.japanhouse.fragment.chat;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.adapter.SearchListAdapter;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.model.ChatSearchBean;
import com.example.administrator.japanhouse.presenter.CharSearchPresenter;
import com.example.administrator.japanhouse.utils.SoftKeyboardTool;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements CharSearchPresenter.ChatSearchCallBack {

    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.search_list_recycler)
    RecyclerView searchListRecycler;
    @BindView(R.id.act_search_manager)
    EditText actSearchManager;
    @BindView(R.id.tv_noContent)
    TextView tvNoContent;
    private List<ChatSearchBean.DatasBean> mList = new ArrayList();
    private SearchListAdapter adapter;

    private int page = 1;

    private CharSearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        presenter = new CharSearchPresenter(this, this);

        searchListRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new SearchListAdapter(this, mList);
        searchListRecycler.setAdapter(adapter);

        actSearchManager.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0) {
                    mList.clear();
                    adapter.setSearchContent(actSearchManager.getText().toString());
                    presenter.getSearchList(page, actSearchManager.getText().toString());
                } else {
                    tvNoContent.setVisibility(View.GONE);
                    searchListRecycler.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                SoftKeyboardTool.closeKeyboard(this);
                finish();
                break;
        }
    }

    @Override
    public void getSearchList(Response<ChatSearchBean> response) {
        if (response != null && response.body() != null && response.body().getDatas() != null) {
            if (response.body().getDatas().size() > 0) {
                mList.addAll(response.body().getDatas());
                searchListRecycler.setVisibility(View.VISIBLE);
                tvNoContent.setVisibility(View.GONE);
            } else {
                tvNoContent.setVisibility(View.VISIBLE);
                searchListRecycler.setVisibility(View.GONE);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
