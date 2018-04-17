package com.example.administrator.japanhouse.fragment.mine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.bean.FragEventBug;
import com.example.administrator.japanhouse.fragment.mine.fragment.Calcul_Detil_Fragment;

import org.greenrobot.eventbus.EventBus;

public class Calculator_DetilsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back_img;
    private TextView tv_register;
    private TextView text_benxi;
    private View xian1;
    private RelativeLayout liner1;
    private TextView text_benjin;
    private View xian2;
    private RelativeLayout liner2;
    private Fragment currfit;
    private Calcul_Detil_Fragment calcul_detil_fragment;
    private FrameLayout fl;
    private String shoufu;
    private String daikuan;
    private String lixi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator__detils);
        initView();
    }

    private void initView() {
        back_img = (ImageView) findViewById(R.id.back_img);
        xian1 = (View) findViewById(R.id.xian1);
        liner1 = (RelativeLayout) findViewById(R.id.liner1);
        text_benjin = (TextView) findViewById(R.id.text_benjin);
        text_benxi = (TextView) findViewById(R.id.text_benxi);
        xian2 = (View) findViewById(R.id.xian2);
        liner2 = (RelativeLayout) findViewById(R.id.liner2);
        fl = (FrameLayout) findViewById(R.id.fl);

        Intent intent = getIntent();
        shoufu = intent.getStringExtra("shoufu");
        daikuan = intent.getStringExtra("daikuan");
        lixi = intent.getStringExtra("lixi");
        if (calcul_detil_fragment == null) {
            calcul_detil_fragment = new Calcul_Detil_Fragment();
        }
        AddFragment(calcul_detil_fragment);
        EventBus.getDefault().postSticky(new FragEventBug(shoufu,daikuan,lixi));
        liner2.setOnClickListener(this);
        liner1.setOnClickListener(this);
        back_img.setOnClickListener(this);
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
            fragmentTransaction.add(R.id.fl, f);
        }
        fragmentTransaction.show(f);
        fragmentTransaction.commit();
        currfit = f;


    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;

            case R.id.liner1:
                text_benxi.setTextColor(R.color.colorAccent);
                text_benjin.setTextColor(R.color.text_black);
                xian1.setVisibility(View.VISIBLE);
                xian2.setVisibility(View.INVISIBLE);
                if (calcul_detil_fragment == null) {
                    calcul_detil_fragment = new Calcul_Detil_Fragment();
                }
                AddFragment(calcul_detil_fragment);
                EventBus.getDefault().postSticky(new FragEventBug(shoufu,daikuan,lixi));
                break;
            case R.id.liner2:
                text_benjin.setTextColor(R.color.colorAccent);
                text_benxi.setTextColor(R.color.text_black);
                xian2.setVisibility(View.VISIBLE);
                xian1.setVisibility(View.INVISIBLE);
                if (calcul_detil_fragment == null) {
                    calcul_detil_fragment = new Calcul_Detil_Fragment();
                }
                AddFragment(calcul_detil_fragment);
                EventBus.getDefault().postSticky(new FragEventBug(shoufu,daikuan,lixi));
                break;


        }
    }
}
