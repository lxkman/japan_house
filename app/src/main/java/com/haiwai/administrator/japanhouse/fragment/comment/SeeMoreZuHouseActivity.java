package com.haiwai.administrator.japanhouse.fragment.comment;

import android.os.Bundle;
import android.widget.ImageView;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SeeMoreZuHouseActivity extends BaseActivity {

    @BindView(R.id.back_img)
    ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_more_zu_house);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back_img)
    public void onClick() {
        finish();
    }
}
