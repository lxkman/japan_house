package com.haiwai.administrator.japanhouse.login;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.utils.CacheUtils;
import com.haiwai.administrator.japanhouse.utils.Constants;
import com.haiwai.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.haiwai.administrator.japanhouse.utils.SpUtils;
import com.haiwai.administrator.japanhouse.utils.TUtils;
import com.orhanobut.logger.Logger;

import org.zackratos.ultimatebar.UltimateBar;

import java.util.HashMap;
import java.util.Locale;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by Administrator on 2017/8/24.
 */

public class LancherActivity extends BaseActivity {
    private Handler mHandler = new Handler();
    private ImageView iv_launcher;
    public LocationClient mLocationClient = null;
    private BDLocationListener myListener = new MyLocationListener();
    private String location;
    private int languageType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()) {
            //部分机型（OPPOA37、mi2）第一次安装时，点击Home键后，在打开应用，应用会重新启动
            finish();
            return;
        }
        //拉伸图片覆盖标题栏
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar(false);
        setContentView(R.layout.activity_launcher);

        iv_launcher = (ImageView) findViewById(R.id.iv_launcher);
//        String location = SharedPreferencesUtils.getInstace(this).getStringPreference("city", "");

        if (CacheUtils.get(Constants.MANAGER_T) == null) {
            CacheUtils.put(Constants.MANAGER_T, "1");
        }

        location = CacheUtils.get(Constants.COUNTRY);
        if (!TextUtils.isEmpty(location)) {
            Resources resources = getResources();
            DisplayMetrics dm = resources.getDisplayMetrics();
            Configuration config = resources.getConfiguration();
            Locale myLocale = new Locale(location);
            config.locale = myLocale;
            resources.updateConfiguration(config, dm);
            if (location.equals("ja")) {
//                iv_launcher.setBackground(getResources().getDrawable(R.drawable.start_bg));
                languageType=1;
            }else {
                languageType=0;
            }
        } else {
            String locale = Locale.getDefault().getLanguage();
            if (TextUtils.equals(locale, "ja")) {
//                iv_launcher.setBackground(getResources().getDrawable(R.drawable.start_bg));
                CacheUtils.put(Constants.COUNTRY, locale);
                languageType=1;
            }else {
                CacheUtils.put(Constants.COUNTRY, "zh");
                languageType=0;
            }
        }

        initLocation();//百度地图定位
        mLocationClient.start();

        initLancherNet();
    }

    private void initLancherNet() {
        if (languageType==0){
            Glide.with(mContext).load(R.drawable.launcher_zh).into(iv_launcher);
        }else if (languageType==1){
            Glide.with(mContext).load(R.drawable.start_bg).into(iv_launcher);
        }
//        HttpParams params = new HttpParams();
//        params.put("imageType", "0");
//        params.put("languageType",languageType);
//        OkGo.<LancherBean>post(MyUrls.BASEURL + "/app/image/images")
//                .tag(this)
//                .params(params)
//                .execute(new DialogCallback<LancherBean>(this, LancherBean.class) {
//                    @Override
//                    public void onSuccess(Response<LancherBean> response) {
//                        int code = response.code();
//                        LancherBean LancherBean = response.body();
//                        String locale = Locale.getDefault().getLanguage();
//                        if (LancherBean != null) {
//                            if (LancherBean.getCode().equals("200")) {
//                                if (LancherBean.getDatas()!=null && LancherBean.getDatas().size()>0){
//                                    if (TextUtils.equals(locale, "ja")) {
//                                        Glide.with(LancherActivity.this).load(LancherBean.getDatas().get(0).getImageUrl()).into(iv_launcher);
//                                    } else {
//                                        Glide.with(LancherActivity.this).load(LancherBean.getDatas().get(0).getImageUrl()).into(iv_launcher);
//                                    }
//                                }


                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        boolean guide = SharedPreferencesUtils.getInstace(LancherActivity.this).getBooleanPreference("guide", false);
                                        if (!guide) {
                                            Intent intent = new Intent(LancherActivity.this, GuidePageActivity.class);
                                            startActivity(intent);
                                            finish();

                                        } else {
                                            HashMap<String, Boolean> hashMap = new HashMap<>();
                                            //会话类型 以及是否聚合显示
                                            hashMap.put(Conversation.ConversationType.PRIVATE.getName(), false);
//        hashMap.put(Conversation.ConversationType.PUSH_SERVICE.getName(),true);
//        hashMap.put(Conversation.ConversationType.SYSTEM.getName(),true);
                                            RongIM.getInstance().startConversationList(LancherActivity.this, hashMap);
                                            finish();

                                        }
                                    }
                                }, 3000);
//                            }
//                        }
//                    }
//                });

    }

    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());//声明LocationClient类
        mLocationClient.registerLocationListener(myListener);//注册监听函数

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
        int span = 0;
        option.setScanSpan(span);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);// 可选，默认false,设置是否使用gps
        option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);// 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);// 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);// 可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            int errorCode = location.getLocType();//获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            Logger.e(errorCode + "");
            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            String coorType = location.getCoorType();//获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            String city = location.getCity();
            mLocationClient.stop();
            if (errorCode == 61 || errorCode == 66 || errorCode == 161) {
                SpUtils.putString("latitude", String.valueOf(latitude));
                SpUtils.putString("longitude", String.valueOf(longitude));
                SpUtils.putString("city", city);
                Logger.e("纬度：" + latitude + "\n经度：" + longitude + "\n定位城市：" + city);
            } else {
                TUtils.showShort(getApplicationContext(), "百度地图定位失败，请检查网络操作后重试。");
            }
        }
    }

}
