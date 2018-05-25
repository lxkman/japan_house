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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.MoreCheckBean;
import com.example.administrator.japanhouse.bean.OldHouseListBean;
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.example.administrator.japanhouse.bean.ZuHouseShaiXuanBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.fragment.comment.ZuHousedetailsActivity;
import com.example.administrator.japanhouse.fragment.mine.LiShiJiLuActivity;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.TUtils;
import com.example.administrator.japanhouse.view.MyFooter;
import com.example.administrator.japanhouse.view.MyHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class ZufangListActivity extends BaseActivity implements MyItemClickListener {

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
    private LiebiaoAdapter liebiaoAdapter;
    private List<OneCheckBean> list;
    private SpringView springview;
    private boolean isLoadMore;
    private int page = 1;
    private boolean isJa;
    private int mType;
    private int mType2;
    private List<OldHouseListBean.DatasEntity> mDatas;
    private String mjId = "-2";
    private String zjId = "-2";
    private List<String> zidingyiPriceList = new ArrayList<>();
    private boolean isZiDingyiPrice;
    private List<ZuHouseShaiXuanBean.DatasEntity.MianjiEntity> mianji;
    private List<ZuHouseShaiXuanBean.DatasEntity.ZujinEntity> zujin;
    private List<List<String>> mMoreSelectedBeanList = new ArrayList<>();

    private String searchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zufang_list);
        ButterKnife.bind(this);
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        mType = getIntent().getIntExtra("type", 0);
        if (mType == 0) {
            mType2 = 5;
        } else if (mType == 1) {
            mType2 = 4;
        } else if (mType == 2) {
            mType2 = 3;
        } else if (mType == 3) {
            mType2 = 2;
        } else if (mType == 4) {
            mType2 = 1;
        } else if (mType == 5) {
            mType2 = 0;
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
        /**
         * Dropdownmenu下面的主体部分
         * */
        final View fifthView = LayoutInflater.from(ZufangListActivity.this).inflate(R.layout.activity_zufang_view, null);
        mrecycler = (RecyclerView) fifthView.findViewById(R.id.mrecycler);
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        TextView shipinTv = (TextView) fifthView.findViewById(R.id.shipin_tv);
        TextView tongqinTv = (TextView) fifthView.findViewById(R.id.tongqin_tv);
        TextView jiluTv = (TextView) fifthView.findViewById(R.id.jilu_tv);
        springview = (SpringView) fifthView.findViewById(R.id.springview);
        shipinTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TUtils.showShort(mContext, "视频房源");
            }
        });
        tongqinTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TongqinActivity.class);
