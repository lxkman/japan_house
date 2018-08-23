package com.haiwai.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.activity.AgentListActivity;
import com.haiwai.administrator.japanhouse.activity.FreeApartmentActivity;
import com.haiwai.administrator.japanhouse.activity.OwnerActivity;
import com.haiwai.administrator.japanhouse.adapter.MyGridViewAdpter;
import com.haiwai.administrator.japanhouse.adapter.MyViewPagerAdapter;
import com.haiwai.administrator.japanhouse.base.BaseFragment;
import com.haiwai.administrator.japanhouse.bean.CityListBean;
import com.haiwai.administrator.japanhouse.bean.EventBean;
import com.haiwai.administrator.japanhouse.bean.HomeItemBean;
import com.haiwai.administrator.japanhouse.bean.HomePageBean;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.callback.JsonCallback;
import com.haiwai.administrator.japanhouse.fragment.chat.ManagerActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.GaoerfuDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.HaiWaiDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.JiudianDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.NewHousedetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.OldHousedetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.ShangpuDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.TudidetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.XiezilouDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.ZhongguoDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.ZuHousedetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.home.ui.activity.Buyhouse_Baike_Activity;
import com.haiwai.administrator.japanhouse.fragment.home.ui.activity.Daikuan_Activity;
import com.haiwai.administrator.japanhouse.fragment.home.ui.activity.QuestionActivity;
import com.haiwai.administrator.japanhouse.fragment.home.ui.activity.ToutiaoActivity;
import com.haiwai.administrator.japanhouse.fragment.home.ui.activity.ZhinengActivity;
import com.haiwai.administrator.japanhouse.utils.BannerUtils;
import com.haiwai.administrator.japanhouse.utils.CacheUtils;
import com.haiwai.administrator.japanhouse.utils.Constants;
import com.haiwai.administrator.japanhouse.utils.GlideReqUtils;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.haiwai.administrator.japanhouse.utils.MyUtils;
import com.haiwai.administrator.japanhouse.view.RatingBarView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.haiwai.administrator.japanhouse.R.id.re_top_bg;

