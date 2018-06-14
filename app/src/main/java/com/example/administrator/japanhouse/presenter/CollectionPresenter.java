package com.example.administrator.japanhouse.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.login.LoginActivity;
import com.example.administrator.japanhouse.model.NoDataBean;
import com.example.administrator.japanhouse.more.CollectionListBean;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.TUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * Created by   admin on 2018/5/31.
 */

public class CollectionPresenter {
    private Activity activity;
    private CollectionCallBack callBack;

    public CollectionPresenter(Activity activity, CollectionCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * 查看用户收藏房源列表
     *
     * @param pageNo
     * @param token
     */
    public void getCollectionHouseList(int pageNo, String token) {
        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("token", token);
        OkGo.<CollectionListBean>post(MyUrls.BASEURL + "/app/collectionhouse/collectionlist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<CollectionListBean>(activity, CollectionListBean.class) {
                    @Override
                    public void onSuccess(Response<CollectionListBean> response) {
                        if (TextUtils.equals(response.body().getCode(), "201")) {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                            MyApplication.logOut();
                        } else {
                            callBack.getCollectionHouseList(response);
                        }
                    }

                    @Override
                    public void onError(Response<CollectionListBean> response) {
                        super.onError(response);

                        callBack.collectionNetwork();

                    }
                });
    }

    /**
     * 取消收藏
     *
     * @param hType
     * @param shType
     * @param hId
     */
    public void deteleCollectionHouse(String hType, String shType, int hId) {
        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        params.put("hType", hType);
        params.put("shType", shType);
        params.put("hId", hId);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/collectionhouse/deletecollectionhouse")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<NoDataBean>(NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {
                        if (TextUtils.equals(response.body().getCode(), "201")) {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                            MyApplication.logOut();
                        }
                    }

                    @Override
                    public void onError(Response<NoDataBean> response) {
                        super.onError(response);

                    }
                });
    }

    public interface CollectionCallBack {
        void getCollectionHouseList(Response<CollectionListBean> response);

        void collectionNetwork();
    }
}
