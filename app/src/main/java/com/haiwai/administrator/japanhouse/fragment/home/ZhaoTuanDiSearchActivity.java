package com.haiwai.administrator.japanhouse.fragment.home;

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
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.bean.HotSearchBean;
import com.haiwai.administrator.japanhouse.model.SimpleBean;
import com.haiwai.administrator.japanhouse.model.TopSearchHintBean;
import com.haiwai.administrator.japanhouse.presenter.MainSearchPresenter;
import com.haiwai.administrator.japanhouse.view.FluidLayout;
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
    @BindView(R.id.tv_noContent)
    TextView tvNoContent;
    private MainSearchPresenter searchPresenter;
    private SearchListAdapter searchListAdapter;
    private List<String> SearchList = new ArrayList<>();
    private int page = 1;

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
                    tvNoContent.setVisibility(View.GONE);
                } else {
                    scrollView.setVisibility(View.VISIBLE);
                    searchListRecycler.setVisibility(View.GONE);
                    tvNoContent.setVisibility(View.GONE);
                    Intent intent = new Intent();
                    intent.putExtra("searchText", searchEt.getText().toString());
                    setResult(11, intent);
                    finish();
                    return;
                }
                searchPresenter.getSearchHint(4, 6, searchEt.getText().toString());
            }
        });
    }

    private void initHot() {
        searchPresenter.getHotSearchData(page,2);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent();
        intent.putExtra("searchText", SearchList.get(position));
        setResult(11, intent);
        finish();
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
                int indexOf = item.indexOf(searchEt.getText().toString().trim());
                if (indexOf!=-1) {
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
                    spannableString.setSpan(colorSpan,indexOf, indexOf+searchEt.getText().toString().trim().length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
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
                searchListAdapter.setOnItemClickListener(ZhaoTuanDiSearchActivity.this);
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
                    Intent intent = new Intent();
                    intent.putExtra("searchText", tv.getText().toString());
                    setResult(11, intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void getWdSearchHint(Response<SimpleBean> response) {

    }
}
