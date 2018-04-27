package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.view.BaseDialog;

public class DaikuanDetilsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_beak;
    private TextView dai_name;
    private TextView hk_time;
    private Button shenqing;
    private ImageView kefu;
    private boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daikuan_detils);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shenqing:
                shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                break;
            case R.id.img_beak:
                finish();
                break;
            case R.id.kefu:
                Toast.makeText(this,"客服",Toast.LENGTH_SHORT).show();
                break;
        }
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
        CheckBox box = dialog.getView(R.id.check_box);
        TextView xieyi = dialog.getView(R.id.xieyi);
        Button tijiao = dialog.getView(R.id.tijiao);
        //点差
        cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
         //CheckBox事件监听
        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                flag=b;
            }
        });
        //点击提交
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = uname.getText().toString();
                String tel = utel.getText().toString();
                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(tel)){
                    Toast.makeText(DaikuanDetilsActivity.this,"输入框为空",Toast.LENGTH_SHORT).show();
                }else{
                    if(flag){
                        uname.setText("");
                        utel.setText("");
                        Toast.makeText(DaikuanDetilsActivity.this,"已提交",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(DaikuanDetilsActivity.this,"请阅读用户协议",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        //点击协议
        xieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DaikuanDetilsActivity.this,DealActivity.class));
            }
        });
        dialog.show();
    }

}
