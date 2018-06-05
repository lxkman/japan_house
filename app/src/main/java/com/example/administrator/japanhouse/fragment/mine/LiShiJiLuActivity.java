package com.example.administrator.japanhouse.fragment.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.ZuHistroyBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.fragment.comment.NewHousedetailsActivity;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.SpUtils;
import com.example.administrator.japanhouse.view.MyFooter;
import com.example.administrator.japanhouse.view.MyHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
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

public class LiShiJiLuActivity extends BaseActivity {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.mrecycler)
    SwipeMenuRecyclerView mrecycler;
    @BindView(R.id.springview)
    SpringView springview;
    private LiebiaoAdapter liebiaoAdapter;
    private int page = 1;
    private boolean isJa;
    private boolean isLoadMore;
    private List<ZuHistroyBean.DatasEntity> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_shi);
        ButterKnife.bind(this);
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        mrecycler.setNestedScrollingEnabled(false);
        mrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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

    private void initData() {
        int houseType = getIntent().getIntExtra("houseType", 0);
        HttpParams params = new HttpParams();
        params.put("pageNo", page);
        params.put("shType", houseType);
        params.put("token", SpUtils.getString("token", ""));
        OkGo.<ZuHistroyBean>post(MyUrls.BASEURL + "/app/seehouselog/seezfhouselogs")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<ZuHistroyBean>(LiShiJiLuActivity.this, ZuHistroyBean.class) {
                    @Override
                    public void onSuccess(Response<ZuHistroyBean> response) {
                        int code = response.code();
                        ZuHistroyBean body = response.body();
                        final List<ZuHistroyBean.DatasEntity> datas = body.getDatas();
                        if (mDatas == null || mDatas.size() == 0) {
                            if (datas == null || datas.size() == 0) {
                                Toast.makeText(LiShiJiLuActivity.this, "无数据~", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(LiShiJiLuActivity.this, "没有更多数据了~", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (!isLoadMore) {
                                mDatas = datas;
                                Toast.makeText(LiShiJiLuActivity.this, "刷新成功~", Toast.LENGTH_SHORT).show();
                            } else {
                                mDatas.addAll(datas);
                            }
                            liebiaoAdapter.notifyDataSetChanged();
                        }
                        mrecycler.setSwipeMenuCreator(mSwipeMenuCreator);

                        mrecycler.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
                            @Override
                            public void onItemClick(SwipeMenuBridge menuBridge) {
                                datas.remove(menuBridge.getAdapterPosition());
                                menuBridge.closeMenu();
                                liebiaoAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
    }

    // 创建菜单:
    SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
            //            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext); // 各种文字和图标属性设置。
            //            leftMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。
            SwipeMenuItem deleteItem = new SwipeMenuItem(LiShiJiLuActivity.this); // 各种文字和图标属性设置。
            deleteItem.setWeight(100);
            deleteItem.setHeight(380);
            deleteItem.setText(getResources().getString(R.string.shanchu));
            deleteItem.setTextSize(14);
            deleteItem.setBackgroundColor(getResources().getColor(R.color.red1));
            deleteItem.setTextColor(Color.WHITE);
            rightMenu.addMenuItem(deleteItem); // 在Item右侧添加一个菜单。
            // 注意:哪边不想要菜单,那么不要添加即可。
        }
    };


    class LiebiaoAdapter extends BaseQuickAdapter<ZuHistroyBean.DatasEntity, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<ZuHistroyBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ZuHistroyBean.DatasEntity item) {
            helper.getView(R.id.layout_all_height).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(LiShiJiLuActivity.this, NewHousedetailsActivity.class));
                }
            });
            Glide.with(MyApplication.getGloableContext()).load(item.getImageUrl())
                    .into((ImageView) helper.getView(R.id.img_house));
            helper.setText(R.id.tv_house_name, isJa ? item.getTitleJpn() : item.getTitleCn())
                    .setText(R.id.tv_house_address, isJa ? item.getAddressJpn() : item.getAddressCn())
                    .setText(R.id.tv_house_area, isJa ? item.getAreaJpn() : item.getAreaCn())
                    .setText(R.id.tv_price, isJa ? item.getPriceJpn() + "元/月" : item.getPriceCn() + "元/月")
                    .setText(R.id.tv_house_room, isJa ? item.getDoorModelJpn() : item.getDoorModelCn());
        }
    }

    @OnClick(R.id.back_img)
    public void onClick() {
        finish();
    }
}
