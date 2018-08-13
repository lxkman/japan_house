package com.haiwai.administrator.japanhouse.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.bean.SuccessBean;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.haiwai.administrator.japanhouse.utils.MyUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MsgDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.tv_msg_details)
    TextView tvMsgDetails;
    @BindView(R.id.tv_msg_time)
    TextView tvMsgTime;
    private String msgid, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_detail);
        ButterKnife.bind(this);
        backImg.setOnClickListener(this);
        String content = getIntent().getStringExtra("content");
        msgid = getIntent().getStringExtra("msgid");
        time = getIntent().getStringExtra("time");
        tvMsgDetails.setText(content);
        tvMsgTime.setText(MyUtils.getDateToStringY(time));
        ReadMsg();

    }

    private void ReadMsg() {
        HttpParams params = new HttpParams();
        params.put("id", msgid);
        OkGo.<SuccessBean>post(MyUrls.BASEURL + "/app/noticeinfo/upreadstatus")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SuccessBean>(MsgDetailActivity.this, SuccessBean.class) {
                    @Override
                    public void onSuccess(Response<SuccessBean> response) {
                        int code = response.code();
                        final SuccessBean SuccessBean = response.body();
                        String code1 = SuccessBean.getCode();
                        if (code1.equals("200")) {
                            Log.d("MsgDetailActivity", "已读" + "---------");
                        } else {
                            Toast.makeText(MsgDetailActivity.this, code1, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img:
                Intent intent = getIntent();
                setResult(1, intent);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = getIntent();
        setResult(1, intent);
        finish();
    }
}
