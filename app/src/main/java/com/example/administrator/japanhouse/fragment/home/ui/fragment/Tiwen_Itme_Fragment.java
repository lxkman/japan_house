package com.example.administrator.japanhouse.fragment.home.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.bean.Bay_baike_Bean;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.bean.HuiFu_Bean;
import com.example.administrator.japanhouse.bean.TiwenBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.fragment.home.ui.activity.Buyhouse_Baike_Activity;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.Tiwen_Adapter;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.TUtils;
import com.example.administrator.japanhouse.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class Tiwen_Itme_Fragment extends BaseFragment {

    private RecyclerView buy_recyclwe;
    private int pageNo = 1;
    List<TiwenBean.DatasBean> list = new ArrayList<>();
    private SpringView sp_view;
    private Tiwen_Adapter tiwen_adapter;

    private TextView state;
    private boolean isRefresh = true;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_buyhouse, container, false);
        sp_view = (SpringView) view.findViewById(R.id.sp_view);
        state = (TextView) view.findViewById(R.id.no_more_data);
        buy_recyclwe = (RecyclerView) view.findViewById(R.id.Buy_recycler);
        intdata();

        EventBus.getDefault().register(this);
        return view;
    }


    @Override
    protected void initLazyData() {

    }

    @Override
    protected void lazyLoad() {

        sp_view.setType(SpringView.Type.FOLLOW);
        sp_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        list.clear();
                        pageNo = 1;
                        intdata();
                        tiwen_adapter.notifyDataSetChanged();
                    }
                }, 0);
                sp_view.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = false;
                        pageNo++;
                        intdata();
                        tiwen_adapter.notifyDataSetChanged();
                    }
                }, 0);
                sp_view.onFinishFreshAndLoad();
            }
        });
        sp_view.setFooter(new DefaultFooter(getActivity()));
        sp_view.setHeader(new DefaultHeader(getActivity()));

        buy_recyclwe.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        tiwen_adapter = new Tiwen_Adapter(getActivity(), list);
        buy_recyclwe.setAdapter(tiwen_adapter);
    }

    private void intdata() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("ad", "");
        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        params.put("pageNo", pageNo);
        OkGo.<TiwenBean>post(MyUrls.BASEURL + "/app/askinfo/myquestion")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<TiwenBean>(getActivity(), TiwenBean.class) {
                    @Override
                    public void onSuccess(Response<TiwenBean> response) {
                        if (isRefresh) {
                            TUtils.showFail(getContext(), getString(R.string.refresh_success));
                        }
                        state.setText(getString(R.string.no_more_ask_data));
                        if (response != null && response.body() != null && response.body().getDatas() != null) {
                            if (pageNo == 1) {
                                if (response.body().getDatas().size() > 0) {
                                    state.setVisibility(View.GONE);
                                } else {
                                    state.setVisibility(View.VISIBLE);
                                }
                            }

                            if (response.body().getDatas().size() > 0) {
                                list.addAll(response.body().getDatas());
                            } else {
                                pageNo--;
                                if (!isRefresh) {
                                    TUtils.showFail(getContext(), getString(R.string.refresh_no_data));
                                }
                            }

                            tiwen_adapter.notifyDataSetChanged();
                        }


                    }

                    @Override
                    public void onError(Response<TiwenBean> response) {
                        super.onError(response);
                        if (!MyApplication.isNetworkAvailable()) {
                            state.setVisibility(View.VISIBLE);
                            state.setText(getString(R.string.no_network));
                        }
                        TUtils.showFail(getContext(), getString(R.string.refresh_fail));
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventUserInfos(EventBean eventBean) {
        if (TextUtils.equals(eventBean.getMsg(), Constants.EVENT_W)) {
            intdata();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
