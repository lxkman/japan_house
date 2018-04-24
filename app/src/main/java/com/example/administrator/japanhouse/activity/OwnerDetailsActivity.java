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
 * 百科详情
 * Created by   admin on 2018/4/17.
 */

public class OwnerDetailsActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_details);
        ButterKnife.bind(this);

        final int state = getIntent().getIntExtra("state", 0);

        findViewById(R.id.act_owner_details_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (state != 0) {
//                    OwnerWikipediaActivity.invoke(OwnerDetailsActivity.this);
//                }

                finish();
            }
        });
    }

    public static void invoke(Context context) {
        Intent intent = new Intent(context, OwnerDetailsActivity.class);
        context.startActivity(intent);
    }

    public static void invoke(Context context, int state) {
        Intent intent = new Intent(context, OwnerDetailsActivity.class);
        intent.putExtra("state", state);
        context.startActivity(intent);
    }
}
