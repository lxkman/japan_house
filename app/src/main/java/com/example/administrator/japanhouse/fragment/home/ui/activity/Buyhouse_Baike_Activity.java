package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.BaikeAdapter;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.TouAdapter;

public class Buyhouse_Baike_Activity extends AppCompatActivity {

    private ImageView img_beak;
    private RecyclerView baike_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyhouse__baike_);
        initView();
    }

    private void initView() {
        img_beak = (ImageView) findViewById(R.id.img_beak);
        baike_recycler = (RecyclerView) findViewById(R.id.baike_recycler);
           img_beak.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   finish();
               }
           });

        //加载适配器
        baike_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        BaikeAdapter touAdapter = new BaikeAdapter(this);
        baike_recycler.setAdapter(touAdapter);
    }
}
