package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.model.NoDataBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * Created by   admin on 2018/5/30.
 */

public class RentalPresenter {
    private Activity activity;
    private RentalCallBack callBack;

    public RentalPresenter(Activity activity, RentalCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * @param userName              用户姓名
     * @param userContact           联系方式
     * @param housingLocation       房屋位置
     * @param stationDistance       车站距离 单位米
     * @param floor                 楼层 单位层
     * @param area                  面积 单位平方米
     * @param pattern               格局
     * @param bathroomTogether      是否洗卫一体 0是 1否
     * @param toward                朝向
     * @param surroundingFacilities 周边环境
     * @param entrustType           委托类型 0出租 1出售
     */
    public void requestRental(String userName, String userContact, String housingLocation, String stationDistance, String floor, String area,
                              String pattern, String bathroomTogether, String toward, String surroundingFacilities, String entrustType, String imageUrls, String videoUrls) {

        HttpParams params = new HttpParams();
        params.put("userName", userName);
        params.put("userContact", userContact);
        params.put("housingLocation", housingLocation);
        params.put("stationDistance", stationDistance);
        params.put("floor", floor);
        params.put("area", area);
        params.put("pattern", pattern);
        params.put("bathroomTogether", bathroomTogether);
        params.put("toward", toward);
        params.put("surroundingFacilities", surroundingFacilities);
        params.put("entrustType", entrustType);
        params.put("token", MyApplication.getUserToken());
        params.put("imageUrls", imageUrls);
        params.put("videoUrls", videoUrls);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/landlordrental/entrust")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<NoDataBean>(activity, NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {
                        callBack.requestRental(response);
                    }
                });

    }

    public interface RentalCallBack {
        void requestRental(Response<NoDataBean> response);
    }
}
