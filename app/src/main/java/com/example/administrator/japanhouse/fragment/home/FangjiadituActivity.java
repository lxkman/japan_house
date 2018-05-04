package com.example.administrator.japanhouse.fragment.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.MarkerBean;
import com.example.administrator.japanhouse.view.BaseDialog;
import com.example.administrator.japanhouse.view.ChartView;
import com.example.administrator.japanhouse.view.ChartViewOneLine;

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
    List<Float> mlist=new ArrayList<>();
    //第二条折线对应的折点
    List<Float> mlist1=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangjiaditu);
        ButterKnife.bind(this);
        initMap();
        initChartList();
    }

    private void initChartList() {
        for (int i = 10; i > 0; i--) {
            mlist.add((float) (Math.random()*3.5+0.5));
            mlist1.add((float) (Math.random()*3.5+0.5));
        }
        for (int i =0; i <10 ; i++) {
            xValue.add("11-1"+i);
            value.put("11-1"+i, mlist.get(i));
            value1.put("11-1"+i, mlist1.get(i));
        }
        yValue.add((float) 0.55);
        yValue.add((float) 1.55);
        yValue.add((float) 2.55);
        yValue.add((float) 3.55);
        yValue.add((float) 4.55);
    }


    @OnClick({R.id.btn_location, R.id.layout_pop,R.id.back_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_location:
                break;
            case R.id.layout_pop:
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
        final BaseDialog  dialog = builder.setViewId(R.layout.dialog_mp_chart)
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
        //绘制折线图
        initLineChart(dialog);
    }
    private void showMPChartOneLine(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final BaseDialog  dialog = builder.setViewId(R.layout.dialog_mp_chart_oneline)
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
        //绘制折线图
        initLineChartOneLine(dialog);
    }
    private void initLineChart(BaseDialog dialog) {
        ChartView chartview = (ChartView) dialog.findViewById(R.id.chartview);
        chartview.setValueTwoLine(value,value1, xValue, yValue);
    }
    private void initLineChartOneLine(BaseDialog dialog) {
        ChartViewOneLine chartview = (ChartViewOneLine) dialog.findViewById(R.id.chartview);
        chartview.setValueOneLine(value1, xValue, yValue);
    }

    private void initMap() {
        mapview.removeViewAt(1);//隐藏logo
        mapview.removeViewAt(2);//隐藏比例尺
        mapview.showZoomControls(false);// 隐藏缩放控件
        baiduMap = mapview.getMap();

        LatLng center = new LatLng(35.68, 139.75); // 默认 东京
        float zoom = 13.0f; // 默认 11级
        MapStatus mMapStatus = new MapStatus.Builder().target(
                center).zoom(zoom).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory
                .newMapStatus(mMapStatus);
        baiduMap.setMapStatus(mMapStatusUpdate);
        initOverlay();
    }


    private void initOverlay() {
        List<MarkerBean> markerBeanList = new ArrayList<>();
        markerBeanList.add(new MarkerBean(139.738954,35.707239));
        markerBeanList.add(new MarkerBean(139.83439,35.678863));
        markerBeanList.add(new MarkerBean(139.741541,35.643203));
        markerBeanList.add(new MarkerBean(139.690661,35.638979));
        markerBeanList.add(new MarkerBean(139.758788,35.684492));
        markerBeanList.add(new MarkerBean(139.758788,35.728807));



        List<OverlayOptions> overlayOptionsList = new ArrayList<>();
        for (int i = 0; i < markerBeanList.size(); i++) {
            View markView = LayoutInflater.from(mContext).inflate(R.layout.map_marker_view,null);
            TextView title = (TextView) markView.findViewById(R.id.item_title_tv);
            ImageView iv = (ImageView) markView.findViewById(R.id.iv_topordown);
            TextView content = (TextView) markView.findViewById(R.id.item_content_tv);
            title.setText("5.6万套");
            iv.setVisibility(View.GONE);
            MarkerOptions markerOptions = new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromView(markView))
                    .position(new LatLng(markerBeanList.get(i).getWei(), markerBeanList.get(i).getJing()))
                    .zIndex(13)
                    .draggable(true);
            overlayOptionsList.add(markerOptions);
        }
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                showMPChart(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                return false;
            }
        });
        baiduMap.addOverlays(overlayOptionsList);
    }

}
