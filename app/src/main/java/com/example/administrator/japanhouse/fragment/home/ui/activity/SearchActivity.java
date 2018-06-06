package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.activity.adapter.SimpleBaseAdapter;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.fragment.home.ui.utils.flow;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.SoftKeyboardTool;
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
    List<String> list = new ArrayList<>();
    private FluidLayout fff;

    private SimpleBaseAdapter adapter;
    private List<String> historyDatas;

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
                    Intent intent = new Intent(SearchActivity.this, QuestionActivity.class);
                    intent.putExtra("searchText", mNames[finalI]);
                    startActivity(intent);

                    if (historyDatas != null) {
                        historyDatas.add(0, mNames[finalI]);
                    } else {
                        List<String> arrayList = new ArrayList();
                        arrayList.add(mNames[finalI]);
                        CacheUtils.put(Constants.SEARCH_WD_HISTORY, arrayList);
                    }
                    queryList();
                }
            });
        }

        sou = (EditText) findViewById(R.id.sou);
        beak = (TextView) findViewById(R.id.beak);
        lishi = (TextView) findViewById(R.id.lishi);
        shanchu = (ImageView) findViewById(R.id.shanchu);
        lv = (ListView) findViewById(R.id.lv);
        beak.setOnClickListener(this);
        shanchu.setOnClickListener(this);

        adapter = new SimpleBaseAdapter(this, list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, QuestionActivity.class);
                intent.putExtra("searchText", list.get(position));
                startActivity(intent);
            }
        });

        sou.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH && sou.getText().length() > 0) {
                    ((InputMethodManager) sou.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);

                    Intent intent = new Intent(SearchActivity.this, QuestionActivity.class);
                    intent.putExtra("searchText", sou.getText().toString());
                    startActivity(intent);

                    if (historyDatas != null) {
                        historyDatas.add(0, sou.getText().toString());
                    } else {
                        List<String> arrayList = new ArrayList();
                        arrayList.add(sou.getText().toString());
                        CacheUtils.put(Constants.SEARCH_WD_HISTORY, arrayList);
                    }
                    queryList();
                    return true;
                }
                return false;
            }
        });

        queryList();
    }

    private void queryList(){
        list.clear();
        historyDatas = CacheUtils.get(Constants.SEARCH_WD_HISTORY);
        if (historyDatas != null && historyDatas.size() > 0) {
            for (int i = 0; i < historyDatas.size(); i++) {
                list.add(historyDatas.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beak:
                SoftKeyboardTool.closeKeyboard(this);
                finish();
                break;
            case R.id.shanchu:
                list.clear();
                adapter.notifyDataSetChanged();
                CacheUtils.remove(Constants.SEARCH_WD_HISTORY);
                break;
        }
    }

}
