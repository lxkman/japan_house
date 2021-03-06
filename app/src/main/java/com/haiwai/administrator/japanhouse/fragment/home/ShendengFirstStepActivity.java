package com.haiwai.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.bean.MoreCheckBean;
import com.haiwai.administrator.japanhouse.bean.OneCheckBean;
import com.haiwai.administrator.japanhouse.bean.QuYuBean;
import com.haiwai.administrator.japanhouse.bean.ShenDengRuleBean;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.callback.JsonCallback;
import com.haiwai.administrator.japanhouse.utils.CacheUtils;
import com.haiwai.administrator.japanhouse.utils.Constants;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.haiwai.administrator.japanhouse.utils.MyUtils;
import com.haiwai.administrator.japanhouse.utils.TUtils;
import com.haiwai.administrator.japanhouse.view.BaseDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShendengFirstStepActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.yusuan_recycler)
    RecyclerView yusuanRecycler;
    @BindView(R.id.huxing_recycler)
    RecyclerView huxingRecycler;
    @BindView(R.id.weizhi_recycler)
    RecyclerView weizhiRecycler;
    @BindView(R.id.next_tv)
    TextView nextTv;
    private List<OneCheckBean> yusuanList;
    private boolean isJa;
    private List<OneCheckBean> huxingList;
    private List<OneCheckBean> weizhiList;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private ThreeAdapter threeAdapter;
    private List<MoreCheckBean> mQuyuListBean = new ArrayList<>();
    private int adapterPosition2;
    private List<OneCheckBean> mList3 = new ArrayList();
    private QuYuAdapter quYuAdapter;
    private RecyclerView mrecycler2;
    private RecyclerView mrecycler3;
    private List<String> allCheckedTextList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shendeng_first_step);
        ButterKnife.bind(this);
        String country = CacheUtils.get(Constants.COUNTRY);
        if (country != null && country.equals("ja")) {
            isJa = true;
        } else {
            isJa = false;
        }
        initView();
        initData();
        initquyu();
    }

    private void initData() {
        OkGo.<ShenDengRuleBean>post(MyUrls.BASEURL + "/app/twoscreening/sdzftj")
                .tag(this)
                .execute(new DialogCallback<ShenDengRuleBean>(ShendengFirstStepActivity.this, ShenDengRuleBean.class) {
                    @Override
                    public void onSuccess(Response<ShenDengRuleBean> response) {
                        int code = response.code();
                        ShenDengRuleBean shenDengRuleBean = response.body();
                        if (shenDengRuleBean == null) {
                            return;
                        }
                        ShenDengRuleBean.DatasEntity datas = shenDengRuleBean.getDatas();
                        ShenDengRuleBean.DatasEntity.PttjEntity pttj = datas.getPttj();
                        List<ShenDengRuleBean.DatasEntity.PttjEntity.ZujinEntity> zujin = pttj.getZujin();
                        List<ShenDengRuleBean.DatasEntity.PttjEntity.HuxingEntity> huxing = pttj.getHuxing();
                        List<ShenDengRuleBean.DatasEntity.PttjEntity.QyEntity> qy = pttj.getQy();
                        if (zujin != null && zujin.size() > 0) {
                            yusuanList = new ArrayList<>();
                            for (int i = 0; i < zujin.size(); i++) {
                                if (i == 0) {
                                    yusuanList.add(new OneCheckBean(true, isJa ? zujin.get(i).getScreeValJpn() : zujin.get(i).getScreeValCn()));
                                } else {
                                    yusuanList.add(new OneCheckBean(false, isJa ? zujin.get(i).getScreeValJpn() : zujin.get(i).getScreeValCn()));
                                }
                            }
                            final YusuanAdapter yusuanAdapter = new YusuanAdapter(R.layout.item_shendeng_yusuan, yusuanList);
                            yusuanRecycler.setAdapter(yusuanAdapter);
                            yusuanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    for (int i = 0; i < yusuanList.size(); i++) {
                                        if (i == position) {
                                            yusuanList.get(i).setChecked(true);
                                        } else {
                                            yusuanList.get(i).setChecked(false);
                                        }
                                    }
                                    yusuanAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                        if (huxing != null && huxing.size() > 0) {
                            huxingList = new ArrayList<>();
                            for (int i = 0; i < huxing.size(); i++) {
                                if (i == 0) {
                                    huxingList.add(new OneCheckBean(true, isJa ? huxing.get(i).getScreeValJpn() : huxing.get(i).getScreeValCn()));
                                } else {
                                    huxingList.add(new OneCheckBean(false, isJa ? huxing.get(i).getScreeValJpn() : huxing.get(i).getScreeValCn()));
                                }
                            }
                            final HuxingAdapter huxingAdapter = new HuxingAdapter(R.layout.item_shendeng_yusuan, huxingList);
                            huxingRecycler.setAdapter(huxingAdapter);
                            huxingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    for (int i = 0; i < huxingList.size(); i++) {
                                        if (i == position) {
                                            huxingList.get(i).setChecked(true);
                                        } else {
                                            huxingList.get(i).setChecked(false);
                                        }
                                    }
                                    huxingAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                        if (qy != null && qy.size() > 0) {
                            weizhiList = new ArrayList<>();
                            for (int i = 0; i < qy.size(); i++) {
                                if (i == 0) {
                                    weizhiList.add(new OneCheckBean(true, isJa ? qy.get(i).getAreaNameJpn() : qy.get(i).getAreaNameCn()));
                                } else {
                                    weizhiList.add(new OneCheckBean(false, isJa ? qy.get(i).getAreaNameJpn() : qy.get(i).getAreaNameCn()));
                                }
                            }
                            final WeizhiAdapter weizhiAdapter = new WeizhiAdapter(R.layout.item_shendeng_yusuan, weizhiList);
                            View footerView = LayoutInflater.from(ShendengFirstStepActivity.this).inflate(R.layout.item_footer_shendeng, weizhiRecycler, false);
                            weizhiAdapter.addFooterView(footerView);
                            weizhiRecycler.setAdapter(weizhiAdapter);
                            LinearLayout footerview = (LinearLayout) footerView.findViewById(R.id.footerview);
                            footerview.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    showLocationDialog();
                                }
                            });
                            weizhiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    for (int i = 0; i < weizhiList.size(); i++) {
                                        if (i == position) {
                                            weizhiList.get(i).setChecked(true);
                                        } else {
                                            weizhiList.get(i).setChecked(false);
                                        }
                                    }
                                    weizhiAdapter.notifyDataSetChanged();
                                }
                            });
                        }

                    }
                });
    }

    private boolean isYuSuanChecked() {
        if (yusuanList != null && yusuanList.size() > 0) {
            for (int i = 0; i < yusuanList.size(); i++) {
                if (yusuanList.get(i).isChecked()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isWeizhiChecked() {
        if (weizhiList != null && weizhiList.size() > 0) {
            for (int i = 0; i < weizhiList.size(); i++) {
                if (weizhiList.get(i).isChecked()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isHuxingChecked() {
        if (huxingList != null && huxingList.size() > 0) {
            for (int i = 0; i < huxingList.size(); i++) {
                if (huxingList.get(i).isChecked()) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<String> getAllWeizhiChecked() {
        List<String> weizhi = new ArrayList<>();
        if (weizhiList != null && weizhiList.size() > 0) {
            for (int i = 0; i < weizhiList.size(); i++) {
                if (weizhiList.get(i).isChecked()) {
                    weizhi.add(weizhiList.get(i).getId() + "");
                    allCheckedTextList.add(weizhiList.get(i).getName());
                }
            }
        }
        return weizhi;
    }

    private List<String> getAllMoreWeizhiCheckedItem(int adapterPosition2) {
        List<String> list = new ArrayList<>();
        if (mQuyuListBean != null && mQuyuListBean.size() > 0) {
            List<OneCheckBean> checkBeanList = mQuyuListBean.get(adapterPosition2).getCheckBeanList();
            if (checkBeanList != null && checkBeanList.size() > 0) {
                for (int i1 = 0; i1 < checkBeanList.size(); i1++) {
                    if (checkBeanList.get(0).isChecked()) {
                        list.add(checkBeanList.get(i1).getId() + "");
                        allCheckedTextList.add(checkBeanList.get(i1).getName());
                    } else if (i1 != 0 && checkBeanList.get(i1).isChecked()) {
                        list.add(checkBeanList.get(i1).getId() + "");
                        allCheckedTextList.add(checkBeanList.get(i1).getName());
                    }
                }
            }
        }
        return list;
    }

    private String getAllYuSuanChecked() {
        String yusuan = "";
        if (yusuanList != null && yusuanList.size() > 0) {
            for (int i = 0; i < yusuanList.size(); i++) {
                if (yusuanList.get(i).isChecked()) {
                    yusuan = yusuanList.get(i).getId() + "";
                    allCheckedTextList.add(yusuanList.get(i).getName());
                }
            }
        }
        return yusuan;
    }

    private String getAllHuxingChecked() {
        String huxingid = "";
        if (huxingList != null && huxingList.size() > 0) {
            for (int i = 0; i < huxingList.size(); i++) {
                if (huxingList.get(i).isChecked()) {
                    huxingid = huxingList.get(i).getId() + "";
                    allCheckedTextList.add(huxingList.get(i).getName());
                }
            }
        }
        return huxingid;
    }


    private void initquyu() {
        HttpParams params = new HttpParams();
        params.put("cId", 2);
        OkGo.<QuYuBean>post(MyUrls.BASEURL + "/app/areamanage/selectareaandsubway")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<QuYuBean>(QuYuBean.class) {
                    @Override
                    public void onSuccess(Response<QuYuBean> response) {
                        QuYuBean body = response.body();
                        QuYuBean.DatasEntity datas = body.getDatas();
                        List<QuYuBean.DatasEntity.AreasEntity> areas = datas.getAreas();
                        List<MoreCheckBean> quyuListBean = new ArrayList<MoreCheckBean>();
                        if (areas != null && areas.size() > 0) {
                            for (int i = 0; i < areas.size(); i++) {
                                QuYuBean.DatasEntity.AreasEntity areasEntity = areas.get(i);
                                if (areasEntity != null) {
                                    String administrationNameCn = areasEntity.getAdministrationNameCn();
                                    String administrationNameJpn = areasEntity.getAdministrationNameJpn();
                                    MoreCheckBean moreCheckBean = new MoreCheckBean();
                                    moreCheckBean.setName(isJa ? administrationNameJpn : administrationNameCn);
                                    moreCheckBean.setId(areasEntity.getId());
                                    List<QuYuBean.DatasEntity.AreasEntity.HwdcAreaManagesEntity> hwdcAreaManages = areasEntity.getHwdcAreaManages();
                                    List<OneCheckBean> oneCheckBeanList = new ArrayList<OneCheckBean>();
                                    oneCheckBeanList.add(new OneCheckBean(true, getResources().getString(R.string.buxian)));
                                    if (hwdcAreaManages != null && hwdcAreaManages.size() > 0) {
                                        for (int i1 = 0; i1 < hwdcAreaManages.size(); i1++) {
                                            int id = hwdcAreaManages.get(i1).getId();
                                            String areaNameCn = hwdcAreaManages.get(i1).getAreaNameCn();
                                            String areaNameJpn = hwdcAreaManages.get(i1).getAreaNameJpn();
                                            OneCheckBean oneCheckBean = new OneCheckBean(false, isJa ? areaNameJpn : areaNameCn, id);
                                            oneCheckBeanList.add(oneCheckBean);
                                        }
                                    }
                                    moreCheckBean.setCheckBeanList(oneCheckBeanList);
                                    quyuListBean.add(moreCheckBean);
                                }
                            }
                        }
                        mQuyuListBean = quyuListBean;
                    }
                });
    }

    private void initView() {
        yusuanRecycler.setNestedScrollingEnabled(false);
        yusuanRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
        huxingRecycler.setNestedScrollingEnabled(false);
        huxingRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
        weizhiRecycler.setNestedScrollingEnabled(false);
        weizhiRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
    }

    private void showLocationDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_shendengquyu)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.BOTTOM)
                //设置动画
                .setAnimation(R.style.Bottom_Top_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, MyUtils.getScreenHeight(this) / 2)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        mDialog.getView(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShendengSecondStepActivity.class);
                intent.putExtra("hxs", getAllHuxingChecked());
                intent.putExtra("zjId", getAllYuSuanChecked());
                List<String> allWeizhiChecked = getAllMoreWeizhiCheckedItem(adapterPosition2);
                intent.putExtra("qys", (Serializable) allWeizhiChecked);
                intent.putExtra("checkedcontent", (Serializable) allCheckedTextList);
                startActivity(intent);
                mDialog.dismiss();
                finish();
            }
        });
        mrecycler2 = mDialog.getView(R.id.Mrecycler2);
        mrecycler3 = mDialog.getView(R.id.Mrecycler3);
        mrecycler2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mrecycler2.setNestedScrollingEnabled(false);
        mrecycler3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mrecycler3.setNestedScrollingEnabled(false);
        initDialogData();
        mDialog.show();
    }

    private void initDialogData() {
        quYuAdapter = new QuYuAdapter(R.layout.leixing_item2, mQuyuListBean);
        mrecycler2.setAdapter(quYuAdapter);
        mList3 = mQuyuListBean.get(adapterPosition2).getCheckBeanList();
        threeAdapter = new ThreeAdapter(R.layout.leixing_item2, mList3);
        mrecycler2.setAdapter(quYuAdapter);
        mrecycler3.setAdapter(threeAdapter);
    }

    private class YusuanAdapter extends BaseQuickAdapter<OneCheckBean, BaseViewHolder> {

        public YusuanAdapter(int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final OneCheckBean item) {
            final TextView textView = helper.getView(R.id.item_content_tv);
            textView.setText(item.getName());
            if (item.isChecked()) {
                textView.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_true));
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
            } else {
                textView.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_false));
                textView.setTextColor(getResources().getColor(R.color.text_gray));
            }
        }
    }

    private class HuxingAdapter extends BaseQuickAdapter<OneCheckBean, BaseViewHolder> {

        public HuxingAdapter(int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OneCheckBean item) {
            TextView textView = helper.getView(R.id.item_content_tv);
            textView.setText(item.getName());
            if (item.isChecked()) {
                textView.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_true));
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
            } else {
                textView.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_false));
                textView.setTextColor(getResources().getColor(R.color.text_gray));
            }
        }
    }

    private class WeizhiAdapter extends BaseQuickAdapter<OneCheckBean, BaseViewHolder> {

        public WeizhiAdapter(int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OneCheckBean item) {
            final TextView textView = helper.getView(R.id.item_content_tv);
            textView.setText(item.getName());
            if (item.isChecked()) {
                textView.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_true));
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
            } else {
                textView.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_false));
                textView.setTextColor(getResources().getColor(R.color.text_gray));
            }
        }
    }

    @OnClick({R.id.title_back_iv, R.id.next_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.next_tv:
                //防止数据为空的情况
                if (!isYuSuanChecked()){
                    TUtils.showFail(this,getResources().getString(R.string.xinliyusuanyouduoshao));
                    return;
                }
                if (!isHuxingChecked()){
                    TUtils.showFail(this,getResources().getString(R.string.xihuanshenemhuxing));
                    return;
                }
                if (!isWeizhiChecked()){
                    TUtils.showFail(this,getResources().getString(R.string.xinyiweizhizaina));
                    return;
                }
                Intent intent = new Intent(mContext, ShendengSecondStepActivity.class);
                intent.putExtra("hxs", getAllHuxingChecked());
                intent.putExtra("zjId", getAllYuSuanChecked());
                List<String> allWeizhiChecked = getAllWeizhiChecked();
                intent.putExtra("qys", (Serializable) allWeizhiChecked);
                intent.putExtra("checkedcontent", (Serializable) allCheckedTextList);
                startActivity(intent);
                finish();
                break;
        }
    }


    class QuYuAdapter extends BaseQuickAdapter<MoreCheckBean, BaseViewHolder> {

        public QuYuAdapter(@LayoutRes int layoutResId, @Nullable List<MoreCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, MoreCheckBean item) {
            helper.setText(R.id.rb_title, item.getName());
            helper.setChecked(R.id.rb_title, item.isChecked());
            helper.setVisible(R.id.img_isCheck, false);
            helper.getView(R.id.rb_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapterPosition2 == helper.getAdapterPosition()) {
                        return;
                    }
                    adapterPosition2 = helper.getAdapterPosition();
                    for (int i = 0; i < mQuyuListBean.size(); i++) {
                        if (adapterPosition2 == i) {
                            mQuyuListBean.get(i).setChecked(true);
                        } else {
                            mQuyuListBean.get(i).setChecked(false);
                        }
                    }
                    setFirstCheckedItem(mQuyuListBean, adapterPosition2);
                    initDialogData();
                }

            });
        }
    }

    /*点击朝阳区的时候清除其他区域所有item的选中状态*/
    private void setFirstCheckedItem(List<MoreCheckBean> list, int adapterPosition) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                List<OneCheckBean> checkBeanList = list.get(i).getCheckBeanList();
                if (checkBeanList != null && checkBeanList.size() > 0) {
                    if (i != adapterPosition) {
                        for (int i1 = 0; i1 < checkBeanList.size(); i1++) {
                            OneCheckBean oneCheckBean = checkBeanList.get(i1);
                            if (i1 == 0) {
                                oneCheckBean.setChecked(true);
                            } else {
                                oneCheckBean.setChecked(false);
                            }
                        }
                    }
                }
            }
        }
    }

    class ThreeAdapter extends BaseQuickAdapter<OneCheckBean, BaseViewHolder> {

        public ThreeAdapter(@LayoutRes int layoutResId, @Nullable List<OneCheckBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, OneCheckBean item) {
            helper.setText(R.id.rb_title, item.getName());
            helper.setChecked(R.id.rb_title, item.isChecked());
            helper.setVisible(R.id.img_isCheck, item.isChecked());
            final int adapterPosition = helper.getAdapterPosition();
            helper.getView(R.id.rb_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapterPosition == 0) {
                        for (int i = 0; i < mList3.size(); i++) {
                            if (i == 0) {
                                mList3.get(0).setChecked(true);
                            } else {
                                mList3.get(i).setChecked(false);
                            }
                        }
                    } else {
                        mList3.get(adapterPosition).setChecked(!mList3.get(adapterPosition).isChecked());
                        if (getList3Checked()) {
                            mList3.get(0).setChecked(false);
                        } else {
                            mList3.get(0).setChecked(true);
                        }
                    }
                    threeAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    private boolean getList3Checked() {
        for (int i = 1; i < mList3.size(); i++) {
            if (mList3.get(i).isChecked()) {
                return true;
            }
        }
        return false;
    }
}
