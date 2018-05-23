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
import com.example.administrator.japanhouse.fragment.comment.ZhongguoDetailsActivity;
import com.example.administrator.japanhouse.utils.Constants;
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

import static com.example.administrator.japanhouse.R.string.shoujia;

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
    private int page;

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
        String[] headers = {getString(R.string.quyu), getString(shoujia),
                getString(R.string.huxing), getString(R.string.gengduo)};
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
        list1.add(new OneCheckBean(false, "3-10万"));
        list1.add(new OneCheckBean(false, "6-15万"));
        list1.add(new OneCheckBean(false, "10万以上"));
        ThreeView secView = new ThreeView(this);
        popupViews.add(secView.firstView());
        secView.setListener(this);
        secView.insertData(list1, dropDownMenu);

        /**
         * 第三个界面
         * */
        List<OneCheckBean> list2 = new ArrayList<>();
        list2.add(new OneCheckBean(false, "不限"));
        list2.add(new OneCheckBean(false, "一室"));
        list2.add(new OneCheckBean(false, "二室"));
        list2.add(new OneCheckBean(false, "三室"));
        list2.add(new OneCheckBean(false, "三室以上"));
        SecView threeView = new SecView(this);
        popupViews.add(threeView.secView());
        threeView.insertData(list2, dropDownMenu);
        threeView.setListener(this);
        /**
         * 第四个界面
         * */
        List<OneCheckBean> list3 = new ArrayList<>();
        //        list3.add(new OneCheckBean(false, "区域"));
        list3.add(new OneCheckBean(false, "构造"));
        list3.add(new OneCheckBean(false, "地段"));
        list3.add(new OneCheckBean(false, "朝向"));
        list3.add(new OneCheckBean(false, "面积(平米)"));
        list3.add(new OneCheckBean(false, "室内设施"));
        MoreView fourView = new MoreView(ChineseLiebiaoActivity.this);
        popupViews.add(fourView.secView());
        fourView.insertData(list3, dropDownMenu);
        fourView.setListener(this);

        /**
         * Dropdownmenu下面的主体部分
         * */
        View fifthView = LayoutInflater.from(ChineseLiebiaoActivity.this).inflate(R.layout.activity_main_view, null);
        mrecycler = (RecyclerView) fifthView.findViewById(R.id.mrecycler);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, fifthView);
        springview = (SpringView) fifthView.findViewById(R.id.springview);
    }

    private void initData() {
        final String type = getIntent().getStringExtra("type");

        if (mList.size() <= 0) {
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
        }
        liebiaoAdapter = new LiebiaoAdapter(R.layout.item_china_like, mList);
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mrecycler.setAdapter(liebiaoAdapter);
        liebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //                startActivity(new Intent(mContext, ZuHousedetailsActivity.class));
                startActivity(new Intent(mContext, ZhongguoDetailsActivity.class));
                /*switch (type){
                    case "0":
                        startActivity(new Intent(mContext, ShangpuDetailsActivity.class));
                        break;
                    case "1":
                        startActivity(new Intent(mContext, ShangpuDetailsActivity.class));
                        break;
                    case "2":
                        startActivity(new Intent(mContext, XiezilouDetailsActivity.class));
                        break;
                    case "3":
                        startActivity(new Intent(mContext, ShangpuDetailsActivity.class));
                        break;
                    case "4":
                        startActivity(new Intent(mContext, GaoerfuDetailsActivity.class));
                        break;
                    case "5":
                        startActivity(new Intent(mContext, JiudianDetailsActivity.class));
                        break;
                }*/
            }
        });

    }

    @Override
    public void onItemClick(View view, int postion, int itemPosition) {

    }

    @Override
    public void onItemClick(View view, int postion, List<String> priceRegin) {

    }

    @Override
    public void onMoreItemClick(View view, List<List<String>> moreSelectedBeanList) {

    }

    class LiebiaoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

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
