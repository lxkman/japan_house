package com.example.administrator.japanhouse.fragment.comment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.administrator.japanhouse.bean.ShangYeDetailsBean;
import com.example.administrator.japanhouse.bean.SuccessBean;
import com.example.administrator.japanhouse.bean.SydcListBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.im.DetailsExtensionModule;
import com.example.administrator.japanhouse.more.ShangPuMoreActivity;
import com.example.administrator.japanhouse.utils.CacheUtils;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;

public class ShangpuDetailsActivity extends BaseActivity {

    @BindView(R.id.vp_vidio)
    ViewPager vpVidio;
    @BindView(R.id.tv_to_num)
    TextView tvToNum;
    @BindView(R.id.tv_all_num)
    TextView tvAllNum;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.img_start)
    ImageView imgStart;
    @BindView(R.id.love_Recycler)
    RecyclerView loveRecycler;
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.tv_suiyi)
    TextView tvSuiyi;
    @BindView(R.id.mScrollView)
    NestedScrollView mScrollView;
    @BindView(R.id.re_top_bg)
    RelativeLayout re_top_bg;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_See_More)
    TextView tvSeeMore;
    @BindView(R.id.tv_details_name)
    TextView tvDetailsName;
    @BindView(R.id.tv_details_area)
    TextView tvDetailsArea;
    @BindView(R.id.tv_details_shoumaileixing)
    TextView tvDetailsShoumaileixing;
    @BindView(R.id.tv_details_location)
    TextView tvDetailsLocation;
    @BindView(R.id.shangpu_wl)
    TextView shangpuWl;
    @BindView(R.id.tv_details_manager_head)
    CircleImageView tvDetailsManagerHead;
    @BindView(R.id.tv_details_manager_name)
    TextView tvDetailsManagerName;
    private int mDistanceY;
    private LoveAdapter loveAdapter;
    private List<String> mList = new ArrayList();
    private List<Fragment> mBaseFragmentList = new ArrayList<>();
    private FragmentManager fm;
    private MyAdapter myAdapter;
    private boolean isJa;
    private String houseId;
    private ShangYeDetailsBean.DatasBean datas;
    private String token;
    private int isSc;
    private boolean isStart;
    private List<View> mBannerList = new ArrayList<>();
    private List<String> bannerlist;
    private List<String> mUrlList = new ArrayList();
    private String realEstateImgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //拉伸图片覆盖标题栏
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar(false);
        setContentView(R.layout.activity_shangpu_details);
        ButterKnife.bind(this);

        //户型图
        initData();
        //猜你喜欢
        initLoveRecycler();
        initScroll();
        initDetailsNet();


    }

    private void initDetailsNet() {
        token = SharedPreferencesUtils.getInstace(this).getStringPreference("token", "");
        houseId = getIntent().getStringExtra("houseId");
        String city = CacheUtils.get(Constants.COUNTRY);
        if (city != null && city.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        HttpParams params = new HttpParams();
        params.put("hId", houseId);
        params.put("token",token);
        params.put("htype",3);
        OkGo.<ShangYeDetailsBean>post(MyUrls.BASEURL + "/app/realestate/realestateinfo")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<ShangYeDetailsBean>(this, ShangYeDetailsBean.class) {
                    @Override
                    public void onSuccess(Response<ShangYeDetailsBean> response) {
                        int code = response.code();
                        ShangYeDetailsBean SydcListBean = response.body();
                        datas = SydcListBean.getDatas();
                        ShangYeDetailsBean.DatasBean.HwdcBrokerBean hwdcBroker = datas.getHwdcBroker();
                        tvDetailsName.setText(isJa ? datas.getTitleJpn() : datas.getTitleCn());
                        tv_price.setText(isJa ? datas.getSellingPriceJpn() : datas.getSellingPriceCn());
                        tvDetailsArea.setText(isJa ? datas.getAreaJpn() : datas.getAreaCn());
                        tvDetailsLocation.setText(isJa ? datas.getSpecificLocationJpn() : datas.getSpecificLocationCn());
                        tvDetailsShoumaileixing.setText(isJa ? datas.getLeaseTypeJpn() : datas.getLeaseTypeCn());
                        tvDetailsManagerName.setText(hwdcBroker.getBrokerName());
                        Glide.with(ShangpuDetailsActivity.this).load(hwdcBroker.getPic() + "").into(tvDetailsManagerHead);
                        isSc = datas.getIsSc();
                        if (isSc==0){//收藏
                            isStart=true;
                            imgStart.setImageResource(R.drawable.shoucang2);
                        }else {//未收藏
                            isStart=false;
                            imgStart.setImageResource(R.drawable.shoucang);
                        }
                        initViewPager();
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

    private void initViewPager() {
        realEstateImgs = datas.getRealEstateImgs();
        String str2=realEstateImgs.replace("", "");//去掉所用空格
        bannerlist = Arrays.asList(str2.split(","));//截取逗号分开的数据并添加到list中
        if (mBannerList.size() <= 0) {
            if (datas.getVideoUrls() != null) {
                if (datas.getVideoUrls().equals("")) {
                    for (int i = 0; i < bannerlist.size(); i++) {
                        mUrlList.add(bannerlist.get(i) + "");
                    }
                } else {
                    mUrlList.add(datas.getVideoImgs());
                    for (int i = 0; i < bannerlist.size(); i++) {
                        mUrlList.add(bannerlist.get(i) + "");
                    }
                }
            }
            for (int i = 0; i < mUrlList.size(); i++) {
                View inflate = View.inflate(mContext, R.layout.details_banner_layout, null);
                ImageView img_banner = (ImageView) inflate.findViewById(R.id.img_banner);
                ImageView imgStartVideo = (ImageView) inflate.findViewById(R.id.img_start_video);
                RelativeLayout rela_layout = (RelativeLayout) inflate.findViewById(R.id.rela_layout);
                Glide.with(this).load(mUrlList.get(i)).into(img_banner);
                mBannerList.add(inflate);
                if (i == 0 && !datas.getVideoUrls().equals("")) {
                    imgStartVideo.setVisibility(View.VISIBLE);
                } else {
                    imgStartVideo.setVisibility(View.GONE);
                }
                final int finalI = i;
                rela_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (finalI == 0 && !datas.getVideoUrls().equals("")) {
                            Intent intent = new Intent(mContext, VideoDetailsActivity.class);
                            intent.putExtra("VideoUrl", datas.getVideoUrls() + "");
                            intent.putExtra("VideoImg", datas.getVideoImgs() + "");
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(mContext, BannerDetailsActivity.class);
                            intent.putExtra("bannerlist", (Serializable) bannerlist);
                            if (datas.getVideoUrls().equals("")) {
                                intent.putExtra("position", finalI + "");
                            } else {
                                intent.putExtra("position", (finalI - 1) + "");
                            }
                            startActivity(intent);
                        }
                    }
                });

            }


        }
        tvAllNum.setText(mBannerList.size() + "");
        vpVidio.setAdapter(adapter);
        vpVidio.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {

                tvToNum.setText((position + 1) + "");
                if (position == 1) {

                } else if (position == 0) {
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //需要给ViewPager设置适配器
    PagerAdapter adapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        //有多少个切换页
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mBannerList.size();
        }

        //对超出范围的资源进行销毁
        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            // TODO Auto-generated method stub
            container.removeView(mBannerList.get(position));
        }

        //对显示的资源进行初始化
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            container.addView(mBannerList.get(position));
            return mBannerList.get(position);
        }

    };

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
        findViewById(R.id.shangpu_wl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.getInstace(ShangpuDetailsActivity.this).setStringPreference(Constants.CHAT, Constants.CHAT_DETAILS);
                setMyExtensionModule();
                if (RongIM.getInstance() != null) {
                    Log.e("MainActivity", "创建单聊");
                    RongIM.getInstance().startPrivateChat(ShangpuDetailsActivity.this, "123456", getString(R.string.act_chat_title));
                }
            }
        });

    }

    private void initLoveRecycler() {
        HttpParams params = new HttpParams();
        if (isJa) {
            params.put("languageType", 1);
        } else {
            params.put("languageType", 0);
        }
        params.put("hType", 3);
        params.put("pageNo","1");
        params.put("cId","2");
        OkGo.<SydcListBean>post(MyUrls.BASEURL + "/app/realestate/searchlist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SydcListBean>(ShangpuDetailsActivity.this, SydcListBean.class) {
                    @Override
                    public void onSuccess(Response<SydcListBean> response) {
                        int code = response.code();
                        final SydcListBean SydcListBean = response.body();
                        if (SydcListBean == null) {
                            return;
                        }
                        List<SydcListBean.DatasEntity> datas = SydcListBean.getDatas();
                        if (loveAdapter == null) {
                            loveAdapter = new LoveAdapter(R.layout.item_zuijin, datas);
                        }
                        loveRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        loveRecycler.setNestedScrollingEnabled(false);
                        loveRecycler.setAdapter(loveAdapter);
                        loveAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(ShangpuDetailsActivity.this, ShangpuDetailsActivity.class);
                                intent.putExtra("houseId",SydcListBean.getDatas().get(position).getId()+"");
                                startActivity(intent);
                            }
                        });
                    }
                });
    }
    class LoveAdapter extends BaseQuickAdapter<SydcListBean.DatasEntity, BaseViewHolder> {

        public LoveAdapter(@LayoutRes int layoutResId, @Nullable List<SydcListBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SydcListBean.DatasEntity item) {
            helper.setText(R.id.tv_house_name,isJa?item.getTitleJpn():item.getTitleCn());
            helper.setText(R.id.tv_house_address,isJa?item.getSpecificLocationJpn():item.getSpecificLocationCn());
//            helper.setText(R.id.tv_house_room,isJa?item.getDoorModelJpn():item.getDoorModelCn());
            helper.setVisible(R.id.tv_house_room,false);
            helper.setText(R.id.tv_house_area,isJa?item.getAreaJpn():item.getAreaCn());
            helper.setText(R.id.tv_price,isJa?item.getSellingPriceJpn():item.getSellingPriceCn());
            Glide.with(ShangpuDetailsActivity.this).load(item.getRealEstateImgs()).into((ImageView) helper.getView(R.id.img_house));
        }
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

    @OnClick({R.id.img_share, R.id.img_start, R.id.back_img, R.id.tv_See_More})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_share:
                showDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.img_start:
                if (MyUtils.isLogin(this)){
                    if (!isStart) {
                        initStart();
                        isStart = true;
                    } else {
                        initUnStart();
                        isStart = false;
                    }
                }else {
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    MyUtils.StartLoginActivity(this);
                }
                break;
            case R.id.back_img:
                finish();
                break;
            case R.id.tv_See_More:
                Intent intent = new Intent(ShangpuDetailsActivity.this, ShangPuMoreActivity.class);
                intent.putExtra("datas",datas);
                startActivity(intent);
                break;
        }
    }
    //收藏
    private void initStart() {
        HttpParams params = new HttpParams();
        params.put("hType", 5);//房源类型 0二手房 1新房 2租房 3土地 4别墅 5商业地产 6中国房源 7海外房源 8找团地
        params.put("token", token);//用户登录标识
        params.put("shType", "3");//房源类型下的小类型 例：租房下的二层公寓传3 租房（0办公室出租 1商铺出租 2别墅 3二层公寓 4学生公寓详情 5多层公寓详情） 商业地产（0酒店 1高尔夫球场 2写字楼 3商铺）
        params.put("hId",houseId);
        OkGo.<SuccessBean>post(MyUrls.BASEURL + "/app/collectionhouse/insertcollectionhouse")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SuccessBean>(ShangpuDetailsActivity.this, SuccessBean.class) {
                    @Override
                    public void onSuccess(Response<SuccessBean> response) {
                        int code = response.code();
                        final SuccessBean oldHouseListBean = response.body();
                        String code1 = oldHouseListBean.getCode();
                        if (code1.equals("200")){
                            imgStart.setImageResource(R.drawable.shoucang2);
                            Toast.makeText(ShangpuDetailsActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ShangpuDetailsActivity.this, code1, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    //取消收藏
    private void initUnStart() {
        HttpParams params = new HttpParams();
        params.put("hType", 5);//房源类型 0二手房 1新房 2租房 3土地 4别墅 5商业地产 6中国房源 7海外房源 8找团地
        params.put("token", token);//用户登录标识
        params.put("shType", "3");//房源类型下的小类型 例：租房下的二层公寓传3 租房（0办公室出租 1商铺出租 2别墅 3二层公寓 4学生公寓详情 5多层公寓详情） 商业地产（0酒店 1高尔夫球场 2写字楼 3商铺）
        params.put("hId",houseId);
        OkGo.<SuccessBean>post(MyUrls.BASEURL + "/app/collectionhouse/deletecollectionhouse")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SuccessBean>(ShangpuDetailsActivity.this, SuccessBean.class) {
                    @Override
                    public void onSuccess(Response<SuccessBean> response) {
                        int code = response.code();
                        final SuccessBean oldHouseListBean = response.body();
                        String code1 = oldHouseListBean.getCode();
                        if (code1.equals("200")){
                            imgStart.setImageResource(R.drawable.shoucang);
                            Toast.makeText(ShangpuDetailsActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ShangpuDetailsActivity.this, code1, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

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

}
