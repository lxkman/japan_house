package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.login.LoginActivity;
import com.example.administrator.japanhouse.model.NoDataBean;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.TUtils;
import com.example.administrator.japanhouse.view.BaseDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaikuanDetilsActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.btn_call)
    Button btnCall;
    private ImageView img_beak;
    private TextView dai_name;
    private TextView hk_time;
    private Button shenqing;
    private ImageView kefu;
    private boolean flag=true;
    String tel="17600000000";
    private TextView yuexi;
    private TextView price;
    private TextView nianxian;
    private TextView fk_fangshi;
    private TextView hj_yaoqiu;
    private TextView sx_cailiao;
    private TextView nl_yaoyiu;


    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daikuan_detils);
        ButterKnife.bind(this);
        initView();


    }

    private void initView() {
        //获取跳转的所有数据
        id = getIntent().getIntExtra("id", 0);
        String name = getIntent().getStringExtra("name");
        String accrual = getIntent().getStringExtra("lixi");
//        double i = Integer.parseInt(accrual)*1.0d;
        String fanwei = getIntent().getStringExtra("fanwei");
        String zhouqi = getIntent().getStringExtra("zhouqi");
        String fangshi = getIntent().getStringExtra("fangshi");
        String shijian = getIntent().getStringExtra("shijian");
        String huji = getIntent().getStringExtra("huji");
        String cailiao = getIntent().getStringExtra("cailiao");
        String age = getIntent().getStringExtra("age");


        img_beak = (ImageView) findViewById(R.id.img_beak);
        dai_name = (TextView) findViewById(R.id.dai_name);
        yuexi = (TextView) findViewById(R.id.yuexi);
        nianxian = (TextView) findViewById(R.id.nianxian);
        fk_fangshi = (TextView) findViewById(R.id.fk_fangshi);
        sx_cailiao = (TextView) findViewById(R.id.sx_cailiao);
        price = (TextView) findViewById(R.id.price);
        hk_time = (TextView) findViewById(R.id.hk_time);
        hj_yaoqiu = (TextView) findViewById(R.id.hj_yaoqiu);
        nl_yaoyiu = (TextView) findViewById(R.id.nl_yaoyiu);
        shenqing = (Button) findViewById(R.id.shenqing);
        kefu = (ImageView) findViewById(R.id.kefu);
        img_beak.setOnClickListener(this);
        shenqing.setOnClickListener(this);
        kefu.setOnClickListener(this);
        btnCall.setOnClickListener(this);
//        String city = SharedPreferencesUtils.getInstace(this).getStringPreference("city", "");
        String city = CacheUtils.get(Constants.COUNTRY);
        if (city!=null&&city.equals("ja")) {
            kefu.setVisibility(View.GONE);
            btnCall.setVisibility(View.VISIBLE);
        } else {
            kefu.setVisibility(View.VISIBLE);
            btnCall.setVisibility(View.GONE);
        }
        //赋值
        dai_name.setText(name);
        yuexi.setText(String.format(getString(R.string.rate_max), accrual));
        price.setText(String.format(getString(R.string.linitRange_max), fanwei));
        nianxian.setText(zhouqi+"年");
        fk_fangshi.setText(fangshi);
        hk_time.setText(shijian);
        hj_yaoqiu.setText(huji);
        sx_cailiao.setText(cailiao);
        nl_yaoyiu.setText(age);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shenqing:
                if (!MyApplication.isLogin()) {
                    startActivity(new Intent(this, LoginActivity.class));
                    return;
                }
                shumaDialog(Gravity.CENTER, R.style.Alpah_aniamtion);
                break;
            case R.id.img_beak:
                finish();
                break;
            case R.id.kefu:
                ShowCallDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                break;
            case R.id.btn_call:
                ShowCallDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                break;
        }
    }

    private void ShowCallDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(DaikuanDetilsActivity.this);
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
    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.loans_apply)
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

        ImageView cha = dialog.getView(R.id.cha);
        final EditText uname = dialog.getView(R.id.user_name);
        final EditText utel = dialog.getView(R.id.user_tel);
        final ImageView iv_check = dialog.getView(R.id.iv_check);
        TextView xieyi = dialog.getView(R.id.xieyi);
        Button tijiao = dialog.getView(R.id.tijiao);
        //点差
        cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        iv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    iv_check.setImageResource(R.drawable.cbuncheck);
                }else {
                    iv_check.setImageResource(R.drawable.cbcheck);
                }
                flag = !flag;
            }
        });
        //点击提交
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = uname.getText().toString();
                String tel = utel.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(tel)) {
                    Toast.makeText(DaikuanDetilsActivity.this, "输入框为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (flag) {
                        HttpParams params = new HttpParams();
                        params.put("token", MyApplication.getUserToken());
                        params.put("applicationName", uname.getText().toString());
                        params.put("applicationPhone", utel.getText().toString());
                        params.put("localId", id);
                        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/application/insertapplication")
                                .tag(this)
                                .params(params)
                                .execute(new DialogCallback<NoDataBean>(DaikuanDetilsActivity.this, NoDataBean.class) {
                                    @Override
                                    public void onSuccess(Response<NoDataBean> response) {
                                        if (!TextUtils.equals(response.body().getCode(), "200")) {
                                            TUtils.showFail(DaikuanDetilsActivity.this, getString(R.string.fail_sq));
                                        } else {
                                            TUtils.showFail(DaikuanDetilsActivity.this, getString(R.string.success_sq));
                                        }
                                    }
                                });

                        dialog.dismiss();
                    } else {
                        Toast.makeText(DaikuanDetilsActivity.this, "请阅读用户协议", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        //点击协议
        xieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DaikuanDetilsActivity.this, DealActivity.class));
            }
        });
        dialog.show();
    }

}
