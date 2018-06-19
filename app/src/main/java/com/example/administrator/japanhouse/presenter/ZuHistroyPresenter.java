package com.example.administrator.japanhouse.presenter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.bean.ZuHistroyBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.login.LoginActivity;
import com.example.administrator.japanhouse.model.NoDataBean;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * Created by Administrator on 2018/6/14.
 */

public class ZuHistroyPresenter {

    private Activity activity;
    private ZuHistroyCallBack callBack;

    public ZuHistroyPresenter(Activity activity, ZuHistroyCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /*获取租房的分类下面的历史记录*/
    public void getZuHistroyList(int pageNo, int shType) {
        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("shType", shType);
        params.put("token", MyApplication.getUserToken());
        OkGo.<ZuHistroyBean>post(MyUrls.BASEURL + "/app/seehouselog/seezfhouselogs")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<ZuHistroyBean>(activity, ZuHistroyBean.class) {
                    @Override
                    public void onSuccess(Response<ZuHistroyBean> response) {
                        if (TextUtils.equals(response.body().getCode(), "201")) {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                            MyApplication.logOut();
                        } else {
                            callBack.getZuHistroyList(response);
                        }
                    }
                });
    }

    /*删除历史记录*/
    public void deteleHouseRecord(int hId, int shType) {
        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        params.put("hType", 2);
        params.put("hId", hId);
        params.put("shType", shType);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/seehouselog/deletekflog")
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
                });
    }

    public interface ZuHistroyCallBack {
        void getZuHistroyList(Response<ZuHistroyBean> response);
    }
}
