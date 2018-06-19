package com.example.administrator.japanhouse.fragment.comment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.example.administrator.japanhouse.im.ImManager;
import com.example.administrator.japanhouse.more.GaoErFuMoreActivity;
import com.example.administrator.japanhouse.presenter.HouseLogPresenter;
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

import static com.example.administrator.japanhouse.R.id.tv_price;

public class GaoerfuDetailsActivity extends BaseActivity {
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
    @BindView(R.id.tv_See_More)
    TextView tv_See_More;
    @BindView(R.id.tv_details_name)
    TextView tvDetailsName;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_details_area)
    TextView tvDetailsArea;
    @BindView(R.id.tv_details_location)
    TextView tvDetailsLocation;
    @BindView(R.id.tv_details_manager_head)
    CircleImageView tvDetailsManagerHead;
    @BindView(R.id.tv_details_manager_name)
    TextView tvDetailsManagerName;
    @BindView(R.id.gaoerfu_wl)
    TextView gaoerfuWl;
    @BindView(R.id.activity_lishi_new_house)
    RelativeLayout activityLishiNewHouse;
    @BindView(R.id.tv_details_manager_phone)
    TextView tvDetailsManagerPhone;
    private int mDistanceY;
    private LoveAdapter loveAdapter;
    private List<String> mList = new ArrayList();
    private List<Fragment> mBaseFragmentList = new ArrayList<>();
    private FragmentManager fm;
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
    private ShangYeDetailsBean.DatasBean.HwdcBrokerBean hwdcBroker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //拉伸图片覆盖标题栏
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar(false);
        setContentView(R.layout.activity_gaoerfu_details);
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
        new HouseLogPresenter(this).setHouseLog("5",houseId,"1");
        String city = CacheUtils.get(Constants.COUNTRY);
        if (city != null && city.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        HttpParams params = new HttpParams();
        params.put("hId", houseId);
        params.put("token", token);
        params.put("htype", 1);
        OkGo.<ShangYeDetailsBean>post(MyUrls.BASEURL + "/app/realestate/realestateinfo")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<ShangYeDetailsBean>(this, ShangYeDetailsBean.class) {
                    @Override
                    public void onSuccess(Response<ShangYeDetailsBean> response) {
                        int code = response.code();
                        ShangYeDetailsBean SydcListBean = response.body();
                        datas = SydcListBean.getDatas();
                        hwdcBroker = datas.getHwdcBroker();
                        tvDetailsName.setText(isJa ? datas.getTitleJpn() : datas.getTitleCn());
                        tvPrice.setText(isJa ? datas.getSellingPriceJpn() : datas.getSellingPriceCn());
                        tvDetailsArea.setText(isJa ? datas.getAreaJpn() : datas.getAreaCn());
                        tvDetailsLocation.setText(isJa ? datas.getSpecificLocationJpn() : datas.getSpecificLocationCn());
                        tvDetailsManagerName.setText(hwdcBroker.getBrokerName());
                        Glide.with(GaoerfuDetailsActivity.this).load(hwdcBroker.getPic() + "").into(tvDetailsManagerHead);
                        isSc = datas.getIsSc();
                        if (isSc == 0) {//收藏
                            isStart = true;
                            imgStart.setImageResource(R.drawable.shoucang2);
                        } else {//未收藏
                            isStart = false;
                            imgStart.setImageResource(R.drawable.shoucang);
                        }
                        initViewPager();

                    }

                });
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
        String str2 = realEstateImgs.replace("", "");//去掉所用空格
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
        findViewById(R.id.gaoerfu_wl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hwdcBroker != null) {
                    ImManager.enterChatDetails(GaoerfuDetailsActivity.this, hwdcBroker.getId() + "", hwdcBroker.getBrokerName(), hwdcBroker.getPic());
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
        params.put("hType", 1);
        params.put("pageNo", "1");
        params.put("cId", "2");
        OkGo.<SydcListBean>post(MyUrls.BASEURL + "/app/realestate/searchlist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SydcListBean>(GaoerfuDetailsActivity.this, SydcListBean.class) {
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
                                Intent intent = new Intent(GaoerfuDetailsActivity.this, GaoerfuDetailsActivity.class);
                                intent.putExtra("houseId", SydcListBean.getDatas().get(position).getId() + "");
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
            helper.setText(R.id.tv_house_name, isJa ? item.getTitleJpn() : item.getTitleCn());
            helper.setText(R.id.tv_house_address, isJa ? item.getSpecificLocationJpn() : item.getSpecificLocationCn());
//            helper.setText(R.id.tv_house_room,isJa?item.getDoorModelJpn():item.getDoorModelCn());
            helper.setVisible(R.id.tv_house_room, false);
            helper.setText(R.id.tv_house_area, isJa ? item.getAreaJpn() : item.getAreaCn());
            helper.setText(tv_price, isJa ? item.getSellingPriceJpn() : item.getSellingPriceCn());
            Glide.with(GaoerfuDetailsActivity.this).load(item.getRealEstateImgs()).into((ImageView) helper.getView(R.id.img_house));
        }
    }


    private void ShowCallDialog(final String tel) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.call_layout)
                .setPaddingdp(0, 10, 0, 10)
                .setGravity(Gravity.CENTER)
                .setAnimation(R.style.bottom_tab_style)
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                .isOnTouchCanceled(false)
                .builder();
        dialog.show();
        TextView text_sure = dialog.getView(R.id.text_sure);
        final TextView tv_content = dialog.getView(R.id.tv_content);
        tv_content.setText(tel);
        TextView text_pause = dialog.getView(R.id.text_pause);

        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent dialIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + tel));
                startActivity(dialIntent);
            }
        });

        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //头部 添加相应地区
    private final static String BAIDU_HEAD = "baidumap://map/direction?region=0";
    //起点的经纬度
    private final static String BAIDU_ORIGIN = "&origin=";
    //终点的经纬度
    private final static String BAIDU_DESTINATION = "&destination=";
    //路线规划方式
    private final static String BAIDU_MODE = "&mode=walking";
    //百度地图的包名
    private final static String BAIDU_PKG = "com.baidu.BaiduMap";


    /**
     * 检测地图应用是否安装
     *
     * @param context
     * @param packagename
     * @return
     */
    public boolean checkMapAppsIsExist(Context context, String packagename) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (Exception e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }

    @OnClick({R.id.img_share, R.id.img_start, R.id.back_img, R.id.tv_See_More,R.id.tv_details_manager_phone,R.id.tv_details_location})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_share:
                showDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.tv_details_manager_phone:
                ShowCallDialog(hwdcBroker.getPhone() + "");
                break;
            case R.id.tv_details_location:
                //检测地图是否安装和唤起
                if (checkMapAppsIsExist(GaoerfuDetailsActivity.this, BAIDU_PKG)) {
                    Toast.makeText(GaoerfuDetailsActivity.this, "百度地图已经安装", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setData(Uri.parse(BAIDU_HEAD + BAIDU_ORIGIN + "35.68"
                            + "," + "139.75" + BAIDU_DESTINATION + datas.getLatitude() + "," + datas.getLongitude()
                            + BAIDU_MODE));
                    startActivity(intent);
                } else {
                    Toast.makeText(GaoerfuDetailsActivity.this, "百度地图未安装或版本过低", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.img_start:
                if (MyUtils.isLogin(this)) {
                    if (!isStart) {
                        initStart();
                        isStart = true;
                    } else {
                        initUnStart();
                        isStart = false;
                    }
                } else {
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    MyUtils.StartLoginActivity(this);
                }
                break;
            case R.id.back_img:
                finish();
                break;
            case R.id.tv_See_More:
                Intent intent = new Intent(GaoerfuDetailsActivity.this, GaoErFuMoreActivity.class);
                intent.putExtra("datas", datas);
                startActivity(intent);
                break;
        }
    }


    //收藏
    private void initStart() {
        HttpParams params = new HttpParams();
        params.put("hType", 5);//房源类型 0二手房 1新房 2租房 3土地 4别墅 5商业地产 6中国房源 7海外房源 8找团地
        params.put("token", token);//用户登录标识
        params.put("shType", "1");//房源类型下的小类型 例：租房下的二层公寓传3 租房（0办公室出租 1商铺出租 2别墅 3二层公寓 4学生公寓详情 5多层公寓详情） 商业地产（0酒店 1高尔夫球场 2写字楼 3商铺）
        params.put("hId", houseId);
        OkGo.<SuccessBean>post(MyUrls.BASEURL + "/app/collectionhouse/insertcollectionhouse")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SuccessBean>(GaoerfuDetailsActivity.this, SuccessBean.class) {
                    @Override
                    public void onSuccess(Response<SuccessBean> response) {
                        int code = response.code();
                        final SuccessBean oldHouseListBean = response.body();
                        String code1 = oldHouseListBean.getCode();
                        if (code1.equals("200")) {
                            imgStart.setImageResource(R.drawable.shoucang2);
                            Toast.makeText(GaoerfuDetailsActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(GaoerfuDetailsActivity.this, code1, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    //取消收藏
    private void initUnStart() {
        HttpParams params = new HttpParams();
        params.put("hType", 5);//房源类型 0二手房 1新房 2租房 3土地 4别墅 5商业地产 6中国房源 7海外房源 8找团地
        params.put("token", token);//用户登录标识
        params.put("shType", "1");//房源类型下的小类型 例：租房下的二层公寓传3 租房（0办公室出租 1商铺出租 2别墅 3二层公寓 4学生公寓详情 5多层公寓详情） 商业地产（0酒店 1高尔夫球场 2写字楼 3商铺）
        params.put("hId", houseId);
        OkGo.<SuccessBean>post(MyUrls.BASEURL + "/app/collectionhouse/deletecollectionhouse")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SuccessBean>(GaoerfuDetailsActivity.this, SuccessBean.class) {
                    @Override
                    public void onSuccess(Response<SuccessBean> response) {
                        int code = response.code();
                        final SuccessBean oldHouseListBean = response.body();
                        String code1 = oldHouseListBean.getCode();
                        if (code1.equals("200")) {
                            imgStart.setImageResource(R.drawable.shoucang);
                            Toast.makeText(GaoerfuDetailsActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(GaoerfuDetailsActivity.this, code1, Toast.LENGTH_SHORT).show();
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
