package com.example.administrator.japanhouse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.adapter.FreeApartmentAdapter;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.view.BaseDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 免费看房
 * Created by   admin on 2018/4/17.
 */

public class FreeApartmentActivity extends BaseActivity implements FreeApartmentAdapter.onClickListener{

    @BindView(R.id.act_apartment_back)
    ImageView actApartmentBack;
    @BindView(R.id.act_apartment_msg)
    ImageView actApartmentMsg;
    @BindView(R.id.act_apartment_recyclerView)
    RecyclerView mRecyclerView;

    private FreeApartmentAdapter freeApartmentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_apartment);
        ButterKnife.bind(this);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        freeApartmentAdapter = new FreeApartmentAdapter(this);
        freeApartmentAdapter.setOnClickListener(this);
        mRecyclerView.setAdapter(freeApartmentAdapter);


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

    private void showDialog() {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置dialogpadding
        //设置显示位置
        //设置动画
        //设置dialog的宽高
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final BaseDialog
                dialog = builder.setViewId(R.layout.dialog_checkroom)
                //设置dialogpadding
                .setPaddingdp(20, 0, 20, 0)
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
        dialog.getView(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }


    @Override
    public void onSignUpClickListener() {
        showDialog();
    }

    public static void invoke(Context context){
        Intent intent = new Intent(context, FreeApartmentActivity.class);
        context.startActivity(intent);
    }

}
