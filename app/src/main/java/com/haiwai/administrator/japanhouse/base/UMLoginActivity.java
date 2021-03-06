package com.haiwai.administrator.japanhouse.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.bean.LoginBean;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.login.BindPhoneActivity;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.haiwai.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.haiwai.administrator.japanhouse.utils.TUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by lxk on 2017/7/3.
 */

public class UMLoginActivity extends BaseActivity {

    private static Activity mContext;
    private String umpushid;

    /*
            * 授权中只是能拿到uid，openid，token这些授权信息，想获取用户名和用户资料，需要使用这个接口
            * 其中umAuthListener为授权回调，构建如下，其中授权成功会回调onComplete，取消授权回调onCancel，
            * 授权错误回调onError，对应的错误信息可以用过onError的Throwable参数来打印
            * */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected static void loginBySina(Activity context) {
        mContext = context;
        UMShareAPI.get(context).getPlatformInfo(context, SHARE_MEDIA.SINA, umAuthListener);
    }

    protected static void loginByWeiXin(Activity context) {
        mContext = context;
        UMShareAPI.get(context).getPlatformInfo(context, SHARE_MEDIA.WEIXIN, umAuthListener);
    }

    protected static void loginByQQ(Activity context) {
        mContext = context;
        UMShareAPI.get(context).getPlatformInfo(context, SHARE_MEDIA.QQ, umAuthListener);
    }

    private static UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
//            Toast.makeText(MyApplication.getGloableContext(), "授权开始回调", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            /*
        * 登录成功后，第三方平台会将用户资料传回， 全部会在Map data中返回 ，由于各个平台对于用户资料的标识不同，
        * 因此为了便于开发者使用，我们将一些常用的字段做了统一封装，开发者可以直接获取，
        * 不再需要对不同平台的不同字段名做转换，这里列出我们封装的字段及含义
        * */

            final String username = data.get("name");
            final String userhead = data.get("iconurl");
            final String uid = data.get("uid");

            String type = "";
            if (platform.equals(SHARE_MEDIA.QQ)) {
                type = "1";
            } else if (platform.equals(SHARE_MEDIA.WEIXIN)) {
                type = "0";
            } else if (platform.equals(SHARE_MEDIA.SINA)) {
                type = "2";
            } else if (platform.equals(SHARE_MEDIA.LINE)) {
                type = "3";
            }
            final String finalType = type;
            HttpParams params = new HttpParams();
            params.put("loginType", finalType);
            params.put("uId", uid);
            OkGo.<LoginBean>post(MyUrls.BASEURL + "/app/user/specialogin")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<LoginBean>(mContext, LoginBean.class) {
                        @Override
                        public void onSuccess(Response<LoginBean> response) {
                            int code = response.code();
                            LoginBean LoginBean = response.body();
                            if (LoginBean.getCode().equals("200")) {
                                SharedPreferencesUtils.getInstace(mContext).setStringPreference("uid", response.body().getDatas().getId() + "");
                                SharedPreferencesUtils.getInstace(mContext).setStringPreference("token", response.body().getDatas().getToken() + "");
                                TUtils.showFail(MyApplication.getGloableContext(), mContext.getString(R.string.dengluchenggong));
                                HashMap<String, Boolean> hm = new HashMap<>();
                                //会话类型 以及是否聚合显示
                                hm.put(Conversation.ConversationType.PRIVATE.getName(), false);
                                //        hashMap.put(Conversation.ConversationType.PUSH_SERVICE.getName(),true);
                                //        hashMap.put(Conversation.ConversationType.SYSTEM.getName(),true);
                                RongIM.getInstance().startConversationList(mContext, hm);
                            } else if (LoginBean.getCode().equals("-1")) {
                                Intent intent = new Intent(mContext, BindPhoneActivity.class);
                                intent.putExtra("loginType", finalType);
                                intent.putExtra("uId", uid);
                                mContext.startActivity(intent);
                                Toast.makeText(mContext, LoginBean.getMsg(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, LoginBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Response<LoginBean> response) {
                            super.onError(response);
                            Log.e("xxxxxxxxxx","登陆失败,服务器出现问题"+response.getException().getMessage());
                        }
                    });

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            TUtils.showFail(MyApplication.getGloableContext(), mContext.getString(R.string.denglushibai));
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            TUtils.showFail(MyApplication.getGloableContext(), mContext.getString(R.string.quxiaodenglu));
        }
    };


    /*
    * 最后在登录所在的Activity里复写onActivityResult方法,注意不可在fragment中实现，如果在fragment中调用登录，
    * 在fragment依赖的Activity中实现，如果不实现onActivityResult方法，会导致登录或回调无法正常进行
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

}
