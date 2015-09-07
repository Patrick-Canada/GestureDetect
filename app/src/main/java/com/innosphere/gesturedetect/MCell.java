package com.innosphere.gesturedetect;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by gen on 2015-09-07.
 */
public class MCell extends FrameLayout {

    public MCell(Context context) {
        super(context);
        init();
    }

    public MCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){

    }

    public boolean onInterceptTouchEvent(MotionEvent event){

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        return super.onTouchEvent(event);
    }
}
