package com.example.administrator.japanhouse.presenter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.login.LoginActivity;
import com.example.administrator.japanhouse.model.HouseRecordListBean;
import com.example.administrator.japanhouse.model.NoDataBean;
import com.example.administrator.japanhouse.model.SellHouseBean;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.TUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * 作者：  admin on 2018/6/5 15:13
 * 邮箱：github.com
 */
public class SellHousePresenter {
    private Activity activity;
    private SellHouseCallBack callBack;

    public SellHousePresenter(Activity activity, SellHouseCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * 卖房管理
     *
     * @param token
     * @param eType
     * @param pageNo
     */
    public void getSellHouseList(String token, int eType, int pageNo) {
        HttpParams params = new HttpParams();
        params.put("token", token);
        params.put("eType", eType);
        params.put("pageNo", pageNo);
        OkGo.<SellHouseBean>post(MyUrls.BASEURL + "/app/landlordrental/myhouse")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SellHouseBean>(activity, SellHouseBean.class) {
                    @Override
                    public void onSuccess(Response<SellHouseBean> response) {
                        callBack.getSellHouseList(response);
                    }

                    @Override
                    public void onError(Response<SellHouseBean> response) {
                        super.onError(response);
                        callBack.sellHouseNetwork();
                    }
                });
    }

    /**
     * 删除租售
     *
     * @param hId
     */
    public void deteleSellHouse(int hId) {
        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        params.put("hId", hId);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/landlordrental/deletemyhouselog")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<NoDataBean>(NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {
                    }
                });
    }

    public interface SellHouseCallBack {
        void getSellHouseList(Response<SellHouseBean> response);

        void sellHouseNetwork();
    }
}
