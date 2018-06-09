package com.example.administrator.japanhouse.presenter;

import android.app.Activity;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.model.NoDataBean;
import com.example.administrator.japanhouse.utils.MyUrls;
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
    public void setHouseLog(int houseType, int houseId, int shType){
        HttpParams params = new HttpParams();
        params.put("userId", MyApplication.getUserId());
        params.put("houseType", houseType);
        params.put("houseId", houseId);
        params.put("shType", shType);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/seehouselog/insertkflog")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<NoDataBean>(activity, NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {
                    }
                });
    }
}
