package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.bean.LoansBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.Daikuan_Adapter;
import com.example.administrator.japanhouse.presenter.HouseLogPresenter;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.ToastUtils;
import com.example.administrator.japanhouse.view.BaseDialog;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Daikuan_Activity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_call)
    TextView tvCall;
    @BindView(R.id.kefu)
    ImageView kefu;
    @BindView(R.id.sp_view)
    SpringView sp_view;
    private ImageView img_beak;
    private ImageView xinxi;
    private RelativeLayout liner;
    private RecyclerView toutiao_recycler;
    private Daikuan_Adapter daikuan_adapter;
    String tel="17600000000";
    private int pageNo=1;
    List<LoansBean.DatasBean>list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daikuan_);
        ButterKnife.bind(this);
        initView();
        intdata();
    }



    private void initView() {
        img_beak = (ImageView) findViewById(R.id.img_beak);
        xinxi = (ImageView) findViewById(R.id.xinxi);
        toutiao_recycler = (RecyclerView) findViewById(R.id.toutiao_recycler);
        img_beak.setOnClickListener(this);
        xinxi.setOnClickListener(this);
        tvCall.setOnClickListener(this);
        kefu.setOnClickListener(this);
        //        //加载适配器
        toutiao_recycler.setLayoutManager(new LinearLayoutManager(this));
        toutiao_recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        toutiao_recycler.setNestedScrollingEnabled(false);


//        String city = SharedPreferencesUtils.getInstace(this).getStringPreference("city", "");
        String city = CacheUtils.get(Constants.COUNTRY);
        if (city!=null&&city.equals("ja")) {
            tvCall.setVisibility(View.VISIBLE);
            kefu.setVisibility(View.GONE);
        } else {
            tvCall.setVisibility(View.GONE);
            kefu.setVisibility(View.VISIBLE);
        }

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
                        daikuan_adapter.notifyDataSetChanged();
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
                        daikuan_adapter.notifyDataSetChanged();
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }
        });
        sp_view.setFooter(new DefaultFooter(this));
        sp_view.setHeader(new DefaultHeader(this));

        daikuan_adapter = new Daikuan_Adapter(Daikuan_Activity.this,list);
        toutiao_recycler.setAdapter(daikuan_adapter);
    }
    private void intdata() {
        HttpParams params = new HttpParams();
        params.put("pageNo",pageNo);
        OkGo.<LoansBean>post(MyUrls.BASEURL + "/app/financial/list")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<LoansBean>(Daikuan_Activity.this,LoansBean.class){

                    @Override
                    public void onSuccess(Response<LoansBean> response) {
                        LoansBean body = response.body();
                        String code = body.getCode();
                        if(code.equals("200")){
                            List<LoansBean.DatasBean> datas = body.getDatas();
                            if(pageNo>1){
                                if(datas.size()>0){
                                    list.addAll(datas);
                                }
                            }else{
                                list.addAll(datas);
                            }
                            daikuan_adapter.notifyDataSetChanged();
                        }else if(code.equals("201")){
                            ToastUtils.getToast(Daikuan_Activity.this,body.getMsg());
                        }else if(code.equals("500")){
                            ToastUtils.getToast(Daikuan_Activity.this,body.getMsg());
                        }else if(code.equals("404")){
                            ToastUtils.getToast(Daikuan_Activity.this,body.getMsg());
                        }else if(code.equals("203")){
                            ToastUtils.getToast(Daikuan_Activity.this,body.getMsg());
                        }else if(code.equals("204")){
                            ToastUtils.getToast(Daikuan_Activity.this,body.getMsg());
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
            case R.id.xinxi:
                EventBus.getDefault().post(new EventBean(Constants.EVENT_CHAT));
                finish();
                break;
            case R.id.tv_call:
                ShowCallDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                break;
            case R.id.kefu:
                ShowCallDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                break;
        }
    }
    private void ShowCallDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(Daikuan_Activity.this);
        final BaseDialog dialog = builder.setViewId(R.layout.call_layout)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        TextView text_sure = dialog.getView(R.id.text_sure);
        final TextView tv_content = dialog.getView(R.id.tv_content);

        TextView text_pause = dialog.getView(R.id.text_pause);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent dialIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + tel));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
            }
        });
        //取消
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
