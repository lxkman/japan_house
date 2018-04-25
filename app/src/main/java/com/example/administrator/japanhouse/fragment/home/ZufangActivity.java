package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.HomeItemBean;
import com.example.administrator.japanhouse.fragment.comment.ZuHousedetailsActivity;
import com.example.administrator.japanhouse.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZufangActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.img_dingwei)
    ImageView imgDingwei;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.fenlei_recycler)
    RecyclerView fenleiRecycler;
    @BindView(R.id.look_more_tv)
    TextView lookMoreTv;
    @BindView(R.id.yanjiu_recycler)
    RecyclerView yanjiuRecycler;
    @BindView(R.id.shendeng_tv)
    TextView shendengTv;
    @BindView(R.id.view_12)
    TextView view12;
    @BindView(R.id.tuijian_recycler)
    RecyclerView tuijianRecycler;
    @BindView(R.id.not_shendeng_ll)
    LinearLayout notShendengLl;
    @BindView(R.id.shendeng_more_tv)
    TextView shendengMoreTv;
    @BindView(R.id.shendeng_recycler)
    RecyclerView shendengRecycler;
    @BindView(R.id.already_shendeng_ll)
    LinearLayout alreadyShendengLl;
    private String[] itemName = {"单身公寓", "学生公寓", "夫妇住房", "大家庭住房", "商铺", "办公室", "饮食店", " 酒店承包"};
    private int[] itemPic = {R.drawable.danshengongyu_iv, R.drawable.xueshenggongyu_iv, R.drawable.fufuzhufang_iv,
            R.drawable.dajiating_iv, R.drawable.shangpu_iv, R.drawable.bangongshi_iv, R.drawable.yinshidian_iv,
            R.drawable.jiudianchengbao_iv};
    private int[] yanjiuitemPic = {R.drawable.test_jingzhuang_iv, R.drawable.test_dayangtai_iv,
            R.drawable.test_yangguangfang_iv, R.drawable.test_haohua_iv};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zufang);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
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
                Intent intent = new Intent(mContext, ZufangListActivity.class);
                intent.putExtra("type",position+"");
                startActivity(intent);
            }
        });

        List<HomeItemBean> yanjiuItemBeanList = new ArrayList<>();
        for (int i = 0; i < yanjiuitemPic.length; i++) {
            yanjiuItemBeanList.add(new HomeItemBean(itemName[i], yanjiuitemPic[i]));
        }
        yanjiuRecycler.setNestedScrollingEnabled(false);
        yanjiuRecycler.setLayoutManager(new GridLayoutManager(mContext, 2));
        YanjiuAdapter yanjiuAdapter = new YanjiuAdapter(R.layout.item_yanjiu_layout, yanjiuItemBeanList);
        yanjiuRecycler.setAdapter(yanjiuAdapter);
        yanjiuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext,YanjiuDetailActivity.class));
            }
        });

        List<String> likeList = new ArrayList<>();
        likeList.add("");
        likeList.add("");
        likeList.add("");
        likeList.add("");
        likeList.add("");
        tuijianRecycler.setNestedScrollingEnabled(false);
        tuijianRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        LikeAdapter likeAdapter = new LikeAdapter(R.layout.item_sydc_like, likeList);
        tuijianRecycler.setAdapter(likeAdapter);
        likeAdapter.setOnItemClickListener(this);

        if (SpUtils.getBoolean("shendeng",false)){
            notShendengLl.setVisibility(View.GONE);
            alreadyShendengLl.setVisibility(View.VISIBLE);
        }

        shendengRecycler.setNestedScrollingEnabled(false);
        shendengRecycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        ShendengAdapter shendengAdapter = new ShendengAdapter(R.layout.item_shendeng_layout,likeList);
        View footerView = LayoutInflater.from(mContext).inflate(R.layout.item_shendeng_footer,shendengRecycler,false);
        shendengAdapter.setFooterView(footerView);
        shendengRecycler.setAdapter(shendengAdapter);
        shendengAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, ZuHousedetailsActivity.class));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (SpUtils.getBoolean("shendeng",false)){
            notShendengLl.setVisibility(View.GONE);
            alreadyShendengLl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(new Intent(mContext, ZuHousedetailsActivity.class));
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

    private class YanjiuAdapter extends BaseQuickAdapter<HomeItemBean, BaseViewHolder> {

        public YanjiuAdapter(int layoutResId, @Nullable List<HomeItemBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeItemBean item) {
            ImageView imageView = helper.getView(R.id.item_pic_iv);
            Glide.with(mContext).load(item.getImg()).into(imageView);
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

    private class ShendengAdapter extends BaseQuickAdapter<String, BaseViewHolder>{

        public ShendengAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    @OnClick({R.id.back_img, R.id.search_tv, R.id.img_dingwei, R.id.img_message,
            R.id.look_more_tv, R.id.shendeng_tv, R.id.shendeng_more_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            case R.id.search_tv:
                startActivity(new Intent(mContext, HomeSearchActivity.class));
                break;
            case R.id.img_dingwei:
                startActivity(new Intent(mContext, HomeMapActivity.class));
                break;
            case R.id.img_message:
                setResult(2);
                finish();
                break;
            case R.id.look_more_tv:
                startActivity(new Intent(mContext, YanjiuListActivity.class));
                break;
            case R.id.shendeng_tv:
                startActivity(new Intent(mContext, ShendengFirstStepActivity.class));
                break;
            case R.id.shendeng_more_tv:
                startActivity(new Intent(mContext, ShendengListActivity.class));
                break;
        }
    }
}
