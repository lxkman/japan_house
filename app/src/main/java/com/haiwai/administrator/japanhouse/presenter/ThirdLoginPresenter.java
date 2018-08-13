package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.bean.LoginBean;
import com.haiwai.administrator.japanhouse.callback.JsonCallback;
import com.haiwai.administrator.japanhouse.model.NoDataBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * admin  2018/6/12
 */
public class ThirdLoginPresenter {
    private Activity activity;

    private ThirdLoginCallBack callBack;

    public ThirdLoginPresenter(Activity activity, ThirdLoginCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     *
     * @param loginType 登录类型 0微信 1qq 2微博 3line
     * @param uId
     */
    public void setThirdLogin(int loginType, String uId){
        HttpParams params = new HttpParams();
        params.put("loginType", loginType);
        params.put("uId", uId);
        OkGo.<LoginBean>post(MyUrls.BASEURL + "/app/user/specialogin")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<LoginBean>(LoginBean.class) {
                    @Override
                    public void onSuccess(Response< LoginBean > response) {
                        callBack.getThirdLogin(response);
                    }
                });
    }

    /**
     *
     * @param loginType 登录类型 0微信 1qq 2微博 3line
     * @param uId
     */
    public void bindThirdLogin(int loginType, String uId, String phone, String msg, String newPassword){
        HttpParams params = new HttpParams();
        params.put("loginType", loginType);
        params.put("uId", uId);
        params.put("phone", phone);
        params.put("msg", msg);
        params.put("newPassword", newPassword);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/user/bingdingphone")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<NoDataBean>(NoDataBean.class) {
                    @Override
                    public void onSuccess(Response< NoDataBean > response) {
                        callBack.bindThirdLogin(response);
                    }
                });
    }

    public interface ThirdLoginCallBack{
        void getThirdLogin(Response<LoginBean> response);
        void bindThirdLogin(Response<NoDataBean> response);
    }
}
