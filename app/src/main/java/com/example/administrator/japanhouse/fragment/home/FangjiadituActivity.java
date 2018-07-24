package com.example.administrator.japanhouse.fragment.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.ChartBean;
import com.example.administrator.japanhouse.bean.FangjiaMapBean;
import com.example.administrator.japanhouse.bean.MarkerBean;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.example.administrator.japanhouse.view.BaseDialog;
import com.example.administrator.japanhouse.view.ChartView;
import com.example.administrator.japanhouse.view.ChartViewOneLine;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FangjiadituActivity extends BaseActivity {

    @BindView(R.id.mapview)
    MapView mapview;
    @BindView(R.id.btn_location)
    Button btnLocation;
    @BindView(R.id.layout_pop)
    LinearLayout layoutPop;
    @BindView(R.id.back_img)
    ImageView back_img;
    private BaiduMap baiduMap;
    //x轴坐标对应的数据
    private List<String> xValue = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Float> yValue = new ArrayList<>();
    //折线对应的数据
    private Map<String, Float> value = new HashMap<>();
    //第二条折线
    private Map<String, Float> value1 = new HashMap<>();
    //第一条折线对应的折点
    List<Float> mlist = new ArrayList<>();
    //第二条折线对应的折点
    List<Float> mlist1 = new ArrayList<>();
    private boolean isJa;
    private LocationClient mLocClient;
    private String mCity;
    private List<FangjiaMapBean.DatasBean.CityzxtBean> cityzxt;
    private Float monthbfb;
    private Float yearbfb;
    private List<ChartBean.DatasBean.ZxtlistBean> zxtlist;
    private double bigVal;
    private double endVal;
    private int avgMoney;
    private String Tag;
    private Float monthbfboneline;
    private Float yeaybfboneline;
    private int avgMoneyoneline;
    private double bigValoneline;
    private double endValoneline;
    private double longitude;
    private double latitude;
    @BindView(R.id.tv_cityname)
    TextView tv_cityname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangjiaditu);
        ButterKnife.bind(this);
        mapview.removeViewAt(1);//隐藏logo
        mapview.removeViewAt(2);//隐藏比例尺
        mapview.showZoomControls(false);// 隐藏缩放控件
        baiduMap = mapview.getMap();
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        initLocation();
        tv_cityname.setText((String) CacheUtils.get("cityName"));
    }

    private void initChartNet(int id) {
        HttpParams params = new HttpParams();
        params.put("qId", id);
        OkGo.<ChartBean>post(MyUrls.BASEURL + "/app/houseresourse/maphouse")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<ChartBean>(ChartBean.class) {
                    @Override
                    public void onSuccess(Response<ChartBean> response) {
                        int code = response.code();
                        ChartBean body = response.body();
                        ChartBean.DatasBean datas = body.getDatas();
                        zxtlist = datas.getZxtlist();
                        monthbfb = datas.getMonthbfb();
                        yearbfb = datas.getYearbfb();
                        ChartBean.DatasBean.BigandsmallvalBean bigandsmallval = datas.getBigandsmallval();
                        bigVal = bigandsmallval.getBigVal();
                        endVal = bigandsmallval.getEndVal();
                        if (Tag.equals("2")) {
                            showMPChart(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                        }
                    }
                });
    }

    private void initChartList() {
        mlist.clear();
        mlist1.clear();
        xValue.clear();
        value.clear();
        value1.clear();
        yValue.clear();

        for (int i = 0; i < cityzxt.size(); i++) {
            mlist.add((float) cityzxt.get(i).getAvgPrice());
            xValue.add(MyUtils.getDateToStringM(String.valueOf(cityzxt.get(i).getDays())));
            value.put(MyUtils.getDateToStringM(String.valueOf(cityzxt.get(i).getDays())), mlist.get(i));
        }
        if (zxtlist != null && zxtlist.size() > 0) {
            for (int i = 0; i < zxtlist.size(); i++) {
                mlist1.add((float) zxtlist.get(i).getAvgPrice());
                value1.put(MyUtils.getDateToStringM(String.valueOf(zxtlist.get(i).getDays())), mlist1.get(i));
            }
        }

        double num = (bigValoneline - endValoneline) / 4;
        yValue.add((float) endValoneline);
        yValue.add(MyUtils.floatToString((float) ((float) endValoneline + num)));
        yValue.add(MyUtils.floatToString((float) ((float) endValoneline + num * 2)));
        yValue.add(MyUtils.floatToString((float) ((float) endValoneline + num * 3)));
        yValue.add((float) bigValoneline);
        Log.d("FangjiadituActivity", "bigValoneline:" + bigValoneline + "---------");
        for (int i = 0; i < yValue.size(); i++) {
            Log.d("FangjiadituActivity", "yValue.get(i):" + yValue.get(i) + "-----------");
        }
        for (int i = 0; i < mlist.size(); i++) {
            Log.d("FangjiadituActivity", "mlist.get(i):" + mlist.get(i) + "-------");
        }
        for (int i = 0; i < mlist1.size(); i++) {
            Log.d("FangjiadituActivity", "mlist1.get(i):" + mlist1.get(i) + "-------");
        }
    }


    @OnClick({R.id.btn_location, R.id.layout_pop, R.id.back_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_location:
                // 开启定位图层
                baiduMap.setMyLocationEnabled(true);
                MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
                //添加定位信息
                baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(14).build()));   // 设置级别
                LatLng ll = new LatLng(latitude,
                        longitude);
                baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(ll));
                MyLocationData.Builder builder = new MyLocationData.Builder();
                builder.latitude(latitude);
                builder.longitude(longitude);
                MyLocationData data = builder.build();
                //设置定位的小图标
                BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                        .fromResource(R.drawable.dingwei_logo1);
                baiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                        mCurrentMode, true, mCurrentMarker,
                        0, 0));

                //设置定位数据
                baiduMap.setMyLocationData(data);
                break;
            case R.id.layout_pop:
                Tag = "1";
                //一条折线
                showMPChartOneLine(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.back_img:
                finish();
                break;
        }
    }

    private void showMPChart(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_mp_chart)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        TextView tv_mouth = (TextView) dialog.findViewById(R.id.tv_mouth);
        TextView tv_years = (TextView) dialog.findViewById(R.id.tv_years);
        TextView tv_dialog_price = (TextView) dialog.findViewById(R.id.tv_dialog_price);
        tv_dialog_price.setText(avgMoney + "元/平");
        Drawable drawable = getResources().getDrawable(R.drawable.xiajiantou);
        //一定要加这行！！！！！！！！！！！
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        if (monthbfb >= 0) {//上月增长
            tv_mouth.setText(monthbfb + "");
        } else {//下降
            tv_mouth.setText(monthbfb * (-1) + "");
            tv_mouth.setCompoundDrawables(drawable, null, null, null);
        }
        if (yearbfb >= 0) {//去年增长
            tv_years.setText(yearbfb + "");
        } else {//下降
            tv_years.setCompoundDrawables(drawable, null, null, null);
            tv_years.setText(yearbfb * (-1) + "");
        }
        //绘制折线图
        initLineChart(dialog);
    }

    private void showMPChartOneLine(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_mp_chart_oneline)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        TextView tv_mouth = (TextView) dialog.findViewById(R.id.tv_mouth);
        TextView tv_years = (TextView) dialog.findViewById(R.id.tv_years);
        TextView tv_dialog_price = (TextView) dialog.findViewById(R.id.tv_dialog_price);
        tv_dialog_price.setText(avgMoneyoneline + "元/平");
        Drawable drawable = getResources().getDrawable(R.drawable.xiajiantou);
        //一定要加这行！！！！！！！！！！！
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        if (monthbfboneline >= 0) {//上月增长
            tv_mouth.setText(monthbfboneline + "");
        } else {//下降
            tv_mouth.setText(monthbfboneline * (-1) + "");
            tv_mouth.setCompoundDrawables(drawable, null, null, null);
        }
        if (yeaybfboneline >= 0) {//去年增长
            tv_years.setText(yeaybfboneline + "");
        } else {//下降
            tv_years.setCompoundDrawables(drawable, null, null, null);
            tv_years.setText(yeaybfboneline * (-1) + "");
        }
        //绘制折线图
        initLineChartOneLine(dialog);
    }

    private void initLineChart(BaseDialog dialog) {
        initChartList();
        ChartView chartview = (ChartView) dialog.findViewById(R.id.chartview);
        chartview.setValueTwoLine(value, value1, xValue, yValue);
    }

    private void initLineChartOneLine(BaseDialog dialog) {
        initChartList();
        ChartViewOneLine chartview = (ChartViewOneLine) dialog.findViewById(R.id.chartview);
        chartview.setValueOneLine(value, xValue, yValue);
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
                longitude = CacheUtils.get("mylongitude");
                latitude = CacheUtils.get("mylatitude");
                mCity = bdLocation.getCity();
                initMap(latitude, longitude);
                initOverlay();
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


    private void initOverlay() {
        baiduMap.clear();
        HttpParams params = new HttpParams();
        int cityId = CacheUtils.get("cityId");
        params.put("cId", cityId);
        OkGo.<FangjiaMapBean>post(MyUrls.BASEURL + "/app/city/fjmap")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<FangjiaMapBean>(FangjiaMapBean.class) {
                    @Override
                    public void onSuccess(Response<FangjiaMapBean> response) {
                        int code = response.code();
                        FangjiaMapBean body = response.body();
                        FangjiaMapBean.DatasBean datas = body.getDatas();
                        bigValoneline = datas.getBigandsmallval().getBigVal();
                        endValoneline = datas.getBigandsmallval().getEndVal();
                        FangjiaMapBean.DatasBean.CityBean city = datas.getCity();
                        avgMoneyoneline = city.getAvgMoney();
                        FangjiadituActivity.this.avgMoney = city.getAvgMoney();
                        cityzxt = datas.getCityzxt();
                        monthbfboneline = datas.getMonthbfb();
                        yeaybfboneline = datas.getYeaybfb();
                        List<FangjiaMapBean.DatasBean.QysBean> qys = datas.getQys();
                        if (datas != null && qys.size() > 0) {
                            List<MarkerBean> markerBeanList = new ArrayList<>();
                            List<OverlayOptions> overlayOptionsList = new ArrayList<>();
                            for (int i = 0; i < qys.size(); i++) {
                                FangjiaMapBean.DatasBean.QysBean datasEntity = qys.get(i);
                                markerBeanList.add(new MarkerBean(datasEntity.getLongitude(), datasEntity.getLatitude()));
                                View markView = LayoutInflater.from(mContext).inflate(R.layout.map_marker_view, null);
                                TextView title = (TextView) markView.findViewById(R.id.item_title_tv);
                                ImageView iv = (ImageView) markView.findViewById(R.id.iv_topordown);
                                TextView content = (TextView) markView.findViewById(R.id.item_content_tv);
                                content.setText(isJa ? datasEntity.getAdministrationNameJpn() : datasEntity.getAdministrationNameCn());
                                title.setText(datasEntity.getHouseNum() + "万套");
                                if (datasEntity.getPriceStete() == 0) {
                                    iv.setVisibility(View.GONE);
                                } else if (datasEntity.getPriceStete() == 1) {
                                    iv.setVisibility(View.VISIBLE);
                                    iv.setImageResource(R.drawable.arrowtop);
                                } else if (datasEntity.getPriceStete() == 2) {
                                    iv.setVisibility(View.VISIBLE);
                                    iv.setImageResource(R.drawable.arrowdown);
                                }
                                MarkerOptions markerOptions = new MarkerOptions()
                                        .icon(BitmapDescriptorFactory.fromView(markView))
                                        .position(new LatLng(markerBeanList.get(i).getWei(), markerBeanList.get(i).getJing()))
                                        .zIndex(11)
                                        .title(qys.get(i).getId() + "")
                                        .draggable(true);
                                overlayOptionsList.add(markerOptions);

                            }
                            baiduMap.addOverlays(overlayOptionsList);
                            baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    Tag = "2";
                                    initChartNet(Integer.parseInt(marker.getTitle()));
                                    return false;
                                }
                            });

                        }

                    }
                });
    }

}
