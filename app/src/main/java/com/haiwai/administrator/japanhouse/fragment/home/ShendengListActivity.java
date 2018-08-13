package com.haiwai.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.bean.ZufangListBean;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.fragment.comment.ZuHousedetailsActivity;
import com.haiwai.administrator.japanhouse.utils.CacheUtils;
import com.haiwai.administrator.japanhouse.utils.Constants;
import com.haiwai.administrator.japanhouse.utils.GlideReqUtils;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.haiwai.administrator.japanhouse.utils.TUtils;
import com.haiwai.administrator.japanhouse.view.FluidLayout;
import com.haiwai.administrator.japanhouse.view.MyFooter;
import com.haiwai.administrator.japanhouse.view.MyHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.haiwai.administrator.japanhouse.R.id.result_fluidlayout;

public class ShendengListActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.springview)
    SpringView springview;
    @BindView(R.id.tv_noContent)
    TextView tvNoContent;
    private FluidLayout resultFluidlayout;
    private boolean isJa;
    private int page = 1;
    private boolean isLoadMore;
    private List<ZufangListBean.DatasEntity> mDatas;
    private LiebiaoAdapter liebiaoAdapter;
    private HashMap<String, List<String>> hashMap_want;
    private HashMap<String, List<String>> hashMap_dontwant;
    private List<String> allCheckedTextList;
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shendeng_list);
        ButterKnife.bind(this);
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        initView();
        initData();
        initListener();
    }

    private void initData() {
        HttpParams params = new HttpParams();
        if (isJa) {
            params.put("languageType", 1);
        } else {
            params.put("languageType", 0);
        }
        params.put("cId", 2);
        params.put("pageNo", page);
        String hxs = getIntent().getStringExtra("hxs");
        String zjId = getIntent().getStringExtra("zjId");
        List<String> qys = (List<String>) getIntent().getSerializableExtra("qys");
        hashMap_want = (HashMap<String, List<String>>) getIntent().getSerializableExtra("want");
        hashMap_dontwant = (HashMap<String, List<String>>) getIntent().getSerializableExtra("dontwant");
        if (TextUtils.isEmpty(hxs)) {
            hashMap_want = (HashMap<String, List<String>>) CacheUtils.get("shendeng_want");
            hashMap_dontwant = (HashMap<String, List<String>>) CacheUtils.get("shendeng_dontwant");
            hxs = CacheUtils.get("shendeng_hxs");
            zjId = CacheUtils.get("shendeng_zjId");
            qys = CacheUtils.get("shendeng_qys");
        }
        params.put("hxs", hxs);//户型
        params.put("zjId", zjId);//租金
        params.putUrlParams("qys", qys);//区域
        if (hashMap_want != null && hashMap_want.size() > 0) {
            for (String key : hashMap_want.keySet()) {
                params.putUrlParams(key, hashMap_want.get(key));
            }
        }
        if (hashMap_dontwant != null && hashMap_dontwant.size() > 0) {
            for (String key : hashMap_dontwant.keySet()) {
                params.putUrlParams(key, hashMap_dontwant.get(key));
            }
        }
        final String finalHxs = hxs;
        final String finalZjId = zjId;
        final List<String> finalQys = qys;
        OkGo.<ZufangListBean>post(MyUrls.BASEURL + "/app/houseresourse/sdzflist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<ZufangListBean>(ShendengListActivity.this, ZufangListBean.class) {
                    @Override
                    public void onSuccess(Response<ZufangListBean> response) {
                        int code = response.code();
                        ZufangListBean zufangListBean = response.body();
                        if (zufangListBean == null) {
                            return;
                        }
                        tvNoContent.setVisibility(View.GONE);
                        springview.setVisibility(View.VISIBLE);
                        List<ZufangListBean.DatasEntity> datas = zufangListBean.getDatas();
                        if (mDatas == null || mDatas.size() == 0) {
                            if (datas == null || datas.size() == 0) {
                                tvNoContent.setVisibility(View.VISIBLE);
                                springview.setVisibility(View.GONE);
                                if (liebiaoAdapter != null) {
                                    liebiaoAdapter.notifyDataSetChanged();
                                }
                                return;
                            }
                            //有数据再存
                            CacheUtils.put("shendeng_hxs", finalHxs);
                            CacheUtils.put("shendeng_zjId", finalZjId);
                            CacheUtils.put("shendeng_qys", finalQys);
                            CacheUtils.put("shendeng_want", hashMap_want);
                            CacheUtils.put("shendeng_dontwant", hashMap_dontwant);
                            mDatas = datas;
                            liebiaoAdapter = new LiebiaoAdapter(R.layout.item_cnxh_layout, mDatas);
                            recyclerView.setAdapter(liebiaoAdapter);
                            liebiaoAdapter.addHeaderView(headerView);
                        } else {
                            if (datas == null || datas.size() == 0) {
                                TUtils.showFail(mContext, getString(R.string.meiyougengduoshujule));
                                return;
                            }
                            if (!isLoadMore) {
                                mDatas = datas;
                                TUtils.showFail(mContext, getString(R.string.shuaxinchenggong));
                            } else {
                                mDatas.addAll(datas);
                            }
                            liebiaoAdapter.notifyDataSetChanged();
                        }
                        liebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(mContext, ZuHousedetailsActivity.class);
                                String houseType = mDatas.get(position).getHouseType();
                                if (houseType.equals("5")) {
                                    intent.putExtra("houseType", "duoceng");
                                } else if (houseType.equals("4")) {
                                    intent.putExtra("houseType", "xuesheng");
                                } else if (houseType.equals("3")) {
                                    intent.putExtra("houseType", "erceng");
                                } else if (houseType.equals("2")) {
                                    intent.putExtra("houseType", "bieshu");
                                } else if (houseType.equals("1")) {
                                    intent.putExtra("houseType", "shangpu");
                                } else if (houseType.equals("0")) {
                                    intent.putExtra("houseType", "bangongshi");
                                } else if (houseType.equals("6")) {
                                    intent.putExtra("houseType", "zhaotuandi");
                                }
                                intent.putExtra("houseId", mDatas.get(position).getId() + "");
                                startActivity(intent);
                            }
                        });
                    }
                });
    }

    private void initListener() {
        springview.setHeader(new MyHeader(this));
        springview.setFooter(new MyFooter(this));
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                page = 1;
                initData();
                springview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                isLoadMore = true;
                page++;
                initData();
                springview.onFinishFreshAndLoad();
            }
        });
    }

    private void initView() {
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        headerView = View.inflate(this, R.layout.shendenglist_header, null);
        resultFluidlayout = (FluidLayout) headerView.findViewById(result_fluidlayout);
        resultFluidlayout.removeAllViews();
        allCheckedTextList = (List<String>) getIntent().getSerializableExtra("checkedcontent");
        if (allCheckedTextList != null && allCheckedTextList.size() > 0) {
            CacheUtils.put("allCheckedTextList", allCheckedTextList);
            for (int i = 0; i < allCheckedTextList.size(); i++) {
                final TextView tv = (TextView) View.inflate(mContext, R.layout.item_shendeng_result, null);
                tv.setText(allCheckedTextList.get(i));
                FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 12, 12, 12);
                resultFluidlayout.addView(tv, params);
            }
        } else {
            allCheckedTextList = CacheUtils.get("allCheckedTextList");
            if (allCheckedTextList==null || allCheckedTextList.size()==0){
                return;
            }
            for (int i = 0; i < allCheckedTextList.size(); i++) {
                final TextView tv = (TextView) View.inflate(mContext, R.layout.item_shendeng_result, null);
                tv.setText(allCheckedTextList.get(i));
                FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 12, 12, 12);
                resultFluidlayout.addView(tv, params);
            }
        }
    }

    private class LiebiaoAdapter extends BaseQuickAdapter<ZufangListBean.DatasEntity, BaseViewHolder> {

        public LiebiaoAdapter(int layoutResId, @Nullable List<ZufangListBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ZufangListBean.DatasEntity item) {
            Glide.with(MyApplication.getGloableContext())
                    .load(TextUtils.isEmpty(item.getVideoImgs()) ? item.getRoomImgs():item.getVideoImgs())
                    .apply(GlideReqUtils.getReq())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.tv_title, isJa ? item.getTitleJpn() : item.getTitleCn())
                    .setText(R.id.tv_area, isJa ? item.getSpecificLocationJpn() : item.getSpecificLocationCn())
                    .setText(R.id.tv_mianji, isJa ? item.getAreaJpn() : item.getAreaCn())
                    .setText(R.id.tv_ting, isJa ? item.getDoorModelJpn() : item.getDoorModelCn())
                    .setText(R.id.tv_price, isJa ? item.getPriceJpn() : item.getPriceCn());
        }
    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
