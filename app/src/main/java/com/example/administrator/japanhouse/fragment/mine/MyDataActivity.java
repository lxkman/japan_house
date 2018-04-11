package com.example.administrator.japanhouse.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDataActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.iv_select_photo)
    ImageView ivSelectPhoto;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.cb_man)
    CheckBox cbMan;
    @BindView(R.id.ll_man)
    LinearLayout llMan;
    @BindView(R.id.cb_woman)
    CheckBox cbWoman;
    @BindView(R.id.ll_woman)
    LinearLayout llWoman;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.activity_my_data)
    LinearLayout activityMyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);
        ButterKnife.bind(this);
        llWoman.setOnClickListener(this);
        llMan.setOnClickListener(this);
        backImg.setOnClickListener(this);
        tvPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_woman:
                cbWoman.setChecked(true);
                cbMan.setChecked(false);
                break;
            case R.id.ll_man:
                cbMan.setChecked(true);
                cbWoman.setChecked(false);
                break;
            case R.id.back_img:
                finish();
                break;
            case R.id.tv_phone:
                startActivity(new Intent(this,BindPhoneActivity.class));
                break;
        }
    }
}
