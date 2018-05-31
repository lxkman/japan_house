package com.example.administrator.japanhouse.presenter;

import android.app.Activity;

import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.more.CollectionListBean;
import com.example.administrator.japanhouse.utils.MyUrls;
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
     * @param pageNo
     * @param token
     */
    public void getCollectionHouseList(int pageNo, String token){
        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("token", token);
        OkGo.<CollectionListBean>post(MyUrls.BASEURL + "/app/collectionhouse/collectionlist")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<CollectionListBean>(CollectionListBean.class) {
                    @Override
                    public void onSuccess(Response<CollectionListBean> response) {
                        callBack.getCollectionHouseList(response);
                    }
                });
    }

    public interface CollectionCallBack {
        void getCollectionHouseList(Response<CollectionListBean> response);
    }
}
