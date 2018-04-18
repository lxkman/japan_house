package com.example.administrator.japanhouse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.adapter.OwnerAdapter;
import com.example.administrator.japanhouse.adapter.OwnerWikipediaAdapter;
import com.example.administrator.japanhouse.base.BaseActivity;

/**
 *
 *  业主百科
 * Created by   admin on 2018/4/16.
 */

public class OwnerWikipediaActivity extends BaseActivity implements OwnerWikipediaAdapter.onItemClickListener{

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_wikipedia);

        mRecyclerView = (RecyclerView) findViewById(R.id.act_owner_wikipedia_recyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        OwnerWikipediaAdapter adapter = new OwnerWikipediaAdapter(this);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView = (RecyclerView) findViewById(R.id.act_owner_wikipedia_recyclerView);
        findViewById(R.id.act_owner_wikipedia_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void invoke(Context context){
        Intent intent = new Intent(context, OwnerWikipediaActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onItemClick() {
        OwnerDetailsActivity.invoke(this);
    }
}
