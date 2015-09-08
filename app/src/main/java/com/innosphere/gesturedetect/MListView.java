package com.innosphere.gesturedetect;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gen on 2015-09-07.
 */
public class MListView extends DynamicListView implements GestureDetector.OnGestureListener{

    private final static String TAG="MListView";

    private GestureDetectorCompat mDetector;

    private int mDownX;

    private int mDownY;

    private boolean isScrolling;

    private MotionEvent downEvent;

    public MListView(Context context) {
        super(context);
        init();
    }

    public MListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init(){
        mDetector = new GestureDetectorCompat(this.getContext(),this);
    }



    public boolean onInterceptTouchEvent(MotionEvent event){
        return true;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event){
        mDetector.onTouchEvent(event);
        postEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, "onDown=======eX:" + e.getX() + "======eY:" + e.getY());
        mDownX=(int)e.getX();
        mDownY=(int)e.getY();
        isScrolling=false;
        downEvent=MotionEvent.obtain(e);
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
        if (Math.abs(distanceX)>10){
            if (!isScrolling){
                MotionEvent motionEvent=MotionEvent.obtain(e1);
                postEvent(motionEvent);
            }
            isScrolling=true;
            postEvent(e2);
        }
        return false;
    }


    public void postEvent(MotionEvent event){
        int position = pointToPosition(mDownX, mDownY);
        int itemNum = position - getFirstVisiblePosition();
        View selectedView = getChildAt(itemNum);
        if (selectedView instanceof ViewGroup){
            ViewGroup group=(ViewGroup)selectedView;
            group.onTouchEvent(event);
        }
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG,"onLongPress=======eX:"+e.getX()+"========eY:"+e.getY());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG,"fling=======velocityX:"+velocityX+" velocityY:"+velocityY);
        isScrolling=false;
        MotionEvent motionEvent=MotionEvent.obtain(e2);
        postEvent(motionEvent);
        return false;
    }
}
