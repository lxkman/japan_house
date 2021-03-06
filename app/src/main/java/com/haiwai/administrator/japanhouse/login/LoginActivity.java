package com.haiwai.administrator.japanhouse.login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.UMLoginActivity;
import com.haiwai.administrator.japanhouse.bean.LoginBean;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.im.RcConnect;
import com.haiwai.administrator.japanhouse.model.NoDataBean;
import com.haiwai.administrator.japanhouse.presenter.ThirdLoginPresenter;
import com.haiwai.administrator.japanhouse.utils.CacheUtils;
import com.haiwai.administrator.japanhouse.utils.Constants;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.haiwai.administrator.japanhouse.utils.MyUtils;
import com.haiwai.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.haiwai.administrator.japanhouse.utils.TUtils;
import com.linecorp.linesdk.LineProfile;
import com.linecorp.linesdk.auth.LineLoginApi;
import com.linecorp.linesdk.auth.LineLoginResult;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.umeng.socialize.UMShareAPI;

import org.zackratos.ultimatebar.UltimateBar;

import java.util.HashMap;

import butterknife.BindView;


import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class LoginActivity extends UMLoginActivity implements ThirdLoginPresenter.ThirdLoginCallBack {
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

    private ThirdLoginPresenter loginPresenter;

    private String lineId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //拉伸图片覆盖标题栏
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar(false);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        boolean ja = MyUtils.isJa();
        if (!ja) {
            tvShowPop.setText("+86");
        } else {
            tvShowPop.setText("+81");
        }

        loginPresenter = new ThirdLoginPresenter(this, this);
    }

    @OnClick({R.id.back_img, R.id.tv_register, R.id.tv_forget_pass, R.id.btn_login, R.id.img_weixin, R.id.img_weibo, R.id.img_qq, R.id.img_line, R.id.tv_show_pop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                //                HashMap<String, Boolean> hashMap = new HashMap<>();
                //                hashMap.put(Conversation.ConversationType.PRIVATE.getName(), false);
                //                RongIM.getInstance().startConversationList(this, hashMap);
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
                //                startActivity(new Intent(LoginActivity.this, BindPhoneActivity.class));
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
            TUtils.showFail(this,getResources().getString(R.string.phonenumber));
            return;
        } else if (!MyUtils.isMobileNO(edtPhone.getText().toString())) {
            TUtils.showFail(this,getResources().getString(R.string.shoujihaogeshierror));
            return;
        } else if (!TextUtils.isEmpty(edtPhone.getText().toString()) && MyUtils.isMobileNO(edtPhone.getText().toString())) {
            String phone = edtPhone.getText().toString();
            String substring = phone.substring(0, 3);
            if (substring.equals("050") || substring.equals("060") || substring.equals("070") || substring.equals("080") || substring.equals("090")) {
                if (tvShowPop.getText().equals("+86")) {
                    TUtils.showFail(this,getResources().getString(R.string.qingxuanzezhengquequhao));
                    return;
                }
            } else {
                if (tvShowPop.getText().equals("+81")) {
                    TUtils.showFail(this,getResources().getString(R.string.qingxuanzezhengquequhao));
                    return;
                }
            }
            if (TextUtils.isEmpty(edtPass.getText().toString())) {
                TUtils.showFail(this,getResources().getString(R.string.pass));
                return;
            } else if (!MyUtils.isPswRuleNO(edtPass.getText().toString())) {
                TUtils.showFail(this,getResources().getString(R.string.qingshurushuzihezimumima));
            } else {
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
                                LoginBean loginBean = response.body();
                                //                                Log.d("LoginActivity", LoginBean.getCode()+"-------------");
                                if (loginBean.getCode().equals("200")) {
                                    SharedPreferencesUtils.getInstace(LoginActivity.this).setStringPreference("uid", loginBean.getDatas().getId() + "");
                                    SharedPreferencesUtils.getInstace(LoginActivity.this).setStringPreference("token", loginBean.getDatas().getToken() + "");

                                    RcConnect.rongCloudConection(loginBean.getDatas().getRongCloudToken());

                                    CacheUtils.put(Constants.USERINFO, loginBean.getDatas());
                                    TUtils.showFail(LoginActivity.this, getResources().getString(R.string.dengluchenggong));

                                    HashMap<String, Boolean> hm = new HashMap<>();
                                    //会话类型 以及是否聚合显示
                                    hm.put(Conversation.ConversationType.PRIVATE.getName(), false);
                                    //        hashMap.put(Conversation.ConversationType.PUSH_SERVICE.getName(),true);
                                    //        hashMap.put(Conversation.ConversationType.SYSTEM.getName(),true);
                                    String type = getIntent().getStringExtra("type");
                                    if (!TextUtils.isEmpty(type) && type.equals("daikuan")){
                                        finish();
                                        return;
                                    }
                                    RongIM.getInstance().startConversationList(LoginActivity.this, hm);
                                    finish();
                                } else if (loginBean.getCode().equals("-1")) {
                                    TUtils.showFail(LoginActivity.this, getResources().getString(R.string.denglushibai));
                                } else if (loginBean.getCode().equals("206")) {
                                    TUtils.showFail(LoginActivity.this, getResources().getString(R.string.yonghuminghuomimacuowu));
                                } else {
                                    TUtils.showFail(LoginActivity.this, loginBean.getMsg());
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

        popupView = View.inflate(this, R.layout.layout_check_popupwindow, null);
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
                    LineProfile lineProfile = result.getLineProfile();
                    lineId = lineProfile.getUserId();
                    loginPresenter.setThirdLogin(3, lineProfile.getUserId());
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


    @Override
    public void getThirdLogin(Response<LoginBean> response) {
        if (response != null && response.body() != null && response.body().getCode() != null) {
            if (TextUtils.equals(response.body().getCode(), "200")) {
                if (response.body().getDatas() != null) {
                    SharedPreferencesUtils.getInstace(LoginActivity.this).setStringPreference("uid", response.body().getDatas().getId() + "");
                    SharedPreferencesUtils.getInstace(LoginActivity.this).setStringPreference("token", response.body().getDatas().getToken() + "");

                    RcConnect.rongCloudConection(response.body().getDatas().getRongCloudToken());

                    CacheUtils.put(Constants.USERINFO, response.body().getDatas());
                    TUtils.showFail(LoginActivity.this, getResources().getString(R.string.dengluchenggong));

                    HashMap<String, Boolean> hm = new HashMap<>();
                    //会话类型 以及是否聚合显示
                    hm.put(Conversation.ConversationType.PRIVATE.getName(), false);
                    //        hashMap.put(Conversation.ConversationType.PUSH_SERVICE.getName(),true);
                    //        hashMap.put(Conversation.ConversationType.SYSTEM.getName(),true);
                    RongIM.getInstance().startConversationList(LoginActivity.this, hm);
                    finish();
                }
            } else if (TextUtils.equals(response.body().getCode(), "-1")) {
                Intent intent = new Intent(LoginActivity.this, BindPhoneActivity.class);
                intent.putExtra("uId", lineId);
                intent.putExtra("loginType", 3);
                startActivity(intent);
            }
        }
    }

    @Override
    public void bindThirdLogin(Response<NoDataBean> response) {

    }
}
