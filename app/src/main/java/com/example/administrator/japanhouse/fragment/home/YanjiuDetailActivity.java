package com.example.administrator.japanhouse.fragment.home;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.YanJiuDetailBean;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YanjiuDetailActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.date_tv)
    TextView dateTv;
    @BindView(R.id.content_tv)
    TextView contentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yanjiu_detail);
        ButterKnife.bind(this);
        String yjType = getIntent().getStringExtra("yjType");
        HttpParams params = new HttpParams();
        params.put("yjType", yjType);
        OkGo.<YanJiuDetailBean>post(MyUrls.BASEURL + "/app/zfyjs/topzfyjsinfo")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<YanJiuDetailBean>(YanJiuDetailBean.class) {
                    @Override
                    public void onSuccess(Response<YanJiuDetailBean> response) {
                        int code = response.code();
                        YanJiuDetailBean yanJiuDetailBean = response.body();
                        YanJiuDetailBean.DatasEntity datas = yanJiuDetailBean.getDatas();
                        if (datas==null){
                            return;
                        }
                        titleTv.setText(datas.getYjTitle());
                        contentTv.setText(datas.getYjContent());
                        long createTime = datas.getCreateTime();
                        String s = MyUtils.longtoStringDate(createTime);
                        dateTv.setText(s);
                    }
                });
    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
