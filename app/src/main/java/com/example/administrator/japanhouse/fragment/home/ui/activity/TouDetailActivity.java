package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.ToutiaoAdapter;

public class TouDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_beak;
    private TextView title;
    private TextView time;
    private TextView sfm;
    private TextView neirong;
    private RecyclerView detail_recy;
    private TextView send;
    private ImageView biaoqing;
    private EditText ed_pinglun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tou_detail);
        initView();


    }

    private void initView() {
        img_beak = (ImageView) findViewById(R.id.img_beak);
        title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time);
        sfm = (TextView) findViewById(R.id.sfm);
        neirong = (TextView) findViewById(R.id.neirong);
        detail_recy = (RecyclerView) findViewById(R.id.detail_recy);
        send = (TextView) findViewById(R.id.send);
        biaoqing = (ImageView) findViewById(R.id.biaoqing);
        ed_pinglun = (EditText) findViewById(R.id.ed_pinglun);
        img_beak.setOnClickListener(this);
        send.setOnClickListener(this);
       //加载适配器
        detail_recy.setLayoutManager(new LinearLayoutManager(this));
        detail_recy.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        detail_recy.setNestedScrollingEnabled(false);
        //detail_recy.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        ToutiaoAdapter touAdapter = new ToutiaoAdapter(this);
        detail_recy.setAdapter(touAdapter);

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.img_beak:
                finish();
                break;
            case R.id.send:
                //获取输入框信息
                String pinglun = ed_pinglun.getText().toString();
                Toast.makeText(this,pinglun,Toast.LENGTH_SHORT).show();
                break;
            case R.id.biaoqing:
                Toast.makeText(this,"表情",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
