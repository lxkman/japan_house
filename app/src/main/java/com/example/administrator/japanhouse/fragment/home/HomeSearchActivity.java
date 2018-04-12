package com.example.administrator.japanhouse.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.utils.TUtils;
import com.example.administrator.japanhouse.view.FluidLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
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
                    TUtils.showShort(mContext,"点击了---" + hotNameList.get(finalI));
                }
            });
        }

        List<String> historyList = new ArrayList<>();
        historyList.add("东京");
        historyList.add("澳大利亚");
        historyList.add("中国");
        historyList.add("美国");
        historyList.add("缅甸");
        historyList.add("阿富汗");
        historyRecycler.setNestedScrollingEnabled(false);
        historyRecycler.setLayoutManager(new LinearLayoutManager(this));
        HistoryAdapter historyAdapter = new HistoryAdapter(R.layout.item_history_search,historyList);
        historyRecycler.setAdapter(historyAdapter);
    }

    private class HistoryAdapter extends BaseQuickAdapter<String, BaseViewHolder>{

        public HistoryAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.item_name_tv, item);
        }
    }
}
