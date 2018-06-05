package com.example.administrator.japanhouse.fragment.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.act_feedback_et)
    EditText etMsg;
    @BindView(R.id.act_feedback_textNum)
    TextView tvTextNum;
    @BindView(R.id.act_feedback_submit)
    TextView tvSubmit;
    private ImageView back_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        initView();

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
                tvTextNum.setText(s.length() + "/140");
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
                finish();
                break;
            case R.id.act_feedback_submit:

                break;
        }
    }
}
