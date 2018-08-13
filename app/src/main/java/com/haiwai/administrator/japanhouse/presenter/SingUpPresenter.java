package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.callback.JsonCallback;
import com.haiwai.administrator.japanhouse.model.NoDataBean;
import com.haiwai.administrator.japanhouse.model.SingUpBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * 作者：  admin on 2018/6/6 09:16
 * 邮箱：github.com
 */
public class SingUpPresenter {
    private Activity activity;
    private SingUpCallBack callBack;

    public SingUpPresenter(Activity activity, SingUpCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * 查看我的报名
     *
     * @param token
     * @param pageNo
     */
    public void getSingUpList(String token, int pageNo) {
        HttpParams params = new HttpParams();
        params.put("token", token);
        params.put("pageNo", pageNo);
        OkGo.<SingUpBean>post(MyUrls.BASEURL + "/app/seehouse/myseehouse")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SingUpBean>(activity, SingUpBean.class) {
                    @Override
                    public void onSuccess(Response<SingUpBean> response) {
                        callBack.getSingUpList(response);
                    }

                    @Override
                    public void onError(Response<SingUpBean> response) {
                        super.onError(response);
                        callBack.singUpNetwork();
                    }
                });
    }

    /**
     * 删除报名
     *
     * @param sId
     * @param token
     */
    public void deteleSingUp(String sId, String token) {
        HttpParams params = new HttpParams();
        params.put("sId", sId);
        params.put("token", token);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/applyuser/deleteapplyuser")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<NoDataBean>(NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {
                        callBack.deteleSingUp(response);
                    }
                });
    }

    public interface SingUpCallBack {
        void getSingUpList(Response<SingUpBean> response);

        void deteleSingUp(Response<NoDataBean> response);

        void singUpNetwork();
    }
}
