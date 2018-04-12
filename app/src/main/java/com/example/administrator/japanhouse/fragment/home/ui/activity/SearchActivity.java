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
import com.example.administrator.japanhouse.fragment.home.ui.utils.flow;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        initView();
    }

    private void initView() {
        initChildViews();
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

   /*
   * 流式布局
   * */
    private void initChildViews() {
        flowLayout = (flow) findViewById(R.id.ffff);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 20;
        lp.rightMargin = 20;
        lp.bottomMargin = 10;
        for(int i = 0; i < mNames.length; i ++){
            TextView view = new TextView(this);
            view.setText(mNames[i]);
            view.setTextColor(Color.BLACK);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));

            flowLayout.addView(view,lp);
        }

    }


}
