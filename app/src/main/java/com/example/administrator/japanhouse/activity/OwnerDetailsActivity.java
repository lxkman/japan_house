package com.example.administrator.japanhouse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.model.OwnerDetailsBean;
import com.example.administrator.japanhouse.model.OwnerListBean;
import com.example.administrator.japanhouse.presenter.OwnerPresenter;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;

/**
 * 百科详情
 * Created by   admin on 2018/4/17.
 */

public class OwnerDetailsActivity extends BaseActivity implements OwnerPresenter.OwnerCallBack{
    private OwnerPresenter presenter;
    private TextView tvTitle;
    private TextView tvDate;
    private TextView tvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_details);
        ButterKnife.bind(this);

        final int itemId = getIntent().getIntExtra("itemId", 0);

        tvTitle = (TextView) findViewById(R.id.act_owner_details_title);
        tvDate = (TextView) findViewById(R.id.act_owner_details_date);
        tvContent = (TextView) findViewById(R.id.act_owner_details_content);

        findViewById(R.id.act_owner_details_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        presenter = new OwnerPresenter(this, this);
        presenter.getOwnerDetails(itemId);
    }

    public static void invoke(Context context, int itemId) {
        Intent intent = new Intent(context, OwnerDetailsActivity.class);
        intent.putExtra("itemId", itemId);
        context.startActivity(intent);
    }

    @Override
    public void getOwnerList(Response<OwnerListBean> response) {

    }

    @Override
    public void getOwnerDetails(Response<OwnerDetailsBean> response) {
        if (response != null && response.body() != null && response.body().getDatas() != null) {
            tvTitle.setText(MyApplication.isJapanese() ? response.body().getDatas().getTitleJpn() : response.body().getDatas().getTitleCn());
            tvContent.setText(Html.fromHtml(MyApplication.isJapanese() ? response.body().getDatas().getContentJpn() : response.body().getDatas().getContentCn()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            String format = sdf.format(new Date(response.body().getDatas().getCreateTime()));
            tvDate.setText(format);
        }
    }

    @Override
    public void ownerListNetwork() {

    }
}
