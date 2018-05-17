package com.example.administrator.japanhouse.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapSearchActivity extends BaseActivity {

    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.cancle_tv)
    TextView cancleTv;
    @BindView(R.id.view_rl)
    RelativeLayout viewRl;
    @BindView(R.id.history_recycler)
    RecyclerView historyRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_search);
        ButterKnife.bind(this);
        initView();
        searchEt.setOnEditorActionListener(editorActionListener);
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

    private void initView() {
        List<String> historyList = new ArrayList<>();
        historyList.add("东京");
        historyList.add("澳大利亚");
        historyList.add("中国");
        historyList.add("美国");
        historyList.add("缅甸");
        historyList.add("阿富汗");
        historyRecycler.setNestedScrollingEnabled(false);
        historyRecycler.setLayoutManager(new LinearLayoutManager(this));
        HistoryAdapter historyAdapter = new HistoryAdapter(R.layout.item_history_search, historyList);
        historyRecycler.setAdapter(historyAdapter);
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

    @OnClick(R.id.cancle_tv)
    public void onViewClicked() {
        finish();
    }
}
