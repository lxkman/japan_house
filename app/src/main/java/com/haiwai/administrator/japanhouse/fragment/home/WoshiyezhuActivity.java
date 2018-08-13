package com.haiwai.administrator.japanhouse.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.bean.EventBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WoshiyezhuActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_message_iv)
    ImageView titleMessageIv;
    @BindView(R.id.zushou_tv)
    TextView zushouTv;
    @BindView(R.id.fangjiaditu_tv)
    TextView fangjiadituTv;
    @BindView(R.id.yezhubaike_tv)
    TextView yezhubaikeTv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woshiyezhu);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        LiebiaoAdapter liebiaoAdapter = new LiebiaoAdapter(R.layout.item_wsyz_layout,list);
        recyclerView.setAdapter(liebiaoAdapter);

    }

    private class LiebiaoAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

        public LiebiaoAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    @OnClick({R.id.title_back_iv, R.id.title_message_iv, R.id.zushou_tv, R.id.fangjiaditu_tv, R.id.yezhubaike_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_message_iv:
                EventBean eventBean = new EventBean("chat");
                EventBus.getDefault().postSticky(eventBean);
                finish();
                break;
            case R.id.zushou_tv:
                break;
            case R.id.fangjiaditu_tv:
                break;
            case R.id.yezhubaike_tv:
                break;
        }
    }
}
