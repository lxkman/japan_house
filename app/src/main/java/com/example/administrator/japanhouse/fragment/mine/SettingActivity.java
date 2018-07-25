package com.example.administrator.japanhouse.fragment.mine;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.model.NoDataBean;
import com.example.administrator.japanhouse.model.UserInfo;
import com.example.administrator.japanhouse.model.VersionBean;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.example.administrator.japanhouse.utils.TUtils;
import com.example.administrator.japanhouse.view.BaseDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.iv_switch)
    ImageView ivSwitch;
    @BindView(R.id.ll_language)
    LinearLayout llLanguage;
    @BindView(R.id.ll_about)
    LinearLayout llAbout;
    @BindView(R.id.ll_version)
    LinearLayout llVersion;
    @BindView(R.id.btn_loginout)
    Button btnLoginout;
    @BindView(R.id.activity_zui_jin)
    LinearLayout activityZuiJin;
    private int isClose = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ButterKnife.bind(this);

        if (MyApplication.getSwitchState() == 1) {
            ivSwitch.setImageResource(R.drawable.button_normal);
        } else {
            ivSwitch.setImageResource(R.drawable.button_green);
        }

        backImg.setOnClickListener(this);
        btnLoginout.setOnClickListener(this);
        ivSwitch.setOnClickListener(this);
        llLanguage.setOnClickListener(this);
        llAbout.setOnClickListener(this);
        llVersion.setOnClickListener(this);
    }

    private void showcallDialog() {
        BaseDialog.Builder builder = new BaseDialog.Builder(SettingActivity.this);
        //设置dialogpadding
        //设置显示位置
        //设置动画
        //设置dialog的宽高
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final BaseDialog
                dialog = builder.setViewId(R.layout.item_callnumber)
                //设置dialogpadding
                .setPaddingdp(70, 0, 70, 0)
                //设置显示位置
                .setGravity(Gravity.CENTER)
                //设置动画
                .setAnimation(R.style.bottom_tab_style)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        Button btnclear = dialog.getView(R.id.btn_call);
        Button btndismiss = dialog.getView(R.id.btn_dismiss);
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                MyApplication.logOut();
                finish();
                SharedPreferencesUtils.getInstace(SettingActivity.this).setStringPreference("token", "");
                EventBus.getDefault().postSticky(new EventBean(Constants.EVENT_MAIN));
            }
        });
        btndismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_loginout:
                showcallDialog();
                break;
            case R.id.back_img:
                finish();
                break;
            case R.id.iv_switch:
                if (MyApplication.getSwitchState() == 1) {
                    ivSwitch.setImageResource(R.drawable.button_green);
                    isClose = 0;
                } else {
                    ivSwitch.setImageResource(R.drawable.button_normal);
                    isClose = 1;
                }
                UserInfo.DatasBean.UserBean userBean = CacheUtils.get(Constants.P_USERINFO);
                userBean.setIsBrokerSay(isClose);
                CacheUtils.put(Constants.P_USERINFO, userBean);
                break;
            case R.id.ll_language:
                startActivity(new Intent(this, LanguageActivity.class));
                break;
            case R.id.ll_about:
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
            case R.id.ll_version:
                getVersiion();
                break;
        }
    }

    private void getVersiion() {
        OkGo.<VersionBean>post(MyUrls.BASEURL + "/app/version/getVersion")
                .tag(this)
                .execute(new DialogCallback<VersionBean>(SettingActivity.this, VersionBean.class) {
                    @Override
                    public void onSuccess(Response<VersionBean> response) {
                        if (response != null && response.body() != null && response.body().getDatas() != null && response.body().getDatas().getVersionNumber() != null) {
                            try {
                                String verName = getPackageManager().
                                        getPackageInfo(getPackageName(), 0).versionName;

                                if (TextUtils.equals(verName, response.body().getDatas().getVersionNumber())) {
                                    TUtils.showFail(SettingActivity.this, getString(R.string.max_version));
                                } else{
                                    String mAddress = "market://details?id=" + getPackageName();
                                    Intent marketIntent = new Intent("android.intent.action.VIEW");
                                    marketIntent.setData(Uri.parse(mAddress));
                                    if (marketIntent.resolveActivity(getPackageManager()) != null) { //可以接收
                                        startActivity(marketIntent);
                                    } else {
                                        TUtils.showFail(SettingActivity.this, getString(R.string.no_apk));
                                    }
                                }
                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @Override
    public void finish() {
        super.finish();
        if (CacheUtils.get(Constants.P_USERINFO) == null)
            return;
        UserInfo.DatasBean.UserBean userBean = CacheUtils.get(Constants.P_USERINFO);
        HttpParams params = new HttpParams();
        params.put("token", userBean.getToken());
        params.put("nickName", userBean.getNickname());
        params.put("sex", userBean.getSex());
        params.put("age", getTime(new Date(userBean.getBirthday())));
        params.put("picUrl", userBean.getPic());
        params.put("isBrokerSay", userBean.getIsBrokerSay());
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/user/updateinfo")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<NoDataBean>(NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {

                    }
                });
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
