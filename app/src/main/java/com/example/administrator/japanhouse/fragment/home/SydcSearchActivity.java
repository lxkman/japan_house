package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.HotSearchBean;
import com.example.administrator.japanhouse.model.SimpleBean;
import com.example.administrator.japanhouse.model.TopSearchHintBean;
import com.example.administrator.japanhouse.presenter.MainSearchPresenter;
import com.example.administrator.japanhouse.view.FluidLayout;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SydcSearchActivity extends BaseActivity implements MainSearchPresenter.MainSearchCallBack, BaseQuickAdapter.OnItemClickListener {

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
    @BindView(R.id.tv_noContent)
    TextView tvNoContent;
    private MainSearchPresenter searchPresenter;
    private int state;
    private int state2 = 100;
    private int state3;
    private SearchListAdapter searchListAdapter;
    private List<String> SearchList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sydc_search);
        ButterKnife.bind(this);
        searchListRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        searchPresenter = new MainSearchPresenter(this, this);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("edt_hint"))) {
            searchEt.setHint(getIntent().getStringExtra("edt_hint"));
        }
        state = getIntent().getIntExtra("state", 0);
        state2 = getIntent().getIntExtra("state2", 100);
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
//                    if (SearchList == null || SearchList.size() == 0) {
//                        tvNoContent.setVisibility(View.VISIBLE);
//                        scrollView.setVisibility(View.GONE);
//                        searchListRecycler.setVisibility(View.GONE);
//                        return false;
//                    }
                    tvNoContent.setVisibility(View.GONE);
                    scrollView.setVisibility(View.GONE);
                    searchListRecycler.setVisibility(View.VISIBLE);
                    tiaozhuan(searchEt.getText().toString());
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
                    tvNoContent.setVisibility(View.GONE);
                } else {
                    scrollView.setVisibility(View.VISIBLE);
                    searchListRecycler.setVisibility(View.GONE);
                    tvNoContent.setVisibility(View.GONE);
                    return;
                }
                if (state == 7) {
                    if (state2 != 100) {
                        searchPresenter.getSearchHint(state, state2, searchEt.getText().toString());
                    } else {
                        searchPresenter.getSearchHint(state, searchEt.getText().toString());
                    }
                } else {
                    searchPresenter.getSearchHint(state, searchEt.getText().toString());
                }
            }
        });
    }

    private void tiaozhuan(String searchText) {
        switch (state) {
            case 6: //海外地产
                Intent newHouseIntent = new Intent(SydcSearchActivity.this, HaiwaiListActivity.class);
                newHouseIntent.putExtra("searchText", searchText);
                startActivity(newHouseIntent);
                break;
            case 7: //中国房源
                if (state2 == 100) {
                    Intent businessIntent = new Intent(SydcSearchActivity.this, ChineseLiebiaoActivity.class);
                    businessIntent.putExtra("searchText", searchText);
                    startActivity(businessIntent);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("searchText", searchText);
                    setResult(11, intent);
                    finish();
                }
                break;
        }
    }

    private void initHot() {
        switch (state) {
            case 6:
                state3 = 4;
                break;
            case 7:
                state3 = 5;
                break;
        }
        searchPresenter.getHotSearchData(1,state3);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        tiaozhuan(SearchList.get(position));
    }

    private class SearchListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public SearchListAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            SpannableString spannableString = new SpannableString(item);
            if (item.equals(searchEt.getText().toString())) {
                helper.setText(R.id.tv_Search_list, item);
                helper.setTextColor(R.id.tv_Search_list, getResources().getColor(R.color.colorPrimary));
            } else {
                boolean contains = item.contains(searchEt.getText().toString());
                if (contains) {
                    String[] split = item.split(searchEt.getText().toString());
                    String xxx = split[0] + searchEt.getText().toString();
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
                    spannableString.setSpan(colorSpan, split[0].length(), xxx.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    helper.setText(R.id.tv_Search_list, item);
                } else {
                    helper.setText(R.id.tv_Search_list, item);
                }
            }
        }
    }

    @OnClick(R.id.cancle_tv)
    public void onViewClicked() {
        finish();
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
                }else {
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
                searchListAdapter.setOnItemClickListener(SydcSearchActivity.this);
            }else {
                tvNoContent.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
                searchListRecycler.setVisibility(View.GONE);
            }
        }else {
            tvNoContent.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
            searchListRecycler.setVisibility(View.GONE);
        }
    }

    @Override
    public void getHotSearchData(Response<HotSearchBean> response) {
        HotSearchBean body = response.body();
        List<HotSearchBean.DatasEntity> datas = body.getDatas();
        final List<String> hotNameList = new ArrayList<>();
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                String tagText = datas.get(i).getTagText();
                hotNameList.add(tagText);
            }
        }
        fluidlayout.removeAllViews();
        for (int i = 0; i < hotNameList.size(); i++) {
            final TextView tv = (TextView) View.inflate(mContext, R.layout.item_hot_search, null);
            tv.setText(hotNameList.get(i));
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(12, 12, 12, 12);
            fluidlayout.addView(tv, params);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tiaozhuan(tv.getText().toString());
                }
            });
        }
    }

    @Override
    public void getWdSearchHint(Response<SimpleBean> response) {

    }
}
