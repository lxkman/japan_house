package com.example.administrator.japanhouse.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;

/**
 * Created by Administrator on 2017/8/24.
 */

public class LancherActivity extends BaseActivity {
    private Handler mHandler = new Handler();
    private ImageView iv_launcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        iv_launcher = (ImageView) findViewById(R.id.iv_launcher);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean guide = SharedPreferencesUtils.getInstace(LancherActivity.this).getBooleanPreference("guide", false);
                if (!guide) {
                    Intent intent = new Intent(LancherActivity.this, GuidePageActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Intent intent = new Intent(LancherActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        }, 1500);
    }


}
