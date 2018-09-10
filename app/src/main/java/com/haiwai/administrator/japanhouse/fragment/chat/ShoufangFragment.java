package com.haiwai.administrator.japanhouse.fragment.chat;

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
import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseFragment;
import com.haiwai.administrator.japanhouse.bean.ManShouBean;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.fragment.comment.GaoerfuDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.HaiWaiDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.JiudianDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.NewHousedetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.OldHousedetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.OldhouseFragment;
import com.haiwai.administrator.japanhouse.fragment.comment.ShangpuDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.TudidetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.XiaoQuDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.XiezilouDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.ZhongguoDetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.comment.ZuHousedetailsActivity;
import com.haiwai.administrator.japanhouse.fragment.home.BieshudetailsActivity;
import com.haiwai.administrator.japanhouse.model.HouseListBean;
import com.haiwai.administrator.japanhouse.utils.GlideReqUtils;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.haiwai.administrator.japanhouse.utils.MyUtils;
import com.haiwai.administrator.japanhouse.utils.SharedPreferencesUtils;
import com.haiwai.administrator.japanhouse.view.MyFooter;
import com.haiwai.administrator.japanhouse.view.MyHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import static com.haiwai.administrator.japanhouse.R.id.tv_wushuju;

/**
 * Created by Administrator on 2018/1/18.
 */
public class ShoufangFragment extends BaseFragment {

    private RecyclerView mrecycler;
    private TextView tv_refresh_time;
    private TextView tvNoData;
    private SpringView springview;
    private LiebiaoAdapter mLiebiaoAdapter;
    private List<String> mList = new ArrayList();
    private List<ManShouBean.DatasBean> mRefreshData;
    private boolean isLoadMore;
    private int page = 1;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_shoufang, null);
        mrecycler = (RecyclerView) view.findViewById(R.id.Mrecycler);
        tv_refresh_time = (TextView) view.findViewById(R.id.tv_refresh_time);
        tvNoData = (TextView) view.findViewById(tv_wushuju);
        springview = (SpringView) view.findViewById(R.id.springview);
        tv_refresh_time.setVisibility(View.GONE);
        springview.setHeader(new MyHeader(mContext));
        springview.setFooter(new MyFooter(mContext));
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                page = 1;
                initNet();
                springview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                isLoadMore = true;
                page++;
                initNet();
                springview.onFinishFreshAndLoad();
            }
        });
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
        params.put("pageNo", page);
        OkGo.<ManShouBean>post(MyUrls.BASEURL + "/app/brokerhouse/selecthouselist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<ManShouBean>(mActivity, ManShouBean.class) {
                    @Override
                    public void onSuccess(Response<ManShouBean> response) {
                        final List<ManShouBean.DatasBean> datas = response.body().getDatas();
                        if (mRefreshData == null || mRefreshData.size() == 0) {
                            if (datas == null || datas.size() == 0) {
                                tvNoData.setVisibility(View.VISIBLE);
                                return;
                            }
                            mRefreshData = datas;
                            mLiebiaoAdapter = new LiebiaoAdapter(R.layout.item_zuijin, mRefreshData);
                            mrecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
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
                            mLiebiaoAdapter = new LiebiaoAdapter(R.layout.item_zuijin, mRefreshData);
                            mrecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                            mrecycler.setNestedScrollingEnabled(false);
                            mrecycler.setAdapter(mLiebiaoAdapter);
                        }

                            mLiebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    if (!TextUtils.isEmpty(datas.get(position).getHType())) {
                                        if (TextUtils.equals(datas.get(position).getHType(), "0")) { //二手房
                                            Intent intent = new Intent(mContext, OldHousedetailsActivity.class);
                                            intent.putExtra("houseId", datas.get(position).getId() + "");
                                            startActivity(intent);
                                        } else if (TextUtils.equals(datas.get(position).getHType(), "1")) { //新房
                                            Intent intent = new Intent(mContext, NewHousedetailsActivity.class);
                                            intent.putExtra("houseId", datas.get(position).getId() + "");
                                            startActivity(intent);
                                        } else if (TextUtils.equals(datas.get(position).getHType(), "2")) { //租房
                                            Intent intent = new Intent(mContext, ZuHousedetailsActivity.class);

                                            if (TextUtils.equals(datas.get(position).getShType() + "", "0")) { //办公室出租
                                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                                intent.putExtra("houseType", "bangongshi");
                                            } else if (TextUtils.equals(datas.get(position).getShType() + "", "1")) { //商铺出租
                                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                                intent.putExtra("houseType", "shangpu");
                                            } else if (TextUtils.equals(datas.get(position).getShType() + "", "2")) { //别墅
                                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                                intent.putExtra("houseType", "bieshu");
                                            } else if (TextUtils.equals(datas.get(position).getShType() + "", "3")) { //二层公寓
                                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                                intent.putExtra("houseType", "erceng");
                                            } else if (TextUtils.equals(datas.get(position).getShType() + "", "4")) { //学生公寓
                                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                                intent.putExtra("houseType", "xuesheng");
                                            } else if (TextUtils.equals(datas.get(position).getShType() + "", "5")) { //多层公寓
                                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                                intent.putExtra("houseType", "duoceng");
                                            }
                                            startActivity(intent);
                                        } else if (TextUtils.equals(datas.get(position).getHType(), "3")) {  //土地
                                            Intent intent = new Intent(mContext, TudidetailsActivity.class);
                                            intent.putExtra("houseId", datas.get(position).getId() + "");
                                            startActivity(intent);
                                        } else if (TextUtils.equals(datas.get(position).getHType(), "4")) {  //别墅
                                            Intent intent = new Intent(mContext, BieshudetailsActivity.class);
                                            intent.putExtra("houseId", datas.get(position).getId() + "");
                                            startActivity(intent);
                                        } else if (TextUtils.equals(datas.get(position).getHType(), "5")) {  //商业地产
                                            if (TextUtils.equals(datas.get(position).getShType() + "", "0")) {    //酒店
                                                Intent intent = new Intent(mContext, JiudianDetailsActivity.class);
                                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                                startActivity(intent);
                                            } else if (TextUtils.equals(datas.get(position).getShType() + "", "1")) { //高尔夫球场
                                                Intent intent = new Intent(mContext, GaoerfuDetailsActivity.class);
                                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                                startActivity(intent);
                                            } else if (TextUtils.equals(datas.get(position).getShType() + "", "2")) { //写字楼
                                                Intent intent = new Intent(mContext, XiezilouDetailsActivity.class);
                                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                                startActivity(intent);
                                            } else if (TextUtils.equals(datas.get(position).getShType() + "", "3")) { //商铺
                                                Intent intent = new Intent(mContext, ShangpuDetailsActivity.class);
                                                intent.putExtra("houseId", datas.get(position).getId() + "");
                                                startActivity(intent);
                                            }
                                        } else if (TextUtils.equals(datas.get(position).getHType(), "6")) { //中国房源
                                            Intent intent = new Intent(mContext, ZhongguoDetailsActivity.class);
                                            intent.putExtra("houseId", datas.get(position).getId() + "");
                                            startActivity(intent);
                                        } else if (TextUtils.equals(datas.get(position).getHType(), "7")) {  //海外房源
                                            Intent intent = new Intent(mContext, HaiWaiDetailsActivity.class);
                                            intent.putExtra("houseId", datas.get(position).getId() + "");
                                            startActivity(intent);
                                        } else if (TextUtils.equals(datas.get(position).getHType(), "8")) {  //找团地
                                            Intent intent = new Intent(mContext, XiaoQuDetailsActivity.class);
                                            intent.putExtra("houseId", datas.get(position).getId() + "");
                                            startActivity(intent);
                                        }

                                    }
                                }
                            });
                        }
