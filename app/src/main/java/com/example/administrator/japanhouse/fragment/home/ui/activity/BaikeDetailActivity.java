package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;

public class BaikeDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_beak;
    private View xian;
    private TextView title;
    private TextView time;
    private TextView sfm;
    private TextView neirong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baike_detail);
        initView();
    }

    private void initView() {
        img_beak = (ImageView) findViewById(R.id.img_beak);
        title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time);
        sfm = (TextView) findViewById(R.id.sfm);
        neirong = (TextView) findViewById(R.id.neirong);
        img_beak.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.img_beak:
                finish();
                break;
        }
    }
}
