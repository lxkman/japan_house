package com.example.administrator.japanhouse.presenter;

import android.app.Activity;

import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.model.HouseListBean;
import com.example.administrator.japanhouse.model.LandBean;
import com.example.administrator.japanhouse.utils.MyUrls;
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
        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("hType", hType);
        params.put("cId", "2");
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
        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("cId", "2");
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
