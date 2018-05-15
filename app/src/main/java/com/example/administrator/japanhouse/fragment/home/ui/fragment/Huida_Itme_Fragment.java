package com.example.administrator.japanhouse.fragment.home.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.bean.HuiFu_Bean;
import com.example.administrator.japanhouse.bean.TiwenBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.Huida_Adapter;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.Tiwen_Adapter;
import com.example.administrator.japanhouse.utils.MyUrls;
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
    List<HuiFu_Bean.DatasBean> list=new ArrayList<>();
    private Huida_Adapter tiwen_adapter;
    private int pageNo=1;

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
                        pageNo =1;
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
        tiwen_adapter = new Huida_Adapter(getActivity(),list);
        buy_recyclwe.setAdapter(tiwen_adapter);

    }


    private void intdata() {
        HttpParams params = new HttpParams();
        params.put("token", MyUrls.TOKEN);
        params.put("pageNo",pageNo);
        OkGo.<HuiFu_Bean>post(MyUrls.BASEURL + "/app/askinfo/myreply")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<HuiFu_Bean>(getActivity(),HuiFu_Bean.class){
                    @Override
                    public void onSuccess(Response<HuiFu_Bean> response) {
                        HuiFu_Bean body = response.body();
                        String code = body.getCode();
                        if(code.equals("200")){
                            List<HuiFu_Bean.DatasBean> datas = body.getDatas();
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
