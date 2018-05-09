package com.example.administrator.japanhouse.login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.japanhouse.MainActivity;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;

import org.zackratos.ultimatebar.UltimateBar;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class LoginActivity extends BaseActivity {

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
                finish();
                SharedPreferencesUtils.getInstace(LoginActivity.this).setStringPreference("uid", "1");
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
                 HashMap<String, Boolean> hm = new HashMap<>();
                //会话类型 以及是否聚合显示
                hm.put(Conversation.ConversationType.PRIVATE.getName(), false);
//        hashMap.put(Conversation.ConversationType.PUSH_SERVICE.getName(),true);
//        hashMap.put(Conversation.ConversationType.SYSTEM.getName(),true);
                RongIM.getInstance().startConversationList(this, hm);
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
                startActivity(new Intent(LoginActivity.this, BindPhoneActivity.class));
                break;
            case R.id.tv_show_pop:
                initPop();
                basePopupWindow.showAsDropDown(view);
                break;
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

}
