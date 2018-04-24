package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.example.administrator.japanhouse.utils.TUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShendengFirstStepActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.yusuan_recycler)
    RecyclerView yusuanRecycler;
    @BindView(R.id.huxing_recycler)
    RecyclerView huxingRecycler;
    @BindView(R.id.weizhi_recycler)
    RecyclerView weizhiRecycler;
    @BindView(R.id.next_tv)
    TextView nextTv;
    private List<OneCheckBean> yusuanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shendeng_first_step);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        yusuanList = new ArrayList<>();
        yusuanList.add(new OneCheckBean(false,"2500元以下"));
        yusuanList.add(new OneCheckBean(false,"2500-4000元"));
        yusuanList.add(new OneCheckBean(false,"4000-5500元"));
        yusuanList.add(new OneCheckBean(false,"5500-7000元"));
        yusuanList.add(new OneCheckBean(false,"7000-8500元"));
        yusuanList.add(new OneCheckBean(false,"8500元以上"));

        yusuanRecycler.setNestedScrollingEnabled(false);
        yusuanRecycler.setLayoutManager(new GridLayoutManager(mContext,3));
        final YusuanAdapter yusuanAdapter = new YusuanAdapter(R.layout.item_shendeng_yusuan, yusuanList);
        yusuanRecycler.setAdapter(yusuanAdapter);
        yusuanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < yusuanList.size(); i++) {
                    if (i == position){
                        yusuanList.get(i).setChecked(true);
                    }else {
                        yusuanList.get(i).setChecked(false);
                    }
                }
                yusuanAdapter.notifyDataSetChanged();
            }
        });

        final List<OneCheckBean> huxingList = new ArrayList<>();
        huxingList.add(new OneCheckBean(false,"不限"));
        huxingList.add(new OneCheckBean(false,"一室"));
        huxingList.add(new OneCheckBean(false,"二室"));
        huxingList.add(new OneCheckBean(false,"三室"));
        huxingList.add(new OneCheckBean(false,"五室及以上"));

        huxingRecycler.setNestedScrollingEnabled(false);
        huxingRecycler.setLayoutManager(new GridLayoutManager(mContext,3));
        final HuxingAdapter huxingAdapter = new HuxingAdapter(R.layout.item_shendeng_yusuan, huxingList);
        huxingRecycler.setAdapter(huxingAdapter);
        huxingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < huxingList.size(); i++) {
                    if (i == position){
                        if (huxingList.get(i).isChecked()){
                            huxingList.get(i).setChecked(false);
                        }else {
                            huxingList.get(i).setChecked(true);
                        }
                    }
                }
                huxingAdapter.notifyDataSetChanged();
            }
        });

        final List<OneCheckBean> weizhiList = new ArrayList<>();
        weizhiList.add(new OneCheckBean(false,"石景山・杨庄"));
        weizhiList.add(new OneCheckBean(false,"石景山・古城"));
        weizhiList.add(new OneCheckBean(false,"大兴・高米店"));
        weizhiList.add(new OneCheckBean(false,"延庆・延庆城区"));
        weizhiList.add(new OneCheckBean(false,"房山・琉璃河"));
        weizhiRecycler.setNestedScrollingEnabled(false);
        weizhiRecycler.setLayoutManager(new GridLayoutManager(mContext,3));
        final WeizhiAdapter weizhiAdapter = new WeizhiAdapter(R.layout.item_shendeng_yusuan, weizhiList);
        View footerView = LayoutInflater.from(this).inflate(R.layout.item_footer_shendeng,weizhiRecycler,false);
        weizhiAdapter.addFooterView(footerView);
        weizhiRecycler.setAdapter(weizhiAdapter);
        LinearLayout footerview = (LinearLayout) footerView.findViewById(R.id.footerview);
        footerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TUtils.showShort(mContext,"更多位置");
            }
        });
        weizhiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < weizhiList.size(); i++) {
                    if (i == position){
                        weizhiList.get(i).setChecked(true);
                    }else {
                        weizhiList.get(i).setChecked(false);
                    }
                }
                weizhiAdapter.notifyDataSetChanged();
            }
        });
    }

    private class YusuanAdapter extends BaseQuickAdapter<OneCheckBean,BaseViewHolder>{

        public YusuanAdapter(int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final OneCheckBean item) {
            final TextView textView = helper.getView(R.id.item_content_tv);
            textView.setText(item.getName());
            if (item.isChecked()){
                textView.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_true));
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
            }else {
                textView.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_false));
                textView.setTextColor(getResources().getColor(R.color.text_gray));
            }
        }
    }

    private class HuxingAdapter extends BaseQuickAdapter<OneCheckBean,BaseViewHolder>{

        public HuxingAdapter(int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OneCheckBean item) {
            TextView textView = helper.getView(R.id.item_content_tv);
            textView.setText(item.getName());
            if (item.isChecked()){
                textView.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_true));
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
            }else {
                textView.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_false));
                textView.setTextColor(getResources().getColor(R.color.text_gray));
            }
        }
    }

    private class WeizhiAdapter extends BaseQuickAdapter<OneCheckBean,BaseViewHolder>{

        public WeizhiAdapter(int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OneCheckBean item) {
            final TextView textView = helper.getView(R.id.item_content_tv);
            textView.setText(item.getName());
            if (item.isChecked()){
                textView.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_true));
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
            }else {
                textView.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_false));
                textView.setTextColor(getResources().getColor(R.color.text_gray));
            }
        }
    }

    @OnClick({R.id.title_back_iv, R.id.next_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.next_tv:
                startActivity(new Intent(mContext,ShendengSecondStepActivity.class));
                finish();
                break;
        }
    }
}
