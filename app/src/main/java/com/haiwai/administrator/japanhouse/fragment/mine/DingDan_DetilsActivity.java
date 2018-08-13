package com.haiwai.administrator.japanhouse.fragment.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.fragment.home.ui.adapter.DingDan_Adapter;
import com.haiwai.administrator.japanhouse.model.OrderBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DingDan_DetilsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back_img;
    private TextView dd_price;
    private TextView dd_time;
    private TextView dd_state;
    private TextView dd_name;
    private TextView dd_address;
    private TextView dd_geju;
    private TextView dd_smll;
    private RecyclerView dd_recycler;

    private OrderBean.DatasBean datasBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan__detils);

        datasBean = getIntent().getParcelableExtra("datas");

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
        back_img.setOnClickListener(this);
        dd_recycler.setLayoutManager(new GridLayoutManager(this, 3));
        dd_recycler.setNestedScrollingEnabled(false);
        DingDan_Adapter dingDan_adapter = new DingDan_Adapter(this, getList(datasBean.getImgUrl()));
        dd_recycler.setAdapter(dingDan_adapter);

        dd_price.setText(MyApplication.isJapanese() ? datasBean.getTransactionPriceJpn() : datasBean.getTransactionPriceCn());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        dd_time.setText(sdf.format(new Date(datasBean.getCreateTime())));

        // 0 新房 1租房 2二手房 3土地 4商业地产  5海外房源 6中国房源 7别墅
        if (TextUtils.equals(datasBean.getHouseType(), "0")) {
            dd_state.setText(getString(R.string.new_house));
        } else if (TextUtils.equals(datasBean.getHouseType(), "1")) {
            dd_state.setText(getString(R.string.zu_house));
        } else if (TextUtils.equals(datasBean.getHouseType(), "2")) {
            dd_state.setText(getString(R.string.old_house));
        } else if (TextUtils.equals(datasBean.getHouseType(), "3")) {
            dd_state.setText(getString(R.string.tudi));
        } else if (TextUtils.equals(datasBean.getHouseType(), "4")) {
            dd_state.setText(getString(R.string.shangyedichan));
        } else if (TextUtils.equals(datasBean.getHouseType(), "5")) {
            dd_state.setText(getString(R.string.haiwaifangyuan));
        } else if (TextUtils.equals(datasBean.getHouseType(), "6")) {
            dd_state.setText(getString(R.string.zhongguofangyuan));
        } else if (TextUtils.equals(datasBean.getHouseType(), "7")) {
            dd_state.setText(getString(R.string.bieshu));
        }

        dd_name.setText(MyApplication.isJapanese() ? datasBean.getTitleJpn() : datasBean.getTitleCn());
        dd_address.setText(MyApplication.isJapanese() ? datasBean.getAddressJpn() : datasBean.getAddressCn());
        dd_geju.setText(MyApplication.isJapanese() ? datasBean.getDoorTypeJpn() : datasBean.getDoorTypeCn());
        dd_smll.setText(MyApplication.isJapanese() ? datasBean.getAreaJpn() + "㎡" : datasBean.getAreaCn() + "㎡");

    }

    private List<String> getList(String pic) {
        String d[] = pic.split(",");
        List<String> picList = new ArrayList();

        for (int i = 0; i < d.length; i++) {
            picList.add(d[i]);
        }
        return picList;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
        }
    }
}
