package com.example.administrator.japanhouse.presenter;

import android.app.Activity;

import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.model.AgentInfoBean;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;


/**
 * admin  2018/6/22
 */
public class ManagerPresenter {
    private Activity activity;
    private ManagerCallBack callBack;

    public ManagerPresenter(Activity activity, ManagerCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    public void getManagerInfo(int brokerId){
        HttpParams params = new HttpParams();
        params.put("brokerId", brokerId);
        OkGo.<AgentInfoBean>post(MyUrls.BASEURL + "/app/broker/getbrokerinfo")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<AgentInfoBean>(AgentInfoBean.class) {
                    @Override
                    public void onSuccess(Response<AgentInfoBean> response) {
                        callBack.getManagerInfo(response);
                    }
                });
    }

    public interface ManagerCallBack {
        void getManagerInfo(Response<AgentInfoBean> response);
    }
}
