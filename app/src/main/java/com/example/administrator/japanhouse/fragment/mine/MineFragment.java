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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.fragment.home.ui.activity.WendaItemActivity;
import com.example.administrator.japanhouse.utils.ToastUtils;
import com.example.administrator.japanhouse.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.tv_histroy_count)
    TextView tvHistroyCount;
    @BindView(R.id.tv_myhouse_price)
    TextView tvMyhousePrice;
    @BindView(R.id.tv_myorder)
    TextView tvMyorder;
    @BindView(R.id.tv_sellinghouse)
    TextView tvSellinghouse;
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
    @BindView(R.id.rel_lianxiren_layout)
    RelativeLayout relLianxirenLayout;
    @BindView(R.id.rel_lishi_layout)
    LinearLayout relLishiLayout;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerFoot.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("");
        }
        FootAdapter footAdapter = new FootAdapter(R.layout.item_myfoot, list);
        recyclerFoot.setAdapter(footAdapter);
    }

    @OnClick({R.id.iv_msg, R.id.iv_setting, R.id.iv_head, R.id.rel_lianxiren_layout,
            R.id.rel_lishi_layout, R.id.tv_qustion,R.id.tv_collect_count,R.id.tv_subscription_count,R.id.tv_myorder,
            R.id.tv_myask,R.id.tv_mysignup,R.id.tv_sellinghouse,R.id.tv_myhouse_price,R.id.tv_calculator,R.id.tv_feedback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                startActivity(new Intent(mContext, MyDataActivity.class));
                break;
            case R.id.iv_setting:
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
            case R.id.iv_msg:
                startActivity(new Intent(mContext, MineMsgActivity.class));
                break;
            case R.id.tv_qustion:
                startActivity(new Intent(mContext, WenJuanActivity.class));
                break;
            //联系人
            case R.id.rel_lianxiren_layout:
                startActivity(new Intent(mContext, LianxirenActivity.class));
                break;
            //历史记录
            case R.id.rel_lishi_layout:
                startActivity(new Intent(mContext, LiShiJiLuActivity.class));
                break;
            //收藏
            case R.id.tv_collect_count:
                startActivity(new Intent(mContext, ShouCangActivity.class));
                break;
            //订阅
            case R.id.tv_subscription_count:
                startActivity(new Intent(mContext, DingYueActivity.class));
                break;
            //订单
            case R.id.tv_myorder:
                startActivity(new Intent(mContext, DingDanActivity.class));
                break;
            //我的问答
            case R.id.tv_myask:
                startActivity(new Intent(mContext, WendaItemActivity.class));
                break;
            case R.id.tv_mysignup:
                startActivity(new Intent(mContext, SingUpActivity.class));
                break;
                //买房管理
            case R.id.tv_sellinghouse:
                startActivity(new Intent(mContext, SellHouseActivity.class));
                break;
                //我的房价
            case R.id.tv_myhouse_price:
                ToastUtils.getToast(getActivity(),"地图");
                break;
                case R.id.tv_calculator:
                startActivity(new Intent(mContext,CalculatorActivity.class));
                break;
            case R.id.tv_feedback:
                startActivity(new Intent(mContext,FeedbackActivity.class));
                break;
        }
    }


    private class FootAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

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
