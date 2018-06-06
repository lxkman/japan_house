package com.example.administrator.japanhouse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * admin  2018/6/6
 */
public class FreeApartmentDetailsActivity extends BaseActivity {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.act_freeApart_icon)
    ImageView icon;
    @BindView(R.id.act_freeApart_title_ja)
    TextView titleJa;
    @BindView(R.id.act_freeApart_time_ja)
    TextView timeJa;
    @BindView(R.id.act_freeApart_num_ja)
    TextView numJa;
    @BindView(R.id.act_freeApart_apply_ja)
    Button applyJa;
    @BindView(R.id.act_freeApart_layout_ja)
    LinearLayout layoutJa;
    @BindView(R.id.act_freeApart_title_zh)
    TextView titleZh;
    @BindView(R.id.act_freeApart_time_zh)
    TextView timeZh;
    @BindView(R.id.act_freeApart_num_zh)
    TextView numZh;
    @BindView(R.id.act_freeApart_apply_zh)
    Button applyZh;
    @BindView(R.id.act_freeApart_layout_zh)
    LinearLayout layoutZh;
    @BindView(R.id.act_freeApart_details)
    TextView details;
    @BindView(R.id.act_freeApart_range)
    TextView range;
    @BindView(R.id.act_freeApart_phone)
    TextView phone;
    @BindView(R.id.act_freeApart_houseBs)
    TextView houseBs;
    @BindView(R.id.act_freeApart_date)
    TextView date;
    @BindView(R.id.act_freeApart_address)
    TextView address;
    @BindView(R.id.act_freeApart_managerIcon)
    ImageView managerIcon;
    @BindView(R.id.act_freeApart_managerName)
    TextView managerName;
    @BindView(R.id.act_freeApart_wChat)
    TextView wChat;
    @BindView(R.id.act_freeApart_managerPhone)
    TextView managerPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeapart_details);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        if (MyApplication.isJapanese()) {
            layoutJa.setVisibility(View.VISIBLE);
            layoutZh.setVisibility(View.GONE);
        } else {
            layoutZh.setVisibility(View.VISIBLE);
            layoutJa.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.back_img, R.id.act_freeApart_wChat, R.id.act_freeApart_managerPhone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            case R.id.act_freeApart_wChat:
                break;
            case R.id.act_freeApart_managerPhone:
                break;
        }
    }
}
