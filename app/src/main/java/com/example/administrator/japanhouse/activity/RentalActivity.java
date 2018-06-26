package com.example.administrator.japanhouse.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.activity.adapter.GridViewAddImgesAdpter;
import com.example.administrator.japanhouse.activity.adapter.GridViewAddVideoAdapter;
import com.example.administrator.japanhouse.adapter.PicRentalAdapter;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.RentalDetailsBean;
import com.example.administrator.japanhouse.login.LoginActivity;
import com.example.administrator.japanhouse.model.FileBean;
import com.example.administrator.japanhouse.model.NoDataBean;
import com.example.administrator.japanhouse.presenter.RentalPresenter;
import com.example.administrator.japanhouse.presenter.UpFilePresenter;
import com.example.administrator.japanhouse.utils.BitmapUtil;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.CompressPhotoUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.SoftKeyboardTool;
import com.example.administrator.japanhouse.utils.TUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.model.Response;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalSelectionDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;

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
