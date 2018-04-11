package com.example.administrator.japanhouse.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.login.UserAgreementActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.ll_gomarket)
    LinearLayout llGomarket;
    @BindView(R.id.ll_des)
    LinearLayout llDes;
    @BindView(R.id.activity_zui_jin)
    LinearLayout activityZuiJin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        backImg.setOnClickListener(this);
        llGomarket.setOnClickListener(this);
        llDes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img:
                finish();
                break;
            case R.id.ll_gomarket:
                break;
            case R.id.ll_des:
                startActivity(new Intent(this, UserAgreementActivity.class));
                break;
        }
    }
}
