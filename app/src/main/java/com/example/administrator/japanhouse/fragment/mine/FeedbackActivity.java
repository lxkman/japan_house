package com.example.administrator.japanhouse.fragment.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.model.NoDataBean;
import com.example.administrator.japanhouse.presenter.FeedBackPresenter;
import com.example.administrator.japanhouse.utils.TUtils;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener, FeedBackPresenter.FeedBackCallBack {

    @BindView(R.id.act_feedback_et)
    EditText etMsg;
    @BindView(R.id.act_feedback_textNum)
    TextView tvTextNum;
    @BindView(R.id.act_feedback_submit)
    TextView tvSubmit;
    private ImageView back_img;

    private FeedBackPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        initView();

        presenter = new FeedBackPresenter(this, this);

        etMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 140) {
                    etMsg.setText(s.toString().substring(0, 140));
                    etMsg.setSelection(140);
                    return;
                }
                tvTextNum.setText((140 - s.length()) + "/140");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void initView() {
        back_img = (ImageView) findViewById(R.id.back_img);
        back_img.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                finish();
                break;
            case R.id.act_feedback_submit:
                presenter.requestFeedBack(etMsg.getText().toString());
                break;
        }
    }

    @Override
    public void requestFeedBack(Response<NoDataBean> response) {
        if (response != null && response.body() != null && response.body().getCode() != null && response.body().getCode().equals("200")) {
            TUtils.showFail(this, response.body().getMsg().toString());
            finish();
        }
    }
}
