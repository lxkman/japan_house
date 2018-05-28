package com.example.administrator.japanhouse.fragment.comment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.HouseDetailsBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.im.DetailsExtensionModule;
import com.example.administrator.japanhouse.more.ZuBanGongMoreActivity;
import com.example.administrator.japanhouse.more.ZuBieSuMoreActivity;
import com.example.administrator.japanhouse.more.ZuDuoCengMoreActivity;
import com.example.administrator.japanhouse.more.ZuErCengMoreActivity;
import com.example.administrator.japanhouse.more.ZuShangPuMoreActivity;
import com.example.administrator.japanhouse.more.ZuXueShengMoreActivity;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.example.administrator.japanhouse.view.BaseDialog;
import com.example.administrator.japanhouse.view.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.zackratos.ultimatebar.UltimateBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;

public class ZuHousedetailsActivity extends BaseActivity {

    @BindView(R.id.vp_vidio)
    ViewPager vpVidio;
    @BindView(R.id.tv_to_num)
    TextView tvToNum;
    @BindView(R.id.tv_all_num)
    TextView tvAllNum;
    @BindView(R.id.Huxing_Recycler)
    RecyclerView HuxingRecycler;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.img_start)
    ImageView imgStart;
    @BindView(R.id.tv_See_More)
    TextView tvSeeMore;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.love_Recycler)
    RecyclerView loveRecycler;
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.mScrollView)
    NestedScrollView mScrollView;
    @BindView(R.id.re_top_bg)
    RelativeLayout re_top_bg;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_details_name)
    TextView tvDetailsName;
    @BindView(R.id.tv_details_area)
    TextView tvDetailsArea;
    @BindView(R.id.tv_details_location)
    TextView tvDetailsLocation;
    @BindView(R.id.tv_details_manager_head)
    CircleImageView tvDetailsManagerHead;
    @BindView(R.id.tv_details_manager_name)
    TextView tvDetailsManagerName;
    private int mDistanceY;
    private LoveAdapter loveAdapter;
    private LiebiaoAdapter mLiebiaoAdapter;
    private List<String> mList = new ArrayList();
    private List<Fragment> mBaseFragmentList = new ArrayList<>();
    private FragmentManager fm;
    private MyAdapter myAdapter;
    private String houseType;
    private HouseDetailsBean.DatasBean datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //拉伸图片覆盖标题栏
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar(false);
        setContentView(R.layout.activity_lishi_zu_house);
        ButterKnife.bind(this);
        String iszu = getIntent().getStringExtra("iszu");
        houseType = getIntent().getStringExtra("houseType");
        Toast.makeText(mContext, houseType, Toast.LENGTH_SHORT).show();
        if (!TextUtils.isEmpty(iszu) && iszu.equals("iszu")) {
            tv_price.setText("1750元/月");
        }
        initData();
        initViewPager();
        //猜你喜欢
        initLoveRecycler();
        initScroll();
        initDetailsNet();
        findViewById(R.id.lishi_zu_wl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.getInstace(ZuHousedetailsActivity.this).setStringPreference(Constants.CHAT, Constants.CHAT_DETAILS);
                setMyExtensionModule();
                if (RongIM.getInstance() != null) {
                    Log.e("MainActivity", "创建单聊");
                    RongIM.getInstance().startPrivateChat(ZuHousedetailsActivity.this, "123456", getString(R.string.act_chat_title));
                }
            }
        });

    }

    private void initDetailsNet() {
        String houseId = getIntent().getStringExtra("houseId");
        HttpParams params = new HttpParams();
//        if (houseId!=null&&!houseId.equals("")){
        params.put("hId", "8");
        OkGo.<HouseDetailsBean>post(MyUrls.BASEURL + "/app/houseresourse/houseinfo")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<HouseDetailsBean>(this, HouseDetailsBean.class) {
                    @Override
                    public void onSuccess(Response<HouseDetailsBean> response) {
                        int code = response.code();
                        HouseDetailsBean oldHouseListBean = response.body();
                        datas = oldHouseListBean.getDatas();
                        HouseDetailsBean.DatasBean.HwdcBrokerBean hwdcBroker = datas.getHwdcBroker();
                        boolean isJa = MyUtils.isJa();
                        tvDetailsName.setText(isJa ? datas.getTitleJpn() : datas.getTitleCn());
                        tv_price.setText(isJa ? datas.getSellingPriceJpn() : datas.getSellingPriceCn());
                        tvDetailsArea.setText(isJa ? datas.getAreaJpn() : datas.getAreaCn());
                        tvDetailsLocation.setText(isJa ? datas.getSpecificLocationJpn() : datas.getSpecificLocationCn());
                        tvDetailsManagerName.setText(hwdcBroker.getBrokerName());
                        Glide.with(ZuHousedetailsActivity.this).load(hwdcBroker.getPic() + "").into(tvDetailsManagerHead);
                    }
                });
    }

    public void setMyExtensionModule() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new DetailsExtensionModule());
            }
        }
    }

    private void initScroll() {
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //滑动的距离
                mDistanceY += scrollY - oldScrollY;
                //toolbar的高度
                int toolbarHeight = 300;//我写死的高度

                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= toolbarHeight) {
                    float scale = (float) mDistanceY / toolbarHeight;
                    float alpha = scale * 255;
                    re_top_bg.setBackgroundColor(Color.argb((int) alpha, 199, 151, 127));
                    tv_title.setVisibility(View.GONE);
                } else {
                    //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
                    //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
                    //将标题栏的颜色设置为完全不透明状态
                    re_top_bg.setBackgroundResource(R.color.shihuangse);
                    tv_title.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initLoveRecycler() {
        if (loveAdapter == null) {
            loveAdapter = new LoveAdapter(R.layout.item_zuijin, mList);
        }
        loveRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        loveRecycler.setNestedScrollingEnabled(false);
        loveRecycler.setAdapter(loveAdapter);
        loveAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ZuHousedetailsActivity.this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
            }
        });
    }


    class LoveAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public LoveAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
        }
    }

    private void initViewPager() {
        if (mBaseFragmentList.size() <= 0) {
            //            mBaseFragmentList.add(new VidioFragment());
            mBaseFragmentList.add(new BannerFragment());
            mBaseFragmentList.add(new BannerFragment());
            mBaseFragmentList.add(new BannerFragment());
        }
        tvAllNum.setText(mBaseFragmentList.size() + "");
        fm = getSupportFragmentManager();
        myAdapter = new MyAdapter(fm);
        vpVidio.setAdapter(myAdapter);
        vpVidio.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvToNum.setText((position + 1) + "");
                if (position == 1) {
                    JZVideoPlayer.releaseAllVideos();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    private void initData() {
        if (mList.size() <= 0) {
            mList.add("");
            mList.add("");
            mList.add("");
        }


        if (mLiebiaoAdapter == null) {
            mLiebiaoAdapter = new LiebiaoAdapter(R.layout.huxing_item, mList);
        }
        HuxingRecycler.setLayoutManager(new GridLayoutManager(ZuHousedetailsActivity.this, 3));
        HuxingRecycler.setNestedScrollingEnabled(false);
        HuxingRecycler.setAdapter(mLiebiaoAdapter);
        mLiebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ZuHousedetailsActivity.this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
            }
        });
    }


    class MyAdapter extends FragmentStatePagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mBaseFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mBaseFragmentList.size();
        }
    }

    @OnClick({R.id.img_share, R.id.img_start, R.id.tv_See_More, R.id.back_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_share:
                showDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.img_start:
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_See_More:
                if (!TextUtils.isEmpty(houseType)) {
                    Intent intent;
                    switch (houseType) {
                        case "duoceng":
                            intent = new Intent(mContext, ZuDuoCengMoreActivity.class);
                            intent.putExtra("datas", datas);
                            startActivity(intent);
                            break;
                        case "xuesheng":
                            intent = new Intent(mContext, ZuXueShengMoreActivity.class);
                            intent.putExtra("datas", datas);
                            startActivity(intent);
                            break;
                        case "erceng":
                            intent = new Intent(mContext, ZuErCengMoreActivity.class);
                            intent.putExtra("datas", datas);
                            startActivity(intent);
                            break;
                        case "bieshu":
                            intent = new Intent(mContext, ZuBieSuMoreActivity.class);
                            intent.putExtra("datas", datas);
                            startActivity(intent);
                            break;
                        case "shangpu":
                            intent = new Intent(mContext, ZuShangPuMoreActivity.class);
                            intent.putExtra("datas", datas);
                            startActivity(intent);
                            break;
                        case "bangongshi":
                            intent = new Intent(mContext, ZuBanGongMoreActivity.class);
                            intent.putExtra("datas", datas);
                            startActivity(intent);
                            break;
                    }
                }
                break;
            case R.id.back_img:
                finish();
                break;
        }
    }

    private void showDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_share)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        LinearLayout weiliao_layout = (LinearLayout) dialog.findViewById(R.id.weiliao_layout);
        LinearLayout weixin_layout = (LinearLayout) dialog.findViewById(R.id.weixin_layout);
        LinearLayout pengyouquan_layout = (LinearLayout) dialog.findViewById(R.id.pengyouquan_layout);
        LinearLayout weibo_layout = (LinearLayout) dialog.findViewById(R.id.weibo_layout);
        TextView tv_dismiss = (TextView) dialog.findViewById(R.id.tv_dismiss);
        //微聊分享
        weiliao_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //微信分享
        weixin_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //朋友圈分享
        pengyouquan_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //微博分享
        weibo_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //取消
        tv_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    class LiebiaoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
        }
    }
}
