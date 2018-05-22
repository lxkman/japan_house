package com.example.administrator.japanhouse.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.adapter.SearchListAdapter;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.example.administrator.japanhouse.utils.SoftKeyboardTool;
import com.example.administrator.japanhouse.utils.TUtils;
import com.example.administrator.japanhouse.view.CommonPopupWindow;
import com.example.administrator.japanhouse.view.FluidLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeSearchActivity extends BaseActivity {

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
    private CommonPopupWindow popupWindow;
    private List<String> historyList;
    private List<String> SearchList=new ArrayList<>();
    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);
        ButterKnife.bind(this);
        initView();
        if (!TextUtils.isEmpty(getIntent().getStringExtra("popcontent"))) {
            locationTv.setText(getIntent().getStringExtra("popcontent"));
        }
        searchEt.setOnEditorActionListener(editorActionListener);
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initView() {

        if (SearchList.size()<=0){
            SearchList.add("北街家园");
            SearchList.add("北街家园一区");
            SearchList.add("北街家园二区");
            SearchList.add("北街家园三区");
        }
        searchListRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        SearchListAdapter   SearchListAdapter = new SearchListAdapter(this,SearchList,MyUtils.isJa());
        searchListRecycler.setAdapter(SearchListAdapter);



        List<String> hotNameList = new ArrayList<>();
        hotNameList.add("朝阳");
        hotNameList.add("青森县");
        hotNameList.add("采光");
        hotNameList.add("南向");
        hotNameList.add("秋田县");
        hotNameList.add("山形县");
        initHot(hotNameList);

        historyList = new ArrayList<>();
        historyList.add("东京");
        historyList.add("澳大利亚");
        historyList.add("中国");
        historyList.add("美国");
        historyList.add("缅甸");
        historyList.add("阿富汗");
        historyRecycler.setNestedScrollingEnabled(false);
        historyRecycler.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new HistoryAdapter(R.layout.item_history_search, historyList);
        historyRecycler.setAdapter(historyAdapter);
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
                    TUtils.showShort(mContext, "点击了---" + hotNameList.get(finalI));
                }
            });
        }
    }

    TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //                SoftKeyboardTool.closeKeyboard(mSearchEt);//关闭软键盘
                searchEt.setFocusable(true);
                searchEt.setFocusableInTouchMode(true);
                return true;
            }
            return false;
        }
    };

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
                            }
                        });
                        TextView xinfangTv = (TextView) view.findViewById(R.id.xinfang_tv);
                        xinfangTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                locationTv.setText(getResources().getString(R.string.new_house));
                            }
                        });
                        TextView zufangTv = (TextView) view.findViewById(R.id.zufang_tv);
                        zufangTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                locationTv.setText(getResources().getString(R.string.zu_house));
                            }
                        });
                        TextView maishangpuTv = (TextView) view.findViewById(R.id.bieshu_tv);
                        maishangpuTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                locationTv.setText(getResources().getString(R.string.bieshu));
                            }
                        });
                        TextView maixiezilouTv = (TextView) view.findViewById(R.id.tudi_tv);
                        maixiezilouTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                locationTv.setText(getResources().getString(R.string.tudi));
                            }
                        });
                        TextView zuxiezilouTv = (TextView) view.findViewById(R.id.sydc_tv);
                        zuxiezilouTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                                locationTv.setText(getResources().getString(R.string.shangyedichan));
                            }
                        });
                    }
                })
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAsDropDown(view);
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

}