/**
 * Created by Administrator on 2018/4/8.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.location_tv)
    TextView locationTv;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.map_tv)
    TextView mapTv;
    @BindView(R.id.jrdk_tv)
    TextView jrdkTv;
    @BindView(R.id.gfbk_tv)
    TextView gfbkTv;
    @BindView(R.id.fcwd_tv)
    TextView fcwdTv;
    @BindView(R.id.znmf_tv)
    TextView znmfTv;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.points)
    LinearLayout points;
    @BindView(R.id.tantan_tv)
    TextView tantanTv;
    @BindView(R.id.banner)
    Banner banner;
    Unbinder unbinder;
    @BindView(R.id.tjxf_more_tv)
    TextView tjxfMoreTv;
    @BindView(R.id.tjxf_recycler)
    RecyclerView tjxfRecycler;
    @BindView(R.id.tjesf_more_tv)
    TextView tjesfMoreTv;
    @BindView(R.id.tjesf_recycler)
    RecyclerView tjesfRecycler;
    @BindView(R.id.tjyxjjr_more_tv)
    TextView tjyxjjrMoreTv;
    @BindView(R.id.tjyxjjr_recycler)
    RecyclerView tjyxjjrRecycler;
    @BindView(R.id.tjzf_more_tv)
    TextView tjzfMoreTv;
    @BindView(R.id.tjzf_recycler)
    RecyclerView tjzfRecycler;
    @BindView(R.id.tjtd_more_tv)
    TextView tjtdMoreTv;
    @BindView(R.id.tjtd_recycler)
    RecyclerView tjtdRecycler;
    @BindView(R.id.cnxh_recycler)
    RecyclerView cnxhRecycler;
    @BindView(R.id.view_011)
    LinearLayout view011;
    @BindView(R.id.srcollview)
    NestedScrollView scrollView;
    @BindView(re_top_bg)
    RelativeLayout reTopBg;
    @BindView(R.id.iv_tantan)
    ImageView ivTantan;
    private int mDistanceY;
    private int totalPage; //总的页数
    private int mPageSize = 10; //每页显示的最大的数量
    private ImageView[] ivPoints;//小圆点图片的集合
    private List<HomeItemBean> homeItemBeanList;//总的数据源
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private boolean isJa;
    private LocationClient mLocClient;
    private int[] itemPic = {
            R.drawable.home_xinfang_iv,
            R.drawable.home_bieshu_iv,
            R.drawable.home_tudi_iv,
            R.drawable.home_ershoufang_iv,
            R.drawable.home_shangyedichan_iv,
            R.drawable.home_zufang_iv,
            R.drawable.home_zhaoxiaoqu_iv,
            R.drawable.home_fangjiaditu_iv,
            R.drawable.home_woshiyezhu_iv,
            R.drawable.home_mianfeikanfang_iv,
            R.drawable.home_zhongguofangyuan_iv,
            R.drawable.home_haiwaidichan_iv
    };

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
//        initLocation();
        initLocation2();
        initViewData();
        initScroll();
        return view;
    }

    private void initLocation2() {
        String cityName = getResources().getString(R.string.dongjing);
        CacheUtils.put("cityName", cityName);
        locationTv.setText(cityName);
        CacheUtils.put("cityId", 2);
        CacheUtils.put("mylongitude", 139.46);
        CacheUtils.put("mylatitude", 35.42);
        initData();
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
                String country = bdLocation.getCountry();
                if (!country.equals("日本")) {
                    String cityName = getResources().getString(R.string.dongjing);
                    CacheUtils.put("cityName", cityName);
                    locationTv.setText(cityName);
                    CacheUtils.put("cityId", 2);
                    CacheUtils.put("mylongitude", 139.46);
                    CacheUtils.put("mylatitude", 35.42);
                    initData();
                } else {
                    double longitude = bdLocation.getLongitude();
                    CacheUtils.put("mylongitude", longitude);
                    double latitude = bdLocation.getLatitude();
                    CacheUtils.put("mylatitude", latitude);
                    String city = bdLocation.getCity();
                    initLocationData(city);
                }
            }
        });
    }

    private void initLocationData(final String city) {
        HttpParams params = new HttpParams();
        params.put("languageType", 0);
        OkGo.<CityListBean>post(MyUrls.BASEURL + "/app/city/getcity")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<CityListBean>(CityListBean.class) {
                    @Override
                    public void onSuccess(Response<CityListBean> response) {
                        int code = response.code();
                        CityListBean body = response.body();
                        CityListBean.DatasEntity datas = body.getDatas();
                        List<CityListBean.DatasEntity.CitysEntity> citysList = datas.getCitys();
                        if (citysList != null && citysList.size() > 0) {
                            for (int i = 0; i < citysList.size(); i++) {
                                CityListBean.DatasEntity.CitysEntity datasEntity = citysList.get(i);
                                List<CityListBean.DatasEntity.CitysEntity.CityListEntity> cityList = datasEntity.getCityList();
                                if (cityList != null && cityList.size() > 0) {
                                    for (int i1 = 0; i1 < cityList.size(); i1++) {
                                        String administrationNameCn = cityList.get(i1).getAdministrationNameCn();
                                        if (!TextUtils.isEmpty(city) && city.equals(administrationNameCn)) {
                                            CacheUtils.put("cityId", cityList.get(i1).getId());
                                            CacheUtils.put("cityName", cityList.get(i1).getAdministrationNameCn());
                                            String cityName = cityList.get(i1).getAdministrationNameCn();
                                            if (cityName != null && cityName.length() <= 3) {
                                                locationTv.setText(cityName);
                                            } else if (cityName != null && cityName.length() > 3) {
                                                locationTv.setText(cityName.substring(0, 2) + "...");
                                            }
                                            //不放在这会报空指针，cityId获取到后才能initData
                                            initData();
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String cityName = data.getStringExtra("cityName");
        if (cityName != null && cityName.length() <= 3) {
            locationTv.setText(cityName);
        } else if (cityName != null && cityName.length() > 3) {
            locationTv.setText(cityName.substring(0, 2) + "...");
        }
        int cityId = data.getIntExtra("cityId", 0);
        int cityId2 = CacheUtils.get("cityId");
        if (cityId == cityId2) {
            return;
        }
        CacheUtils.put("cityId", cityId);
        CacheUtils.put("cityName", cityName);
        double jingdu = data.getDoubleExtra("jingdu", 139.46);
        double weidu = data.getDoubleExtra("weidu", 35.42);
        CacheUtils.put("mylongitude", jingdu);
        CacheUtils.put("mylatitude", weidu);
        initData();
        EventBus.getDefault().postSticky(new EventBean("refreshComment"));//刷新推荐的数据
    }

    private void initViewData() {
        String[] itemName = {
                mActivity.getResources().getString(R.string.new_house),
                mActivity.getResources().getString(R.string.bieshu),
                mActivity.getResources().getString(R.string.tudi),
                mActivity.getResources().getString(R.string.old_house),
                mActivity.getResources().getString(R.string.shangyedichan),
                mActivity.getResources().getString(R.string.zu_house),
                mActivity.getResources().getString(R.string.zhaoxiaoqu),
                mActivity.getResources().getString(R.string.fangjiaditu),
                mActivity.getResources().getString(R.string.woshiyezhu),
                mActivity.getResources().getString(R.string.mianfeikanfang),
                mActivity.getResources().getString(R.string.zhongguofangyuan),
                mActivity.getResources().getString(R.string.haiwaidichan)
        };
        //-----item导航栏-----
        homeItemBeanList = new ArrayList<>();
        for (int i = 0; i < itemName.length; i++) {
            homeItemBeanList.add(new HomeItemBean(itemName[i], itemPic[i]));
        }
        //总的页数向上取整
        totalPage = (int) Math.ceil(homeItemBeanList.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) View.inflate(mContext, R.layout.item_gridview, null);
            gridView.setAdapter(new MyGridViewAdpter(mContext, homeItemBeanList, i, mPageSize));
            //添加item点击监听
            final int finalI = i;
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    // TODO Auto-generated method stub
                    Object obj = gridView.getItemAtPosition(position);
                    if (obj != null && obj instanceof HomeItemBean) {
                        switch (position) {
                            case 0:
                                if (finalI == 0) {
                                    startActivity(new Intent(mContext, NewHouseActivity.class));
                                } else {
                                    startActivity(new Intent(mContext, ChineseFangyuanActivity.class));
                                }
                                break;
                            case 1:
                                if (finalI == 0) {
                                    startActivity(new Intent(mContext, BieShuActivity.class));
                                } else {
                                    startActivity(new Intent(mContext, HaiWaiActivity.class));
                                }
                                break;
                            case 2://土地
                                startActivity(new Intent(mContext, TudiActivity.class));
                                break;
                            case 3://二手房
                                startActivity(new Intent(mContext, ErshoufangActiviy.class));
                                break;
                            case 4://商业地产
                                startActivity(new Intent(mContext, BusinessDichanActivity.class));
                                break;
                            case 5://租房
                                startActivity(new Intent(mContext, ZufangActivity.class));
                                break;
                            case 6://找小区
                                startActivity(new Intent(mContext, ZhaoxiaoquActivity.class));
                                break;
                            case 7://房价地图
                                startActivity(new Intent(mContext, FangjiadituActivity.class));
                                break;
                            case 8://我是业主
                                startActivity(new Intent(mContext, OwnerActivity.class));
                                break;
                            case 9://免费看房
                                startActivity(new Intent(mContext, FreeApartmentActivity.class));
                                break;
                        }
                    }
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        //设置ViewPager适配器
        viewpager.setAdapter(new MyViewPagerAdapter(viewPagerList));
        //添加小圆点
        ivPoints = new ImageView[totalPage];
        for (int i = 0; i < totalPage; i++) {
            //循坏加入点点图片组
            ivPoints[i] = new ImageView(mContext);
            if (i == 0) {
                ivPoints[i].setImageResource(R.drawable.dot_selected);
            } else {
                ivPoints[i].setImageResource(R.drawable.dot_unselected);
            }
            ivPoints[i].setPadding(8, 8, 8, 8);
            points.addView(ivPoints[i]);
        }
        //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
        viewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //currentPage = position;
                for (int i = 0; i < totalPage; i++) {
                    if (i == position) {
                        ivPoints[i].setImageResource(R.drawable.dot_selected);
                    } else {
                        ivPoints[i].setImageResource(R.drawable.dot_unselected);
                    }
                }
            }
        });
        //-----城市探探-----
        //        tantanTv.requestFocus();
        tjxfRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        tjxfRecycler.setNestedScrollingEnabled(false);
        tjesfRecycler.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        tjesfRecycler.setLayoutManager(linearLayoutManager);
        tjyxjjrRecycler.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        tjyxjjrRecycler.setLayoutManager(linearLayoutManager1);
        tjzfRecycler.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        tjzfRecycler.setLayoutManager(linearLayoutManager2);
        cnxhRecycler.setNestedScrollingEnabled(false);
        cnxhRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        tjtdRecycler.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(mContext);
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        tjtdRecycler.setLayoutManager(linearLayoutManager3);
        if (isJa) {
            ivTantan.setImageResource(R.drawable.tatan_ja);
        } else {
            ivTantan.setImageResource(R.drawable.home_paoma_iv);
        }
    }

    private void initData() {
        HttpParams params = new HttpParams();
        int cityId = CacheUtils.get("cityId");
        params.put("cId", cityId);
        if (isJa) {
            params.put("languageType", 1);
        } else {
            params.put("languageType", 0);
        }
        OkGo.<HomePageBean>post(MyUrls.BASEURL + "/app/index/init")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<HomePageBean>(mActivity, HomePageBean.class) {
                    @Override
                    public void onSuccess(Response<HomePageBean> response) {
                        HomePageBean body = response.body();
                        final HomePageBean.DatasEntity datas = body.getDatas();
                        final List<HomePageBean.DatasEntity.CnxhEntity> cnxh = datas.getCnxh();//猜你喜欢
                        final List<HomePageBean.DatasEntity.ImagesEntity> images = datas.getImages();//banner
                        final List<HomePageBean.DatasEntity.TjesfEntity> tjesf = datas.getTjesf();//推荐二手房
                        final List<HomePageBean.DatasEntity.TjjjrEntity> tjjjr = datas.getTjjjr();//推荐经纪人
                        final List<HomePageBean.DatasEntity.TjtdEntity> tjtd = datas.getTjtd();//推荐土地
                        final List<HomePageBean.DatasEntity.TjxfEntity> tjxf = datas.getTjxf();//推荐新房
                        final List<HomePageBean.DatasEntity.TjzfEntity> tjzf = datas.getTjzf();//推荐租房
                        List<HomePageBean.DatasEntity.CsttEntity> cstt = datas.getCstt();//楼市探探
                        if (cstt != null && cstt.size() > 0) {
                            tantanTv.setText(isJa ? cstt.get(0).getTitleJpn() : cstt.get(0).getTitleCn());
                        }
                        //-----banner-----
                        List<String> bannerList = new ArrayList<>();
                        if (images != null && images.size() > 0) {
                            for (int i = 0; i < images.size(); i++) {
                                String imageUrl = images.get(i).getImageUrl();
                                bannerList.add(imageUrl);
                            }
                        }
                        BannerUtils.startBanner(banner, bannerList);
                        banner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                int linkHtype = images.get(position).getLinkHtype();
                                int linkShtype = images.get(position).getLinkShtype();
                                Intent intent = null;
                                switch (linkHtype) {
                                    case 0:
                                        intent = new Intent(mContext, OldHousedetailsActivity.class);
                                        break;
                                    case 1:
                                        intent = new Intent(mContext, NewHousedetailsActivity.class);
                                        break;
                                    case 2:
                                        intent = new Intent(mContext, ZuHousedetailsActivity.class);
                                        if (linkShtype == 5) {
                                            intent.putExtra("houseType", "duoceng");
                                        } else if (linkShtype == 4) {
                                            intent.putExtra("houseType", "xuesheng");
                                        } else if (linkShtype == 3) {
                                            intent.putExtra("houseType", "erceng");
                                        } else if (linkShtype == 2) {
                                            intent.putExtra("houseType", "bieshu");
                                        } else if (linkShtype == 1) {
                                            intent.putExtra("houseType", "shangpu");
                                        } else if (linkShtype == 0) {
                                            intent.putExtra("houseType", "bangongshi");
                                        } else if (linkShtype == 6) {
                                            intent.putExtra("houseType", "zhaotuandi");
                                        }
                                        break;
                                    case 3:
                                        intent = new Intent(mContext, TudidetailsActivity.class);
                                        break;
                                    case 4:
                                        intent = new Intent(mContext, BieshudetailsActivity.class);
                                        break;
                                    case 5:
                                        if (linkShtype == 0) {
                                            intent = new Intent(mContext, JiudianDetailsActivity.class);
                                        } else if (linkShtype == 1) {
                                            intent = new Intent(mContext, GaoerfuDetailsActivity.class);
                                        } else if (linkShtype == 2) {
                                            intent = new Intent(mContext, XiezilouDetailsActivity.class);
                                        } else if (linkShtype == 3) {
                                            intent = new Intent(mContext, ShangpuDetailsActivity.class);
                                        }
                                        break;
                                    case 6:
                                        intent = new Intent(mContext, ZhongguoDetailsActivity.class);
                                        break;
                                    case 7:
                                        intent = new Intent(mContext, HaiWaiDetailsActivity.class);
                                        break;
                                }
                                intent.putExtra("houseId", images.get(position).getHid() + "");
                                startActivity(intent);
                            }
                        });
                        //-----推荐新房-----
                        TjxfAdapter adapter = new TjxfAdapter(R.layout.item_home_tjxf, tjxf);
                        tjxfRecycler.setAdapter(adapter);
                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(mContext, NewHousedetailsActivity.class);
                                intent.putExtra("houseId", tjxf.get(position).getId() + "");
                                startActivity(intent);
                            }
                        });
                        //-----推荐二手房-----
                        TjesfAdapter tjesfAdapter = new TjesfAdapter(R.layout.item_tjesf_layout, tjesf);
                        tjesfRecycler.setAdapter(tjesfAdapter);
                        tjesfAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(mContext, OldHousedetailsActivity.class);
                                intent.putExtra("houseId", tjesf.get(position).getId() + "");
                                startActivity(intent);
                            }
                        });
                        //-----推荐优秀经纪人-----
                        TjyxjjrAdapter tjyxjjrAdapter = new TjyxjjrAdapter(R.layout.item_tjyxjjr_layout, tjjjr);
                        tjyxjjrRecycler.setAdapter(tjyxjjrAdapter);
                        tjyxjjrAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent Managerintent = new Intent(mContext, ManagerActivity.class);
                                Managerintent.putExtra("ManagerId", tjjjr.get(position).getId() + "");
                                startActivity(Managerintent);
                            }
                        });
                        //-----推荐租房-----
                        TjzfAdapter tjzfAdapter = new TjzfAdapter(R.layout.item_tjzf_layout, tjzf);
                        tjzfRecycler.setAdapter(tjzfAdapter);
                        tjzfAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(mContext, ZuHousedetailsActivity.class);
                                String houseType = tjzf.get(position).getHouseType();
                                if (houseType.equals("5")) {
                                    intent.putExtra("houseType", "duoceng");
                                } else if (houseType.equals("4")) {
                                    intent.putExtra("houseType", "xuesheng");
                                } else if (houseType.equals("3")) {
                                    intent.putExtra("houseType", "erceng");
                                } else if (houseType.equals("2")) {
                                    intent.putExtra("houseType", "bieshu");
                                } else if (houseType.equals("1")) {
                                    intent.putExtra("houseType", "shangpu");
                                } else if (houseType.equals("0")) {
                                    intent.putExtra("houseType", "bangongshi");
                                } else if (houseType.equals("6")) {
                                    intent.putExtra("houseType", "zhaotuandi");
                                }
                                intent.putExtra("houseId", tjzf.get(position).getId() + "");
                                startActivity(intent);
                            }
                        });
                        //-----推荐土地-----
                        TjtdAdapter tjtdAdapter = new TjtdAdapter(R.layout.item_tjtd_layout, tjtd);
                        tjtdRecycler.setAdapter(tjtdAdapter);
                        tjtdAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(mContext, TudidetailsActivity.class);
                                intent.putExtra("houseId", tjtd.get(position).getId() + "");
                                startActivity(intent);
                            }
                        });
                        //-----猜你喜欢-----
                        CnxhAdapter cnxhAdapter = new CnxhAdapter(R.layout.item_cnxh_layout, cnxh);
                        cnxhRecycler.setAdapter(cnxhAdapter);
                        cnxhAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                String status = cnxh.get(position).getStatus();
                                if (TextUtils.isEmpty(status)) {
                                    return;
                                }
                                Intent intent = null;
                                if (status.equals("0")) {
                                    intent = new Intent(mContext, OldHousedetailsActivity.class);
                                } else if (status.equals("1")) {
                                    intent = new Intent(mContext, NewHousedetailsActivity.class);
                                } else if (status.equals("2")) {
                                    intent = new Intent(mContext, ZuHousedetailsActivity.class);
                                    String houseType = cnxh.get(position).getHouseType();
                                    if (houseType.equals("5")) {
                                        intent.putExtra("houseType", "duoceng");
                                    } else if (houseType.equals("4")) {
                                        intent.putExtra("houseType", "xuesheng");
                                    } else if (houseType.equals("3")) {
                                        intent.putExtra("houseType", "erceng");
                                    } else if (houseType.equals("2")) {
                                        intent.putExtra("houseType", "bieshu");
                                    } else if (houseType.equals("1")) {
                                        intent.putExtra("houseType", "shangpu");
                                    } else if (houseType.equals("0")) {
                                        intent.putExtra("houseType", "bangongshi");
                                    } else if (houseType.equals("6")) {
                                        intent.putExtra("houseType", "zhaotuandi");
                                    }
                                }
                                intent.putExtra("houseId", cnxh.get(position).getId() + "");
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onError(Response<HomePageBean> response) {
                        super.onError(response);
                        Log.e("xxx", response.getException().getMessage());
                    }
                });
    }

    @Override
    protected void initLazyData() {

    }

    private void initScroll() {
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //滑动的距离
                mDistanceY += scrollY - oldScrollY;
                //toolbar的高度
                int toolbarHeight = 300;//我写死的高度
//                Log.e("vvvvvvvvvv", "scrollY:" + scrollY + "   " + "oldScrollY:" + oldScrollY);
                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= toolbarHeight) {
                    float scale = (float) mDistanceY / toolbarHeight;
                    float alpha = scale * 255;
                    reTopBg.setBackgroundColor(Color.argb((int) alpha, 199, 151, 127));
                } else {
                    //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
                    //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
                    //将标题栏的颜色设置为完全不透明状态
                    reTopBg.setBackgroundResource(R.color.shihuangse);
                }
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //        tantanTv.requestFocus();//解决切换fragment之后回来不滚动的问题
    }

    private class TjxfAdapter extends BaseQuickAdapter<HomePageBean.DatasEntity.TjxfEntity, BaseViewHolder> {

        public TjxfAdapter(int layoutResId, @Nullable List<HomePageBean.DatasEntity.TjxfEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final HomePageBean.DatasEntity.TjxfEntity item) {
            Glide.with(MyApplication.getGloableContext())
                    .load(TextUtils.isEmpty(item.getVideoImgs()) ? MyUtils.getSpiltText(item.getRoomImgs()) : MyUtils.getSpiltText(item.getVideoImgs()))
                    .apply(GlideReqUtils.getReq())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.tv_title, isJa ? item.getTitleJpn() : item.getTitleCn())
                    .setText(R.id.tv_area, isJa ? item.getSpecificLocationJpn() : item.getSpecificLocationCn())
                    .setText(R.id.tv_mianji, isJa ? item.getAreaJpn() : item.getAreaCn())
                    .setText(R.id.tv_price, isJa ? item.getPriceJpn() : item.getPriceCn());
            RecyclerView itemTjxfRecycler = helper.getView(R.id.item_tjxf_recycler);
            itemTjxfRecycler.setNestedScrollingEnabled(false);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            itemTjxfRecycler.setLayoutManager(linearLayoutManager);
            ItemTjxfAdapter itemTjxfAdapter = new ItemTjxfAdapter(R.layout.item_tjxf_item_layout, item.getImageList());
            itemTjxfRecycler.setAdapter(itemTjxfAdapter);
            itemTjxfAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(mContext, NewHousedetailsActivity.class);
                    intent.putExtra("houseId", item.getId() + "");
                    startActivity(intent);
                }
            });
        }
    }

    private class ItemTjxfAdapter extends BaseQuickAdapter<HomePageBean.DatasEntity.TjxfEntity.ImageListEntity, BaseViewHolder> {

        public ItemTjxfAdapter(int layoutResId, @Nullable List<HomePageBean.DatasEntity.TjxfEntity.ImageListEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomePageBean.DatasEntity.TjxfEntity.ImageListEntity item) {
            Glide.with(MyApplication.getGloableContext()).load(item.getVal())
                    .apply(GlideReqUtils.getReq())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
        }
    }

    private class TjesfAdapter extends BaseQuickAdapter<HomePageBean.DatasEntity.TjesfEntity, BaseViewHolder> {

        public TjesfAdapter(int layoutResId, @Nullable List<HomePageBean.DatasEntity.TjesfEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomePageBean.DatasEntity.TjesfEntity item) {
            Glide.with(MyApplication.getGloableContext())
                    .load(TextUtils.isEmpty(item.getVideoImgs()) ? MyUtils.getSpiltText(item.getRoomImgs()) : MyUtils.getSpiltText(item.getVideoImgs()))
                    .apply(GlideReqUtils.getReq())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.tv_title, isJa ? item.getTitleJpn() : item.getTitleCn())
                    .setText(R.id.tv_area, isJa ? item.getSpecificLocationJpn() : item.getSpecificLocationCn())
                    .setText(R.id.tv_mianji, isJa ? item.getAreaJpn() : item.getAreaCn())
                    .setText(R.id.tv_ting, isJa ? item.getDoorModelJpn() : item.getDoorModelCn())
                    .setText(R.id.tv_price, isJa ? item.getPriceJpn() : item.getPriceCn());
        }
    }

    private class TjyxjjrAdapter extends BaseQuickAdapter<HomePageBean.DatasEntity.TjjjrEntity, BaseViewHolder> {

        public TjyxjjrAdapter(int layoutResId, @Nullable List<HomePageBean.DatasEntity.TjjjrEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomePageBean.DatasEntity.TjjjrEntity item) {
            RatingBarView ratingBarView = helper.getView(R.id.ratingBarView);
            ratingBarView.setRatingCount(5);//设置RatingBarView总数
            ratingBarView.setSelectedCount((int) item.getAvgStar());//设置RatingBarView选中数
            ratingBarView.setSelectedIconResId(R.drawable.start_check);//设置RatingBarView选中的图片id
            ratingBarView.setNormalIconResId(R.drawable.start_nocheck);//设置RatingBarView正常图片id
            ratingBarView.setClickable(false);//设置RatingBarView是否可点击
            ratingBarView.setChildPadding(2);//设置RatingBarView的子view的padding
            ratingBarView.setChildMargin(2);//设置RatingBarView的子view左右之间的margin
            ratingBarView.setChildDimension(22);//设置RatingBarView的子view的宽高尺寸
            ratingBarView.setOnRatingBarClickListener(new RatingBarView.RatingBarViewClickListener() {
                @Override
                public void onRatingBarClick(LinearLayout parent, View childView, int position) {
                    Log.e("tag", String.valueOf(childView instanceof ImageView) + "," + position);
                }
            });
            if (helper.getAdapterPosition() == 4) {
                helper.getView(R.id.tv_lookmore).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_lookmore).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(mContext, AgentListActivity.class));
                    }
                });
            }
            Glide.with(MyApplication.getGloableContext()).load(item.getPic())
                    .into((ImageView) helper.getView(R.id.iv_head));
            helper.setText(R.id.tv_name, item.getNickname())
                    .setText(R.id.tv_area, getResources().getString(R.string.zhuying) + item.getShop());
        }
    }

    private class TjzfAdapter extends BaseQuickAdapter<HomePageBean.DatasEntity.TjzfEntity, BaseViewHolder> {

        public TjzfAdapter(int layoutResId, @Nullable List<HomePageBean.DatasEntity.TjzfEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomePageBean.DatasEntity.TjzfEntity item) {
            Glide.with(MyApplication.getGloableContext())
                    .load(TextUtils.isEmpty(item.getVideoImgs()) ? MyUtils.getSpiltText(item.getRoomImgs()) : MyUtils.getSpiltText(item.getVideoImgs()))
                    .apply(GlideReqUtils.getReq())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.tv_title, isJa ? item.getTitleJpn() : item.getTitleCn())
                    .setText(R.id.tv_area, isJa ? item.getSpecificLocationJpn() : item.getSpecificLocationCn())
                    .setText(R.id.tv_mianji, isJa ? item.getAreaJpn() : item.getAreaCn())
                    .setText(R.id.tv_ting, isJa ? item.getDoorModelJpn() : item.getDoorModelCn())
                    .setText(R.id.tv_price, isJa ? item.getRentJpn() : item.getRentCn());
        }
    }

    private class TjtdAdapter extends BaseQuickAdapter<HomePageBean.DatasEntity.TjtdEntity, BaseViewHolder> {

        public TjtdAdapter(int layoutResId, @Nullable List<HomePageBean.DatasEntity.TjtdEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomePageBean.DatasEntity.TjtdEntity item) {
            Glide.with(MyApplication.getGloableContext())
                    .load(TextUtils.isEmpty(item.getVideoImgs()) ? MyUtils.getSpiltText(item.getLandImages()) : MyUtils.getSpiltText(item.getVideoImgs()))
                    .apply(GlideReqUtils.getReq())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.tv_title, isJa ? item.getTitleJpn() : item.getTitleCn())
                    .setText(R.id.tv_area, isJa ? item.getSpecificLocationJpn() : item.getSpecificLocationCn())
                    .setText(R.id.tv_mianji, isJa ? item.getAreaJpn() : item.getAreaCn())
                    .setText(R.id.tv_price, isJa ? item.getSellingPriceJpn() : item.getSellingPriceCn())
                    .setVisible(R.id.iv_isplay, !TextUtils.isEmpty(item.getVideoImgs()));
        }
    }

    private class CnxhAdapter extends BaseQuickAdapter<HomePageBean.DatasEntity.CnxhEntity, BaseViewHolder> {

        public CnxhAdapter(int layoutResId, @Nullable List<HomePageBean.DatasEntity.CnxhEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomePageBean.DatasEntity.CnxhEntity item) {
            Glide.with(MyApplication.getGloableContext())
                    .load(TextUtils.isEmpty(item.getVideoImgs()) ? MyUtils.getSpiltText(item.getRoomImgs()) : MyUtils.getSpiltText(item.getVideoImgs()))
                    .apply(GlideReqUtils.getReq())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
            String status = item.getStatus();
            String price = isJa ? item.getPriceJpn() : item.getPriceCn();
            String rent = isJa ? item.getRentJpn() : item.getRentCn();
            String area;
            if (isJa) {
                area = item.getSpecificLocationJpn();
            } else {
                area = item.getSpecificLocationCn();
            }
            if (area.length() > 5) {
                area = area.substring(0, 5) + "...";
            }
            helper.setText(R.id.tv_title, isJa ? item.getTitleJpn() : item.getTitleCn())
                    .setText(R.id.tv_area, area)
                    .setText(R.id.tv_mianji, isJa ? item.getAreaJpn() : item.getAreaCn())
                    .setText(R.id.tv_ting, isJa ? item.getDoorModelJpn() : item.getDoorModelCn())
                    .setText(R.id.tv_price, status.equals("2") ? rent : price);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.location_tv, R.id.search_tv, R.id.map_tv, R.id.jrdk_tv, R.id.gfbk_tv,
            R.id.fcwd_tv, R.id.znmf_tv, R.id.view_011, R.id.tjxf_more_tv, R.id.tjesf_more_tv,
            R.id.tjyxjjr_more_tv, R.id.tjzf_more_tv, R.id.tjtd_more_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.location_tv:
                startActivityForResult(new Intent(mContext, LocationActivity2.class), 0);
                break;
            case R.id.search_tv:
                Intent intent1 = new Intent(mContext, HomeSearchActivity.class);
                intent1.putExtra("home", "home");
                startActivity(intent1);
                break;
            case R.id.map_tv:
                Intent intent = new Intent(mContext, HomeMapActivity.class);
                getActivity().startActivityForResult(intent, 1);
                break;
            case R.id.jrdk_tv:
                startActivity(new Intent(mContext, Daikuan_Activity.class));
                break;
            case R.id.gfbk_tv:
                startActivity(new Intent(mContext, Buyhouse_Baike_Activity.class));
                break;
            case R.id.fcwd_tv:
                startActivity(new Intent(mContext, QuestionActivity.class));
                break;
            case R.id.znmf_tv:
                startActivity(new Intent(mContext, ZhinengActivity.class));
                break;
            case R.id.view_011:
                startActivity(new Intent(mContext, ToutiaoActivity.class));
                break;
            case R.id.tjxf_more_tv:
                startActivity(new Intent(mContext, NewHouseActivity.class));
                break;
            case R.id.tjesf_more_tv:
                startActivity(new Intent(mContext, ErshoufangActiviy.class));
                break;
            case R.id.tjyxjjr_more_tv:
                startActivity(new Intent(mContext, AgentListActivity.class));
                break;
            case R.id.tjzf_more_tv:
                startActivity(new Intent(mContext, ZufangActivity.class));
                break;
            case R.id.tjtd_more_tv:
                startActivity(new Intent(mContext, TudiActivity.class));
                break;
        }
    }
}
