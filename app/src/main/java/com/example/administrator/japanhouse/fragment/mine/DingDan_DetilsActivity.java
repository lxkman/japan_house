package com.example.administrator.japanhouse.fragment.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.DingDan_Adapter;

public class DingDan_DetilsActivity extends AppCompatActivity {

    private ImageView back_img;
    private TextView dd_price;
    private TextView dd_time;
    private TextView dd_state;
    private TextView dd_name;
    private TextView dd_address;
    private TextView dd_geju;
    private TextView dd_smll;
    private RecyclerView dd_recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan__detils);
        initView();
    }

    private void initView() {
        back_img = (ImageView) findViewById(R.id.back_img);
        dd_price = (TextView) findViewById(R.id.dd_price);
        dd_time = (TextView) findViewById(R.id.dd_time);
        dd_state = (TextView) findViewById(R.id.dd_state);
        dd_name = (TextView) findViewById(R.id.dd_name);
        dd_address = (TextView) findViewById(R.id.dd_address);
        dd_geju = (TextView) findViewById(R.id.dd_geju);
        dd_smll = (TextView) findViewById(R.id.dd_smll);
        dd_recycler = (RecyclerView) findViewById(R.id.dd_recycler);

        dd_recycler.setLayoutManager(new GridLayoutManager(this,3));
        dd_recycler.setNestedScrollingEnabled(false);
        DingDan_Adapter dingDan_adapter = new DingDan_Adapter(this);
        dd_recycler.setAdapter(dingDan_adapter);


    }
}
