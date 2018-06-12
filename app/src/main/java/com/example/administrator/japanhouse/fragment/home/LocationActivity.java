package com.example.administrator.japanhouse.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
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
import com.example.administrator.japanhouse.bean.CityListBean;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.utils.DividerItemDecoration;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.SoftKeyboardTool;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationActivity extends BaseActivity {

    @BindView(R.id.item_title_back)
    ImageView itemTitleBack;
    @BindView(R.id.search_tv)
    EditText searchTv;
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
    private List<CityBean> cityBeanList;
    private CityAdapter cityAdapter;
    private List<String> hotList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);
        initView();
        initData();
        searchTv.setOnEditorActionListener(editorActionListener);
    }

    private void initData() {
        HttpParams params = new HttpParams();
        params.put("languageType", 0);
        OkGo.<CityListBean>post(MyUrls.BASEURL + "/app/city/getcity")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<CityListBean>(LocationActivity.this, CityListBean.class) {
                    @Override
                    public void onSuccess(Response<CityListBean> response) {
                        int code = response.code();
                        CityListBean body = response.body();
                        List<CityListBean.DatasEntity> datas = body.getDatas();
                        List<String> citysList = new ArrayList<String>();
                        if (datas != null && datas.size() > 0) {
                            for (int i = 0; i < datas.size(); i++) {
                                CityListBean.DatasEntity datasEntity = datas.get(i);
//                                String administrationNameCn = datasEntity.getAdministrationNameCn();
//                                citysList.add(administrationNameCn);
                            }
                        }
                        initDatas(citysList);
                    }
                });
    }

    TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //                SoftKeyboardTool.closeKeyboard(mSearchEt);//关闭软键盘
                searchTv.setFocusable(true);
                searchTv.setFocusableInTouchMode(true);
                return true;
            }
            return false;
        }
    };

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
        cityAdapter = new CityAdapter(this, cityBeanList);
        cityAdapter.setOnItemClickListener(new CityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                EventBus.getDefault().postSticky(new EventBean("changecity", cityBeanList.get(position - 1).getCity()));
                finish();
            }
        });
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(cityAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                RecyclerView hotRecycler = holder.getView(R.id.hot_recycler);
                hotRecycler.setNestedScrollingEnabled(false);
                hotRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
                HotcityAdapter hotcityAdapter = new HotcityAdapter(R.layout.item_hotcity_layout, hotList);
                hotRecycler.setAdapter(hotcityAdapter);
                hotcityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        //                        TUtils.showShort(mContext,"北海道");
                        EventBus.getDefault().postSticky(new EventBean("changecity", "北海道"));
                        finish();
                    }
                });
            }
        };
        mHeaderAdapter.setHeaderView(R.layout.item_head_mylocation, "");
        cityRecycler.setAdapter(mHeaderAdapter);
        cityRecycler.addItemDecoration(mDecoration = new SuspensionDecoration(this, cityBeanList).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));

        //如果add两个，那么按照先后顺序，依次渲染。
        cityRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

    }

    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final List<String> data) {
        cityBeanList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            CityBean cityBean = new CityBean();
            cityBean.setCity(data.get(i));//设置城市名称
            cityBeanList.add(cityBean);
        }

        mIndexBar.setmSourceDatas(cityBeanList)//设置数据
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount())//设置HeaderView数量
                .invalidate();

        cityAdapter.setDatas(cityBeanList);
        mHeaderAdapter.notifyDataSetChanged();
        mDecoration.setmDatas(cityBeanList);
    }

    private class HotcityAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

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
                SoftKeyboardTool.closeKeyboard(this);
                finish();
                break;
            case R.id.search_tv:
                //startActivity(new Intent(mContext,HomeSearchActivity.class));
                break;
        }
    }
}
