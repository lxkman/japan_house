package com.example.administrator.japanhouse.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/8.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_platform_time)
    TextView tvPlatformTime;
    @BindView(R.id.tv_collect_count)
    TextView tvCollectCount;
    @BindView(R.id.tv_subscription_count)
    TextView tvSubscriptionCount;
    @BindView(R.id.tv_contacts_count)
    TextView tvContactsCount;
    @BindView(R.id.tv_lookhouse_count)
    TextView tvLookhouseCount;
    @BindView(R.id.tv_histroy_count)
    TextView tvHistroyCount;
    @BindView(R.id.tv_myhouse_price)
    TextView tvMyhousePrice;
    @BindView(R.id.tv_myorder)
    TextView tvMyorder;
    @BindView(R.id.tv_sellinghouse)
    TextView tvSellinghouse;
    @BindView(R.id.tv_report)
    TextView tvReport;
    @BindView(R.id.tv_calculator)
    TextView tvCalculator;
    @BindView(R.id.tv_myask)
    TextView tvMyask;
    @BindView(R.id.tv_mysignup)
    TextView tvMysignup;
    @BindView(R.id.tv_feedback)
    TextView tvFeedback;
    @BindView(R.id.recycler_foot)
    RecyclerView recyclerFoot;
    @BindView(R.id.tv_qustion)
    ImageView tvQustion;
    Unbinder unbinder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, rootView);
        ivHead.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
        ivMsg.setOnClickListener(this);
        tvQustion.setOnClickListener(this);
        return rootView;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerFoot.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        List<String> list=new ArrayList<>();
        for (int i=0;i<3;i++){
            list.add("");
        }
        FootAdapter footAdapter=new FootAdapter(R.layout.item_myfoot,list);
        recyclerFoot.setAdapter(footAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_head:
                startActivity(new Intent(mContext,MyDataActivity.class));
                break;
            case R.id.iv_setting:
                startActivity(new Intent(mContext,SettingActivity.class));
                break;
            case R.id.iv_msg:
                startActivity(new Intent(mContext,MineMsgActivity.class));
                break;
            case R.id.tv_qustion:
                startActivity(new Intent(mContext,WenJuanActivity.class));
                break;
        }
    }

    private class FootAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

        public FootAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
