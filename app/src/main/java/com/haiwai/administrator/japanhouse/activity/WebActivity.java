package com.haiwai.administrator.japanhouse.activity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WebActivity extends BaseActivity {
    @BindView(R.id.img_beak)
    ImageView imgBeak;
    @BindView(R.id.web_title)
    TextView webTitle;
    private WebView webView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        String result = getIntent().getStringExtra("result");
        String title = getIntent().getStringExtra("title");
        webView = (WebView) findViewById(R.id.webView);
        webTitle.setText(title);
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
//                Toast.makeText(WebActivity.this, "加载出错了", Toast.LENGTH_SHORT).show();
            }
        });
        webView.loadUrl(result);


    }

    @OnClick(R.id.img_beak)
    public void onViewClicked() {
        finish();
    }
}
