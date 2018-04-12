package com.example.administrator.japanhouse.fragment.home.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.Buyhouse_Adapter;


/**
 * Created by Mr赵 on 2018/4/11.
 */

public class Buyhouse_Fragment extends BaseFragment{

    private RecyclerView buy_recyclwe;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_buyhouse, container, false);
        buy_recyclwe = (RecyclerView) view.findViewById(R.id.Buy_recycler);
        return view;
    }

    @Override
    protected void initLazyData() {

    }


    @Override
    protected void lazyLoad() {
        //加载适配器
        buy_recyclwe.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        Buyhouse_Adapter buyhouse_adapter = new Buyhouse_Adapter(getActivity());
         buy_recyclwe.setAdapter(buyhouse_adapter);


    }
}
