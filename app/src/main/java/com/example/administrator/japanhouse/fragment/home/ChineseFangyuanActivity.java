package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.ChinaCityItemBean;
import com.example.administrator.japanhouse.bean.ChinaListBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.fragment.comment.ZhongguoDetailsActivity;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.GlideReqUtils;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChineseFangyuanActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.search_et)
    TextView searchEt;
    @BindView(R.id.fenlei_recycler)
    RecyclerView fenleiRecycler;
    @BindView(R.id.like_recycler)
    RecyclerView likeRecycler;
    @BindView(R.id.rl_search)
    RelativeLayout rl_search;
    private boolean isJa;
    private List<ChinaListBean.DatasEntity> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_fangyuan);
        ButterKnife.bind(this);
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        initview();
    }

    private void initview() {
        fenleiRecycler.setNestedScrollingEnabled(false);
        fenleiRecycler.setLayoutManager(new GridLayoutManager(mContext, 4));
        HttpParams params = new HttpParams();
        params.put("hType", 4);
        OkGo.<ChinaCityItemBean>post(MyUrls.BASEURL + "/app/oiverseas/selectcityscree")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<ChinaCityItemBean>(ChinaCityItemBean.class) {
                    @Override
                    public void onSuccess(Response<ChinaCityItemBean> response) {
                        int code = response.code();
                        ChinaCityItemBean body = response.body();
                        final List<ChinaCityItemBean.DatasEntity> datas = body.getDatas();
                        FenleiAdapter fenleiAdapter = new FenleiAdapter(R.layout.item_chinese_city, datas);
                        fenleiRecycler.setAdapter(fenleiAdapter);
                        fenleiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(mContext, ChineseLiebiaoActivity.class);
                                intent.putExtra("id", datas.get(position).getCountryOrcityId());
                                startActivity(intent);
                            }
                        });
                    }
                });
        likeRecycler.setNestedScrollingEnabled(false);
        likeRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        HttpParams params2 = new HttpParams();
        params2.put("pageNo", 1);
        if (isJa) {
            params2.put("languageType", 1);
        } else {
            params2.put("languageType", 0);
        }
        params2.put("hType", 1);
//        params2.put("cityId", 0);//城市id
        OkGo.<ChinaListBean>post(MyUrls.BASEURL + "/app/oiverseas/searchlist")
                .tag(this)
                .params(params2)
                .execute(new DialogCallback<ChinaListBean>(ChineseFangyuanActivity.this, ChinaListBean.class) {
                    @Override
                    public void onSuccess(Response<ChinaListBean> response) {
                        int code = response.code();
                        ChinaListBean chinaListBean = response.body();
                        if (chinaListBean == null) {
                            return;
                        }
                        datas = chinaListBean.getDatas();
                        if (datas != null && datas.size() > 0) {
                            LikeAdapter likeAdapter = new LikeAdapter(R.layout.item_china_like, datas);
                            likeRecycler.setAdapter(likeAdapter);
                            likeAdapter.setOnItemClickListener(ChineseFangyuanActivity.this);
                        }
                    }
                });
    }

    @OnClick({R.id.title_back_iv, R.id.rl_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.rl_search:
                Intent intent = new Intent(mContext, SydcSearchActivity.class);
                intent.putExtra("edt_hint", getResources().getString(R.string.qsrdcmchqy));
                intent.putExtra("state", 7);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent=new Intent(mContext, ZhongguoDetailsActivity.class);
        intent.putExtra("houseId",datas.get(position).getId()+"");
        startActivity(intent);
    }

    private class FenleiAdapter extends BaseQuickAdapter<ChinaCityItemBean.DatasEntity, BaseViewHolder> {

        public FenleiAdapter(int layoutResId, @Nullable List<ChinaCityItemBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ChinaCityItemBean.DatasEntity item) {
            helper.setText(R.id.item_name_tv, isJa ? item.getScreeValJpn() : item.getScreeValCn());
            Glide.with(MyApplication.getGloableContext()).load(item.getLogoUrl())
                    .into((ImageView) helper.getView(R.id.item_pic_iv));
        }
    }

    private class LikeAdapter extends BaseQuickAdapter<ChinaListBean.DatasEntity, BaseViewHolder> {

        public LikeAdapter(int layoutResId, @Nullable List<ChinaListBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ChinaListBean.DatasEntity item) {
            Glide.with(MyApplication.getGloableContext())
                    .load(TextUtils.isEmpty(item.getVideoImgs()) ? item.getHouseImgs():item.getVideoImgs())
                    .apply(GlideReqUtils.getReq())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
            String area;
            if (isJa) {
                area = item.getSpecificLocationJpn();
            } else {
                area = item.getSpecificLocationCn();
            }
            if (area.length()>7){
                area = area.substring(0, 7) + "...";
            }
            helper.setText(R.id.tv_title, isJa ? item.getTitleJpn() : item.getTitleCn())
                    .setText(R.id.tv_area, area)
                    .setText(R.id.tv_mianji, isJa ? item.getAreaJpn() : item.getAreaCn())
                    .setText(R.id.tv_price, isJa ? item.getSellingPriceJpn() : item.getSellingPriceCn())
                    .setText(R.id.tv_ting, isJa ? item.getHouseTypeJpn() : item.getHouseTypeCn());
        }
    }
}
