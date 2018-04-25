package com.example.administrator.japanhouse.fragment.home.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.japanhouse.R;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_beak;
    private RelativeLayout liner;
    private EditText ed_wen_title;
    private EditText ed_wen_content;
    private Button tijiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initView();
    }

    private void initView() {
        img_beak = (ImageView) findViewById(R.id.img_beak);
        ed_wen_title = (EditText) findViewById(R.id.ed_wen_title);
        ed_wen_content = (EditText) findViewById(R.id.ed_wen_content);
        tijiao = (Button) findViewById(R.id.tijiao);
        tijiao.setOnClickListener(this);
        img_beak.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_beak:
                finish();
                break;
            case R.id.tijiao:
                Toast.makeText(QuizActivity.this,"提交",Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
