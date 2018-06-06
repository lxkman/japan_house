package com.example.administrator.japanhouse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.activity.adapter.AgentListAdapter;
import com.example.administrator.japanhouse.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by   admin on 2018/4/26.
 */

public class AgentListActivity extends BaseActivity {

    private AgentListAdapter adapter;

    @BindView(R.id.act_agentList_back)
    ImageView back;
    @BindView(R.id.act_agentList_recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agentlist);
        ButterKnife.bind(this);

        adapter = new AgentListAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
