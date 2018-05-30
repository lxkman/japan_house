package com.example.administrator.japanhouse.fragment.comment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.HouseDetailsBean;
import com.example.administrator.japanhouse.bean.OldHouseListBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.im.DetailsExtensionModule;
import com.example.administrator.japanhouse.map.MapActivity;
import com.example.administrator.japanhouse.map.MyLocationListenner;
import com.example.administrator.japanhouse.more.OldHouseMoreActivity;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.example.administrator.japanhouse.view.BaseDialog;
import com.example.administrator.japanhouse.view.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.zackratos.ultimatebar.UltimateBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;

public class OldHousedetailsActivity extends BaseActivity {

    @BindView(R.id.vp_vidio)
    ViewPager vpVidio;
    @BindView(R.id.tv_to_num)
    TextView tvToNum;
    @BindView(R.id.tv_all_num)
    TextView tvAllNum;
    @BindView(R.id.Huxing_Recycler)
    RecyclerView HuxingRecycler;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.img_start)
    ImageView imgStart;
    @BindView(R.id.tv_See_More)
    TextView tvSeeMore;
    @BindView(R.id.love_Recycler)
    RecyclerView loveRecycler;
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.tv_suiyi)
    TextView tvSuiyi;
    @BindView(R.id.mScrollView)
    NestedScrollView mScrollView;
    @BindView(R.id.re_top_bg)
    RelativeLayout re_top_bg;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.bmapView)
    TextureMapView mapView;
    @BindView(R.id.shop_layout)
    LinearLayout shopLayout;
    @BindView(R.id.school_layout)
    LinearLayout schoolLayout;
    @BindView(R.id.youeryuan_layout)
    LinearLayout youeryuanLayout;
    @BindView(R.id.yiyuan_layout)
    LinearLayout yiyuanLayout;
    @BindView(R.id.tv_details_name)
    TextView tvDetailsName;
    @BindView(R.id.tv_details_price)
    TextView tvDetailsPrice;
    @BindView(R.id.tv_details_area)
    TextView tvDetailsArea;
    @BindView(R.id.tv_details_location)
    TextView tvDetailsLocation;
    @BindView(R.id.tv_details_manager_head)
    CircleImageView tvDetailsManagerHead;
    @BindView(R.id.tv_details_manager_name)
    TextView tvDetailsManagerName;
    @BindView(R.id.bdMap_layout)
    LinearLayout bdMapLayout;
    @BindView(R.id.lishi_old_wl)
    TextView lishiOldWl;
    @BindView(R.id.activity_lishi_new_house)
    RelativeLayout activityLishiNewHouse;
    private int mDistanceY;
    private LoveAdapter loveAdapter;
    private LiebiaoAdapter mLiebiaoAdapter;
    private List<String> mList = new ArrayList();
    private List<Fragment> mBaseFragmentList = new ArrayList<>();
    private FragmentManager fm;
    private MyAdapter myAdapter;
    private BaiduMap mBaiduMap;
    private LocationClient mLocClient;
    private MyLocationListenner myListener = new MyLocationListenner();
    private LocationManager locationManager;
    private Intent intent;
    private String Tag = "1";
    private boolean isJa;
    private String houseId;
    private HouseDetailsBean.DatasBean datas;
    //头部 添加相应地区
    private final static String BAIDU_HEAD = "baidumap://map/direction?region=0";
    //起点的经纬度
    private final static String BAIDU_ORIGIN = "&origin=";
    //终点的经纬度
    private final static String BAIDU_DESTINATION = "&destination=";
    //路线规划方式
    private final static String BAIDU_MODE = "&mode=walking";
    //百度地图的包名
    private final static String BAIDU_PKG = "com.baidu.BaiduMap";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //拉伸图片覆盖标题栏
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar(false);
        setContentView(R.layout.activity_lishi_old_house);
        ButterKnife.bind(this);
        //banner
        initViewPager();
        //户型图
        initData();
        //猜你喜欢
        initLoveRecycler();
        //渐变
        initScroll();
        //地图
        initMap();
        initLocation();
        //请求接口
        initDetailsNet();
        intent = new Intent(OldHousedetailsActivity.this, MapActivity.class);
        intent.putExtra("lat", "35.68");
        intent.putExtra("log", "139.75");
        findViewById(R.id.lishi_old_wl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.getInstace(OldHousedetailsActivity.this).setStringPreference(Constants.CHAT, Constants.CHAT_DETAILS);
                setMyExtensionModule();
                if (RongIM.getInstance() != null) {
                    Log.e("MainActivity", "创建单聊");
                    RongIM.getInstance().startPrivateChat(OldHousedetailsActivity.this, "123456", getString(R.string.act_chat_title));
                }
            }
        });

    }

    private void initDetailsNet() {
        houseId = getIntent().getStringExtra("houseId");
        String city = CacheUtils.get(Constants.COUNTRY);
        if (city != null && city.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        HttpParams params = new HttpParams();
//        if (houseId!=null&&!houseId.equals("")){
        params.put("hId", "8");
        OkGo.<HouseDetailsBean>post(MyUrls.BASEURL + "/app/houseresourse/houseinfo")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<HouseDetailsBean>(this, HouseDetailsBean.class) {
                    @Override
                    public void onSuccess(Response<HouseDetailsBean> response) {
                        int code = response.code();
                        HouseDetailsBean oldHouseListBean = response.body();
                        datas = oldHouseListBean.getDatas();
                        HouseDetailsBean.DatasBean.HwdcBrokerBean hwdcBroker = datas.getHwdcBroker();
                        tvDetailsName.setText(isJa ? datas.getTitleJpn() : datas.getTitleCn());
                        tvDetailsPrice.setText(isJa ? datas.getSellingPriceJpn() : datas.getSellingPriceCn());
                        tvDetailsArea.setText(isJa ? datas.getAreaJpn() : datas.getAreaCn());
                        tvDetailsLocation.setText(isJa ? datas.getSpecificLocationJpn() : datas.getSpecificLocationCn());
                        tvDetailsManagerName.setText(hwdcBroker.getBrokerName());
                        Glide.with(OldHousedetailsActivity.this).load(hwdcBroker.getPic() + "").into(tvDetailsManagerHead);
                    }
                });
    }

    //    }
    @OnClick({R.id.img_share, R.id.img_start, R.id.tv_See_More, R.id.back_img, R.id.shop_layout, R.id.school_layout, R.id.youeryuan_layout, R.id.yiyuan_layout,R.id.bdMap_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_share:
                showDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.img_start:
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_See_More:
                Intent intent1 = new Intent(OldHousedetailsActivity.this, OldHouseMoreActivity.class);
                intent1.putExtra("datas", datas);
                startActivity(intent1);
                break;
            case R.id.back_img:
                finish();
                break;
            case R.id.shop_layout:
                Tag = "1";
                intent.putExtra("TAG", Tag);
                startActivity(intent);
                break;
            case R.id.school_layout:
                Tag = "2";
                intent.putExtra("TAG", Tag);
                startActivity(intent);
                break;
            case R.id.youeryuan_layout:
                Tag = "3";
                intent.putExtra("TAG", Tag);
                startActivity(intent);
                break;
            case R.id.yiyuan_layout:
                Tag = "4";
                intent.putExtra("TAG", Tag);
                startActivity(intent);
                break;
            case R.id.bdMap_layout:

                //检测地图是否安装和唤起
                if (checkMapAppsIsExist(OldHousedetailsActivity.this,BAIDU_PKG)){
                    Toast.makeText(OldHousedetailsActivity.this,"百度地图已经安装",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setData(Uri.parse(BAIDU_HEAD+BAIDU_ORIGIN+"35.68"
                            +","+"139.75"+BAIDU_DESTINATION+"40.05"+","+"116.30"
                            +BAIDU_MODE));
                    startActivity(intent);
                }else {
                    Toast.makeText(OldHousedetailsActivity.this,"百度地图未安装",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    /**
     * 检测地图应用是否安装
     * @param context
     * @param packagename
     * @return
     */
    public boolean checkMapAppsIsExist(Context context, String packagename){
        PackageInfo packageInfo;
        try{
            packageInfo = context.getPackageManager().getPackageInfo(packagename,0);
        }catch (Exception e){
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null){
            return false;
        }else{
            return true;
        }
    }


    public void setMyExtensionModule() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new DetailsExtensionModule());
            }
        }
    }

    private void initLocation() {
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        //		option.setScanSpan(5000);// 设置发起定位请求的间隔时间,ms
        option.setNeedDeviceDirect(true);// 设置返回结果包含手机的方向
        option.setOpenGps(true);
        option.setAddrType("all");// 返回的定位结果包含地址信息
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
        option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        option.setIsNeedLocationPoiList(true);

        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    private void initMap() {
        mapView.removeViewAt(1);//隐藏logo
        mapView.removeViewAt(2);//隐藏比例尺
        mapView.showZoomControls(false);// 隐藏缩放控件


        mBaiduMap = mapView.getMap();

        UiSettings uiSettings = mBaiduMap.getUiSettings();
//        uiSettings. setScrollGesturesEnabled(false);//禁用平移的功能
//        uiSettings. setZoomGesturesEnabled(false);//禁用缩放手势
//        uiSettings. setOverlookingGesturesEnabled(false);//禁用俯视（3D）功能
//        uiSettings .setRotateGesturesEnabled(false);//禁用地图旋转功能
        uiSettings.setAllGesturesEnabled(false);//禁止所有手势

        myListener = new MyLocationListenner();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(14).build()));   // 设置级别
        LatLng ll = new LatLng(Double.parseDouble("35.68"),
                Double.parseDouble("139.75"));
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(ll));
        MyLocationData.Builder builder = new MyLocationData.Builder();
        builder.latitude(Double.parseDouble("35.68"));
        builder.longitude(Double.parseDouble("139.75"));
        MyLocationData data = builder.build();
        mBaiduMap.setMyLocationData(data);
        MapClick();
    }

    private void MapClick() {
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent intent = new Intent(OldHousedetailsActivity.this, MapActivity.class);
                intent.putExtra("lat", "35.68");
                intent.putExtra("log", "139.75");
                intent.putExtra("TAG", "1");
                startActivity(intent);

            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
    }

    private void initScroll() {
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //滑动的距离
                mDistanceY += scrollY - oldScrollY;
                //toolbar的高度
                int toolbarHeight = 300;//我写死的高度

                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= toolbarHeight) {
                    float scale = (float) mDistanceY / toolbarHeight;
                    float alpha = scale * 255;
                    re_top_bg.setBackgroundColor(Color.argb((int) alpha, 199, 151, 127));
                    tv_title.setVisibility(View.GONE);
                } else {
                    //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
                    //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
                    //将标题栏的颜色设置为完全不透明状态
                    re_top_bg.setBackgroundResource(R.color.shihuangse);
                    tv_title.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initViewPager() {
        if (mBaseFragmentList.size() <= 0) {
//            mBaseFragmentList.add(new VidioFragment());
            mBaseFragmentList.add(new BannerFragment());
            mBaseFragmentList.add(new BannerFragment());
            mBaseFragmentList.add(new BannerFragment());
        }
        tvAllNum.setText(mBaseFragmentList.size() + "");
        fm = getSupportFragmentManager();
        myAdapter = new MyAdapter(fm);
        vpVidio.setAdapter(myAdapter);
        vpVidio.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvToNum.setText((position + 1) + "");
                if (position == 1) {
                    JZVideoPlayer.releaseAllVideos();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    private void initData() {
        if (mList.size() <= 0) {
            mList.add("");
            mList.add("");
            mList.add("");
        }


        if (mLiebiaoAdapter == null) {
            mLiebiaoAdapter = new LiebiaoAdapter(R.layout.huxing_item, mList);
        }
        HuxingRecycler.setLayoutManager(new GridLayoutManager(OldHousedetailsActivity.this, 3));
        HuxingRecycler.setNestedScrollingEnabled(false);
        HuxingRecycler.setAdapter(mLiebiaoAdapter);
        mLiebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(OldHousedetailsActivity.this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initLoveRecycler() {
        HttpParams params = new HttpParams();
        if (isJa) {
            params.put("languageType", 1);
        } else {
            params.put("languageType", 0);
        }
        params.put("hType", 0);
        params.put("pageNo","1");
        OkGo.<OldHouseListBean>post(MyUrls.BASEURL + "/app/houseresourse/searchlist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<OldHouseListBean>(OldHousedetailsActivity.this, OldHouseListBean.class) {
                    @Override
                    public void onSuccess(Response<OldHouseListBean> response) {
                        int code = response.code();
                        final OldHouseListBean oldHouseListBean = response.body();
                        if (oldHouseListBean == null) {
                            return;
                        }
                        List<OldHouseListBean.DatasBean> datas = oldHouseListBean.getDatas();
                        if (loveAdapter == null) {
                            loveAdapter = new LoveAdapter(R.layout.item_zuijin, datas);
                        }
                        loveRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        loveRecycler.setNestedScrollingEnabled(false);
                        loveRecycler.setAdapter(loveAdapter);
                        loveAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(OldHousedetailsActivity.this, OldHousedetailsActivity.class);
                                intent.putExtra("houseId",oldHouseListBean.getDatas().get(position).getId());
                                startActivity(intent);
                            }
                        });
                    }
                });
    }
    class LoveAdapter extends BaseQuickAdapter<OldHouseListBean.DatasBean, BaseViewHolder> {

        public LoveAdapter(@LayoutRes int layoutResId, @Nullable List<OldHouseListBean.DatasBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OldHouseListBean.DatasBean item) {
            helper.setText(R.id.tv_house_name,isJa?item.getTitleJpn():item.getTitleCn());
            helper.setText(R.id.tv_house_address,isJa?item.getSpecificLocationJpn():item.getSpecificLocationCn());
            helper.setText(R.id.tv_house_room,isJa?item.getDoorModelJpn():item.getDoorModelCn());
            helper.setText(R.id.tv_house_area,isJa?item.getAreaJpn():item.getAreaCn());
            helper.setText(R.id.tv_price,isJa?item.getPriceJpn():item.getPriceCn());
            Glide.with(OldHousedetailsActivity.this).load(item.getRoomImgs()).into((ImageView) helper.getView(R.id.img_house));
        }
    }


    class MyAdapter extends FragmentStatePagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mBaseFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mBaseFragmentList.size();
        }
    }


    private void showDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_share)
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
        LinearLayout weiliao_layout = (LinearLayout) dialog.findViewById(R.id.weiliao_layout);
        LinearLayout weixin_layout = (LinearLayout) dialog.findViewById(R.id.weixin_layout);
        LinearLayout pengyouquan_layout = (LinearLayout) dialog.findViewById(R.id.pengyouquan_layout);
        LinearLayout weibo_layout = (LinearLayout) dialog.findViewById(R.id.weibo_layout);
        TextView tv_dismiss = (TextView) dialog.findViewById(R.id.tv_dismiss);
        //微聊分享
        weiliao_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //微信分享
        weixin_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //朋友圈分享
        pengyouquan_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //微博分享
        weibo_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //取消
        tv_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    class LiebiaoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
        }
    }



}
