package com.example.administrator.japanhouse.presenter;

import android.app.Activity;

import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.model.ZNHouseBean;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * Created by   admin on 2018/5/16.
 */

public class ZNBuyHousePresenter {
    private Activity activity;
    private BuyHouseCallBack buyHouseCallBack;

    public ZNBuyHousePresenter(Activity activity, BuyHouseCallBack buyHouseCallBack) {
        this.activity = activity;
        this.buyHouseCallBack = buyHouseCallBack;
    }

    /**
     * 智能买房
     */
    public void getZNBuyHouseList() {
        OkGo.<ZNHouseBean>post(MyUrls.BASEURL + "/app/smartbyhouse/alllist")
                .tag(this)
                .execute(new DialogCallback<ZNHouseBean>(activity, ZNHouseBean.class) {
                    @Override
                    public void onSuccess(Response<ZNHouseBean> response) {
                        buyHouseCallBack.getZNBuyHouseList(response);
                    }
                });
    }

    public interface BuyHouseCallBack {
        void getZNBuyHouseList(Response<ZNHouseBean> response);
    }
}
