package com.example.administrator.japanhouse.fragment.comment;

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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.model.HouseListBean;
import com.example.administrator.japanhouse.model.LandBean;
import com.example.administrator.japanhouse.presenter.TJNewHousePresenter;
import com.example.administrator.japanhouse.utils.MyUtils;
import com.example.administrator.japanhouse.view.MyFooter;
import com.example.administrator.japanhouse.view.MyHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */
public class OldhouseFragment extends BaseFragment implements TJNewHousePresenter.HouseCallBack{

    private RecyclerView mrecycler;
    private TextView tv_refresh_time;
    private TextView tv_wushuju;
    private LiebiaoAdapter mLiebiaoAdapter;
    private List<String> mList=new ArrayList();
    private TJNewHousePresenter tjNewHousePresenter;
    private List<HouseListBean.DatasBean> mRefreshData;
    private SpringView springview;
    private boolean isLoadMore;
    private int page = 1;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_shoufang, null);
        mrecycler= (RecyclerView) view.findViewById(R.id.Mrecycler);
        springview= (SpringView) view.findViewById(R.id.springview);
        tv_refresh_time= (TextView) view.findViewById(R.id.tv_refresh_time);
        tv_wushuju= (TextView) view.findViewById(R.id.tv_wushuju);
        tjNewHousePresenter=new TJNewHousePresenter(mActivity,this);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListener();
        initNet();
    }

    private void initNet() {
        String currentDate = MyUtils.getCurrentDate();
        tv_refresh_time.setText(currentDate+getResources().getString(R.string.gengxin));
        tjNewHousePresenter.getHouseList(page,"0");
    }
    private void initListener() {
        //        mSpringview.setType(SpringView.Type.FOLLOW);
        springview.setHeader(new MyHeader(mContext));
        springview.setFooter(new MyFooter(mContext));
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                page = 1;
                tjNewHousePresenter.getHouseList(page,"0");
                String currentDate = MyUtils.getCurrentDate();
                tv_refresh_time.setText(currentDate+getResources().getString(R.string.gengxin));
                springview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                isLoadMore = true;
                page++;
                tjNewHousePresenter.getHouseList(page,"0");
                springview.onFinishFreshAndLoad();
            }
        });
    }



    @Override
    protected void initLazyData() {

    }

    @Override
    public void getHouseList(Response<HouseListBean> response) {
        HouseListBean body = response.body();
         List<HouseListBean.DatasBean>  datas = body.getDatas();
        if (mRefreshData == null || mRefreshData.size() == 0) {
            if (datas == null || datas.size() == 0) {
                tv_wushuju.setVisibility(View.VISIBLE);
                return;
            }
            mRefreshData = datas;
            mLiebiaoAdapter = new LiebiaoAdapter(R.layout.item_zuijin,mRefreshData);
            mrecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false));
            mrecycler.setNestedScrollingEnabled(false);
            mrecycler.setAdapter(mLiebiaoAdapter);
        } else {
            if (datas == null || datas.size() == 0) {
                Toast.makeText(mContext, R.string.meiyougengduoshujule, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isLoadMore) {
                mRefreshData = datas;
                Toast.makeText(mContext, R.string.shuaxinchenggong, Toast.LENGTH_SHORT).show();
            } else {
                mRefreshData.addAll(datas);
            }
            mLiebiaoAdapter.notifyDataSetChanged();

        }
        mLiebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mContext,OldHousedetailsActivity.class);
                intent.putExtra("houseId",mRefreshData.get(position).getId()+"");
                startActivity(intent);
            }
        });
    }

    @Override
    public void getLand(Response<LandBean> response) {

    }

    class LiebiaoAdapter extends BaseQuickAdapter<HouseListBean.DatasBean, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<HouseListBean.DatasBean> data) {
            super(layoutResId,data);
        }



        @Override
        protected void convert(BaseViewHolder helper, HouseListBean.DatasBean item) {
            boolean isJa = MyUtils.isJa();
            Glide.with(mContext).load(item.getRoomImgs()).into((ImageView) helper.getView(R.id.img_house));
            helper.setText(R.id.tv_house_name,isJa ? item.getTitleJpn() : item.getTitleCn());
            helper.setText(R.id.tv_house_address,isJa ? item.getSpecificLocationJpn() : item.getSpecificLocationCn());
            helper.setText(R.id.tv_house_room,isJa ? item.getDoorModelJpn() : item.getDoorModelCn());
            helper.setText(R.id.tv_house_area,isJa ? item.getAreaJpn() : item.getAreaCn());
            helper.setText(R.id.tv_price,isJa ? item.getPriceJpn() : item.getPriceCn());

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


