package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.example.administrator.japanhouse.bean.ShenDengRuleBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.view.FluidLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShendengSecondStepActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.want_fluidlayout)
    FluidLayout wantFluidlayout;
    @BindView(R.id.notwant_fluidlayout)
    FluidLayout notwantFluidlayout;
    @BindView(R.id.finish_tv)
    TextView finishTv;
    private boolean isJa;
    private HashMap<String, List<String>> hashMap_want = new HashMap<>();
    private HashMap<String, List<String>> hashMap_dontwant = new HashMap<>();
    private List<String> allCheckedTextList;
    private List<OneCheckBean> wantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shendeng_second_step);
        ButterKnife.bind(this);
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        allCheckedTextList = (List<String>) getIntent().getSerializableExtra("checkedcontent");
        initView();
    }

    private void initView() {
        wantFluidlayout.removeAllViews();
        OkGo.<ShenDengRuleBean>post(MyUrls.BASEURL + "/app/twoscreening/sdzftj")
                .tag(this)
                .execute(new DialogCallback<ShenDengRuleBean>(ShendengSecondStepActivity.this, ShenDengRuleBean.class) {
                    @Override
                    public void onSuccess(Response<ShenDengRuleBean> response) {
                        int code = response.code();
                        ShenDengRuleBean shenDengRuleBean = response.body();
                        if (shenDengRuleBean == null) {
                            return;
                        }
                        ShenDengRuleBean.DatasEntity datas = shenDengRuleBean.getDatas();
                        ShenDengRuleBean.DatasEntity.XytjEntity xytj = datas.getXytj();
                        ShenDengRuleBean.DatasEntity.BxytjEntity bxytj = datas.getBxytj();
                        wantList = getxiangyao(xytj);
                        wantList.get(0).setChecked(true);
                        for (int i = 0; i < wantList.size(); i++) {
                            final TextView tv = (TextView) View.inflate(mContext, R.layout.item_shendeng_second, null);
                            tv.setText(wantList.get(i).getName());
                            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT
                            );
                            if (wantList.get(i).isChecked()) {
                                tv.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_sec_true));
                                tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                            }
                            params.setMargins(12, 12, 12, 12);
                            wantFluidlayout.addView(tv, params);
                            final int finalI = i;
                            tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (!wantList.get(finalI).isChecked()) {
                                        tv.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_sec_true));
                                        tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                                        wantList.get(finalI).setChecked(true);
                                    } else {
                                        tv.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_sec_false));
                                        tv.setTextColor(getResources().getColor(R.color.text_gray));
                                        wantList.get(finalI).setChecked(false);
                                    }
                                }
                            });
                        }
                        notwantFluidlayout.removeAllViews();
                        final List<OneCheckBean> nowantList = getbuxiangyao(bxytj);
                        nowantList.get(0).setChecked(true);
                        for (int i = 0; i < nowantList.size(); i++) {
                            final TextView tv = (TextView) View.inflate(mContext, R.layout.item_shendeng_second, null);
                            tv.setText(nowantList.get(i).getName());
                            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT
                            );
                            if (nowantList.get(i).isChecked()) {
                                tv.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_sec_true));
                                tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                            }
                            params.setMargins(12, 12, 12, 12);
                            notwantFluidlayout.addView(tv, params);
                            final int finalI = i;
                            tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (!nowantList.get(finalI).isChecked()) {
                                        tv.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_sec_true));
                                        tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                                        nowantList.get(finalI).setChecked(true);
                                    } else {
                                        tv.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_sec_false));
                                        tv.setTextColor(getResources().getColor(R.color.text_gray));
                                        nowantList.get(finalI).setChecked(false);
                                    }
                                }
                            });
                        }

                    }
                });
    }

    @OnClick({R.id.title_back_iv, R.id.finish_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.finish_tv:
                Intent intent = new Intent(mContext, ShendengListActivity.class);
                intent.putExtra("hxs", getIntent().getStringExtra("hxs"));
                intent.putExtra("zjId", getIntent().getStringExtra("zjId"));
                getWantAllText();
                intent.putExtra("checkedcontent", (Serializable) allCheckedTextList);
                intent.putExtra("qys", (Serializable) getIntent().getSerializableExtra("qys"));
                intent.putExtra("want", hashMap_want);
                intent.putExtra("dontwant", hashMap_dontwant);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void getWantAllText() {
        if (wantList != null && wantList.size() > 0) {
            for (int i = 0; i < wantList.size(); i++) {
                if (wantList.get(i).isChecked()) {
                    allCheckedTextList.add(wantList.get(i).getName());
                }
            }
        }
    }

    private List<OneCheckBean> getxiangyao(ShenDengRuleBean.DatasEntity.XytjEntity xytj) {
        List<OneCheckBean> wantList = new ArrayList<>();
        if (xytj != null) {
            List<ShenDengRuleBean.DatasEntity.XytjEntity.ChaoxiangEntity> chaoxiang = xytj.getChaoxiang();
            if (chaoxiang != null && chaoxiang.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < chaoxiang.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? chaoxiang.get(i).getScreeValJpn() : chaoxiang.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(chaoxiang.get(i).getId() + "");
                }
                hashMap_want.put("cxs", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.ChezhanjuliEntity> chezhanjuli = xytj.getChezhanjuli();
            if (chezhanjuli != null && chezhanjuli.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < chezhanjuli.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? chezhanjuli.get(i).getScreeValJpn() : chezhanjuli.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(chezhanjuli.get(i).getId() + "");
                }
                hashMap_want.put("czjls", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.ChuqifeiyongEntity> chuqifeiyong = xytj.getChuqifeiyong();
            if (chuqifeiyong != null && chuqifeiyong.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < chuqifeiyong.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? chuqifeiyong.get(i).getScreeValJpn() : chuqifeiyong.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(chuqifeiyong.get(i).getId() + "");
                }
                hashMap_want.put("cqfys", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.ChuzuleixingEntity> chuzuleixing = xytj.getChuzuleixing();
            if (chuzuleixing != null && chuzuleixing.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < chuzuleixing.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? chuzuleixing.get(i).getScreeValJpn() : chuzuleixing.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(chuzuleixing.get(i).getId() + "");
                }
                hashMap_want.put("czlxs", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.DiduanEntity> diduan = xytj.getDiduan();
            if (diduan != null && diduan.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < diduan.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? diduan.get(i).getScreeValJpn() : diduan.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(diduan.get(i).getId() + "");
                }
                hashMap_want.put("dds", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.FangjianzhuangkuangEntity> fangjianzhuangkuang = xytj.getFangjianzhuangkuang();
            if (fangjianzhuangkuang != null && fangjianzhuangkuang.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < fangjianzhuangkuang.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? fangjianzhuangkuang.get(i).getScreeValJpn() : fangjianzhuangkuang.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(fangjianzhuangkuang.get(i).getId() + "");
                }
                hashMap_want.put("fjzks", list);
            }
            //户型不用写
            List<ShenDengRuleBean.DatasEntity.XytjEntity.HuxingEntity> huxing = xytj.getHuxing();
            if (huxing != null && huxing.size() > 0) {
                for (int i = 0; i < huxing.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? huxing.get(i).getScreeValJpn() : huxing.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                }
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.JianzhugouzaoEntity> jianzhugouzao = xytj.getJianzhugouzao();
            if (jianzhugouzao != null && jianzhugouzao.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < jianzhugouzao.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? jianzhugouzao.get(i).getScreeValJpn() : jianzhugouzao.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(jianzhugouzao.get(i).getId() + "");
                }
                hashMap_want.put("jzgzs", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.JianzhunianfenEntity> jianzhunianfen = xytj.getJianzhunianfen();
            if (jianzhunianfen != null && jianzhunianfen.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < jianzhunianfen.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? jianzhunianfen.get(i).getScreeValJpn() : jianzhunianfen.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(jianzhunianfen.get(i).getId() + "");
                }
                hashMap_want.put("jznfs", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.JiudianleixingEntity> jiudianleixing = xytj.getJiudianleixing();
            if (jiudianleixing != null && jiudianleixing.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < jiudianleixing.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? jiudianleixing.get(i).getScreeValJpn() : jiudianleixing.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(jiudianleixing.get(i).getId() + "");
                }
                hashMap_want.put("jdlxs", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.JiudianloucengshuEntity> jiudianloucengshu = xytj.getJiudianloucengshu();
            if (jiudianloucengshu != null && jiudianloucengshu.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < jiudianloucengshu.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? jiudianloucengshu.get(i).getScreeValJpn() : jiudianloucengshu.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(jiudianloucengshu.get(i).getId() + "");
                }
                hashMap_want.put("jdlcs", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.LoucengEntity> louceng = xytj.getLouceng();
            if (louceng != null && louceng.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < louceng.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? louceng.get(i).getScreeValJpn() : louceng.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(louceng.get(i).getId() + "");
                }
                hashMap_want.put("lcs", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.MianjiEntity> mianji = xytj.getMianji();
            if (mianji != null && mianji.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < mianji.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? mianji.get(i).getScreeValJpn() : mianji.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(mianji.get(i).getId() + "");
                }
                hashMap_want.put("mjId", list);
            }
            //租金不用写
            List<ShenDengRuleBean.DatasEntity.XytjEntity.ZujinEntity> zujin = xytj.getZujin();
            if (zujin != null && zujin.size() > 0) {
                for (int i = 0; i < zujin.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? zujin.get(i).getScreeValJpn() : zujin.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                }
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.YingyeleixingEntity> yingyeleixing = xytj.getYingyeleixing();
            if (yingyeleixing != null && yingyeleixing.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < yingyeleixing.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? yingyeleixing.get(i).getScreeValJpn() : yingyeleixing.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(yingyeleixing.get(i).getId() + "");
                }
                hashMap_want.put("yylxs", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.XianzhuangEntity> xianzhuang = xytj.getXianzhuang();
            if (xianzhuang != null && xianzhuang.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < xianzhuang.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? xianzhuang.get(i).getScreeValJpn() : xianzhuang.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(xianzhuang.get(i).getId() + "");
                }
                hashMap_want.put("xzs", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.TiaojianEntity> tiaojian = xytj.getTiaojian();
            if (tiaojian != null && tiaojian.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < tiaojian.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? tiaojian.get(i).getScreeValJpn() : tiaojian.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(tiaojian.get(i).getId() + "");
                }
                hashMap_want.put("tjs", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.TezhengEntity> tezheng = xytj.getTezheng();
            if (tezheng != null && tezheng.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < tezheng.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? tezheng.get(i).getScreeValJpn() : tezheng.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(tezheng.get(i).getId() + "");
                }
                hashMap_want.put("tzs", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.SuoyouquanEntity> suoyouquan = xytj.getSuoyouquan();
            if (suoyouquan != null && suoyouquan.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < suoyouquan.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? suoyouquan.get(i).getScreeValJpn() : suoyouquan.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(suoyouquan.get(i).getId() + "");
                }
                hashMap_want.put("syqs", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.ShoumaileixingEntity> shoumaileixing = xytj.getShoumaileixing();
            if (shoumaileixing != null && shoumaileixing.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < shoumaileixing.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? shoumaileixing.get(i).getScreeValJpn() : shoumaileixing.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(shoumaileixing.get(i).getId() + "");
                }
                hashMap_want.put("smlxs", list);
            }
            //售价不用写
            List<ShenDengRuleBean.DatasEntity.XytjEntity.ShoujiaEntity> shoujia = xytj.getShoujia();
            if (shoujia != null && shoujia.size() > 0) {
                for (int i = 0; i < shoujia.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? shoujia.get(i).getScreeValJpn() : shoujia.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                }
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.RujuriqiEntity> rujuriqi = xytj.getRujuriqi();
            if (rujuriqi != null && rujuriqi.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < rujuriqi.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? rujuriqi.get(i).getScreeValJpn() : rujuriqi.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(rujuriqi.get(i).getId() + "");
                }
                hashMap_want.put("rjrqs", list);
            }
            List<ShenDengRuleBean.DatasEntity.XytjEntity.RenqixuanzeEntity> renqixuanze = xytj.getRenqixuanze();
            if (renqixuanze != null && renqixuanze.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < renqixuanze.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? renqixuanze.get(i).getScreeValJpn() : renqixuanze.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(renqixuanze.get(i).getId() + "");
                }
                hashMap_want.put("rqxzs", list);
            }
        }
        return wantList;
    }

    private List<OneCheckBean> getbuxiangyao(ShenDengRuleBean.DatasEntity.BxytjEntity bxytjEntity) {
        List<OneCheckBean> wantList = new ArrayList<>();
        if (bxytjEntity != null) {
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.ChaoxiangEntity> chaoxiang = bxytjEntity.getChaoxiang();
            if (chaoxiang != null && chaoxiang.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < chaoxiang.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? chaoxiang.get(i).getScreeValJpn() : chaoxiang.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(chaoxiang.get(i).getId() + "");
                }
                hashMap_dontwant.put("nocxs", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.ChezhanjuliEntity> chezhanjuli = bxytjEntity.getChezhanjuli();
            if (chezhanjuli != null && chezhanjuli.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < chezhanjuli.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? chezhanjuli.get(i).getScreeValJpn() : chezhanjuli.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(chezhanjuli.get(i).getId() + "");
                }
                hashMap_dontwant.put("noczjls", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.ChuqifeiyongEntity> chuqifeiyong = bxytjEntity.getChuqifeiyong();
            if (chuqifeiyong != null && chuqifeiyong.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < chuqifeiyong.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? chuqifeiyong.get(i).getScreeValJpn() : chuqifeiyong.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(chuqifeiyong.get(i).getId() + "");
                }
                hashMap_dontwant.put("nocqfys", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.ChuzuleixingEntity> chuzuleixing = bxytjEntity.getChuzuleixing();
            if (chuzuleixing != null && chuzuleixing.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < chuzuleixing.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? chuzuleixing.get(i).getScreeValJpn() : chuzuleixing.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(chuzuleixing.get(i).getId() + "");
                }
                hashMap_dontwant.put("noczlxs", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.DiduanEntity> diduan = bxytjEntity.getDiduan();
            if (diduan != null && diduan.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < diduan.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? diduan.get(i).getScreeValJpn() : diduan.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(diduan.get(i).getId() + "");
                }
                hashMap_dontwant.put("nodds", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.FangjianzhuangkuangEntity> fangjianzhuangkuang = bxytjEntity.getFangjianzhuangkuang();
            if (fangjianzhuangkuang != null && fangjianzhuangkuang.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < fangjianzhuangkuang.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? fangjianzhuangkuang.get(i).getScreeValJpn() : fangjianzhuangkuang.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(fangjianzhuangkuang.get(i).getId() + "");
                }
                hashMap_dontwant.put("nofjzks", list);
            }
            //户型不用写
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.HuxingEntity> huxing = bxytjEntity.getHuxing();
            if (huxing != null && huxing.size() > 0) {
                for (int i = 0; i < huxing.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? huxing.get(i).getScreeValJpn() : huxing.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                }
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.JianzhugouzaoEntity> jianzhugouzao = bxytjEntity.getJianzhugouzao();
            if (jianzhugouzao != null && jianzhugouzao.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < jianzhugouzao.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? jianzhugouzao.get(i).getScreeValJpn() : jianzhugouzao.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(jianzhugouzao.get(i).getId() + "");
                }
                hashMap_dontwant.put("nojzgzs", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.JianzhunianfenEntity> jianzhunianfen = bxytjEntity.getJianzhunianfen();
            if (jianzhunianfen != null && jianzhunianfen.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < jianzhunianfen.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? jianzhunianfen.get(i).getScreeValJpn() : jianzhunianfen.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(jianzhunianfen.get(i).getId() + "");
                }
                hashMap_dontwant.put("nojznfs", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.JiudianleixingEntity> jiudianleixing = bxytjEntity.getJiudianleixing();
            if (jiudianleixing != null && jiudianleixing.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < jiudianleixing.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? jiudianleixing.get(i).getScreeValJpn() : jiudianleixing.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(jiudianleixing.get(i).getId() + "");
                }
                hashMap_dontwant.put("nojdlxs", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.JiudianloucengshuEntity> jiudianloucengshu = bxytjEntity.getJiudianloucengshu();
            if (jiudianloucengshu != null && jiudianloucengshu.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < jiudianloucengshu.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? jiudianloucengshu.get(i).getScreeValJpn() : jiudianloucengshu.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(jiudianloucengshu.get(i).getId() + "");
                }
                hashMap_dontwant.put("nojdlcs", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.LoucengEntity> louceng = bxytjEntity.getLouceng();
            if (louceng != null && louceng.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < louceng.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? louceng.get(i).getScreeValJpn() : louceng.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(louceng.get(i).getId() + "");
                }
                hashMap_dontwant.put("nolcs", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.MianjiEntity> mianji = bxytjEntity.getMianji();
            if (mianji != null && mianji.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < mianji.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? mianji.get(i).getScreeValJpn() : mianji.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(mianji.get(i).getId() + "");
                }
                hashMap_dontwant.put("nomjId", list);
            }
            //租金不用写
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.ZujinEntity> zujin = bxytjEntity.getZujin();
            if (zujin != null && zujin.size() > 0) {
                for (int i = 0; i < zujin.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? zujin.get(i).getScreeValJpn() : zujin.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                }
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.YingyeleixingEntity> yingyeleixing = bxytjEntity.getYingyeleixing();
            if (yingyeleixing != null && yingyeleixing.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < yingyeleixing.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? yingyeleixing.get(i).getScreeValJpn() : yingyeleixing.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(yingyeleixing.get(i).getId() + "");
                }
                hashMap_dontwant.put("noyylxs", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.XianzhuangEntity> xianzhuang = bxytjEntity.getXianzhuang();
            if (xianzhuang != null && xianzhuang.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < xianzhuang.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? xianzhuang.get(i).getScreeValJpn() : xianzhuang.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(xianzhuang.get(i).getId() + "");
                }
                hashMap_dontwant.put("noxzs", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.TiaojianEntity> tiaojian = bxytjEntity.getTiaojian();
            if (tiaojian != null && tiaojian.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < tiaojian.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? tiaojian.get(i).getScreeValJpn() : tiaojian.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(tiaojian.get(i).getId() + "");
                }
                hashMap_dontwant.put("notjs", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.TezhengEntity> tezheng = bxytjEntity.getTezheng();
            if (tezheng != null && tezheng.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < tezheng.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? tezheng.get(i).getScreeValJpn() : tezheng.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(tezheng.get(i).getId() + "");
                }
                hashMap_dontwant.put("notzs", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.SuoyouquanEntity> suoyouquan = bxytjEntity.getSuoyouquan();
            if (suoyouquan != null && suoyouquan.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < suoyouquan.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? suoyouquan.get(i).getScreeValJpn() : suoyouquan.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(suoyouquan.get(i).getId() + "");
                }
                hashMap_dontwant.put("nosyqs", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.ShoumaileixingEntity> shoumaileixing = bxytjEntity.getShoumaileixing();
            if (shoumaileixing != null && shoumaileixing.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < shoumaileixing.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? shoumaileixing.get(i).getScreeValJpn() : shoumaileixing.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(shoumaileixing.get(i).getId() + "");
                }
                hashMap_dontwant.put("nosmlxs", list);
            }
            //售价不用写
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.ShoujiaEntity> shoujia = bxytjEntity.getShoujia();
            if (shoujia != null && shoujia.size() > 0) {
                for (int i = 0; i < shoujia.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? shoujia.get(i).getScreeValJpn() : shoujia.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                }
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.RujuriqiEntity> rujuriqi = bxytjEntity.getRujuriqi();
            if (rujuriqi != null && rujuriqi.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < rujuriqi.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? rujuriqi.get(i).getScreeValJpn() : rujuriqi.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(rujuriqi.get(i).getId() + "");
                }
                hashMap_dontwant.put("norjrqs", list);
            }
            List<ShenDengRuleBean.DatasEntity.BxytjEntity.RenqixuanzeEntity> renqixuanze = bxytjEntity.getRenqixuanze();
            if (renqixuanze != null && renqixuanze.size() > 0) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < renqixuanze.size(); i++) {
                    OneCheckBean oneCheckBean = new OneCheckBean(false,
                            isJa ? renqixuanze.get(i).getScreeValJpn() : renqixuanze.get(i).getScreeValCn());
                    wantList.add(oneCheckBean);
                    list.add(renqixuanze.get(i).getId() + "");
                }
                hashMap_dontwant.put("norqxzs", list);
            }
        }
        return wantList;
    }
}
