package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.fragment.home.ui.fragment.Buyhouse_Fragment;
import com.example.administrator.japanhouse.fragment.home.ui.fragment.Sellhouse_Fragment;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_beak;
    private LinearLayout search;
    private LinearLayout liner;
    private TextView text_buyfang;
    private RelativeLayout liner1;
    private TextView text_sellfang;
    private RelativeLayout liner2;
    private LinearLayout liners;
    private FrameLayout fl;
    private TextView text_tiwen;
    private RelativeLayout liner3;
    private TextView text_huida;
    private RelativeLayout liner4;
    private LinearLayout liner5;
    private Fragment currfit;
    private Buyhouse_Fragment buyhouse_fragment;
    private Sellhouse_Fragment sellhouse_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initView();
    }

    private void initView() {
        img_beak = (ImageView) findViewById(R.id.img_beak);
        search = (LinearLayout) findViewById(R.id.search);
        text_buyfang = (TextView) findViewById(R.id.text_buyfang);
        liner1 = (RelativeLayout) findViewById(R.id.liner1);
        text_sellfang = (TextView) findViewById(R.id.text_sellfang);
        liner2 = (RelativeLayout) findViewById(R.id.liner2);
        liners = (LinearLayout) findViewById(R.id.liners);
        fl = (FrameLayout) findViewById(R.id.fl);
        text_tiwen = (TextView) findViewById(R.id.text_tiwen);
        liner3 = (RelativeLayout) findViewById(R.id.liner3);
        text_huida = (TextView) findViewById(R.id.text_huida);
        liner4 = (RelativeLayout) findViewById(R.id.liner4);
        liner5 = (LinearLayout) findViewById(R.id.liner5);

        //事件
        img_beak.setOnClickListener(this);
        search.setOnClickListener(this);
        liner1.setOnClickListener(this);
        liner2.setOnClickListener(this);
        liner3.setOnClickListener(this);
        liner4.setOnClickListener(this);
        //默认显示界面
        if(buyhouse_fragment==null){
            buyhouse_fragment = new Buyhouse_Fragment();
        }
        AddFragment(buyhouse_fragment);

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
          switch (view.getId()){
              case R.id.img_beak:
                  finish();
                  break;
              case R.id.search:
                  //跳转搜索页面
                  Intent intent1 = new Intent(QuestionActivity.this, SearchActivity.class);
                  startActivity(intent1);
                  break;
              case R.id.liner1:
                  text_buyfang.setTextColor(getColor(R.color.colorAccent));
                  text_sellfang.setTextColor(getColor(R.color.black));
                     if(buyhouse_fragment==null){
                         buyhouse_fragment = new Buyhouse_Fragment();
                     }
                     AddFragment(buyhouse_fragment);
                  //转换页面
                  break;
              case R.id.liner2:
                  text_sellfang.setTextColor(getColor(R.color.colorAccent));
                  text_buyfang.setTextColor(getColor(R.color.black));
                  if(sellhouse_fragment==null){
                      sellhouse_fragment = new Sellhouse_Fragment();
                  }
                  AddFragment(sellhouse_fragment);
                  //转换页面
                  break;
              case R.id.liner3:
                  Intent intent = new Intent(QuestionActivity.this, QuizActivity.class);
                  startActivity(intent);
                  break;
              case R.id.liner4:
                  //转换页面
                  Intent wenda = new Intent(QuestionActivity.this, WendaItemActivity.class);
                  startActivity(wenda);
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
