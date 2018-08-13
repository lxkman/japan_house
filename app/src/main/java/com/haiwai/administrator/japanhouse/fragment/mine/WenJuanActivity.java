package com.haiwai.administrator.japanhouse.fragment.mine;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WenJuanActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.recycler_question)
    RecyclerView recyclerQuestion;
    @BindView(R.id.activity_my_data)
    LinearLayout activityMyData;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wen_juan);
        ButterKnife.bind(this);
        backImg.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        recyclerQuestion.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("");
        }
        QuestionAdapter questionAdapter = new QuestionAdapter(R.layout.item_question, list);
        recyclerQuestion.setAdapter(questionAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img:
                finish();
                break;
            case R.id.tv_submit:
                finish();
                break;
        }
    }

    private class QuestionAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public QuestionAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            RecyclerView recyclerView = helper.getView(R.id.recycler);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list.add("");
            }
            AnswerAdapter answerAdapter=new AnswerAdapter(R.layout.item_answer,list);
            recyclerView.setLayoutManager(new LinearLayoutManager(WenJuanActivity.this));
            recyclerView.setAdapter(answerAdapter);
        }
    }

    private class AnswerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public AnswerAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            LinearLayout ll_rootv=helper.getView(R.id.ll_root);
            final CheckBox cb_answer=helper.getView(R.id.cb_answer);
            if (helper.getAdapterPosition()==0){
                cb_answer.setChecked(true);
            }
            ll_rootv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cb_answer.setChecked(!cb_answer.isChecked());
                }
            });
        }
    }
}
