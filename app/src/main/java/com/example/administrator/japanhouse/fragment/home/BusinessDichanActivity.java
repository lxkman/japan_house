package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.administrator.japanhouse.bean.HomeItemBean;
import com.example.administrator.japanhouse.bean.SydcListBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.fragment.comment.ShangpuDetailsActivity;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessDichanActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

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
    private int[] itemPic = {R.drawable.shangpumaimai_iv, R.drawable.xzlmaimai_iv,
            R.drawable.gaoerfu_iv, R.drawable.jiudian_iv};
    @BindView(R.id.rl_search)
    RelativeLayout rl_search;
    private boolean isJa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_dichan);
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
        String[] itemName = {getString(R.string.business_symm),
                getString(R.string.business_mmxzl),
                getString(R.string.business_gefqc), getString(R.string.business_jd)};
        likeRecycler.setNestedScrollingEnabled(false);
        likeRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        List<HomeItemBean> homeItemBeanList = new ArrayList<>();
        for (int i = 0; i < itemName.length; i++) {
            homeItemBeanList.add(new HomeItemBean(itemName[i], itemPic[i]));
        }
        fenleiRecycler.setNestedScrollingEnabled(false);
        fenleiRecycler.setLayoutManager(new GridLayoutManager(mContext, 4));
        FenleiAdapter fenleiAdapter = new FenleiAdapter(R.layout.item_sydc_fenlei, homeItemBeanList);
        fenleiRecycler.setAdapter(fenleiAdapter);
        fenleiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, SydcLiebiaoActivity.class);
                intent.putExtra("type", position);
                if (position == 0) {
                    intent.putExtra("houseType", "shangpu");
                } else if (position == 1) {
                    intent.putExtra("houseType", "xiezilou");
                } else if (position == 2) {
                    intent.putExtra("houseType", "gaoerfu");
                } else if (position == 3) {
                    intent.putExtra("houseType", "jiudian");
                }
                startActivity(intent);
            }
        });
        HttpParams params = new HttpParams();
        if (isJa) {
            params.put("languageType", 1);
        } else {
            params.put("languageType", 0);
        }
        params.put("hType", 0);
        params.put("pageNo", 1);
        OkGo.<SydcListBean>post(MyUrls.BASEURL + "/app/realestate/searchlist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SydcListBean>(BusinessDichanActivity.this, SydcListBean.class) {
                    @Override
                    public void onSuccess(Response<SydcListBean> response) {
                        int code = response.code();
                        SydcListBean oldHouseListBean = response.body();
                        if (oldHouseListBean == null) {
                            return;
                        }
                        List<SydcListBean.DatasEntity> datas = oldHouseListBean.getDatas();
                        if (datas == null || datas.size() == 0) {
                            return;
                        }
                        LikeAdapter likeAdapter = new LikeAdapter(R.layout.item_sydc_like, datas);
                        likeRecycler.setAdapter(likeAdapter);
                        likeAdapter.setOnItemClickListener(BusinessDichanActivity.this);
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
                Intent intent = new Intent(mContext, HomeSearchActivity.class);
                intent.putExtra("popcontent", getResources().getString(R.string.shangyedichan));
                intent.putExtra("state", 5);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(mContext, ShangpuDetailsActivity.class));
    }

    private class FenleiAdapter extends BaseQuickAdapter<HomeItemBean, BaseViewHolder> {

        public FenleiAdapter(int layoutResId, @Nullable List<HomeItemBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeItemBean item) {
            helper.setText(R.id.item_name_tv, item.getTitle());
            helper.setImageResource(R.id.item_pic_iv, item.getImg());
        }
    }

    private class LikeAdapter extends BaseQuickAdapter<SydcListBean.DatasEntity, BaseViewHolder> {

        public LikeAdapter(int layoutResId, @Nullable List<SydcListBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SydcListBean.DatasEntity item) {
            Glide.with(MyApplication.getGloableContext()).load(item.getRealEstateImgs())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.tv_title, isJa ? item.getTitleJpn() : item.getTitleCn())
                    .setText(R.id.tv_area, isJa ? item.getSpecificLocationJpn() : item.getSpecificLocationCn())
                    .setText(R.id.tv_mianji, isJa ? item.getAreaJpn() : item.getAreaCn())
                    .setText(R.id.tv_price, isJa ? item.getSellingPriceJpn() + "万" : item.getSellingPriceCn() + "万");
        }
    }

}
