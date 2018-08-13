package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.callback.JsonCallback;
import com.haiwai.administrator.japanhouse.model.NoDataBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * admin  2018/6/9
 */
public class HouseLogPresenter {
    private Activity activity;

    public HouseLogPresenter(Activity activity) {
        this.activity = activity;
    }

    /**
     * 看房日志
     * @param houseType
     * @param houseId
     * @param shType
     */
    public void setHouseLog(String houseType, String houseId, String shType){
        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        params.put("houseType", houseType);
        params.put("houseId", houseId);
        params.put("shType", shType);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/seehouselog/insertkflog")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<NoDataBean>(NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {
                    }
                });
    }
}
