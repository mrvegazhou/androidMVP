package com.effortapp.business.widget.pullExtend;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

public class PullExtendLayout extends LinearLayout implements IPullToExtend {

    public PullExtendLayout(Context context) {
        this(context, null);
    }
    public PullExtendLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public PullExtendLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (getChildAt(0) instanceof ExtendLayout) {
            mHeaderLayout = (ExtendLayout) getChildAt(0);
            mRefreshableView = getChildAt(1);
        } else {
            throw new IllegalStateException("布局异常");
        }

        if (mRefreshableView == null) {
            throw new IllegalStateException("布局异常，一定要有内容布局");
        }
        if(mHeaderLayout!=null){
            mHeaderLayout.onReset();
            mHeaderLayout.setResetLayout(new ExtendLayout.ResetLayout() {
                @Override
                public void onRest() {
                    //滑动到原来的位置，初始状态
                    smoothScrollTo(0);
                }
            });
        }

        //遍历主View，找出其中的滑动控件（RecyclerView或NestedScrollView）用于处理滑动冲突
        if( mRefreshableView instanceof ViewGroup ) {
            ViewGroup mRefreshableViewGroup= (ViewGroup) mRefreshableView;
            //这里只默认一层，若有多层嵌套可用递归法进行修改处理
            for( int i=0; i<mRefreshableViewGroup.getChildCount(); i++ ) {
                View child = mRefreshableViewGroup.getChildAt(i);
                if( child instanceof RecyclerView || child instanceof NestedScrollView ) {
                    mScrollRefreshableView = child;
                }
            }
        } else {
            if( mRefreshableView instanceof RecyclerView || mRefreshableView instanceof NestedScrollView ) {
                mScrollRefreshableView = mRefreshableView;
            }
        }
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override
    protected final void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mHeaderLayout!=null) {
            mHeaderLayout.resetHeaderSize(h);
        }
        refreshLoadingViewsSize();
        // 设置刷新View的大小
        refreshRefreshableViewSize(w, h);
    }

    /**
     * 初始化padding，我们根据header的高度来设置top padding和bottom padding
     */
    private void refreshLoadingViewsSize() {
        // 得到header和footer的内容高度，它将会作为拖动刷新的一个临界值，如果拖动距离大于这个高度
        // 然后再松开手，就会触发刷新操作
        int headerHeight = (null != mHeaderLayout) ? mHeaderLayout.getContentSize() : 0;
        headerListHeight = (null != mHeaderLayout) ? mHeaderLayout.getListSize() : 0;

        if (headerHeight < 0) {
            headerHeight = 0;
        }

        mHeaderHeight = headerHeight;

        headerHeight = (null != mHeaderLayout) ? mHeaderLayout.getMeasuredHeight() : 0;

        //重点：距离变化后必须进行重新的滚动
        if(getScrollY()!=0 && -headerListHeight!=getScrollY()){
            smoothScrollTo(-headerListHeight);
        }
        int pLeft = getPaddingLeft();
        int pTop = -headerListHeight;
        int pRight = getPaddingRight();
        setPadding(pLeft, pTop, pRight, 0);
    }

    /**
     * 计算刷新View的大小
     *
     * @param width  当前容器的宽度
     * @param height 当前容器的宽度
     */
    protected void refreshRefreshableViewSize(int width, int height) {
        LayoutParams lp = (LayoutParams) mRefreshableView.getLayoutParams();
        if (lp.height != height) {
            lp.height = height;
            mRefreshableView.requestLayout();
        }
    }

    /**
     * 回滚的时间
     */
    private static final int SCROLL_DURATION = 50;

    /**
     * 阻尼系数
     */
    private float offsetRadio = 1.0f;

    /**
     * 第一次按下的点
     */
    private float mFirstDownY = -1;

    /**
     * 上一次移动的点
     */
    private float mLastMotionY = -1;

    /**
     * 开始时刻
     */
    private long mStartTime = -1;

    /**
     * 头部布局
     */
    private ExtendLayout mHeaderLayout;

    /**
     * 列表开始显示的高度
     */
    private int mHeaderHeight;

    /**
     * 列表的高度
     */
    private int headerListHeight;

    /**
     * 下拉是否可用
     */
    private boolean mPullRefreshEnabled = true;

    /**
     * 是否截断touch事件
     */
    private boolean mInterceptEventEnable = true;

    /**
     * 表示是否消费了touch事件，如果是，则不调用父类的onTouchEvent方法
     */
    private boolean mIsHandledTouchEvent = false;

    /**
     * 移动点的保护范围值
     */
    private int mTouchSlop;

    /**
     * 主View
     */
    View mRefreshableView;
    /**
     * 主View中的滑动控件，用于处理滑动冲突
     */
    View mScrollRefreshableView;

    /**
     * 平滑滚动的Runnable
     */
    private SmoothScrollRunnable mSmoothScrollRunnable;

    final class SmoothScrollRunnable implements Runnable {

        /**
         * 结束Y
         */
        private final int mScrollToY;
        /**
         * 开始Y
         */
        private final int mScrollFromY;
        /**
         * 滑动时间
         */
        private final long mDuration;
        /**
         * 动画效果
         */
        private final Interpolator mInterpolator;

        /**
         * 当前Y
         */
        private int mCurrentY = -1;

        /**
         * 是否继续运行
         */
        private boolean mContinueRunning = true;

        /**
         * 构造方法
         *
         * @param fromY    开始Y
         * @param toY      结束Y
         * @param duration 动画时间
         */
        public SmoothScrollRunnable(int fromY, int toY, long duration) {
            mScrollFromY = fromY;
            mScrollToY = toY;
            mDuration = duration;
            mInterpolator = new DecelerateInterpolator();
        }

        @Override
        public void run() {
            if (mDuration <= 0) {
                scrollTo(0, mScrollToY);
                return;
            }
            if (mStartTime == -1) {
                mStartTime = System.currentTimeMillis();
            } else {
                final long oneSecond = 1000;
                long normalizedTime = (oneSecond * (System.currentTimeMillis() - mStartTime)) / mDuration;
                normalizedTime = Math.max(Math.min(normalizedTime, oneSecond), 0);

                final int deltaY = Math.round((mScrollFromY - mScrollToY) * mInterpolator.getInterpolation(normalizedTime / (float) oneSecond));
                mCurrentY = mScrollFromY - deltaY;
                scrollTo(0, mCurrentY);

                if (null != mHeaderLayout && 0 != mHeaderHeight) {
                    mHeaderLayout.onPull(Math.abs(mCurrentY));
                    if (mCurrentY == 0) {
                        mHeaderLayout.setState(IExtendLayout.State.RESET);
                    }
                    if (Math.abs(mCurrentY) == headerListHeight) {
                        mHeaderLayout.setState(IExtendLayout.State.arrivedHeight);
                    }
                }
            }

            if (mContinueRunning && mScrollToY != mCurrentY) {
                PullExtendLayout.this.postDelayed(this, 16);
            }
        }

        /**
         * 停止滑动
         */
        public void stop() {
            mContinueRunning = false;
            removeCallbacks(this);
        }
    }

    /**
     * 平滑滚动
     *
     * @param newScrollValue 滚动的值
     */
    private void smoothScrollTo(int newScrollValue) {
        smoothScrollTo(newScrollValue, SCROLL_DURATION, 0);
    }

    /**
     * 平滑滚动
     * @param newScrollValue 滚动的值
     * @param duration       滚动时间
     * @param delayMillis    延迟时间，0代表不延迟
     */
    private void smoothScrollTo(int newScrollValue, long duration, long delayMillis) {
        if (null != mSmoothScrollRunnable) {
            mSmoothScrollRunnable.stop();
        }
        int oldScrollValue = getScrollY();
        boolean post = (oldScrollValue != newScrollValue);
        if (post) {
            mSmoothScrollRunnable = new SmoothScrollRunnable(oldScrollValue, newScrollValue, duration);
            if (delayMillis > 0) {
                postDelayed(mSmoothScrollRunnable, delayMillis);
            } else {
                post(mSmoothScrollRunnable);
            }
        }
    }


    @Override
    public void setPullRefreshEnabled(boolean pullRefreshEnabled) {
        mPullRefreshEnabled = pullRefreshEnabled;
    }

    @Override
    public boolean isPullRefreshEnabled() {
        return mPullRefreshEnabled && (null != mHeaderLayout);
    }

    @Override
    public ExtendLayout getHeaderExtendLayout() {
        return null;
    }

    @Override
    public final boolean onInterceptTouchEvent(MotionEvent event) {
        if (!isPullRefreshEnabled()) {
            return false;
        }
        final int action = event.getAction();
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mIsHandledTouchEvent = false;
            return false;
        }
        if (action != MotionEvent.ACTION_DOWN && mIsHandledTouchEvent) {
            return true;
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mFirstDownY = event.getY();
                mLastMotionY = event.getY();
                mIsHandledTouchEvent = false;
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = event.getY() - mLastMotionY;
                //在头部展开时，进行子View滑动冲突的处理
                if (mHeaderLayout!=null && mHeaderLayout.childScrollView!=null && mHeaderLayout.getState()== IExtendLayout.State.arrivedHeight) {
                    //在此判断子View是否可以滑动，如果可以滑动，不进行拦截
                    if (mHeaderLayout.childScrollView instanceof RecyclerView) {
                        RecyclerView mRecyclerView = (RecyclerView) mHeaderLayout.childScrollView;
                        //注意进行方向的判定
                        if ((mRecyclerView.canScrollVertically(1) && deltaY<0) || (mRecyclerView.canScrollVertically(-1) && deltaY>0)) {
                            return false;
                        }
                    } else if (mHeaderLayout.childScrollView instanceof NestedScrollView) {
                        NestedScrollView mNestedScrollView = (NestedScrollView) mHeaderLayout.childScrollView;
                        int scrollY = mNestedScrollView.getScrollY();
                        View onlyChild = mNestedScrollView.getChildAt(0);
                        //如果朝上拖动，屏蔽return true
                        float distance = onlyChild.getHeight()-(scrollY + mNestedScrollView.getHeight());
                        if (distance>0 || (distance==0 && deltaY>0)){
                            return false;
                        } else {
                            return false;
                        }
                    }
                }
                //处理主View的滑动冲突事件
                if(!dealWithMainView(deltaY)){
                    return false;
                }
                // 位移差大于mTouchSlop，这是为了防止快速拖动引发刷新
                final float absDiff = Math.abs(deltaY);
                if ((absDiff > mTouchSlop)) {
                    mLastMotionY = event.getY();
                    // 第一个显示出来，Header已经显示或拉下
                    if (isPullRefreshEnabled()) {
                        mIsHandledTouchEvent = (Math.abs(getScrollY()) > 0 || deltaY > 0.5f || deltaY < -0.5f);
                    }
                }
                break;

            default:
                break;
        }
        return mIsHandledTouchEvent;
    }

    @Override
    public final boolean onTouchEvent(MotionEvent ev) {
        boolean handled = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFirstDownY = ev.getY();
                mLastMotionY = ev.getY();
                mIsHandledTouchEvent = false;
                break;

            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getY() - mLastMotionY;
                mLastMotionY = ev.getY();
                if (isPullRefreshEnabled() && isReadyForPullDown(deltaY)) {
                    //处理主View的滑动冲突事件
                    if (!dealWithMainView(deltaY)) {
                        return false;
                    }
                    pullHeaderLayout(deltaY / offsetRadio);
                    handled = true;
                } else {
                    mIsHandledTouchEvent = false;
                }
                break;

            case MotionEvent.ACTION_CANCEL:

            case MotionEvent.ACTION_UP:
                if (mIsHandledTouchEvent) {
                    mIsHandledTouchEvent = false;
                    // 当第一个显示出来时
                    if (isReadyForPullDown(0)) {
                        resetHeaderLayout();
                    }
                }
                break;

            default:
                break;

        }
        return handled;
    }

    /**
     * 处理主View的滑动冲突事件
     */
    private boolean dealWithMainView(float deltaY){
        //如果主View存在滑动控件，则必须处理滑动冲突
        if (mScrollRefreshableView!=null) {
            //当主View处于初始状态时，不拦截事件
            if (getScrollY()==0) {
                if (mScrollRefreshableView instanceof RecyclerView) {
                    RecyclerView mRecyclerView = (RecyclerView) mScrollRefreshableView;
                    if ((mRecyclerView.canScrollVertically(1) && deltaY<0) || (mRecyclerView.canScrollVertically(-1) && deltaY>0)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    /**
     * 判断刷新的View是否滑动到顶部
     *
     * @return true表示已经滑动到顶部，否则false
     */
    protected boolean isReadyForPullDown(float deltaY) {
        return getScrollY() < 0 || (getScrollY() == 0 && deltaY > 0);
    }

    /**
     * 拉动Header Layout时调用
     *
     * @param delta 移动的距离
     */
    protected void pullHeaderLayout(float delta) {
        // 向上滑动，并且当前scrollY为0时，不滑动
        int oldScrollY = getScrollY();
        if (delta < 0 && (oldScrollY - delta) >= 0) {
            scrollTo(0, 0);
            if (null != mHeaderLayout && 0 != mHeaderHeight) {
                mHeaderLayout.setState(IExtendLayout.State.RESET);
                mHeaderLayout.onPull(0);
            }
            return;
        }

        // 向下滑动布局
        scrollBy(0, -(int) delta);
        int scrollY = Math.abs(getScrollY());
        if (null != mHeaderLayout && 0 != mHeaderHeight) {
            if (scrollY >= headerListHeight) {
                mHeaderLayout.setState(IExtendLayout.State.arrivedHeight);
                this.offsetRadio = 2.0f;
            } else {
                this.offsetRadio = 1.0f;
            }
            mHeaderLayout.onPull(scrollY);
        }
    }

    /**
     * 重置header
     */
    protected void resetHeaderLayout() {
        final int scrollY = Math.abs(getScrollY());
        //上滑
        if (mLastMotionY-mFirstDownY<0) {
            smoothScrollTo(0);
        } else {
            if (scrollY >= mHeaderHeight) {
                smoothScrollTo(-headerListHeight);
            } else {
                smoothScrollTo(0);
            }
        }
    }

}
