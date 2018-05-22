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
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.LoginParmeter;
import com.example.administrator.japanhouse.bean.LoginBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.linecorp.linesdk.LineCredential;
import com.linecorp.linesdk.LineProfile;
import com.linecorp.linesdk.auth.LineLoginApi;
import com.linecorp.linesdk.auth.LineLoginResult;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.zackratos.ultimatebar.UltimateBar;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class LoginActivity extends BaseActivity {
    private static final int LINE_REQUEST_CODE = 123;

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.tv_forget_pass)
    TextView tvForgetPass;
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
    @BindView(R.id.tv_show_pop)
    TextView tvShowPop;
    private View popupView;
    private PopupWindow basePopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //拉伸图片覆盖标题栏
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar(false);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        boolean ja = MyUtils.isJa();
        if (!ja){
            tvShowPop.setText("+86");
        }else {
            tvShowPop.setText("+81");
        }
    }

    @OnClick({R.id.back_img, R.id.tv_register, R.id.tv_forget_pass, R.id.btn_login, R.id.img_weixin, R.id.img_weibo, R.id.img_qq, R.id.img_line, R.id.tv_show_pop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                HashMap<String, Boolean> hashMap = new HashMap<>();
                //会话类型 以及是否聚合显示
                hashMap.put(Conversation.ConversationType.PRIVATE.getName(), false);
//        hashMap.put(Conversation.ConversationType.PUSH_SERVICE.getName(),true);
//        hashMap.put(Conversation.ConversationType.SYSTEM.getName(),true);
                RongIM.getInstance().startConversationList(this, hashMap);
                finish();
                break;
            //注册
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            //忘记密码
            case R.id.tv_forget_pass:
                startActivity(new Intent(LoginActivity.this, ForgetPswActivity.class));
                break;
            //登录
            case R.id.btn_login:
                initLoginNet();
                break;
            case R.id.img_weixin:
                startActivity(new Intent(LoginActivity.this, BindPhoneActivity.class));
                break;
            case R.id.img_weibo:
                startActivity(new Intent(LoginActivity.this, BindPhoneActivity.class));
                break;
            case R.id.img_qq:
                startActivity(new Intent(LoginActivity.this, BindPhoneActivity.class));
                break;
            case R.id.img_line:
//                startActivity(new Intent(LoginActivity.this, BindPhoneActivity.class));
                try {
                    // App to App loginline
                    Intent LoginIntent = LineLoginApi.getLoginIntent(this, getResources().getString(R.string.line_channel_id));
                    startActivityForResult(LoginIntent, LINE_REQUEST_CODE);
                } catch (Exception e) {
                    Log.e("ERROR", "33:" + e.toString());
                }
                break;
            case R.id.tv_show_pop:
                initPop();
                basePopupWindow.showAsDropDown(view);
                break;
        }
    }

    private void initLoginNet() {
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
                if (tvShowPop.getText().equals("+86")){
                    Toast.makeText(mContext, "请选择正确的区号", Toast.LENGTH_SHORT).show();
                    return;
                }
            }else {
                if (tvShowPop.getText().equals("+81")){
                    Toast.makeText(mContext, "请选择正确的区号", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
             if(TextUtils.isEmpty(edtPass.getText().toString())) {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                return;
            }else if (!MyUtils.isPswRuleNO(edtPass.getText().toString())){
                Toast.makeText(this, "请输入6-16位数字和字母组成的密码", Toast.LENGTH_SHORT).show();
            }else {
                HttpParams params = new HttpParams();
                params.put("tPhone", edtPhone.getText().toString());
                params.put("passWord", edtPass.getText().toString());
                OkGo.<LoginBean>post(MyUrls.BASEURL + "/app/user/login")
                        .tag(this)
                        .params(params)
                        .execute(new DialogCallback<LoginBean>(this, LoginBean.class) {
                            @Override
                            public void onSuccess(Response<LoginBean> response) {
                                int code = response.code();
                                LoginBean LoginBean = response.body();
//                                Log.d("LoginActivity", LoginBean.getCode()+"-------------");
                                if (LoginBean.getCode().equals("200")){
                                    SharedPreferencesUtils.getInstace(LoginActivity.this).setStringPreference("uid",LoginBean.getDatas().getUid()+"");
                                    SharedPreferencesUtils.getInstace(LoginActivity.this).setStringPreference("token",LoginBean.getDatas().getToken()+"");
                                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();

                                    HashMap<String, Boolean> hm = new HashMap<>();
                                    //会话类型 以及是否聚合显示
                                    hm.put(Conversation.ConversationType.PRIVATE.getName(), false);
                            //        hashMap.put(Conversation.ConversationType.PUSH_SERVICE.getName(),true);
                            //        hashMap.put(Conversation.ConversationType.SYSTEM.getName(),true);
                                    RongIM.getInstance().startConversationList(LoginActivity.this, hm);
                                    finish();
                                }else if (LoginBean.getCode().equals("-1")){
                                    Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                                }else if (LoginBean.getCode().equals("206")){
                                    Toast.makeText(LoginActivity.this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }

    }

    private void initPop() {
        //屏幕变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

        popupView = View.inflate(mContext, R.layout.layout_check_popupwindow, null);
        popupView.findViewById(R.id.tv_saoyisao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvShowPop.setText("+86");
                basePopupWindow.dismiss();
            }
        });
        popupView.findViewById(R.id.tv_weiliao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvShowPop.setText("+81");
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
                WindowManager.LayoutParams lp = getWindow().getAttributes();
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
