package com.example.administrator.japanhouse.fragment.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.fragment.comment.NewHousedetailsActivity;
import com.example.administrator.japanhouse.fragment.home.FangjiadituActivity;
import com.example.administrator.japanhouse.fragment.home.ui.activity.WendaItemActivity;
import com.example.administrator.japanhouse.im.FeedBackExtensionModule;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.example.administrator.japanhouse.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;

import static com.example.administrator.japanhouse.R.id.tv_qustion;
import static com.example.administrator.japanhouse.R.id.tv_wenjuan;

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
    TextView tvQustion;
    @BindView(R.id.rel_lianxiren_layout)
    RelativeLayout relLianxirenLayout;
    @BindView(R.id.rel_lishi_layout)
    LinearLayout relLishiLayout;
    @BindView(R.id.nestScroll)
    NestedScrollView nestScroll;
    @BindView(R.id.ll_zh)
    LinearLayout llZh;
    @BindView(R.id.tv_wenjuan)
    TextView tvWenjuan;
    @BindView(R.id.ll_ja)
    LinearLayout llJa;
    @BindView(R.id.re_top_bg)
    RelativeLayout reTopBg;
    @BindView(R.id.tv_calculator1)
    TextView tvCalculator1;
    @BindView(R.id.tv_myhouse_price1)
    TextView tvMyhousePrice1;
    @BindView(R.id.tv_myask1)
    TextView tvMyask1;
    @BindView(R.id.tv_feedback1)
    TextView tvFeedback1;
    @BindView(R.id.fl_lxk)
    FrameLayout fl_lxk;
    @BindView(R.id.fl_lxk2)
    FrameLayout fl_lxk2;
    Unbinder unbinder;
    @BindView(R.id.ll_collect)
    LinearLayout llCollect;
    @BindView(R.id.ll_dingyue)
    LinearLayout llDingyue;
    @BindView(R.id.tv_collect_count2)
    TextView tvCollectCount2;
    @BindView(R.id.ll_collect2)
    LinearLayout llCollect2;
    @BindView(R.id.tv_subscription_count2)
    TextView tvSubscriptionCount2;
    @BindView(R.id.ll_dingyue2)
    LinearLayout llDingyue2;
    private int mDistanceY;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, rootView);
        initScroll();
        return rootView;
    }

    private void initScroll() {
        nestScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //滑动的距离
                mDistanceY += scrollY - oldScrollY;
                //toolbar的高度
                int toolbarHeight = 300;//我写死的高度

                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= toolbarHeight) {
                    float scale = (float) mDistanceY / toolbarHeight;
                    float alpha = scale * 255;
                    reTopBg.setBackgroundColor(Color.argb((int) alpha, 199, 151, 127));
                } else {
                    //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
                    //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
                    //将标题栏的颜色设置为完全不透明状态
                    reTopBg.setBackgroundResource(R.color.shihuangse);
                }
            }
        });
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String city = CacheUtils.get(Constants.COUNTRY);
        if (city != null && city.equals("ja")) {
            llZh.setVisibility(View.GONE);
            llJa.setVisibility(View.VISIBLE);
            tvQustion.setVisibility(View.GONE);
            tvWenjuan.setVisibility(View.VISIBLE);
            fl_lxk2.setVisibility(View.VISIBLE);
            fl_lxk.setVisibility(View.GONE);
        } else {
            llZh.setVisibility(View.VISIBLE);
            llJa.setVisibility(View.GONE);
            tvQustion.setVisibility(View.VISIBLE);
            tvWenjuan.setVisibility(View.GONE);
        }
        recyclerFoot.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("");
        }
        FootAdapter footAdapter = new FootAdapter(R.layout.item_myfoot, list);
        recyclerFoot.setAdapter(footAdapter);
        footAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, NewHousedetailsActivity.class));
            }
        });
    }

    @OnClick({R.id.iv_msg, R.id.iv_setting, R.id.iv_head, R.id.rel_lianxiren_layout,
            R.id.rel_lishi_layout, tv_qustion, R.id.ll_collect, R.id.ll_dingyue, R.id.ll_collect2, R.id.ll_dingyue2, R.id.tv_myorder,
            R.id.tv_myask, R.id.tv_mysignup, R.id.tv_sellinghouse, R.id.tv_myhouse_price, R.id.tv_calculator, R.id.tv_feedback,
            R.id.tv_myhouse_price1, R.id.tv_calculator1, R.id.tv_feedback1, R.id.tv_myask1, R.id.tv_wenjuan})
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
            case tv_qustion:
            case tv_wenjuan:
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
            case R.id.ll_collect:
            case R.id.ll_collect2:
                startActivity(new Intent(mContext, ShouCangActivity.class));
                break;
            //订阅
            case R.id.ll_dingyue:
            case R.id.ll_dingyue2:
                startActivity(new Intent(mContext, DingYueActivity.class));
                break;
            //订单
            case R.id.tv_myorder:
                startActivity(new Intent(mContext, DingDanActivity.class));
                break;
            //我的问答
            case R.id.tv_myask:
            case R.id.tv_myask1:
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
            case R.id.tv_myhouse_price1:
                startActivity(new Intent(mContext, FangjiadituActivity.class));
                break;
            case R.id.tv_calculator:

            case R.id.tv_calculator1:
                startActivity(new Intent(mContext, CalculatorActivity.class));
                break;
            case R.id.tv_feedback:
            case R.id.tv_feedback1:
                //                startActivity(new Intent(mContext, FeedbackActivity.class));
                SharedPreferencesUtils.getInstace(getActivity()).setStringPreference(Constants.CHAT, Constants.CHAT_FEEDBACK);
                setMyExtensionModule();
                if (RongIM.getInstance() != null) {
                    Log.e("MainActivity", "创建单聊");
                    RongIM.getInstance().startPrivateChat(getActivity(), "123456", getString(R.string.mine_userfeedback));
                }
                break;
        }
    }

    public void setMyExtensionModule() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new FeedBackExtensionModule());
            }
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
