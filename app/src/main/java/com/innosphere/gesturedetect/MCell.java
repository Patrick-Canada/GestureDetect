package com.innosphere.gesturedetect;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.daimajia.swipe.SwipeLayout;

/**
 * Created by gen on 2015-09-07.
 */
public class MCell extends FrameLayout implements GestureDetector.OnGestureListener{

    private final static String TAG="MCell";

    private GestureDetectorCompat mDetector;


    private  boolean isScrolling;

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
        mDetector = new GestureDetectorCompat(this.getContext(),this);
    }

    public boolean onInterceptTouchEvent(MotionEvent event){
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG,"onDown=======eX:"+e.getX()+"======eY:"+e.getY());
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG,"onShowPress=======eX:"+e.getX()+"=====eY:"+e.getY());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG,"onSingleTapUp=======");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll=======distanceX:" + distanceX);
        if (Math.abs(distanceX)>10){
            isScrolling=true;

            SwipeLayout swipeLayout=(SwipeLayout) this.findViewById(R.id.swipe_layout);
            swipeLayout.scrollTo((int)e2.getX(),0);
        }

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG,"onLongPress=======eX:"+e.getX()+"========eY:"+e.getY());
        isScrolling=false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "fling=======velocityX:" + velocityX + " velocityY:" + velocityY);
        return false;
    }

}
