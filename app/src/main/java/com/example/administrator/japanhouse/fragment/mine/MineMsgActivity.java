package com.example.administrator.japanhouse.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.MsgBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.example.administrator.japanhouse.view.MyFooter;
import com.example.administrator.japanhouse.view.MyHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineMsgActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.recycler_msg)
    RecyclerView recyclerMsg;
    @BindView(R.id.activity_zui_jin)
    LinearLayout activityZuiJin;
    @BindView(R.id.tv_Nodata)
    TextView tvNodata;
    private String token;
    private MsgAdapter msgAdapter;
    private SpringView springView;
    private int pageNo = 1;
    private boolean isLoadMore;
    private List<MsgBean.DatasBean> mRefreshData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_msg);
        ButterKnife.bind(this);
        backImg.setOnClickListener(this);
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
        params.put("noticeType", "");
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
                                tvNodata.setVisibility(View.VISIBLE);
                                return;
                            }
                            mRefreshData = datas;
                            msgAdapter = new MsgAdapter(R.layout.item_msg, mRefreshData);
                            recyclerMsg.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                            recyclerMsg.setNestedScrollingEnabled(false);
                            recyclerMsg.setAdapter(msgAdapter);
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
                            msgAdapter.notifyDataSetChanged();

                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img:
                finish();
                break;
        }
    }

    private class MsgAdapter extends BaseQuickAdapter<MsgBean.DatasBean, BaseViewHolder> {

        public MsgAdapter(@LayoutRes int layoutResId, @Nullable List<MsgBean.DatasBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final MsgBean.DatasBean item) {
            ImageView img_isread = helper.getView(R.id.img_isread);
            if (item.getIsRead().equals("0")) {//已读
                img_isread.setVisibility(View.INVISIBLE);
            } else {//未读
                img_isread.setVisibility(View.VISIBLE);
            }
            helper.setText(R.id.tv_content, item.getContent());
            helper.getView(R.id.content).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MineMsgActivity.this, MsgDetailActivity.class);
                    intent.putExtra("content", item.getContent() + "");
                    intent.putExtra("time", item.getCreateTime() + "");
                    intent.putExtra("msgid", item.getId() + "");
                    startActivityForResult(intent, 1);
                }
            });
            helper.getView(R.id.right).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RemoveMsg(item.getId());
                    msgAdapter.notifyDataSetChanged();
                }
            });

        }
    }

    private void RemoveMsg(int id) {
        HttpParams params = new HttpParams();
        params.put("id", id);
        OkGo.<MsgBean>post(MyUrls.BASEURL + "/app/noticeinfo/deletenotice")
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
                            Log.d("MineMsgActivity", "删除成功-----------");
                        } else {
                            Toast.makeText(MineMsgActivity.this, code1, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            getMsg();
        }
    }
}
