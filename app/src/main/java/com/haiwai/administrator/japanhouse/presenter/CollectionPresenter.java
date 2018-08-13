package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.callback.JsonCallback;
import com.haiwai.administrator.japanhouse.model.NoDataBean;
import com.haiwai.administrator.japanhouse.more.CollectionListBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
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
                        callBack.getCollectionHouseList(response);
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
