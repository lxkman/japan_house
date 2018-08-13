package com.haiwai.administrator.japanhouse.fragment.comment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.utils.CacheUtils;
import com.haiwai.administrator.japanhouse.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SeeMoreActivity extends BaseActivity {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.tv_more_name)
    TextView tvMoreName;
    @BindView(R.id.tv_more_chaoxiang)
    TextView tvMoreChaoxiang;
    @BindView(R.id.tv_more_nianfen)
    TextView tvMoreNianfen;
    @BindView(R.id.tv_more_guanlifei)
    TextView tvMoreGuanlifei;
    @BindView(R.id.tv_more_gouzao)
    TextView tvMoreGouzao;
    @BindView(R.id.tv_more_dianfei)
    TextView tvMoreDianfei;
    @BindView(R.id.tv_more_shuifei)
    TextView tvMoreShuifei;
    @BindView(R.id.tv_more_louceng)
    TextView tvMoreLouceng;
    @BindView(R.id.tv_more_yangshi)
    TextView tvMoreYangshi;
    @BindView(R.id.tv_more_heshi)
    TextView tvMoreHeshi;
    @BindView(R.id.tv_more_muyu)
    TextView tvMoreMuyu;
    @BindView(R.id.tv_more_shehui)
    TextView tvMoreShehui;
    @BindView(R.id.tv_more_weizhi)
    TextView tvMoreWeizhi;
    @BindView(R.id.tv_more_shebei)
    TextView tvMoreShebei;
    @BindView(R.id.tv_more_wasi)
    TextView tvMoreWasi;
    @BindView(R.id.tv_more_caizhi)
    TextView tvMoreCaizhi;
    @BindView(R.id.tv_more_juli)
    TextView tvMoreJuli;
    @BindView(R.id.tv_more_fengjing)
    TextView tvMoreFengjing;
    @BindView(R.id.tv_more_waiguan)
    TextView tvMoreWaiguan;
    @BindView(R.id.tv_more_neiguan)
    TextView tvMoreNeiguan;
    private boolean isJa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_more);
        ButterKnife.bind(this);
        initDetailsNet();
    }

    private void initDetailsNet() {
        String city = CacheUtils.get(Constants.COUNTRY);
        if (city != null && city.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
//        HttpParams params = new HttpParams();
//        params.put("hId", 18);
//        OkGo.<HouseDetailsBean>post(MyUrls.BASEURL + "/app/houseresourse/houseinfo")
//                .tag(this)
//                .params(params)
//                .execute(new DialogCallback<HouseDetailsBean>(this, HouseDetailsBean.class) {
//                    @Override
//                    public void onSuccess(Response<HouseDetailsBean> response) {
//                        int code = response.code();
//                        HouseDetailsBean oldHouseListBean = response.body();
//                        HouseDetailsBean.DatasBean datas = oldHouseListBean.getDatas();
//                        HouseDetailsBean.DatasBean.HwdcBrokerBean hwdcBroker = datas.getHwdcBroker();
//                        tvMoreName.setText(isJa ? datas.getPlotNameJpn() : datas.getPlotNameCn());
//                        tvMoreChaoxiang.setText(isJa ? datas.getOrientationJpn() : datas.getOrientationCn());
//                        tvMoreNianfen.setText(isJa ? datas.getYearBuiltJpn() : datas.getYearBuiltCn());
//                        tvMoreGuanlifei.setText(isJa ? datas.getRepairChargeJpn()+"" : datas.getRepairChargeCn()+"");
//                        tvMoreGouzao.setText(isJa ? datas.getArchitecturalConstructionJpn() : datas.getArchitecturalConstructionCn());
//                        tvMoreDianfei.setText(isJa ? datas.getElectricityJpn() : datas.getElectricityCn());
//                        tvMoreShuifei.setText(isJa ? datas.getWaterJpn() : datas.getWaterCn());
//                        tvMoreLouceng.setText(isJa ? datas.getFloorJpn() : datas.getFloorCn());
//                        tvMoreYangshi.setText(isJa ? datas.getWesternRoomJpn() : datas.getWesternRoomCn());
//                        tvMoreHeshi.setText(isJa ? datas.getTatamiRoomJpn() : datas.getTatamiRoomCn());
////                        tvMoreMuyu.setText(isJa ? datas.() : datas.getTatamiRoomCn());
//                        tvMoreShehui.setText(isJa ? datas.getManageSocietyJpn() : datas.getManageSocietyCn());
//                        tvMoreWeizhi.setText(isJa ? datas.getLocationLevel1Jpn() : datas.getLocationLevel1Cn());
//                        tvMoreShebei.setText(isJa ? datas.getEquipmentJpn() : datas.getEquipmentCn());
//                        tvMoreWasi.setText(isJa ? datas.getGasJpn() : datas.getGasCn());
//                        tvMoreCaizhi.setText(isJa ? datas.getDecorateMaterialJpn() : datas.getDecorateMaterialCn());
//                        tvMoreJuli.setText(isJa ? datas.getStationDistanceJpn() : datas.getStationDistanceCn());
//                        tvMoreFengjing.setText(isJa ? datas.getSceneryJpn() : datas.getSceneryCn());
//                        tvMoreWaiguan.setText(isJa ? datas.getAppearanceJpn() : datas.getAppearanceCn());
//                        tvMoreNeiguan.setText(isJa ? datas.getIntrospectionJpn() : datas.getIntrospectionCn());
//                    }
//                });
    }

    @OnClick(R.id.back_img)
    public void onClick() {
        finish();
    }
}
