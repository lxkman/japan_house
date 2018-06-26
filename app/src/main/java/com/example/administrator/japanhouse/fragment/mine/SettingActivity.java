package com.example.administrator.japanhouse.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.login.LoginActivity;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.example.administrator.japanhouse.view.BaseDialog;

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
    private String isClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ButterKnife.bind(this);

        if (CacheUtils.get(Constants.MANAGER_T) == null) {
            isClose = "1";
            CacheUtils.put(Constants.MANAGER_T, isClose);
        } else {
            isClose = CacheUtils.get(Constants.MANAGER_T);
        }

        if (isClose.equals("1")) {
            ivSwitch.setImageResource(R.drawable.button_green);
        } else {
            ivSwitch.setImageResource(R.drawable.button_normal);
        }

        backImg.setOnClickListener(this);
        btnLoginout.setOnClickListener(this);
        ivSwitch.setOnClickListener(this);
        llLanguage.setOnClickListener(this);
        llAbout.setOnClickListener(this);
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
                removeAllActivitys();
                SharedPreferencesUtils.getInstace(SettingActivity.this).setStringPreference("token","");
                startActivity(new Intent(SettingActivity.this, LoginActivity.class));
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
                if (isClose.equals("1")) {
                    ivSwitch.setImageResource(R.drawable.button_normal);
                    isClose = "0";
                } else {
                    ivSwitch.setImageResource(R.drawable.button_green);
                    isClose = "1";
                }
                CacheUtils.put(Constants.MANAGER_T, isClose);
                break;
            case R.id.ll_language:
                startActivity(new Intent(this,LanguageActivity.class));
                break;
            case R.id.ll_about:
                startActivity(new Intent(this,AboutUsActivity.class));
                break;
        }
    }
}