//                        else {
//                            Toast.makeText(mContext, ManShouBean.getMsg(), Toast.LENGTH_SHORT).show();
//                        }


//                    }
                });
    }

    @Override
    protected void initLazyData() {

    }

    class LiebiaoAdapter extends BaseQuickAdapter<ManShouBean.DatasBean, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<ManShouBean.DatasBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ManShouBean.DatasBean item) {
            boolean isJa = MyUtils.isJa();
            String area;
            if (MyApplication.isJapanese()) {
                area = item.getAddressJpn();
            } else {
                area = item.getAddressCn();
            }
            String price = MyApplication.isJapanese() ? item.getPriceJpn() : item.getPriceCn();
            helper.setText(R.id.tv_house_name, isJa ? item.getTitleJpn() : item.getTitleCn());
            helper.setText(R.id.tv_house_address, MyUtils.getSubText(area, price));
            helper.setText(R.id.tv_house_room, isJa ? item.getDoorModelJpn() : item.getDoorModelCn());
            helper.setText(R.id.tv_house_area, isJa ? item.getAreaJpn() : item.getAreaCn());
            helper.setText(R.id.tv_price, isJa ? item.getPriceJpn() : item.getPriceCn());
            Glide.with(MyApplication.getGloableContext())
                    .load(TextUtils.isEmpty(item.getVideoImageUrl()) ? MyUtils.getSpiltText(item.getImageUrl()) : item.getVideoImageUrl())
                    .apply(GlideReqUtils.getReq())
                    .into((ImageView) helper.getView(R.id.img_house));
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


