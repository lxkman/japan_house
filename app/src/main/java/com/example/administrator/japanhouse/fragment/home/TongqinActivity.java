package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.HomeItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TongqinActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.location_rl)
    RelativeLayout locationRl;
    @BindView(R.id.type_recycler)
    RecyclerView typeRecycler;
    @BindView(R.id.time_tv)
    TextView timeTv;
    @BindView(R.id.start_find_tv)
    TextView startFindTv;
    @BindView(R.id.location_tv)
    TextView locationTv;
    private int[] itemPic = {R.drawable.buxing_iv, R.drawable.qixing_iv, R.drawable.gongjiao_iv,
            R.drawable.ditie_iv, R.drawable.zijia_iv};
    private OptionsPickerView pvCustomOptions;
    private List<String> timeList;
    //    private int type;
    private LocationClient mLocClient;
    private double mylongitude;
    private double mylatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongqin);
        ButterKnife.bind(this);
        //        type = getIntent().getIntExtra("type", 0);
        initView();
        initLocation();
    }

    private void initLocation() {
        mLocClient = new LocationClient(mContext);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setNeedDeviceDirect(true);// 设置返回结果包含手机的方向
        option.setOpenGps(true);
        option.setAddrType("all");// 返回的定位结果包含地址信息
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
        option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        option.setIsNeedLocationPoiList(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
        mLocClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                mylongitude = bdLocation.getLongitude();
                mylatitude = bdLocation.getLatitude();
                String addrStr = bdLocation.getAddrStr();
                locationTv.setText(addrStr);
            }
        });
    }

    /*
    * 步行：80m/mim
      骑行：400m/mim
      公交：800m/mim
      自驾：1000m/mim
      地铁：1500m/mim
    * */
    private void initView() {
        timeList = new ArrayList<>();
        timeList.add("15");
        timeList.add("30");
        timeList.add("45");
        timeList.add("60");
        timeList.add("75");
        timeList.add("90");

        final List<HomeItemBean> list = new ArrayList<>();
        list.add(new HomeItemBean(false, itemPic[0], getString(R.string.buxing)));
        list.add(new HomeItemBean(false, itemPic[1], getString(R.string.qixing)));
        list.add(new HomeItemBean(false, itemPic[2], getString(R.string.gongjiao)));
        list.add(new HomeItemBean(false, itemPic[3], getString(R.string.ditie)));
        list.add(new HomeItemBean(true, itemPic[4], getString(R.string.zijia)));

        typeRecycler.setNestedScrollingEnabled(false);
        typeRecycler.setLayoutManager(new GridLayoutManager(mContext, 5));
        final TypeAdapter typeAdapter = new TypeAdapter(R.layout.item_tongqin_type, list);
        typeRecycler.setAdapter(typeAdapter);
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < list.size(); i++) {
                    if (position == i) {
                        list.get(i).setChecked(true);
                    } else {
                        list.get(i).setChecked(false);
                    }
                }
                typeAdapter.notifyDataSetChanged();
            }
        });
    }

    private void go() {
        finish();
        startActivity(new Intent(mContext, ZufangListActivity.class));
    }

    private class TypeAdapter extends BaseQuickAdapter<HomeItemBean, BaseViewHolder> {

        public TypeAdapter(int layoutResId, @Nullable List<HomeItemBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeItemBean item) {
            if (item.isChecked()) {
                helper.getView(R.id.item_check_iv).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.item_check_iv).setVisibility(View.GONE);
            }
            helper.setImageResource(R.id.item_pic_iv, item.getImg());
            helper.setText(R.id.item_content_tv, item.getTitle());
        }
    }

    private void initCustomOptionPicker(final List<String> data) {
        pvCustomOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = data.get(options1);
                timeTv.setText(tx);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        final TextView tvCancle = (TextView) v.findViewById(R.id.tv_cancle);

                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();
                            }
                        });

                        tvCancle.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });
                    }
                })
                .setSelectOptions(3)//默认选中项
                .setContentTextSize(20)//设置滚轮文字大小
                .setBgColor(getResources().getColor(R.color.white))
                .setTextColorOut(getResources().getColor(R.color.text_black))
                .setDividerColor(getResources().getColor(R.color.tab_text_normal_color))
                .setTextColorCenter(getResources().getColor(R.color.text_black)) //设置选中项文字颜色
                .build();
        pvCustomOptions.setPicker(data);//添加数据

    }

    @OnClick({R.id.title_back_iv, R.id.location_rl, R.id.time_tv, R.id.start_find_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.location_rl:
                startActivityForResult(new Intent(mContext, TongqinSearchActivity.class), 0);
                break;
            case R.id.time_tv:
                initCustomOptionPicker(timeList);
                pvCustomOptions.show();
                break;
            case R.id.start_find_tv:
                go();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            String address = data.getStringExtra("address");
            if (TextUtils.isEmpty(address)) {
                //点击的是我的位置

            } else {
                mylatitude = data.getDoubleExtra("latitude", mylatitude);
                mylongitude = data.getDoubleExtra("longitude", mylongitude);
                locationTv.setText(address);
            }
        }
    }
}
