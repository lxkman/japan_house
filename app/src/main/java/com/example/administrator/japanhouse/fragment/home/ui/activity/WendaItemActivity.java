package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.fragment.home.ui.fragment.Huida_Itme_Fragment;
import com.example.administrator.japanhouse.fragment.home.ui.fragment.Tiwen_Itme_Fragment;

public class WendaItemActivity extends BaseActivity implements View.OnClickListener {

    private ImageView img_beak;
    private TextView me_tiwen;
    private View wen_xian;
    private TextView me_huida;
    private View da_xian;
    private View xian;
    private FrameLayout fl;
    private Fragment currfit;
    private Tiwen_Itme_Fragment tiwen_itme_fragment;
    private Huida_Itme_Fragment huida_itme_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wenda);
        initView();
    }

    private void initView() {
        img_beak = (ImageView) findViewById(R.id.img_beak);
        me_tiwen = (TextView) findViewById(R.id.me_tiwen);
        wen_xian = (View) findViewById(R.id.wen_xian);
        me_huida = (TextView) findViewById(R.id.me_huida);
        da_xian = (View) findViewById(R.id.da_xian);
        xian = (View) findViewById(R.id.xian);
        fl = (FrameLayout) findViewById(R.id.fl);
        img_beak.setOnClickListener(this);
        me_tiwen.setOnClickListener(this);
        me_huida.setOnClickListener(this);
         //默认界面
        if(tiwen_itme_fragment==null){
            tiwen_itme_fragment = new Tiwen_Itme_Fragment();
        }
        AddFragment(tiwen_itme_fragment);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.img_beak:
                finish();
                break;
            case R.id.me_tiwen:
                wen_xian.setVisibility(View.VISIBLE);
                da_xian.setVisibility(View.INVISIBLE);
                //转换页面
                     if(tiwen_itme_fragment==null){
                         tiwen_itme_fragment = new Tiwen_Itme_Fragment();
                     }
                     AddFragment(tiwen_itme_fragment);
                break;
            case R.id.me_huida:
                wen_xian.setVisibility(View.INVISIBLE);
                da_xian.setVisibility(View.VISIBLE);
                //转换页面
                if(huida_itme_fragment==null){
                    huida_itme_fragment = new Huida_Itme_Fragment();
                }
                AddFragment(huida_itme_fragment);
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
            fragmentTransaction.add(R.id.fl,f);
        }
        fragmentTransaction.show(f);
        fragmentTransaction.commit();
        currfit =f;


    }
}
