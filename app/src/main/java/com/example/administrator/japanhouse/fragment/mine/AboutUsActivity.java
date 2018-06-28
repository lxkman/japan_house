package com.example.administrator.japanhouse.fragment.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.activity.WebActivity;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.im.ImManager;
import com.example.administrator.japanhouse.login.UserAgreementActivity;
import com.example.administrator.japanhouse.utils.TUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.ll_gomarket)
    LinearLayout llGomarket;
    @BindView(R.id.ll_des)
    LinearLayout llDes;
    @BindView(R.id.activity_zui_jin)
    LinearLayout activityZuiJin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        backImg.setOnClickListener(this);
        llGomarket.setOnClickListener(this);
        llDes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img:
                finish();
                break;
            case R.id.ll_gomarket:
                String mAddress = "market://details?id=" + getPackageName();
                Intent marketIntent = new Intent("android.intent.action.VIEW");
                marketIntent.setData(Uri.parse(mAddress));
                if (marketIntent.resolveActivity(getPackageManager()) != null) { //可以接收
                    startActivity(marketIntent);
                } else {
                    TUtils.showFail(this, getString(R.string.no_apk));
                }
                break;
            case R.id.ll_des:

                Intent intent = new Intent(this, WebActivity.class);
                intent.putExtra("title", getString(R.string.aboutus));
                if (MyApplication.isJapanese()) {
                    intent.putExtra("result", "http://39.106.131.61:8080/hwdch5/info/Agreement.html?type=4");
                } else {
                    intent.putExtra("result", "http://39.106.131.61:8080/hwdch5/info/AgreementCn.html?type=4");
                }
                startActivity(intent);
                break;
        }
    }
}
