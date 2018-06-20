package com.example.administrator.japanhouse.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseActivity;
import com.example.administrator.japanhouse.bean.EventBean;
import com.example.administrator.japanhouse.utils.Constants;
import com.example.administrator.japanhouse.view.NoScrollViewPager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeMapActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.all_ershoufang)
    RadioButton allErshoufang;
    @BindView(R.id.rb_zufang)
    RadioButton rbZufang;
    @BindView(R.id.rb_xinfang)
    RadioButton rbXinfang;
    @BindView(R.id.rg_look)
    RadioGroup rgLook;
    @BindView(R.id.view_ershoufang)
    View viewErshoufang;
    @BindView(R.id.view_zufang)
    View viewZufang;
    @BindView(R.id.view_xinfang)
    View viewXinfang;
    @BindView(R.id.title_search_iv)
    ImageView titleSearchIv;
    @BindView(R.id.title_message_iv)
    ImageView titleMessageIv;
    @BindView(R.id.vp_look)
    NoScrollViewPager vpLook;
    private List<Fragment> mBaseFragmentList = new ArrayList<>();
    private FragmentManager fm;
    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_map);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        if (mBaseFragmentList.size() <= 0) {
            mBaseFragmentList.add(new MapOldhouseFragment());
            mBaseFragmentList.add(new MapZuhouseFragment());
            mBaseFragmentList.add(new MapNewhouseFragment());
        }
        initListener();
    }

    private void initListener() {
        vpLook.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) rgLook.getChildAt(position)).setChecked(true);
                if (position == 0) {
                    viewErshoufang.setVisibility(View.VISIBLE);
                    viewZufang.setVisibility(View.INVISIBLE);
                    viewXinfang.setVisibility(View.INVISIBLE);
                } else if (position == 1) {
                    viewErshoufang.setVisibility(View.INVISIBLE);
                    viewZufang.setVisibility(View.VISIBLE);
                    viewXinfang.setVisibility(View.INVISIBLE);
                } else if (position == 2) {
                    viewErshoufang.setVisibility(View.INVISIBLE);
                    viewZufang.setVisibility(View.INVISIBLE);
                    viewXinfang.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rgLook.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.all_ershoufang:
                        vpLook.setCurrentItem(0);
                        break;
                    case R.id.rb_zufang:
                        vpLook.setCurrentItem(1);
                        break;
                    case R.id.rb_xinfang:
                        vpLook.setCurrentItem(2);
                        break;
                    default:
                        vpLook.setCurrentItem(0);
                        break;
                }
            }
        });
        fm = getSupportFragmentManager();
        myAdapter = new MyAdapter(fm);
        vpLook.setAdapter(myAdapter);
    }

    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mBaseFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mBaseFragmentList.size();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        //        mBaiduMap.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        //        mBaiduMap.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        //        mBaiduMap.onPause();
    }

    @OnClick({R.id.title_back_iv, R.id.title_search_iv, R.id.title_message_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_search_iv:
                Intent intent = new Intent(mContext, MapSearchActivity.class);
                intent.putExtra("state", vpLook.getCurrentItem());
                startActivityForResult(intent, 0);
                break;
            case R.id.title_message_iv:
                EventBus.getDefault().post(new EventBean(Constants.EVENT_CHAT));
                finish();
                break;
        }
    }


}
