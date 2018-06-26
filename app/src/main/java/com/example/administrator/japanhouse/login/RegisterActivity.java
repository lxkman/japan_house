package com.example.administrator.japanhouse.login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.UMLoginActivity;
import com.example.administrator.japanhouse.bean.LoginParmeter;
import com.example.administrator.japanhouse.bean.SuccessBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.example.administrator.japanhouse.utils.SendSmsTimerUtils;
import com.linecorp.linesdk.LineCredential;
import com.linecorp.linesdk.LineProfile;
import com.linecorp.linesdk.auth.LineLoginApi;
import com.linecorp.linesdk.auth.LineLoginResult;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.administrator.japanhouse.R.id.edt_phone;

public class RegisterActivity extends UMLoginActivity {

    private static final int LINE_REQUEST_CODE = 123;

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(edt_phone)
    EditText edtPhone;
    @BindView(R.id.edt_yanzheng_code)
    EditText edtYanzhengCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.edt_pass_sure)
    EditText edtPassSure;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.img_weixin)
    ImageView imgWeixin;
    @BindView(R.id.img_weibo)
    ImageView imgWeibo;
    @BindView(R.id.img_qq)
    ImageView imgQq;
    @BindView(R.id.img_line)
    ImageView imgLine;
    @BindView(R.id.people_xieyi)
    TextView peopleXieyi;
    @BindView(R.id.private_xieyi)
    TextView privateXieyi;
    @BindView(R.id.activity_register)
    RelativeLayout activityRegister;
    @BindView(R.id.check_quyu)
    TextView checkQuyu;
    private View popupView;
    private PopupWindow basePopupWindow;
    private String QuNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
                boolean ja = MyUtils.isJa();
        if (!ja){
            checkQuyu.setText("+86");
        }else {
            checkQuyu.setText("+81");
        }
    }


    @OnClick({R.id.back_img, R.id.tv_login, R.id.tv_get_code, R.id.btn_login, R.id.img_weixin, R.id.img_weibo, R.id.img_qq,
            R.id.img_line, R.id.people_xieyi, R.id.private_xieyi,R.id.check_quyu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            case R.id.tv_login:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
            case R.id.tv_get_code:
                    if (TextUtils.isEmpty(edtPhone.getText().toString())) {
                        Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                        return;
                    }else if (!MyUtils.isMobileNO(edtPhone.getText().toString())) {
                        Toast.makeText(this, "手机号格式错误", Toast.LENGTH_SHORT).show();
                        return;
                    }else if (!TextUtils.isEmpty(edtPhone.getText().toString())&&MyUtils.isMobileNO(edtPhone.getText().toString())) {
                        String phone = edtPhone.getText().toString();
                        String substring = phone.substring(0, 3);
                        if (substring.equals("050") || substring.equals("060") || substring.equals("070") || substring.equals("080") || substring.equals("090")) {
                            QuNumber = "1";//国际
                            if (checkQuyu.getText().equals("+86")) {
                                Toast.makeText(this, "请选择正确的区号", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } else {
                            QuNumber = "2";//国内
                            if (checkQuyu.getText().equals("+81")) {
                                Toast.makeText(this, "请选择正确的区号", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        getCode();//验证码接口
                    }
                break;
            case R.id.btn_login:
                initNet();
                break;
            case R.id.img_weixin:
                loginByWeiXin(this);
                break;
            case R.id.img_weibo:
//                startActivity(new Intent(LoginActivity.this, BindPhoneActivity.class));
                loginBySina(this);
                break;
            case R.id.img_qq:
//                startActivity(new Intent(LoginActivity.this, BindPhoneActivity.class));
                loginByQQ(this);
                break;
            case R.id.img_line:
                try {
                    // App to App loginline
                    Intent LoginIntent = LineLoginApi.getLoginIntent(this, getResources().getString(R.string.line_channel_id));
                    startActivityForResult(LoginIntent, LINE_REQUEST_CODE);
                } catch (Exception e) {
                    Log.e("ERROR", "33:" + e.toString());
                }
//                startActivity(new Intent(RegisterActivity.this, BindPhoneActivity.class));
                break;
            case R.id.people_xieyi:
                startActivity(new Intent(RegisterActivity.this, PeopleXieyiActivity.class));
                break;
            case R.id.private_xieyi:
                startActivity(new Intent(RegisterActivity.this, PrivateActivity.class));
                break;
            case R.id.check_quyu:
                initPop();
                basePopupWindow.showAsDropDown(view);
                break;
        }
    }

    private void getCode() {
        HttpParams params = new HttpParams();
        String quhao;
        if (checkQuyu.getText().equals("+81")){
            quhao="81";
        }else {
            quhao="86";
        }
        Log.d("RegisterActivity", QuNumber+"---------"+quhao);
        params.put("phone", "00"+quhao+edtPhone.getText().toString());
        params.put("sendType",QuNumber);
        OkGo.<SuccessBean>post(MyUrls.BASEURL + "/send/msg/sendmsg")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SuccessBean>(this, SuccessBean.class) {
                    @Override
                    public void onSuccess(Response<SuccessBean> response) {
                        int code = response.code();
                        SuccessBean successBean = response.body();
                        if (successBean.getCode().equals("200")){
                            SendSmsTimerUtils.sendSms(tvGetCode, R.color.shihuangse, R.color.shihuangse);
                            Toast.makeText(RegisterActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                        }else if (successBean.getCode().equals("-1")){
                            Toast.makeText(RegisterActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                        }else if (successBean.getCode().equals("500")){
                            Toast.makeText(RegisterActivity.this, "内部服务器错误", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegisterActivity.this, successBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
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
                    Toast.makeText(this, "请选择正确的区号", Toast.LENGTH_SHORT).show();
                    return;
                }
            }else {
                if (checkQuyu.getText().equals("+81")){
                    Toast.makeText(this, "请选择正确的区号", Toast.LENGTH_SHORT).show();
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
                params.put("tPhone", edtPhone.getText().toString());
                params.put("code",edtYanzhengCode.getText().toString());
                params.put("passWord", edtPass.getText().toString());
                OkGo.<SuccessBean>post(MyUrls.BASEURL + "/app/user/registered")
                        .tag(this)
                        .params(params)
                        .execute(new DialogCallback<SuccessBean>(this, SuccessBean.class) {
                            @Override
                            public void onSuccess(Response<SuccessBean> response) {
                                int code = response.code();
                                SuccessBean successBean = response.body();
                                if (successBean.getCode().equals("200")){
                                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                   startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                   finish();
                                    return;
                                }else if (successBean.getCode().equals("-1")){
                                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                    return;
                                }else if (successBean.getCode().equals("207")){
                                    Toast.makeText(RegisterActivity.this, "此号码已被注册", Toast.LENGTH_SHORT).show();
                                    return;
                                }else {
                                    Toast.makeText(RegisterActivity.this, successBean.getMsg()+"", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });
            }
        }

    }

    private void initPop() {
        //屏幕变暗
        WindowManager.LayoutParams lp =  getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

        popupView = View.inflate(this,R.layout.layout_check_popupwindow, null);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LINE_REQUEST_CODE) {
            LineLoginResult result = LineLoginApi.getLoginResultFromIntent(data);
            switch (result.getResponseCode()) {
                case SUCCESS:
                    LoginParmeter loginParmeter = new LoginParmeter();
                    LineProfile lineProfile = result.getLineProfile();
                    LineCredential credential = result.getLineCredential();
                    loginParmeter.id = lineProfile.getUserId();
                    loginParmeter.age = "";
                    loginParmeter.accessToken = credential.getAccessToken().getAccessToken();

                    Uri facebookUri = lineProfile.getPictureUrl();
                    if (facebookUri != null) {
                        loginParmeter.avatar = facebookUri.toString();
                        Log.e("======>>avatar", "" + facebookUri.toString());
                    } else {
                        loginParmeter.avatar = "";
                    }
                    loginParmeter.nickname = lineProfile.getDisplayName();
                    Log.e("======>>id", "" + lineProfile.getUserId());
                    Log.e("======>>accessToken", "" + credential.getAccessToken().getAccessToken());

                    Log.e("======>>nickname", "" + lineProfile.getDisplayName());
                    break;
                case CANCEL:
                    Log.e("======>>", "LINE login  Canceled by user!!");
                    break;
                default:
                    Log.e("======>>", "login LINE FAILED!");
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
