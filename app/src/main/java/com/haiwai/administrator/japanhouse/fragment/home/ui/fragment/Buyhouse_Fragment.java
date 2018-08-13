package com.haiwai.administrator.japanhouse.fragment.home.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseFragment;
import com.haiwai.administrator.japanhouse.bean.EventBean;
import com.haiwai.administrator.japanhouse.bean.QueandansBean;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.fragment.home.ui.adapter.Buyhouse_Adapter;
import com.haiwai.administrator.japanhouse.utils.Constants;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.haiwai.administrator.japanhouse.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mr赵 on 2018/4/11.
 */
public class Buyhouse_Fragment extends BaseFragment{

    private RecyclerView buy_recyclwe;
    private SpringView sp_view;
    private Buyhouse_Adapter buyhouse_adapter;
    private int pageNo=1;
    private String searchText;
    List<QueandansBean.DatasBean>list=new ArrayList<>();

    private int type;

    public Buyhouse_Fragment() {
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_buyhouse, container, false);
        buy_recyclwe = (RecyclerView) view.findViewById(R.id.Buy_recycler);
        sp_view = (SpringView) view.findViewById(R.id.sp_view);
        searchText=getArguments().getString("searchText");
        type = getArguments().getInt("type");
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
                        list.clear();
                        pageNo=1;
                        intdata();
                        buyhouse_adapter.notifyDataSetChanged();
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
                        buyhouse_adapter.notifyDataSetChanged();
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }
        });
        sp_view.setFooter(new DefaultFooter(getActivity()));
        sp_view.setHeader(new DefaultHeader(getActivity()));

        //加载适配器
        buy_recyclwe.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        buyhouse_adapter = new Buyhouse_Adapter(getActivity(),list);
        buyhouse_adapter.setType(type);
         buy_recyclwe.setAdapter(buyhouse_adapter);

    }

    private void intdata() {
        HttpParams params = new HttpParams();
        params.put("pageNo",pageNo);
        params.put("searchText",searchText);
        params.put("qType",1);
        OkGo.<QueandansBean>post(MyUrls.BASEURL + "/app/askinfo/searchask")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<QueandansBean>(getActivity(),QueandansBean.class){
                    @Override
                    public void onSuccess(Response<QueandansBean> response) {
                        QueandansBean body = response.body();
                        String code = body.getCode();
                        if(code.equals("200")){
                            List<QueandansBean.DatasBean> datas = body.getDatas();
                            if(pageNo>1){
                                if(datas.size()>0){
                                    list.addAll(datas);
                                }
                            }else{
                                list.addAll(datas);
                            }
                            buyhouse_adapter.notifyDataSetChanged();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventUserInfos(EventBean eventBean) {
        if (TextUtils.equals(eventBean.getMsg(), Constants.EVENT_QUEST_W)) {
            list.clear();
            pageNo = 1;
            intdata();
        }
    }
}
