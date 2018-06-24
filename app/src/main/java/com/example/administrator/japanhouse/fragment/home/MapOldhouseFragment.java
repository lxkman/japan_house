package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.bean.DrawMapBean;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.bean.MapHouseBean;
import com.example.administrator.japanhouse.bean.MapHouseDetailBean;
import com.example.administrator.japanhouse.bean.MarkerBean;
import com.example.administrator.japanhouse.bean.MoreCheckBean;
import com.example.administrator.japanhouse.bean.OldHouseShaiXuanBean;
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.example.administrator.japanhouse.bean.QuYuBean;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.SoftKeyboardTool;
import com.example.administrator.japanhouse.view.MyDrawCircleView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.yyydjk.library.DropDownMenu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by power on 2018/4/20.
 */

public class MapOldhouseFragment extends BaseFragment implements MyItemClickListener, View.OnClickListener {
    private DropDownMenu dropDownMenu;
    private List<View> popupViews = new ArrayList<>();
    private TextureMapView mapView;
    private BaiduMap baiduMap;
    private MyDrawCircleView mydrawcircleview;
    private LinearLayout ll_clear;
    private boolean isJa;
    private LocationClient mLocClient;
    private String mCity;
    private String mjId = "-2";
    private String sjId = "-2";
    private List<String> zidingyiPriceList = new ArrayList<>();
    private boolean isZiDingyiPrice;
    private List<OldHouseShaiXuanBean.DatasEntity.MianjiEntity> mianji;
    private List<OldHouseShaiXuanBean.DatasEntity.ShoujiaEntity> shoujia;
    private List<List<String>> mMoreSelectedBeanList = new ArrayList<>();
    private View fifthView;
    private String[] headers;
    private OldHouseShaiXuanBean.DatasEntity shaiXuanBeanDatas;
    private boolean isDitie;
    private List<String> quyuList = new ArrayList<>();
    private List<String> ditieList = new ArrayList<>();
    private List<LatLng> latLngList;
    private boolean isHuaQuan;
    private double[] vertx;
    private double[] verty;
    private LatLng curr_northeast;
    private LatLng curr_southwest;
    private boolean isevent;
    private String searchText = "";
    private float zoom = 11;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_old, null, false);
        dropDownMenu = (DropDownMenu) view.findViewById(R.id.dropDownMenu);
        //防止eventbus重复注册错误，因为fragment结合viewpager的时候，从第一个跳到第三个，再点第一个还是会走initView方法
        if (!isevent) {
            EventBus.getDefault().register(this);
            isevent = true;
        }
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        Log.e("xxx", "fragment111" + " oncreateView()");
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.e("xxx", "fragment111" + " onDestroy()");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventBean eventBean) {
        if (eventBean.getMsg().equals("drawcirclefindhouse_old")) {
            mydrawcircleview.clearAll("old");
            mydrawcircleview.setVisibility(View.VISIBLE);
            baiduMap.clear();
            //画圈时设置缩放13
            baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(13).build()));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(DrawMapBean mapBean) {
        if (mapBean.getMsg().equals("drawcirclemapover_old")) {
            mydrawcircleview.setVisibility(View.GONE);
            ll_clear.setVisibility(View.VISIBLE);
            List<android.graphics.Point> pointList = mapBean.getPointList();
            latLngList = new ArrayList<>();
            if (pointList != null && pointList.size() > 0) {
                for (int i = 0; i < pointList.size(); i++) {
                    android.graphics.Point point = pointList.get(i);
                    //百度地图中的方法，将屏幕坐标点转换成经纬度坐标
                    LatLng latlng1 = baiduMap.getProjection().fromScreenLocation(point);
                    latLngList.add(latlng1);
                }
            }
            //构建用户绘制多边形的Option对象
            OverlayOptions polygonOption = new PolygonOptions()
                    .points(latLngList)
                    .stroke(new Stroke(5, getResources().getColor(R.color.mapcirclestroke)))
                    .fillColor(getResources().getColor(R.color.mapcirclesfill));

            //在地图上添加多边形Option，用于显示
            baiduMap.addOverlay(polygonOption);
            //展示画圈内的房源
            isHuaQuan = true;
            vertx = new double[latLngList.size()];
            verty = new double[latLngList.size()];
            for (int i = 0; i < latLngList.size(); i++) {
                double latitude = latLngList.get(i).latitude;
                double longitude = latLngList.get(i).longitude;
                vertx[i] = latitude;
                verty[i] = longitude;
            }
            loadAllXiaoQu(curr_northeast, curr_southwest);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("xxx", "fragment111" + " onActivityCreated()");
        initData();
        initLocation();
        initListener();
    }

    private void initData() {
        headers = new String[]{getString(R.string.quyu), getString(R.string.lxkmianji),
                getString(R.string.shoujia), getString(R.string.gengduo)};
        popupViews.clear();
        fifthView = LayoutInflater.from(mContext).inflate(R.layout.dropdown_map_layout, null);
        mapView = (TextureMapView) fifthView.findViewById(R.id.mapview);
        ll_clear = (LinearLayout) fifthView.findViewById(R.id.ll_clear);
        ll_clear.setOnClickListener(this);
        mydrawcircleview = (MyDrawCircleView) fifthView.findViewById(R.id.mydrawcircleview);
        mapView.removeViewAt(1);//隐藏logo
        mapView.removeViewAt(2);//隐藏比例尺
        mapView.showZoomControls(false);//隐藏缩放控件
        baiduMap = mapView.getMap();
        HttpParams params = new HttpParams();
        params.put("hType", 0);
        OkGo.<OldHouseShaiXuanBean>post(MyUrls.BASEURL + "/app/onescreening/selectallscree")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<OldHouseShaiXuanBean>(OldHouseShaiXuanBean.class) {
                    @Override
                    public void onSuccess(Response<OldHouseShaiXuanBean> response) {
                        int code = response.code();
                        OldHouseShaiXuanBean shaiXuanBean = response.body();
                        if (shaiXuanBean == null) {
                            return;
                        }
                        shaiXuanBeanDatas = shaiXuanBean.getDatas();
                        initShaiXuan();
                    }
                });
    }

    private void initShaiXuan() {
        HttpParams params = new HttpParams();
        params.put("cId", 2);
        OkGo.<QuYuBean>post(MyUrls.BASEURL + "/app/areamanage/selectareaandsubway")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<QuYuBean>(QuYuBean.class) {
                    @Override
                    public void onSuccess(Response<QuYuBean> response) {
                        QuYuBean body = response.body();
                        QuYuBean.DatasEntity datas = body.getDatas();
                        List<QuYuBean.DatasEntity.AreasEntity> areas = datas.getAreas();
                        List<QuYuBean.DatasEntity.SubwaylinesEntity> subwaylines = datas.getSubwaylines();
                        List<MoreCheckBean> quyuListBean = new ArrayList<MoreCheckBean>();
                        List<MoreCheckBean> ditieListBean = new ArrayList<MoreCheckBean>();
                        List<OneCheckBean> oneCheckBeanList1 = new ArrayList<OneCheckBean>();
                        oneCheckBeanList1.add(new OneCheckBean(true, getResources().getString(R.string.buxian)));
                        MoreCheckBean moreCheckBean1 = new MoreCheckBean(true, getResources().getString(R.string.buxian));
                        moreCheckBean1.setCheckBeanList(oneCheckBeanList1);
                        quyuListBean.add(moreCheckBean1);
                        ditieListBean.add(moreCheckBean1);
                        if (areas != null && areas.size() > 0) {
                            for (int i = 0; i < areas.size(); i++) {
                                QuYuBean.DatasEntity.AreasEntity areasEntity = areas.get(i);
                                if (areasEntity != null) {
                                    String administrationNameCn = areasEntity.getAdministrationNameCn();
                                    String administrationNameJpn = areasEntity.getAdministrationNameJpn();
                                    MoreCheckBean moreCheckBean = new MoreCheckBean();
                                    moreCheckBean.setName(isJa ? administrationNameJpn : administrationNameCn);
                                    moreCheckBean.setId(areasEntity.getId());
                                    List<QuYuBean.DatasEntity.AreasEntity.HwdcAreaManagesEntity> hwdcAreaManages = areasEntity.getHwdcAreaManages();
                                    List<OneCheckBean> oneCheckBeanList = new ArrayList<OneCheckBean>();
                                    oneCheckBeanList.add(new OneCheckBean(true, getResources().getString(R.string.buxian)));
                                    if (hwdcAreaManages != null && hwdcAreaManages.size() > 0) {
                                        for (int i1 = 0; i1 < hwdcAreaManages.size(); i1++) {
                                            int id = hwdcAreaManages.get(i1).getId();
                                            String areaNameCn = hwdcAreaManages.get(i1).getAreaNameCn();
                                            String areaNameJpn = hwdcAreaManages.get(i1).getAreaNameJpn();
                                            OneCheckBean oneCheckBean = new OneCheckBean(false, isJa ? areaNameJpn : areaNameCn, id);
                                            oneCheckBeanList.add(oneCheckBean);
                                        }
                                    }
                                    moreCheckBean.setCheckBeanList(oneCheckBeanList);
                                    quyuListBean.add(moreCheckBean);
                                }
                            }
                        }
                        if (subwaylines != null && subwaylines.size() > 0) {
                            for (int i = 0; i < subwaylines.size(); i++) {
                                QuYuBean.DatasEntity.SubwaylinesEntity subwaylinesEntity = subwaylines.get(i);
                                if (subwaylinesEntity != null) {
                                    String lineNameCn = subwaylinesEntity.getLineNameCn();
                                    String lineNameJpn = subwaylinesEntity.getLineNameJpn();
                                    MoreCheckBean moreCheckBean = new MoreCheckBean();
                                    moreCheckBean.setName(isJa ? lineNameJpn : lineNameCn);
                                    moreCheckBean.setId(subwaylinesEntity.getId());
                                    List<QuYuBean.DatasEntity.SubwaylinesEntity.SubwayStationsEntity> subwayStations = subwaylinesEntity.getSubwayStations();
                                    List<OneCheckBean> oneCheckBeanList = new ArrayList<OneCheckBean>();
                                    oneCheckBeanList.add(new OneCheckBean(true, getResources().getString(R.string.buxian)));
                                    if (subwayStations != null && subwayStations.size() > 0) {
                                        for (int i1 = 0; i1 < subwayStations.size(); i1++) {
                                            int id = subwayStations.get(i1).getId();
                                            String stationNameCn = subwayStations.get(i1).getStationNameCn();
                                            String stationNameJpn = subwayStations.get(i1).getStationNameJpn();
                                            OneCheckBean oneCheckBean = new OneCheckBean(false, isJa ? stationNameJpn : stationNameCn, id);
                                            oneCheckBeanList.add(oneCheckBean);
                                        }
                                    }
                                    moreCheckBean.setCheckBeanList(oneCheckBeanList);
                                    ditieListBean.add(moreCheckBean);
                                }
                            }
                        }
                        /**
                         * 第一个界面
                         * */
                        FirstView firstView = new FirstView(mContext);
                        popupViews.add(firstView.firstView());
                        firstView.insertData(quyuListBean, ditieListBean, dropDownMenu);
                        firstView.setListener(MapOldhouseFragment.this);
                        /**
                         * 第二个界面
                         * */
                        mianji = shaiXuanBeanDatas.getMianji();
                        List<OneCheckBean> list1 = new ArrayList<>();
                        list1.add(new OneCheckBean(false, getResources().getString(R.string.buxian)));
                        if (mianji != null && mianji.size() > 0) {
                            for (int i = 0; i < mianji.size(); i++) {
                                OldHouseShaiXuanBean.DatasEntity.MianjiEntity mianjiEntity = mianji.get(i);
                                list1.add(new OneCheckBean(false, isJa ? mianjiEntity.getScreeValJpn() : mianjiEntity.getScreeValCn()));
                            }
                        }
                        SecView secView = new SecView(mContext);
                        popupViews.add(secView.secView());
                        secView.setListener(MapOldhouseFragment.this);
                        secView.insertData(list1, dropDownMenu);

                        /**
                         * 第三个界面
                         * */
                        shoujia = shaiXuanBeanDatas.getShoujia();
                        List<OneCheckBean> list2 = new ArrayList<>();
                        list2.add(new OneCheckBean(false, getResources().getString(R.string.buxian)));
                        if (shoujia != null && shoujia.size() > 0) {
                            for (int i = 0; i < shoujia.size(); i++) {
                                OldHouseShaiXuanBean.DatasEntity.ShoujiaEntity shoujiaEntity = shoujia.get(i);
                                list2.add(new OneCheckBean(false, isJa ? shoujiaEntity.getScreeValJpn() : shoujiaEntity.getScreeValCn()));
                            }
                        }
                        ThreeView threeView = new ThreeView(mContext);
                        popupViews.add(threeView.firstView());
                        threeView.insertData(list2, dropDownMenu);
                        threeView.setListener(MapOldhouseFragment.this);
                        /**
                         * 第四个界面
                         * */
                        List<MoreCheckBean> moreCheckBeanList = new ArrayList<MoreCheckBean>();
                        MoreCheckBean huaquanBean = new MoreCheckBean(false, getResources().getString(R.string.quyu));
                        List<OneCheckBean> itemBean = new ArrayList<>();
                        itemBean.add(new OneCheckBean(false, getResources().getString(R.string.huaquanzhaofang)));
                        huaquanBean.setCheckBeanList(itemBean);
                        moreCheckBeanList.add(huaquanBean);
                        List<OldHouseShaiXuanBean.DatasEntity.MoreEntity> more = shaiXuanBeanDatas.getMore();
                        if (more != null && more.size() > 0) {
                            for (int i = 0; i < more.size(); i++) {
                                OldHouseShaiXuanBean.DatasEntity.MoreEntity moreEntity = more.get(i);
                                List<OldHouseShaiXuanBean.DatasEntity.MoreEntity.DataEntity> data = moreEntity.getData();
                                String nameCn = moreEntity.getNameCn();
                                String nameJpn = moreEntity.getNameJpn();
                                MoreCheckBean moreCheckBean = new MoreCheckBean();
                                moreCheckBean.setName(isJa ? nameJpn : nameCn);
                                if (data != null && data.size() > 0) {
                                    List<OneCheckBean> list3 = new ArrayList<>();
                                    for (int i1 = 0; i1 < data.size(); i1++) {
                                        list3.add(new OneCheckBean(false,
                                                isJa ? data.get(i1).getScreeValJpn() : data.get(i1).getScreeValCn(), data.get(i1).getId()));
                                    }
                                    moreCheckBean.setCheckBeanList(list3);
                                }
                                moreCheckBeanList.add(moreCheckBean);
                            }
                        }
                        MoreView fourView = new MoreView(mContext);
                        popupViews.add(fourView.secView());
                        fourView.insertData(moreCheckBeanList, dropDownMenu, "oldhouse");
                        fourView.setListener(MapOldhouseFragment.this);
                        /**
                         * Dropdownmenu下面的主体部分
                         * */
                        Log.e("xxx", "1111111111111111111111111111");
                        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, fifthView);
                    }
                });
    }


    private void initMap(double weidu, double jingdu) {
        LatLng center = new LatLng(weidu, jingdu);
        float zoom = 11.0f; // 默认 11级
        MapStatus mMapStatus = new MapStatus.Builder().target(
                center).zoom(zoom).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory
                .newMapStatus(mMapStatus);
        baiduMap.setMapStatus(mMapStatusUpdate);
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
                //                if (!country.equals("日本")) {
                //                    // 默认 东京
                //                    mCity = "东京市";
                //                    double longitude = 139.75;
                //                    double latitude = 35.68;
                //                    initMap(latitude, longitude);
                //                    initOverlay(mCity);
                //                } else {
                //                    double longitude = bdLocation.getLongitude();
                //                    double latitude = bdLocation.getLatitude();
                //                    mCity = bdLocation.getCity();
                //                    initMap(latitude, longitude);
                //                    initOverlay(mCity);
                //                }
                double longitude = bdLocation.getLongitude();
                double latitude = bdLocation.getLatitude();
                mCity = bdLocation.getCity();
                initMap(latitude, longitude);
                initOverlay(mCity);
            }
        });
    }

    private void initOverlay(String city) {
        baiduMap.clear();
        HttpParams params = new HttpParams();
        params.put("cityName", city);
        params.put("hType", 0);
        params.put("mjId", mjId);//面积
        params.put("sjId", sjId);//售价
        if (isZiDingyiPrice) {
            if (zidingyiPriceList.size() == 1) {
                String s = zidingyiPriceList.get(0);
                if (s.contains("below")) {
                    String[] split = s.split(",");
                    params.put("starSj", split[1]);//售价最低价
                } else if (s.contains("high")) {
                    String[] split = s.split(",");
                    params.put("endSj", split[1]);//售价最高价
                }
            } else if (zidingyiPriceList.size() == 2) {
                params.put("starSj", zidingyiPriceList.get(0).split(",")[1]);//售价最低价
                params.put("endSj", zidingyiPriceList.get(1).split(",")[1]);//售价最高价
            }
        }
        if (isDitie) {
            params.putUrlParams("dtzs", ditieList);//地铁站
        } else {
            params.putUrlParams("qys", quyuList);//区域
        }
        params.put("searchText", searchText);
        if (mMoreSelectedBeanList.size() > 0)
            params.putUrlParams("hxs", mMoreSelectedBeanList.get(0));//户型
        if (mMoreSelectedBeanList.size() > 1)
            params.putUrlParams("lcs", mMoreSelectedBeanList.get(1));//楼层
        if (mMoreSelectedBeanList.size() > 2)
            params.putUrlParams("jznfs", mMoreSelectedBeanList.get(2));//建筑年份
        if (mMoreSelectedBeanList.size() > 3)
            params.putUrlParams("jzgzs", mMoreSelectedBeanList.get(3));//建筑构造
        if (mMoreSelectedBeanList.size() > 4)
            params.putUrlParams("dds", mMoreSelectedBeanList.get(4));//地段
        if (mMoreSelectedBeanList.size() > 5)
            params.putUrlParams("cxs", mMoreSelectedBeanList.get(5));//朝向
        if (mMoreSelectedBeanList.size() > 6)
            params.putUrlParams("czjls", mMoreSelectedBeanList.get(6));//车站距离
        if (mMoreSelectedBeanList.size() > 7)
            params.putUrlParams("syqs", mMoreSelectedBeanList.get(7));//所有权
        if (mMoreSelectedBeanList.size() > 8)
            params.putUrlParams("rzrqs", mMoreSelectedBeanList.get(8));//入居日期
        OkGo.<MapHouseBean>post(MyUrls.BASEURL + "/app/city/selectbycity")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<MapHouseBean>(MapHouseBean.class) {
                    @Override
                    public void onSuccess(Response<MapHouseBean> response) {
                        int code = response.code();
                        MapHouseBean body = response.body();
                        List<MapHouseBean.DatasEntity> datas = body.getDatas();
                        if (datas != null && datas.size() > 0) {
                            List<MarkerBean> markerBeanList = new ArrayList<>();
                            List<OverlayOptions> overlayOptionsList = new ArrayList<>();
                            for (int i = 0; i < datas.size(); i++) {
                                MapHouseBean.DatasEntity datasEntity = datas.get(i);
                                markerBeanList.add(new MarkerBean(datasEntity.getLongitude(), datasEntity.getLatitude()));
                                View markView = LayoutInflater.from(mContext).inflate(R.layout.map_marker_view, null);
                                TextView title = (TextView) markView.findViewById(R.id.item_title_tv);
                                ImageView iv = (ImageView) markView.findViewById(R.id.iv_topordown);
                                TextView content = (TextView) markView.findViewById(R.id.item_content_tv);
                                content.setText(isJa ? datasEntity.getAdministrationNameJpn() : datasEntity.getAdministrationNameCn());
                                title.setText(datasEntity.getHouseNum() + getString(R.string.wantao));
                                iv.setVisibility(View.GONE);
                                MarkerOptions markerOptions = new MarkerOptions()
                                        .icon(BitmapDescriptorFactory.fromView(markView))
                                        .position(new LatLng(markerBeanList.get(i).getWei(), markerBeanList.get(i).getJing()))
                                        .zIndex(11)
                                        .draggable(true);
                                overlayOptionsList.add(markerOptions);
                            }
                            baiduMap.addOverlays(overlayOptionsList);
                        }
                    }
                });
    }

    private void initListener() {
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.getZIndex() == 11) {
                    baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(13).build()));
                    MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(marker.getPosition());
                    baiduMap.animateMapStatus(u);
                    //                    loadAllXiaoQu(northeast, southwest);
                } else {
                    Intent intent = new Intent(mContext, ErshoufangActiviy.class);
                    intent.putExtra("communityId", marker.getTitle());
                    startActivity(intent);
                }
                return false;
            }
        });
        baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {
                Log.e("xxxx", "百度地图状态开始改变");
            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                Log.e("xxxx", "百度地图状态改变结束");
                zoom = mapStatus.zoom;
                if (mapStatus.zoom < 12) {
                    initOverlay(mCity);
                } else if (mapStatus.zoom >= 12) {
                    //                    LatLng target = mapStatus.target;
                    //                    allupdate(target.latitude + "", target.longitude + "");
                    LatLngBounds bound = mapStatus.bound;
                    curr_northeast = bound.northeast;
                    curr_southwest = bound.southwest;
                    loadAllXiaoQu(curr_northeast, curr_southwest);
                }
            }
        });
    }

    private void loadAllXiaoQu(LatLng northeast, LatLng southwest) {
        if (isHuaQuan) {

        } else {
            baiduMap.clear();
        }
        HttpParams params = new HttpParams();
        params.put("hType", 0);
        params.put("starJd", southwest.longitude);
        params.put("endJd", northeast.longitude);
        params.put("starWd", southwest.latitude);
        params.put("endWd", northeast.latitude);
        params.put("mjId", mjId);//面积
        params.put("sjId", sjId);//售价
        if (isZiDingyiPrice) {
            if (zidingyiPriceList.size() == 1) {
                String s = zidingyiPriceList.get(0);
                if (s.contains("below")) {
                    String[] split = s.split(",");
                    params.put("starSj", split[1]);//售价最低价
                } else if (s.contains("high")) {
                    String[] split = s.split(",");
                    params.put("endSj", split[1]);//售价最高价
                }
            } else if (zidingyiPriceList.size() == 2) {
                params.put("starSj", zidingyiPriceList.get(0).split(",")[1]);//售价最低价
                params.put("endSj", zidingyiPriceList.get(1).split(",")[1]);//售价最高价
            }
        }
        params.put("searchText", searchText);
        if (isDitie) {
            params.putUrlParams("dtzs", ditieList);//地铁站
        } else {
            params.putUrlParams("qys", quyuList);//区域
        }
        if (mMoreSelectedBeanList.size() > 0)
            params.putUrlParams("hxs", mMoreSelectedBeanList.get(0));//户型
        if (mMoreSelectedBeanList.size() > 1)
            params.putUrlParams("lcs", mMoreSelectedBeanList.get(1));//楼层
        if (mMoreSelectedBeanList.size() > 2)
            params.putUrlParams("jznfs", mMoreSelectedBeanList.get(2));//建筑年份
        if (mMoreSelectedBeanList.size() > 3)
            params.putUrlParams("jzgzs", mMoreSelectedBeanList.get(3));//建筑构造
        if (mMoreSelectedBeanList.size() > 4)
            params.putUrlParams("dds", mMoreSelectedBeanList.get(4));//地段
        if (mMoreSelectedBeanList.size() > 5)
            params.putUrlParams("cxs", mMoreSelectedBeanList.get(5));//朝向
        if (mMoreSelectedBeanList.size() > 6)
            params.putUrlParams("czjls", mMoreSelectedBeanList.get(6));//车站距离
        if (mMoreSelectedBeanList.size() > 7)
            params.putUrlParams("syqs", mMoreSelectedBeanList.get(7));//所有权
        if (mMoreSelectedBeanList.size() > 8)
            params.putUrlParams("rzrqs", mMoreSelectedBeanList.get(8));//入居日期
        OkGo.<MapHouseDetailBean>post(MyUrls.BASEURL + "/app/community/selectbyjwd")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<MapHouseDetailBean>(MapHouseDetailBean.class) {
                    @Override
                    public void onSuccess(Response<MapHouseDetailBean> response) {
                        int code = response.code();
                        MapHouseDetailBean body = response.body();
                        List<MapHouseDetailBean.DatasEntity> datas = body.getDatas();
                        if (datas != null && datas.size() > 0) {
                            List<MarkerBean> markerBeanList = new ArrayList<>();
                            List<OverlayOptions> overlayOptionsList = new ArrayList<>();
                            for (int i = 0; i < datas.size(); i++) {
                                MapHouseDetailBean.DatasEntity datasEntity = datas.get(i);
                                if (isHuaQuan) {
                                    if (latLngList == null || latLngList.size() == 0) {
                                        return;
                                    }
                                    boolean pnpoly = pnpoly(latLngList.size(), vertx, verty, datasEntity.getLatiude(), datasEntity.getLongitude());
                                    if (pnpoly) {
                                        markerBeanList.add(new MarkerBean(datasEntity.getLongitude(), datasEntity.getLatiude()));
                                        View markView = LayoutInflater.from(mContext).inflate(R.layout.map_item_xiaoqu, null);
                                        TextView content = (TextView) markView.findViewById(R.id.tv_xiaoqu);
                                        content.setText(isJa ? datasEntity.getCommunityNameJpn() + "（" + datasEntity.getHouseNum() + getString(R.string.tao)
                                                : datasEntity.getCommunityNameCn() + "（" + datasEntity.getHouseNum() + getString(R.string.tao));
                                        MarkerOptions markerOptions = new MarkerOptions()
                                                .icon(BitmapDescriptorFactory.fromView(markView))
                                                .position(new LatLng(markerBeanList.get(i).getWei(), markerBeanList.get(i).getJing()))
                                                .zIndex(12)
                                                .title(datasEntity.getId() + "")//做个标记，点击事件时候用到
                                                .draggable(true);
                                        overlayOptionsList.add(markerOptions);
                                    }
                                } else {
                                    markerBeanList.add(new MarkerBean(datasEntity.getLongitude(), datasEntity.getLatiude()));
                                    View markView = LayoutInflater.from(mContext).inflate(R.layout.map_item_xiaoqu, null);
                                    TextView content = (TextView) markView.findViewById(R.id.tv_xiaoqu);
                                    content.setText(isJa ? datasEntity.getCommunityNameJpn() + "（" + datasEntity.getHouseNum() + getString(R.string.tao)
                                            : datasEntity.getCommunityNameCn() + "（" + datasEntity.getHouseNum() + getString(R.string.tao));
                                    MarkerOptions markerOptions = new MarkerOptions()
                                            .icon(BitmapDescriptorFactory.fromView(markView))
                                            .position(new LatLng(markerBeanList.get(i).getWei(), markerBeanList.get(i).getJing()))
                                            .zIndex(12)
                                            .title(datasEntity.getId() + "")//做个标记，点击事件时候用到
                                            .draggable(true);
                                    overlayOptionsList.add(markerOptions);
                                }
                            }
                            baiduMap.addOverlays(overlayOptionsList);
                        }
                    }
                });
    }


    @Override
    protected void initLazyData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("xxx", "fragment111" + " onResume()");
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("xxx", "fragment111" + " onPause()");
        mapView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("xxx", "fragment111" + " onDestroyView()");
        mapView.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_clear:
                isHuaQuan = false;
                loadAllXiaoQu(curr_northeast, curr_southwest);
                ll_clear.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int postion, int itemPosition) {
        switch (postion) {
            case 1:
                break;
            case 2://面积
                if (itemPosition == 0) {//说明是点击的不限
                    mjId = "-2";
                } else {
                    if (mianji != null && mianji.size() > 0) {
                        OldHouseShaiXuanBean.DatasEntity.MianjiEntity mianjiEntity = mianji.get(itemPosition - 1);
                        mjId = mianjiEntity.getId() + "";
                    }
                }
                refreshMap();
                break;
            case 3://售价
                isZiDingyiPrice = false;
                zidingyiPriceList.clear();
                if (itemPosition == 0) {
                    sjId = "-2";
                } else {
                    if (shoujia != null && shoujia.size() > 0) {
                        sjId = shoujia.get(itemPosition - 1).getId() + "";
                    }
                }
                refreshMap();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int postion, List<String> priceRegin) {
        if (postion == 1) {//区域
            isDitie = false;
            quyuList = priceRegin;
        } else if (postion == 2) {//地铁
            isDitie = true;
            ditieList = priceRegin;
        } else {//自定义价格
            if (shoujia != null && shoujia.size() > 0) {
                SoftKeyboardTool.closeKeyboard2(mActivity);
                isZiDingyiPrice = true;
                sjId = "-1";
                zidingyiPriceList.clear();
                zidingyiPriceList = priceRegin;
                refreshMap();
            }
        }
    }

    @Override
    public void onMoreItemClick(View view, List<List<String>> moreSelectedBeanList) {
        mMoreSelectedBeanList.clear();
        mMoreSelectedBeanList = moreSelectedBeanList;
        refreshMap();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            searchText = data.getStringExtra("searchText");
            refreshMap();
        }
    }

    private void refreshMap() {
        if (zoom < 12) {
            initOverlay(mCity);
        } else if (zoom >= 12) {
            loadAllXiaoQu(curr_northeast, curr_southwest);
        }
    }

    /*判断某个经纬度点是否在多边形内部*/
    private boolean pnpoly(int nvert, double[] vertx, double[] verty, double testx, double testy) {
        int i, j = 0;
        boolean c = false;
        for (i = 0, j = nvert - 1; i < nvert; j = i++) {
            if (((verty[i] > testy) != (verty[j] > testy)) &&
                    (testx < (vertx[j] - vertx[i]) * (testy - verty[i]) / (verty[j] - verty[i]) + vertx[i]))
                c = !c;
        }
        return c;
    }


}