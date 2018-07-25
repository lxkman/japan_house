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
    private RecyclerView hot_recycler;
    private RecyclerView recycler_search_result;
    private EditText et_search;
    private List<CityListBean.DatasEntity.CitysEntity> citysList;
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
        iv_back = (ImageView) findViewById(R.id.item_title_back);
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
        headerView = View.inflate(LocationActivity2.this, R.layout.location_recycler_header, null);
        tv_mylocation = (TextView) headerView.findViewById(R.id.tv_mylocation);
        tv_mylocation.setOnClickListener(this);
        hot_recycler = (RecyclerView) headerView.findViewById(R.id.hot_recycler);
        hot_recycler.setLayoutManager(new GridLayoutManager(LocationActivity2.this, 3));
        initLocation();
        initData();
        initListener();
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
                String country = bdLocation.getCountry();
                if (!country.equals("日本")) {
                    String cityName1 = CacheUtils.get("cityName");
                    if (!TextUtils.isEmpty(cityName1)) {
                        tv_mylocation.setText(cityName1);
                    } else {
                        String cityName = getResources().getString(R.string.dongjing);
                        tv_mylocation.setText(cityName);
                    }
                } else {
                    tv_mylocation.setText(bdLocation.getCity());
                }
            }
        });
    }

    private void initData() {
        HttpParams params = new HttpParams();
        if (isJa) {
            params.put("languageType", 1);
        } else {
            params.put("languageType", 0);
        }
        OkGo.<CityListBean>post(MyUrls.BASEURL + "/app/city/getcity")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<CityListBean>(LocationActivity2.this, CityListBean.class) {
                    @Override
                    public void onSuccess(Response<CityListBean> response) {
                        int code = response.code();
                        CityListBean body = response.body();
                        CityListBean.DatasEntity datas = body.getDatas();
                        List<CityListBean.DatasEntity.CitysEntity> citys = datas.getCitys();
                        citysList = citys;
                        myletterview.setKeyword(getAllKeys());
                        if (adapter == null) {
                            adapter = new CityAdapter(R.layout.item_city, citysList);
                        }
                        adapter.addHeaderView(headerView);
                        recyclerView.setAdapter(adapter);
                        //设置recyclerview悬停
                        recyclerView.addItemDecoration(mDecoration = new SuspensionDecoration(LocationActivity2.this, citysList).setHeaderViewCount(1));
                        //热门城市
                        final List<CityListBean.DatasEntity.HotcitesEntity> hotcites = datas.getHotcites();
                        if (hotcites != null && hotcites.size() > 0) {
                            HotAdapter hotAdapter = new HotAdapter(R.layout.item_hotcity_layout, hotcites);
                            hot_recycler.setAdapter(hotAdapter);
                            hotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent();
                                    intent.putExtra("cityId", hotcites.get(position).getId());
                                    intent.putExtra("cityName", isJa ? hotcites.get(position).getAdministrationNameJpn() : hotcites.get(position).getAdministrationNameCn());
                                    intent.putExtra("jingdu", hotcites.get(position).getLongitude());
                                    intent.putExtra("weidu", hotcites.get(position).getLatitude());
                                    setResult(1, intent);
                                    finish();
                                }
                            });
                        }
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
            case R.id.tv_mylocation:
                SoftKeyboardTool.closeKeyboard(this);
                if (!isHasThisCity()) {
                    Intent intent = new Intent();
                    intent.putExtra("cityId", 2);
                    intent.putExtra("cityName", getResources().getString(R.string.dongjing));
                    setResult(1, intent);
                    finish();
                }
                break;
        }
    }

    private boolean isHasThisCity() {
        if (citysList != null && citysList.size() > 0) {
            for (int i = 0; i < citysList.size(); i++) {
                CityListBean.DatasEntity.CitysEntity datasEntity = citysList.get(i);
                String cityName = tv_mylocation.getText().toString();
                List<CityListBean.DatasEntity.CitysEntity.CityListEntity> cityList = datasEntity.getCityList();
                if (cityList != null && cityList.size() > 0) {
                    for (int i1 = 0; i1 < cityList.size(); i1++) {
                        String administrationNameCn = cityList.get(i1).getAdministrationNameCn();
                        if (!TextUtils.isEmpty(cityName) && cityName.equals(administrationNameCn)) {
                            Intent intent = new Intent();
                            intent.putExtra("cityId", cityList.get(i1).getId());
                            intent.putExtra("cityName", isJa ? cityList.get(i1).getAdministrationNameJpn() : cityList.get(i1).getAdministrationNameCn());
                            intent.putExtra("jingdu", cityList.get(i1).getLongitude());
                            intent.putExtra("weidu", cityList.get(i1).getLatitude());
                            setResult(1, intent);
                            finish();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private class HotAdapter extends BaseQuickAdapter<CityListBean.DatasEntity.HotcitesEntity, BaseViewHolder> {

        public HotAdapter(@LayoutRes int layoutResId, @Nullable List<CityListBean.DatasEntity.HotcitesEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CityListBean.DatasEntity.HotcitesEntity item) {
            helper.setText(R.id.tv_name, isJa ? item.getAdministrationNameJpn() : item.getAdministrationNameCn());
        }
    }

    private class CityAdapter extends BaseQuickAdapter<CityListBean.DatasEntity.CitysEntity, BaseViewHolder> {

        public CityAdapter(@LayoutRes int layoutResId, @Nullable List<CityListBean.DatasEntity.CitysEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final CityListBean.DatasEntity.CitysEntity item) {
            RecyclerView recyclerView_city = helper.getView(R.id.recyclerView_city);
            recyclerView_city.setLayoutManager(new LinearLayoutManager(LocationActivity2.this));
            ItemCityAdapter itemCityAdapter = new ItemCityAdapter(R.layout.item_city_item, item.getCityList(), item.getCityList().size());
            recyclerView_city.setAdapter(itemCityAdapter);
            itemCityAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent();
                    intent.putExtra("cityId", item.getCityList().get(position).getId());
                    intent.putExtra("cityName", isJa ? item.getCityList().get(position).getAdministrationNameJpn()
                            : item.getCityList().get(position).getAdministrationNameCn());
                    intent.putExtra("jingdu", item.getCityList().get(position).getLongitude());
                    intent.putExtra("weidu", item.getCityList().get(position).getLatitude());
                    setResult(1, intent);
                    finish();
                }
            });
        }
    }

    private class ItemCityAdapter extends BaseQuickAdapter<CityListBean.DatasEntity.CitysEntity.CityListEntity, BaseViewHolder> {
        int lastsize;

        public ItemCityAdapter(@LayoutRes int layoutResId, @Nullable List<CityListBean.DatasEntity.CitysEntity.CityListEntity> data, int size) {
            super(layoutResId, data);
            lastsize = size;
        }

        @Override
        protected void convert(BaseViewHolder helper, CityListBean.DatasEntity.CitysEntity.CityListEntity item) {
            helper.setText(R.id.tv_city_item, isJa ? item.getAdministrationNameJpn() : item.getAdministrationNameCn());
//            if (helper.getAdapterPosition() == lastsize - 1) {
//                helper.setVisible(R.id.view_line, false);
//            }
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
                            Intent intent = new Intent();
                            intent.putExtra("cityId", searchResult.get(position).getId());
                            intent.putExtra("cityName", searchResult.get(position).getName());
                            intent.putExtra("jingdu", searchResult.get(position).getJingdu());
                            intent.putExtra("weidu", searchResult.get(position).getWeidu());
                            setResult(1, intent);
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
                List<CityListBean.DatasEntity.CitysEntity.CityListEntity> cityList = citysList.get(i).getCityList();
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
                            bean.setJingdu(cityList.get(i1).getLongitude());
                            bean.setWeidu(cityList.get(i1).getLatitude());
                            list.add(bean);
                        }
                    }
                }
            }
        }
        return list;
    }
}
