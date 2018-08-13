package com.haiwai.administrator.japanhouse.fragment.comment;

import android.view.View;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.YangBaseFragment;

/**
 * Created by Administrator on 2018/1/18.
 */
public class BannerFragment extends YangBaseFragment {



    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_banner, null);
        return view;
    }

    @Override
    protected void initData() {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        super.onPause();
           }
}


