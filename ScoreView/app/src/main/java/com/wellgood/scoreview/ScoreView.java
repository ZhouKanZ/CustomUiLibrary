package com.wellgood.scoreview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by NiJiaqi on 2017-1-11.
 */

public class ScoreView extends View implements OnScoreChangeListener {

    /**
     * 1. 画一个圆环
     */
    private float arc;
    private int start;
    private int currentPro;
    private int score;
    private Paint paint;

    private int aim = 0;
    private int mRadius = 100;
    private int bordeColor = Color.WHITE;
    private int bordeWidth = 5;
    private int arcPadding = 15;
    private int arcColor = Color.WHITE;
    private int scoreColor = Color.WHITE;
    private int scoreSize = 70;
    private int scoreTextSize = 20;
    private int textPadding = 10;
    private int arcWidth = 20;

    private float pre = 0;
    private int lastScore;

    private OnScoreChangeListener scoreChangeListener;

    public void setScoreChangeListener(OnScoreChangeListener scoreChangeListener) {
        this.scoreChangeListener = scoreChangeListener;
    }

    public ScoreView(Context context) {
        this(context, null);
    }

    public ScoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ScoreView, defStyleAttr, 0);
        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.ScoreView_aim:
                    aim = a.getInteger(attr,0);
                    break;
                case R.styleable.ScoreView_mRadius:
                    mRadius =  a.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.ScoreView_bordeColor:
                    bordeColor = a.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.ScoreView_bordeWidth:
                    bordeWidth =  a.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.ScoreView_arcPadding:
                    arcPadding =  a.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.ScoreView_arcColor:
                    arcColor = a.getColor(attr,Color.WHITE);
                    break;
                case R.styleable.ScoreView_scoreColor:
                    scoreColor = a.getColor(attr,Color.WHITE);
                    break;
                case R.styleable.ScoreView_scoreSize:
                    scoreSize = a.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 70, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.ScoreView_scoreTextSize:
                    scoreTextSize = a.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.ScoreView_textPadding:
                    textPadding =  a.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.ScoreView_arcWidth:
                    arcWidth = a.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
                    break;
                default:
                    break;
            }
        }
        a.recycle();

        arc = (int) ((float) aim / (float) 100 * 360);
        start = -90;
        currentPro = 0;
        score = 0;
        drawArc();
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPro = 0;
                drawArc();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint = new Paint();
//        bordeColor
        paint.setColor(bordeColor);
        paint.setAntiAlias(true);


        paint.setStyle(Paint.Style.STROKE); // 绘制空心圆
//        bordeWidth
        paint.setStrokeWidth(bordeWidth);            // 线宽
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        canvas.drawCircle(cx, cy, mRadius, paint);
        int center = getWidth() / 2;

        Log.d("xxx", "onDraw: " + center + ",cx :" + cx + ",cy:" + cy);
//       arcPadding
        RectF oval = new RectF(cx - mRadius + arcPadding, cy - mRadius + arcPadding, cx + mRadius - arcPadding, cy + mRadius - arcPadding); // 用于定义的圆弧的形状和大小的界限

        // arcWidth

        paint.setStrokeWidth(arcWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        // arcColor
        paint.setColor(arcColor); // 设置圆环的颜色
//        paint.setARGB(0,255,255,255);
        int alpha = (int) ((pre / (float) aim) * 100);
        paint.setAlpha(alpha);
        canvas.drawArc(oval, -90, currentPro, false, paint); // 根据进度画圆弧

//        start = currentPro;
        String text = String.valueOf(score);
        Rect re = new Rect();
        // scoreColor
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(scoreColor);
        // scoreSize
        paint.setTextSize(scoreSize);
        paint.getTextBounds(text, 0, text.length(), re);
        canvas.drawText(text, cx - (re.width() / 2), cy + (re.height() / 2), paint);

        String t = "分";
        Rect ret = new Rect();
        paint.setColor(scoreColor);
        paint.setStrokeWidth(2);
        // scoreTextSize
        paint.setTextSize(scoreTextSize);
        paint.getTextBounds(t, 0, t.length(), ret);
        // textPadding
        canvas.drawText(t, cx + re.width() / 2 + textPadding, cy + re.height() / 2, paint);

    }

    private void drawArc() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    if (currentPro >= arc) {
                        break;
                    }
                    currentPro++;
                    pre = (float) currentPro / (float) 360 * 100;
                    lastScore = score;
                    score = (int) Math.ceil(pre);
                    if (lastScore != score){
                        if (scoreChangeListener != null){
                            scoreChangeListener.scoreChangeListener(score);
                        }
                    }
                    Log.d("xxx", "run: " + pre + "  " + score);
                    postInvalidate();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height ;
        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        } else
        {
            float w= mRadius + bordeWidth;
            int desired = (int) (getPaddingLeft() + w*2 +  + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            float h = mRadius + bordeWidth ;
            int desired = (int) (getPaddingTop() + h*2 + getPaddingBottom());
            height = desired;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    public void scoreChangeListener(int score) {}

    public void setAim(int aim) {
        this.aim = aim;
        arc = (int) ((float) aim / (float) 100 * 360);
        start = -90;
        currentPro = 0;
        score = 0;
        drawArc();
    }
}
