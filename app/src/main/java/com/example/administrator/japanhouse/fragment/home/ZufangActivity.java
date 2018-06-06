package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.bean.HomeItemBean;
import com.example.administrator.japanhouse.bean.ZufangListBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.fragment.comment.ZuHousedetailsActivity;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.SpUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

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
    private int[] itemPic = {R.drawable.danshengongyu_iv, R.drawable.xueshenggongyu_iv, R.drawable.fufuzhufang_iv,
            R.drawable.bieshu_iv_zu, R.drawable.shangpu_iv, R.drawable.bangongshi_iv};
    private int[] yanjiuitemPic = {R.drawable.test_jingzhuang_iv, R.drawable.test_dayangtai_iv,
            R.drawable.test_yangguangfang_iv, R.drawable.test_haohua_iv};
    private boolean isJa;
    private List<ZufangListBean.DatasEntity> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zufang);
        ButterKnife.bind(this);
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        initView();
    }

    private void initView() {
        String[] itemName = {getString(R.string.zufang_dsgy),
                getString(R.string.zufang_xsgy),
                getString(R.string.zufang_ffzf),
                getString(R.string.bieshu),
                getString(R.string.zufang_sp),
                getString(R.string.zufang_bgs),
        };
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
                intent.putExtra("type", position);
                if (position == 0) {
                    intent.putExtra("houseType", "duoceng");
                } else if (position == 1) {
                    intent.putExtra("houseType", "xuesheng");
                } else if (position == 2) {
                    intent.putExtra("houseType", "erceng");
                } else if (position == 3) {
                    intent.putExtra("houseType", "bieshu");
                } else if (position == 4) {
                    intent.putExtra("houseType", "shangpu");
                    intent.putExtra("edt_hint", getResources().getString(R.string.xuandianpuflc));
                } else if (position == 5) {
                    intent.putExtra("houseType", "bangongshi");
                    intent.putExtra("edt_hint", getResources().getString(R.string.xuanbangongshiflc));
                }else if (position == 6) {
                    intent.putExtra("houseType", "zhaotuandi");
                }
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
                Intent intent = new Intent(mContext, YanjiuDetailActivity.class);
                intent.putExtra("yjType", position + "");
                startActivity(intent);
            }
        });
        tuijianRecycler.setNestedScrollingEnabled(false);
        tuijianRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        HttpParams params = new HttpParams();
        params.put("cId", 2);
        OkGo.<ZufangListBean>post(MyUrls.BASEURL + "/app/houseresourse/jrtj")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<ZufangListBean>(ZufangActivity.this, ZufangListBean.class) {
                    @Override
                    public void onSuccess(Response<ZufangListBean> response) {
                        int code = response.code();
                        ZufangListBean oldHouseListBean = response.body();
                        if (oldHouseListBean == null) {
                            return;
                        }
                        datas = oldHouseListBean.getDatas();
                        if (datas != null && datas.size() > 0) {
                            LikeAdapter likeAdapter = new LikeAdapter(R.layout.item_zufang_tuijian, datas);
                            tuijianRecycler.setAdapter(likeAdapter);
                            likeAdapter.setOnItemClickListener(ZufangActivity.this);
                        }
                    }
                });
        if (SpUtils.getBoolean("shendeng", false)) {
            notShendengLl.setVisibility(View.GONE);
            alreadyShendengLl.setVisibility(View.VISIBLE);
        }
        List<String> likeList = new ArrayList<>();
        likeList.add("");
        likeList.add("");
        likeList.add("");
        likeList.add("");
        likeList.add("");
        shendengRecycler.setNestedScrollingEnabled(false);
        shendengRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        ShendengAdapter shendengAdapter = new ShendengAdapter(R.layout.item_shendeng_layout, likeList);
        //        View footerView = LayoutInflater.from(mContext).inflate(R.layout.item_shendeng_footer,shendengRecycler,false);
        //        shendengAdapter.setFooterView(footerView);
        shendengRecycler.setAdapter(shendengAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (SpUtils.getBoolean("shendeng", false)) {
            notShendengLl.setVisibility(View.GONE);
            alreadyShendengLl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(ZufangActivity.this, ZuHousedetailsActivity.class);
        String houseType = datas.get(position).getHouseType();
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
        }
        intent.putExtra("houseId", datas.get(position).getId() + "");
        startActivity(intent);
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
            Glide.with(MyApplication.getGloableContext()).load(item.getImg()).into(imageView);
            helper.setText(R.id.tv_content,item.getTitle());
        }
    }

    private class LikeAdapter extends BaseQuickAdapter<ZufangListBean.DatasEntity, BaseViewHolder> {

        public LikeAdapter(int layoutResId, @Nullable List<ZufangListBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ZufangListBean.DatasEntity item) {
            Glide.with(MyApplication.getGloableContext()).load(item.getRoomImgs())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.tv_title, isJa ? item.getTitleJpn() : item.getTitleCn())
                    .setText(R.id.tv_area, isJa ? item.getSpecificLocationJpn() : item.getSpecificLocationCn())
                    .setText(R.id.tv_mianji, isJa ? item.getAreaJpn() : item.getAreaCn())
                    .setText(R.id.tv_price, isJa ? item.getPriceJpn()  : item.getPriceCn() );
        }
    }

    private class ShendengAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ShendengAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            if (helper.getAdapterPosition() == 4) {
                helper.getView(R.id.tv_lookmore).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_lookmore).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(mContext, ShendengFirstStepActivity.class));
                    }
                });
            }
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
                Intent intent = new Intent(mContext, HomeSearchActivity.class);
                intent.putExtra("popcontent", getResources().getString(R.string.zu_house));
                intent.putExtra("state", 4);
                startActivity(intent);
                break;
            case R.id.img_dingwei:
                startActivity(new Intent(mContext, HomeMapActivity.class));
                break;
            case R.id.img_message:
                EventBus.getDefault().post(new EventBean(Constants.EVENT_CHAT));
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
