package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.bean.HuiDaBean;
import com.example.administrator.japanhouse.bean.QueandansBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_beak;
    private RelativeLayout liner;
    private EditText ed_wen_title;
    private EditText ed_wen_content;
    private Button tijiao;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initView();
    }



    private void initView() {
        type = getIntent().getIntExtra("type", 0);
        img_beak = (ImageView) findViewById(R.id.img_beak);
        ed_wen_title = (EditText) findViewById(R.id.ed_wen_title);
        ed_wen_content = (EditText) findViewById(R.id.ed_wen_content);
        tijiao = (Button) findViewById(R.id.tijiao);
        tijiao.setOnClickListener(this);
        img_beak.setOnClickListener(this);
    }

    private void intdaview() {
        String title = ed_wen_title.getText().toString().trim();
        String content = ed_wen_content.getText().toString().trim();

        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        params.put("qTitle",title);
        params.put("qContext",content);
        params.put("qType",type);
        OkGo.<HuiDaBean>post(MyUrls.BASEURL + "/app/askinfo/askquestions")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<HuiDaBean>(QuizActivity.this,HuiDaBean.class){
                    @Override
                    public void onSuccess(Response<HuiDaBean> response) {
                        HuiDaBean body = response.body();
                        String code = body.getCode();
                        if(code.equals("200")){
                            EventBus.getDefault().post(new EventBean(Constants.EVENT_W));
                            finish();
                            ToastUtils.getToast(QuizActivity.this,body.getMsg());
                        }else if(code.equals("201")){
                            ToastUtils.getToast(QuizActivity.this,body.getMsg());
                        }else if(code.equals("500")){
                            ToastUtils.getToast(QuizActivity.this,body.getMsg());
                        }else if(code.equals("404")){
                            ToastUtils.getToast(QuizActivity.this,body.getMsg());
                        }else if(code.equals("203")){
                            ToastUtils.getToast(QuizActivity.this,body.getMsg());
                        }else if(code.equals("204")){
                            ToastUtils.getToast(QuizActivity.this,body.getMsg());
                        }

                    }

                });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_beak:
                finish();
                break;
            case R.id.tijiao:
                intdaview();
                break;
        }
    }


}
