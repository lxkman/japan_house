package com.haiwai.administrator.japanhouse.fragment.mine.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseFragment;
import com.haiwai.administrator.japanhouse.bean.FragEventBug;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/**
 * Created by Mr赵 on 2018/4/17.
 */
public class Calcul_Detil_Fragment extends BaseFragment implements View.OnClickListener {
    private Button js_cxjs;
    private TextView js_sf;
    private TextView js_dkze;
    private TextView js_lxzj;
    private TextView js_yg;
    private boolean flag=true;
    private String shoufu;
    private String daikuan;
    private String lixi;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calcalator_detils, container, false);
        js_sf = (TextView) view.findViewById(R.id.js_sf);
        js_dkze = (TextView) view.findViewById(R.id.js_dkze);
        js_lxzj = (TextView) view.findViewById(R.id.js_lxzj);
        js_yg = (TextView) view.findViewById(R.id.js_yg);
        js_cxjs = (Button) view.findViewById(R.id.js_cxjs);
        return view;
    }
    @Override
    protected void initLazyData() {

    }

    @Override
    protected void lazyLoad() {
        if(flag){
            //注册
            EventBus.getDefault().register(this);
            flag=false;
        }
        js_sf.setText(shoufu+"");
        js_dkze.setText(daikuan+"");
        js_lxzj.setText(lixi+"");
        js_cxjs.setOnClickListener(this);



    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void fangfa(FragEventBug eveen){
        shoufu = eveen.getShoufu();
        daikuan = eveen.getDaikuan();
        lixi = eveen.getLixi();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.js_cxjs:
                getActivity().finish();
                break;
        }

    }
}
