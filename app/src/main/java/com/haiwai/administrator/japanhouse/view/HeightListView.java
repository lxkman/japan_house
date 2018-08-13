package com.haiwai.administrator.japanhouse.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by   admin on 2018/5/28.
 */

public class HeightListView extends ListView {
    public HeightListView(Context context) {
        super(context);
    }

    public HeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeightListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
