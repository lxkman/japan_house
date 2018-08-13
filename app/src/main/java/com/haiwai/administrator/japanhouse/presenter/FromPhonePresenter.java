package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.callback.JsonCallback;
import com.haiwai.administrator.japanhouse.model.AgentInfoBean;
import com.haiwai.administrator.japanhouse.model.NoDataBean;
import com.haiwai.administrator.japanhouse.model.UserBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * admin  2018/6/13
 */
public class FromPhonePresenter {
    private Activity activity;
    private PhoneCallBack callBack;

    public FromPhonePresenter(Activity activity, PhoneCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * 获取手机号
     *
     * @param userId
     */
    public void getUserPhone(String userId) {
        HttpParams params = new HttpParams();
        params.put("userId", MyApplication.getUserId(activity));
        params.put("brokerId", userId);
        OkGo.<UserBean>post(MyUrls.BASEURL + "/app/user/getuserinfo")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<UserBean>(UserBean.class) {
                    @Override
                    public void onSuccess(Response<UserBean> response) {
                        callBack.getUserPhone(response);
                    }
                });
    }

    public void getManagerInfo(String brokerId){
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

    /**
     * 添加联系人
     *
     * @param brokerId
     */
    public void addLinkman(String brokerId) {
        HttpParams params = new HttpParams();
        params.put("userId", MyApplication.getUserId(activity));
        params.put("brokerId", brokerId);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/contact/insertcontact")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<NoDataBean>(NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {

                    }
                });
    }

    /**
     * 删除联系人
     *
     * @param brokerId
     */
    public void deteleLinkman(String brokerId) {
        HttpParams params = new HttpParams();
        params.put("userId", MyApplication.getUserId(activity));
        params.put("brokerId", brokerId);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/contact/deletecontact")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<NoDataBean>(NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {

                    }
                });
    }

    /**
     * @param brokerId
     */
    public void recordReply(String brokerId) {
        HttpParams params = new HttpParams();
        params.put("userId", MyApplication.getUserId(activity));
        params.put("brokerId", brokerId);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/brokerrevler/insertbrokerrevler")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<NoDataBean>(NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {

                    }
                });
    }

    public interface PhoneCallBack {
        void getUserPhone(Response<UserBean> response);
        void getManagerInfo(Response<AgentInfoBean> response);
    }
}
