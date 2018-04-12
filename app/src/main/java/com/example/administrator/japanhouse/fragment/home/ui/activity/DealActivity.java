package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;

public class DealActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_beak;
    private TextView deal_neirong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);
        initView();
    }

    private void initView() {
        img_beak = (ImageView) findViewById(R.id.img_beak);
        deal_neirong = (TextView) findViewById(R.id.deal_neirong);
        img_beak.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_beak:
                finish();
                break;
        }
    }
}
