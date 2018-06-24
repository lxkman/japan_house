package com.example.administrator.japanhouse.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.adapter.FreeApartmentAdapter;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.login.LoginActivity;
import com.example.administrator.japanhouse.model.FreeApartmentBean;
import com.example.administrator.japanhouse.model.NoDataBean;
import com.example.administrator.japanhouse.presenter.FreeApartmentPresenter;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.TUtils;
import com.example.administrator.japanhouse.view.BaseDialog;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 免费看房
 * Created by   admin on 2018/4/17.
 */

public class  FreeApartmentActivity extends BaseActivity implements FreeApartmentAdapter.onClickListener, FreeApartmentPresenter.FreeApartmentCallBack {

    @BindView(R.id.act_apartment_back)
    ImageView actApartmentBack;
    @BindView(R.id.act_apartment_msg)
    ImageView actApartmentMsg;
    @BindView(R.id.act_apartment_recyclerView)
    RecyclerView mRecyclerView;

    private SpringView springView;
    private FreeApartmentAdapter freeApartmentAdapter;
    private FreeApartmentPresenter presenter;
    private int pageNo = 1;
    private List<FreeApartmentBean.DatasBean> datas = new ArrayList<>();

    private TextView state;
    private boolean isRefresh = true;

    private boolean refresh = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_apartment);
        ButterKnife.bind(this);

        state = (TextView) findViewById(R.id.no_more_data);

        springView = (SpringView) findViewById(R.id.act_apartment_springView);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        freeApartmentAdapter = new FreeApartmentAdapter(this, datas);
        freeApartmentAdapter.setOnClickListener(this);
        mRecyclerView.setAdapter(freeApartmentAdapter);

        presenter = new FreeApartmentPresenter(this, this);
        presenter.getFreeApartmentList(pageNo);

        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        datas.clear();
                        pageNo = 1;
                        presenter.getFreeApartmentList(pageNo);
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = false;
                        pageNo++;
                        presenter.getFreeApartmentList(pageNo);
                    }
                }, 0);
                springView.onFinishFreshAndLoad();
            }
        });
        springView.setFooter(new DefaultFooter(this));
        springView.setHeader(new DefaultHeader(this));
    }


    @OnClick({R.id.act_apartment_back, R.id.act_apartment_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_apartment_back:
                finish();
                break;

            case R.id.act_apartment_msg:
                EventBus.getDefault().post(new EventBean(Constants.EVENT_CHAT));
                finish();
                break;
        }
    }

    private void showSignupDialog(final int actionId) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_checkroom)
                .setPaddingdp(20, 0, 20, 0)
                .setGravity(Gravity.CENTER)
                .setAnimation(R.style.bottom_tab_style)
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                .isOnTouchCanceled(true)
                .builder();

        final EditText etName = dialog.getView(R.id.et_singup_name);
        final EditText etPhone = dialog.getView(R.id.et_singup_phone);

        dialog.getView(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getView(R.id.tv_singup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etName.getText().toString()) && !TextUtils.isEmpty(etPhone.getText().toString())) {
                    presenter.getSignUp(MyApplication.getUserId(FreeApartmentActivity.this), actionId, etPhone.getText().toString(), etName.getText().toString());
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    private void ShowCallDialog(final String tel) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.call_layout)
                .setPaddingdp(0, 10, 0, 10)
                .setGravity(Gravity.CENTER)
                .setAnimation(R.style.bottom_tab_style)
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                .isOnTouchCanceled(false)
                .builder();
        dialog.show();
        TextView text_sure = dialog.getView(R.id.text_sure);
        final TextView tv_content = dialog.getView(R.id.tv_content);
        tv_content.setText(tel);
        TextView text_pause = dialog.getView(R.id.text_pause);

        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent dialIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + tel));
                startActivity(dialIntent);
            }
        });

        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onSignUpClickListener(int actionId) {
        showSignupDialog(actionId);
    }

    @Override
    public void onCallClickListener(String tel) {
        ShowCallDialog(tel);
    }

    @Override
    public void onItemDeteleClickListener(int position) {
        datas.remove(position);

        if (datas.size() > 0) {
            long days = (datas.get(0).getEndTime() - datas.get(0).getCurrentTime()) / (1000 * 60 * 60 * 24);
            long hours = ((datas.get(0).getEndTime() - datas.get(0).getCurrentTime()) - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = ((datas.get(0).getEndTime() - datas.get(0).getCurrentTime()) - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long second = ((datas.get(0).getEndTime() - datas.get(0).getCurrentTime()) - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * 1000 * 60) ;
            cancelTimer();
            startTimer(second + 1000L);
        }
    }

    @Override
    public void onItemClickListener(FreeApartmentBean.DatasBean datasBean) {
        Intent intent = new Intent(this, FreeApartmentDetailsActivity.class);
        intent.putExtra("datas", datasBean);
        startActivity(intent);
    }

    public static void invoke(Context context) {
        Intent intent = new Intent(context, FreeApartmentActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void getFreeApartmentList(Response<FreeApartmentBean> response) {
        if (TextUtils.equals(response.body().getCode(), "201")) {
            startActivity(new Intent(this, LoginActivity.class));
            MyApplication.logOut();
            return;
        }

        if (isRefresh && refresh) {
            TUtils.showFail(this, getString(R.string.refresh_success));
        }

        refresh = true;

        state.setText(getString(R.string.no_more_data));

        if (response != null && response.body() != null && response.body().getDatas() != null) {
            if (pageNo == 1) {
                if (response.body().getDatas().size() > 0) {
                    state.setVisibility(View.GONE);
                } else {
                    state.setVisibility(View.VISIBLE);
                }
            }

            if (response.body().getDatas().size() > 0) {
                datas.addAll(response.body().getDatas());
            } else {
                pageNo --;
                if (!isRefresh) {
                    TUtils.showFail(this, getString(R.string.refresh_no_data));
                }
            }

            if (datas.size() > 0) {
                long days = (datas.get(0).getEndTime() - datas.get(0).getCurrentTime()) / (1000 * 60 * 60 * 24);
                long hours = ((datas.get(0).getEndTime() - datas.get(0).getCurrentTime()) - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                long minutes = ((datas.get(0).getEndTime() - datas.get(0).getCurrentTime()) - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
                long second = ((datas.get(0).getEndTime() - datas.get(0).getCurrentTime()) - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * 1000 * 60) ;
                cancelTimer();
                startTimer(second + 1000L);
                freeApartmentAdapter.notifyDataSetChanged();
            } else {
                pageNo --;
            }
        }
    }

    @Override
    public void getSignUp(Response<NoDataBean> response) {
        if (TextUtils.equals(response.body().getCode(), "201")) {
            startActivity(new Intent(this, LoginActivity.class));
            MyApplication.logOut();
            return;
        }

        if (response != null && response.body() != null && !TextUtils.isEmpty(response.body().getCode())) {
            if (TextUtils.equals(response.body().getCode(), "200")) {
                Toast.makeText(this, "报名成功", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.equals(response.body().getCode(), "205")) {
                Toast.makeText(this, "该用户已报名该活动", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.equals(response.body().getCode(), "-1")) {
                Toast.makeText(this, "报名失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void freeApartmentNetwork() {
        TUtils.showFail(this, getString(R.string.refresh_fail));
        if (!MyApplication.isNetworkAvailable()) {
            state.setVisibility(View.VISIBLE);
            state.setText(getString(R.string.no_network));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }

    private MyCountDownTimer timer;
    private final long TIME = 61 * 1000L;
    private final long INTERVAL = 1000L;

    class MyCountDownTimer extends CountDownTimer {
        private long s;
        public MyCountDownTimer(long millisInFuture, long countDownInterval, long s) {
            super(millisInFuture, countDownInterval);
            this.s = s;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long time = millisUntilFinished / 1000L;
            for (int i = 0; i < datas.size(); i++) {
                datas.get(i).setCurrentTime(datas.get(i).getCurrentTime() + 1000L);
            }
            freeApartmentAdapter.notifyDataSetChanged();
            if (time <= 1) {
                cancelTimer();
                startTimer(61999);
            }
        }

        @Override
        public void onFinish() {
            cancelTimer();
        }
    }

    /**
     * 开始倒计时
     */
    private void startTimer(long sec) {
        if (timer == null) {
            timer = new MyCountDownTimer(sec, INTERVAL, sec);
        }
        timer.start();
    }

    /**
     * 取消倒计时
     */
    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}


