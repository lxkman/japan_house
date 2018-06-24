package com.example.administrator.japanhouse.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.SuccessBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.login.BindPhoneActivity;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.example.administrator.japanhouse.utils.SendSmsTimerUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineBindPhoneActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.edt_phone)
    TextView edtPhone;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.activity_zui_jin)
    LinearLayout activityZuiJin;
    private String QuNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_bind_phone);
        ButterKnife.bind(this);
        backImg.setOnClickListener(this);
        tvGetcode.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        String phone = getIntent().getStringExtra("phone");
        edtPhone.setText(phone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.tv_getcode:
                if (TextUtils.isEmpty(edtPhone.getText().toString())) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!MyUtils.isMobileNO(edtPhone.getText().toString())) {
                    Toast.makeText(this, "手机号格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!TextUtils.isEmpty(edtPhone.getText().toString())&&MyUtils.isMobileNO(edtPhone.getText().toString())) {
                    String phone = edtPhone.getText().toString();
                    String substring = phone.substring(0, 3);
                    if (substring.equals("050") || substring.equals("060") || substring.equals("070") || substring.equals("080") || substring.equals("090")) {
                        QuNumber = "1";//国际
                    } else {
                        QuNumber = "2";//国内
                    }
                    getCode();//验证码接口
                }
                break;
            case R.id.btn_next:
                if (!TextUtils.isEmpty(edtCode.getText().toString())){
                    startActivity(new Intent(this,ChangePhoneActivity.class));
                    finish();
                }else {
                    Toast.makeText(mContext, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private void getCode() {
        HttpParams params = new HttpParams();
        String quhao;
        if (QuNumber.equals("1")){
            quhao="81";
        }else {
            quhao="86";
        }
        params.put("phone", "00"+quhao+edtPhone.getText().toString());
        params.put("sendType",QuNumber);
        OkGo.<SuccessBean>post(MyUrls.BASEURL + "/send/msg/sendmsg")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SuccessBean>(this, SuccessBean.class) {
                    @Override
                    public void onSuccess(Response<SuccessBean> response) {
                        int code = response.code();
                        SuccessBean successBean = response.body();
                        if (successBean.getCode().equals("200")){
                            SendSmsTimerUtils.sendSms(tvGetcode, R.color.shihuangse, R.color.shihuangse);
                            Toast.makeText(MineBindPhoneActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                        }else if (successBean.getCode().equals("-1")){
                            Toast.makeText(MineBindPhoneActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                        }else if (successBean.getCode().equals("500")){
                            Toast.makeText(MineBindPhoneActivity.this, "内部服务器错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
