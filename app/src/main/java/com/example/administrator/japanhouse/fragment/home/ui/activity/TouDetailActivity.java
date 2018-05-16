package com.example.administrator.japanhouse.fragment.home.ui.activity;

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

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.bean.SuccessBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.ToutiaoAdapter;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class TouDetailActivity extends AppCompatActivity implements View.OnClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tou_detail);
        initView();
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

       //加载适配器
        detail_recy.setLayoutManager(new LinearLayoutManager(this));
        detail_recy.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        detail_recy.setNestedScrollingEnabled(false);
        //detail_recy.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        ToutiaoAdapter touAdapter = new ToutiaoAdapter(this);
        detail_recy.setAdapter(touAdapter);

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.img_beak:
                finish();
                break;
            case R.id.send:
                //获取输入框信息
                pinglun = ed_pinglun.getText().toString();
                if(!TextUtils.isEmpty(pinglun)){
                    initNet();
                }else{
                    Toast.makeText(this,"请输入评论内容",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.biaoqing:
                Toast.makeText(this,"表情",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void initNet() {
        HttpParams params = new HttpParams();
        params.put("token", "111");
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
                        }
                    }
                });
    }
}
