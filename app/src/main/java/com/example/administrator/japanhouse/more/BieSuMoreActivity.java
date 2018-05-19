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

public class BieSuMoreActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.act_biesu_wujianmingcheng)
    TextView actBiesuWujianmingcheng;
    @BindView(R.id.act_biesu_shoujia)
    TextView actBiesuShoujia;
    @BindView(R.id.act_biesu_huxing)
    TextView actBiesuHuxing;
    @BindView(R.id.act_biesu_dongcengshu)
    TextView actBiesuDongcengshu;
    @BindView(R.id.act_biesu_tudimianji)
    TextView actBiesuTudimianji;
    @BindView(R.id.act_biesu_jianzumianji)
    TextView actBiesuJianzumianji;
    @BindView(R.id.act_biesu_suoyouquan)
    TextView actBiesuSuoyouquan;
    @BindView(R.id.act_biesu_jianzhunianfen)
    TextView actBiesuJianzhunianfen;
    @BindView(R.id.act_biesu_jianzhushejigongsi)
    TextView actBiesuJianzhushejigongsi;
    @BindView(R.id.act_biesu_jianzhugouzao)
    TextView actBiesuJianzhugouzao;
    @BindView(R.id.act_biesu_chaoxiang)
    TextView actBiesuChaoxiang;
    @BindView(R.id.act_biesu_jianzhufugai)
    TextView actBiesuJianzhufugai;
    @BindView(R.id.act_biesu_rongjilv)
    TextView actBiesuRongjilv;
    @BindView(R.id.act_biesu_rujuriqi)
    TextView actBiesuRujuriqi;
    @BindView(R.id.act_biesu_xianzhuang)
    TextView actBiesuXianzhuang;
    @BindView(R.id.act_biesu_tingchechang)
    TextView actBiesuTingchechang;
    @BindView(R.id.act_biesu_gongkaiqingbaori)
    TextView actBiesuGongkaiqingbaori;
    @BindView(R.id.act_biesu_diduan)
    TextView actBiesuDiduan;
    @BindView(R.id.act_biesu_jutiweizhi)
    TextView actBiesuJutiweizhi;
    @BindView(R.id.act_biesu_zhuyaochezhan)
    TextView actBiesuZhuyaochezhan;
    @BindView(R.id.act_biesu_chezhanjuli)
    TextView actBiesuChezhanjuli;
    @BindView(R.id.act_biesu_daolulinjiejuli)
    TextView actBiesuDaolulinjiejuli;
    @BindView(R.id.act_biesu_tudi)
    TextView actBiesuTudi;
    @BindView(R.id.act_biesu_shineishebei)
    TextView actBiesuShineishebei;
    @BindView(R.id.act_biesu_chufang)
    TextView actBiesuChufang;
    @BindView(R.id.act_biesu_weishengjian)
    TextView actBiesuWeishengjian;
    @BindView(R.id.act_biesu_yushi)
    TextView actBiesuYushi;
    @BindView(R.id.act_biesu_xingneng)
    TextView actBiesuXingneng;
    @BindView(R.id.act_biesu_zhengmingshu)
    TextView actBiesuZhengmingshu;
    @BindView(R.id.act_biesu_xiacibaozhang)
    TextView actBiesuXiacibaozhang;
    @BindView(R.id.act_biesu_beizhu)
    TextView actBiesuBeizhu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_biesu_more);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
