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

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.view.BaseDialog;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daikuan_detils);
        ButterKnife.bind(this);
        initView();


    }

    private void initView() {
        img_beak = (ImageView) findViewById(R.id.img_beak);
        dai_name = (TextView) findViewById(R.id.dai_name);
        hk_time = (TextView) findViewById(R.id.hk_time);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shenqing:
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
                        uname.setText("");
                        utel.setText("");
                        Toast.makeText(DaikuanDetilsActivity.this, "已提交", Toast.LENGTH_SHORT).show();
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
