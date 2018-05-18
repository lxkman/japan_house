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

public class ZuXueShengMoreActivity extends BaseActivity {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.tv_house_huxing)
    TextView tvHouseHuxing;
    @BindView(R.id.tv_house_price)
    TextView tvHousePrice;
    @BindView(R.id.tv_more_louceng)
    TextView tvMoreLouceng;
    @BindView(R.id.tv_house_chaoxiang)
    TextView tvHouseChaoxiang;
    @BindView(R.id.tv_house_area)
    TextView tvHouseArea;
    @BindView(R.id.tv_house_zhuyaochezhan)
    TextView tvHouseZhuyaochezhan;
    @BindView(R.id.tv_house_chezhanjuli)
    TextView tvHouseChezhanjuli;
    @BindView(R.id.tv_house_fangjianzhuangkuang)
    TextView tvHouseFangjianzhuangkuang;
    @BindView(R.id.tv_house_years)
    TextView tvHouseYears;
    @BindView(R.id.tv_house_feiyong)
    TextView tvHouseFeiyong;
    @BindView(R.id.tv_house_shineishebei)
    TextView tvHouseShineishebei;
    @BindView(R.id.tv_house_beizhu)
    TextView tvHouseBeizhu;
    @BindView(R.id.activity_zui_jin)
    LinearLayout activityZuiJin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xue_sheng_more);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back_img)
    public void onViewClicked() {
        finish();
    }
}
