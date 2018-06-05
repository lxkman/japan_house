package com.example.administrator.japanhouse.fragment.mine.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.activity.RentalDetailsActivity;
import com.example.administrator.japanhouse.adapter.OwnerWikipediaAdapter;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.bean.RentalDetailsBean;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.MaiFang_house_Adapter;
import com.example.administrator.japanhouse.model.SellHouseBean;
import com.example.administrator.japanhouse.presenter.SellHousePresenter;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.model.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/16.
 */

public class Sell_house_Fragment extends BaseFragment implements MaiFang_house_Adapter.OnItemClickListener, SellHousePresenter.SellHouseCallBack{

    private RecyclerView mrecycler;
    private MaiFang_house_Adapter sell_house_adapter;
    List<SellHouseBean.DatasBean> mList = new ArrayList<>();
    private SpringView springView;
    private SellHousePresenter presenter;
    private int page = 1;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sell_house_fragment, container, false);
        mrecycler = (RecyclerView) view.findViewById(R.id.mrecycler);
        presenter = new SellHousePresenter(getActivity(), this);
        presenter.getSellHouseList(MyApplication.getUserToken(), 1, page);
        springView = (SpringView) view.findViewById(R.id.frag_sellHouse_springView);

        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mList.clear();
                        page = 1;
                        presenter.getSellHouseList(MyApplication.getUserToken(), 1, page);
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        presenter.getSellHouseList(MyApplication.getUserToken(), 1, page);
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }
        });
        springView.setFooter(new DefaultFooter(getActivity()));
        springView.setHeader(new DefaultHeader(getActivity()));

        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void lazyLoad() {

        mrecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        sell_house_adapter = new MaiFang_house_Adapter(getActivity(), mList);

        mrecycler.setAdapter(sell_house_adapter);
        sell_house_adapter.setOnItemClickListener(this);

    }

    @Override
    public void getSellHouseList(Response<SellHouseBean> response) {
        if (response != null && response.body() != null && response.body().getDatas() != null) {
            if (response.body().getDatas().size() > 0) {
                mList.addAll(response.body().getDatas());
            } else {
                page --;
            }
            sell_house_adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClickListener(int position, RentalDetailsBean bean) {
        RentalDetailsActivity.invoke(getActivity(), bean);
    }
}
