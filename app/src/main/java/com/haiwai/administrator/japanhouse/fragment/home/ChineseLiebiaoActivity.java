package com.haiwai.administrator.japanhouse.fragment.home;

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
import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.bean.ChinaListBean;
import com.haiwai.administrator.japanhouse.bean.ChinaShaiXuanBean;
import com.haiwai.administrator.japanhouse.bean.EventBean;
import com.haiwai.administrator.japanhouse.bean.MoreCheckBean;
import com.haiwai.administrator.japanhouse.bean.OneCheckBean;
import com.haiwai.administrator.japanhouse.bean.QuYuBean;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.callback.JsonCallback;
import com.haiwai.administrator.japanhouse.fragment.comment.ZhongguoDetailsActivity;
import com.haiwai.administrator.japanhouse.utils.CacheUtils;
import com.haiwai.administrator.japanhouse.utils.Constants;
import com.haiwai.administrator.japanhouse.utils.GlideReqUtils;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.haiwai.administrator.japanhouse.utils.MyUtils;
import com.haiwai.administrator.japanhouse.utils.NetWorkUtils;
import com.haiwai.administrator.japanhouse.utils.TUtils;
import com.haiwai.administrator.japanhouse.view.MyFooter;
import com.haiwai.administrator.japanhouse.view.MyHeader;
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

import static android.R.attr.id;

