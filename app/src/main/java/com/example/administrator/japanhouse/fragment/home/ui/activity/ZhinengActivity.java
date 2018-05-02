package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;

public class ZhinengActivity extends BaseActivity implements View.OnClickListener {

    private ImageView img_beak;
    private ImageView xinxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhineng);
        initView();
    }

    private void initView() {
        img_beak = (ImageView) findViewById(R.id.img_beak);
        xinxi = (ImageView) findViewById(R.id.xinxi);

        img_beak.setOnClickListener(this);
        xinxi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_beak:
                finish();
                break;
            case R.id.xinxi:
                Toast.makeText(ZhinengActivity.this,"跳转",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
