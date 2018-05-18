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
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.bean.DrawMapBean;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.bean.MapHouseBean;
import com.example.administrator.japanhouse.bean.MapHouseDetailBean;
import com.example.administrator.japanhouse.bean.MarkerBean;
import com.example.administrator.japanhouse.bean.OneCheckBean;
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

public class MapOldhouseFragment extends BaseFragment implements MyItemClickListener, View.OnClickListener {
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    Unbinder unbinder;
    private List<View> popupViews;
    private List<OneCheckBean> list;
    private MapView mapView;
    private BaiduMap baiduMap;
    MyDrawCircleView mydrawcircleview;
    private LinearLayout ll_clear;
    private boolean isJa;
    private LocationClient mLocClient;
    private String mCity;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_old, null, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventBean eventBean) {
        if (eventBean.getMsg().equals("drawcirclefindhouse_old")) {
            mydrawcircleview.clearAll("old");
            mydrawcircleview.setVisibility(View.VISIBLE);
            baiduMap.clear();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(DrawMapBean mapBean) {
        if (mapBean.getMsg().equals("drawcirclemapover_old")) {
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        popupViews = new ArrayList<>();
        /**
         * 第一个界面
         * */
        list = new ArrayList<>();
        list.add(new OneCheckBean(false, "不限"));
        list.add(new OneCheckBean(false, "3-10万"));
        list.add(new OneCheckBean(false, "6-15万"));
        list.add(new OneCheckBean(false, "10万以上"));
        ThreeView firstView = new ThreeView(mContext);
        popupViews.add(firstView.firstView());
        firstView.insertData(list, dropDownMenu);
        firstView.setListener(this);

        /**
         * 第二个界面
         * */
        List<OneCheckBean> list1 = new ArrayList<>();
        list1.add(new OneCheckBean(false, "不限"));
        list1.add(new OneCheckBean(false, "地下室"));
        list1.add(new OneCheckBean(false, "一层"));
        list1.add(new OneCheckBean(false, "二层"));
        list1.add(new OneCheckBean(false, "三层"));
        list1.add(new OneCheckBean(false, "四层"));
        SecView secView = new SecView(mContext);
        popupViews.add(secView.secView());
        secView.setListener(this);
        secView.insertData(list1, dropDownMenu);

        /**
         * 第三个界面
         * */
        List<OneCheckBean> list2 = new ArrayList<>();
        list2.add(new OneCheckBean(false, "不限"));
        list2.add(new OneCheckBean(false, "1990"));
        list2.add(new OneCheckBean(false, "1990"));
        list2.add(new OneCheckBean(false, "1990"));
        list2.add(new OneCheckBean(false, "1990"));
        list2.add(new OneCheckBean(false, "1990"));
        list2.add(new OneCheckBean(false, "1990"));
        list2.add(new OneCheckBean(false, "1990"));
        SecView threeView = new SecView(mContext);
        popupViews.add(threeView.secView());
        threeView.insertData(list2, dropDownMenu);
        threeView.setListener(this);
        /**
         * 第四个界面
         * */
        List<OneCheckBean> list3 = new ArrayList<>();
        list3.add(new OneCheckBean(false, "区域"));
        list3.add(new OneCheckBean(false, "构造"));
        list3.add(new OneCheckBean(false, "地段"));
        list3.add(new OneCheckBean(false, "朝向"));
        list3.add(new OneCheckBean(false, "面积(平米)"));
        list3.add(new OneCheckBean(false, "室内设施"));
        MoreView fourView = new MoreView(mContext);
        popupViews.add(fourView.secView());
        fourView.insertData2("oldhouse", list3, dropDownMenu);
        fourView.setListener(this);
        /**
         * Dropdownmenu下面的主体部分
         * */
        String[] headers = {getString(R.string.shoujia), getString(R.string.louceng),
                getString(R.string.jianzhunianfen), getString(R.string.gengduo)};
        View fifthView = LayoutInflater.from(mContext).inflate(R.layout.dropdown_map_layout, null);
        mapView = (MapView) fifthView.findViewById(R.id.mapview);
        ll_clear = (LinearLayout) fifthView.findViewById(R.id.ll_clear);
        ll_clear.setOnClickListener(this);
        mydrawcircleview = (MyDrawCircleView) fifthView.findViewById(R.id.mydrawcircleview);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, fifthView);
        mapView.removeViewAt(1);//隐藏logo
        mapView.removeViewAt(2);//隐藏比例尺
        mapView.showZoomControls(false);// 隐藏缩放控件
        baiduMap = mapView.getMap();
        initLocation();
        initListener();
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
        String country = CacheUtils.get(Constants.COUNTRY);
        HttpParams params = new HttpParams();
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        params.put("cityName", city);
        params.put("hType", 0);
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
        String country = CacheUtils.get(Constants.COUNTRY);
        HttpParams params = new HttpParams();
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        params.put("starJd", southwest.longitude);
        params.put("endJd", northeast.longitude);
        params.put("starWd", southwest.latitude);
        params.put("endWd", northeast.latitude);
        params.put("hType", 0);
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
    public void onItemClick(View view, int postion, String string) {

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
}