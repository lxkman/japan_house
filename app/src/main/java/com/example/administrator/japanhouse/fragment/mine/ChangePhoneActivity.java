package com.example.administrator.japanhouse.fragment.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.bean.SuccessBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.model.UserInfo;
import com.example.administrator.japanhouse.utils.CacheUtils;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.example.administrator.japanhouse.utils.SendSmsTimerUtils;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.example.administrator.japanhouse.utils.TUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePhoneActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.activity_zui_jin)
    LinearLayout activityZuiJin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        ButterKnife.bind(this);
        backImg.setOnClickListener(this);
        tvGetcode.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.tv_getcode:
                if (!TextUtils.isEmpty(edtPhone.getText().toString())) {
                    SendSmsTimerUtils.sendSms(tvGetcode, R.color.shihuangse, R.color.shihuangse);
                } else {
                    TUtils.showFail(this, getString(R.string.get_code_fail));
                }
                break;
            case R.id.btn_next:
                if (!MyUtils.isMobileNO(edtPhone.getText().toString())){
                    Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(edtCode.getText().toString())){
                    Toast.makeText(mContext, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    initNet();
                }

                break;
        }
    }

    private void initNet() {
      String  token = SharedPreferencesUtils.getInstace(this).getStringPreference("token", "");

        HttpParams params = new HttpParams();
        params.put("token", token);
        params.put("newPhone",edtPhone.getText().toString());
        params.put("codeMsg","1234");
        OkGo.<SuccessBean>post(MyUrls.BASEURL + "/app/user/replacephone")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<SuccessBean>(ChangePhoneActivity.this, SuccessBean.class) {
                    @Override
                    public void onSuccess(Response<SuccessBean> response) {
                        int code = response.code();
                        final SuccessBean SuccessBean = response.body();
                        if (SuccessBean == null) {
                            return;
                        }
                        if (SuccessBean.getCode().equals("200")){
                            Toast.makeText(ChangePhoneActivity.this, "换绑成功", Toast.LENGTH_SHORT).show();

                            UserInfo.DatasBean.UserBean userBean = CacheUtils.get(Constants.P_USERINFO);
                            userBean.setPhone(edtPhone.getText().toString());
                            CacheUtils.put(Constants.P_USERINFO, userBean);

                            EventBus.getDefault().post(new EventBean(Constants.EVENT_USERINFO));

                            finish();
                        }else {
                            Toast.makeText(ChangePhoneActivity.this, SuccessBean.getMsg()+"", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}
