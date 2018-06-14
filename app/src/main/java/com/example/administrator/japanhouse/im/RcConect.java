package com.example.administrator.japanhouse.im;

import android.net.Uri;
import android.util.Log;

import com.example.administrator.japanhouse.bean.LoginBean;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

/**
 * admin  2018/6/12
 */
public class RcConect {

    public static void rongCloudConection(String token){
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String s) {
                Log.e("MainActivity", "——onSuccess—-" +
                        s.toString());
                setUserInfo();
                setUserInfo1();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("MainActivity", "——onError—-" +
                        errorCode);
            }

            @Override
            public void onTokenIncorrect() {
                //Connect Token 失效的状态处理，需要重新获取 Token
            }
        });
    }

    public static void setUserInfo(){
        final LoginBean.DatasBean loginBean = CacheUtils.get(Constants.USERINFO);

        if (loginBean == null)
            return;

        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String s) {
                return new UserInfo(loginBean.getId() + "", loginBean.getNickname(), Uri.parse(loginBean.getPic()));
            }
        }, true);
    }

    public static void setUserInfo1(){
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String s) {
                return new UserInfo("23", "张杰围", Uri.parse("http://imgsrc.baidu.com/forum/w=580/sign=1588b7c5d739b6004dce0fbfd9503526/7bec54e736d12f2eb97e1a464dc2d56285356898.jpg"));
            }
        }, true);
    }
}
