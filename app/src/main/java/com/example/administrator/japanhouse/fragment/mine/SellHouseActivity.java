package com.example.administrator.japanhouse.fragment.mine;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.fragment.mine.fragment.Rent_house_Fragment;
import com.example.administrator.japanhouse.fragment.mine.fragment.Sell_house_Fragment;

public class SellHouseActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back_img;
    private TextView text_sell;
    private View xian1;
    private RelativeLayout liner1;
    private TextView text_rent;
    private View xian2;
    private RelativeLayout liner2;
    private FrameLayout frame;
    private Fragment currfit;
    private Sell_house_Fragment sell_house_fragment;
    private Rent_house_Fragment rent_house_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_house);
        initView();
    }

    private void initView() {
        back_img = (ImageView) findViewById(R.id.back_img);
        text_sell = (TextView) findViewById(R.id.text_sell);
        xian1 = (View) findViewById(R.id.xian1);
        liner1 = (RelativeLayout) findViewById(R.id.liner1);
        text_rent = (TextView) findViewById(R.id.text_rent);
        xian2 = (View) findViewById(R.id.xian2);
        liner2 = (RelativeLayout) findViewById(R.id.liner2);
        frame = (FrameLayout) findViewById(R.id.frame);
        //点击事件
        liner2.setOnClickListener(this);
        liner1.setOnClickListener(this);
        back_img.setOnClickListener(this);
        if(sell_house_fragment==null){
            sell_house_fragment = new Sell_house_Fragment();
        }
        AddFragment(sell_house_fragment);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
             case R.id.liner1:
                 xian1.setVisibility(View.VISIBLE);
                 xian2.setVisibility(View.INVISIBLE);
                 text_sell.setTextColor(getResources().getColor(R.color.colorAccent));
                 text_rent.setTextColor(getResources().getColor(R.color.black));
                 if(sell_house_fragment==null){
                     sell_house_fragment = new Sell_house_Fragment();
                 }
                 AddFragment(sell_house_fragment);
                 break;
            case R.id.liner2:
                xian1.setVisibility(View.INVISIBLE);
                xian2.setVisibility(View.VISIBLE);
                text_rent.setTextColor(getResources().getColor(R.color.colorAccent));
                text_sell.setTextColor(getResources().getColor(R.color.black));
                if(rent_house_fragment==null){
                    rent_house_fragment = new Rent_house_Fragment();
                }
                AddFragment(rent_house_fragment);

                break;

        }
    }

    /*
    * 动态添加fragment方法
    * */
    public void AddFragment(Fragment f){

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if(currfit !=null){
            fragmentTransaction.hide(currfit);

        }
        if(!f.isAdded()){
            fragmentTransaction.add(R.id.frame,f);
        }
        fragmentTransaction.show(f);
        fragmentTransaction.commit();
        currfit =f;


    }

}
