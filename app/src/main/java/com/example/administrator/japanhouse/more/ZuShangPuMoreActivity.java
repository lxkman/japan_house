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

public class ZuShangPuMoreActivity extends BaseActivity {

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
    @BindView(R.id.tv_house_gouzao)
    TextView tvHouseGouzao;
    @BindView(R.id.tv_house_chaoxiang)
    TextView tvHouseChaoxiang;
    @BindView(R.id.tv_chuqi_feiyong)
    TextView tvChuqiFeiyong;
    @BindView(R.id.tv_ruzhu_riqi)
    TextView tvRuzhuRiqi;
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
    @BindView(R.id.tv_shangpu_zhaopai)
    TextView tvShangpuZhaopai;
    @BindView(R.id.tv_house_jujiagaodu)
    TextView tvHouseJujiagaodu;
    @BindView(R.id.tv_house_shiwaishezhi)
    TextView tvHouseShiwaishezhi;
    @BindView(R.id.tv_house_shineishebei)
    TextView tvHouseShineishebei;
    @BindView(R.id.tv_house_tezheng)
    TextView tvHouseTezheng;
    @BindView(R.id.tv_house_tiaojian)
    TextView tvHouseTiaojian;
    @BindView(R.id.tv_house_beizhu)
    TextView tvHouseBeizhu;
    @BindView(R.id.activity_zui_jin)
    LinearLayout activityZuiJin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zu_shang_pu_more);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back_img)
    public void onViewClicked() {
    }
}
