package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.activity.FreeApartmentActivity;
import com.example.administrator.japanhouse.activity.OwnerActivity;
import com.example.administrator.japanhouse.adapter.MyGridViewAdpter;
import com.example.administrator.japanhouse.adapter.MyViewPagerAdapter;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.bean.HomeItemBean;
import com.example.administrator.japanhouse.fragment.chat.ManagerActivity;
import com.example.administrator.japanhouse.fragment.comment.NewHousedetailsActivity;
import com.example.administrator.japanhouse.fragment.comment.OldHousedetailsActivity;
import com.example.administrator.japanhouse.fragment.comment.TudidetailsActivity;
import com.example.administrator.japanhouse.fragment.comment.ZuHousedetailsActivity;
import com.example.administrator.japanhouse.fragment.home.ui.activity.Buyhouse_Baike_Activity;
import com.example.administrator.japanhouse.fragment.home.ui.activity.Daikuan_Activity;
import com.example.administrator.japanhouse.fragment.home.ui.activity.QuestionActivity;
import com.example.administrator.japanhouse.fragment.home.ui.activity.ToutiaoActivity;
import com.example.administrator.japanhouse.fragment.home.ui.activity.ZhinengActivity;
import com.example.administrator.japanhouse.utils.BannerUtils;
import com.example.administrator.japanhouse.view.RatingBarView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.administrator.japanhouse.R.id.re_top_bg;

