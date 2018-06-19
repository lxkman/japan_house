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
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.TUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * 作者：  admin on 2018/6/1 12:02
 * 邮箱：github.com
 */
public class HouseRecordPresenter {
    private Activity activity;
    private HouseRecordCallBack callBack;

    public HouseRecordPresenter(Activity activity, HouseRecordCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * 查询一周内浏览记录
     *
     * @param token
     * @param pageNo
     */
    public void getHouseRecordList(String token, int pageNo) {
        HttpParams params = new HttpParams();
        params.put("token", token);
        params.put("pageNo", pageNo);
        OkGo.<HouseRecordListBean>post(MyUrls.BASEURL + "/app/seehouselog/seehouselogs")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<HouseRecordListBean>(activity, HouseRecordListBean.class) {
                    @Override
                    public void onSuccess(Response<HouseRecordListBean> response) {
                            callBack.getHouseRecordList(response);
                    }

                    @Override
                    public void onError(Response<HouseRecordListBean> response) {
                        super.onError(response);
                        callBack.historyNetwork();
                    }
                });
    }

    /**
     * @param hType
     * @param hId
     * @param shType
     */
    public void deteleHouseRecord(String hType, int hId, String shType) {
        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        params.put("hType", hType);
        params.put("hId", hId);
        params.put("shType", shType);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/seehouselog/deletekflog")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<NoDataBean>(NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {

                    }

                });
    }

    public interface HouseRecordCallBack {
        void getHouseRecordList(Response<HouseRecordListBean> response);

        void historyNetwork();
    }
}
