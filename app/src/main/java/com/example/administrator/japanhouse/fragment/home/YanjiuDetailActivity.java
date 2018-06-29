package com.example.administrator.japanhouse.fragment.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.YanJiuDetailBean;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
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
    @BindView(R.id.webView)
    WebView webView;
    private boolean isJa;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yanjiu_detail);
        ButterKnife.bind(this);
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        String yjType = getIntent().getStringExtra("yjType");
        if (TextUtils.isEmpty(yjType)) {
            id = getIntent().getIntExtra("id", 0);
            goWeb();
            //            titleTv.setText(getIntent().getStringExtra("title"));
            //            contentTv.setText(getIntent().getStringExtra("content"));
            //            long createTime = getIntent().getLongExtra("time", 0);
            //            String s = MyUtils.longtoStringDate(createTime);
            //            dateTv.setText(s);
            //            return;
        } else {
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
                            if (datas == null) {
                                return;
                            }
                            //                            titleTv.setText(datas.getYjTitle());
                            //                            contentTv.setText(datas.getYjContent());
                            //                            long createTime = datas.getCreateTime();
                            //                            String s = MyUtils.longtoStringDate(createTime);
                            //                            dateTv.setText(s);
                            id = datas.getId();
                            goWeb();
                        }
                    });
        }
    }

    private void goWeb(){
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    //                    Toast.makeText(WebActivity.this, "网页加载完成", Toast.LENGTH_SHORT).show();

                } else {

                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(YanjiuDetailActivity.this, "加载出错了", Toast.LENGTH_SHORT).show();
            }
        });
        String url;
        if (isJa) {
            url = "http://39.106.131.61:8080/hwdch5/info/research.html?id=" + id;
        } else {
            url = "http://39.106.131.61:8080/hwdch5/info/researchCn.html?id=" + id;
        }
        webView.loadUrl(url);
    }

    @OnClick(R.id.title_back_iv)
    public void onViewClicked() {
        finish();
    }
}
