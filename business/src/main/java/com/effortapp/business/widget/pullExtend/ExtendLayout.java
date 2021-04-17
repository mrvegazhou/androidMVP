package com.effortapp.business.widget.pullExtend;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class ExtendLayout extends FrameLayout implements IExtendLayout {
    /**
     * 当前的状态
     */
    private State mCurState = State.NONE;
    /**
     * 前一个状态
     */
    private State mPreState = State.NONE;


    public ExtendLayout(@NonNull Context context) {
        super(context);
    }

    public ExtendLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExtendLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    protected void init(Context context, AttributeSet attrs) {
        View container = createView(context, attrs);
        if (null == container) {
            throw new NullPointerException("Loading view can not be null.");
        }
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(container, params);
        bindView(container);
    }

    /**
     * 重置控件的高度
     */
    public abstract void resetHeaderSize(int h);

    protected abstract void bindView(View container);

    /**
     * 创建Loading的View
     *
     * @param context context
     * @param attrs   attrs
     * @return Loading的View
     */
    protected abstract View createView(Context context, AttributeSet attrs);


    @Override
    public void setState(State state) {
        if (mCurState != state) {
            mPreState = mCurState;
            mCurState = state;
            onStateChanged(state, mPreState);
        }
    }

    @Override
    public State getState() {
        return mCurState;
    }

    /**
     * 当状态改变时调用
     * @param curState 当前状态
     * @param oldState 老的状态
     */
    protected void onStateChanged(State curState, State oldState) {
        switch (curState) {
            case RESET:
                onReset();
                break;

            case beyondHeight:
                onReleaseToRefresh();
                break;

            case startShowPullView:
                onRefreshing();
                break;

            case arrivedHeight:
                onArrivedHeight();
                break;

            default:
                break;
        }
    }

    /**
     * 当状态设置为{@link State#RESET}时调用
     */
    protected void onReset() {

    }

    /**
     */
    protected void onPullToRefresh() {

    }

    /**
     * 当状态设置为{@link State#beyondHeight}时调用
     */
    protected void onReleaseToRefresh() {

    }

    /**
     * 当状态设置为{@link State#startShowPullView}时调用
     */
    protected void onRefreshing() {

    }

    /**
     * 当状态设置为{@link State#arrivedHeight}时调用
     */
    protected void onArrivedHeight() {

    }

    protected View childScrollView;
    public View getChildScrollView() {
        return childScrollView;
    }

    /**
     * 得到当前Layout的内容大小，它将作为一个刷新的临界点
     *
     * @return 高度
     */
    public abstract int getContentSize();

    public abstract int getListSize();

    /**
     * 定义一个接口，用于复位操作
     */
    public interface ResetLayout{
        void onRest();
    }
    protected ResetLayout resetLayout;
    public void setResetLayout(ResetLayout resetLayout) {
        this.resetLayout = resetLayout;
    }

    /**
     * 定义一个接口，用于状态变化时做出相应的动作
     */
    public interface StateLayout{
        void onStateChange(State state);
    }
    protected StateLayout stateLayout;
    public void setStateLayout(StateLayout stateLayout) {
        this.stateLayout = stateLayout;
    }

}
