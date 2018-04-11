package com.example.administrator.japanhouse.login;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PeopleXieyiActivity extends BaseActivity {

    @BindView(R.id.back_img)
    ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_xieyi);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back_img)
    public void onClick() {
        finish();
    }
}
