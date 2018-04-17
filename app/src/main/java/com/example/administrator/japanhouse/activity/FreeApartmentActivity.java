package com.example.administrator.japanhouse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 免费看房
 * Created by   admin on 2018/4/17.
 */

public class FreeApartmentActivity extends BaseActivity {

    @BindView(R.id.act_apartment_back)
    ImageView actApartmentBack;
    @BindView(R.id.act_apartment_msg)
    ImageView actApartmentMsg;
    @BindView(R.id.act_apartment_recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_apartment);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.act_apartment_back, R.id.act_apartment_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_apartment_back:
                finish();
                break;

            case R.id.act_apartment_msg:
                //跳转到微聊

                break;
        }
    }

    public static void invoke(Context context){
        Intent intent = new Intent(context, FreeApartmentActivity.class);
        context.startActivity(intent);
    }
}
