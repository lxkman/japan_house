package com.example.administrator.japanhouse.fragment.home.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.Huida_Adapter;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.Tiwen_Adapter;

/**
 * Created by Mr赵 on 2018/4/11.
 */

public class Huida_Itme_Fragment extends BaseFragment {

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
        buy_recyclwe.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        Huida_Adapter tiwen_adapter = new Huida_Adapter(getActivity());
        buy_recyclwe.setAdapter(tiwen_adapter);

    }
}
