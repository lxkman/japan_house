package com.example.administrator.japanhouse.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.MyApplication;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.model.FreeApartmentBean;
import com.example.administrator.japanhouse.model.NoDataBean;
import com.example.administrator.japanhouse.presenter.FreeApartmentPresenter;
import com.example.administrator.japanhouse.view.BaseDialog;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * admin  2018/6/6
 */
public class FreeApartmentDetailsActivity extends BaseActivity implements FreeApartmentPresenter.FreeApartmentCallBack {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.act_freeApart_icon)
    ImageView icon;
    @BindView(R.id.act_freeApart_title_ja)
    TextView titleJa;
    @BindView(R.id.act_freeApart_time_ja)
    TextView timeJa;
    @BindView(R.id.act_freeApart_num_ja)
    TextView numJa;
    @BindView(R.id.act_freeApart_apply_ja)
    Button applyJa;
    @BindView(R.id.act_freeApart_layout_ja)
    LinearLayout layoutJa;
    @BindView(R.id.act_freeApart_title_zh)
    TextView titleZh;
    @BindView(R.id.act_freeApart_time_zh)
    TextView timeZh;
    @BindView(R.id.act_freeApart_num_zh)
    TextView numZh;
    @BindView(R.id.act_freeApart_apply_zh)
    Button applyZh;
    @BindView(R.id.act_freeApart_layout_zh)
    LinearLayout layoutZh;
    @BindView(R.id.act_freeApart_details)
    TextView details;
    @BindView(R.id.act_freeApart_range)
    TextView range;
    @BindView(R.id.act_freeApart_phone)
    TextView phone;
    @BindView(R.id.act_freeApart_houseBs)
    TextView houseBs;
    @BindView(R.id.act_freeApart_date)
    TextView date;
    @BindView(R.id.act_freeApart_address)
    TextView address;
    @BindView(R.id.act_freeApart_managerIcon)
    ImageView managerIcon;
    @BindView(R.id.act_freeApart_managerName)
    TextView managerName;
    @BindView(R.id.act_freeApart_wChat)
    TextView wChat;
    @BindView(R.id.act_freeApart_managerPhone)
    TextView managerPhone;

    private FreeApartmentPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeapart_details);
        ButterKnife.bind(this);
        presenter = new FreeApartmentPresenter(this, this);
        initView();
    }

    private void initView() {
        if (MyApplication.isJapanese()) {
            layoutJa.setVisibility(View.VISIBLE);
            layoutZh.setVisibility(View.GONE);
        } else {
            layoutZh.setVisibility(View.VISIBLE);
            layoutJa.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.back_img, R.id.act_freeApart_wChat, R.id.act_freeApart_managerPhone, R.id.act_freeApart_apply_ja, R.id.act_freeApart_apply_zh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            case R.id.act_freeApart_wChat:
                break;
            case R.id.act_freeApart_managerPhone:
                break;

            case R.id.act_freeApart_apply_ja:
            case R.id.act_freeApart_apply_zh:

                break;
        }
    }

    private void showSignupDialog(final int actionId) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_checkroom)
                .setPaddingdp(20, 0, 20, 0)
                .setGravity(Gravity.CENTER)
                .setAnimation(R.style.bottom_tab_style)
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                .isOnTouchCanceled(true)
                .builder();

        final EditText etName = dialog.getView(R.id.et_singup_name);
        final EditText etPhone = dialog.getView(R.id.et_singup_phone);

        dialog.getView(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getView(R.id.tv_singup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etName.getText().toString()) && !TextUtils.isEmpty(etPhone.getText().toString())) {
                    presenter.getSignUp(1, actionId, etPhone.getText().toString(), etName.getText().toString());
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    private void ShowCallDialog(final String tel) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.call_layout)
                .setPaddingdp(0, 10, 0, 10)
                .setGravity(Gravity.CENTER)
                .setAnimation(R.style.bottom_tab_style)
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                .isOnTouchCanceled(false)
                .builder();
        dialog.show();
        TextView text_sure = dialog.getView(R.id.text_sure);
        final TextView tv_content = dialog.getView(R.id.tv_content);
        tv_content.setText(tel);
        TextView text_pause = dialog.getView(R.id.text_pause);

        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent dialIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + tel));
                startActivity(dialIntent);
            }
        });

        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void getFreeApartmentList(Response<FreeApartmentBean> response) {

    }

    @Override
    public void getSignUp(Response<NoDataBean> response) {

    }
}
