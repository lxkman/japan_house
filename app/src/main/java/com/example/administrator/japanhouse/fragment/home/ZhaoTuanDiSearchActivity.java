package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.model.TopSearchHintBean;
import com.example.administrator.japanhouse.presenter.MainSearchPresenter;
import com.example.administrator.japanhouse.utils.TUtils;
import com.example.administrator.japanhouse.view.FluidLayout;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhaoTuanDiSearchActivity extends BaseActivity implements MainSearchPresenter.MainSearchCallBack, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.cancle_tv)
    TextView cancleTv;
    @BindView(R.id.hotsearch_recycler)
    FluidLayout fluidlayout;
    @BindView(R.id.scrollview)
    NestedScrollView scrollView;
    @BindView(R.id.search_list_recycler)
    RecyclerView searchListRecycler;
    private MainSearchPresenter searchPresenter;
    private SearchListAdapter searchListAdapter;
    private List<String> SearchList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhao_tuan_di_search);
        ButterKnife.bind(this);
        searchListRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        searchPresenter = new MainSearchPresenter(this, this);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("edt_hint"))) {
            searchEt.setHint(getIntent().getStringExtra("edt_hint"));
        }
        initHot();
        initListener();
    }

    private void initListener() {
        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //                SoftKeyboardTool.closeKeyboard(mSearchEt);//关闭软键盘
                    searchEt.setFocusable(true);
                    searchEt.setFocusableInTouchMode(true);
                    if (SearchList == null || SearchList.size() == 0) {
                        Toast.makeText(mContext, "无数据~", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("searchText", searchEt.getText().toString());
                    setResult(11, intent);
                    finish();
                    return true;
                }
                return false;
            }
        });
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
                } else {
                    scrollView.setVisibility(View.VISIBLE);
                    searchListRecycler.setVisibility(View.GONE);
                }
                SearchList.clear();
                if (searchListAdapter != null) {
                    searchListAdapter.notifyDataSetChanged();
                }
                searchPresenter.getSearchHint(4, 6, searchEt.getText().toString());
            }
        });
    }

    private void initHot() {
        fluidlayout.removeAllViews();
        final List<String> hotNameList = new ArrayList<>();
        hotNameList.add("朝阳");
        hotNameList.add("青森县");
        hotNameList.add("采光");
        hotNameList.add("南向");
        hotNameList.add("秋田县");
        hotNameList.add("山形县");
        for (int i = 0; i < hotNameList.size(); i++) {
            final TextView tv = (TextView) View.inflate(mContext, R.layout.item_hot_search, null);
            tv.setText(hotNameList.get(i));
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(12, 12, 12, 12);
            fluidlayout.addView(tv, params);
            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TUtils.showShort(mContext, "点击了---" + hotNameList.get(finalI));
                }
            });
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

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

    @OnClick(R.id.cancle_tv)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getSearchHint(Response<TopSearchHintBean> response) {
        if (response != null && response.body() != null && response.body().getDatas() != null) {
            if (response.body().getDatas().size() > 0) {
                for (int i = 0; i < response.body().getDatas().size(); i++) {
                    SearchList.add(response.body().getDatas().get(i).getVal());
                }
                if (SearchList == null || SearchList.size() == 0) {
                    Toast.makeText(mContext, "无数据~", Toast.LENGTH_SHORT).show();
                }
                if (searchListAdapter == null) {
                    searchListAdapter = new SearchListAdapter(R.layout.search_list_item, SearchList);
                    searchListRecycler.setAdapter(searchListAdapter);
                } else {
                    searchListAdapter.notifyDataSetChanged();
                }
                searchListAdapter.setOnItemClickListener(ZhaoTuanDiSearchActivity.this);
            }
        }
    }
}
