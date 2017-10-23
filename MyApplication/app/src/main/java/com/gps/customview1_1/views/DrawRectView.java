package com.gps.customview1_1.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author : zhoukan
 * @CreateDate : 2017/10/23 0023
 * @Descriptiong : xxx
 */

public class DrawRectView extends View{

    Paint paint = new Paint();

    public DrawRectView(Context context) {
        this(context,null);
    }

    public DrawRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(200,200,80,paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(400,200,80,paint);
    }
}
