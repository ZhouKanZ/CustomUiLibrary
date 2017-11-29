package com.gps.customview1_1.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author : zhoukan
 * @CreateDate : 2017/10/24 0024
 * @Descriptiong : xxx
 */

public class HistogramView extends View {

    public HistogramView(Context context) {
        this(context,null);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();

    }

    private void initPaint() {
    }

}
