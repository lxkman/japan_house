package com.haiwai.administrator.japanhouse.fragment.home.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.bean.Bay_baike_Bean;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.fragment.home.ui.adapter.BaikeAdapter;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.haiwai.administrator.japanhouse.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class Buyhouse_Baike_Activity extends AppCompatActivity {

    private ImageView img_beak;
    private RecyclerView baike_recycler;
    private BaikeAdapter touAdapter;
    private SpringView sp_view;
   private int pageNo=1;
   private boolean isJa;
   List<Bay_baike_Bean.DatasBean>list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyhouse__baike_);
        initView();
        intdata();
    }



    private void initView() {
        img_beak = (ImageView) findViewById(R.id.img_beak);
        baike_recycler = (RecyclerView) findViewById(R.id.baike_recycler);
        sp_view = (SpringView) findViewById(R.id.sp_view);
        //点击返回事件
        img_beak.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   finish();
               }
           });
        sp_view.setType(SpringView.Type.FOLLOW);
        sp_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        pageNo=1;
                        intdata();
                        touAdapter.notifyDataSetChanged();
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNo++;
                        intdata();
                        touAdapter.notifyDataSetChanged();
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }
        });
        sp_view.setFooter(new DefaultFooter(this));
        sp_view.setHeader(new DefaultHeader(this));
        //加载适配器
        baike_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        touAdapter = new BaikeAdapter(this,list,isJa);
        baike_recycler.setAdapter(touAdapter);
    }
    private void intdata() {
        HttpParams params = new HttpParams();
        params.put("pageNo",pageNo);
        OkGo.<Bay_baike_Bean>post(MyUrls.BASEURL + "/app/textsource/purchasewikipedia")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Bay_baike_Bean>(Buyhouse_Baike_Activity.this,Bay_baike_Bean.class){
                    @Override
                    public void onSuccess(Response<Bay_baike_Bean> response) {
                        Bay_baike_Bean body = response.body();
                        String code = body.getCode();
                        if(code.equals("200")){
                            List<Bay_baike_Bean.DatasBean> datas = body.getDatas();
                            if(pageNo>1){
                                if(datas.size()>0){
                                    list.addAll(datas);
                                }
                            }else{
                                list.addAll(datas);
                            }
                            touAdapter.notifyDataSetChanged();
                        }else if(code.equals("201")){
                            ToastUtils.getToast(Buyhouse_Baike_Activity.this,body.getMsg());
                        }else if(code.equals("500")){
                            ToastUtils.getToast(Buyhouse_Baike_Activity.this,body.getMsg());
                        }else if(code.equals("404")){
                            ToastUtils.getToast(Buyhouse_Baike_Activity.this,body.getMsg());
                        }else if(code.equals("203")){
                            ToastUtils.getToast(Buyhouse_Baike_Activity.this,body.getMsg());
                        }else if(code.equals("204")){
                            ToastUtils.getToast(Buyhouse_Baike_Activity.this,body.getMsg());
                        }
                    }
                });
    }
}
