package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.im.ChatMsgAdapter;
import com.example.administrator.japanhouse.model.ZNHouseBean;
import com.example.administrator.japanhouse.presenter.ZNBuyHousePresenter;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.view.FluidLayout;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZhinengActivity extends BaseActivity implements View.OnClickListener, ZNBuyHousePresenter.BuyHouseCallBack, ChatMsgAdapter.OnBackgroundClickListener {

    private ImageView img_beak;
    private ImageView xinxi;

    private FluidLayout fluidLayout;
    private TextView more;
    private TextView tvleft;
    private LinearLayout flowLineLayout;
    private int windowWidth;
    private LinearLayout linearLayout;

    private ZNBuyHousePresenter presenter;

    private List<String> issueList = new ArrayList<>();
    private List<String> answerList = new ArrayList<>();

    private ListView listView;
    private ChatMsgAdapter msgAdapter;

    private List<String> adapterList = new ArrayList<>();
    private List<Boolean> booleenList = new ArrayList<>();
    private List<Boolean> timeList = new ArrayList<>();
    private List<String> tList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhineng);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        float density = dm.density;
        windowWidth = (int) (width / density);

        tvleft = (TextView) findViewById(R.id.act_zn_cainixiagnwen);
        linearLayout = (LinearLayout) findViewById(R.id.act_zn_liner);
        listView = (ListView) findViewById(R.id.act_zn_listView);

        long longTime = 0;
        CacheUtils.put(Constants.SYSTEM_TIME, longTime);

        initView();

    }

    private void initView() {

        img_beak = (ImageView) findViewById(R.id.img_beak);
        xinxi = (ImageView) findViewById(R.id.xinxi);
        fluidLayout = (FluidLayout) findViewById(R.id.act_zn_FluidLayout);
        more = (TextView) findViewById(R.id.act_zn_more);
        flowLineLayout = (LinearLayout) findViewById(R.id.act_zn_lineFlow);
        more.setVisibility(View.GONE);

        presenter = new ZNBuyHousePresenter(this, this);
        presenter.getZNBuyHouseList();
        init(issueList);

        msgAdapter = new ChatMsgAdapter(this, adapterList, this, booleenList, timeList, tList);
        listView.setAdapter(msgAdapter);

        img_beak.setOnClickListener(this);
        xinxi.setOnClickListener(this);

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                more.setVisibility(View.GONE);
                flowLineLayout.setVisibility(View.GONE);
                fluidLayout.setVisibility(View.VISIBLE);
                initHot(issueList);
            }
        });

        findViewById(R.id.act_zn_clickBg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowLineLayout.setVisibility(View.VISIBLE);
                fluidLayout.setVisibility(View.GONE);
                init(issueList);
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
                EventBus.getDefault().post(new EventBean(Constants.EVENT_CHAT));
                finish();
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
                    booleenList.add(true);
                    adapterList.add(hotNameList.get(finalI));
                    setTimeShow();
                    msgAdapter.notifyDataSetChanged();
                    answerIssue(finalI);
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

            width += tv.getMeasuredWidth();

            if (width > windowWidth - tvLeft.width - tvRight.width - tvLeft.leftMargin - tvRight.rightMargin) {
                more.setVisibility(View.VISIBLE);
                tv.setVisibility(View.GONE);
                break;
            }

            flowLineLayout.addView(tv);

            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    booleenList.add(true);
                    adapterList.add(hotNameList.get(finalI));
                    setTimeShow();
                    msgAdapter.notifyDataSetChanged();
                    answerIssue(finalI);
                }
            });

        }
    }

    private void answerIssue(final int position){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                booleenList.add(false);
                adapterList.add(answerList.get(position));
                msgAdapter.notifyDataSetChanged();
                setTimeShow();
            }
        }, 1000);
    }

    @Override
    public void getZNBuyHouseList(Response<ZNHouseBean> response) {
        if (response.body().getDatas() == null && response.body().getDatas().size() == 0)
            return;

        List<ZNHouseBean.DatasBean> data = response.body().getDatas();
        for (int i = 0; i < data.size(); i++) {
            issueList.add(data.get(i).getTheKeyword());
            answerList.add(data.get(i).getAnswer());
        }

        init(issueList);
    }

    @Override
    public void onClickListener() {
        flowLineLayout.setVisibility(View.VISIBLE);
        fluidLayout.setVisibility(View.GONE);
        init(issueList);
    }

    private void setTimeShow(){

        long laterTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String timeString = sdf.format(new Date(laterTime));
        long beforeTime = CacheUtils.get(Constants.SYSTEM_TIME);
        if (beforeTime == 0) {
            timeList.add(true);
        } else {
            if (laterTime - beforeTime > 3 * 60 * 1000) {
                timeList.add(true);
            } else {
                timeList.add(false);
            }
        }
        tList.add(timeString);
        CacheUtils.put(Constants.SYSTEM_TIME, laterTime);
    }
}
