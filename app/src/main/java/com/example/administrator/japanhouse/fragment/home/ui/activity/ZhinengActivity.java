package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.example.administrator.japanhouse.utils.TUtils;
import com.example.administrator.japanhouse.view.FluidLayout;

import java.util.ArrayList;
import java.util.List;

public class ZhinengActivity extends BaseActivity implements View.OnClickListener {

    private ImageView img_beak;
    private ImageView xinxi;

    private FluidLayout fluidLayout;
    private TextView more;
    private TextView tvleft;
    private LinearLayout flowLineLayout;
    private int windowWidth;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhineng);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        float density = dm.density;
        windowWidth = (int) (width/density);

        tvleft = (TextView) findViewById(R.id.act_zn_cainixiagnwen);
        linearLayout = (LinearLayout) findViewById(R.id.act_zn_liner);

        initView();
    }

    private void initView() {
        final List<String> list = new ArrayList<>();
        list.add(getString(R.string.zbmf));
        list.add(getString(R.string.daikuan));
        list.add(getString(R.string.baike));
        list.add(getString(R.string.zbmf));
        list.add(getString(R.string.daikuan));
        list.add(getString(R.string.baike));
        list.add(getString(R.string.zbmf));
        list.add(getString(R.string.daikuan));
        list.add(getString(R.string.baike));
        list.add(getString(R.string.zbmf));
        list.add(getString(R.string.daikuan));
        list.add(getString(R.string.baike));

        img_beak = (ImageView) findViewById(R.id.img_beak);
        xinxi = (ImageView) findViewById(R.id.xinxi);
        fluidLayout = (FluidLayout) findViewById(R.id.act_zn_FluidLayout);
        more = (TextView) findViewById(R.id.act_zn_more);
        flowLineLayout = (LinearLayout) findViewById(R.id.act_zn_lineFlow);

        init(list);

        img_beak.setOnClickListener(this);
        xinxi.setOnClickListener(this);

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                more.setVisibility(View.GONE);
                flowLineLayout.setVisibility(View.GONE);
                fluidLayout.setVisibility(View.VISIBLE);
                initHot(list);
            }
        });
        findViewById(R.id.act_zn_relative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                more.setVisibility(View.VISIBLE);
                flowLineLayout.setVisibility(View.VISIBLE);
                fluidLayout.setVisibility(View.GONE);
                init(list);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_beak:
                finish();
                break;
            case R.id.xinxi:
                finish();
                removeAllActivitys();
                MyUtils.startMain(this);
                break;
        }
    }

    private void initHot(final List<String> hotNameList) {
        fluidLayout.removeAllViews();
        for (int i = 0; i < hotNameList.size(); i++) {
            final TextView tv = (TextView) View.inflate(mContext, R.layout.view_zn_item, null);
            tv.setText(hotNameList.get(i));
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 12, 9);
            fluidLayout.addView(tv, params);
            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TUtils.showShort(mContext, "点击了---" + hotNameList.get(finalI));
                }
            });
        }
    }

    private void init(final List<String> hotNameList) {
        int width = 0;
        flowLineLayout.removeAllViews();
        LinearLayout.LayoutParams tvLeft = (LinearLayout.LayoutParams) tvleft.getLayoutParams();
        LinearLayout.LayoutParams tvRight = (LinearLayout.LayoutParams) more.getLayoutParams();

        int mWidth = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int mHeight = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        for (int i = 0; i < hotNameList.size(); i++) {
            final TextView tv = (TextView) View.inflate(mContext, R.layout.view_zn_item, null);
            tv.setText(hotNameList.get(i));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 12, 9);
            tv.setLayoutParams(params);
            tv.measure(mWidth, mHeight);

            if (width + tv.getMeasuredWidth() > windowWidth - tvLeft.width - tvRight.width - tvLeft.leftMargin - tvRight.rightMargin) {
                tv.setVisibility(View.GONE);
                break;
            }
            width += tv.getMeasuredWidth();
            flowLineLayout.addView(tv);

            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TUtils.showShort(mContext, "点击了---" + hotNameList.get(finalI));
                }
            });

        }
    }
}
