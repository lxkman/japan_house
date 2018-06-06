package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.example.administrator.japanhouse.bean.QuYuBean;
import com.example.administrator.japanhouse.bean.ZuHouseShaiXuanBean;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.view.MyDrawCircleView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.yyydjk.library.DropDownMenu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by power on 2018/4/20.
 */

public class MapZuhouseFragment extends BaseFragment implements MyItemClickListener, View.OnClickListener {
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    Unbinder unbinder;
    private List<View> popupViews = new ArrayList<>();
    private List<OneCheckBean> list;
    private TextureMapView mapView;
    private BaiduMap baiduMap;
    MyDrawCircleView mydrawcircleview;
    private LinearLayout ll_clear;
    private boolean isJa;
    private LocationClient mLocClient;
    private String mCity;
    private String mjId = "-2";
    private String zjId = "-2";
    private List<String> zidingyiPriceList = new ArrayList<>();
    private boolean isZiDingyiPrice;
    private List<ZuHouseShaiXuanBean.DatasEntity.MianjiEntity> mianji;
    private List<ZuHouseShaiXuanBean.DatasEntity.ZujinEntity> zujin;
    private List<List<String>> mMoreSelectedBeanList = new ArrayList<>();
    private String[] headers;
    private View fifthView;
    private ZuHouseShaiXuanBean.DatasEntity shaiXuanBeanDatas;
    private boolean isDitie;
    private List<String> quyuList = new ArrayList<>();
    private List<String> ditieList = new ArrayList<>();

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_old, null, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initLocation();
        initListener();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventBean eventBean) {
        if (eventBean.getMsg().equals("drawcirclefindhouse_zu")) {
            mydrawcircleview.clearAll("zu");
            mydrawcircleview.setVisibility(View.VISIBLE);
            baiduMap.clear();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(DrawMapBean mapBean) {
        if (mapBean.getMsg().equals("drawcirclemapover_zu")) {
            mydrawcircleview.setVisibility(View.GONE);
            ll_clear.setVisibility(View.VISIBLE);
            List<android.graphics.Point> pointList = mapBean.getPointList();
            List<LatLng> latLngList = new ArrayList<>();
            if (pointList != null && pointList.size() > 0) {
                for (int i = 0; i < pointList.size(); i++) {
                    android.graphics.Point point = pointList.get(i);
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
            initOverlay(mCity);
        }
    }

    private void initData() {
        headers = new String[]{getString(R.string.quyu), getString(R.string.lxkmianji),
                getString(R.string.zujin), getString(R.string.gengduo)};
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
        params.put("shType", 10);
        OkGo.<ZuHouseShaiXuanBean>post(MyUrls.BASEURL + "/app/twoscreening/selectallscree")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<ZuHouseShaiXuanBean>(ZuHouseShaiXuanBean.class) {
                    @Override
                    public void onSuccess(Response<ZuHouseShaiXuanBean> response) {
                        int code = response.code();
                        ZuHouseShaiXuanBean shaiXuanBean = response.body();
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
                        oneCheckBeanList1.add(new OneCheckBean(true,  getResources().getString(R.string.buxian)));
                        MoreCheckBean moreCheckBean1 = new MoreCheckBean(true,  getResources().getString(R.string.buxian));
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
                                    oneCheckBeanList.add(new OneCheckBean(true,  getResources().getString(R.string.buxian)));
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
                                    oneCheckBeanList.add(new OneCheckBean(true,  getResources().getString(R.string.buxian)));
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
                        firstView.setListener(MapZuhouseFragment.this);
                        /**
                         * 第二个界面
                         * */
                        mianji = shaiXuanBeanDatas.getMianji();
                        List<OneCheckBean> list1 = new ArrayList<>();
                        list1.add(new OneCheckBean(false,  getResources().getString(R.string.buxian)));
                        if (mianji != null && mianji.size() > 0) {
                            for (int i = 0; i < mianji.size(); i++) {
                                ZuHouseShaiXuanBean.DatasEntity.MianjiEntity mianjiEntity = mianji.get(i);
                                list1.add(new OneCheckBean(false, isJa ? mianjiEntity.getScreeValJpn() : mianjiEntity.getScreeValCn()));
                            }
                        }
                        SecView secView = new SecView(mContext);
                        popupViews.add(secView.secView());
                        secView.setListener(MapZuhouseFragment.this);
                        secView.insertData(list1, dropDownMenu);

                        /**
                         * 第三个界面
                         * */
                        zujin = shaiXuanBeanDatas.getZujin();
                        List<OneCheckBean> list2 = new ArrayList<>();
                        list2.add(new OneCheckBean(false,  getResources().getString(R.string.buxian)));
                        if (zujin != null && zujin.size() > 0) {
                            for (int i = 0; i < zujin.size(); i++) {
                                ZuHouseShaiXuanBean.DatasEntity.ZujinEntity shoujiaEntity = zujin.get(i);
                                list2.add(new OneCheckBean(false, isJa ? shoujiaEntity.getScreeValJpn() : shoujiaEntity.getScreeValCn()));
                            }
                        }
                        ThreeView threeView = new ThreeView(mContext);
                        popupViews.add(threeView.firstView());
                        threeView.insertData2(list2, dropDownMenu, true);
                        threeView.setListener(MapZuhouseFragment.this);
                        /**
                         * 第四个界面
                         * */
                        List<MoreCheckBean> moreCheckBeanList = new ArrayList<MoreCheckBean>();
                        MoreCheckBean huaquanBean=new MoreCheckBean(false,getResources().getString(R.string.quyu));
                        List<OneCheckBean> itemBean = new ArrayList<>();
                        itemBean.add(new OneCheckBean(false,getResources().getString(R.string.huaquanzhaofang)));
                        huaquanBean.setCheckBeanList(itemBean);
                        moreCheckBeanList.add(huaquanBean);
                        List<ZuHouseShaiXuanBean.DatasEntity.MoreEntity> more = shaiXuanBeanDatas.getMore();
                        if (more != null && more.size() > 0) {
                            for (int i = 0; i < more.size(); i++) {
                                ZuHouseShaiXuanBean.DatasEntity.MoreEntity moreEntity = more.get(i);
                                List<ZuHouseShaiXuanBean.DatasEntity.MoreEntity.DataEntity> data = moreEntity.getData();
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
                        fourView.insertData(moreCheckBeanList, dropDownMenu,"zuhouse");
                        fourView.setListener(MapZuhouseFragment.this);
                        /**
                         * Dropdownmenu下面的主体部分
                         * */
                        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, fifthView);
                    }
                });
    }

    private void initMap(double weidu, double jingdu) {
        LatLng center = new LatLng(weidu, jingdu);
        //        LatLng center = new LatLng(35.68, 139.75); // 默认 东京
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
        params.put("hType", 2);
        params.put("mjId", mjId);//面积
        params.put("zjId", zjId);//租金
        if (isZiDingyiPrice) {
            params.put("starZj", zidingyiPriceList.get(0));//租金最低价
            params.put("endZj", zidingyiPriceList.get(1));//租金最高价
        }
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
            params.putUrlParams("cxs", mMoreSelectedBeanList.get(3));//朝向
        if (mMoreSelectedBeanList.size() > 4)
            params.putUrlParams("czjls", mMoreSelectedBeanList.get(4));//车站距离
        if (mMoreSelectedBeanList.size() > 5)
            params.putUrlParams("cqfys", mMoreSelectedBeanList.get(5));//初期费用
        if (mMoreSelectedBeanList.size() > 6)
            params.putUrlParams("rqxzs", mMoreSelectedBeanList.get(6));//人气选择
        if (mMoreSelectedBeanList.size() > 7)
            params.putUrlParams("fjzks", mMoreSelectedBeanList.get(7));//房间状况
        if (mMoreSelectedBeanList.size() > 8)
            params.putUrlParams("rzrqs", mMoreSelectedBeanList.get(8));//入居日期
        if (mMoreSelectedBeanList.size() > 9)
            params.putUrlParams("tjs", mMoreSelectedBeanList.get(9));//条件
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
                                title.setText(datasEntity.getHouseNum() + "万套");
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
                    startActivity(new Intent(mContext, ErshoufangActiviy.class));
                }
                return false;
            }
        });
        baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {
                Logger.e("xxxx", "百度地图状态开始改变");
            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                Logger.e("xxxx", "百度地图状态改变结束");
                if (mapStatus.zoom < 12) {
                    initLocation();
                } else if (mapStatus.zoom >= 12) {
                    //                    LatLng target = mapStatus.target;
                    //                    allupdate(target.latitude + "", target.longitude + "");
                    LatLngBounds bound = mapStatus.bound;
                    LatLng northeast = bound.northeast;
                    LatLng southwest = bound.southwest;
                    loadAllXiaoQu(northeast, southwest);
                }
            }
        });
    }

    private void loadAllXiaoQu(LatLng northeast, LatLng southwest) {
        baiduMap.clear();
        HttpParams params = new HttpParams();
        params.put("hType", 2);
        params.put("starJd", southwest.longitude);
        params.put("endJd", northeast.longitude);
        params.put("starWd", southwest.latitude);
        params.put("endWd", northeast.latitude);
        params.put("mjId", mjId);//面积
        params.put("zjId", zjId);//租金
        if (isZiDingyiPrice) {
            params.put("starZj", zidingyiPriceList.get(0));//租金最低价
            params.put("endZj", zidingyiPriceList.get(1));//租金最高价
        }
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
            params.putUrlParams("cxs", mMoreSelectedBeanList.get(3));//朝向
        if (mMoreSelectedBeanList.size() > 4)
            params.putUrlParams("czjls", mMoreSelectedBeanList.get(4));//车站距离
        if (mMoreSelectedBeanList.size() > 5)
            params.putUrlParams("cqfys", mMoreSelectedBeanList.get(5));//初期费用
        if (mMoreSelectedBeanList.size() > 6)
            params.putUrlParams("rqxzs", mMoreSelectedBeanList.get(6));//人气选择
        if (mMoreSelectedBeanList.size() > 7)
            params.putUrlParams("fjzks", mMoreSelectedBeanList.get(7));//房间状况
        if (mMoreSelectedBeanList.size() > 8)
            params.putUrlParams("rzrqs", mMoreSelectedBeanList.get(8));//入居日期
        if (mMoreSelectedBeanList.size() > 9)
            params.putUrlParams("tjs", mMoreSelectedBeanList.get(9));//条件
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
                                markerBeanList.add(new MarkerBean(datasEntity.getLongitude(), datasEntity.getLatiude()));
                                View markView = LayoutInflater.from(mContext).inflate(R.layout.map_item_xiaoqu, null);
                                TextView content = (TextView) markView.findViewById(R.id.tv_xiaoqu);
                                content.setText(isJa ? datasEntity.getCommunityNameJpn() + "（" + datasEntity.getHouseNum() + "套）"
                                        : datasEntity.getCommunityNameCn() + "（" + datasEntity.getHouseNum() + "套）");
                                MarkerOptions markerOptions = new MarkerOptions()
                                        .icon(BitmapDescriptorFactory.fromView(markView))
                                        .position(new LatLng(markerBeanList.get(i).getWei(), markerBeanList.get(i).getJing()))
                                        .zIndex(12)
                                        .draggable(true);
                                overlayOptionsList.add(markerOptions);
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
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_clear:
                baiduMap.clear();
                initOverlay(mCity);
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
                        ZuHouseShaiXuanBean.DatasEntity.MianjiEntity mianjiEntity = mianji.get(itemPosition - 1);
                        mjId = mianjiEntity.getId() + "";
                    }
                }
                initOverlay(mCity);
                break;
            case 3://租金
                isZiDingyiPrice = false;
                zidingyiPriceList.clear();
                if (itemPosition == 0) {
                    zjId = "-2";
                } else {
                    if (zujin != null && zujin.size() > 0) {
                        zjId = zujin.get(itemPosition - 1).getId() + "";
                    }
                }
                initOverlay(mCity);
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
            if (zujin != null && zujin.size() > 0) {
                isZiDingyiPrice = true;
                zjId = "-1";
                zidingyiPriceList.clear();
                zidingyiPriceList = priceRegin;
                initOverlay(mCity);
            }
        }
    }

    @Override
    public void onMoreItemClick(View view, List<List<String>> moreSelectedBeanList) {
        mMoreSelectedBeanList.clear();
        mMoreSelectedBeanList = moreSelectedBeanList;
        initOverlay(mCity);
    }
}
