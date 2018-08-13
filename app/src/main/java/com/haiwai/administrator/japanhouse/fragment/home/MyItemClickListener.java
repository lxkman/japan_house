package com.haiwai.administrator.japanhouse.fragment.home;

import android.view.View;

import java.util.List;

public interface MyItemClickListener {
    void onItemClick(View view, int postion, int itemPosition);
    void onItemClick(View view, int postion, List<String> priceRegin);
    void onMoreItemClick(View view, List<List<String>> moreSelectedBeanList);
}
