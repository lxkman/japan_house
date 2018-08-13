package com.haiwai.administrator.japanhouse.fragment.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.bean.MsgBean;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.haiwai.administrator.japanhouse.utils.MyUtils;
import com.haiwai.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.haiwai.administrator.japanhouse.view.BaseDialog;
import com.haiwai.administrator.japanhouse.view.MyFooter;
import com.haiwai.administrator.japanhouse.view.MyHeader;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DingYueActivity extends BaseActivity {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.mrecycler)
    SwipeMenuRecyclerView mrecycler;
    private List<String> mList=new ArrayList();
    private LiebiaoAdapter liebiaoAdapter;
    private String token;
    private SpringView springView;
    private int pageNo = 1;
    private boolean isLoadMore;
    private List<MsgBean.DatasBean> mRefreshData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_yue);
        ButterKnife.bind(this);
        token = SharedPreferencesUtils.getInstace(this).getStringPreference("token", "");
        getMsg();
        initRefresh();
    }
    private void initRefresh() {
        springView = (SpringView) findViewById(R.id.act_history_springView);
        springView.setHeader(new MyHeader(mContext));
        springView.setFooter(new MyFooter(mContext));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                pageNo = 1;
                getMsg();
                springView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                isLoadMore = true;
                pageNo++;
                getMsg();
                springView.onFinishFreshAndLoad();
            }
        });
    }

    private void getMsg() {
        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        params.put("token", token);//用户登录标识
        params.put("noticeType", "0");
        OkGo.<MsgBean>post(MyUrls.BASEURL + "/app/noticeinfo/msglist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<MsgBean>(this, MsgBean.class) {
                    @Override
                    public void onSuccess(Response<MsgBean> response) {
                        int code = response.code();
                        final MsgBean msgBean = response.body();
                        String code1 = msgBean.getCode();
                        List<MsgBean.DatasBean> datas = msgBean.getDatas();
                        if (mRefreshData == null || mRefreshData.size() == 0) {
                            if (datas == null || datas.size() == 0) {
                                Toast.makeText(mContext, "无数据~", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            mRefreshData = datas;
                            liebiaoAdapter = new LiebiaoAdapter(R.layout.item_dingyue,mRefreshData);
                            mrecycler.setNestedScrollingEnabled(false);
                            mrecycler.setLayoutManager(new LinearLayoutManager(DingYueActivity.this,LinearLayoutManager.VERTICAL,false));
                            // 设置监听器。
                            mrecycler.setSwipeMenuCreator(mSwipeMenuCreator);

                            mrecycler.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
                                @Override
                                public void onItemClick(SwipeMenuBridge menuBridge) {
                                    menuBridge.closeMenu();
                                    shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion, menuBridge.getAdapterPosition());
                                }
                            });
                            mrecycler.setAdapter(liebiaoAdapter);
                            if (datas == null || datas.size() == 0) {
                                Toast.makeText(mContext, R.string.meiyougengduoshujule, Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (!isLoadMore) {
                                mRefreshData = datas;
                                Toast.makeText(mContext, R.string.shuaxinchenggong, Toast.LENGTH_SHORT).show();
                            } else {
                                mRefreshData.addAll(datas);
                            }
                            liebiaoAdapter.notifyDataSetChanged();

                        }
                    }
                });
    }


    // 创建菜单:
    SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
//            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext); // 各种文字和图标属性设置。
//            leftMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。
            SwipeMenuItem deleteItem = new SwipeMenuItem(DingYueActivity.this); // 各种文字和图标属性设置。
            deleteItem.setWeight(MyUtils.dip2px(DingYueActivity.this,50));
            deleteItem.setHeight(MyUtils.dip2px(DingYueActivity.this,150));
            deleteItem.setText("   取消订阅   ");
            deleteItem.setTextSize(14);
            deleteItem.setBackgroundColor(getResources().getColor(R.color.red1));
            deleteItem.setTextColor(Color.WHITE);
            rightMenu.addMenuItem(deleteItem); // 在Item右侧添加一个菜单。
            // 注意:哪边不想要菜单,那么不要添加即可。
        }
    };



    class LiebiaoAdapter extends BaseQuickAdapter<MsgBean.DatasBean, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<MsgBean.DatasBean> data) {
            super(layoutResId,data);
        }

        @Override
        protected void convert(BaseViewHolder helper,MsgBean.DatasBean item) {
            helper.setText(R.id.text_title,item.getTitle());
            helper.setText(R.id.text_time,MyUtils.getDateToStringY(item.getCreateTime()+""));
            helper.setText(R.id.text_neirong,item.getContent());
        }
    }

    @OnClick(R.id.back_img)
    public void onClick() {
        finish();
    }



    private void shumaDialog(int grary, int animationStyle, final int postion) {
        BaseDialog.Builder builder = new BaseDialog.Builder(DingYueActivity.this);
        final BaseDialog dialog = builder.setViewId(R.layout.dingyue_layout)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        TextView text_sure = dialog.getView(R.id.text_sure);

        TextView text_pause = dialog.getView(R.id.text_pause);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemoveMsg(mRefreshData.get(postion).getId());
                liebiaoAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        //取消
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void RemoveMsg(int id) {
        HttpParams params = new HttpParams();
        params.put("id", id);
        OkGo.<MsgBean>post(MyUrls.BASEURL + "/app/usernotice/deletenotice")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<MsgBean>(this, MsgBean.class) {
                    @Override
                    public void onSuccess(Response<MsgBean> response) {
                        int code = response.code();
                        final MsgBean msgBean = response.body();
                        String code1 = msgBean.getCode();
                        mRefreshData = msgBean.getDatas();
                        if (code1.equals("200")) {
                            getMsg();
                            Log.d("MineMsgActivity", "取消订阅成功-----------");
                        } else {
                            Toast.makeText(DingYueActivity.this, code1, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
