package com.example.administrator.japanhouse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.adapter.OwnerAdapter;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.fragment.home.FangjiadituActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我是业主
 * <p>
 * Created by   admin on 2018/4/16.
 */

public class OwnerActivity extends BaseActivity implements OwnerAdapter.onClickItemListener {

    @BindView(R.id.act_owner_back)
    ImageView back;
    @BindView(R.id.act_owner_message)
    ImageView ivMessage;
    @BindView(R.id.act_owner_rental)
    TextView tvRental;
    @BindView(R.id.act_owner_prices)
    TextView tvPrices;
    @BindView(R.id.act_owner_wikipedia)
    TextView tvWikipedia;
    @BindView(R.id.act_owner_recyclerView)
    RecyclerView recyclerview;

    private OwnerAdapter ownerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(manager);
        ownerAdapter = new OwnerAdapter(this);
        ownerAdapter.setOnClickItemListener(this);
        recyclerview.setAdapter(ownerAdapter);
    }

    @OnClick({R.id.act_owner_back, R.id.act_owner_message, R.id.act_owner_rental, R.id.act_owner_prices, R.id.act_owner_wikipedia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_owner_back:
                finish();
                break;

            case R.id.act_owner_message:
                setResult(2);
                finish();
                break;

            case R.id.act_owner_rental:
                Intent intent = new Intent(this, RentalActivity.class);
                startActivityForResult(intent, 10);
                break;

            case R.id.act_owner_prices:
                //跳转房价地图
                startActivity(new Intent(this, FangjiadituActivity.class));
                break;

            case R.id.act_owner_wikipedia:
                OwnerWikipediaActivity.invoke(this);
                break;
        }
    }

    public static void invoke(Context context) {
        Intent intent = new Intent(context, OwnerActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onItemClick() {
        OwnerDetailsActivity.invoke(this, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == 3) {
            setResult(4);
            finish();
        }
    }
}
