package com.example.administrator.japanhouse.fragment.home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.adapter.MyGridViewAdpter;
import com.example.administrator.japanhouse.adapter.MyViewPagerAdapter;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.bean.HomeItemBean;
import com.example.administrator.japanhouse.utils.TUtils;

import org.zackratos.ultimatebar.UltimateBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/8.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.location_tv)
    TextView locationTv;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.map_tv)
    TextView mapTv;
    @BindView(R.id.jrdk_tv)
    TextView jrdkTv;
    @BindView(R.id.gfbk_tv)
    TextView gfbkTv;
    @BindView(R.id.fcwd_tv)
    TextView fcwdTv;
    @BindView(R.id.znmf_tv)
    TextView znmfTv;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.points)
    LinearLayout points;
    Unbinder unbinder;
    private int totalPage; //总的页数
    private int mPageSize = 10; //每页显示的最大的数量
    private ImageView[] ivPoints;//小圆点图片的集合
    private List<HomeItemBean> homeItemBeanList;//总的数据源
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private String[] itemName = {"二手房","新房","土地","租房","商业地产","中国房源",
            "房价地图","找小区","我是业主","免费看房","海外地产"};
    private int[] itemPic = {R.drawable.home_ershoufang_iv,R.drawable.home_xinfang_iv,R.drawable.home_tudi_iv,
            R.drawable.home_zufang_iv,R.drawable.home_shangyedichan_iv,R.drawable.home_zhongguofangyuan_iv,
            R.drawable.home_fangjiaditu_iv, R.drawable.home_zhaoxiaoqu_iv,R.drawable.home_woshiyezhu_iv,
            R.drawable.home_mianfeikanfang_iv,R.drawable.home_haiwaidichan_iv};

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        initViewData(view);
        return view;
    }

    private void initViewData(View view) {
        //-----item导航栏-----
        homeItemBeanList = new ArrayList<>();
        for (int i = 0; i < itemName.length; i++) {
            homeItemBeanList.add(new HomeItemBean(itemName[i],itemPic[i]));
        }
        //总的页数向上取整
        totalPage = (int) Math.ceil(homeItemBeanList.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<>();
        for(int i = 0; i < totalPage; i++){
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView)View.inflate(mContext, R.layout.item_gridview, null);
            gridView.setAdapter(new MyGridViewAdpter(mContext, homeItemBeanList, i, mPageSize));
            //添加item点击监听
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    // TODO Auto-generated method stub
                    Object obj = gridView.getItemAtPosition(position);
                    if(obj != null && obj instanceof HomeItemBean){
                        TUtils.showShort(mContext, ((HomeItemBean)obj).getTitle());
                    }
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        //设置ViewPager适配器
        viewpager.setAdapter(new MyViewPagerAdapter(viewPagerList));
        //添加小圆点
        ivPoints = new ImageView[totalPage];
        for(int i = 0; i < totalPage; i++){
            //循坏加入点点图片组
            ivPoints[i] = new ImageView(mContext);
            if(i==0){
                ivPoints[i].setImageResource(R.drawable.dot_selected);
            }else {
                ivPoints[i].setImageResource(R.drawable.dot_unselected);
            }
            ivPoints[i].setPadding(8, 8, 8, 8);
            points.addView(ivPoints[i]);
        }
        //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
        viewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //currentPage = position;
                for(int i=0 ; i < totalPage; i++){
                    if(i == position){
                        ivPoints[i].setImageResource(R.drawable.dot_selected);
                    }else {
                        ivPoints[i].setImageResource(R.drawable.dot_unselected);
                    }
                }
            }
        });
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.location_tv, R.id.search_tv, R.id.map_tv, R.id.jrdk_tv, R.id.gfbk_tv,
            R.id.fcwd_tv, R.id.znmf_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.location_tv:
                break;
            case R.id.search_tv:
                break;
            case R.id.map_tv:
                break;
            case R.id.jrdk_tv:
                break;
            case R.id.gfbk_tv:
                break;
            case R.id.fcwd_tv:
                break;
            case R.id.znmf_tv:
                break;
        }
    }
}
