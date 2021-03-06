package com.haiwai.administrator.japanhouse.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.activity.adapter.HouseHistoryAdapter;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.bean.EventBean;
import com.haiwai.administrator.japanhouse.fragment.comment.GaoerfuDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.HaiWaiDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.JiudianDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.NewHousedetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.OldHousedetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.ShangpuDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.TudidetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.XiaoQuDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.XiezilouDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.ZhongguoDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.ZuHousedetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.home.BieshudetailsActivity;
import com.haiwai.administrator.japanhouse.login.LoginActivity;
import com.haiwai.administrator.japanhouse.model.HouseRecordListBean;
import com.haiwai.administrator.japanhouse.presenter.HouseRecordPresenter;
import com.haiwai.administrator.japanhouse.utils.Constants;
import com.haiwai.administrator.japanhouse.utils.TUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LiShiJiLuActivity extends BaseActivity implements HouseRecordPresenter.HouseRecordCallBack, HouseHistoryAdapter.OnItemClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.mrecycler)
    RecyclerView mrecycler;
    private List<HouseRecordListBean.DatasBean> mList = new ArrayList();

    private HouseHistoryAdapter adapter;
    private HouseRecordPresenter presenter;
    private SpringView springView;
    private int pageNo = 1;

    private TextView state;
    private boolean isRefresh = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_shi);
        ButterKnife.bind(this);

        state = (TextView) findViewById(R.id.no_more_data);

        presenter = new HouseRecordPresenter(this, this);
        presenter.getHouseRecordList(MyApplication.getUserToken(), pageNo);

        springView = (SpringView) findViewById(R.id.act_history_springView);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        mList.clear();
                        pageNo = 1;
                        presenter.getHouseRecordList(MyApplication.getUserToken(), pageNo);
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
                        pageNo++;
                        presenter.getHouseRecordList(MyApplication.getUserToken(), pageNo);
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }
        });
        springView.setFooter(new DefaultFooter(this));
        springView.setHeader(new DefaultHeader(this));

        initData();
    }

    private void initData() {
        adapter = new HouseHistoryAdapter(this, mList);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mrecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void getHouseRecordList(Response<HouseRecordListBean> response) {
        if (TextUtils.equals(response.body().getCode(), "201")) {
            startActivity(new Intent(this, LoginActivity.class));
            MyApplication.logOut();
            return;
        }

        if (isRefresh) {
            TUtils.showFail(this, getString(R.string.refresh_success));
        }
        state.setText(getString(R.string.no_more_history_data));
        if (response != null && response.body() != null && response.body().getDatas() != null) {
            if (pageNo == 1) {
                if (response.body().getDatas().size() > 0) {
                    state.setVisibility(View.GONE);
                } else {
                    state.setVisibility(View.VISIBLE);
                }
            }

            if (response.body().getDatas().size() > 0) {
                mList.addAll(response.body().getDatas());
            }  else {
                pageNo --;
                if (!isRefresh) {
                    TUtils.showFail(this, getString(R.string.refresh_no_data));
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void historyNetwork() {
        TUtils.showFail(this, getString(R.string.refresh_fail));
        if (!MyApplication.isNetworkAvailable()) {
            state.setVisibility(View.VISIBLE);
            state.setText(getString(R.string.no_network));
        }
    }

    @Override
    public void onItemClickListener(String hType, String ShType, int houseId) {
        if (!TextUtils.isEmpty(hType)) {
            if (TextUtils.equals(hType, "0")) { //二手房
                Intent intent = new Intent(this, OldHousedetailsActivity.class);
                intent.putExtra("houseId", houseId + "");
                startActivity(intent);
            } else if (TextUtils.equals(hType, "1")) { //新房
                Intent intent = new Intent(this, NewHousedetailsActivity.class);
                intent.putExtra("houseId", houseId + "");
                startActivity(intent);
            } else if (TextUtils.equals(hType, "2")) { //租房
                Intent intent = new Intent(this, ZuHousedetailsActivity.class);

                if (TextUtils.equals(ShType, "0")) { //办公室出租
                    intent.putExtra("houseId", houseId + "");
                    intent.putExtra("houseType", "bangongshi");
                } else if (TextUtils.equals(ShType, "1")) { //商铺出租
                    intent.putExtra("houseId", houseId + "");
                    intent.putExtra("houseType", "shangpu");
                } else if (TextUtils.equals(ShType, "2")) { //别墅
                    intent.putExtra("houseId", houseId + "");
                    intent.putExtra("houseType", "bieshu");
                } else if (TextUtils.equals(ShType, "3")) { //二层公寓
                    intent.putExtra("houseId", houseId + "");
                    intent.putExtra("houseType", "erceng");
                } else if (TextUtils.equals(ShType, "4")) { //学生公寓
                    intent.putExtra("houseId", houseId + "");
                    intent.putExtra("houseType", "xuesheng");
                } else if (TextUtils.equals(ShType, "5")) { //多层公寓
                    intent.putExtra("houseId", houseId + "");
                    intent.putExtra("houseType", "duoceng");
                }
                startActivity(intent);
            } else if (TextUtils.equals(hType, "3")) {  //土地
                Intent intent = new Intent(this, TudidetailsActivity.class);
                intent.putExtra("houseId", houseId + "");
                startActivity(intent);
            } else if (TextUtils.equals(hType, "4")) {  //别墅
                Intent intent = new Intent(this, BieshudetailsActivity.class);
                intent.putExtra("houseId", houseId + "");
                startActivity(intent);
            } else if (TextUtils.equals(hType, "5")) {  //商业地产
                if (TextUtils.equals(ShType, "0")) {    //酒店
                    Intent intent = new Intent(this, JiudianDetailsActivity.class);
                    intent.putExtra("houseId", houseId + "");
                    startActivity(intent);
                } else if (TextUtils.equals(ShType, "1")) { //高尔夫球场
                    Intent intent = new Intent(this, GaoerfuDetailsActivity.class);
                    intent.putExtra("houseId", houseId + "");
                    startActivity(intent);
                } else if (TextUtils.equals(ShType, "2")) { //写字楼
                    Intent intent = new Intent(this, XiezilouDetailsActivity.class);
                    intent.putExtra("houseId", houseId + "");
                    startActivity(intent);
                } else if (TextUtils.equals(ShType, "3")) { //商铺
                    Intent intent = new Intent(this, ShangpuDetailsActivity.class);
                    intent.putExtra("houseId", houseId + "");
                    startActivity(intent);
                }
            } else if (TextUtils.equals(hType, "6")) { //中国房源
                Intent intent = new Intent(this, ZhongguoDetailsActivity.class);
                intent.putExtra("houseId", houseId + "");
                startActivity(intent);
            } else if (TextUtils.equals(hType, "7")) {  //海外房源
                Intent intent = new Intent(this, HaiWaiDetailsActivity.class);
                intent.putExtra("houseId", houseId + "");
                startActivity(intent);
            } else if (TextUtils.equals(hType, "8")) {  //找团地
                Intent intent = new Intent(this, XiaoQuDetailsActivity.class);
                intent.putExtra("houseId", houseId + "");
                startActivity(intent);
            }

        }
    }

    @Override
    public void onItemDeleteClickListener(int position, HouseRecordListBean.DatasBean datasBean) {
        presenter.deteleHouseRecord(datasBean.getHType(), datasBean.getId(), datasBean.getShType());
        mList.remove(position);
        adapter.notifyDataSetChanged();
    }


    class LiebiaoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.getView(R.id.layout_all_height).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(LiShiJiLuActivity.this, NewHousedetailsActivity.class));
                }
            });
        }
    }

    @Override
    public void finish() {
        EventBus.getDefault().post(new EventBean(Constants.EVENT_MINE));
        super.finish();
    }

    @OnClick(R.id.back_img)
    public void onClick() {
        finish();
    }
}
