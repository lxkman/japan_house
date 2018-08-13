package com.haiwai.administrator.japanhouse.fragment.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/8.
 */

public class CommentFragment extends BaseFragment {
    @BindView(R.id.all_ershoufang)
    RadioButton allErshoufang;
    @BindView(R.id.rb_zufang)
    RadioButton rbZufang;
    @BindView(R.id.rb_xinfang)
    RadioButton rbXinfang;
    @BindView(R.id.rb_tudi)
    RadioButton rbTudi;
    @BindView(R.id.rg_look)
    RadioGroup rgLook;
    @BindView(R.id.view_ershoufang)
    View viewErshoufang;
    @BindView(R.id.view_zufang)
    View viewZufang;
    @BindView(R.id.view_xinfang)
    View viewXinfang;
    @BindView(R.id.view_tudi)
    View viewTudi;
    @BindView(R.id.vp_look)
    ViewPager vpLook;
    Unbinder unbinder;
    private List<Fragment> mBaseFragmentList = new ArrayList<>();
    private FragmentManager fm;
    private MyAdapter myAdapter;
    private String TAG="";
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_comment, null);
        unbinder = ButterKnife.bind(this, view);
        vpLook.setOffscreenPageLimit(4);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mBaseFragmentList.size() <= 0) {
            mBaseFragmentList.add(new OldhouseFragment());
            mBaseFragmentList.add(new ZuhouseFragment());
            mBaseFragmentList.add(new NewhouseFragment());
            mBaseFragmentList.add(new TudiFragment());
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
                if (position==0){
                    viewErshoufang.setVisibility(View.VISIBLE);
                    viewZufang.setVisibility(View.INVISIBLE);
                    viewXinfang.setVisibility(View.INVISIBLE);
                    viewTudi.setVisibility(View.INVISIBLE);
                }else if (position==1){
                    viewErshoufang.setVisibility(View.INVISIBLE);
                    viewZufang.setVisibility(View.VISIBLE);
                    viewXinfang.setVisibility(View.INVISIBLE);
                    viewTudi.setVisibility(View.INVISIBLE);
                }else if (position==2){
                    viewErshoufang.setVisibility(View.INVISIBLE);
                    viewZufang.setVisibility(View.INVISIBLE);
                    viewXinfang.setVisibility(View.VISIBLE);
                    viewTudi.setVisibility(View.INVISIBLE);
                }else if (position==3){
                    viewErshoufang.setVisibility(View.INVISIBLE);
                    viewZufang.setVisibility(View.INVISIBLE);
                    viewXinfang.setVisibility(View.INVISIBLE);
                    viewTudi.setVisibility(View.VISIBLE);
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
                    case R.id.rb_tudi:
                        vpLook.setCurrentItem(3);
                        break;
                    default:
                        vpLook.setCurrentItem(0);
                        break;
                }
            }
        });


        fm = getChildFragmentManager();
        myAdapter = new MyAdapter(fm);
        vpLook.setAdapter(myAdapter);
    }



    class MyAdapter extends FragmentStatePagerAdapter {
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
