package com.haiwai.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.bean.YanJiuListBean;
import com.haiwai.administrator.japanhouse.callback.JsonCallback;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.haiwai.administrator.japanhouse.utils.TUtils;
import com.haiwai.administrator.japanhouse.view.MyFooter;
import com.haiwai.administrator.japanhouse.view.MyHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YanjiuListActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.springview)
    SpringView springview;
    @BindView(R.id.tv_noContent)
    TextView tvNoContent;
    private int page = 1;
    private boolean isLoadMore;
    private List<YanJiuListBean.DatasEntity> mDatas;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yanjiu_list);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        HttpParams params = new HttpParams();
        params.put("pageNo", page);
        OkGo.<YanJiuListBean>post(MyUrls.BASEURL + "/app/zfyjs/selectlist")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<YanJiuListBean>(YanJiuListBean.class) {
                             @Override
                             public void onSuccess(Response<YanJiuListBean> response) {
                                 int code = response.code();
                                 YanJiuListBean yanJiuListBean = response.body();
                                 if (yanJiuListBean == null) {
                                     return;
                                 }
                                 final List<YanJiuListBean.DatasEntity> datas = yanJiuListBean.getDatas();
                                 tvNoContent.setVisibility(View.GONE);
                                 springview.setVisibility(View.VISIBLE);
                                 if (mDatas == null || mDatas.size() == 0) {
                                     if (datas == null || datas.size() == 0) {
                                         tvNoContent.setVisibility(View.VISIBLE);
                                         springview.setVisibility(View.GONE);
                                         if (listAdapter != null) {
                                             listAdapter.notifyDataSetChanged();
                                         }
                                         return;
                                     }
                                     mDatas = datas;
                                     listAdapter = new ListAdapter(R.layout.item_yanjiu_list, datas);
                                     recyclerView.setAdapter(listAdapter);
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
                                     listAdapter.notifyDataSetChanged();
                                 }
                                 listAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                     @Override
                                     public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                         Intent intent = new Intent(mContext, YanjiuDetailActivity.class);
                                         intent.putExtra("id", datas.get(position).getId());
//                                         intent.putExtra("title", datas.get(position).getYjTitle() + "");
//                                         intent.putExtra("content", datas.get(position).getYjContent() + "");
//                                         intent.putExtra("time", datas.get(position).getCreateTime());
                                         startActivity(intent);
                                     }
                                 });
                             }
                         }
                );
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
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    private class ListAdapter extends BaseQuickAdapter<YanJiuListBean.DatasEntity, BaseViewHolder> {

        public ListAdapter(int layoutResId, @Nullable List<YanJiuListBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, YanJiuListBean.DatasEntity item) {
            Glide.with(MyApplication.getGloableContext()).load(item.getImageUrl())
                    .into((ImageView) helper.getView(R.id.item_pic_iv));
            helper.setText(R.id.tv_title, item.getYjTitle())
                    .setText(R.id.tv_content, item.getYjContent());
        }
    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