/**
 * Created by Administrator on 2018/4/8.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.location_tv)
    TextView locationTv;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.map_tv)
    TextView mapTv;
    @BindView(R.id.jrdk_tv)
    TextView jrdkTv;
    @BindView(R.id.gfbk_tv)
    TextView gfbkTv;
    @BindView(R.id.fcwd_tv)
    TextView fcwdTv;
    @BindView(R.id.znmf_tv)
    TextView znmfTv;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.points)
    LinearLayout points;
    @BindView(R.id.tantan_tv)
    TextView tantanTv;
    @BindView(R.id.banner)
    Banner banner;
    Unbinder unbinder;
    @BindView(R.id.tjxf_more_tv)
    TextView tjxfMoreTv;
    @BindView(R.id.tjxf_recycler)
    RecyclerView tjxfRecycler;
    Unbinder unbinder1;
    @BindView(R.id.tjesf_more_tv)
    TextView tjesfMoreTv;
    @BindView(R.id.tjesf_recycler)
    RecyclerView tjesfRecycler;
    @BindView(R.id.tjyxjjr_more_tv)
    TextView tjyxjjrMoreTv;
    @BindView(R.id.tjyxjjr_recycler)
    RecyclerView tjyxjjrRecycler;
    @BindView(R.id.tjzf_more_tv)
    TextView tjzfMoreTv;
    @BindView(R.id.tjzf_recycler)
    RecyclerView tjzfRecycler;
    @BindView(R.id.tjtd_more_tv)
    TextView tjtdMoreTv;
    @BindView(R.id.tjtd_recycler)
    RecyclerView tjtdRecycler;
    @BindView(R.id.cnxh_recycler)
    RecyclerView cnxhRecycler;
    @BindView(R.id.view_011)
    LinearLayout view011;
    @BindView(R.id.srcollview)
    NestedScrollView scrollView;
    Unbinder unbinder2;
    @BindView(re_top_bg)
    RelativeLayout reTopBg;
    private int mDistanceY;
    private int totalPage; //总的页数
    private int mPageSize = 10; //每页显示的最大的数量
    private ImageView[] ivPoints;//小圆点图片的集合
    private List<HomeItemBean> homeItemBeanList;//总的数据源
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中

    private int[] itemPic = {R.drawable.home_ershoufang_iv, R.drawable.home_xinfang_iv, R.drawable.home_tudi_iv,
            R.drawable.home_zufang_iv, R.drawable.home_shangyedichan_iv, R.drawable.home_zhongguofangyuan_iv,
            R.drawable.home_fangjiaditu_iv, R.drawable.home_zhaoxiaoqu_iv, R.drawable.home_woshiyezhu_iv,
            R.drawable.home_mianfeikanfang_iv, R.drawable.home_haiwaidichan_iv};
    private List<String> itemTjxfList;
    private Intent intent;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        initViewData(view);
        initScroll();
        return view;
    }

    private void initViewData(View view) {
        String[] itemName = {mActivity.getResources().getString(R.string.old_house), mActivity.getResources().getString(R.string.new_house),
                mActivity.getResources().getString(R.string.tudi), mActivity.getResources().getString(R.string.zu_house),
                mActivity.getResources().getString(R.string.shangyedichan), mActivity.getResources().getString(R.string.zhongguofangyuan), mActivity.getResources().getString(R.string.fangjiaditu),
                mActivity.getResources().getString(R.string.zhaoxiaoqu), mActivity.getResources().getString(R.string.woshiyezhu), mActivity.getResources().getString(R.string.mianfeikanfang),
                mActivity.getResources().getString(R.string.haiwaidichan)};
        //-----item导航栏-----
        homeItemBeanList = new ArrayList<>();
        for (int i = 0; i < itemName.length; i++) {
            homeItemBeanList.add(new HomeItemBean(itemName[i], itemPic[i]));
        }
        //总的页数向上取整
        totalPage = (int) Math.ceil(homeItemBeanList.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) View.inflate(mContext, R.layout.item_gridview, null);
            gridView.setAdapter(new MyGridViewAdpter(mContext, homeItemBeanList, i, mPageSize));
            //添加item点击监听
            final int finalI = i;
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    // TODO Auto-generated method stub
                    Object obj = gridView.getItemAtPosition(position);
                    if (obj != null && obj instanceof HomeItemBean) {
                        switch (position) {
                            case 0:
                                if (finalI == 0) {//二手房
                                    intent = new Intent(getActivity(), ErshoufangActiviy.class);
                                    getActivity().startActivityForResult(intent, 1);
                                } else {//海外地产
                                    startActivity(new Intent(mContext, HaiWaiActivity.class));
                                }
                                break;
                            case 1://新房
                                intent = new Intent(getActivity(), NewHouseActivity.class);
                                getActivity().startActivityForResult(intent, 1);
                                break;
                            case 2://土地
                                intent = new Intent(getActivity(), TudiActivity.class);
                                getActivity().startActivityForResult(intent, 1);
                                break;
                            case 3://租房
                                intent = new Intent(getActivity(), ZufangActivity.class);
                                getActivity().startActivityForResult(intent, 1);
                                break;
                            case 4://商业地产
                                startActivity(new Intent(mContext, BusinessDichanActivity.class));
                                break;
                            case 5://中国房源
                                startActivity(new Intent(mContext, ChineseFangyuanActivity.class));
                                break;
                            case 6://房价地图
                                startActivity(new Intent(mContext, FangjiadituActivity.class));
                                break;
                            case 7://找小区
                                intent = new Intent(getActivity(), ZhaoxiaoquActivity.class);
                                getActivity().startActivityForResult(intent, 1);
                                break;
                            case 8://我是业主
//                                OwnerActivity.invoke(getActivity());
                                Intent intent = new Intent(getActivity(), OwnerActivity.class);
                                getActivity().startActivityForResult(intent, 1);
                                break;
                            case 9://免费看房
                                Intent intent1 = new Intent(getActivity(), FreeApartmentActivity.class);
                                getActivity().startActivityForResult(intent1, 1);
                                break;
                        }
                    }
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        //设置ViewPager适配器
        viewpager.setAdapter(new MyViewPagerAdapter(viewPagerList));
        //添加小圆点
        ivPoints = new ImageView[totalPage];
        for (int i = 0; i < totalPage; i++) {
            //循坏加入点点图片组
            ivPoints[i] = new ImageView(mContext);
            if (i == 0) {
                ivPoints[i].setImageResource(R.drawable.dot_selected);
            } else {
                ivPoints[i].setImageResource(R.drawable.dot_unselected);
            }
            ivPoints[i].setPadding(8, 8, 8, 8);
            points.addView(ivPoints[i]);
        }
        //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
        viewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //currentPage = position;
                for (int i = 0; i < totalPage; i++) {
                    if (i == position) {
                        ivPoints[i].setImageResource(R.drawable.dot_selected);
                    } else {
                        ivPoints[i].setImageResource(R.drawable.dot_unselected);
                    }
                }
            }
        });
        //-----item导航栏-----

        //-----城市探探-----
        tantanTv.requestFocus();
        //-----城市探探-----

        //-----banner-----
        List<Integer> baaaneList = new ArrayList<>();
        baaaneList.add(R.drawable.home_banner_iv);
        baaaneList.add(R.drawable.home_banner_iv);
        baaaneList.add(R.drawable.home_banner_iv);
        BannerUtils.startBanner(banner, baaaneList);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                TUtils.showShort(mContext, "点击了Banner" + position);
                startActivity(new Intent(mContext, NewHousedetailsActivity.class));
            }
        });
        //-----banner-----

        //-----推荐新房-----
        List<String> tjxfList = new ArrayList<>();
        tjxfList.add("");
        tjxfList.add("");
        itemTjxfList = new ArrayList<>();
        itemTjxfList.add("");
        itemTjxfList.add("");
        itemTjxfList.add("");
        itemTjxfList.add("");
        tjxfRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        tjxfRecycler.setNestedScrollingEnabled(false);
        TjxfAdapter adapter = new TjxfAdapter(R.layout.item_home_tjxf, tjxfList);
        tjxfRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, NewHousedetailsActivity.class));
            }
        });
        //-----推荐新房-----

        //-----推荐二手房-----
        List<String> tjesfList = new ArrayList<>();
        tjesfList.add("");
        tjesfList.add("");
        tjesfList.add("");
        tjesfRecycler.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        tjesfRecycler.setLayoutManager(linearLayoutManager);
        TjesfAdapter tjesfAdapter = new TjesfAdapter(R.layout.item_tjesf_layout, tjesfList);
        tjesfRecycler.setAdapter(tjesfAdapter);
        tjesfAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, OldHousedetailsActivity.class));
            }
        });
        //-----推荐二手房-----

        //-----推荐优秀经纪人-----
        List<String> tjyxjjrList = new ArrayList<>();
        tjyxjjrList.add("");
        tjyxjjrList.add("");
        tjyxjjrList.add("");
        tjyxjjrList.add("");
        tjyxjjrList.add("");
        tjyxjjrRecycler.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        tjyxjjrRecycler.setLayoutManager(linearLayoutManager1);
        TjyxjjrAdapter tjyxjjrAdapter = new TjyxjjrAdapter(R.layout.item_tjyxjjr_layout, tjyxjjrList);
        tjyxjjrRecycler.setAdapter(tjyxjjrAdapter);
        tjyxjjrAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, ManagerActivity.class));
            }
        });
        //-----推荐优秀经纪人-----

        //-----推荐租房-----
        tjzfRecycler.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        tjzfRecycler.setLayoutManager(linearLayoutManager2);
        TjzfAdapter tjzfAdapter = new TjzfAdapter(R.layout.item_tjzf_layout, tjesfList);
        tjzfRecycler.setAdapter(tjzfAdapter);
        tjzfAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, ZuHousedetailsActivity.class));
            }
        });
        //-----推荐租房-----

        //-----推荐土地-----
        tjtdRecycler.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(mContext);
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        tjtdRecycler.setLayoutManager(linearLayoutManager3);
        TjtdAdapter tjtdAdapter = new TjtdAdapter(R.layout.item_tjtd_layout, tjesfList);
        tjtdRecycler.setAdapter(tjtdAdapter);
        tjtdAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, TudidetailsActivity.class));
            }
        });
        //-----推荐土地-----

        //-----猜你喜欢-----
        cnxhRecycler.setNestedScrollingEnabled(false);
        cnxhRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        CnxhAdapter cnxhAdapter = new CnxhAdapter(R.layout.item_cnxh_layout, tjyxjjrList);
        cnxhRecycler.setAdapter(cnxhAdapter);
        cnxhAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, NewHousedetailsActivity.class));
            }
        });
        //-----猜你喜欢-----
    }

    @Override
    protected void initLazyData() {

    }

    private void initScroll() {
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
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
                    reTopBg.setBackgroundColor(Color.argb((int) alpha, 199, 151, 127));
                } else {
                    //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
                    //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
                    //将标题栏的颜色设置为完全不透明状态
                    reTopBg.setBackgroundResource(R.color.shihuangse);
                }
            }
        });
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        tantanTv.requestFocus();//解决切换fragment之后回来不滚动的问题
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder2 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    private class TjxfAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public TjxfAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            RecyclerView itemTjxfRecycler = helper.getView(R.id.item_tjxf_recycler);
            itemTjxfRecycler.setNestedScrollingEnabled(false);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            itemTjxfRecycler.setLayoutManager(linearLayoutManager);
            ItemTjxfAdapter itemTjxfAdapter = new ItemTjxfAdapter(R.layout.item_tjxf_item_layout, itemTjxfList);
            itemTjxfRecycler.setAdapter(itemTjxfAdapter);
            itemTjxfAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    startActivity(new Intent(mContext, NewHousedetailsActivity.class));
                }
            });
        }
    }

    private class ItemTjxfAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ItemTjxfAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    private class TjesfAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public TjesfAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    private class TjyxjjrAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public TjyxjjrAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            RatingBarView ratingBarView = helper.getView(R.id.ratingBarView);
            ratingBarView.setRatingCount(5);//设置RatingBarView总数
            ratingBarView.setSelectedCount(2);//设置RatingBarView选中数
            ratingBarView.setSelectedIconResId(R.drawable.start_check);//设置RatingBarView选中的图片id
            ratingBarView.setNormalIconResId(R.drawable.start_nocheck);//设置RatingBarView正常图片id
            ratingBarView.setClickable(false);//设置RatingBarView是否可点击
            ratingBarView.setChildPadding(2);//设置RatingBarView的子view的padding
            ratingBarView.setChildMargin(2);//设置RatingBarView的子view左右之间的margin
            ratingBarView.setChildDimension(22);//设置RatingBarView的子view的宽高尺寸
            ratingBarView.setOnRatingBarClickListener(new RatingBarView.RatingBarViewClickListener() {
                @Override
                public void onRatingBarClick(LinearLayout parent, View childView, int position) {
                    Log.e("tag", String.valueOf(childView instanceof ImageView) + "," + position);
                }
            });
        }
    }

    private class TjzfAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public TjzfAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    private class TjtdAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public TjtdAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    private class CnxhAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public CnxhAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.location_tv, R.id.search_tv, R.id.map_tv, R.id.jrdk_tv, R.id.gfbk_tv,
            R.id.fcwd_tv, R.id.znmf_tv, R.id.view_011, R.id.tjxf_more_tv, R.id.tjesf_more_tv,
            R.id.tjyxjjr_more_tv, R.id.tjzf_more_tv, R.id.tjtd_more_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.location_tv:
                startActivity(new Intent(mContext, LocationActivity.class));
                break;
            case R.id.search_tv:
                startActivity(new Intent(mContext, HomeSearchActivity.class));
                break;
            case R.id.map_tv:
                Intent intent = new Intent(mContext, HomeMapActivity.class);
                getActivity().startActivityForResult(intent, 1);
                break;
            case R.id.jrdk_tv:
                startActivity(new Intent(mContext, Daikuan_Activity.class));
                break;
            case R.id.gfbk_tv:
                startActivity(new Intent(mContext, Buyhouse_Baike_Activity.class));
                break;
            case R.id.fcwd_tv:
                startActivity(new Intent(mContext, QuestionActivity.class));
                break;
            case R.id.znmf_tv:
                startActivity(new Intent(mContext, ZhinengActivity.class));
                break;
            case R.id.view_011:
                startActivity(new Intent(mContext, ToutiaoActivity.class));
                break;
            case R.id.tjxf_more_tv:
                startActivity(new Intent(mContext, NewHouseActivity.class));
                break;
            case R.id.tjesf_more_tv:
                startActivity(new Intent(mContext, ErshoufangActiviy.class));
                break;
            case R.id.tjyxjjr_more_tv:
                startActivity(new Intent(mContext, JjrLiebiaoActivity.class));
                break;
            case R.id.tjzf_more_tv:
                startActivity(new Intent(mContext, ZufangActivity.class));
                break;
            case R.id.tjtd_more_tv:
                startActivity(new Intent(mContext, TudiActivity.class));
                break;
        }
    }
}
