package com.haiwai.administrator.japanhouse.fragment.home;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.bean.HomeSearchHistroyBean;
import com.haiwai.administrator.japanhouse.bean.HotSearchBean;
import com.haiwai.administrator.japanhouse.model.SimpleBean;
import com.haiwai.administrator.japanhouse.model.TopSearchHintBean;
import com.haiwai.administrator.japanhouse.presenter.MainSearchPresenter;
import com.haiwai.administrator.japanhouse.utils.CacheUtils;
import com.haiwai.administrator.japanhouse.utils.Constants;
import com.haiwai.administrator.japanhouse.utils.SoftKeyboardTool;
import com.haiwai.administrator.japanhouse.view.CommonPopupWindow;
import com.haiwai.administrator.japanhouse.view.FluidLayout;
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
    @BindView(R.id.tv_noContent)
    TextView tvNoContent;
    private CommonPopupWindow popupWindow;
    private List<String> SearchList = new ArrayList<>();
    private HistoryAdapter historyAdapter;
    private MainSearchPresenter searchPresenter;
    private SearchListAdapter searchListAdapter;
    private boolean isJa;
    private int state;
    private int state2 = 100;
    private int state3;
    private List<HomeSearchHistroyBean> mHistoryList = new ArrayList<>();
    private int position;
    private int page = 1;

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
        state2 = getIntent().getIntExtra("state2", 100);
        searchListRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        historyRecycler.setNestedScrollingEnabled(false);
        historyRecycler.setLayoutManager(new LinearLayoutManager(this));
        initHot();
        initHistroy();
        initListener();
    }

    private void initHistroy() {
        if (getHistory() != null && getHistory().size() > 0) {
            historyClear.setVisibility(View.VISIBLE);
            mHistoryList = getHistory();
            historyAdapter = new HistoryAdapter(R.layout.item_history_search, mHistoryList);
            historyRecycler.setAdapter(historyAdapter);
            historyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    tiaozhuan(mHistoryList.get(position).getContent(), true);
                    doSavehistory2(mHistoryList.get(position));
                }
            });
        } else {
            historyClear.setVisibility(View.GONE);
            mHistoryList.clear();
            if (historyAdapter != null) {
                historyAdapter.notifyDataSetChanged();
            }
        }
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
                    initHistroy();
                    return;
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
                            .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //                    if (SearchList == null || SearchList.size() == 0) {
                    //                        tvNoContent.setVisibility(View.VISIBLE);
                    //                        scrollView.setVisibility(View.GONE);
                    //                        searchListRecycler.setVisibility(View.GONE);
                    //                        return false;
                    //                    }
                    tvNoContent.setVisibility(View.GONE);
                    scrollView.setVisibility(View.GONE);
                    searchListRecycler.setVisibility(View.VISIBLE);
                    tiaozhuan(searchEt.getText().toString(), true);
                    return true;
                }
                return false;
            }
        });
    }

    private void tiaozhuan(String searchText, boolean isSave) {
        switch (state) {
            case 0: //新房
                if (TextUtils.isEmpty(getIntent().getStringExtra("home"))) {
                    Intent intent = new Intent();
                    intent.putExtra("searchText", searchText);
                    setResult(11, intent);
                    finish();
                } else {
                    Intent newHouseIntent = new Intent(HomeSearchActivity.this, NewHouseActivity.class);
                    newHouseIntent.putExtra("searchText", searchText);
                    startActivityForResult(newHouseIntent,0);
                }
                break;
            case 1: //别墅
                if (TextUtils.isEmpty(getIntent().getStringExtra("home"))) {
                    Intent intent = new Intent();
                    intent.putExtra("searchText", searchText);
                    setResult(11, intent);
                    finish();
                } else {
                    Intent villaIntent = new Intent(HomeSearchActivity.this, BieShuActivity.class);
                    villaIntent.putExtra("searchText", searchText);
                    startActivityForResult(villaIntent,0);
                }
                break;
            case 2: //二手房
                if (TextUtils.isEmpty(getIntent().getStringExtra("home"))) {
                    Intent intent = new Intent();
                    intent.putExtra("searchText", searchText);
                    setResult(11, intent);
                    finish();
                } else {
                    Intent oldHouseIntent = new Intent(HomeSearchActivity.this, ErshoufangActiviy.class);
                    oldHouseIntent.putExtra("searchText", searchText);
                    startActivityForResult(oldHouseIntent,0);
                }
                break;
            case 3: //土地
                if (TextUtils.isEmpty(getIntent().getStringExtra("home"))) {
                    Intent intent = new Intent();
                    intent.putExtra("searchText", searchText);
                    setResult(11, intent);
                    finish();
                } else {
                    Intent landIntent = new Intent(HomeSearchActivity.this, TudiActivity.class);
                    landIntent.putExtra("searchText", searchText);
                    startActivityForResult(landIntent,0);
                }
                break;
            case 4: //租房
                if (state2 == 100) {
                    Intent rentalIntent = new Intent(HomeSearchActivity.this, ZufangBigListActivity.class);
                    rentalIntent.putExtra("searchText", searchText);
                    startActivityForResult(rentalIntent,0);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("searchText", searchText);
                    setResult(11, intent);
                    finish();
                }
                break;
            case 5: //商业地产
                if (state2 == 100) {
                    Intent businessIntent = new Intent(HomeSearchActivity.this, SydcBigLiebiaoActivity.class);
                    businessIntent.putExtra("searchText", searchText);
                    startActivityForResult(businessIntent,0);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("searchText", searchText);
                    setResult(11, intent);
                    finish();
                }
                break;
        }
        if (isSave) {
            doSavehistory(state, state2, searchText);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==100){
            finish();
        }
    }

    private void initHot() {
        switch (state) {
            case 0:
                state3 = 1;
                break;
            case 1:
                state3 = 7;
                break;
            case 2:
                state3 = 0;
                break;
            case 3:
                state3 = 3;
                break;
            case 4:
                state3 = 2;
                break;
            case 5:
                state3 = 6;
                break;
        }
        searchPresenter.getHotSearchData(page, state3);
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
                page++;
                initHot();
                break;
            case R.id.history_clear:
                mHistoryList.clear();
                if (historyAdapter != null) {
                    historyAdapter.notifyDataSetChanged();
                }
                saveHistory();
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
                                if (state == 2) {
                                    return;
                                }
                                locationTv.setText(getResources().getString(R.string.old_house));
                                state = 2;
                                page = 1;
                                initHot();
                                initHistroy();
                            }
                        });
                        TextView xinfangTv = (TextView) view.findViewById(R.id.xinfang_tv);
                        xinfangTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                if (state == 0) {
                                    return;
                                }
                                locationTv.setText(getResources().getString(R.string.new_house));
                                state = 0;
                                page = 1;
                                initHot();
                                initHistroy();
                            }
                        });
                        TextView zufangTv = (TextView) view.findViewById(R.id.zufang_tv);
                        zufangTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                if (state == 4) {
                                    return;
                                }
                                locationTv.setText(getResources().getString(R.string.zu_house));
                                state = 4;
                                page = 1;
                                initHot();
                                initHistroy();
                            }
                        });
                        TextView maishangpuTv = (TextView) view.findViewById(R.id.bieshu_tv);
                        maishangpuTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                if (state == 1) {
                                    return;
                                }
                                locationTv.setText(getResources().getString(R.string.bieshu));
                                state = 1;
                                page = 1;
                                initHot();
                                initHistroy();
                            }
                        });
                        TextView maixiezilouTv = (TextView) view.findViewById(R.id.tudi_tv);
                        maixiezilouTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                if (state == 3) {
                                    return;
                                }
                                locationTv.setText(getResources().getString(R.string.tudi));
                                state = 3;
                                page = 1;
                                initHot();
                                initHistroy();
                            }
                        });
                        TextView zuxiezilouTv = (TextView) view.findViewById(R.id.sydc_tv);
                        zuxiezilouTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                if (state == 5) {
                                    return;
                                }
                                locationTv.setText(getResources().getString(R.string.shangyedichan));
                                state = 5;
                                page = 1;
                                initHot();
                                initHistroy();
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
                searchListAdapter.setOnItemClickListener(HomeSearchActivity.this);
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
        HotSearchBean body = response.body();
        List<HotSearchBean.DatasEntity> datas = body.getDatas();
        if (datas != null && datas.size() > 0) {
            final List<String> hotNameList = new ArrayList<>();
            for (int i = 0; i < datas.size(); i++) {
                String tagText = datas.get(i).getTagText();
                hotNameList.add(tagText);
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
                        tiaozhuan(tv.getText().toString(), false);
                    }
                });
            }
        } else {
            fluidlayout.removeAllViews();
            page=0;
        }
    }

    @Override
    public void getWdSearchHint(Response<SimpleBean> response) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        tiaozhuan(SearchList.get(position), true);
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

    private void doSavehistory(int state, int state2, String content) {
        if (isHasSelectData(content)) {//查重
            mHistoryList.remove(position);
        }
        //后来搜索的文字放在集合中的第一个位置
        mHistoryList.add(0, new HomeSearchHistroyBean(state, state2, content));
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
        CacheUtils.put("homesearchhistory" + state + state2, mHistoryList);
        //        SpUtils.putString("homesearchhistory" + state + state2,
        //                new Gson().toJson(mHistoryList));//将java对象转换成json字符串进行保存
    }

    /**
     * 获取历史查询记录
     *
     * @return
     */
    private List<HomeSearchHistroyBean> getHistory() {
        return CacheUtils.get("homesearchhistory" + state + state2);
        //        String historyJson = SpUtils.getString("homesearchhistory" + state + state2, "");
        //        if (historyJson != null && !historyJson.equals("")) {//必须要加上后面的判断，因为获取的字符串默认值就是空字符串
        //            //将json字符串转换成list集合
        //            return new Gson().fromJson(historyJson, new TypeToken<List<HomeSearchHistroyBean>>() {
        //            }.getType());
        //        }
        //        return new ArrayList<HomeSearchHistroyBean>();
    }

}
