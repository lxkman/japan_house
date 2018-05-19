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

public class TuanDiMoreActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.act_tuandi_zujin)
    TextView actTuandiZujin;
    @BindView(R.id.act_tuandi_huxing)
    TextView actTuandiHuxing;
    @BindView(R.id.act_tuandi_chuqifeiyong)
    TextView actTuandiChuqifeiyong;
    @BindView(R.id.act_tuandi_shuidiandengfeiyong)
    TextView actTuandiShuidiandengfeiyong;
    @BindView(R.id.act_tuandi_mianji)
    TextView actTuandiMianji;
    @BindView(R.id.act_tuandi_louceng)
    TextView actTuandiLouceng;
    @BindView(R.id.act_tuandi_chaoxiang)
    TextView actTuandiChaoxiang;
    @BindView(R.id.act_tuandi_fangjianzhuangkuang)
    TextView actTuandiFangjianzhuangkuang;
    @BindView(R.id.act_tuandi_qianyuenianxian)
    TextView actTuandiQianyuenianxian;
    @BindView(R.id.act_tuandi_guanlihuishe)
    TextView actTuandiGuanlihuishe;
    @BindView(R.id.act_tuandi_guanlifei)
    TextView actTuandiGuanlifei;
    @BindView(R.id.act_tuandi_xiushanfei)
    TextView actTuandiXiushanfei;
    @BindView(R.id.act_tuandi_guanli)
    TextView actTuandiGuanli;
    @BindView(R.id.act_tuandi_diduan)
    TextView actTuandiDiduan;
    @BindView(R.id.act_tuandi_jutiweizhi)
    TextView actTuandiJutiweizhi;
    @BindView(R.id.act_tuandi_zhuyaochezhan)
    TextView actTuandiZhuyaochezhan;
    @BindView(R.id.act_tuandi_chezhanjuli)
    TextView actTuandiChezhanjuli;
    @BindView(R.id.act_tuandi_chuangwaifengjing)
    TextView actTuandiChuangwaifengjing;
    @BindView(R.id.act_tuandi_gongyongbufen)
    TextView actTuandiGongyongbufen;
    @BindView(R.id.act_tuandi_zhoubianhuanjing)
    TextView actTuandiZhoubianhuanjing;
    @BindView(R.id.act_tuandi_tingchewei)
    TextView actTuandiTingchewei;
    @BindView(R.id.act_tuandi_shineishebei)
    TextView actTuandiShineishebei;
    @BindView(R.id.act_tuandi_chufang)
    TextView actTuandiChufang;
    @BindView(R.id.act_tuandi_weishengjian)
    TextView actTuandiWeishengjian;
    @BindView(R.id.act_tuandi_yushi)
    TextView actTuandiYushi;
    @BindView(R.id.act_tuandi_xingneng)
    TextView actTuandiXingneng;
    @BindView(R.id.act_tuandi_zhengmingshu)
    TextView actTuandiZhengmingshu;
    @BindView(R.id.act_tuandi_xiacibaozhang)
    TextView actTuandiXiacibaozhang;
    @BindView(R.id.act_tuandi_beizhu)
    TextView actTuandiBeizhu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuandi_more);
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
