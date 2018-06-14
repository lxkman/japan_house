package com.example.administrator.japanhouse.presenter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.login.LoginActivity;
import com.example.administrator.japanhouse.model.HouseRecordListBean;
import com.example.administrator.japanhouse.model.OrderBean;
import com.example.administrator.japanhouse.model.SellHouseBean;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.TUtils;
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
                        if (TextUtils.equals(response.body().getCode(), "201")) {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                            MyApplication.logOut();
                        } else {
                            callBack.getOrderList(response);
                        }
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
