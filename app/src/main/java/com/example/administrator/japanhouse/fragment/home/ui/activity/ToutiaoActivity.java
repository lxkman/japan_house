package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.TouAdapter;

public class ToutiaoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_beak;
    private ImageView xinxi;
    private RecyclerView toutiao_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toutiao);
        initView();
    }

    private void initView() {
        img_beak = (ImageView) findViewById(R.id.img_beak);
        xinxi = (ImageView) findViewById(R.id.xinxi);
        toutiao_recycler = (RecyclerView) findViewById(R.id.toutiao_recycler);

        img_beak.setOnClickListener(this);
        xinxi.setOnClickListener(this);

        //加载适配器
        toutiao_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        TouAdapter touAdapter = new TouAdapter(this);
        toutiao_recycler.setAdapter(touAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_beak:
                finish();
                break;
            case R.id.xinxi:
                Toast.makeText(ToutiaoActivity.this,"跳转",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
