package com.haiwai.administrator.japanhouse.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.utils.SoftKeyboardTool;

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

    private Fragment currfit;

    private RentalFragment rentalFragment;
    private SellFragment sellFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental);
        ButterKnife.bind(this);

        rentalFragment = new RentalFragment();
        AddFragment(rentalFragment);

    }

    /*
     * 动态添加fragment方法
     * */
    public void AddFragment(Fragment f) {

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (currfit != null) {
            fragmentTransaction.hide(currfit);

        }
        if (!f.isAdded()) {
            fragmentTransaction.add(R.id.act_rental_frame, f);
        }
        fragmentTransaction.show(f);
        fragmentTransaction.commit();
        currfit = f;
    }

    @OnClick({R.id.act_rental_back, R.id.act_rental_rent_lt, R.id.act_rental_sell_lt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_rental_back:
                SoftKeyboardTool.closeKeyboard2(this);
                finish();
                break;
            case R.id.act_rental_rent_lt:
                SoftKeyboardTool.closeKeyboard2(this);
                tabRent.setTextColor(Color.parseColor("#c7977f"));
                tabRentIndicator.setBackgroundColor(Color.parseColor("#c7977f"));
                tabSell.setTextColor(Color.parseColor("#444444"));
                tabSellIndicator.setBackgroundColor(Color.WHITE);
                if (rentalFragment == null) {
                    rentalFragment = new RentalFragment();
                }
                AddFragment(rentalFragment);
                break;
            case R.id.act_rental_sell_lt:
                SoftKeyboardTool.closeKeyboard2(this);
                tabSell.setTextColor(Color.parseColor("#c7977f"));
                tabSellIndicator.setBackgroundColor(Color.parseColor("#c7977f"));
                tabRent.setTextColor(Color.parseColor("#444444"));
                tabRentIndicator.setBackgroundColor(Color.WHITE);
                if (sellFragment == null) {
                    sellFragment = new SellFragment();
                }
                AddFragment(sellFragment);
                break;
        }
    }


}
