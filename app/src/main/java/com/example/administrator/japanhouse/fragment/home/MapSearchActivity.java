package com.example.administrator.japanhouse.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.HomeSearchHistroyBean;
import com.example.administrator.japanhouse.bean.HotSearchBean;
import com.example.administrator.japanhouse.model.TopSearchHintBean;
import com.example.administrator.japanhouse.presenter.MainSearchPresenter;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapSearchActivity extends BaseActivity implements MainSearchPresenter.MainSearchCallBack, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.cancle_tv)
    TextView cancleTv;
    @BindView(R.id.view_rl)
    RelativeLayout viewRl;
    @BindView(R.id.history_recycler)
    RecyclerView historyRecycler;
    @BindView(R.id.search_list_recycler)
    RecyclerView searchListRecycler;
    @BindView(R.id.act_homeSearch_scroll)
    NestedScrollView scrollView;
    @BindView(R.id.tv_noContent)
    TextView tvNoContent;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    private MainSearchPresenter searchPresenter;
    private List<HomeSearchHistroyBean> mHistoryList = new ArrayList<>();
    private HistoryAdapter historyAdapter;
    private int state;
    private List<String> SearchList = new ArrayList<>();
    private SearchListAdapter searchListAdapter;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_search);
        ButterKnife.bind(this);
        historyRecycler.setNestedScrollingEnabled(false);
        historyRecycler.setLayoutManager(new LinearLayoutManager(this));
        searchListRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        searchPresenter = new MainSearchPresenter(this, this);
        state = getIntent().getIntExtra("state", 0);
        initHistroy();
        initListener();
    }

    private void initListener() {
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    scrollView.setVisibility(View.GONE);
                    searchListRecycler.setVisibility(View.VISIBLE);
                    tvNoContent.setVisibility(View.GONE);
                } else {
                    scrollView.setVisibility(View.VISIBLE);
                    searchListRecycler.setVisibility(View.GONE);
                    tvNoContent.setVisibility(View.GONE);
                    return;
                }
                searchPresenter.getSearchHint(state, searchEt.getText().toString());
            }
        });

        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH && searchEt.getText().length() > 0) {
                    ((InputMethodManager) searchEt.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    //                    if (SearchList == null || SearchList.size() == 0) {
                    //                        tvNoContent.setVisibility(View.VISIBLE);
                    //                        scrollView.setVisibility(View.GONE);
                    //                        searchListRecycler.setVisibility(View.GONE);
                    //                        return false;
                    //                    }
                    tvNoContent.setVisibility(View.GONE);
                    scrollView.setVisibility(View.GONE);
                    searchListRecycler.setVisibility(View.VISIBLE);
                    Intent intent = new Intent();
                    intent.putExtra("searchText", searchEt.getText().toString());
                    setResult(state, intent);
                    finish();
                    doSavehistory(state, searchEt.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    private void initHistroy() {
        historyRecycler.setNestedScrollingEnabled(false);
        historyRecycler.setLayoutManager(new LinearLayoutManager(this));
        if (getHistory() != null && getHistory().size() > 0) {
            ivDelete.setVisibility(View.VISIBLE);
            mHistoryList = getHistory();
            historyAdapter = new HistoryAdapter(R.layout.item_history_search, mHistoryList);
            historyRecycler.setAdapter(historyAdapter);
            historyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent();
                    intent.putExtra("searchText", mHistoryList.get(position).getContent());
                    setResult(state, intent);
                    finish();
                    doSavehistory2(mHistoryList.get(position));
                }
            });
        }else {
            ivDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public void getSearchHint(Response<TopSearchHintBean> response) {
        SearchList.clear();
        if (searchListAdapter != null) {
            searchListAdapter.notifyDataSetChanged();
        }
        if (response != null && response.body() != null && response.body().getDatas() != null) {
            if (response.body().getDatas().size() > 0) {
                for (int i = 0; i < response.body().getDatas().size(); i++) {
                    SearchList.add(response.body().getDatas().get(i).getVal());
                }
                if (SearchList == null || SearchList.size() == 0) {
                    tvNoContent.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.GONE);
                    searchListRecycler.setVisibility(View.GONE);
                } else {
                    tvNoContent.setVisibility(View.GONE);
                    scrollView.setVisibility(View.GONE);
                    searchListRecycler.setVisibility(View.VISIBLE);
                }
                if (searchListAdapter == null) {
                    searchListAdapter = new SearchListAdapter(R.layout.search_list_item, SearchList);
                    searchListRecycler.setAdapter(searchListAdapter);
                } else {
                    searchListAdapter.notifyDataSetChanged();
                }
                searchListAdapter.setOnItemClickListener(MapSearchActivity.this);
            } else {
                tvNoContent.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
                searchListRecycler.setVisibility(View.GONE);
            }
        } else {
            tvNoContent.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
            searchListRecycler.setVisibility(View.GONE);
        }
    }

    @Override
    public void getHotSearchData(Response<HotSearchBean> response) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent();
        intent.putExtra("searchText", SearchList.get(position));
        setResult(state, intent);
        finish();
        doSavehistory(state, SearchList.get(position));
    }

    private class HistoryAdapter extends BaseQuickAdapter<HomeSearchHistroyBean, BaseViewHolder> {

        public HistoryAdapter(int layoutResId, @Nullable List<HomeSearchHistroyBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeSearchHistroyBean item) {
            helper.setText(R.id.item_name_tv, item.getContent());
        }
    }

    private class SearchListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public SearchListAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_Search_list, item);
        }
    }

    //判断本地数据中有没有存在搜索过的数据，查重
    private boolean isHasSelectData(String content) {
        if (mHistoryList == null || mHistoryList.size() == 0) {
            return false;
        }
        for (int i = 0; i < mHistoryList.size(); i++) {
            if (mHistoryList.get(i).getContent().equals(content)) {
                position = i;
                return true;
            }
        }
        return false;
    }

    private void doSavehistory(int state, String content) {
        if (isHasSelectData(content)) {//查重
            mHistoryList.remove(position);
        }
        //后来搜索的文字放在集合中的第一个位置
        mHistoryList.add(0, new HomeSearchHistroyBean(state, content));
        if (mHistoryList.size() == 11) {//实现本地历史搜索记录最多不超过10个
            mHistoryList.remove(10);
        }
        //将这个mHistoryListData保存到sp中，其实sp中保存的就是这个mHistoryListData集合
        saveHistory();
    }

    private void doSavehistory2(HomeSearchHistroyBean histroyBean) {
        if (isHasSelectData(histroyBean.getContent())) {//查重
            mHistoryList.remove(position);
        }
        //后来搜索的文字放在集合中的第一个位置
        mHistoryList.add(0, new HomeSearchHistroyBean(histroyBean.getState(), histroyBean.getState2()
                , histroyBean.getContent()));

        if (mHistoryList.size() == 11) {//实现本地历史搜索记录最多不超过10个
            mHistoryList.remove(10);
        }
        //将这个mHistoryListData保存到sp中，其实sp中保存的就是这个mHistoryListData集合
        saveHistory();
    }

    /**
     * 保存历史查询记录
     */
    private void saveHistory() {
        CacheUtils.put("mapsearchhistory" + state, mHistoryList);
    }

    /**
     * 获取历史查询记录
     *
     * @return
     */
    private List<HomeSearchHistroyBean> getHistory() {
        return CacheUtils.get("mapsearchhistory" + state);
    }

    @OnClick({R.id.cancle_tv, R.id.iv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancle_tv:
                finish();
                break;
            case R.id.iv_delete:
                mHistoryList.clear();
                if (historyAdapter != null) {
                    historyAdapter.notifyDataSetChanged();
                }
                saveHistory();
                break;
        }
    }
}
