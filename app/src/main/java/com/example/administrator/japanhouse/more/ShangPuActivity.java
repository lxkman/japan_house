package com.example.administrator.japanhouse.more;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
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

public class ShangPuActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.act_shangpu_wujianmingcheng)
    TextView actShangpuWujianmingcheng;
    @BindView(R.id.act_shangpu_shoujia)
    TextView actShangpuShoujia;
    @BindView(R.id.act_shangpu_mianji)
    TextView actShangpuMianji;
    @BindView(R.id.act_shangpu_suoyouquan)
    TextView actShangpuSuoyouquan;
    @BindView(R.id.act_shangpu_diyuyongtu)
    TextView actShangpuDiyuyongtu;
    @BindView(R.id.act_shangpu_shoumaileixing)
    TextView actShangpuShoumaileixing;
    @BindView(R.id.act_shangpu_jianzhunianfen)
    TextView actShangpuJianzhunianfen;
    @BindView(R.id.act_shangpu_jianzhugouzao)
    TextView actShangpuJianzhugouzao;
    @BindView(R.id.act_shangpu_jianzhugongsi)
    TextView actShangpuJianzhugongsi;
    @BindView(R.id.act_shangpu_chaoxiang)
    TextView actShangpuChaoxiang;
    @BindView(R.id.act_shangpu_yingyeleixing)
    TextView actShangpuYingyeleixing;
    @BindView(R.id.act_shangpu_xianzhuang)
    TextView actShangpuXianzhuang;
    @BindView(R.id.act_shangpu_huibaolv)
    TextView actShangpuHuibaolv;
    @BindView(R.id.act_shangpu_rujuriqi)
    TextView actShangpuRujuriqi;
    @BindView(R.id.act_shangpu_diduan)
    TextView actShangpuDiduan;
    @BindView(R.id.act_shangpu_jutiweizhi)
    TextView actShangpuJutiweizhi;
    @BindView(R.id.act_shangpu_zhuyaochezhan)
    TextView actShangpuZhuyaochezhan;
    @BindView(R.id.act_shangpu_chezhanjuli)
    TextView actShangpuChezhanjuli;
    @BindView(R.id.act_shangpu_tingchewei)
    TextView actShangpuTingchewei;
    @BindView(R.id.act_shangpu_shangpuzhaopai)
    TextView actShangpuShangpuzhaopai;
    @BindView(R.id.act_shangpu_jujiagaodu)
    TextView actShangpuJujiagaodu;
    @BindView(R.id.act_shangpu_shiwaishezhi)
    TextView actShangpuShiwaishezhi;
    @BindView(R.id.act_shangpu_shineishebei)
    TextView actShangpuShineishebei;
    @BindView(R.id.act_shangpu_tezheng)
    TextView actShangpuTezheng;
    @BindView(R.id.act_shangpu_tiaojian)
    TextView actShangpuTiaojian;
    @BindView(R.id.act_gaoerfu_beizhu)
    TextView actGaoerfuBeizhu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_shangpu_more);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
