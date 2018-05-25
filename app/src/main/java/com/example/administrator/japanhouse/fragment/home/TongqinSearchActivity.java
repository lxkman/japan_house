package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.TongQinHistroyBean;
import com.example.administrator.japanhouse.utils.SpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TongqinSearchActivity extends BaseActivity {

    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.cancle_tv)
    TextView cancleTv;
    @BindView(R.id.history_recycler)
    RecyclerView historyRecycler;
    @BindView(R.id.view_rl)
    RelativeLayout view_rl;
    @BindView(R.id.history_clear)
    ImageView historyClear;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.location_rl)
    RelativeLayout locationRl;
    @BindView(R.id.search_list_recycler)
    RecyclerView searchListRecycler;
    @BindView(R.id.nestScroll)
    NestedScrollView nestScroll;
    private List<TongQinHistroyBean> mHistoryList = new ArrayList<>();
    private HistoryAdapter historyAdapter;
    private LocationClient mLocClient;
    private String city;
    private PoiSearch poiSearch;
    private List<PoiInfo> addressList = new ArrayList<>();
    private SearchListAdapter searchListAdapter;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongqin_search);
        ButterKnife.bind(this);
        initView();
        searchEt.setOnEditorActionListener(editorActionListener);
        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(poiListener);
        initLocation();
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(searchEt.getText().toString())) {
                    poiSearch.searchInCity((new PoiCitySearchOption())
                            .city(city)
                            .keyword(searchEt.getText().toString())
                            .pageNum(10));
                } else {
                    addressList.clear();
                    searchListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initLocation() {
        mLocClient = new LocationClient(mContext);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setNeedDeviceDirect(true);// 设置返回结果包含手机的方向
        option.setOpenGps(true);
        option.setAddrType("all");// 返回的定位结果包含地址信息
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
        option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        option.setIsNeedLocationPoiList(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
        mLocClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                double longitude = bdLocation.getLongitude();
                double latitude = bdLocation.getLatitude();
                city = bdLocation.getCity();
                String addrStr = bdLocation.getAddrStr();
                tvLocation.setText(addrStr);
            }
        });
    }

    OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {

        public void onGetPoiResult(PoiResult result) {
            //获取POI检索结果
            addressList = result.getAllPoi();
            if (addressList != null && addressList.size() > 0) {
                nestScroll.setVisibility(View.GONE);
                searchListRecycler.setVisibility(View.VISIBLE);
                searchListAdapter = new SearchListAdapter(R.layout.search_list_item, addressList);
                searchListRecycler.setAdapter(searchListAdapter);
                searchListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        doSavehistory(addressList.get(position));
                        Intent intent = new Intent();
                        intent.putExtra("address", addressList.get(position).address);
                        LatLng location = addressList.get(position).location;
                        double latitude = location.latitude;
                        double longitude = location.longitude;
                        intent.putExtra("latitude", latitude);
                        intent.putExtra("longitude", longitude);
                        setResult(1, intent);
                        finish();
                    }
                });
            } else {
                Toast.makeText(mContext, "查询不到此地址", Toast.LENGTH_SHORT).show();
            }
        }

        public void onGetPoiDetailResult(PoiDetailResult result) {
            //获取Place详情页检索结果
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    };

    private class SearchListAdapter extends BaseQuickAdapter<PoiInfo, BaseViewHolder> {

        public SearchListAdapter(@LayoutRes int layoutResId, @Nullable List<PoiInfo> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, PoiInfo item) {
            helper.setText(R.id.tv_Search_list, item.address);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        poiSearch.destroy();
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
        historyRecycler.setNestedScrollingEnabled(false);
        historyRecycler.setLayoutManager(new LinearLayoutManager(this));
        searchListRecycler.setNestedScrollingEnabled(false);
        searchListRecycler.setLayoutManager(new LinearLayoutManager(this));
        mHistoryList = getHistory();
        historyAdapter = new HistoryAdapter(R.layout.item_history_search, mHistoryList);
        historyRecycler.setAdapter(historyAdapter);
        historyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                doSavehistory2(mHistoryList.get(position));
                Intent intent = new Intent();
                intent.putExtra("address", mHistoryList.get(position).getAddress());
                double latitude = mHistoryList.get(position).getLatitude();
                double longitude = mHistoryList.get(position).getLongitude();
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                setResult(1, intent);
                finish();
            }
        });
    }

    @OnClick({R.id.cancle_tv, R.id.history_clear, R.id.location_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancle_tv:
                finish();
                break;
            case R.id.history_clear:
                mHistoryList.clear();
                historyAdapter.notifyDataSetChanged();
                break;
            case R.id.location_rl:
                finish();
                Intent intent = new Intent();
                intent.putExtra("address", "");
                setResult(1, intent);
                break;
        }
    }

    private class HistoryAdapter extends BaseQuickAdapter<TongQinHistroyBean, BaseViewHolder> {

        public HistoryAdapter(int layoutResId, @Nullable List<TongQinHistroyBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, TongQinHistroyBean item) {
            helper.setText(R.id.item_name_tv, item.getAddress());
        }
    }

    //判断本地数据中有没有存在搜索过的数据，查重
    private boolean isHasSelectData(String content) {
        if (mHistoryList == null || mHistoryList.size() == 0) {
            return false;
        }
        for (int i = 0; i < mHistoryList.size(); i++) {
            if (mHistoryList.get(i).getAddress().equals(content)) {
                position = i;
                return true;
            }
        }
        return false;
    }

    private void doSavehistory(PoiInfo poiInfo) {

        if (isHasSelectData(poiInfo.address)) {//查重
            mHistoryList.remove(position);
        }
        //后来搜索的文字放在集合中的第一个位置
        mHistoryList.add(0, new TongQinHistroyBean(poiInfo.address, poiInfo.location.latitude, poiInfo.location.longitude));

        if (mHistoryList.size() == 6) {//实现本地历史搜索记录最多不超过5个
            mHistoryList.remove(5);
        }
        //将这个mHistoryListData保存到sp中，其实sp中保存的就是这个mHistoryListData集合
        saveHistory();
    }

    private void doSavehistory2(TongQinHistroyBean histroyBean) {

        if (isHasSelectData(histroyBean.getAddress())) {//查重
            mHistoryList.remove(position);
        }
        //后来搜索的文字放在集合中的第一个位置
        mHistoryList.add(0, new TongQinHistroyBean(histroyBean.getAddress(), histroyBean.getLatitude()
                , histroyBean.getLongitude()));

        if (mHistoryList.size() == 6) {//实现本地历史搜索记录最多不超过5个
            mHistoryList.remove(5);
        }
        //将这个mHistoryListData保存到sp中，其实sp中保存的就是这个mHistoryListData集合
        saveHistory();
    }

    /**
     * 保存历史查询记录
     */
    private void saveHistory() {
        SpUtils.putString("tongqinhistory",
                new Gson().toJson(mHistoryList));//将java对象转换成json字符串进行保存
    }

    /**
     * 获取历史查询记录
     *
     * @return
     */
    private List<TongQinHistroyBean> getHistory() {
        String historyJson = SpUtils.getString("tongqinhistory", "");
        if (historyJson != null && !historyJson.equals("")) {//必须要加上后面的判断，因为获取的字符串默认值就是空字符串
            //将json字符串转换成list集合
            return new Gson().fromJson(historyJson, new TypeToken<List<TongQinHistroyBean>>() {
            }.getType());
        }
        return new ArrayList<TongQinHistroyBean>();
    }
}
