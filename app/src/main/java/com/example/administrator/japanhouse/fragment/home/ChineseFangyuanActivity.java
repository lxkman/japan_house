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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.HomeItemBean;
import com.example.administrator.japanhouse.fragment.comment.ZhongguoDetailsActivity;

import java.util.ArrayList;
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
    private int[] itemPic = {R.drawable.beijing_iv, R.drawable.shanghai_iv, R.drawable.guangzhou_iv,
            R.drawable.shenzhen_iv, R.drawable.hangzhou_iv, R.drawable.chongqing_iv,R.drawable.qita_iv};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_fangyuan);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        String[] itemName = {getString(R.string.bj),
                getString(R.string.sh),
                getString(R.string.gz),
                getString(R.string.sz),
                getString(R.string.hz),
                getString(R.string.cq),
                getString(R.string.qt)};
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
                Intent intent = new Intent(mContext, ChineseLiebiaoActivity.class);
                intent.putExtra("type",position+"");
                startActivity(intent);
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
        LikeAdapter likeAdapter = new LikeAdapter(R.layout.item_china_like, likeList);
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
                intent.putExtra("edt_hint",getResources().getString(R.string.qsrdcmchqy));
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(mContext, ZhongguoDetailsActivity.class));
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
