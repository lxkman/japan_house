package com.haiwai.administrator.japanhouse.fragment.home.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.bean.EventBean;
import com.haiwai.administrator.japanhouse.bean.HuiDaBean;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.utils.Constants;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.haiwai.administrator.japanhouse.utils.TUtils;
import com.haiwai.administrator.japanhouse.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

public class AnswerActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_beak;
    private RelativeLayout liner;
    private TextView ed_wen_title;
    private EditText ed_wen_content;
    private Button tijiao;
    private String title;
    private int askid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        initView();

    }

    private void initView() {
        askid = getIntent().getIntExtra("askid", 0);
        title = getIntent().getStringExtra("title");
        img_beak = (ImageView) findViewById(R.id.img_beak);
        liner = (RelativeLayout) findViewById(R.id.liner);
        ed_wen_title = (TextView) findViewById(R.id.ed_wen_title);
        ed_wen_content = (EditText) findViewById(R.id.ed_wen_content);
        tijiao = (Button) findViewById(R.id.tijiao);
        tijiao.setOnClickListener(this);
        img_beak.setOnClickListener(this);
        ed_wen_title.setText(title);
    }
    private void intdataview() {
        String content = ed_wen_content.getText().toString().trim();
        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        params.put("content",content);
        params.put("askId",askid);
        OkGo.<HuiDaBean>post(MyUrls.BASEURL + "/app/askinfo/insertask")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<HuiDaBean>(AnswerActivity.this,HuiDaBean.class){
                    @Override
                    public void onSuccess(Response<HuiDaBean> response) {
                        HuiDaBean body = response.body();
                        String code = body.getCode();
                        if(code.equals("200")){
                            EventBus.getDefault().post(new EventBean(Constants.EVENT_D));
                            finish();
                            ToastUtils.getToast(AnswerActivity.this,body.getMsg());
                        }else if(code.equals("201")){
                            ToastUtils.getToast(AnswerActivity.this,body.getMsg());
                        }else if(code.equals("500")){
                            ToastUtils.getToast(AnswerActivity.this,body.getMsg());
                        }else if(code.equals("404")){
                            ToastUtils.getToast(AnswerActivity.this,body.getMsg());
                        }else if(code.equals("203")){
                            ToastUtils.getToast(AnswerActivity.this,body.getMsg());
                        }else if(code.equals("204")){
                            ToastUtils.getToast(AnswerActivity.this,body.getMsg());
                        }

                    }

                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_beak:
                finish();
                break;
            case R.id.tijiao:
                if (!TextUtils.isEmpty(ed_wen_title.getText().toString()) && !TextUtils.isEmpty(ed_wen_content.getText().toString())) {
                    intdataview();
                } else if (TextUtils.isEmpty(ed_wen_title.getText().toString())) {
                    TUtils.showFail(this, getString(R.string.input_title));
                } else if (TextUtils.isEmpty(ed_wen_content.getText().toString())) {
                    TUtils.showFail(this, getString(R.string.input_content));
                }
                break;
        }
    }


}
