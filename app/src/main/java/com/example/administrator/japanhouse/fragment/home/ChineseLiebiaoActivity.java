package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.bean.MoreCheckBean;
import com.example.administrator.japanhouse.bean.OldHouseShaiXuanBean;
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.example.administrator.japanhouse.bean.TudiListBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.fragment.comment.ZhongguoDetailsActivity;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
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
    private List<View> popupViews = new ArrayList<>();
    private RecyclerView mrecycler;
    private List<String> mList = new ArrayList();
    private LiebiaoAdapter liebiaoAdapter;
    private List<OneCheckBean> list;
    private SpringView springview;
    private boolean isLoadMore;
    private int page=1;
    private boolean isJa;
    private List<TudiListBean.DatasEntity> mDatas;
    private String sjId = "-2";
    private List<String> zidingyiPriceList = new ArrayList<>();
    private boolean isZiDingyiPrice;
    private List<OldHouseShaiXuanBean.DatasEntity.MianjiEntity> mianji;
    private List<OldHouseShaiXuanBean.DatasEntity.ShoujiaEntity> shoujia;
    private List<List<String>> mMoreSelectedBeanList = new ArrayList<>();
    private List<String> hxsList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_liebiao);
        ButterKnife.bind(this);
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
        final String[] headers = {getString(R.string.quyu), getString(R.string.shoujia),
                getString(R.string.huxing), getString(R.string.gengduo)};
        final View fifthView = LayoutInflater.from(this).inflate(R.layout.activity_main_view, null);
        mrecycler = (RecyclerView) fifthView.findViewById(R.id.mrecycler);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mrecycler.setNestedScrollingEnabled(false);
        springview = (SpringView) fifthView.findViewById(R.id.springview);
        HttpParams params = new HttpParams();
        params.put("hType", 4);
        OkGo.<OldHouseShaiXuanBean>post(MyUrls.BASEURL + "/app/onescreening/selectallscree")
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
                        OldHouseShaiXuanBean.DatasEntity shaiXuanBeanDatas = shaiXuanBean.getDatas();

                        /**
                         * 第一个界面
                         * */
                        list = new ArrayList<>();
                        FirstView firstView = new FirstView(ChineseLiebiaoActivity.this);
                        popupViews.add(firstView.firstView());
                        firstView.insertData(list, dropDownMenu);
                        firstView.setListener(ChineseLiebiaoActivity.this);

                        /**
                         * 第二个界面
                         * */
                        shoujia = shaiXuanBeanDatas.getShoujia();
                        List<OneCheckBean> list2 = new ArrayList<>();
                        list2.add(new OneCheckBean(false, "不限"));
                        if (shoujia != null && shoujia.size() > 0) {
                            for (int i = 0; i < shoujia.size(); i++) {
                                OldHouseShaiXuanBean.DatasEntity.ShoujiaEntity shoujiaEntity = shoujia.get(i);
                                list2.add(new OneCheckBean(false, isJa ? shoujiaEntity.getScreeValJpn() : shoujiaEntity.getScreeValCn()));
                            }
                        }
                        ThreeView threeView = new ThreeView(ChineseLiebiaoActivity.this);
                        popupViews.add(threeView.firstView());
                        threeView.insertData(list2, dropDownMenu);
                        threeView.setListener(ChineseLiebiaoActivity.this);

                        /**
                         * 第三个界面
                         * */
                        mianji = shaiXuanBeanDatas.getMianji();
                        List<OneCheckBean> list1 = new ArrayList<>();
                        list1.add(new OneCheckBean(false, "不限"));
                        if (mianji != null && mianji.size() > 0) {
                            for (int i = 0; i < mianji.size(); i++) {
                                OldHouseShaiXuanBean.DatasEntity.MianjiEntity mianjiEntity = mianji.get(i);
                                list1.add(new OneCheckBean(false, isJa ? mianjiEntity.getScreeValJpn() : mianjiEntity.getScreeValCn()));
                            }
                        }
                        SecView secView = new SecView(ChineseLiebiaoActivity.this);
                        popupViews.add(secView.secView());
                        secView.setListener(ChineseLiebiaoActivity.this);
                        secView.insertData(list1, dropDownMenu);
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
                        MoreView fourView = new MoreView(ChineseLiebiaoActivity.this);
                        popupViews.add(fourView.secView());
                        fourView.insertData3(moreCheckBeanList, dropDownMenu);
                        fourView.setListener(ChineseLiebiaoActivity.this);
                        /**
                         * Dropdownmenu下面的主体部分
                         * */
                        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, fifthView);
                    }
                });
    }

    private void initData() {
        final String id = getIntent().getStringExtra("id");
        HttpParams params = new HttpParams();
        params.put("pageNo", page);
        if (isJa) {
            params.put("languageType", 1);
        }else {
            params.put("languageType", 0);
        }
        params.put("hType", 1);
        params.put("cityId", id);//城市id
        params.put("sjId", sjId);//售价
        params.putUrlParams("hxs", hxsList);//户型
        if (isZiDingyiPrice) {
            params.put("starSj", zidingyiPriceList.get(0));//售价最低价
            params.put("endSj", zidingyiPriceList.get(1));//售价最高价
        }
        if (mMoreSelectedBeanList.size() > 0)
            params.putUrlParams("mjs", mMoreSelectedBeanList.get(0));//面积
        if (mMoreSelectedBeanList.size() > 1)
            params.putUrlParams("dds", mMoreSelectedBeanList.get(1));//地段
        if (mMoreSelectedBeanList.size() > 2)
            params.putUrlParams("lcs", mMoreSelectedBeanList.get(2));//楼层
        if (mMoreSelectedBeanList.size() > 3)
            params.putUrlParams("cxs", mMoreSelectedBeanList.get(3));//朝向
        OkGo.<TudiListBean>post(MyUrls.BASEURL + "/app/oiverseas/searchlist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<TudiListBean>(ChineseLiebiaoActivity.this, TudiListBean.class) {
                    @Override
                    public void onSuccess(Response<TudiListBean> response) {
                        int code = response.code();
                        TudiListBean tudiListBean = response.body();
                        if (tudiListBean == null) {
                            return;
                        }
                        List<TudiListBean.DatasEntity> datas = tudiListBean.getDatas();
                        if (mDatas == null || mDatas.size() == 0) {
                            if (datas == null || datas.size() == 0) {
                                Toast.makeText(ChineseLiebiaoActivity.this, "无数据~", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(ChineseLiebiaoActivity.this, "没有更多数据了~", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (!isLoadMore) {
                                mDatas = datas;
                                Toast.makeText(ChineseLiebiaoActivity.this, "刷新成功~", Toast.LENGTH_SHORT).show();
                            } else {
                                mDatas.addAll(datas);
                            }
                            liebiaoAdapter.notifyDataSetChanged();
                        }
                        liebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(ChineseLiebiaoActivity.this, ZhongguoDetailsActivity.class);
                                intent.putExtra("id", mDatas.get(position).getId()+"");
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
                    if (mianji != null && mianji.size() > 0) {
                        OldHouseShaiXuanBean.DatasEntity.MianjiEntity mianjiEntity = mianji.get(itemPosition - 1);
                        hxsList.clear();
                        hxsList.add(mianjiEntity.getId() + "");
                    }
                }
                mDatas.clear();
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
                        sjId=shoujia.get(itemPosition-1).getId()+"";
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

    class LiebiaoAdapter extends BaseQuickAdapter<TudiListBean.DatasEntity, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<TudiListBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, TudiListBean.DatasEntity item) {

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
                Intent intent = new Intent(mContext, SydcSearchActivity.class);
                intent.putExtra("edt_hint", getResources().getString(R.string.qsrdcmchqy));
                startActivity(intent);
        }
    }

}
