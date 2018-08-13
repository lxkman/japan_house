package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.callback.JsonCallback;
import com.haiwai.administrator.japanhouse.model.OrderBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * 作者：  admin on 2018/6/5 17:32
 * 邮箱：github.com
 */
public class OrderPresenter {
    private Activity activity;
    private OrderCallBack callBack;

    public OrderPresenter(Activity activity, OrderCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * 我的订单
     *
     * @param token
     * @param pageNo
     */
    public void getOrderList(String token, int pageNo) {
        HttpParams params = new HttpParams();
        params.put("token", token);
        params.put("pageNo", pageNo);
        OkGo.<OrderBean>post(MyUrls.BASEURL + "/app/indent/orderlist")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<OrderBean>(OrderBean.class) {
                    @Override
                    public void onSuccess(Response<OrderBean> response) {
                        callBack.getOrderList(response);
                    }

                    @Override
                    public void onError(Response<OrderBean> response) {
                        super.onError(response);
                        callBack.orderNetwork();
                    }
                });
    }

    public interface OrderCallBack {
        void getOrderList(Response<OrderBean> response);

        void orderNetwork();
    }
}
