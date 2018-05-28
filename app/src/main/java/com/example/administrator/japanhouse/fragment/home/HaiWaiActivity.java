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
import com.example.administrator.japanhouse.bean.ChinaCityItemBean;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.fragment.comment.HaiWaiDetailsActivity;
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

public class HaiWaiActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

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
    private int[] itemPic = {R.drawable.aodaliya_iv, R.drawable.meiguo_iv, R.drawable.jianada_iv,
            R.drawable.yingguo_iv, R.drawable.taiguo_iv, R.drawable.xinxilan_iv};
    private boolean isJa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hai_wai);
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
        params.put("hType", 5);
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
                                Intent intent = new Intent(mContext, HaiwaiListActivity.class);
                                intent.putExtra("id",datas.get(position).getCountryOrcityId()+"");
                                startActivity(intent);
                            }
                        });
                    }
                });



        List<String> likeList = new ArrayList<>();
        likeList.add("");
        likeList.add("");
        likeList.add("");
        likeList.add("");
        likeList.add("");
        likeRecycler.setNestedScrollingEnabled(false);
        likeRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        LikeAdapter likeAdapter = new LikeAdapter(R.layout.item_sydc_like, likeList);
        likeRecycler.setAdapter(likeAdapter);
        likeAdapter.setOnItemClickListener(this);
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
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(mContext, HaiWaiDetailsActivity.class));
    }

    private class FenleiAdapter extends BaseQuickAdapter<ChinaCityItemBean.DatasEntity, BaseViewHolder> {

        public FenleiAdapter(int layoutResId, @Nullable List<ChinaCityItemBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ChinaCityItemBean.DatasEntity item) {
            helper.setText(R.id.item_name_tv, isJa?item.getScreeValJpn():item.getScreeValCn());
            Glide.with(MyApplication.getGloableContext()).load(item.getLogoUrl())
                    .into((ImageView) helper.getView(R.id.item_pic_iv));
        }
    }

    private class LikeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public LikeAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
