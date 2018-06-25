package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.bean.SuccessBean;
import com.example.administrator.japanhouse.bean.TouListBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.ToutiaoAdapter;
import com.example.administrator.japanhouse.login.LoginActivity;
import com.example.administrator.japanhouse.presenter.TopLinePresenter;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

public class TouDetailActivity extends AppCompatActivity implements View.OnClickListener,ToutiaoAdapter.OnItemPraiseListener {

    private ImageView img_beak;
    private TextView title;
    private TextView time;
    private TextView neirong;
    private RecyclerView detail_recy;
    private TextView send;
    private ImageView biaoqing;
    private EditText ed_pinglun;
    private String pinglun;
    private String tid;
    private String token;

    private TopLinePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tou_detail);

        presenter = new TopLinePresenter();

        initView();
        initplNet();
    }

    private void initView() {
        String titleValue = getIntent().getStringExtra("title");
        String timeValue = getIntent().getStringExtra("time");
        String contentValue = getIntent().getStringExtra("content");
        tid = getIntent().getStringExtra("Tid");

        img_beak = (ImageView) findViewById(R.id.img_beak);
        this.title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time);
        neirong = (TextView) findViewById(R.id.neirong);
        detail_recy = (RecyclerView) findViewById(R.id.detail_recy);
        send = (TextView) findViewById(R.id.send);
        biaoqing = (ImageView) findViewById(R.id.biaoqing);
        ed_pinglun = (EditText) findViewById(R.id.ed_pinglun);
        img_beak.setOnClickListener(this);
        send.setOnClickListener(this);
        biaoqing.setOnClickListener(this);

        title.setText(titleValue);
        time.setText(timeValue+"");
        neirong.setText(contentValue);

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
        switch(view.getId()){
            case R.id.img_beak:
                finish();
                break;
            case R.id.send:
                if (MyUtils.isLogin(this)) {
                    //获取输入框信息
                    pinglun = ed_pinglun.getText().toString();
                    if(!TextUtils.isEmpty(pinglun)){
                        initNet();
                    }else{
                        Toast.makeText(this,"请输入评论内容",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TouDetailActivity.this, "登录后可进行评论", Toast.LENGTH_SHORT).show();
                    MyUtils.StartLoginActivity(this);
                }

                break;
            case R.id.biaoqing:
                Toast.makeText(this,"表情",Toast.LENGTH_SHORT).show();
                break;

        }
    }
    //评论列表
    private void initplNet() {
        token = SharedPreferencesUtils.getInstace(this).getStringPreference("token", "");
        HttpParams params = new HttpParams();
        params.put("token", token);
        params.put("tId", tid+"");
        params.put("pageNo","1");
        OkGo.<TouListBean>post(MyUrls.BASEURL + "/app/topline/commentslist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<TouListBean>(this, TouListBean.class) {
                    @Override
                    public void onSuccess(Response<TouListBean> response) {
                        int code = response.code();
                        TouListBean TouListBean = response.body();
                        if (code==200&&TouListBean.getCode().equals("200")){
                            List<com.example.administrator.japanhouse.bean.TouListBean.DatasBean> datas = TouListBean.getDatas();
                            //加载适配器
                            detail_recy.setLayoutManager(new LinearLayoutManager(TouDetailActivity.this));
                            detail_recy.addItemDecoration(new DividerItemDecoration(TouDetailActivity.this,DividerItemDecoration.VERTICAL));
                            detail_recy.setNestedScrollingEnabled(false);
                            ToutiaoAdapter touAdapter = new ToutiaoAdapter(TouDetailActivity.this,datas);
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
        params.put("tId", tid+"");
        params.put("tContent",pinglun);
        OkGo.<SuccessBean>post(MyUrls.BASEURL + "/app/topline/insertcomments")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SuccessBean>(this, SuccessBean.class) {
                    @Override
                    public void onSuccess(Response<SuccessBean> response) {
                        int code = response.code();
                        SuccessBean successBean = response.body();
                        if (code==200&&successBean.getCode().equals("200")){
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
