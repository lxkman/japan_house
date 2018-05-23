package com.example.administrator.japanhouse.more;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.model.VillaDetailsBean;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;

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
    TextView tvWujianmingcheng;
    @BindView(R.id.act_biesu_shoujia)
    TextView tvShoujia;
    @BindView(R.id.act_biesu_huxing)
    TextView tvHuxing;
    @BindView(R.id.act_biesu_dongcengshu)
    TextView tvDongcengshu;
    @BindView(R.id.act_biesu_tudimianji)
    TextView tvTudimianji;
    @BindView(R.id.act_biesu_jianzumianji)
    TextView tvJianzumianji;
    @BindView(R.id.act_biesu_suoyouquan)
    TextView tvSuoyouquan;
    @BindView(R.id.act_biesu_jianzhunianfen)
    TextView tvJianzhunianfen;
    @BindView(R.id.act_biesu_jianzhushejigongsi)
    TextView tvJianzhushejigongsi;
    @BindView(R.id.act_biesu_jianzhugouzao)
    TextView tvJianzhugouzao;
    @BindView(R.id.act_biesu_chaoxiang)
    TextView tvChaoxiang;
    @BindView(R.id.act_biesu_jianzhufugai)
    TextView tvJianzhufugai;
    @BindView(R.id.act_biesu_rongjilv)
    TextView tvRongjilv;
    @BindView(R.id.act_biesu_rujuriqi)
    TextView tvRujuriqi;
    @BindView(R.id.act_biesu_xianzhuang)
    TextView tvXianzhuang;
    @BindView(R.id.act_biesu_tingchechang)
    TextView tvTingchechang;
    @BindView(R.id.act_biesu_gongkaiqingbaori)
    TextView tvGongkaiqingbaori;
    @BindView(R.id.act_biesu_diduan)
    TextView tvDiduan;
    @BindView(R.id.act_biesu_jutiweizhi)
    TextView tvJutiweizhi;
    @BindView(R.id.act_biesu_zhuyaochezhan)
    TextView tvZhuyaochezhan;
    @BindView(R.id.act_biesu_chezhanjuli)
    TextView tvChezhanjuli;
    @BindView(R.id.act_biesu_daolulinjiejuli)
    TextView tvDaolulinjiejuli;
    @BindView(R.id.act_biesu_tudi)
    TextView tvTudi;
    @BindView(R.id.act_biesu_shineishebei)
    TextView tvShineishebei;
    @BindView(R.id.act_biesu_chufang)
    TextView tvChufang;
    @BindView(R.id.act_biesu_weishengjian)
    TextView tvWeishengjian;
    @BindView(R.id.act_biesu_yushi)
    TextView tvYushi;
    @BindView(R.id.act_biesu_xingneng)
    TextView tvXingneng;
    @BindView(R.id.act_biesu_zhengmingshu)
    TextView tvZhengmingshu;
    @BindView(R.id.act_biesu_xiacibaozhang)
    TextView tvXiacibaozhang;
    @BindView(R.id.act_biesu_beizhu)
    TextView tvBeizhu;

    private VillaDetailsBean.DatasBean datasBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biesu_more);
        ButterKnife.bind(this);

        datasBean = getIntent().getParcelableExtra("details");

        String country = CacheUtils.get(Constants.COUNTRY);

        if (!TextUtils.isEmpty(country) && TextUtils.equals(country, "ja")) {
            showJa();
        } else {
            showZh();
        }
    }

    private void showZh() {
        if (datasBean != null) {
            tvWujianmingcheng.setText(datasBean.getArticleNameCn());
            tvShoujia.setText(datasBean.getSellingPriceCn());
            tvHuxing.setText(datasBean.getVillaTypeCn());
            tvDongcengshu.setText(datasBean.getHomesLayerCn());
            tvTudimianji.setText(datasBean.getAreaCn());
            tvJianzumianji.setText(datasBean.getCoveredAreaCn());
            tvSuoyouquan.setText(datasBean.getOwnershipCn());
            tvJianzhunianfen.setText(datasBean.getYearBuiltCn());
            tvJianzhushejigongsi.setText(datasBean.getArchitecturalCn());
            tvJianzhugouzao.setText(datasBean.getBuildingConstructionCn());
            tvChaoxiang.setText(datasBean.getOrientationCn());
            tvJianzhufugai.setText(datasBean.getBuildingWrap());
            tvRongjilv.setText(datasBean.getPlotRatio());
            tvRujuriqi.setText(datasBean.getDoichoIrinoDateCn());
            tvXianzhuang.setText(datasBean.getCurrentSituationCn());
            tvTingchechang.setText(datasBean.getParkCn());
            tvGongkaiqingbaori.setText(datasBean.getPublicInformationDayCn());
            tvDiduan.setText(datasBean.getDistrictCn());
            tvJutiweizhi.setText(datasBean.getSpecificLocationCn());
            tvZhuyaochezhan.setText(datasBean.getTrainStationCn());
            tvChezhanjuli.setText(datasBean.getStationDistanceCn());
            tvDaolulinjiejuli.setText(datasBean.getStreetDistanceCn());
            tvTudi.setText(datasBean.getLandCn());
            tvShineishebei.setText(datasBean.getIndoorFacilityCn());
            tvChufang.setText(datasBean.getKitchenCn());
            tvWeishengjian.setText(datasBean.getToiletCn());
            tvYushi.setText(datasBean.getShowerRoomCn());
            tvXingneng.setText(datasBean.getPerformanceCn());
            tvZhengmingshu.setText(datasBean.getTestimonialCn());
            tvXiacibaozhang.setText(datasBean.getDefectsEnsureCn());
            tvBeizhu.setText(datasBean.getRemarkCn());
        }
    }

    private void showJa() {
        if (datasBean != null) {
            tvWujianmingcheng.setText(datasBean.getArticleNameJpn());
            tvShoujia.setText(datasBean.getSellingPriceJpn());
            tvHuxing.setText(datasBean.getVillaTypeJpn());
            tvDongcengshu.setText(datasBean.getHomesLayerJpn());
            tvTudimianji.setText(datasBean.getAreaJpn());
            tvJianzumianji.setText(datasBean.getCoveredAreaJpn());
            tvSuoyouquan.setText(datasBean.getOwnershipJpn());
            tvJianzhunianfen.setText(datasBean.getYearBuiltJpn());
            tvJianzhushejigongsi.setText(datasBean.getArchitecturalJpn());
            tvJianzhugouzao.setText(datasBean.getBuildingConstructionJpn());
            tvChaoxiang.setText(datasBean.getOrientationJpn());
            tvJianzhufugai.setText(datasBean.getBuildingWrap());
            tvRongjilv.setText(datasBean.getPlotRatio());
            tvRujuriqi.setText(datasBean.getDoichoIrinoDateJpn());
            tvXianzhuang.setText(datasBean.getCurrentSituationJpn());
            tvTingchechang.setText(datasBean.getParkJpn());
            tvGongkaiqingbaori.setText(datasBean.getPublicInformationDayJpn());
            tvDiduan.setText(datasBean.getDistrictJpn());
            tvJutiweizhi.setText(datasBean.getSpecificLocationJpn());
            tvZhuyaochezhan.setText(datasBean.getTrainStationJpn());
            tvChezhanjuli.setText(datasBean.getStationDistanceJpn());
            tvDaolulinjiejuli.setText(datasBean.getStreetDistanceJpn());
            tvTudi.setText(datasBean.getLandJpn());
            tvShineishebei.setText(datasBean.getIndoorFacilityJpn());
            tvChufang.setText(datasBean.getKitchenJpn());
            tvWeishengjian.setText(datasBean.getToiletJpn());
            tvYushi.setText(datasBean.getShowerRoomJpn());
            tvXingneng.setText(datasBean.getPerformanceJpn());
            tvZhengmingshu.setText(datasBean.getTestimonialJpn());
            tvXiacibaozhang.setText(datasBean.getDefectsEnsureJpn());
            tvBeizhu.setText(datasBean.getRemarkJpn());

        }
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
