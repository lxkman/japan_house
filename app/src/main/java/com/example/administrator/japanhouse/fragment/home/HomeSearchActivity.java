package com.example.administrator.japanhouse.fragment.home;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.model.SearchBean;
import com.example.administrator.japanhouse.model.TopSearchHintBean;
import com.example.administrator.japanhouse.presenter.MainSearchPresenter;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.SoftKeyboardTool;
import com.example.administrator.japanhouse.view.CommonPopupWindow;
import com.example.administrator.japanhouse.view.FluidLayout;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeSearchActivity extends BaseActivity implements MainSearchPresenter.MainSearchCallBack, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.location_tv)
    TextView locationTv;
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.cancle_tv)
    TextView cancleTv;
    @BindView(R.id.hotsearch_recycler)
    FluidLayout fluidlayout;
    @BindView(R.id.history_recycler)
    RecyclerView historyRecycler;
    @BindView(R.id.view_rl)
    RelativeLayout view_rl;
    @BindView(R.id.hot_refrash_iv)
    ImageView hotRefrashIv;
    @BindView(R.id.history_clear)
    ImageView historyClear;
    @BindView(R.id.search_list_recycler)
    RecyclerView searchListRecycler;
    @BindView(R.id.act_homeSearch_scroll)
    NestedScrollView scrollView;
    private CommonPopupWindow popupWindow;
    private List<String> historyList;
    private List<String> SearchList = new ArrayList<>();
    private HistoryAdapter historyAdapter;
    private MainSearchPresenter searchPresenter;
    private SearchListAdapter searchListAdapter;
    private boolean isJa;
    private int state;
    private int state2 = 100;
    private List<SearchBean> searchBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);
        ButterKnife.bind(this);
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        searchPresenter = new MainSearchPresenter(this, this);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("popcontent"))) {
            locationTv.setText(getIntent().getStringExtra("popcontent"));
        }
        if (TextUtils.isEmpty(getIntent().getStringExtra("home"))) {
            locationTv.setClickable(false);
        }
        state = getIntent().getIntExtra("state", 0);
        state2 = getIntent().getIntExtra("state2", 0);
        searchListRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        initHot();
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
                } else {
                    scrollView.setVisibility(View.VISIBLE);
                    searchListRecycler.setVisibility(View.GONE);
                }
                SearchList.clear();
                if (searchListAdapter != null) {
                    searchListAdapter.notifyDataSetChanged();
                }
                if (state == 4) {
                    if (state2 != 100) {
                        searchPresenter.getSearchHint(state, state2, searchEt.getText().toString());
                    } else {
                        searchPresenter.getSearchHint(state, searchEt.getText().toString());
                    }
                } else if (state == 5) {
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

        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH && searchEt.getText().length() > 0) {
                    ((InputMethodManager) searchEt.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    if (SearchList == null || SearchList.size() == 0) {
                        Toast.makeText(mContext, "无数据~", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    tiaozhuan();
                    return true;
                }
                return false;
            }
        });
    }

    private void tiaozhuan() {
        switch (state) {
            case 0: //新房
                Intent newHouseIntent = new Intent(HomeSearchActivity.this, NewHouseActivity.class);
                newHouseIntent.putExtra("searchText", searchEt.getText().toString());
                startActivity(newHouseIntent);
                break;
            case 1: //别墅
                Intent villaIntent = new Intent(HomeSearchActivity.this, BieShuActivity.class);
                villaIntent.putExtra("searchText", searchEt.getText().toString());
                startActivity(villaIntent);
                break;
            case 2: //二手房
                Intent oldHouseIntent = new Intent(HomeSearchActivity.this, ErshoufangActiviy.class);
                oldHouseIntent.putExtra("searchText", searchEt.getText().toString());
                startActivity(oldHouseIntent);
                break;
            case 3: //土地
                Intent landIntent = new Intent(HomeSearchActivity.this, TudiActivity.class);
                landIntent.putExtra("searchText", searchEt.getText().toString());
                startActivity(landIntent);
                break;
            case 4: //租房
                if (state2 == 100) {
                    Intent rentalIntent = new Intent(HomeSearchActivity.this, ZufangListActivity.class);
                    rentalIntent.putExtra("searchText", searchEt.getText().toString());
                    startActivity(rentalIntent);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("searchText", searchEt.getText().toString());
                    setResult(11, intent);
                    finish();
                }
                break;
            case 5: //商业地产
                if (state2 == 100) {
                    Intent businessIntent = new Intent(HomeSearchActivity.this, SydcLiebiaoActivity.class);
                    businessIntent.putExtra("searchText", searchEt.getText().toString());
                    startActivity(businessIntent);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("searchText", searchEt.getText().toString());
                    setResult(11, intent);
                    finish();
                }
                break;
        }
        List<SearchBean> list = CacheUtils.get(Constants.SEARCH_MAIN_HISTORY);
        if (list != null) {
            list.add(0, new SearchBean(state, searchEt.getText().toString()));
            CacheUtils.put(Constants.SEARCH_MAIN_HISTORY, list);
        } else {
            List<SearchBean> arrayList = new ArrayList<>();
            arrayList.add(0, new SearchBean(state, searchEt.getText().toString()));
            CacheUtils.put(Constants.SEARCH_MAIN_HISTORY, arrayList);
        }
        queryCacheHistory();
    }

    private void initHot() {
        List<String> hotNameList = new ArrayList<>();
        hotNameList.add("朝阳");
        hotNameList.add("青森县");
        hotNameList.add("采光");
        hotNameList.add("南向");
        hotNameList.add("秋田县");
        hotNameList.add("山形县");
        initHot(hotNameList);

        historyList = new ArrayList<>();

        historyRecycler.setNestedScrollingEnabled(false);
        historyRecycler.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new HistoryAdapter(R.layout.item_history_search, historyList);
        historyRecycler.setAdapter(historyAdapter);
        historyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                switch (searchBean.get(position).state) {
                    case 0: //新房  searchText
                        Intent newHouseIntent = new Intent(HomeSearchActivity.this, NewHouseActivity.class);
                        newHouseIntent.putExtra("searchText", searchBean.get(position).content);
                        startActivity(newHouseIntent);
                        break;
                    case 1: //别墅
                        Intent villaIntent = new Intent(HomeSearchActivity.this, BieShuActivity.class);
                        villaIntent.putExtra("searchText", searchBean.get(position).content);
                        startActivity(villaIntent);
                        break;
                    case 2: //二手房
                        Intent oldHouseIntent = new Intent(HomeSearchActivity.this, ErshoufangActiviy.class);
                        oldHouseIntent.putExtra("searchText", searchBean.get(position).content);
                        startActivity(oldHouseIntent);
                        break;
                    case 3: //土地
                        Intent landIntent = new Intent(HomeSearchActivity.this, TudiActivity.class);
                        landIntent.putExtra("searchText", searchBean.get(position).content);
                        startActivity(landIntent);
                        break;
                    case 4: //租房
                        Intent rentalIntent = new Intent(HomeSearchActivity.this, ZufangListActivity.class);
                        rentalIntent.putExtra("searchText", searchBean.get(position).content);
                        startActivity(rentalIntent);
                        break;
                    case 5: //商业地产
                        Intent businessIntent = new Intent(HomeSearchActivity.this, SydcLiebiaoActivity.class);
                        businessIntent.putExtra("searchText", searchBean.get(position).content);
                        startActivity(businessIntent);
                        break;
                }

                SearchBean sb = searchBean.get(position);
                searchBean.remove(position);
                searchBean.add(0, sb);
                CacheUtils.put(Constants.SEARCH_MAIN_HISTORY, searchBean);
                queryCacheHistory();
            }
        });
        queryCacheHistory();
    }

    private void queryCacheHistory() {
        historyList.clear();
        searchBean = CacheUtils.get(Constants.SEARCH_MAIN_HISTORY);
        if (searchBean != null && searchBean.size() > 0) {
            for (int i = 0; i < searchBean.size(); i++) {
                historyList.add(searchBean.get(i).content);
            }
            historyAdapter.notifyDataSetChanged();
        }
    }

    private void initHot(final List<String> hotNameList) {
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
            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<SearchBean> list = CacheUtils.get(Constants.SEARCH_MAIN_HISTORY);
                    if (list != null) {
                        list.add(0, new SearchBean(state, hotNameList.get(finalI)));
                        CacheUtils.put(Constants.SEARCH_MAIN_HISTORY, list);
                    } else {
                        List<SearchBean> arrayList = new ArrayList<>();
                        arrayList.add(0, new SearchBean(state, hotNameList.get(finalI)));
                        CacheUtils.put(Constants.SEARCH_MAIN_HISTORY, arrayList);
                    }

                    queryCacheHistory();
                }
            });
        }
    }

    @OnClick({R.id.cancle_tv, R.id.location_tv, R.id.hot_refrash_iv, R.id.history_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancle_tv:
                SoftKeyboardTool.closeKeyboard(this);
                finish();
                break;
            case R.id.location_tv:
                showDownPop(locationTv);
                break;
            case R.id.hot_refrash_iv:
                List<String> hotNameList = new ArrayList<>();
                hotNameList.add("朝阳q");
                hotNameList.add("青森县1");
                hotNameList.add("采光2");
                hotNameList.add("南向3");
                hotNameList.add("秋田县g");
                hotNameList.add("山形县sfs");
                initHot(hotNameList);
                break;
            case R.id.history_clear:
                historyList.clear();
                historyAdapter.notifyDataSetChanged();
                CacheUtils.remove(Constants.SEARCH_MAIN_HISTORY);
                break;
        }
    }

    //向下弹出
    public void showDownPop(View view) {
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        popupWindow = new CommonPopupWindow.Builder(HomeSearchActivity.this)
                .setView(R.layout.popup_home_search)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimDown)
                .setBackGroundLevel(0.5f)
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        TextView ershoufangTv = (TextView) view.findViewById(R.id.ershoufang_tv);
                        ershoufangTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                locationTv.setText(getResources().getString(R.string.old_house));
                                state = 2;
                            }
                        });
                        TextView xinfangTv = (TextView) view.findViewById(R.id.xinfang_tv);
                        xinfangTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                locationTv.setText(getResources().getString(R.string.new_house));
                                state = 0;
                            }
                        });
                        TextView zufangTv = (TextView) view.findViewById(R.id.zufang_tv);
                        zufangTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                locationTv.setText(getResources().getString(R.string.zu_house));
                                state = 4;
                            }
                        });
                        TextView maishangpuTv = (TextView) view.findViewById(R.id.bieshu_tv);
                        maishangpuTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                locationTv.setText(getResources().getString(R.string.bieshu));
                                state = 1;
                            }
                        });
                        TextView maixiezilouTv = (TextView) view.findViewById(R.id.tudi_tv);
                        maixiezilouTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                locationTv.setText(getResources().getString(R.string.tudi));
                                state = 3;
                            }
                        });
                        TextView zuxiezilouTv = (TextView) view.findViewById(R.id.sydc_tv);
                        zuxiezilouTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                locationTv.setText(getResources().getString(R.string.shangyedichan));
                                state = 5;
                            }
                        });
                    }
                })
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAsDropDown(view);
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
                searchListAdapter.setOnItemClickListener(HomeSearchActivity.this);
            }
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        tiaozhuan();
    }

    private class HistoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public HistoryAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.item_name_tv, item);
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

}
