package com.example.administrator.japanhouse.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindPhoneActivity extends BaseActivity {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.edt_yanzheng_code)
    EditText edtYanzhengCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.edt_pass_sure)
    EditText edtPassSure;
    @BindView(R.id.btn_find_pass)
    Button btnFindPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_img, R.id.tv_get_code, R.id.btn_find_pass})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            case R.id.tv_get_code:
                break;
            case R.id.btn_find_pass:

                break;
        }
    }
}
