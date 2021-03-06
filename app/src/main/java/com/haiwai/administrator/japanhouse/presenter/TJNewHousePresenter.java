package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.model.HouseListBean;
import com.haiwai.administrator.japanhouse.model.LandBean;
import com.haiwai.administrator.japanhouse.utils.CacheUtils;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * Created by   admin on 2018/5/16.
 */

public class TJNewHousePresenter {
    private Activity activity;
    private HouseCallBack houseCallBack;

    public TJNewHousePresenter(Activity activity, HouseCallBack houseCallBack) {
        this.activity = activity;
        this.houseCallBack = houseCallBack;
    }

    /**
     * 推荐列表查询(新房、二手房、租房)
     * @param pageNo
     * @param hType
     */
    public void getHouseList(int pageNo, String hType){
        if (CacheUtils.get("cityId")==null){
            return;
        }
            int cityId = CacheUtils.get("cityId");
            HttpParams params = new HttpParams();
            params.put("pageNo", pageNo);
            params.put("hType", hType);
            params.put("cId", cityId);
            OkGo.<HouseListBean>post(MyUrls.BASEURL + "/app/houseresourse/recommendlist")
                    .tag(this)
                    .params(params)
                .execute(new DialogCallback<HouseListBean>(activity, HouseListBean.class) {
                    @Override
                    public void onSuccess(Response<HouseListBean> response) {
                        houseCallBack.getHouseList(response);
                    }
                });
    }


    /**
     * 推荐列表查询（土地）
     * @param pageNo
     */
    public void getLand(int pageNo){
        if (CacheUtils.get("cityId")==null){
            return;
        }
        int cityId = CacheUtils.get("cityId");
        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("cId", cityId);
        OkGo.<LandBean>post(MyUrls.BASEURL + "/app/land/recommendland")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<LandBean>(activity, LandBean.class) {
                    @Override
                    public void onSuccess(Response<LandBean> response) {
                        houseCallBack.getLand(response);
                    }
                });
    }

    public interface HouseCallBack{
        void getHouseList(Response<HouseListBean> response);
        void getLand(Response<LandBean> response);
    }
}
