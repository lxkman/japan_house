package com.example.administrator.japanhouse.fragment.mine.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.activity.RentalDetailsActivity;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.bean.RentalDetailsBean;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.Rent_house_Adapter;
import com.example.administrator.japanhouse.model.SellHouseBean;
import com.example.administrator.japanhouse.presenter.SellHousePresenter;
import com.example.administrator.japanhouse.utils.TUtils;
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

public class Rent_house_Fragment extends BaseFragment implements Rent_house_Adapter.OnItemClickListener, SellHousePresenter.SellHouseCallBack {

    private RecyclerView mrecycler;
    private Rent_house_Adapter rent_house_adapter;
    List<SellHouseBean.DatasBean> mList = new ArrayList<>();

    private SpringView springView;
    private SellHousePresenter presenter;
    private int page = 1;

    private TextView state;
    private boolean isRefresh = true;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sell_house_fragment, container, false);
        mrecycler = (RecyclerView) view.findViewById(R.id.mrecycler);

        state = (TextView) view.findViewById(R.id.no_more_data);

        presenter = new SellHousePresenter(getActivity(), this);
        presenter.getSellHouseList(MyApplication.getUserToken(), 0, page);
        springView = (SpringView) view.findViewById(R.id.frag_sellHouse_springView);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        mList.clear();
                        page = 1;
                        presenter.getSellHouseList(MyApplication.getUserToken(), 0, page);
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = false;
                        page++;
                        presenter.getSellHouseList(MyApplication.getUserToken(), 0, page);
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
        rent_house_adapter = new Rent_house_Adapter(getActivity(), mList);
        mrecycler.setAdapter(rent_house_adapter);
        rent_house_adapter.setOnItemClickListener(this);
    }

    @Override
    public void getSellHouseList(Response<SellHouseBean> response) {
        if (isRefresh) {
            TUtils.showFail(getContext(), getString(R.string.refresh_success));
        }
        state.setText(getString(R.string.no_more_rent_data));
        if (response != null && response.body() != null && response.body().getDatas() != null) {
            if (page == 1) {
                if (response.body().getDatas().size() > 0) {
                    state.setVisibility(View.GONE);
                } else {
                    state.setVisibility(View.VISIBLE);
                }
            }

            if (response.body().getDatas().size() > 0) {
                mList.addAll(response.body().getDatas());
            } else {
                page --;
                if (!isRefresh) {
                    TUtils.showFail(getContext(), getString(R.string.refresh_no_data));
                }
            }
            rent_house_adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void sellHouseNetwork() {
        TUtils.showFail(getContext(), getString(R.string.refresh_fail));
        if (!MyApplication.isNetworkAvailable()) {
            state.setVisibility(View.VISIBLE);
            state.setText(getString(R.string.no_network));
        }
    }

    @Override
    public void onClickListener(int position, RentalDetailsBean bean) {
        RentalDetailsActivity.invoke(getActivity(), bean);
    }

    @Override
    public void onItemDeteleListener(int position, SellHouseBean.DatasBean datasBean) {
        presenter.deteleSellHouse(datasBean.getId());
        mList.remove(position);
        rent_house_adapter.notifyDataSetChanged();
    }
}
