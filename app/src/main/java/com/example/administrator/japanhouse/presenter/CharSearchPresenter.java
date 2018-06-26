package com.example.administrator.japanhouse.presenter;

import android.app.Activity;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.model.ChatSearchBean;
import com.example.administrator.japanhouse.more.CollectionListBean;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * admin  2018/6/25
 */
public class CharSearchPresenter {
    private Activity activity;
    private ChatSearchCallBack callBack;

    public CharSearchPresenter(Activity activity, ChatSearchCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    public void getSearchList(int pageNo, String searchText){
        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        params.put("pageNo", pageNo);
        params.put("searchText", searchText);
        OkGo.<ChatSearchBean>post(MyUrls.BASEURL + "/app/broker/searchbroker")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<ChatSearchBean>( ChatSearchBean.class) {
                    @Override
                    public void onSuccess(Response<ChatSearchBean> response) {
                        callBack.getSearchList(response);
                    }
                });
    }

    public interface ChatSearchCallBack {
        void getSearchList(Response<ChatSearchBean> response);
    }
}