//                intent.putExtra("type", mType2);
                startActivity(intent);
            }
        });
        jiluTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, LiShiJiLuActivity.class));
            }
        });
        final String[] headers = {getString(R.string.quyu), getString(R.string.lxkmianji),
                getString(R.string.zujin), getString(R.string.gengduo)};
        HttpParams params = new HttpParams();
        params.put("hType", 0);
        params.put("shType", mType);
        OkGo.<ZuHouseShaiXuanBean>post(MyUrls.BASEURL + "/app/twoscreening/selectallscree")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<ZuHouseShaiXuanBean>(ZuHouseShaiXuanBean.class) {
                    @Override
                    public void onSuccess(Response<ZuHouseShaiXuanBean> response) {
                        int code = response.code();
                        ZuHouseShaiXuanBean shaiXuanBean = response.body();
                        if (shaiXuanBean == null) {
                            return;
                        }
                        ZuHouseShaiXuanBean.DatasEntity shaiXuanBeanDatas = shaiXuanBean.getDatas();

                        /**
                         * 第一个界面
                         * */
                        list = new ArrayList<>();
                        FirstView firstView = new FirstView(ZufangListActivity.this);
                        popupViews.add(firstView.firstView());
                        firstView.insertData(list, dropDownMenu);
                        firstView.setListener(ZufangListActivity.this);

                        /**
                         * 第二个界面
                         * */
                        mianji = shaiXuanBeanDatas.getMianji();
                        List<OneCheckBean> list1 = new ArrayList<>();
                        list1.add(new OneCheckBean(false, "不限"));
                        if (mianji != null && mianji.size() > 0) {
                            for (int i = 0; i < mianji.size(); i++) {
                                ZuHouseShaiXuanBean.DatasEntity.MianjiEntity mianjiEntity = mianji.get(i);
                                list1.add(new OneCheckBean(false, isJa ? mianjiEntity.getScreeValJpn() : mianjiEntity.getScreeValCn()));
                            }
                        }
                        SecView secView = new SecView(ZufangListActivity.this);
                        popupViews.add(secView.secView());
                        secView.setListener(ZufangListActivity.this);
                        secView.insertData(list1, dropDownMenu);

                        /**
                         * 第三个界面
                         * */
                        zujin = shaiXuanBeanDatas.getZujin();
                        List<OneCheckBean> list2 = new ArrayList<>();
                        list2.add(new OneCheckBean(false, "不限"));
                        if (zujin != null && zujin.size() > 0) {
                            for (int i = 0; i < zujin.size(); i++) {
                                ZuHouseShaiXuanBean.DatasEntity.ZujinEntity shoujiaEntity = zujin.get(i);
                                list2.add(new OneCheckBean(false, isJa ? shoujiaEntity.getScreeValJpn() : shoujiaEntity.getScreeValCn()));
                            }
                        }
                        ThreeView threeView = new ThreeView(ZufangListActivity.this);
                        popupViews.add(threeView.firstView());
                        threeView.insertData2(list2, dropDownMenu, true);
                        threeView.setListener(ZufangListActivity.this);
                        /**
                         * 第四个界面
                         * */
                        List<MoreCheckBean> moreCheckBeanList = new ArrayList<MoreCheckBean>();
                        List<ZuHouseShaiXuanBean.DatasEntity.MoreEntity> more = shaiXuanBeanDatas.getMore();
                        if (more != null && more.size() > 0) {
                            for (int i = 0; i < more.size(); i++) {
                                ZuHouseShaiXuanBean.DatasEntity.MoreEntity moreEntity = more.get(i);
                                List<ZuHouseShaiXuanBean.DatasEntity.MoreEntity.DataEntity> data = moreEntity.getData();
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
                        MoreView fourView = new MoreView(ZufangListActivity.this);
                        popupViews.add(fourView.secView());
                        fourView.insertData3(moreCheckBeanList, dropDownMenu);
                        fourView.setListener(ZufangListActivity.this);
                        /**
                         * Dropdownmenu下面的主体部分
                         * */
                        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, fifthView);
                    }
                });
    }

    private void initData() {
        if (!TextUtils.isEmpty(getIntent().getStringExtra("edt_hint"))) {
            searchTv.setHint(getIntent().getStringExtra("edt_hint"));
        }
        final String houseType = getIntent().getStringExtra("houseType");
        HttpParams params = new HttpParams();
        if (isJa) {
            params.put("languageType", 1);
        } else {
            params.put("languageType", 0);
        }
        params.put("pageNo", page);
        params.put("hType", mType2);
        params.put("mjId", mjId);//面积
        params.put("zjId", zjId);//租金
        params.put("searchText", searchText);
        if (isZiDingyiPrice) {
            params.put("starSj", zidingyiPriceList.get(0));//售价最低价
            params.put("endSj", zidingyiPriceList.get(1));//售价最高价
        }
        initMoreParams(params);
        OkGo.<OldHouseListBean>post(MyUrls.BASEURL + "/app/houseresourse/searchlistzf")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<OldHouseListBean>(ZufangListActivity.this, OldHouseListBean.class) {
                    @Override
                    public void onSuccess(Response<OldHouseListBean> response) {
                        int code = response.code();
                        OldHouseListBean oldHouseListBean = response.body();
                        if (oldHouseListBean == null) {
                            return;
                        }
                        List<OldHouseListBean.DatasEntity> datas = oldHouseListBean.getDatas();
                        if (mDatas == null || mDatas.size() == 0) {
                            if (datas == null || datas.size() == 0) {
                                Toast.makeText(ZufangListActivity.this, "无数据~", Toast.LENGTH_SHORT).show();
                                if (liebiaoAdapter != null) {
                                    liebiaoAdapter.notifyDataSetChanged();
                                }
                                return;
                            }
                            mDatas = datas;
                            liebiaoAdapter = new LiebiaoAdapter(R.layout.item_home_xinfang, mDatas);
                            mrecycler.setAdapter(liebiaoAdapter);
                        } else {
                            if (datas == null || datas.size() == 0) {
                                Toast.makeText(ZufangListActivity.this, "没有更多数据了~", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (!isLoadMore) {
                                mDatas = datas;
                                Toast.makeText(ZufangListActivity.this, "刷新成功~", Toast.LENGTH_SHORT).show();
                            } else {
                                mDatas.addAll(datas);
                            }
                            liebiaoAdapter.notifyDataSetChanged();
                        }
                        liebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(mContext, ZuHousedetailsActivity.class);
                                intent.putExtra("iszu", "iszu");
                                intent.putExtra("houseType", houseType);
                                startActivity(intent);
                            }
                        });
                    }
                });
    }

    private void initMoreParams(HttpParams params) {
        switch (mType) {
            case 0://多层公寓
            case 2://二层公寓
                if (mMoreSelectedBeanList.size() > 0)
                    params.putUrlParams("hxs", mMoreSelectedBeanList.get(0));//户型
                if (mMoreSelectedBeanList.size() > 1)
                    params.putUrlParams("lcs", mMoreSelectedBeanList.get(1));//楼层
                if (mMoreSelectedBeanList.size() > 2)
                    params.putUrlParams("jznfs", mMoreSelectedBeanList.get(2));//建筑年份
                if (mMoreSelectedBeanList.size() > 3)
                    params.putUrlParams("cxs", mMoreSelectedBeanList.get(3));//朝向
                if (mMoreSelectedBeanList.size() > 4)
                    params.putUrlParams("czjls", mMoreSelectedBeanList.get(4));//车站距离
                if (mMoreSelectedBeanList.size() > 5)
                    params.putUrlParams("cqfys", mMoreSelectedBeanList.get(5));//初期费用
                if (mMoreSelectedBeanList.size() > 6)
                    params.putUrlParams("rqxzs", mMoreSelectedBeanList.get(6));//人气选择
                break;
            case 1://学生公寓
                if (mMoreSelectedBeanList.size() > 0)
                    params.putUrlParams("hxs", mMoreSelectedBeanList.get(0));//户型
                if (mMoreSelectedBeanList.size() > 1)
                    params.putUrlParams("lcs", mMoreSelectedBeanList.get(1));//楼层
                if (mMoreSelectedBeanList.size() > 2)
                    params.putUrlParams("cxs", mMoreSelectedBeanList.get(2));//朝向
                if (mMoreSelectedBeanList.size() > 3)
                    params.putUrlParams("czjls", mMoreSelectedBeanList.get(3));//车站距离
                if (mMoreSelectedBeanList.size() > 4)
                    params.putUrlParams("fjzks", mMoreSelectedBeanList.get(4));//房间状况
                break;
            case 3://别墅
                if (mMoreSelectedBeanList.size() > 0)
                    params.putUrlParams("lcs", mMoreSelectedBeanList.get(0));//楼层
                if (mMoreSelectedBeanList.size() > 1)
                    params.putUrlParams("jznfs", mMoreSelectedBeanList.get(1));//建筑年份
                if (mMoreSelectedBeanList.size() > 2)
                    params.putUrlParams("cxs", mMoreSelectedBeanList.get(2));//朝向
                if (mMoreSelectedBeanList.size() > 3)
                    params.putUrlParams("jzgzs", mMoreSelectedBeanList.get(3));//建筑构造
                if (mMoreSelectedBeanList.size() > 4)
                    params.putUrlParams("dds", mMoreSelectedBeanList.get(4));//地段
                if (mMoreSelectedBeanList.size() > 5)
                    params.putUrlParams("czjls", mMoreSelectedBeanList.get(5));//车站距离
                if (mMoreSelectedBeanList.size() > 6)
                    params.putUrlParams("rzrqs", mMoreSelectedBeanList.get(6));//入居日期
                break;
            case 4://商铺出租
                if (mMoreSelectedBeanList.size() > 0)
                    params.putUrlParams("czlxs", mMoreSelectedBeanList.get(0));//出租类型
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
                break;
            case 5://办公室出租
                if (mMoreSelectedBeanList.size() > 0)
                    params.putUrlParams("czlxs", mMoreSelectedBeanList.get(0));//出租类型
                if (mMoreSelectedBeanList.size() > 1)
                    params.putUrlParams("czjls", mMoreSelectedBeanList.get(1));//车站距离
                if (mMoreSelectedBeanList.size() > 2)
                    params.putUrlParams("lcs", mMoreSelectedBeanList.get(2));//楼层
                if (mMoreSelectedBeanList.size() > 3)
                    params.putUrlParams("jznfs", mMoreSelectedBeanList.get(3));//建筑年份
                if (mMoreSelectedBeanList.size() > 4)
                    params.putUrlParams("cqfys", mMoreSelectedBeanList.get(4));//初期费用
                if (mMoreSelectedBeanList.size() > 5)
                    params.putUrlParams("rqxzs", mMoreSelectedBeanList.get(5));//人气选择
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
                        ZuHouseShaiXuanBean.DatasEntity.MianjiEntity mianjiEntity = mianji.get(itemPosition - 1);
                        mjId = mianjiEntity.getId() + "";
                    }
                }
                mDatas.clear();
                //            Toast.makeText(this, " "+mjId, Toast.LENGTH_SHORT).show();
                initData();
                break;
            case 3://租金
                page = 1;
                isZiDingyiPrice = false;
                zidingyiPriceList.clear();
                if (itemPosition == 0) {
                    zjId = "-2";
                } else {
                    if (zujin != null && zujin.size() > 0) {
                        zjId = zujin.get(itemPosition - 1).getId() + "";
                    }
                }
                mDatas.clear();
                initData();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int postion, List<String> priceRegin) {
        if (zujin != null && zujin.size() > 0) {
            page = 1;
            isZiDingyiPrice = true;
            zjId = "-1";
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

    class LiebiaoAdapter extends BaseQuickAdapter<OldHouseListBean.DatasEntity, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<OldHouseListBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OldHouseListBean.DatasEntity item) {
            Glide.with(MyApplication.getGloableContext()).load(item.getRoomImgs())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.tv_title, isJa ? item.getTitleJpn() : item.getTitleCn())
                    .setText(R.id.tv_area, isJa ? item.getSpecificLocationJpn() : item.getSpecificLocationCn())
                    .setText(R.id.tv_mianji, isJa ? item.getAreaJpn() : item.getAreaCn())
                    .setText(R.id.tv_price, isJa ? item.getPriceJpn() + "元/月" : item.getPriceCn() + "元/月");
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
                //                startActivity(new Intent(mContext, MainActivity.class));

                //会话类型 以及是否聚合显示
                HashMap<String, Boolean> hm = new HashMap<>();
                hm.put(Conversation.ConversationType.PRIVATE.getName(), false);
                //        hashMap.put(Conversation.ConversationType.PUSH_SERVICE.getName(),true);
                //        hashMap.put(Conversation.ConversationType.SYSTEM.getName(),true);
                RongIM.getInstance().startConversationList(this, hm);
                break;
            case R.id.search_tv:
                Intent intent = new Intent(mContext, HomeSearchActivity.class);
                intent.putExtra("popcontent", getResources().getString(R.string.zu_house));
                intent.putExtra("state", 4);
                startActivity(intent);
        }
    }

}
