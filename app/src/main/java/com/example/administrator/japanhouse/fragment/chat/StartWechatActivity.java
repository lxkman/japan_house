package com.example.administrator.japanhouse.fragment.chat;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartWechatActivity extends BaseActivity {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.Mrecycler)
    RecyclerView Mrecycler;
    private LiebiaoAdapter mLiebiaoAdapter;
    private List<String> mList = new ArrayList();
    private ItemAdapter itemAdapter;
    private RecyclerView mrecycler_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_wechat);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        if (mList.size() <= 0) {
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
        }
        if (mLiebiaoAdapter == null) {
            mLiebiaoAdapter = new LiebiaoAdapter(R.layout.lianxiren_item, mList);
        }
        Mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Mrecycler.setNestedScrollingEnabled(false);
        Mrecycler.setAdapter(mLiebiaoAdapter);
        mLiebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(StartWechatActivity.this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.back_img)
    public void onClick() {
        finish();
    }

    class LiebiaoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            mrecycler_item = helper.getView(R.id.mrecycler_item);
            if (itemAdapter == null) {
                itemAdapter = new ItemAdapter(R.layout.item_lianxiren, mList);
            }
            mrecycler_item.setLayoutManager(new LinearLayoutManager(StartWechatActivity.this, LinearLayoutManager.VERTICAL, false));
            mrecycler_item.setNestedScrollingEnabled(false);
            itemAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Toast.makeText(StartWechatActivity.this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
                }
            });
            mrecycler_item.setAdapter(itemAdapter);

        }
    }


    class ItemAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ItemAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
