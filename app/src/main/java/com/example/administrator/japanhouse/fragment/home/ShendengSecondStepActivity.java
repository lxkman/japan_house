package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.OneCheckBean;
import com.example.administrator.japanhouse.utils.SpUtils;
import com.example.administrator.japanhouse.utils.TUtils;
import com.example.administrator.japanhouse.view.FluidLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShendengSecondStepActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.want_fluidlayout)
    FluidLayout wantFluidlayout;
    @BindView(R.id.notwant_fluidlayout)
    FluidLayout notwantFluidlayout;
    @BindView(R.id.finish_tv)
    TextView finishTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shendeng_second_step);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        wantFluidlayout.removeAllViews();
        final List<OneCheckBean> wantList = new ArrayList<>();
        wantList.add(new OneCheckBean(false,"整租"));
        wantList.add(new OneCheckBean(false,"合租"));
        wantList.add(new OneCheckBean(false,"朝南"));
        wantList.add(new OneCheckBean(false,"精装修"));
        wantList.add(new OneCheckBean(false,"阳台"));
        wantList.add(new OneCheckBean(false,"有车位"));
        wantList.add(new OneCheckBean(false,"独立卫生间"));
        wantList.add(new OneCheckBean(false,"经纪人房源"));
        wantList.add(new OneCheckBean(false,"个人房源"));

        for (int i = 0; i < wantList.size(); i++) {
            final TextView tv = (TextView) View.inflate(mContext, R.layout.item_shendeng_second, null);
            tv.setText(wantList.get(i).getName());
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(12, 12, 12, 12);
            wantFluidlayout.addView(tv, params);
            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!wantList.get(finalI).isChecked()){
                        tv.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_sec_true));
                        tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                        wantList.get(finalI).setChecked(true);
                    }else {
                        tv.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_sec_false));
                        tv.setTextColor(getResources().getColor(R.color.text_gray));
                        wantList.get(finalI).setChecked(false);
                    }
                }
            });
        }

        notwantFluidlayout.removeAllViews();
        final List<OneCheckBean> nowantList = new ArrayList<>();
        nowantList.add(new OneCheckBean(false,"地下室"));
        nowantList.add(new OneCheckBean(false,"底层"));
        nowantList.add(new OneCheckBean(false,"顶层"));
        nowantList.add(new OneCheckBean(false,"毛坯"));
        nowantList.add(new OneCheckBean(false,"朝北"));

        for (int i = 0; i < nowantList.size(); i++) {
            final TextView tv = (TextView) View.inflate(mContext, R.layout.item_shendeng_second, null);
            tv.setText(nowantList.get(i).getName());
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(12, 12, 12, 12);
            notwantFluidlayout.addView(tv, params);
            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!nowantList.get(finalI).isChecked()){
                        tv.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_sec_true));
                        tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                        nowantList.get(finalI).setChecked(true);
                    }else {
                        tv.setBackground(getResources().getDrawable(R.drawable.bg_shendeng_sec_false));
                        tv.setTextColor(getResources().getColor(R.color.text_gray));
                        nowantList.get(finalI).setChecked(false);
                    }
                }
            });
        }
    }

    @OnClick({R.id.title_back_iv, R.id.finish_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.finish_tv:
                SpUtils.putBoolean("shendeng",true);
                startActivity(new Intent(mContext,ShendengListActivity.class));
                finish();
                break;
        }
    }
}
