package com.haiwai.administrator.japanhouse.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.haiwai.administrator.japanhouse.R;
import com.haiwai.administrator.japanhouse.bean.DrawMapBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/25.
 */

public class MyDrawCircleView extends View {
    Paint mpaint;
    //    Paint mpaint2;
    Path mpath;
    //    Path mpath2;
    private boolean isUp;
    private List<Point> pointList = new ArrayList<>();
    private String mtype;

    public MyDrawCircleView(Context context) {
        super(context);
        paint();
    }

    public MyDrawCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint();
    }

    public MyDrawCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint();
    }

    void paint() {
        mpaint = new Paint();
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setStrokeWidth(7f);
        mpaint.setColor(getResources().getColor(R.color.mapcirclestroke));
        mpaint.setAntiAlias(true);
        mpaint.setDither(true);//设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        mpath = new Path();

        //        mpaint2 = new Paint();
        //        mpaint2.setStyle(Paint.Style.FILL);
        //        mpaint2.setStrokeWidth(130f);
        //        mpaint2.setColor(getResources().getColor(R.color.transparent));
        //        mpaint2.setAntiAlias(true);
        //        mpaint2.setDither(true);
        //        mpath2 = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mpath, mpaint);
        //        canvas.drawPath(mpath2, mpaint2);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isUp) {
            return super.onTouchEvent(event);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Point point = new Point();
                point.x = (int) event.getX();
                point.y = (int) event.getY();
                pointList.add(point);
                mpath.moveTo(event.getX(), event.getY());
                //                mpath2.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                Point point2 = new Point();
                point2.x = (int) event.getX();
                point2.y = (int) event.getY();
                pointList.add(point2);
                mpath.lineTo(event.getX(), event.getY());
                //                mpath2.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                Point point3 = new Point();
                point3.x = (int) event.getX();
                point3.y = (int) event.getY();
                pointList.add(point3);
                //                mpaint2.setColor(getResources().getColor(R.color.green2));
                mpath.close();
                //                mpath2.close();
                isUp = true;
                if (!TextUtils.isEmpty(mtype)) {
                    if (mtype.equals("new")) {
                        EventBus.getDefault().postSticky(new DrawMapBean("drawcirclemapover_new", pointList));
                    } else if (mtype.equals("old")) {
                        EventBus.getDefault().postSticky(new DrawMapBean("drawcirclemapover_old", pointList));
                    } else if (mtype.equals("zu")) {
                        EventBus.getDefault().postSticky(new DrawMapBean("drawcirclemapover_zu", pointList));
                    }
                }
                break;
        }
        this.invalidate();
        return true;
    }

    //清屏
    public void clearAll(String type) {
        this.mtype = type;
        mpath.reset();
        //        mpath2.reset();
        this.invalidate();
        isUp = false;
        //        mpaint2.setColor(getResources().getColor(R.color.transparent));
        pointList.clear();
    }

}
