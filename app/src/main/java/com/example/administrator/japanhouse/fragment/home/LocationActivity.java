package com.example.administrator.japanhouse.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.adapter.CityAdapter;
import com.example.administrator.japanhouse.adapter.HeaderRecyclerAndFooterWrapperAdapter;
import com.example.administrator.japanhouse.adapter.ViewHolder;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.CityBean;
import com.example.administrator.japanhouse.utils.DividerItemDecoration;
import com.example.administrator.japanhouse.utils.TUtils;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationActivity extends BaseActivity {

    @BindView(R.id.item_title_back)
    ImageView itemTitleBack;
    @BindView(R.id.search_tv)
    TextView searchTv;
    private LinearLayoutManager mManager;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private SuspensionDecoration mDecoration;
    private RecyclerView cityRecycler;

    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;
    private List<CityBean> cityList;
    private CityAdapter cityAdapter;
    private List<String> hotList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        hotList = new ArrayList<>();
        hotList.add("");
        hotList.add("");
        hotList.add("");
        hotList.add("");
        hotList.add("");
        hotList.add("");
        hotList.add("");
        cityRecycler = (RecyclerView) findViewById(R.id.city_recycler);
        cityRecycler.setLayoutManager(mManager = new LinearLayoutManager(mContext));
        cityAdapter = new CityAdapter(this,cityList);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(cityAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                RecyclerView hotRecycler = holder.getView(R.id.hot_recycler);
                hotRecycler.setNestedScrollingEnabled(false);
                hotRecycler.setLayoutManager(new GridLayoutManager(mContext,3));
                HotcityAdapter hotcityAdapter = new HotcityAdapter(R.layout.item_hotcity_layout, hotList);
                hotRecycler.setAdapter(hotcityAdapter);
                hotcityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        TUtils.showShort(mContext,"北海道");
                    }
                });
            }
        };
        mHeaderAdapter.setHeaderView(R.layout.item_head_mylocation, "");
        cityRecycler.setAdapter(mHeaderAdapter);
        cityRecycler.addItemDecoration(mDecoration = new SuspensionDecoration(this, cityList).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));

        //如果add两个，那么按照先后顺序，依次渲染。
        cityRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

        initDatas(getResources().getStringArray(R.array.provinces));
    }

    /**
     * 组织数据源
     * @param data
     * @return
     */
    private void initDatas(final String[] data) {
        cityList = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            CityBean cityBean = new CityBean();
            cityBean.setCity(data[i]);//设置城市名称
            cityList.add(cityBean);
        }

        mIndexBar.setmSourceDatas(cityList)//设置数据
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount())//设置HeaderView数量
                .invalidate();

        cityAdapter.setDatas(cityList);
        mHeaderAdapter.notifyDataSetChanged();
        mDecoration.setmDatas(cityList);
    }

    private class HotcityAdapter extends BaseQuickAdapter<String, BaseViewHolder>{

        public HotcityAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }


    @OnClick({R.id.item_title_back, R.id.search_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_title_back:
                finish();
                break;
            case R.id.search_tv:
                break;
        }
    }
}
