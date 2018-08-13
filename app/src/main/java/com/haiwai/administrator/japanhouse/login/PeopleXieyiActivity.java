package com.haiwai.administrator.japanhouse.login;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PeopleXieyiActivity extends BaseActivity {

    @BindView(R.id.back_img)
    ImageView backImg;
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_xieyi);
        ButterKnife.bind(this);
        initWeb();
    }
    private void initWeb() {
        webView = (WebView) findViewById(R.id.webView);
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
                Toast.makeText(PeopleXieyiActivity.this, "加载出错了", Toast.LENGTH_SHORT).show();
            }
        });
        webView.loadUrl("http://39.106.131.61:8080/hwdch5/info/AgreementCn.html?type=0");


    }
    @OnClick(R.id.back_img)
    public void onClick() {
        finish();
    }
}
