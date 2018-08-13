package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.callback.JsonCallback;
import com.haiwai.administrator.japanhouse.model.LinkmanBean;
import com.haiwai.administrator.japanhouse.model.NoDataBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * admin  2018/6/13
 */
public class LinkmanPresenter {
    private Activity activity;

    private LinkmanCallBack callBack;

    public LinkmanPresenter(Activity activity, LinkmanCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * 获取联系人列表
     */
    public void getLinkmanList() {
        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        OkGo.<LinkmanBean>post(MyUrls.BASEURL + "/app/contact/mycontact")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<LinkmanBean>(activity, LinkmanBean.class) {
                    @Override
                    public void onSuccess(Response<LinkmanBean> response) {
                        callBack.getLinkmanList(response);
                    }
                });
    }

    /**
     * 添加联系人
     *
     * @param brokerId
     */
    public void addLinkman(String brokerId) {
        HttpParams params = new HttpParams();
        params.put("userId", MyApplication.getUserId(activity));
        params.put("brokerId", brokerId);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/contact/insertcontact")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<NoDataBean>(NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {

                    }
                });
    }

    /**
     * 删除联系人
     *
     * @param brokerId
     */
    public void deteleLinkman(String brokerId) {
        HttpParams params = new HttpParams();
        params.put("userId", MyApplication.getUserId(activity));
        params.put("brokerId", brokerId);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/contact/deletecontact")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<NoDataBean>(NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {

                    }
                });
    }

    public interface LinkmanCallBack {
        void getLinkmanList(Response<LinkmanBean> response);

        void getLinkmanListFail();
    }

}
