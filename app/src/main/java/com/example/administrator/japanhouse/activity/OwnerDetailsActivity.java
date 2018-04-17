package com.example.administrator.japanhouse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 *  百科详情
 * Created by   admin on 2018/4/17.
 */

public class OwnerDetailsActivity extends BaseActivity {

    @BindView(R.id.act_owner_details_title)
    TextView tvTitle;
    @BindView(R.id.act_owner_details_time)
    TextView tvTime;
    @BindView(R.id.act_owner_details_msg)
    TextView tvMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_details);
        ButterKnife.bind(this);

        findViewById(R.id.act_owner_details_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/4/17 跳转操作 看UE图 执行逻辑
                finish();
            }
        });
    }

    public static void invoke(Context context){
        Intent intent = new Intent(context, OwnerDetailsActivity.class);
        context.startActivity(intent);
    }
}
