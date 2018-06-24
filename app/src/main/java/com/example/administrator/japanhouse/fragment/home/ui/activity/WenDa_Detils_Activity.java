package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.bean.WenDa_Details_Bean;
import com.example.administrator.japanhouse.bean.WenDa_Details_Pinglun_Bean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.WenDa_Detil_Adapter;
import com.example.administrator.japanhouse.login.LoginActivity;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WenDa_Detils_Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_beak;
    private TextView title;
    private TextView time;
    private TextView neirong;
    private RecyclerView wenda_recy;
    private TextView text_tiwen;
    private RelativeLayout liner3;
    private TextView text_huida;
    private RelativeLayout liner4;
    private TextView ti_name;
    private TextView huida;
    private int askid;
    private int pageNo=1;
    private SpringView sp_view;
    private WenDa_Detil_Adapter toutiaoAdapter;
    List<WenDa_Details_Pinglun_Bean.DatasBean>list=new ArrayList<>();
    private String title1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wen_da__detils_);
        initView();
        intdaview();

        EventBus.getDefault().register(this);
    }
    private void initView() {
        askid = getIntent().getIntExtra("askid", 0);
        title1 = getIntent().getStringExtra("title");
        img_beak = (ImageView) findViewById(R.id.img_beak);
        this.title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time);
        neirong = (TextView) findViewById(R.id.neirong);
        wenda_recy = (RecyclerView) findViewById(R.id.wenda_recy);
        text_tiwen = (TextView) findViewById(R.id.text_tiwen);
        liner3 = (RelativeLayout) findViewById(R.id.liner3);
        text_huida = (TextView) findViewById(R.id.text_huida);
        liner4 = (RelativeLayout) findViewById(R.id.liner4);
        ti_name = (TextView) findViewById(R.id.ti_name);
        sp_view = (SpringView) findViewById(R.id.sp_view);
        huida = (TextView) findViewById(R.id.huida);
        img_beak.setOnClickListener(this);
        liner3.setOnClickListener(this);
        liner4.setOnClickListener(this);

        //刷新加载
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
                        toutiaoAdapter.notifyDataSetChanged();
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
                        toutiaoAdapter.notifyDataSetChanged();
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }
        });
        sp_view.setFooter(new DefaultFooter(this));
        sp_view.setHeader(new DefaultHeader(this));

        //加载适配器
        wenda_recy.setLayoutManager(new LinearLayoutManager(this));
