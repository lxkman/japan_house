package com.haiwai.administrator.japanhouse.presenter;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.callback.JsonCallback;
import com.haiwai.administrator.japanhouse.model.NoDataBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * admin  2018/6/8
 */
public class TopLinePresenter {

    /**
     *  给评论点赞
     * @param plId
     */
    public void thumbUpComment(int plId){
        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        params.put("plId", plId);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/topline/insertisgood")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<NoDataBean>(NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {

                    }
                });
    }

    /**
     * 取消评论的赞
     * @param plId
     */
    public void thumbDownComment(int plId){
        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        params.put("plId", plId);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/topline/deleteisgood")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<NoDataBean>(NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {

                    }
                });
    }
}
