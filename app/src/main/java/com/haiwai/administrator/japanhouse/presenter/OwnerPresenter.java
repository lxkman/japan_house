package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.model.OwnerDetailsBean;
import com.haiwai.administrator.japanhouse.model.OwnerListBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * Created by   admin on 2018/5/29.
 */

public class OwnerPresenter {
    private Activity activity;
    private OwnerCallBack callBack;

    public OwnerPresenter(Activity activity, OwnerCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * 业主百科列表
     * @param pageNo
     */
    public void getOwnerList(int pageNo){
        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        OkGo.<OwnerListBean>post(MyUrls.BASEURL + "/app/textsource/ownerencyclopedia")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<OwnerListBean>(activity, OwnerListBean.class) {
                    @Override
                    public void onSuccess(Response<OwnerListBean> response) {
                        callBack.getOwnerList(response);
                    }

                    @Override
                    public void onError(Response<OwnerListBean> response) {
                        super.onError(response);
                        callBack.ownerListNetwork();
                    }
                });
    }

    /**
     * 业主百科详情
     * @param wId
     */
    public void getOwnerDetails(int wId){
        HttpParams params = new HttpParams();
        params.put("wId", wId);
        OkGo.<OwnerDetailsBean>post(MyUrls.BASEURL + "/app/textsource/purchasewikipediaInfo")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<OwnerDetailsBean>(activity, OwnerDetailsBean.class) {
                    @Override
                    public void onSuccess(Response<OwnerDetailsBean> response) {
                        callBack.getOwnerDetails(response);
                    }
                });
    }

    public interface OwnerCallBack{
        void getOwnerList(Response<OwnerListBean> response);
        void getOwnerDetails(Response<OwnerDetailsBean> response);
        void ownerListNetwork();
    }
}
