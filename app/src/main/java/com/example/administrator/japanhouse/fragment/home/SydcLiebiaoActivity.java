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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.example.administrator.japanhouse.fragment.comment.GaoerfuDetailsActivity;
import com.example.administrator.japanhouse.fragment.comment.JiudianDetailsActivity;
import com.example.administrator.japanhouse.fragment.comment.ShangpuDetailsActivity;
import com.example.administrator.japanhouse.fragment.comment.XiezilouDetailsActivity;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.example.administrator.japanhouse.view.MyFooter;
import com.example.administrator.japanhouse.view.MyHeader;
import com.liaoinstan.springview.widget.SpringView;
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
    private List<String> mList = new ArrayList();
    private LiebiaoAdapter liebiaoAdapter;
    private List<OneCheckBean> list;
    private String type;
    private SpringView springview;
    private boolean isLoadMore;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sydc_liebiao);
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
        type = getIntent().getStringExtra("type");
        String headers[];
        if (type.equals("1") || type.equals("3")) {
            headers = new String[]{getResources().getString(R.string.zujin),
                    getResources().getString(R.string.lxkmianji),
                    getResources().getString(R.string.lxkchezhanjuli), getResources().getString(R.string.gengduo)};
        } else {
            headers = new String[]{getResources().getString(R.string.shoujia),
                    getResources().getString(R.string.lxkmianji),
                    getResources().getString(R.string.lxkchezhanjuli), getResources().getString(R.string.gengduo)};
        }
        /**
         * 第一个界面
         * */
        list = new ArrayList<>();
        list.add(new OneCheckBean(false, "不限"));
        if (type.equals("1") || type.equals("3")) {
            list.add(new OneCheckBean(false, "1000-2000元/月"));
            list.add(new OneCheckBean(false, "2000-3000元/月"));
            list.add(new OneCheckBean(false, "3000-4000元/月"));
        }else {
            list.add(new OneCheckBean(false, "3-10万"));
            list.add(new OneCheckBean(false, "6-15万"));
            list.add(new OneCheckBean(false, "10万以上"));
        }
        FirstView firstView = new FirstView(SydcLiebiaoActivity.this);
        popupViews.add(firstView.firstView());
        firstView.insertData(list, dropDownMenu);
        firstView.setListener(this);

        /**
         * 第二个界面
         * */
        List<OneCheckBean> list1 = new ArrayList<>();
        list1.add(new OneCheckBean(false, "不限"));
        list1.add(new OneCheckBean(false, "20平方米以下"));
        list1.add(new OneCheckBean(false, "20-40平方米"));
        list1.add(new OneCheckBean(false, "40-80平方米"));
        list1.add(new OneCheckBean(false, "80-100平方米"));
        list1.add(new OneCheckBean(false, "100平方米以上"));

        SecView secView = new SecView(SydcLiebiaoActivity.this);
        popupViews.add(secView.secView());
        secView.setListener(this);
        secView.insertData(list1, dropDownMenu);

        /**
         * 第三个界面
         * */
        List<OneCheckBean> list2 = new ArrayList<>();
        list2.add(new OneCheckBean(false, "不限"));
        list2.add(new OneCheckBean(false, "100米以内"));
        list2.add(new OneCheckBean(false, "200米以内"));
        list2.add(new OneCheckBean(false, "500米以内"));
        list2.add(new OneCheckBean(false, "1000米以内"));
        list2.add(new OneCheckBean(false, "2000米以内"));
        SecView threeView = new SecView(SydcLiebiaoActivity.this);
        popupViews.add(threeView.secView());
        threeView.insertData(list2, dropDownMenu);
        threeView.setListener(this);
        /**
         * 第四个界面
         * */
        List<OneCheckBean> list3 = new ArrayList<>();
        list3.add(new OneCheckBean(false, "不限"));
        list3.add(new OneCheckBean(false, "商业街"));
        list3.add(new OneCheckBean(false, "购物中心"));
        list3.add(new OneCheckBean(false, "商业街"));
        list3.add(new OneCheckBean(false, "购物中心"));
        list3.add(new OneCheckBean(false, "商业街"));
        SecView fourView = new SecView(SydcLiebiaoActivity.this);
        popupViews.add(fourView.secView());
        fourView.insertData(list3, dropDownMenu);
        fourView.setListener(this);

        /**
         * Dropdownmenu下面的主体部分
         * */
        View fifthView = LayoutInflater.from(SydcLiebiaoActivity.this).inflate(R.layout.activity_main_view, null);
        mrecycler = (RecyclerView) fifthView.findViewById(R.id.mrecycler);
        springview = (SpringView) fifthView.findViewById(R.id.springview);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, fifthView);
    }

    private void initData() {
        if (mList.size() <= 0) {
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
        }
        liebiaoAdapter = new LiebiaoAdapter(R.layout.item_home_sydc, mList);
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mrecycler.setAdapter(liebiaoAdapter);
        liebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (type) {
                    case "0":
                        startActivity(new Intent(mContext, ShangpuDetailsActivity.class));
                        break;
                    case "1":
                        Intent intent = new Intent(mContext, ShangpuDetailsActivity.class);
                        intent.putExtra("iszu", "iszu");
                        startActivity(intent);
                        break;
                    case "2":
                        startActivity(new Intent(mContext, XiezilouDetailsActivity.class));
                        break;
                    case "3":
                        Intent intent1 = new Intent(mContext, ShangpuDetailsActivity.class);
                        intent1.putExtra("iszu", "iszu");
                        startActivity(intent1);
                        break;
                    case "4":
                        startActivity(new Intent(mContext, GaoerfuDetailsActivity.class));
                        break;
                    case "5":
                        startActivity(new Intent(mContext, JiudianDetailsActivity.class));
                        break;
                }
            }
        });

    }

    class LiebiaoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView tv_price = helper.getView(R.id.tv_price);
            if (type.equals("1") || type.equals("3")) {
                tv_price.setText("1750元/月");
            }
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
                startActivity(new Intent(mContext, HomeSearchActivity.class));
        }
    }

    @Override
    public void onItemClick(View view, int postion, String string) {

    }
}
