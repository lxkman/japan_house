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
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.MarkerBean;
import com.example.administrator.japanhouse.view.BaseDialog;

import java.util.ArrayList;
import java.util.List;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangjiaditu);
        ButterKnife.bind(this);
        initMap();
    }
    @OnClick({R.id.btn_location, R.id.layout_pop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_location:
                break;
            case R.id.layout_pop:
                showMPChart(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
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
            TextView count = (TextView) markView.findViewById(R.id.item_count_tv);
            TextView content = (TextView) markView.findViewById(R.id.item_content_tv);
            content.setText("地名"+i+"\n"+"0.2万套");
            MarkerOptions markerOptions = new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromView(markView))
                    .position(new LatLng(markerBeanList.get(i).getWei(), markerBeanList.get(i).getJing()))
                    .zIndex(13)
                    .draggable(true);
            overlayOptionsList.add(markerOptions);
        }
        baiduMap.addOverlays(overlayOptionsList);
    }

}
