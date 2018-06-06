package com.example.administrator.japanhouse.fragment.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.activity.FreeApartmentActivity;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.model.SingUpBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingUpDetailsActivity extends BaseActivity {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.act_singUp_details_icon)
    ImageView icon;
    @BindView(R.id.act_singUp_details_title_ja)
    TextView titleJa;
    @BindView(R.id.act_singUp_details_time_ja)
    TextView timeJa;
    @BindView(R.id.act_singUp_details_num_ja)
    TextView numJa;
    @BindView(R.id.act_singUp_details_layout_ja)
    LinearLayout layoutJa;
    @BindView(R.id.act_singUp_details_title_zh)
    TextView titleZh;
    @BindView(R.id.act_singUp_details_time_zh)
    TextView timeZh;
    @BindView(R.id.act_singUp_details_num_zh)
    TextView numZh;
    @BindView(R.id.act_singUp_details_layout_zh)
    LinearLayout layoutZh;
    @BindView(R.id.act_singUp_details_details)
    TextView details;
    @BindView(R.id.act_singUp_details_range)
    TextView range;
    @BindView(R.id.act_singUp_details_phone)
    TextView phone;
    @BindView(R.id.act_singUp_details_houseBs)
    TextView houseBs;
    @BindView(R.id.act_singUp_details_date)
    TextView date;
    @BindView(R.id.act_singUp_details_address)
    TextView address;
    @BindView(R.id.act_singUp_details_managerIcon)
    ImageView managerIcon;
    @BindView(R.id.act_singUp_details_managerName)
    TextView managerName;
    @BindView(R.id.act_singUp_details_wChat)
    TextView wChat;
    @BindView(R.id.act_singUp_details_managerPhone)
    TextView managerPhone;

    private SingUpBean.DatasBean datasBean;
    private String charsStr = "0123456789";
    long second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up_details);
        ButterKnife.bind(this);

        datasBean = getIntent().getParcelableExtra("datas");

        initView();
    }

    private void initView() {
        if (MyApplication.isJapanese()) {
            layoutJa.setVisibility(View.VISIBLE);
            layoutZh.setVisibility(View.GONE);
        } else {
            layoutJa.setVisibility(View.GONE);
            layoutZh.setVisibility(View.VISIBLE);
        }

        setTime();

        Glide.with(this)
                .load(getList(datasBean.getImages()).get(0))
                .into(icon);

        titleJa.setText(datasBean.getActivityNameJpn());
        numJa.setText(String.format(getString(R.string.item_apartment_people), datasBean.getPeopleCount()));
        titleZh.setText(datasBean.getActivityNameCn());
        numZh.setText(String.format(getString(R.string.item_apartment_people), datasBean.getPeopleCount()));
        phone.setText(datasBean.getKfPhone());
        houseBs.setText(MyApplication.isJapanese() ? datasBean.getPriceJpn() + "/平" : datasBean.getPriceCn() + "/平");
//        date.setText();
//        address.setText();



        cancelTimer();
        startTimer(second + 1000L);
    }

    private void setTime(){
        long l = datasBean.getEndTime() - datasBean.getCurrentTime();

        long days = l / (1000 * 60 * 60 * 24);
        long hours = (l - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (l - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        second = (l - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * 1000 * 60);
        SpannableStringBuilder builder = new SpannableStringBuilder(
                String.format(getString(R.string.item_apartment_time), days, hours, minutes));

        char[] chars = builder.toString().toCharArray();
        for (int j = 0; j < chars.length; j++) {
            if (charsStr.contains(String.valueOf(chars[j]))) {
                builder.setSpan(new ForegroundColorSpan(Color.parseColor("#FE972A")), j, j + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        timeJa.setText(builder);
        timeZh.setText(builder);
    }

    @OnClick({R.id.back_img, R.id.act_singUp_details_wChat, R.id.act_singUp_details_managerPhone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            case R.id.act_singUp_details_wChat:
                break;
            case R.id.act_singUp_details_managerPhone:
                break;
        }
    }

    private List<String> getList(String pic) {
        String d[] = pic.split(",");
        List<String> picList = new ArrayList();

        for (int i = 0; i < d.length; i++) {
            picList.add(d[i]);
        }
        return picList;
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

            datasBean.setCurrentTime(datasBean.getCurrentTime() + 1000L);

            setTime();

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }
}
