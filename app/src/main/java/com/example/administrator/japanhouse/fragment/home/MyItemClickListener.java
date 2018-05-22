package com.example.administrator.japanhouse.fragment.home;

import android.view.View;

import java.util.List;

public interface MyItemClickListener {
    void onItemClick(View view, int postion, int itemPosition);
    void onMoreItemClick(View view, List<List<String>> moreSelectedBeanList);
}
