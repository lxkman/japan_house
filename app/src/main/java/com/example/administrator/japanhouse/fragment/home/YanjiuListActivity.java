package com.example.administrator.japanhouse.fragment.home;

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
import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.YanJiuListBean;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.utils.MyUrls;
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
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yanjiu_list);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
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
                        List<YanJiuListBean.DatasEntity> datas = yanJiuListBean.getDatas();
                        ListAdapter adapter = new ListAdapter(R.layout.item_yanjiu_list, datas);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                startActivity(new Intent(mContext, YanjiuDetailActivity.class));
                            }
                        });
                    }
                });
    }

    private class ListAdapter extends BaseQuickAdapter<YanJiuListBean.DatasEntity, BaseViewHolder> {

        public ListAdapter(int layoutResId, @Nullable List<YanJiuListBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, YanJiuListBean.DatasEntity item) {
            Glide.with(MyApplication.getGloableContext()).load(item.getImageUrl())
                    .into((ImageView) helper.getView(R.id.item_pic_iv));
            helper.setText(R.id.tv_title,item.getYjTitle())
                    .setText(R.id.tv_content,item.getYjContent());
        }
    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
