package com.haiwai.administrator.japanhouse.fragment.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.bean.SuccessBean;
import com.haiwai.administrator.japanhouse.bean.TouDetailsBean;
import com.haiwai.administrator.japanhouse.bean.TouListBean;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.fragment.home.ui.adapter.ToutiaoAdapter;
import com.haiwai.administrator.japanhouse.login.LoginActivity;
import com.haiwai.administrator.japanhouse.presenter.TopLinePresenter;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.haiwai.administrator.japanhouse.utils.MyUtils;
import com.haiwai.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;

public class TouDetailActivity extends AppCompatActivity implements View.OnClickListener, ToutiaoAdapter.OnItemPraiseListener {

    private ImageView img_beak;
    private TextView title;
    private TextView time;
    private RecyclerView detail_recy;
    private TextView send;
    private ImageView biaoqing;
    private EditText ed_pinglun;
    private String pinglun;
    private String tid;
    private String token;

    private TopLinePresenter presenter;
    private String contentCn;
    private String contentJpn;
    private Long createTime;
    private String titleCn;
    private String titleJpn;
    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tou_detail);

        presenter = new TopLinePresenter();
        initView();
        initTouNet();
        initplNet();
    }

    private void initTouNet() {
        HttpParams params = new HttpParams();
        params.put("tId", tid + "");
        OkGo.<TouDetailsBean>post(MyUrls.BASEURL + "/app/topline/toplineinfo")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<TouDetailsBean>(this, TouDetailsBean.class) {
                    @Override
                    public void onSuccess(Response<TouDetailsBean> response) {
                        int code = response.code();
                        TouDetailsBean TouDetailsBean = response.body();
                        if (code == 200 && TouDetailsBean.getCode().equals("200")) {
                            contentCn = TouDetailsBean.getDatas().getContentCn();
                            contentJpn = TouDetailsBean.getDatas().getContentJpn();
                            createTime = TouDetailsBean.getDatas().getCreateTime();
                            titleCn = TouDetailsBean.getDatas().getTitleCn();
                            titleJpn = TouDetailsBean.getDatas().getTitleJpn();
                            boolean ja = MyUtils.isJa();
                            title.setText(ja?titleJpn:titleCn);
                            time.setText(MyUtils.getDateTimeFromMillisecond(createTime));
                            mWebview.loadData(ja?contentJpn:contentCn, "text/html;charset=UTF-8", null);
                        }
                    }
                });
    }

    private void initView() {
        tid = getIntent().getStringExtra("Tid");

        img_beak = (ImageView) findViewById(R.id.img_beak);
        this.title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time);
        mWebview = (WebView) findViewById(R.id.webView);
        detail_recy = (RecyclerView) findViewById(R.id.detail_recy);
        send = (TextView) findViewById(R.id.send);
        biaoqing = (ImageView) findViewById(R.id.biaoqing);
        ed_pinglun = (EditText) findViewById(R.id.ed_pinglun);
        img_beak.setOnClickListener(this);
        send.setOnClickListener(this);
        biaoqing.setOnClickListener(this);



        ed_pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyApplication.isLogin()) {
                    startActivity(new Intent(TouDetailActivity.this, LoginActivity.class));
                    ed_pinglun.clearFocus();
                }
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_beak:
                Intent intent=getIntent();
                setResult(1,intent);
                finish();
                break;
            case R.id.send:
                if (MyApplication.isLogin()) {
                    //获取输入框信息
                    pinglun = ed_pinglun.getText().toString();
                    if (!TextUtils.isEmpty(pinglun)) {
                        initNet();
                    } else {
                        Toast.makeText(this,getResources().getString(R.string.qingshurupinlunneirong) , Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TouDetailActivity.this,getResources().getString(R.string.dengluhoukejingxingpinlun), Toast.LENGTH_SHORT).show();
                    MyUtils.StartLoginActivity(this);
                }

                break;
            case R.id.biaoqing:
                Toast.makeText(this, "表情", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent=getIntent();
        setResult(1,intent);
        finish();
    }

    //评论列表
    private void initplNet() {
        token = SharedPreferencesUtils.getInstace(this).getStringPreference("token", "");
        HttpParams params = new HttpParams();
        params.put("token", token);
        params.put("tId", tid + "");
        params.put("pageNo", "1");
        OkGo.<TouListBean>post(MyUrls.BASEURL + "/app/topline/commentslist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<TouListBean>(this, TouListBean.class) {
                    @Override
                    public void onSuccess(Response<TouListBean> response) {
                        int code = response.code();
                        TouListBean TouListBean = response.body();
                        if (code == 200 && TouListBean.getCode().equals("200")) {
                            List<com.haiwai.administrator.japanhouse.bean.TouListBean.DatasBean> datas = TouListBean.getDatas();
                            //加载适配器
                            detail_recy.setLayoutManager(new LinearLayoutManager(TouDetailActivity.this));
                            detail_recy.addItemDecoration(new DividerItemDecoration(TouDetailActivity.this, DividerItemDecoration.VERTICAL));
                            detail_recy.setNestedScrollingEnabled(false);
                            ToutiaoAdapter touAdapter = new ToutiaoAdapter(TouDetailActivity.this, datas);
                            touAdapter.setOnItemPraiseListener(TouDetailActivity.this);
                            detail_recy.setAdapter(touAdapter);
                        }
                    }
                });
    }

    //发表评论
    private void initNet() {
        HttpParams params = new HttpParams();
        params.put("token", token);
        params.put("tId", tid + "");
        params.put("tContent", pinglun);
        OkGo.<SuccessBean>post(MyUrls.BASEURL + "/app/topline/insertcomments")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SuccessBean>(this, SuccessBean.class) {
                    @Override
                    public void onSuccess(Response<SuccessBean> response) {
                        int code = response.code();
                        SuccessBean successBean = response.body();
                        if (code == 200 && successBean.getCode().equals("200")) {
                            ed_pinglun.setText("");
                            Toast.makeText(TouDetailActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                            initplNet();
                        }
                    }
                });
    }

    @Override
    public void onItemUpListener(int commentId) {
        presenter.thumbUpComment(commentId);
    }

    @Override
    public void onItemDwonListener(int commentId) {
        presenter.thumbDownComment(commentId);
    }
}