//        wenda_recy.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        wenda_recy.setNestedScrollingEnabled(false);
        toutiaoAdapter = new WenDa_Detil_Adapter(this,list);
        wenda_recy.setAdapter(toutiaoAdapter);

    }
    private void intdaview() {
        HttpParams params = new HttpParams();
        params.put("askId",askid);
        OkGo.<WenDa_Details_Bean>post(MyUrls.BASEURL + "/app/askinfo/askinfo")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<WenDa_Details_Bean>(WenDa_Detils_Activity.this,WenDa_Details_Bean.class){
                    @Override
                    public void onSuccess(Response<WenDa_Details_Bean> response) {
                        WenDa_Details_Bean body = response.body();
                        String code = body.getCode();
                        if(code.equals("200")){
                            WenDa_Details_Bean.DatasBean datas = body.getDatas();
                            long updateTime = datas.getUpdateTime();
                            String dateToString = getDateToString(String.valueOf(updateTime / 1000));
                            time.setText(dateToString);
                            title.setText(datas.getTitle());
                            neirong.setText(datas.getDescription());
                            if (body.getDatas().getHwdcUser() != null) {
                                ti_name.setText(body.getDatas().getHwdcUser().getNickname());
                            }
                        }else if(code.equals("201")){
                            ToastUtils.getToast(WenDa_Detils_Activity.this,body.getMsg());
                        }else if(code.equals("500")){
                            ToastUtils.getToast(WenDa_Detils_Activity.this,body.getMsg());
                        }else if(code.equals("404")){
                            ToastUtils.getToast(WenDa_Detils_Activity.this,body.getMsg());
                        }else if(code.equals("203")){
                            ToastUtils.getToast(WenDa_Detils_Activity.this,body.getMsg());
                        }else if(code.equals("204")){
                            ToastUtils.getToast(WenDa_Detils_Activity.this,body.getMsg());
                        }
                    }

                });
        HttpParams params1 = new HttpParams();
        params1.put("askId",askid);
        params1.put("pageNo",pageNo);
        OkGo.<WenDa_Details_Pinglun_Bean>post(MyUrls.BASEURL + "/app/revert/revertbyaskid")
                .tag(this)
                .params(params1)
                .execute(new DialogCallback<WenDa_Details_Pinglun_Bean>(WenDa_Detils_Activity.this,WenDa_Details_Pinglun_Bean.class){
                    @Override
                    public void onSuccess(Response<WenDa_Details_Pinglun_Bean> response) {
                        WenDa_Details_Pinglun_Bean body = response.body();
                        String code = body.getCode();
                        if(code.equals("200")){
                            List<WenDa_Details_Pinglun_Bean.DatasBean> datas = body.getDatas();
                            if(pageNo>1){
                                if(datas.size()>0){
                                    list.addAll(datas);
                                }
                            }else{
                                list.addAll(datas);
                            }
                            huida.setText("共"+datas.size()+"个回答");
                            toutiaoAdapter.notifyDataSetChanged();
                        }else if(code.equals("201")){
                            ToastUtils.getToast(WenDa_Detils_Activity.this,body.getMsg());
                        }else if(code.equals("500")){
                            ToastUtils.getToast(WenDa_Detils_Activity.this,body.getMsg());
                        }else if(code.equals("404")){
                            ToastUtils.getToast(WenDa_Detils_Activity.this,body.getMsg());
                        }else if(code.equals("203")){
                            ToastUtils.getToast(WenDa_Detils_Activity.this,body.getMsg());
                        }else if(code.equals("204")){
                            ToastUtils.getToast(WenDa_Detils_Activity.this,body.getMsg());
                        }
                    }

                });
    }


    private void intdata() {
        HttpParams params = new HttpParams();
        params.put("askId",askid);
        params.put("pageNo",pageNo);
        OkGo.<WenDa_Details_Pinglun_Bean>post(MyUrls.BASEURL + "/app/revert/revertbyaskid")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<WenDa_Details_Pinglun_Bean>(WenDa_Detils_Activity.this,WenDa_Details_Pinglun_Bean.class){
                    @Override
                    public void onSuccess(Response<WenDa_Details_Pinglun_Bean> response) {
                        WenDa_Details_Pinglun_Bean body = response.body();
                        String code = body.getCode();
                        if(code.equals("200")){
                            List<WenDa_Details_Pinglun_Bean.DatasBean> datas = body.getDatas();
                            if(pageNo>1){
                                if(datas.size()>0){
                                    list.addAll(datas);
                                }
                            }else{
                                list.addAll(datas);
                            }
                           huida.setText("共"+datas.size()+"个回答");
                            toutiaoAdapter.notifyDataSetChanged();
                        }else if(code.equals("201")){
                            ToastUtils.getToast(WenDa_Detils_Activity.this,body.getMsg());
                        }else if(code.equals("500")){
                            ToastUtils.getToast(WenDa_Detils_Activity.this,body.getMsg());
                        }else if(code.equals("404")){
                            ToastUtils.getToast(WenDa_Detils_Activity.this,body.getMsg());
                        }else if(code.equals("203")){
                            ToastUtils.getToast(WenDa_Detils_Activity.this,body.getMsg());
                        }else if(code.equals("204")){
                            ToastUtils.getToast(WenDa_Detils_Activity.this,body.getMsg());
                        }
                    }

                });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_beak:
                finish();
                break;
            case R.id.liner3:
                if (!MyApplication.isLogin()) {
                    startActivity(new Intent(this, LoginActivity.class));
                    return;
                }

                Intent intent = new Intent(WenDa_Detils_Activity.this, QuizActivity.class);
                startActivity(intent);
                break;
            case R.id.liner4:
                if (!MyApplication.isLogin()) {
                    startActivity(new Intent(this, LoginActivity.class));
                    return;
                }

                Intent intent1 = new Intent(WenDa_Detils_Activity.this, AnswerActivity.class);
                intent1.putExtra("title",title1);
                intent1.putExtra("askid", askid);
                startActivity(intent1);
                break;

        }
    }

    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventUserInfos(EventBean eventBean) {
        if (TextUtils.equals(eventBean.getMsg(), Constants.EVENT_W) || TextUtils.equals(eventBean.getMsg(), Constants.EVENT_D)) {
            list.clear();
            pageNo = 1;
            intdata();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
