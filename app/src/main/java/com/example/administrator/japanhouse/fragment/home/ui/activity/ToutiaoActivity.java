package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.TouTiaoListBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.TouAdapter;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

public class ToutiaoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView img_beak;
    private ImageView xinxi;
    private RecyclerView toutiao_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toutiao);
        initView();
        initNet();
    }

    private void initNet() {
            HttpParams params = new HttpParams();
            params.put("pageNo", "1");
            OkGo.<TouTiaoListBean>post(MyUrls.BASEURL + "/app/topline/toplinelist")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<TouTiaoListBean>(this, TouTiaoListBean.class) {
                        @Override
                        public void onSuccess(Response<TouTiaoListBean> response) {
                            int code = response.code();
                            TouTiaoListBean touTiaoListBean = response.body();
                                if (code==200&&touTiaoListBean!=null){
                                    List<TouTiaoListBean.DatasBean> datas = touTiaoListBean.getDatas();
                                    //加载适配器
                                    toutiao_recycler.setLayoutManager(new LinearLayoutManager(ToutiaoActivity.this,LinearLayoutManager.VERTICAL,false));
                                    TouAdapter touAdapter = new TouAdapter(ToutiaoActivity.this,datas);
                                    toutiao_recycler.setAdapter(touAdapter);
                                }
                        }
                    });
    }

    private void initView() {
        img_beak = (ImageView) findViewById(R.id.img_beak);
        xinxi = (ImageView) findViewById(R.id.xinxi);
        toutiao_recycler = (RecyclerView) findViewById(R.id.toutiao_recycler);

        img_beak.setOnClickListener(this);
        xinxi.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_beak:
                finish();
                break;
            case R.id.xinxi:
                Toast.makeText(ToutiaoActivity.this,"跳转",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
