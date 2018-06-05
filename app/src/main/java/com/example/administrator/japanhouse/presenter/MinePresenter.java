package com.example.administrator.japanhouse.presenter;

import android.app.Activity;

import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.model.HouseRecordListBean;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * 作者：  admin on 2018/6/4 09:39
 * 邮箱：github.com
 */
public class MinePresenter {
    private Activity activity;
    private MineCallBack callBack;

    public MinePresenter(Activity activity, MineCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * 查询一周内浏览记录
     *
     * @param token
     * @param pageNo
     */
    public void getHouseRecordList(String token, int pageNo){
        HttpParams params = new HttpParams();
        params.put("token", token);
        params.put("pageNo", pageNo);
        OkGo.<HouseRecordListBean>post(MyUrls.BASEURL + "/app/seehouselog/seehouselogs")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<HouseRecordListBean>(HouseRecordListBean.class) {
                    @Override
                    public void onSuccess(Response<HouseRecordListBean> response) {
                        callBack.getHouseRecordList(response);
                    }
                });
    }

    public interface MineCallBack {
        void getHouseRecordList(Response<HouseRecordListBean> response);
    }
}
