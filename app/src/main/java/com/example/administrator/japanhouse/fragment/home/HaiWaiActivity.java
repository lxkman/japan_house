package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.HomeItemBean;
import com.example.administrator.japanhouse.fragment.comment.HaiWaiDetailsActivity;

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
    private int[] itemPic = {R.drawable.aodaliya_iv, R.drawable.meiguo_iv, R.drawable.jianada_iv,
            R.drawable.yingguo_iv, R.drawable.taiguo_iv, R.drawable.xinxilan_iv};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hai_wai);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        String[] itemName = {getString(R.string.hw_adly),
                getString(R.string.hw_mg),
                getString(R.string.hw_jnd),
                getString(R.string.hw_yg),
                getString(R.string.hw_tg),
                getString(R.string.hw_xxl)};
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
                startActivity(new Intent(mContext,HaiwaiListActivity.class));
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

    @OnClick({R.id.title_back_iv, R.id.search_et})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.search_et:
                startActivity(new Intent(mContext,SydcSearchActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(mContext, HaiWaiDetailsActivity.class));
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

    private class LikeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public LikeAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
