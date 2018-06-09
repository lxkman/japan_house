package com.example.administrator.japanhouse.login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.SuccessBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPswActivity extends BaseActivity {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.edt_yanzheng_code)
    EditText edtYanzhengCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.edt_pass_sure)
    EditText edtPassSure;
    @BindView(R.id.btn_find_pass)
    Button btnFindPass;
    @BindView(R.id.check_quyu)
    TextView checkQuyu;
    @BindView(R.id.activity_register)
    LinearLayout activityRegister;
    private View popupView;
    private PopupWindow basePopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_psw);
        ButterKnife.bind(this);
        boolean ja = MyUtils.isJa();
        if (!ja){
            checkQuyu.setText("+86");
        }else {
            checkQuyu.setText("+81");
        }
    }

    @OnClick({R.id.back_img, R.id.tv_get_code, R.id.btn_find_pass,R.id.check_quyu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            case R.id.tv_get_code:
                break;
            case R.id.btn_find_pass:
                initNet();

                break;
            case R.id.check_quyu:
                initPop();
                basePopupWindow.showAsDropDown(view);
                break;
        }
    }

    private void initPop() {
        //屏幕变暗
        WindowManager.LayoutParams lp =  getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

        popupView = View.inflate(mContext,R.layout.layout_check_popupwindow, null);
        popupView.findViewById(R.id.tv_saoyisao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkQuyu.setText("+86");
                basePopupWindow.dismiss();
            }
        });
        popupView.findViewById(R.id.tv_weiliao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkQuyu.setText("+81");
                basePopupWindow.dismiss();
            }
        });
        basePopupWindow = (PopupWindow) new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        basePopupWindow.setTouchable(true);
        basePopupWindow.setOutsideTouchable(true);
        basePopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //消失的监听，屏幕还原
        basePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp =  getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
            }
        });
    }
    private void initNet() {
        if (TextUtils.isEmpty(edtPhone.getText().toString())) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }else if (!MyUtils.isMobileNO(edtPhone.getText().toString())) {
            Toast.makeText(this, "手机号格式错误", Toast.LENGTH_SHORT).show();
            return;
        }else if (!TextUtils.isEmpty(edtPhone.getText().toString())&&MyUtils.isMobileNO(edtPhone.getText().toString())){
            String phone = edtPhone.getText().toString();
            String substring = phone.substring(0, 3);
            if (substring.equals("050")||substring.equals("060")||substring.equals("070")||substring.equals("080")||substring.equals("090")){
                if (checkQuyu.getText().equals("+86")){
                    Toast.makeText(mContext, "请选择正确的区号", Toast.LENGTH_SHORT).show();
                    return;
                }
            }else {
                if (checkQuyu.getText().equals("+81")){
                    Toast.makeText(mContext, "请选择正确的区号", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            if(TextUtils.isEmpty(edtYanzhengCode.getText().toString())) {
                Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                return;
            }else if(TextUtils.isEmpty(edtPass.getText().toString())) {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                return;
            }else if (!MyUtils.isPswRuleNO(edtPass.getText().toString())){
                Toast.makeText(this, "请输入6-16位数字和字母组成的密码", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(edtPassSure.getText().toString())) {
                Toast.makeText(this, "请输入确认密码", Toast.LENGTH_SHORT).show();
                return;
            }else if(!edtPass.getText().toString().equals(edtPassSure.getText().toString())) {
                Toast.makeText(this, "两次输入的密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                return;
            }else {
                HttpParams params = new HttpParams();
                params.put("phone", edtPhone.getText().toString());
                params.put("msg","1234");
                params.put("newPassword", edtPass.getText().toString());
                OkGo.<SuccessBean>post(MyUrls.BASEURL + "/app/user/forgotpassword")
                        .tag(this)
                        .params(params)
                        .execute(new DialogCallback<SuccessBean>(this, SuccessBean.class) {
                            @Override
                            public void onSuccess(Response<SuccessBean> response) {
                                int code = response.code();
                                SuccessBean successBean = response.body();
                                if (successBean.getCode().equals("200")){
                                    Toast.makeText(ForgetPswActivity.this, successBean.getMsg()+"", Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent(ForgetPswActivity.this, LoginActivity.class));
                                }else if (successBean.getCode().equals("-1")){
                                    Toast.makeText(ForgetPswActivity.this,successBean.getMsg()+"", Toast.LENGTH_SHORT).show();
                                }else if (successBean.getCode().equals("207")){
                                    Toast.makeText(ForgetPswActivity.this, successBean.getMsg()+"", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }

    }
}
