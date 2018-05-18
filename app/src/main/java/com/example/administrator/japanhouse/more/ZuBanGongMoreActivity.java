package com.example.administrator.japanhouse.more;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZuBanGongMoreActivity extends BaseActivity {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.tv_wujian_name)
    TextView tvWujianName;
    @BindView(R.id.tv_house_price)
    TextView tvHousePrice;
    @BindView(R.id.tv_house_area)
    TextView tvHouseArea;
    @BindView(R.id.tv_more_louceng)
    TextView tvMoreLouceng;
    @BindView(R.id.tv_house_years)
    TextView tvHouseYears;
    @BindView(R.id.tv_chuqi_feiyong)
    TextView tvChuqiFeiyong;
    @BindView(R.id.tv_ruzhu_riqi)
    TextView tvRuzhuRiqi;
    @BindView(R.id.tv_house_guanlihuishe)
    TextView tvHouseGuanlihuishe;
    @BindView(R.id.tv_house_guanlifei)
    TextView tvHouseGuanlifei;
    @BindView(R.id.tv_house_guanli)
    TextView tvHouseGuanli;
    @BindView(R.id.tv_house_diduan)
    TextView tvHouseDiduan;
    @BindView(R.id.tv_house_jutiweizhi)
    TextView tvHouseJutiweizhi;
    @BindView(R.id.tv_house_zhuyaochezhan)
    TextView tvHouseZhuyaochezhan;
    @BindView(R.id.tv_house_chezhanjuli)
    TextView tvHouseChezhanjuli;
    @BindView(R.id.tv_stop_car)
    TextView tvStopCar;
    @BindView(R.id.tv_house_shineishebei)
    TextView tvHouseShineishebei;
    @BindView(R.id.tv_house_beizhu)
    TextView tvHouseBeizhu;
    @BindView(R.id.activity_zui_jin)
    LinearLayout activityZuiJin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zu_ban_gong_more);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back_img)
    public void onViewClicked() {
        finish();
    }
}
