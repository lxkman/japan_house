package com.example.administrator.japanhouse.fragment.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.bean.ManZuBean;
import com.example.administrator.japanhouse.callback.DialogCallback;
import com.example.administrator.japanhouse.fragment.comment.NewHousedetailsActivity;
import com.example.administrator.japanhouse.utils.MyUrls;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.example.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */
public class ZufangFragment extends BaseFragment {

    private RecyclerView mrecycler;
    private LiebiaoAdapter mLiebiaoAdapter;
    private List<String> mList=new ArrayList();
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_zufang, null);
        mrecycler= (RecyclerView) view.findViewById(R.id.Mrecycler);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initNet();
    }

    private void initNet() {
        String brokerId = SharedPreferencesUtils.getInstace(mContext).getStringPreference("brokerId", "");
        HttpParams params = new HttpParams();
        params.put("brokerId", brokerId);
        params.put("pageNo", 1);
        OkGo.<ManZuBean>post(MyUrls.BASEURL + "/app/brokerhouse/brokerhousezflist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<ManZuBean>(mActivity, ManZuBean.class) {
                    @Override
                    public void onSuccess(Response<ManZuBean> response) {
                        int code = response.code();
                        final ManZuBean ManZuBean = response.body();
                        if (ManZuBean==null){
                            return;
                        }
                        String code1 = ManZuBean.getCode();
                        List<com.example.administrator.japanhouse.bean.ManZuBean.DatasBean> datas = ManZuBean.getDatas();
                        if (datas==null){
                            return;
                        }
                        if (code1.equals("200")) {
                            if (mLiebiaoAdapter == null) {
                                mLiebiaoAdapter = new LiebiaoAdapter(R.layout.item_zufang,datas);
                            }
                            mrecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false));
                            mrecycler.setNestedScrollingEnabled(false);
                            mrecycler.setAdapter(mLiebiaoAdapter);
                            mLiebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent=new Intent(mContext,NewHousedetailsActivity.class);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            Toast.makeText(mContext, ManZuBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }




    @Override
    protected void initLazyData() {

    }



    class LiebiaoAdapter extends BaseQuickAdapter<ManZuBean.DatasBean, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<ManZuBean.DatasBean> data) {
            super(layoutResId,data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ManZuBean.DatasBean item) {
            boolean isJa = MyUtils.isJa();
            helper.setText(R.id.tv_house_name, isJa ? item.getTitleJpn() : item.getTitleCn());
            helper.setText(R.id.tv_house_address, isJa ? item.getSpecificLocationJpn() : item.getSpecificLocationCn());
            helper.setText(R.id.tv_house_room, isJa ? item.getDoorModelJpn() : item.getDoorModelCn());
            helper.setText(R.id.tv_house_area, isJa ? item.getAreaJpn() : item.getAreaCn());
            helper.setText(R.id.tv_price, isJa ? item.getPriceJpn() : item.getPriceCn());
            Glide.with(mContext).load(item.getRoomImgs()).into((ImageView) helper.getView(R.id.img_house));
        }
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


