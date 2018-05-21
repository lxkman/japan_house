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

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.bean.OldHouseListBean;
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.utils.CacheUtils;
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

public class BieShuActivity extends BaseActivity implements MyItemClickListener{
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
    private int page = 1;
    private boolean isJa;
    private List<OldHouseListBean.DatasEntity> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ershoufang_activiy);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        //        mSpringview.setType(SpringView.Type.FOLLOW);
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
        String[] headers = {getString(R.string.quyu), getString(R.string.lxkmianji),
                getString(R.string.shoujia), getString(R.string.gengduo)};
        /**
         * 第一个界面
         * */
        list = new ArrayList<>();
        FirstView firstView = new FirstView(this);
        popupViews.add(firstView.firstView());
        firstView.insertData(list, dropDownMenu);
        firstView.setListener(this);

        /**
         * 第二个界面
         * */
        List<OneCheckBean> list1 = new ArrayList<>();
        list1.add(new OneCheckBean(false, "不限"));
        list1.add(new OneCheckBean(false, "80以下"));
        list1.add(new OneCheckBean(false, "80-100"));
        list1.add(new OneCheckBean(false, "100-150"));
        list1.add(new OneCheckBean(false, "300以上"));
        SecView secView = new SecView(this);
        popupViews.add(secView.secView());
        secView.setListener(this);
        secView.insertData(list1, dropDownMenu);

        /**
         * 第三个界面
         * */
        List<OneCheckBean> list2 = new ArrayList<>();
        list2.add(new OneCheckBean(false, "不限"));
        list2.add(new OneCheckBean(false, "3-10万"));
        list2.add(new OneCheckBean(false, "6-15万"));
        list2.add(new OneCheckBean(false, "10万以上"));
        ThreeView threeView = new ThreeView(this);
        popupViews.add(threeView.firstView());
        threeView.insertData(list2, dropDownMenu);
        threeView.setListener(this);
        /**
         * 第四个界面
         * */
        List<OneCheckBean> list3 = new ArrayList<>();
        list3.add(new OneCheckBean(false, "构造"));
        list3.add(new OneCheckBean(false, "地段"));
        list3.add(new OneCheckBean(false, "朝向"));
        list3.add(new OneCheckBean(false, "面积(平米)"));
        list3.add(new OneCheckBean(false, "室内设施"));
        MoreView fourView = new MoreView(this);
        popupViews.add(fourView.secView());
        fourView.insertData(list3, dropDownMenu);
        fourView.setListener(this);
        /**
         * Dropdownmenu下面的主体部分
         * */
        View fifthView = LayoutInflater.from(this).inflate(R.layout.activity_main_view, null);
        mrecycler = (RecyclerView) fifthView.findViewById(R.id.mrecycler);
        springview = (SpringView) fifthView.findViewById(R.id.springview);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, fifthView);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mrecycler.setNestedScrollingEnabled(false);
    }

    private void initData() {
        if (mList.size() <= 0) {
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
        }
        String city = CacheUtils.get(Constants.COUNTRY);
        HttpParams params = new HttpParams();
        if (city != null && city.equals("ja")) {
            params.put("languageType", 1);
            isJa = true;
        } else {
            params.put("languageType", 0);
            isJa = false;
        }
        params.put("status", 0);
        params.put("pageNo", page);
        OkGo.<OldHouseListBean>post(MyUrls.BASEURL + "/app/houseresourse/searchlist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<OldHouseListBean>(BieShuActivity.this, OldHouseListBean.class) {
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
                                Toast.makeText(BieShuActivity.this, "无数据~", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            mDatas = datas;
                            liebiaoAdapter = new LiebiaoAdapter(R.layout.item_home_ershoufang, mDatas);
                            mrecycler.setAdapter(liebiaoAdapter);
                        } else {
                            if (datas == null || datas.size() == 0) {
                                Toast.makeText(BieShuActivity.this, "没有更多数据了~", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (!isLoadMore) {
                                mDatas = datas;
                                Toast.makeText(BieShuActivity.this, "刷新成功~", Toast.LENGTH_SHORT).show();
                            } else {
                                mDatas.addAll(datas);
                            }
                            liebiaoAdapter.notifyDataSetChanged();
                        }
                        liebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(BieShuActivity.this, BieshudetailsActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                });
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
                    .setText(R.id.tv_price, isJa ? item.getPriceJpn() : item.getPriceCn());
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
                intent.putExtra("popcontent",getResources().getString(R.string.bieshu));
                startActivity(intent);
        }
    }

    @Override
    public void onItemClick(View view, int postion, String string) {

    }
}
