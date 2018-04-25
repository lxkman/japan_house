package com.example.administrator.japanhouse.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.bean.MarkerBean;
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by power on 2018/4/20.
 */

public class MapNewhouseFragment extends BaseFragment implements MyItemClickListener {
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    Unbinder unbinder;
    private List<View> popupViews;
    private List<OneCheckBean> list;
    private MapView mBaiduMap;
    private BaiduMap baiduMap;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_old, null, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
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
        FirstView firstView = new FirstView(mContext);
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
        fourView.insertData(list3, dropDownMenu);
        fourView.setListener(this);
        /**
         * Dropdownmenu下面的主体部分
         * */
        String headers[] = {"售价", "楼层", "年份", "更多"};
        View fifthView = LayoutInflater.from(mContext).inflate(R.layout.dropdown_map_layout, null);
        mBaiduMap = (MapView) fifthView.findViewById(R.id.mapview);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, fifthView);
        mBaiduMap.removeViewAt(1);//隐藏logo
        mBaiduMap.removeViewAt(2);//隐藏比例尺
        mBaiduMap.showZoomControls(false);// 隐藏缩放控件

        baiduMap = mBaiduMap.getMap();
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

    @Override
    public void onItemClick(View view, int postion, String string) {

    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mBaiduMap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBaiduMap.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBaiduMap.onDestroy();
        unbinder.unbind();
    }
}
