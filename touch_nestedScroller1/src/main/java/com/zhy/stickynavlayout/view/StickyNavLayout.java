package com.zhy.stickynavlayout.view;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.zhy.stickynavlayout.R;

import java.util.Arrays;

/**
 * 最多滑动mTopViewHeight的距离
 */
public class StickyNavLayout extends LinearLayout implements NestedScrollingParent {
    private static final String TAG = "StickyNavLayout";

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.e(TAG, "onStartNestedScroll," +
                "\nchild=" + child.getClass().getSimpleName() +
                "\ntarget=" + target.getClass().getSimpleName() + ",\nnestedScrollAxes=" + nestedScrollAxes);
        return true;//返回true 接受嵌套滑动，回调onNestedScrollAccepted
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        Log.e(TAG, "onNestedScrollAccepted," +
                "\nchild=" + child.getClass().getSimpleName() +
                "\ntarget=" + target.getClass().getSimpleName() + ",\nnestedScrollAxes=" + nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        Log.e(TAG, "onStopNestedScroll," +
                "\ntarget=" + target.getClass().getSimpleName());
    }

    //子View开始滑动
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.e(TAG, "onNestedScroll," +
                "\ntarget=" + target.getClass().getSimpleName()
                + "\ndxConsumed=" + dxConsumed + ",dyConsumed=" + dyConsumed + ",dxUnconsumed=" + dxUnconsumed + ",dyUnconsumed=" + dyUnconsumed);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.e(TAG, "onNestedPreScroll," +
                "\ntarget=" + target.getClass().getSimpleName()
                + "\ndx=" + dx + ",dy=" + dy + ",int[]=" + Arrays.toString(consumed));
        boolean hiddenTop = dy > 0 && getScrollY() < mTopViewHeight; //内容向上滑动
        boolean showTop = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);//内容向下滑动

        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;//消耗
        }
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.e(TAG, "onNestedFling," +
                "target=" + target.getClass().getSimpleName() +
                "\nvelocityX=" + velocityX + ",velocityY=" + velocityY + ",consumed=" + consumed
        );
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.e(TAG, "onNestedPreFling," +
                "target=" + target.getClass().getSimpleName()
                + "\nvelocityX=" + velocityX + ",velocityY=" + velocityY
        );
        //down - //up+
        if (getScrollY() >= mTopViewHeight) return false;
        fling((int) velocityY);
        return true;
    }

    @Override
    public int getNestedScrollAxes() {
        Log.e(TAG, "getNestedScrollAxes");
        return 0;
    }

    private View mTop;
    private View mNav;
    private ViewPager mViewPager;

    private int mTopViewHeight;

    private OverScroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mTouchSlop;
    private int mMaximumVelocity, mMinimumVelocity;

    private float mLastY;
    private boolean mDragging;

    public StickyNavLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);

        mScroller = new OverScroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mMaximumVelocity = ViewConfiguration.get(context)
                .getScaledMaximumFlingVelocity();
        mMinimumVelocity = ViewConfiguration.get(context)
                .getScaledMinimumFlingVelocity();

    }

    private void initVelocityTrackerIfNotExists() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event)
//    {
//        initVelocityTrackerIfNotExists();
//        mVelocityTracker.addMovement(event);
//        int action = event.getAction();
//        float y = event.getY();
//
//        switch (action)
//        {
//            case MotionEvent.ACTION_DOWN:
//                if (!mScroller.isFinished())
//                    mScroller.abortAnimation();
//                mLastY = y;
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                float dy = y - mLastY;
//
//                if (!mDragging && Math.abs(dy) > mTouchSlop)
//                {
//                    mDragging = true;
//                }
//                if (mDragging)
//                {
//                    scrollBy(0, (int) -dy);
//                }
//
//                mLastY = y;
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                mDragging = false;
//                recycleVelocityTracker();
//                if (!mScroller.isFinished())
//                {
//                    mScroller.abortAnimation();
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                mDragging = false;
//                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
//                int velocityY = (int) mVelocityTracker.getYVelocity();
//                if (Math.abs(velocityY) > mMinimumVelocity)
//                {
//                    fling(-velocityY);
//                }
//                recycleVelocityTracker();
//                break;
//        }
//
//        return super.onTouchEvent(event);
//    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTop = findViewById(R.id.id_stickynavlayout_topview);
        mNav = findViewById(R.id.id_stickynavlayout_indicator);
        View view = findViewById(R.id.id_stickynavlayout_viewpager);
        if (!(view instanceof ViewPager)) {
            throw new RuntimeException(
                    "id_stickynavlayout_viewpager show used by ViewPager !");
        }
        mViewPager = (ViewPager) view;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //不限制顶部的高度
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getChildAt(0).measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
        params.height = getMeasuredHeight() - mNav.getMeasuredHeight();
        setMeasuredDimension(getMeasuredWidth(), mTop.getMeasuredHeight() + mNav.getMeasuredHeight() + mViewPager.getMeasuredHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTopViewHeight = mTop.getMeasuredHeight();
    }

    public void fling(int velocityY) {
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, mTopViewHeight);
        invalidate();
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mTopViewHeight) {
            y = mTopViewHeight;
        }
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }


}
