package com.example.administrator.japanhouse.fragment.home.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.bean.Bay_baike_Bean;
import com.example.administrator.japanhouse.bean.TiwenBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.fragment.home.ui.activity.Buyhouse_Baike_Activity;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.Tiwen_Adapter;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class Tiwen_Itme_Fragment extends BaseFragment {

    private RecyclerView buy_recyclwe;
    private int pageNo=1;
    List<TiwenBean.DatasBean>list=new ArrayList<>();
    private SpringView sp_view;
    private Tiwen_Adapter tiwen_adapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_buyhouse, container, false);
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
                        list.clear();
                        pageNo=1;
                        intdata();
                        tiwen_adapter.notifyDataSetChanged();
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNo++;
                        intdata();
                        tiwen_adapter.notifyDataSetChanged();
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }
        });
        sp_view.setFooter(new DefaultFooter(getActivity()));
        sp_view.setHeader(new DefaultHeader(getActivity()));


        buy_recyclwe.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        tiwen_adapter = new Tiwen_Adapter(getActivity(),list);
        buy_recyclwe.setAdapter(tiwen_adapter);
    }
    private void intdata() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("ad","");
        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        params.put("pageNo",pageNo);
        OkGo.<TiwenBean>post(MyUrls.BASEURL + "/app/askinfo/myquestion")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<TiwenBean>(getActivity(),TiwenBean.class){
                    @Override
                    public void onSuccess(Response<TiwenBean> response) {
                        TiwenBean body = response.body();
                        String code = body.getCode();
                        if(code.equals("200")){
                            List<TiwenBean.DatasBean> datas = body.getDatas();
                            if(pageNo >1){
                                if(datas.size()>0){
                                    list.addAll(datas);
                                }
                            }else{
                                list.addAll(datas);
                            }
                            tiwen_adapter.notifyDataSetChanged();
                        }else if(code.equals("201")){
                            ToastUtils.getToast(getActivity(),body.getMsg());
                        }else if(code.equals("500")){
                            ToastUtils.getToast(getActivity(),body.getMsg());
                        }else if(code.equals("404")){
                            ToastUtils.getToast(getActivity(),body.getMsg());
                        }else if(code.equals("203")){
                            ToastUtils.getToast(getActivity(),body.getMsg());
                        }else if(code.equals("204")){
                            ToastUtils.getToast(getActivity(),body.getMsg());
                        }
                    }
                });
    }
}
