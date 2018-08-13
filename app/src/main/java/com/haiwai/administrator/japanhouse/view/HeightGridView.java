package com.haiwai.administrator.japanhouse.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by   admin on 2018/4/19.
 */

public class HeightGridView extends GridView{

    public HeightGridView(Context context) {
        super(context);
    }

    public HeightGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeightGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
