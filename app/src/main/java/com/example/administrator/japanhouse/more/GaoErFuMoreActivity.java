package com.example.administrator.japanhouse.more;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by   admin on 2018/5/18.
 */

public class GaoErFuMoreActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.act_gaoerfu_shoujia)
    TextView actGaoerfuShoujia;
    @BindView(R.id.act_gaoerfu_mianji)
    TextView actGaoerfuMianji;
    @BindView(R.id.act_gaoerfu_suoyouquan)
    TextView actGaoerfuSuoyouquan;
    @BindView(R.id.act_gaoerfu_chezhanjuli)
    TextView actGaoerfuChezhanjuli;
    @BindView(R.id.act_gaoerfu_jutiweizhi)
    TextView actGaoerfuJutiweizhi;
    @BindView(R.id.act_gaoerfu_xianzhuang)
    TextView actGaoerfuXianzhuang;
    @BindView(R.id.act_gaoerfu_shuiyuan)
    TextView actGaoerfuShuiyuan;
    @BindView(R.id.act_gaoerfu_dixing)
    TextView actGaoerfuDixing;
    @BindView(R.id.act_gaoerfu_turangqingkuang)
    TextView actGaoerfuTurangqingkuang;
    @BindView(R.id.act_gaoerfu_huibaolv)
    TextView actGaoerfuHuibaolv;
    @BindView(R.id.act_gaoerfu_zhoubiansheshi)
    TextView actGaoerfuZhoubiansheshi;
    @BindView(R.id.act_gaoerfu_beizhu)
    TextView actGaoerfuBeizhu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaoerfu_more);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
