package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.model.AgentListBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * admin  2018/6/22
 */
public class AgentPresenter {
    private Activity activity;
    private AgentCallBack callBack;

    public AgentPresenter(Activity activity, AgentCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * 经纪人列表
     * @param cId
     * @param pageNo
     */
    public void getAgentList(int cId, int pageNo){
        HttpParams params = new HttpParams();
        params.put("cId", cId);
        params.put("pageNo", pageNo);
        OkGo.<AgentListBean>post(MyUrls.BASEURL + "/app/broker/brokerlist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<AgentListBean>(activity, AgentListBean.class) {
                    @Override
                    public void onSuccess(Response<AgentListBean> response) {
                        callBack.getAgentList(response);
                    }

                    @Override
                    public void onError(Response<AgentListBean> response) {
                        super.onError(response);
                        callBack.getAgentListFail();
                    }
                });
    }

    public interface AgentCallBack {
        void getAgentList(Response<AgentListBean> response);
        void getAgentListFail();
    }
}
