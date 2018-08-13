package com.haiwai.administrator.japanhouse.fragment.mine.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.bean.ZuHistroyBean;
import com.haiwai.administrator.japanhouse.fragment.comment.ZuHousedetailsActivity;
import com.haiwai.administrator.japanhouse.presenter.ZuHistroyPresenter;
import com.haiwai.administrator.japanhouse.utils.CacheUtils;
import com.haiwai.administrator.japanhouse.utils.Constants;
import com.haiwai.administrator.japanhouse.utils.TUtils;
import com.haiwai.administrator.japanhouse.view.MyFooter;
import com.haiwai.administrator.japanhouse.view.MyHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.model.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LiShiJiLu2Activity extends BaseActivity implements ZuHistroyPresenter.ZuHistroyCallBack {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.mrecycler)
    SwipeMenuRecyclerView mrecycler;
    @BindView(R.id.springview)
    SpringView springview;
    @BindView(R.id.tv_noContent)
    TextView tvNoContent;
    private LiebiaoAdapter liebiaoAdapter;
    private int page = 1;
    private boolean isJa;
    private boolean isLoadMore;
    private List<ZuHistroyBean.DatasEntity> mDatas;
    private ZuHistroyPresenter presenter;
    private int houseType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_shi2);
        ButterKnife.bind(this);
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        presenter = new ZuHistroyPresenter(this,this);
        houseType = getIntent().getIntExtra("houseType", 0);
        presenter.getZuHistroyList(page, houseType);
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
                presenter.getZuHistroyList(page, houseType);
                springview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                isLoadMore = true;
                page++;
                presenter.getZuHistroyList(page, houseType);
                springview.onFinishFreshAndLoad();
            }
        });
    }

    // 创建菜单:
    SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
            //            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext); // 各种文字和图标属性设置。
            //            leftMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。
            SwipeMenuItem deleteItem = new SwipeMenuItem(LiShiJiLu2Activity.this); // 各种文字和图标属性设置。
            deleteItem.setWidth(150);
            deleteItem.setHeight(380);
            deleteItem.setText(getResources().getString(R.string.shanchu));
            deleteItem.setTextSize(14);
            deleteItem.setBackgroundColor(getResources().getColor(R.color.red1));
            deleteItem.setTextColor(Color.WHITE);
            rightMenu.addMenuItem(deleteItem); // 在Item右侧添加一个菜单。
            // 注意:哪边不想要菜单,那么不要添加即可。
        }
    };

    @Override
    public void getZuHistroyList(Response<ZuHistroyBean> response) {
        ZuHistroyBean body = response.body();
        final List<ZuHistroyBean.DatasEntity> datas = body.getDatas();
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
            liebiaoAdapter = new LiebiaoAdapter(R.layout.item_zuijin, datas);
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
        mrecycler.setSwipeMenuCreator(mSwipeMenuCreator);

        mrecycler.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                presenter.deteleHouseRecord(mDatas.get(menuBridge.getPosition()).getId(),houseType);
                datas.remove(menuBridge.getAdapterPosition());
                menuBridge.closeMenu();
                liebiaoAdapter.notifyDataSetChanged();
            }
        });
    }


    class LiebiaoAdapter extends BaseQuickAdapter<ZuHistroyBean.DatasEntity, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<ZuHistroyBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, ZuHistroyBean.DatasEntity item) {
            helper.getView(R.id.layout_all_height).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ZuHousedetailsActivity.class);
                    String houseType = mData.get(helper.getAdapterPosition()).getShType();
                    if (houseType.equals("5")) {
                        intent.putExtra("houseType", "duoceng");
                    } else if (houseType.equals("4")) {
                        intent.putExtra("houseType", "xuesheng");
                    } else if (houseType.equals("3")) {
                        intent.putExtra("houseType", "erceng");
                    } else if (houseType.equals("2")) {
                        intent.putExtra("houseType", "bieshu");
                    } else if (houseType.equals("1")) {
                        intent.putExtra("houseType", "shangpu");
                    } else if (houseType.equals("0")) {
                        intent.putExtra("houseType", "bangongshi");
                    } else if (houseType.equals("6")) {
                        intent.putExtra("houseType", "zhaotuandi");
                    }
                    intent.putExtra("houseId", mData.get(helper.getAdapterPosition()).getId() + "");
                    startActivity(intent);
                }
            });
            Glide.with(MyApplication.getGloableContext()).load(item.getImageUrl())
                    .into((ImageView) helper.getView(R.id.img_house));
            helper.setText(R.id.tv_house_name, isJa ? item.getTitleJpn() : item.getTitleCn())
                    .setText(R.id.tv_house_address, isJa ? item.getAddressJpn() : item.getAddressCn())
                    .setText(R.id.tv_house_area, isJa ? item.getAreaJpn() : item.getAreaCn())
                    .setText(R.id.tv_price, isJa ? item.getPriceJpn() : item.getPriceCn())
                    .setText(R.id.tv_house_room, isJa ? item.getDoorModelJpn() : item.getDoorModelCn());
        }
    }

    @OnClick(R.id.back_img)
    public void onClick() {
        finish();
    }
}
