package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.fragment.home.ui.utils.flow;
import com.example.administrator.japanhouse.utils.TUtils;
import com.example.administrator.japanhouse.view.FluidLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private String mNames[] = {
            "房价", "群租房", "买房",
            "卖房", "北京房", "南京房"
    };
    private EditText sou;
    private TextView beak;
    private flow flowLayout;
    private TextView lishi;
    private ImageView shanchu;
    private ListView lv;
    List<String>list=new ArrayList<>();
    private FluidLayout fff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        initView();
    }

    private void initView() {

        fff = (FluidLayout) findViewById(R.id.search_recycler);
        fff.removeAllViews();
        for (int i = 0; i < mNames.length; i++) {
            final TextView tv = (TextView) View.inflate(mContext, R.layout.item_hot_search, null);
            tv.setText(mNames[i]);
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(12, 12, 12, 12);
            fff.addView(tv, params);
            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TUtils.showShort(mContext, "点击了---" + mNames[finalI]);
                }
            });
        }

        //initChildViews();
        sou = (EditText) findViewById(R.id.sou);
        beak = (TextView) findViewById(R.id.beak);
        lishi = (TextView) findViewById(R.id.lishi);
        shanchu = (ImageView) findViewById(R.id.shanchu);
        lv = (ListView) findViewById(R.id.lv);
        beak.setOnClickListener(this);
        shanchu.setOnClickListener(this);
           list.add("租房");
           list.add("押金");

          lv.setAdapter(new ArrayAdapter<String>(SearchActivity.this,android.R.layout.simple_list_item_1,list));

    }
    @Override
    public void onClick(View view) {
      switch(view.getId()){
          case R.id.beak:
              finish();
              break;
          case R.id.shanchu:
             list.clear();
              lv.setAdapter(new ArrayAdapter<String>(SearchActivity.this,android.R.layout.simple_list_item_1,list));
              break;
      }
    }


}
