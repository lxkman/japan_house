package com.example.administrator.japanhouse.fragment.mine.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.japanhouse.R;
import com.example.administrator.japanhouse.base.BaseFragment;
import com.example.administrator.japanhouse.fragment.home.ui.adapter.MaiFang_house_Adapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/4/16.
 */

public class Sell_house_Fragment extends BaseFragment {

    private SwipeMenuRecyclerView mrecycler;
    private MaiFang_house_Adapter sell_house_adapter;
    List<String>mList=new ArrayList<>();
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sell_house_fragment, container, false);
        mrecycler = (SwipeMenuRecyclerView) view.findViewById(R.id.mrecycler);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void lazyLoad() {
        if (mList.size()<=0){
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
            mList.add("");
        }


        mrecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        sell_house_adapter = new MaiFang_house_Adapter(getActivity(),mList);
        // 设置监听器。
        mrecycler.setSwipeMenuCreator(mSwipeMenuCreator);

        mrecycler.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                mList.remove( menuBridge.getAdapterPosition());
                menuBridge.closeMenu();
                sell_house_adapter.notifyDataSetChanged();

            }
        });
        mrecycler.setAdapter(sell_house_adapter);

    }
    // 创建菜单:
    SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
//            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext); // 各种文字和图标属性设置。
//            leftMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。
            SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity()); // 各种文字和图标属性设置。
            deleteItem.setWeight(100);
            deleteItem.setHeight(380);
            deleteItem.setText(getString(R.string.shanchu));
            deleteItem.setTextSize(14);
            deleteItem.setBackgroundColor(getResources().getColor(R.color.red1));
            deleteItem.setTextColor(Color.WHITE);
            rightMenu.addMenuItem(deleteItem); // 在Item右侧添加一个菜单。
            // 注意:哪边不想要菜单,那么不要添加即可。
        }
    };


}
