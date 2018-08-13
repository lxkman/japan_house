package com.haiwai.administrator.japanhouse.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.haiwai.administrator.japanhouse.R;

/**
 * Created by jianhun on 2017/4/21.
 */
public class MyLetterListView extends View {
    //注意这不是全部的26个字母，除了#号一共是22个字母
    private String[] keyword = {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L",
            "M", "N", "P", "Q", "R", "S", "T", "W", "X", "Y", "Z", "#"};
    private Paint paint = new Paint();//别忘了new
    private int choose = -1;//默认选中的是-1位置的字母
    public static final int STATE_DOWN = 1;//用int值来记录状态
    public static final int STATE_MOVE = 2;
    public static final int STATE_UP = 3;


    public MyLetterListView(Context context) {
        super(context);
    }

    public MyLetterListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyLetterListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setKeyword(String[] keyword1) {
        keyword = keyword1;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        if (keyword.length==0){
            return;
        }
        int singleheight = height / keyword.length;//获取单个字母的高度
        /*
        * 每次调用onDraw方法，都要遍历一遍所有的字母并重新drawText，因为遍历的时候会判断点击的item的position，并且绘制颜色
        * 所以外界触摸事件每次发生变化都要再调用一下onDraw方法，通过Invalidate()或者postInvalidate()，区别是一个是主线程中调用，一个是子线程中调用的
        * */
        for (int i = 0; i < keyword.length; i++) {
            paint.setColor(getResources().getColor(R.color.colorPrimary));
            //            paint.setTypeface(Typeface.DEFAULT_BOLD);//加粗
            paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.sp_12));//这样能直接设置sp单位的字体
            paint.setAntiAlias(true);
            if (i == choose) {
                paint.setColor(getResources().getColor(R.color.colorPrimary));
                paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.sp_20));
            }
            float xPos = (width - paint.measureText(keyword[i])) / 2;//获取每个字母的x坐标
            float yPos = singleheight * i + singleheight;//获取每个字母的y坐标，是每个字母的底部
            canvas.drawText(keyword[i], xPos, yPos, paint);//注意循环一次是画一个字母
            paint.reset();//为什么要重置画笔呢
        }
    }

    //覆写dispatchTouchEvent或者onTouchEvent都能实现效果
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int v = (int) (event.getY() / getHeight() * keyword.length);//触摸事件发生时，查看触摸点是第几个字母

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (choose != v && listener != null) {
                    if (v >= 0 && v < keyword.length) {
                        listener.changed(v, keyword[v], STATE_DOWN);
                        choose = v;
                        invalidate();//重新draw
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (choose != v && listener != null) {
                    if (v >= 0 && v < keyword.length) {
                        listener.changed(v, keyword[v], STATE_MOVE);
                        choose = v;
                        invalidate();//重新draw
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                choose = -1;//恢复默认值
                listener.changed(choose, null, STATE_UP);
                invalidate();//重新draw
                break;
        }
        return true;//如果不返回true的话move就不会生效
    }

    private onTouchingLetterChangedListener listener;

    public void setonTouchingLetterChangedListener(onTouchingLetterChangedListener listener) {
        this.listener = listener;
    }

    public interface onTouchingLetterChangedListener {
        void changed(int position, String letter, int state);
    }
}
