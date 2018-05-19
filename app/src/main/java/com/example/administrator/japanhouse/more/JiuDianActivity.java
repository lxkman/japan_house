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

public class JiuDianActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.act_jiudian_shoujia)
    TextView actJiudianShoujia;
    @BindView(R.id.act_jiudian_jiudianloucengshu)
    TextView actJiudianJiudianloucengshu;
    @BindView(R.id.act_jiudian_suoyouquan)
    TextView actJiudianSuoyouquan;
    @BindView(R.id.act_jiudian_chezhanjuli)
    TextView actJiudianChezhanjuli;
    @BindView(R.id.act_jiudian_jianzhunianfen)
    TextView actJiudianJianzhunianfen;
    @BindView(R.id.act_jiudian_jianzhugouzao)
    TextView actJiudianJianzhugouzao;
    @BindView(R.id.act_jiudian_diyuyongtu)
    TextView actJiudianDiyuyongtu;
    @BindView(R.id.act_jiudian_xianzhuang)
    TextView actJiudianXianzhuang;
    @BindView(R.id.act_jiudian_huibaolv)
    TextView actJiudianHuibaolv;
    @BindView(R.id.act_gaoerfu_beizhu)
    TextView actGaoerfuBeizhu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_jiudian_more);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
