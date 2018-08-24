package com.haiwai.administrator.japanhouse.fragment.comment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseFragment;
import com.haiwai.administrator.japanhouse.bean.EventBean;
import com.haiwai.administrator.japanhouse.model.HouseListBean;
import com.haiwai.administrator.japanhouse.model.LandBean;
import com.haiwai.administrator.japanhouse.presenter.TJNewHousePresenter;
import com.haiwai.administrator.japanhouse.utils.GlideReqUtils;
import com.haiwai.administrator.japanhouse.utils.MyUtils;
import com.haiwai.administrator.japanhouse.view.MyFooter;
import com.haiwai.administrator.japanhouse.view.MyHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */
public class ZuhouseFragment extends BaseFragment implements TJNewHousePresenter.HouseCallBack{

    private RecyclerView mrecycler;
    private TextView tv_refresh_time,tv_wushuju;
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
        EventBus.getDefault().register(this);
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
        initNet();
        initListener();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mRefresh(EventBean eventBean) {
        if (eventBean.getMsg().equals("refreshComment")){
            tjNewHousePresenter.getHouseList(page,"0");
            String currentDate = MyUtils.getCurrentDate();
            tv_refresh_time.setText(currentDate+getResources().getString(R.string.gengxin));
        }
    }
    private void initNet() {
        String currentDate = MyUtils.getCurrentDate();
        tv_refresh_time.setText(currentDate+getResources().getString(R.string.gengxin));
        tjNewHousePresenter.getHouseList(page,"2");
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
                tjNewHousePresenter.getHouseList(page,"2");
                String currentDate = MyUtils.getCurrentDate();
                tv_refresh_time.setText(currentDate+getResources().getString(R.string.gengxin));
                springview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                isLoadMore = true;
                page++;
                tjNewHousePresenter.getHouseList(page,"2");
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
            mLiebiaoAdapter = new LiebiaoAdapter(R.layout.item_zuijin,mRefreshData);
            mrecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false));
            mrecycler.setNestedScrollingEnabled(false);
            mrecycler.setAdapter(mLiebiaoAdapter);

        }


        mLiebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mContext,ZuHousedetailsActivity.class);
                String houseType = mRefreshData.get(position).getHouseType();
                intent.putExtra("houseId",mRefreshData.get(position).getId()+"");
                if (houseType.equals("5")) {
                    intent.putExtra("houseType", "duoceng");
                } else if (houseType.equals("4")) {
                    intent.putExtra("houseType", "xuesheng");
                } else if (houseType.equals("3")) {
                    intent.putExtra("houseType", "erceng");
                } else if (houseType.equals("2")) {
                    intent.putExtra("houseType", "bieshu");
                } else if (houseType.equals("1")) {
                    intent.putExtra("houseType", "shangpu");
                } else if (houseType.equals("0")) {
                    intent.putExtra("houseType", "bangongshi");
                }
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
            Glide.with(mContext).load(TextUtils.isEmpty(item.getVideoImgs()) ?
                    MyUtils.getSpiltText(item.getRoomImgs()) : item.getVideoImgs())
                    .apply(GlideReqUtils.getReq())
                    .into((ImageView) helper.getView(R.id.img_house));
            helper.setText(R.id.tv_house_name,isJa ? item.getTitleJpn() : item.getTitleCn());
            String area;
            if (isJa) {
                area = item.getSpecificLocationJpn();
            } else {
                area = item.getSpecificLocationCn();
            }
            if (area.length() > 5) {
                area = area.substring(0, 5) + "...";
            }
            helper.setText(R.id.tv_house_address,area);
            helper.setText(R.id.tv_house_room,isJa ? item.getDoorModelJpn() : item.getDoorModelCn());
            helper.setText(R.id.tv_house_area,isJa ? item.getAreaJpn() : item.getAreaCn());
            helper.setText(R.id.tv_price,isJa ? item.getRentJpn() : item.getRentCn());

        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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


