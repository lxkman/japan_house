package com.example.administrator.japanhouse.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 出租/出售
 * Created by   admin on 2018/4/17.
 */

public class RentalActivity extends BaseActivity {

    @BindView(R.id.act_rental_back)
    ImageView back;
    @BindView(R.id.act_rental_rent)
    TextView tabRent;
    @BindView(R.id.act_rental_rentView)
    View tabRentIndicator;
    @BindView(R.id.act_rental_sell)
    TextView tabSell;
    @BindView(R.id.act_rental_sellView)
    View tabSellIndicator;
    @BindView(R.id.act_rental_call)
    EditText etCall;
    @BindView(R.id.act_rental_contact)
    EditText etContact;
    @BindView(R.id.act_rental_location)
    EditText etLocation;
    @BindView(R.id.act_rental_distance)
    EditText etDistance;
    @BindView(R.id.act_rental_floor)
    EditText etFloor;
    @BindView(R.id.act_rental_area)
    EditText etArea;
    @BindView(R.id.act_rental_areaSpan)
    TextView tvAreaSpan;
    @BindView(R.id.act_rental_pattern)
    EditText etPattern;
    @BindView(R.id.act_rental_check)
    RadioButton rbCheck;
    @BindView(R.id.act_rental_checked)
    RadioButton rbChecked;
    @BindView(R.id.act_rental_toward)
    EditText etToward;
    @BindView(R.id.act_rental_equipment)
    EditText etEquipment;
    @BindView(R.id.act_rental_picRecyclerView)
    RecyclerView picRecyclerView;
    @BindView(R.id.act_rental_videoRecyclerView)
    RecyclerView videoRecyclerView;
    @BindView(R.id.act_rental_entrust)
    TextView tvEntrust;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental);
        ButterKnife.bind(this);

        //显示平方米
        SpannableString m2 = new SpannableString("m2");
        m2.setSpan(new RelativeSizeSpan(0.5f), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        m2.setSpan(new SuperscriptSpan(), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvAreaSpan.setText(m2);


    }

    @OnClick({R.id.act_rental_back, R.id.act_rental_rent_lt, R.id.act_rental_sell_lt, R.id.act_rental_check, R.id.act_rental_checked, R.id.act_rental_entrust})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_rental_back:
                finish();
                break;
            case R.id.act_rental_rent_lt:
                tabRent.setTextColor(Color.parseColor("#c7977f"));
                tabRentIndicator.setBackgroundColor(Color.parseColor("#c7977f"));
                tabSell.setTextColor(Color.parseColor("#444444"));
                tabSellIndicator.setBackgroundColor(Color.WHITE);
                break;
            case R.id.act_rental_sell_lt:
                tabSell.setTextColor(Color.parseColor("#c7977f"));
                tabSellIndicator.setBackgroundColor(Color.parseColor("#c7977f"));
                tabRent.setTextColor(Color.parseColor("#444444"));
                tabRentIndicator.setBackgroundColor(Color.WHITE);
                break;
            case R.id.act_rental_check:

                break;
            case R.id.act_rental_checked:

                break;
            case R.id.act_rental_entrust:

                break;
        }
    }

    public static void invoke(Context context) {
        Intent intent = new Intent(context, RentalActivity.class);
        context.startActivity(intent);
    }
}
