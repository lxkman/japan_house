package com.haiwai.administrator.japanhouse.fragment.home;

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
import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseActivity;
import com.haiwai.administrator.japanhouse.bean.HomeItemBean;
import com.haiwai.administrator.japanhouse.utils.MapUtils;
import com.haiwai.administrator.japanhouse.utils.TUtils;

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
    private LocationClient mLocClient;
    private double mylongitude;
    private double mylatitude;
    private TypeAdapter typeAdapter;
    private List<HomeItemBean> typeList;
    private String lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongqin);
        ButterKnife.bind(this);
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
                String street = bdLocation.getStreet();
                locationTv.setText(street);
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

        typeList = new ArrayList<>();
        typeList.add(new HomeItemBean(true, itemPic[0], getString(R.string.buxing)));
        typeList.add(new HomeItemBean(false, itemPic[1], getString(R.string.qixing)));
        typeList.add(new HomeItemBean(false, itemPic[2], getString(R.string.gongjiao)));
        typeList.add(new HomeItemBean(false, itemPic[3], getString(R.string.ditie)));
        typeList.add(new HomeItemBean(false, itemPic[4], getString(R.string.zijia)));

        typeRecycler.setNestedScrollingEnabled(false);
        typeRecycler.setLayoutManager(new GridLayoutManager(mContext, 5));
        typeAdapter = new TypeAdapter(R.layout.item_tongqin_type, typeList);
        typeRecycler.setAdapter(typeAdapter);
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < typeList.size(); i++) {
                    if (position == i) {
                        typeList.get(i).setChecked(true);
                    } else {
                        typeList.get(i).setChecked(false);
                    }
                }
                typeAdapter.notifyDataSetChanged();
            }
        });
    }

    private void go() {
        int position = getCheckedTypePosition();
        if (TextUtils.isEmpty(lastTime)){
            TUtils.showFail(mContext, getString(R.string.qingxuanzeshijian));
            return;
        }
        int time = Integer.parseInt(lastTime);
        int distance = 0;
        switch (position) {
            case 0:
                distance = 80 * time;
                break;
            case 1:
                distance = 400 * time;
                break;
            case 2:
                distance = 800 * time;
                break;
            case 3:
                distance = 1000 * time;
                break;
            case 4:
                distance = 1500 * time;
                break;
        }
        double[] around = MapUtils.getjingweidufanwei(mylatitude, mylongitude, distance);
        Intent intent = new Intent();
        intent.putExtra("starJd", around[0]);
        intent.putExtra("endJd", around[1]);
        intent.putExtra("starWd", around[2]);
        intent.putExtra("endWd", around[3]);
        setResult(1, intent);
        finish();
    }

    private int getCheckedTypePosition() {
        for (int i = 0; i < typeList.size(); i++) {
            if (typeList.get(i).isChecked()) {
                return i;
            }
        }
        return 0;//默认是步行
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
                lastTime = data.get(options1);
                timeTv.setText(lastTime);
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
                locationTv.setText(address.length()>14?address.substring(0,14)+"...":address);
            }
        }
    }
}
