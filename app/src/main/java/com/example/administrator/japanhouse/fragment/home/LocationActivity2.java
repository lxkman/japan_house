package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.CityListBean;
import com.example.administrator.japanhouse.bean.SearchPositonBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.SoftKeyboardTool;
import com.example.administrator.japanhouse.view.MyLetterListView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity2 extends BaseActivity implements View.OnClickListener {
    private MyLetterListView myletterview;
    private TextView tvSideBarHint;
    private RecyclerView recyclerView;
    private CityAdapter adapter;
    private List<String> hotList = new ArrayList<>();
    private RecyclerView hot_recycler;
    private RecyclerView recycler_search_result;
    private EditText et_search;
    private List<CityListBean.DatasEntity> citysList;
    private RelativeLayout rl_root;
    private SuspensionDecoration mDecoration;
    private boolean isJa;
    private SearchResultAdapter searchResultAdapter;
    private List<SearchPositonBean> searchResult;
    private LocationClient mLocClient;
    private TextView tv_mylocation;
    private View headerView;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location2);
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        iv_back= (ImageView) findViewById(R.id.item_title_back);
        iv_back.setOnClickListener(this);
        myletterview = (MyLetterListView) findViewById(R.id.myletterview);
        tvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);
        rl_root = (RelativeLayout) findViewById(R.id.rl_root);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recycler_search_result = (RecyclerView) findViewById(R.id.recycler_search_result);
        et_search = (EditText) findViewById(R.id.et_search);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recycler_search_result.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        initLocation();
        initHot();
        initData();
        initListener();
    }

    private void initHot() {
        headerView = View.inflate(LocationActivity2.this, R.layout.location_recycler_header, null);
        tv_mylocation= (TextView) headerView.findViewById(R.id.tv_mylocation);
        hot_recycler = (RecyclerView) headerView.findViewById(R.id.hot_recycler);
        hot_recycler.setLayoutManager(new GridLayoutManager(LocationActivity2.this, 3));
        hotList.add("");
        hotList.add("");
        hotList.add("");
        hotList.add("");
        hotList.add("");
        hotList.add("");
        hotList.add("");
        HotAdapter hotAdapter = new HotAdapter(R.layout.item_hotcity_layout, hotList);
        hot_recycler.setAdapter(hotAdapter);
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
                tv_mylocation.setText(bdLocation.getCity());
            }
        });
    }

    private void initData() {
        HttpParams params = new HttpParams();
        params.put("languageType", 0);
        OkGo.<CityListBean>post(MyUrls.BASEURL + "/app/city/getcity")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<CityListBean>(LocationActivity2.this, CityListBean.class) {
                    @Override
                    public void onSuccess(Response<CityListBean> response) {
                        int code = response.code();
                        CityListBean body = response.body();
                        List<CityListBean.DatasEntity> datas = body.getDatas();
                        citysList = datas;
                        myletterview.setKeyword(getAllKeys());
                        if (adapter == null) {
                            adapter = new CityAdapter(R.layout.item_city, citysList);
                        }
                        adapter.addHeaderView(headerView);
                        recyclerView.setAdapter(adapter);
                        //设置recyclerview悬停
                        recyclerView.addItemDecoration(mDecoration = new SuspensionDecoration(LocationActivity2.this, citysList).setHeaderViewCount(1));
                    }
                });
    }

    private String[] getAllKeys() {
        String[] keyword = new String[0];
        if (citysList != null && citysList.size() > 0) {
            keyword = new String[citysList.size()];
            for (int i = 0; i < citysList.size(); i++) {
                String key = citysList.get(i).getKey();
                keyword[i] = key;
            }
        }
        return keyword;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_title_back:
                SoftKeyboardTool.closeKeyboard(this);
                finish();
                break;
        }
    }

    private class HotAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public HotAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    private class CityAdapter extends BaseQuickAdapter<CityListBean.DatasEntity, BaseViewHolder> {

        public CityAdapter(@LayoutRes int layoutResId, @Nullable List<CityListBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final CityListBean.DatasEntity item) {
            RecyclerView recyclerView_city = helper.getView(R.id.recyclerView_city);
            recyclerView_city.setLayoutManager(new LinearLayoutManager(LocationActivity2.this));
            ItemCityAdapter itemCityAdapter = new ItemCityAdapter(R.layout.item_city_item, item.getCityList(), item.getCityList().size());
            recyclerView_city.setAdapter(itemCityAdapter);
            itemCityAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent=new Intent();
                    intent.putExtra("cityId",item.getCityList().get(position).getId());
                    intent.putExtra("cityName",isJa?item.getCityList().get(position).getAdministrationNameJpn()
                            :item.getCityList().get(position).getAdministrationNameCn());
                    setResult(1,intent);
                    finish();
                }
            });
        }
    }

    private class ItemCityAdapter extends BaseQuickAdapter<CityListBean.DatasEntity.CityListEntity, BaseViewHolder> {
        int lastsize;

        public ItemCityAdapter(@LayoutRes int layoutResId, @Nullable List<CityListBean.DatasEntity.CityListEntity> data, int size) {
            super(layoutResId, data);
            lastsize = size;
        }

        @Override
        protected void convert(BaseViewHolder helper, CityListBean.DatasEntity.CityListEntity item) {
            helper.setText(R.id.tv_city_item, isJa ? item.getAdministrationNameJpn() : item.getAdministrationNameCn());
            if (helper.getAdapterPosition() == lastsize - 1) {
                helper.setVisible(R.id.view_line, false);
            }
        }
    }

    private class SearchResultAdapter extends BaseQuickAdapter<SearchPositonBean, BaseViewHolder> {

        public SearchResultAdapter(@LayoutRes int layoutResId, @Nullable List<SearchPositonBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SearchPositonBean item) {
            helper.setText(R.id.tv_city_item, item.getName());
        }
    }

    private void initListener() {
        myletterview.setonTouchingLetterChangedListener(new MyLetterListView.onTouchingLetterChangedListener() {
            @Override
            public void changed(int position, String letter, int state) {
                switch (state) {
                    case MyLetterListView.STATE_DOWN:
                        //                        Toast.makeText(MainActivity.this, "按下了第" + position + "个，字母" + letter, Toast.LENGTH_SHORT).show();
                        tvSideBarHint.setVisibility(View.VISIBLE);
                        tvSideBarHint.setText(letter);
                        //                            recyclerView.scrollToPosition(position);//这种滑动不准确
                        ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(position + 1, 0);
                        break;
                    case MyLetterListView.STATE_MOVE:
                        //                        Toast.makeText(MainActivity.this, "移动了第" + position + "个，字母" + letter, Toast.LENGTH_SHORT).show();
                        tvSideBarHint.setVisibility(View.VISIBLE);
                        tvSideBarHint.setText(letter);
                        ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(position + 1, 0);
                        break;
                    case MyLetterListView.STATE_UP:
                        //                        Toast.makeText(MainActivity.this, "手指抬起", Toast.LENGTH_SHORT).show();
                        tvSideBarHint.setVisibility(View.GONE);
                        break;
                }
            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(et_search.getText().toString())) {
                    rl_root.setVisibility(View.VISIBLE);
                    recycler_search_result.setVisibility(View.GONE);
                    return;
                }
                if (getSearchResult().size() > 0) {
                    rl_root.setVisibility(View.GONE);
                    recycler_search_result.setVisibility(View.VISIBLE);
                    searchResult = getSearchResult();
                    searchResultAdapter = new SearchResultAdapter(R.layout.item_city_search_result, searchResult);
                    recycler_search_result.setAdapter(searchResultAdapter);
                    searchResultAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent=new Intent();
                            intent.putExtra("cityId",searchResult.get(position).getId());
                            intent.putExtra("cityName",searchResult.get(position).getName());
                            setResult(1,intent);
                            finish();
                        }
                    });
                } else {
                    rl_root.setVisibility(View.VISIBLE);
                    recycler_search_result.setVisibility(View.GONE);
                }
            }
        });
    }

    private List<SearchPositonBean> getSearchResult() {
        List<SearchPositonBean> list = new ArrayList<>();
        if (citysList != null && citysList.size() > 0) {
            for (int i = 0; i < citysList.size(); i++) {
                List<CityListBean.DatasEntity.CityListEntity> cityList = citysList.get(i).getCityList();
                if (cityList != null && cityList.size() > 0) {
                    for (int i1 = 0; i1 < cityList.size(); i1++) {
                        String xxx = "";
                        if (isJa) {
                            xxx = cityList.get(i1).getAdministrationNameJpn();
                        } else {
                            xxx = cityList.get(i1).getAdministrationNameCn();
                        }
                        if (xxx.contains(et_search.getText().toString())) {
                            SearchPositonBean bean = new SearchPositonBean();
                            bean.setPosition(i);
                            bean.setItem_position(i1);
                            bean.setId(cityList.get(i1).getId());
                            bean.setName(xxx);
                            list.add(bean);
                        }
                    }
                }
            }
        }
        return list;
    }
}