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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.bean.SuccessBean;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.haiwai.administrator.japanhouse.utils.MyUtils;
import com.haiwai.administrator.japanhouse.utils.SendSmsTimerUtils;
import com.haiwai.administrator.japanhouse.utils.TUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindPhoneActivity extends BaseActivity {

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
    private String QuNumber;
    private String loginType;
    private String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        ButterKnife.bind(this);
        loginType = getIntent().getStringExtra("loginType");
        uId = getIntent().getStringExtra("uId");
    }

    @OnClick({R.id.back_img, R.id.tv_get_code, R.id.btn_find_pass,R.id.check_quyu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
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
                            Toast.makeText(mContext, "请选择正确的区号", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else {
                        QuNumber = "2";//国内
                        if (checkQuyu.getText().equals("+81")) {
                            Toast.makeText(mContext, "请选择正确的区号", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    getCode();//验证码接口
                }
                break;
            case R.id.btn_find_pass:
                BindPhone();
//                startActivity(new Intent(BindPhoneActivity.this, MainActivity.class));
//                HashMap<String, Boolean> hashMap = new HashMap<>();
//                //会话类型 以及是否聚合显示
//                hashMap.put(Conversation.ConversationType.PRIVATE.getName(), false);
////        hashMap.put(Conversation.ConversationType.PUSH_SERVICE.getName(),true);
////        hashMap.put(Conversation.ConversationType.SYSTEM.getName(),true);
//                RongIM.getInstance().startConversationList(this, hashMap);
                break;
            case R.id.check_quyu:
                initPop();
                basePopupWindow.showAsDropDown(view);
                break;
        }
    }

    private void BindPhone() {
        HttpParams params = new HttpParams();
        params.put("loginType",loginType);
        params.put("uId",uId);
        params.put("phone",edtPhone.getText().toString());
        params.put("msg",edtYanzhengCode.getText().toString());
        params.put("newPassword",edtPassSure.getText().toString());
        OkGo.<SuccessBean>post(MyUrls.BASEURL + "/app/user/bingdingphone")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SuccessBean>(this, SuccessBean.class) {
                    @Override
                    public void onSuccess(Response<SuccessBean> response) {
                        int code = response.code();
                        SuccessBean successBean = response.body();
                        if (successBean.getCode().equals("200")){
                            startActivity(new Intent(BindPhoneActivity.this,LoginActivity.class));
                            finish();
                            Toast.makeText(BindPhoneActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(BindPhoneActivity.this, successBean.getCode(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
        params.put("vPhone",edtPhone.getText().toString());
        OkGo.<SuccessBean>post(MyUrls.BASEURL + "/app/send/msg/sendmsg")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SuccessBean>(this, SuccessBean.class) {
                    @Override
                    public void onSuccess(Response<SuccessBean> response) {
                        int code = response.code();
                        SuccessBean successBean = response.body();
                        if (successBean.getCode().equals("200")){
                            SendSmsTimerUtils.sendSms(tvGetCode, R.color.shihuangse, R.color.shihuangse);
                            TUtils.showFail(BindPhoneActivity.this,getString(R.string.fasongchegngong));
                        }else if (successBean.getCode().equals("-1")){
                            TUtils.showFail(BindPhoneActivity.this,getString(R.string.fasongshibai));
                        }else if (successBean.getCode().equals("500")){
                            TUtils.showFail(BindPhoneActivity.this,getString(R.string.neibufuwuqicuowu));
                        }else {
                            TUtils.showFail(BindPhoneActivity.this,successBean.getMsg());
                        }
                    }
                });
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
}
