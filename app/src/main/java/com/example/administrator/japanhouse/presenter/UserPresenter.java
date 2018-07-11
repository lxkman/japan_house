package com.example.administrator.japanhouse.presenter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.login.LoginActivity;
import com.example.administrator.japanhouse.model.NoDataBean;
import com.example.administrator.japanhouse.model.UserInfo;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * admin  2018/6/6
 */
public class UserPresenter {
    private Activity activity;
    private UserCallBack callBack;

    public UserPresenter(Activity activity, UserCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * 获取用户信息
     *
     * @param token
     */
    public void getUserInfo(String token) {
        HttpParams params = new HttpParams();
        params.put("token", token);
        OkGo.<UserInfo>post(MyUrls.BASEURL + "/app/user/getmyinfo")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<UserInfo>(UserInfo.class) {
                    @Override
                    public void onSuccess(Response<UserInfo> response) {
                            callBack.getUserInfo(response);
                    }
                });
    }

    /**
     * 更新用户信息
     *
     * @param token
     * @param nickName
     * @param sex
     * @param age
     */
    public void updateUserInfo(String token, String nickName, int sex, String age, String picUrl) {
        HttpParams params = new HttpParams();
        params.put("token", token);
        params.put("nickName", nickName);
        params.put("sex", sex);
        params.put("age", age);
        params.put("picUrl", picUrl);
        params.put("isBrokerSay", MyApplication.getSwitchState());
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/user/updateinfo")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<NoDataBean>(activity, NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {
                            callBack.updateUserInfo(response);
                    }
                });
    }

    public interface UserCallBack {
        void getUserInfo(Response<UserInfo> response);

        void updateUserInfo(Response<NoDataBean> response);
    }
}
