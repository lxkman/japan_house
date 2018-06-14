package com.example.administrator.japanhouse.fragment.home.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.bean.HuiFu_Bean;
import com.example.administrator.japanhouse.bean.TiwenBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.Huida_Adapter;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.Tiwen_Adapter;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.TUtils;
import com.example.administrator.japanhouse.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class Huida_Itme_Fragment extends BaseFragment {

    private RecyclerView buy_recyclwe;
    private SpringView sp_view;
    List<HuiFu_Bean.DatasBean> list = new ArrayList<>();
    private Huida_Adapter tiwen_adapter;
    private int pageNo = 1;

    private TextView state;
    private boolean isRefresh = true;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_buyhouse, container, false);
        state = (TextView) view.findViewById(R.id.no_more_data);
        sp_view = (SpringView) view.findViewById(R.id.sp_view);
        buy_recyclwe = (RecyclerView) view.findViewById(R.id.Buy_recycler);
        intdata();
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
        tiwen_adapter = new Huida_Adapter(getActivity(), list);
        buy_recyclwe.setAdapter(tiwen_adapter);

    }

    private void intdata() {
        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        params.put("pageNo", pageNo);
        OkGo.<HuiFu_Bean>post(MyUrls.BASEURL + "/app/askinfo/myreply")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<HuiFu_Bean>(getActivity(), HuiFu_Bean.class) {
                    @Override
                    public void onSuccess(Response<HuiFu_Bean> response) {
                        if (isRefresh) {
                            TUtils.showFail(getContext(), getString(R.string.refresh_success));
                        }
                        state.setText(getString(R.string.no_more_answer_data));
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
                    public void onError(Response<HuiFu_Bean> response) {
                        super.onError(response);
                        if (!MyApplication.isNetworkAvailable()) {
                            state.setVisibility(View.VISIBLE);
                            state.setText(getString(R.string.no_network));
                        }
                        TUtils.showFail(getContext(), getString(R.string.refresh_fail));
                    }
                });

    }
}