public class ChineseLiebiaoActivity extends BaseActivity implements MyItemClickListener {

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
    private TextView tvNoContent;
    private List<View> popupViews = new ArrayList<>();
    private RecyclerView mrecycler;
    private LiebiaoAdapter liebiaoAdapter;
    private SpringView springview;
    private boolean isLoadMore;
    private int page = 1;
    private boolean isJa;
    private List<ChinaListBean.DatasEntity> mDatas;
    private String sjId = "-2";
    private List<String> zidingyiPriceList = new ArrayList<>();
    private boolean isZiDingyiPrice;
    private List<ChinaShaiXuanBean.DatasEntity.HuxingEntity> huxing;
    private List<ChinaShaiXuanBean.DatasEntity.ShoujiaEntity> shoujia;
    private List<List<String>> mMoreSelectedBeanList = new ArrayList<>();
    private List<String> hxsList = new ArrayList<>();
    private String[] headers;
    private View fifthView;
    private ChinaShaiXuanBean.DatasEntity shaiXuanBeanDatas;
    private boolean isDitie;
    private List<String> quyuList = new ArrayList<>();
    private List<String> ditieList = new ArrayList<>();
    private String cityId;
    private String searchText;
    private FirstView firstView;
    private ThreeView threeView;
    private SecView secView;
    private MoreView fourView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_liebiao);
        ButterKnife.bind(this);
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        searchText = getIntent().getStringExtra("searchText");
        if (!TextUtils.isEmpty(searchText)) {
            searchTv.setText(searchText);
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
        headers = new String[]{getString(R.string.quyu), getString(R.string.shoujia),
                getString(R.string.huxing), getString(R.string.gengduo)};
        fifthView = LayoutInflater.from(this).inflate(R.layout.activity_main_view, null);
        mrecycler = (RecyclerView) fifthView.findViewById(R.id.mrecycler);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mrecycler.setNestedScrollingEnabled(false);
        springview = (SpringView) fifthView.findViewById(R.id.springview);
        tvNoContent = (TextView) fifthView.findViewById(R.id.tv_noContent);
        HttpParams params = new HttpParams();
        params.put("hType", 4);
        OkGo.<ChinaShaiXuanBean>post(MyUrls.BASEURL + "/app/onescreening/selectallscree")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<ChinaShaiXuanBean>(ChinaShaiXuanBean.class) {
                    @Override
                    public void onSuccess(Response<ChinaShaiXuanBean> response) {
                        int code = response.code();
                        ChinaShaiXuanBean shaiXuanBean = response.body();
                        if (shaiXuanBean == null) {
                            return;
                        }
                        shaiXuanBeanDatas = shaiXuanBean.getDatas();
                        firstView = new FirstView(ChineseLiebiaoActivity.this);
                        popupViews.add(firstView.firstView());
                        threeView = new ThreeView(ChineseLiebiaoActivity.this);
                        popupViews.add(threeView.firstView());
                        secView = new SecView(ChineseLiebiaoActivity.this);
                        popupViews.add(secView.secView());
                        fourView = new MoreView(ChineseLiebiaoActivity.this);
                        popupViews.add(fourView.secView());
                        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, fifthView);
                        initShaiXuan();
                    }
                });
    }

    private void initShaiXuan() {
        HttpParams params = new HttpParams();
        int cityId = CacheUtils.get("cityId");
        params.put("cId", cityId);
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
                                    oneCheckBeanList.add(new OneCheckBean(true, getResources().getString(R.string.buxian)));
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
//                        FirstView firstView = new FirstView(ChineseLiebiaoActivity.this);
//                        popupViews.add(firstView.firstView());
                        firstView.insertData(quyuListBean, ditieListBean, dropDownMenu);
                        firstView.setListener(ChineseLiebiaoActivity.this);
                        if (shaiXuanBeanDatas==null){
                            return;
                        }
                        /**
                         * 第二个界面
                         * */
                        shoujia = shaiXuanBeanDatas.getShoujia();
                        List<OneCheckBean> list2 = new ArrayList<>();
                        list2.add(new OneCheckBean(false, getResources().getString(R.string.buxian)));
                        if (shoujia != null && shoujia.size() > 0) {
                            for (int i = 0; i < shoujia.size(); i++) {
                                ChinaShaiXuanBean.DatasEntity.ShoujiaEntity shoujiaEntity = shoujia.get(i);
                                list2.add(new OneCheckBean(false, isJa ? shoujiaEntity.getScreeValJpn() : shoujiaEntity.getScreeValCn()));
                            }
                        }
//                        ThreeView threeView = new ThreeView(ChineseLiebiaoActivity.this);
//                        popupViews.add(threeView.firstView());
                        threeView.insertData(list2, dropDownMenu);
                        threeView.setListener(ChineseLiebiaoActivity.this);

                        /**
                         * 第三个界面
                         * */
                        huxing = shaiXuanBeanDatas.getHuxing();
                        List<OneCheckBean> list1 = new ArrayList<>();
                        list1.add(new OneCheckBean(false, getResources().getString(R.string.buxian)));
                        if (huxing != null && huxing.size() > 0) {
                            for (int i = 0; i < huxing.size(); i++) {
                                ChinaShaiXuanBean.DatasEntity.HuxingEntity mianjiEntity = huxing.get(i);
                                list1.add(new OneCheckBean(false, isJa ? mianjiEntity.getScreeValJpn() : mianjiEntity.getScreeValCn()));
                            }
                        }
//                        SecView secView = new SecView(ChineseLiebiaoActivity.this);
//                        popupViews.add(secView.secView());
                        secView.setListener(ChineseLiebiaoActivity.this);
                        secView.insertData(list1, dropDownMenu);
                        /**
                         * 第四个界面
                         * */
                        List<MoreCheckBean> moreCheckBeanList = new ArrayList<MoreCheckBean>();
                        List<ChinaShaiXuanBean.DatasEntity.MoreEntity> more = shaiXuanBeanDatas.getMore();
                        if (more != null && more.size() > 0) {
                            for (int i = 0; i < more.size(); i++) {
                                ChinaShaiXuanBean.DatasEntity.MoreEntity moreEntity = more.get(i);
                                List<ChinaShaiXuanBean.DatasEntity.MoreEntity.DataEntity> data = moreEntity.getData();
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
//                        MoreView fourView = new MoreView(ChineseLiebiaoActivity.this);
//                        popupViews.add(fourView.secView());
                        fourView.insertData(moreCheckBeanList, dropDownMenu);
                        fourView.setListener(ChineseLiebiaoActivity.this);
                        /**
                         * Dropdownmenu下面的主体部分
                         * */
//                        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, fifthView);
                    }
                });
    }


    private void initData() {
        if (!NetWorkUtils.isNetworkConnected(this)) {
            tvNoContent.setVisibility(View.VISIBLE);
            tvNoContent.setText(R.string.wangluoshiqulianjie);
            springview.setVisibility(View.GONE);
            return;
        }
        cityId = getIntent().getStringExtra("id");
        HttpParams params = new HttpParams();
        params.put("pageNo", page);
        if (isJa) {
            params.put("languageType", 1);
        } else {
            params.put("languageType", 0);
        }
        params.put("hType", 1);
        params.put("cityId", cityId);//城市id
        params.put("sjId", sjId);//售价
        params.putUrlParams("hxs", hxsList);//户型
        params.put("searchText", searchText);//搜索
        if (isZiDingyiPrice) {
            if (zidingyiPriceList.size() == 1) {
                String s = zidingyiPriceList.get(0);
                if (s.contains("below")) {
                    String[] split = s.split(",");
                    params.put("starSj", split[1]);//售价最低价
                } else if (s.contains("high")) {
                    String[] split = s.split(",");
                    params.put("endSj", split[1]);//售价最高价
                }
            } else if (zidingyiPriceList.size() == 2) {
                params.put("starSj", zidingyiPriceList.get(0).split(",")[1]);//售价最低价
                params.put("endSj", zidingyiPriceList.get(1).split(",")[1]);//售价最高价
            }
        }
        if (isDitie) {
            params.putUrlParams("dtzs", ditieList);//地铁站
        } else {
            params.putUrlParams("qys", quyuList);//区域
        }
        if (mMoreSelectedBeanList.size() > 0)
            params.putUrlParams("mjs", mMoreSelectedBeanList.get(0));//面积
        if (mMoreSelectedBeanList.size() > 1)
            params.putUrlParams("dds", mMoreSelectedBeanList.get(1));//地段
        if (mMoreSelectedBeanList.size() > 2)
            params.putUrlParams("lcs", mMoreSelectedBeanList.get(2));//楼层
        if (mMoreSelectedBeanList.size() > 3)
            params.putUrlParams("cxs", mMoreSelectedBeanList.get(3));//朝向
        OkGo.<ChinaListBean>post(MyUrls.BASEURL + "/app/oiverseas/searchlist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<ChinaListBean>(ChineseLiebiaoActivity.this, ChinaListBean.class) {
                    @Override
                    public void onSuccess(Response<ChinaListBean> response) {
                        int code = response.code();
                        ChinaListBean chinaListBean = response.body();
                        if (chinaListBean == null) {
                            return;
                        }
                        List<ChinaListBean.DatasEntity> datas = chinaListBean.getDatas();
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
                            liebiaoAdapter = new LiebiaoAdapter(R.layout.item_china_like, mDatas);
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
                                Intent intent = new Intent(ChineseLiebiaoActivity.this, ZhongguoDetailsActivity.class);
                                intent.putExtra("houseId", mDatas.get(position).getId() + "");
                                intent.putExtra("cityId", id);
                                startActivity(intent);
                            }
                        });
                    }
                });
    }

    @Override
    public void onItemClick(View view, int postion, int itemPosition) {
        switch (postion) {
            case 1:
                break;
            case 2://户型
                page = 1;
                if (itemPosition == 0) {//说明是点击的不限
                    hxsList.clear();
                    hxsList.add("-2");
                } else {
                    if (huxing != null && huxing.size() > 0) {
                        ChinaShaiXuanBean.DatasEntity.HuxingEntity huxingEntity = huxing.get(itemPosition - 1);
                        hxsList.clear();
                        hxsList.add(huxingEntity.getId() + "");
                    }
                }
                if (mDatas != null) {
                    mDatas.clear();
                }
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
                if (mDatas != null) {
                    mDatas.clear();
                }
                initData();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int postion, List<String> priceRegin) {
        if (postion == 1) {//区域
            isDitie = false;
            quyuList = priceRegin;
        } else if (postion == 2) {//地铁
            isDitie = true;
            ditieList = priceRegin;
        } else {//自定义价格
            if (shoujia != null && shoujia.size() > 0) {
                page = 1;
                isZiDingyiPrice = true;
                sjId = "-1";
                zidingyiPriceList.clear();
                zidingyiPriceList = priceRegin;
                if (mDatas != null) {
                    mDatas.clear();
                }
                initData();
            }
        }
    }

    @Override
    public void onMoreItemClick(View view, List<List<String>> moreSelectedBeanList) {
        page = 1;
        mMoreSelectedBeanList.clear();
        mMoreSelectedBeanList = moreSelectedBeanList;
        if (mDatas != null) {
            mDatas.clear();
        }
        initData();
    }

    class LiebiaoAdapter extends BaseQuickAdapter<ChinaListBean.DatasEntity, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<ChinaListBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ChinaListBean.DatasEntity item) {
            Glide.with(MyApplication.getGloableContext())
                    .load(TextUtils.isEmpty(item.getVideoImgs()) ? MyUtils.getSpiltText(item.getHouseImgs())
                            : item.getVideoImgs())
                    .apply(GlideReqUtils.getReq())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
            String area;
            if (isJa) {
                area = item.getSpecificLocationJpn();
            } else {
                area = item.getSpecificLocationCn();
            }
            String price=isJa ? item.getSellingPriceJpn() : item.getSellingPriceCn();
            helper.setText(R.id.tv_title, isJa ? item.getTitleJpn() : item.getTitleCn())
                    .setText(R.id.tv_area, MyUtils.getSubText(area, price))
                    .setText(R.id.tv_mianji, isJa ? item.getAreaJpn() : item.getAreaCn())
                    .setText(R.id.tv_price, price)
                    .setText(R.id.tv_ting, isJa ? item.getHouseTypeJpn() : item.getHouseTypeCn());
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
                startActivityForResult(new Intent(mContext, HomeMapActivity.class),0);
                break;
            //消息
            case R.id.img_message:
                EventBus.getDefault().post(new EventBean(Constants.EVENT_CHAT));
                finish();
                break;
            case R.id.search_tv:
                Intent intent = new Intent(mContext, SydcSearchActivity.class);
                intent.putExtra("edt_hint", getResources().getString(R.string.qsrdcmchqy));
                intent.putExtra("state", 7);
                intent.putExtra("state2", Integer.parseInt(cityId));
                startActivityForResult(intent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 11) {
            searchText = data.getStringExtra("searchText");
            if (!TextUtils.isEmpty(searchText)) {
                searchTv.setText(searchText);
            }
            page = 1;
            if (mDatas != null) {
                mDatas.clear();
            }
            initData();
        }else if (resultCode==100){
            finish();
        }
    }
}
