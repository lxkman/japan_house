package com.example.administrator.japanhouse.presenter;

import android.app.Activity;

import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.model.VillaDetailsBean;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * Created by   admin on 2018/5/23.
 */

public class VillaDetailsPresenter {
    private Activity activity;
    private VillaDetailsCallBack callBack;

    public VillaDetailsPresenter(Activity activity, VillaDetailsCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     *  别墅详情查询
     * @param vId 别墅ID
     */
    public void getVillaDetails(String vId){
        HttpParams params = new HttpParams();
        params.put("vId", vId);
        OkGo.<VillaDetailsBean>post(MyUrls.BASEURL + "/app/villadom/vildominfo")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<VillaDetailsBean>(activity, VillaDetailsBean.class) {
                    @Override
                    public void onSuccess(Response<VillaDetailsBean> response) {
                        callBack.getVillaDetails(response);
                    }
                });
    }

    public interface VillaDetailsCallBack{
        void getVillaDetails(Response<VillaDetailsBean> response);
    }
}
