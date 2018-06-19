package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.bean.MoreCheckBean;
import com.example.administrator.japanhouse.bean.OldHouseShaiXuanBean;
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.example.administrator.japanhouse.bean.QuYuBean;
import com.example.administrator.japanhouse.bean.SydcListBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.fragment.comment.GaoerfuDetailsActivity;
import com.example.administrator.japanhouse.fragment.comment.JiudianDetailsActivity;
import com.example.administrator.japanhouse.fragment.comment.ShangpuDetailsActivity;
import com.example.administrator.japanhouse.fragment.comment.XiezilouDetailsActivity;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.NetWorkUtils;
import com.example.administrator.japanhouse.utils.TUtils;
import com.example.administrator.japanhouse.view.MyFooter;
import com.example.administrator.japanhouse.view.MyHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.yyydjk.library.DropDownMenu;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SydcLiebiaoActivity extends BaseActivity implements MyItemClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.img_dingwei)
    ImageView imgDingwei;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    @BindView(R.id.search_tv)
    TextView searchTv;
    private List<View> popupViews = new ArrayList<>();
    private RecyclerView mrecycler;
    private TextView tvNoContent;
    private LiebiaoAdapter liebiaoAdapter;
    private int type;
    private int type2;
    private String houseType;
    private SpringView springview;
    private boolean isLoadMore;
    private String searchText = "";
    private int page = 1;
    private boolean isJa;
    private List<SydcListBean.DatasEntity> mDatas;
    private String mjId = "-2";
    private String sjId = "-2";
    private List<String> zidingyiPriceList = new ArrayList<>();
    private boolean isZiDingyiPrice;
    private List<OldHouseShaiXuanBean.DatasEntity.MianjiEntity> mianji;
    private List<OldHouseShaiXuanBean.DatasEntity.ShoujiaEntity> shoujia;
    private List<List<String>> mMoreSelectedBeanList = new ArrayList<>();
    private View fifthView;
    private String[] headers;
    private OldHouseShaiXuanBean.DatasEntity shaiXuanBeanDatas;
    private boolean isDitie;
    private List<String> quyuList = new ArrayList<>();
    private List<String> ditieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sydc_liebiao);
        ButterKnife.bind(this);
        searchText = getIntent().getStringExtra("searchText");
        if (!TextUtils.isEmpty(searchText)) {
            searchTv.setText(searchText);
        }

        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        springview.setHeader(new MyHeader(this));
        springview.setFooter(new MyFooter(this));
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                page = 1;
                initData();
                springview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                isLoadMore = true;
                page++;
                initData();
                springview.onFinishFreshAndLoad();
            }
        });
    }

    private void initView() {
        int type1 = getIntent().getIntExtra("type", 0);
        if (type1 == 0) {
            type = 3;
            type2 = 6;
        } else if (type1 == 1) {
            type = 2;
            type2 = 7;
        } else if (type1 == 2) {
            type = 1;
            type2 = 8;
        } else if (type1 == 3) {
            type = 0;
            type2 = 9;
        }
        houseType = getIntent().getStringExtra("houseType");
        fifthView = LayoutInflater.from(SydcLiebiaoActivity.this).inflate(R.layout.activity_main_view, null);
        mrecycler = (RecyclerView) fifthView.findViewById(R.id.mrecycler);
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        springview = (SpringView) fifthView.findViewById(R.id.springview);
        tvNoContent= (TextView) fifthView.findViewById(R.id.tv_noContent);
        headers = new String[]{getString(R.string.quyu), getString(R.string.lxkmianji),
                getString(R.string.shoujia), getString(R.string.gengduo)};

        HttpParams params = new HttpParams();
        params.put("hType", 1);
        params.put("shType", type2);
        OkGo.<OldHouseShaiXuanBean>post(MyUrls.BASEURL + "/app/twoscreening/selectallscree")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<OldHouseShaiXuanBean>(OldHouseShaiXuanBean.class) {
                    @Override
                    public void onSuccess(Response<OldHouseShaiXuanBean> response) {
                        int code = response.code();
                        OldHouseShaiXuanBean shaiXuanBean = response.body();
                        if (shaiXuanBean == null) {
                            return;
                        }
                        shaiXuanBeanDatas = shaiXuanBean.getDatas();
                        initShaiXuan();
                    }
                });
    }

    private void initShaiXuan() {
        HttpParams params = new HttpParams();
        params.put("cId", 2);
        OkGo.<QuYuBean>post(MyUrls.BASEURL + "/app/areamanage/selectareaandsubway")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<QuYuBean>(QuYuBean.class) {
                    @Override
                    public void onSuccess(Response<QuYuBean> response) {
                        QuYuBean body = response.body();
                        QuYuBean.DatasEntity datas = body.getDatas();
                        List<QuYuBean.DatasEntity.AreasEntity> areas = datas.getAreas();
                        List<QuYuBean.DatasEntity.SubwaylinesEntity> subwaylines = datas.getSubwaylines();
                        List<MoreCheckBean> quyuListBean = new ArrayList<MoreCheckBean>();
                        List<MoreCheckBean> ditieListBean = new ArrayList<MoreCheckBean>();
                        List<OneCheckBean> oneCheckBeanList1 = new ArrayList<OneCheckBean>();
                        oneCheckBeanList1.add(new OneCheckBean(true, getResources().getString(R.string.buxian)));
                        MoreCheckBean moreCheckBean1 = new MoreCheckBean(true, getResources().getString(R.string.buxian));
                        moreCheckBean1.setCheckBeanList(oneCheckBeanList1);
                        quyuListBean.add(moreCheckBean1);
                        ditieListBean.add(moreCheckBean1);
                        if (areas != null && areas.size() > 0) {
                            for (int i = 0; i < areas.size(); i++) {
                                QuYuBean.DatasEntity.AreasEntity areasEntity = areas.get(i);
                                if (areasEntity != null) {
                                    String administrationNameCn = areasEntity.getAdministrationNameCn();
                                    String administrationNameJpn = areasEntity.getAdministrationNameJpn();
                                    MoreCheckBean moreCheckBean = new MoreCheckBean();
                                    moreCheckBean.setName(isJa ? administrationNameJpn : administrationNameCn);
                                    moreCheckBean.setId(areasEntity.getId());
                                    List<QuYuBean.DatasEntity.AreasEntity.HwdcAreaManagesEntity> hwdcAreaManages = areasEntity.getHwdcAreaManages();
                                    List<OneCheckBean> oneCheckBeanList = new ArrayList<OneCheckBean>();
                                    oneCheckBeanList.add(new OneCheckBean(true,getResources().getString(R.string.buxian)));
                                    if (hwdcAreaManages != null && hwdcAreaManages.size() > 0) {
                                        for (int i1 = 0; i1 < hwdcAreaManages.size(); i1++) {
                                            int id = hwdcAreaManages.get(i1).getId();
                                            String areaNameCn = hwdcAreaManages.get(i1).getAreaNameCn();
                                            String areaNameJpn = hwdcAreaManages.get(i1).getAreaNameJpn();
                                            OneCheckBean oneCheckBean = new OneCheckBean(false, isJa ? areaNameJpn : areaNameCn, id);
                                            oneCheckBeanList.add(oneCheckBean);
                                        }
                                    }
                                    moreCheckBean.setCheckBeanList(oneCheckBeanList);
                                    quyuListBean.add(moreCheckBean);
                                }
                            }
                        }
                        if (subwaylines != null && subwaylines.size() > 0) {
                            for (int i = 0; i < subwaylines.size(); i++) {
                                QuYuBean.DatasEntity.SubwaylinesEntity subwaylinesEntity = subwaylines.get(i);
                                if (subwaylinesEntity != null) {
                                    String lineNameCn = subwaylinesEntity.getLineNameCn();
                                    String lineNameJpn = subwaylinesEntity.getLineNameJpn();
                                    MoreCheckBean moreCheckBean = new MoreCheckBean();
                                    moreCheckBean.setName(isJa ? lineNameJpn : lineNameCn);
                                    moreCheckBean.setId(subwaylinesEntity.getId());
                                    List<QuYuBean.DatasEntity.SubwaylinesEntity.SubwayStationsEntity> subwayStations = subwaylinesEntity.getSubwayStations();
                                    List<OneCheckBean> oneCheckBeanList = new ArrayList<OneCheckBean>();
                                    oneCheckBeanList.add(new OneCheckBean(true, getResources().getString(R.string.buxian)));
                                    if (subwayStations != null && subwayStations.size() > 0) {
                                        for (int i1 = 0; i1 < subwayStations.size(); i1++) {
                                            int id = subwayStations.get(i1).getId();
                                            String stationNameCn = subwayStations.get(i1).getStationNameCn();
                                            String stationNameJpn = subwayStations.get(i1).getStationNameJpn();
                                            OneCheckBean oneCheckBean = new OneCheckBean(false, isJa ? stationNameJpn : stationNameCn, id);
                                            oneCheckBeanList.add(oneCheckBean);
                                        }
                                    }
                                    moreCheckBean.setCheckBeanList(oneCheckBeanList);
                                    ditieListBean.add(moreCheckBean);
                                }
                            }
                        }
                        /**
                         * 第一个界面
                         * */
                        FirstView firstView = new FirstView(SydcLiebiaoActivity.this);
                        popupViews.add(firstView.firstView());
                        firstView.insertData(quyuListBean, ditieListBean, dropDownMenu);
                        firstView.setListener(SydcLiebiaoActivity.this);

                        /**
                         * 第二个界面
                         * */
                        mianji = shaiXuanBeanDatas.getMianji();
                        List<OneCheckBean> list1 = new ArrayList<>();
                        list1.add(new OneCheckBean(false, getResources().getString(R.string.buxian)));
                        if (mianji != null && mianji.size() > 0) {
                            for (int i = 0; i < mianji.size(); i++) {
                                OldHouseShaiXuanBean.DatasEntity.MianjiEntity mianjiEntity = mianji.get(i);
                                list1.add(new OneCheckBean(false, isJa ? mianjiEntity.getScreeValJpn() : mianjiEntity.getScreeValCn()));
                            }
                        }
                        SecView secView = new SecView(SydcLiebiaoActivity.this);
                        popupViews.add(secView.secView());
                        secView.setListener(SydcLiebiaoActivity.this);
                        secView.insertData(list1, dropDownMenu);

                        /**
                         * 第三个界面
                         * */
                        shoujia = shaiXuanBeanDatas.getShoujia();
                        List<OneCheckBean> list2 = new ArrayList<>();
                        list2.add(new OneCheckBean(false,getResources().getString(R.string.buxian)));
                        if (shoujia != null && shoujia.size() > 0) {
                            for (int i = 0; i < shoujia.size(); i++) {
                                OldHouseShaiXuanBean.DatasEntity.ShoujiaEntity shoujiaEntity = shoujia.get(i);
                                list2.add(new OneCheckBean(false, isJa ? shoujiaEntity.getScreeValJpn() : shoujiaEntity.getScreeValCn()));
                            }
                        }
                        ThreeView threeView = new ThreeView(SydcLiebiaoActivity.this);
                        popupViews.add(threeView.firstView());
                        threeView.insertData(list2, dropDownMenu);
                        threeView.setListener(SydcLiebiaoActivity.this);
                        /**
                         * 第四个界面
                         * */
                        List<MoreCheckBean> moreCheckBeanList = new ArrayList<MoreCheckBean>();
                        List<OldHouseShaiXuanBean.DatasEntity.MoreEntity> more = shaiXuanBeanDatas.getMore();
                        if (more != null && more.size() > 0) {
                            for (int i = 0; i < more.size(); i++) {
                                OldHouseShaiXuanBean.DatasEntity.MoreEntity moreEntity = more.get(i);
                                List<OldHouseShaiXuanBean.DatasEntity.MoreEntity.DataEntity> data = moreEntity.getData();
                                String nameCn = moreEntity.getNameCn();
                                String nameJpn = moreEntity.getNameJpn();
                                MoreCheckBean moreCheckBean = new MoreCheckBean();
                                moreCheckBean.setName(isJa ? nameJpn : nameCn);
                                if (data != null && data.size() > 0) {
                                    List<OneCheckBean> list3 = new ArrayList<>();
                                    for (int i1 = 0; i1 < data.size(); i1++) {
                                        list3.add(new OneCheckBean(false,
                                                isJa ? data.get(i1).getScreeValJpn() : data.get(i1).getScreeValCn(), data.get(i1).getId()));
                                    }
                                    moreCheckBean.setCheckBeanList(list3);
                                }
                                moreCheckBeanList.add(moreCheckBean);
                            }
                        }
                        MoreView fourView = new MoreView(SydcLiebiaoActivity.this);
                        popupViews.add(fourView.secView());
                        fourView.insertData(moreCheckBeanList, dropDownMenu);
                        fourView.setListener(SydcLiebiaoActivity.this);
                        /**
                         * Dropdownmenu下面的主体部分
                         * */
                        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, fifthView);
                    }
                });
    }

    private void initData() {
        if (!NetWorkUtils.isNetworkConnected(this)){
            tvNoContent.setVisibility(View.VISIBLE);
            tvNoContent.setText(R.string.wangluoshiqulianjie);
            springview.setVisibility(View.GONE);
            return;
        }
        HttpParams params = new HttpParams();
        if (isJa) {
            params.put("languageType", 1);
        } else {
            params.put("languageType", 0);
        }
        params.put("cId", 2);
        params.put("hType", type);
        params.put("pageNo", page);
        params.put("mjId", mjId);//面积
        params.put("sjId", sjId);//售价
        params.put("searchText", searchText);
        if (isZiDingyiPrice) {
            params.put("starSj", zidingyiPriceList.get(0));//售价最低价
            params.put("endSj", zidingyiPriceList.get(1));//售价最高价
        }
        if (isDitie) {
            params.putUrlParams("dtzs", ditieList);//地铁站
        } else {
            params.putUrlParams("qys", quyuList);//区域
        }
        initMoreParams(params);
        OkGo.<SydcListBean>post(MyUrls.BASEURL + "/app/realestate/searchlist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SydcListBean>(SydcLiebiaoActivity.this, SydcListBean.class) {
                    @Override
                    public void onSuccess(Response<SydcListBean> response) {
                        int code = response.code();
                        SydcListBean oldHouseListBean = response.body();
                        if (oldHouseListBean == null) {
                            return;
                        }
                        List<SydcListBean.DatasEntity> datas = oldHouseListBean.getDatas();
                        tvNoContent.setVisibility(View.GONE);
                        springview.setVisibility(View.VISIBLE);
                        if (mDatas == null || mDatas.size() == 0) {
                            if (datas == null || datas.size() == 0) {
                                tvNoContent.setVisibility(View.VISIBLE);
                                springview.setVisibility(View.GONE);
                                if (liebiaoAdapter != null) {
                                    liebiaoAdapter.notifyDataSetChanged();
                                }
                                return;
                            }
                            mDatas = datas;
                            liebiaoAdapter = new LiebiaoAdapter(R.layout.item_home_sydc, mDatas);
                            mrecycler.setAdapter(liebiaoAdapter);
                        } else {
                            if (datas == null || datas.size() == 0) {
                                TUtils.showFail(mContext, getString(R.string.meiyougengduoshujule));
                                return;
                            }
                            if (!isLoadMore) {
                                mDatas = datas;
                                TUtils.showFail(mContext, getString(R.string.shuaxinchenggong));
                            } else {
                                mDatas.addAll(datas);
                            }
                            liebiaoAdapter.notifyDataSetChanged();
                        }
                        liebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent;
                                switch (houseType) {
                                    case "shangpu":
                                        intent = new Intent(mContext, ShangpuDetailsActivity.class);
                                        intent.putExtra("houseId", mDatas.get(position).getId() + "");
                                        startActivity(intent);
                                        break;
                                    case "xiezilou":
                                        intent = new Intent(mContext, XiezilouDetailsActivity.class);
                                        intent.putExtra("houseId", mDatas.get(position).getId() + "");
                                        startActivity(intent);
                                        break;
                                    case "gaoerfu":
                                        intent = new Intent(mContext, GaoerfuDetailsActivity.class);
                                        intent.putExtra("houseId", mDatas.get(position).getId() + "");
                                        startActivity(intent);
                                        break;
                                    case "jiudian":
                                        intent = new Intent(mContext, JiudianDetailsActivity.class);
                                        intent.putExtra("houseId", mDatas.get(position).getId() + "");
                                        startActivity(intent);
                                        break;
                                }
                            }
                        });
                    }
                });
    }

    private void initMoreParams(HttpParams params) {
        switch (type) {
            case 3://商铺买卖
                if (mMoreSelectedBeanList.size() > 0)
                    params.putUrlParams("smlxs", mMoreSelectedBeanList.get(0));//售卖类型
                if (mMoreSelectedBeanList.size() > 1)
                    params.putUrlParams("czjls", mMoreSelectedBeanList.get(1));//车站距离
                if (mMoreSelectedBeanList.size() > 2)
                    params.putUrlParams("yylxs", mMoreSelectedBeanList.get(2));//营业类型
                if (mMoreSelectedBeanList.size() > 3)
                    params.putUrlParams("jznfs", mMoreSelectedBeanList.get(3));//建筑年份
                if (mMoreSelectedBeanList.size() > 4)
                    params.putUrlParams("jzgzs", mMoreSelectedBeanList.get(4));//建筑构造
                if (mMoreSelectedBeanList.size() > 5)
                    params.putUrlParams("tzs", mMoreSelectedBeanList.get(5));//特征
                if (mMoreSelectedBeanList.size() > 6)
                    params.putUrlParams("tjs", mMoreSelectedBeanList.get(6));//条件
                if (mMoreSelectedBeanList.size() > 7)
                    params.putUrlParams("syqs", mMoreSelectedBeanList.get(7));//所有权
                if (mMoreSelectedBeanList.size() > 8)
                    params.putUrlParams("xzs", mMoreSelectedBeanList.get(8));//现状
                break;
            case 2://写字楼买卖
                if (mMoreSelectedBeanList.size() > 0)
                    params.putUrlParams("smlxs", mMoreSelectedBeanList.get(0));//售卖类型
                if (mMoreSelectedBeanList.size() > 1)
                    params.putUrlParams("czjls", mMoreSelectedBeanList.get(1));//车站距离
                if (mMoreSelectedBeanList.size() > 2)
                    params.putUrlParams("jznfs", mMoreSelectedBeanList.get(2));//建筑年份
                if (mMoreSelectedBeanList.size() > 3)
                    params.putUrlParams("rqxzs", mMoreSelectedBeanList.get(3));//人气选择
                if (mMoreSelectedBeanList.size() > 4)
                    params.putUrlParams("syqs", mMoreSelectedBeanList.get(4));//所有权
                if (mMoreSelectedBeanList.size() > 5)
                    params.putUrlParams("xzs", mMoreSelectedBeanList.get(5));//现状
                break;
            case 1://高尔夫球场
                if (mMoreSelectedBeanList.size() > 0)
                    params.putUrlParams("czjls", mMoreSelectedBeanList.get(0));//车站距离
                if (mMoreSelectedBeanList.size() > 1)
                    params.putUrlParams("syqs", mMoreSelectedBeanList.get(1));//所有权
                if (mMoreSelectedBeanList.size() > 2)
                    params.putUrlParams("xzs", mMoreSelectedBeanList.get(2));//现状
                break;
            case 0://酒店
                if (mMoreSelectedBeanList.size() > 0)
                    params.putUrlParams("jdlxs", mMoreSelectedBeanList.get(0));//酒店类型
                if (mMoreSelectedBeanList.size() > 1)
                    params.putUrlParams("czjls", mMoreSelectedBeanList.get(1));//车站距离
                if (mMoreSelectedBeanList.size() > 2)
                    params.putUrlParams("jdlcs", mMoreSelectedBeanList.get(2));//酒店楼层数
                if (mMoreSelectedBeanList.size() > 3)
                    params.putUrlParams("syqs", mMoreSelectedBeanList.get(3));//所有权
                if (mMoreSelectedBeanList.size() > 4)
                    params.putUrlParams("xzs", mMoreSelectedBeanList.get(4));//现状
                break;
        }
    }

    @Override
    public void onItemClick(View view, int postion, int itemPosition) {
        switch (postion) {
            case 1:
                break;
            case 2://面积
                page = 1;
                if (itemPosition == 0) {//说明是点击的不限
                    mjId = "-2";
                } else {
                    if (mianji != null && mianji.size() > 0) {
                        OldHouseShaiXuanBean.DatasEntity.MianjiEntity mianjiEntity = mianji.get(itemPosition - 1);
                        mjId = mianjiEntity.getId() + "";
                    }
                }
                mDatas.clear();
                //            Toast.makeText(this, " "+mjId, Toast.LENGTH_SHORT).show();
                initData();
                break;
            case 3://售价
                page = 1;
                isZiDingyiPrice = false;
                zidingyiPriceList.clear();
                if (itemPosition == 0) {
                    sjId = "-2";
                } else {
                    if (shoujia != null && shoujia.size() > 0) {
                        sjId = shoujia.get(itemPosition - 1).getId() + "";
                    }
                }
                mDatas.clear();
                initData();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int postion, List<String> priceRegin) {
        if (shoujia != null && shoujia.size() > 0) {
            page = 1;
            isZiDingyiPrice = true;
            sjId = "-1";
            zidingyiPriceList.clear();
            zidingyiPriceList = priceRegin;
            mDatas.clear();
            initData();
        }
    }

    @Override
    public void onMoreItemClick(View view, List<List<String>> moreSelectedBeanList) {
        page = 1;
        mMoreSelectedBeanList.clear();
        mMoreSelectedBeanList = moreSelectedBeanList;
        mDatas.clear();
        initData();
    }

    class LiebiaoAdapter extends BaseQuickAdapter<SydcListBean.DatasEntity, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<SydcListBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SydcListBean.DatasEntity item) {
            Glide.with(MyApplication.getGloableContext()).load(item.getRealEstateImgs())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.tv_title, isJa ? item.getTitleJpn() : item.getTitleCn())
                    .setText(R.id.tv_area, isJa ? item.getSpecificLocationJpn() : item.getSpecificLocationCn())
                    .setText(R.id.tv_mianji, isJa ? item.getAreaJpn() : item.getAreaCn())
                    .setText(R.id.tv_price, isJa ? item.getSellingPriceJpn(): item.getSellingPriceCn());
        }
    }

    @OnClick({R.id.back_img, R.id.img_dingwei, R.id.img_message, R.id.search_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            //地图
            case R.id.img_dingwei:
                startActivity(new Intent(mContext, HomeMapActivity.class));
                break;
            //消息
            case R.id.img_message:
                EventBus.getDefault().post(new EventBean(Constants.EVENT_CHAT));
                finish();
                break;
            case R.id.search_tv:
                Intent intent = new Intent(mContext, HomeSearchActivity.class);
                intent.putExtra("popcontent", getResources().getString(R.string.shangyedichan));
                intent.putExtra("state", 5);
                intent.putExtra("state2", type);
                startActivityForResult(intent,0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==11){
            searchText=data.getStringExtra("searchText");
            page=1;
            mDatas.clear();
            initData();
        }
    }
}
