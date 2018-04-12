package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.ToutiaoAdapter;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.WenDa_Detil_Adapter;

public class WenDa_Detils_Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_beak;
    private TextView title;
    private TextView time;
    private TextView neirong;
    private RecyclerView wenda_recy;
    private TextView text_tiwen;
    private RelativeLayout liner3;
    private TextView text_huida;
    private RelativeLayout liner4;
    private TextView ti_name;
    private TextView huida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wen_da__detils_);
        initView();
    }

    private void initView() {
        img_beak = (ImageView) findViewById(R.id.img_beak);
        title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time);
        neirong = (TextView) findViewById(R.id.neirong);
        wenda_recy = (RecyclerView) findViewById(R.id.wenda_recy);
        text_tiwen = (TextView) findViewById(R.id.text_tiwen);
        liner3 = (RelativeLayout) findViewById(R.id.liner3);
        text_huida = (TextView) findViewById(R.id.text_huida);
        liner4 = (RelativeLayout) findViewById(R.id.liner4);
        ti_name = (TextView) findViewById(R.id.ti_name);
        huida = (TextView) findViewById(R.id.huida);
        img_beak.setOnClickListener(this);
        liner3.setOnClickListener(this);
        liner4.setOnClickListener(this);
        //加载适配器
        wenda_recy.setLayoutManager(new LinearLayoutManager(this));
        wenda_recy.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        wenda_recy.setNestedScrollingEnabled(false);
        WenDa_Detil_Adapter toutiaoAdapter = new WenDa_Detil_Adapter(this);
        wenda_recy.setAdapter(toutiaoAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_beak:
                finish();
                break;
            case R.id.liner3:
                Intent intent = new Intent(WenDa_Detils_Activity.this, QuizActivity.class);
                startActivity(intent);
                break;
            case R.id.liner4:
                Intent intent1 = new Intent(WenDa_Detils_Activity.this, AnswerActivity.class);
                startActivity(intent1);
                break;

        }
    }
}
