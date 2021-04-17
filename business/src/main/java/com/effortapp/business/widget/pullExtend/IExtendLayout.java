package com.effortapp.business.widget.pullExtend;

public interface IExtendLayout {

    enum State {

        /**
         * Initial state
         */
        NONE,

        /**
         * When the UI is in a state which means that user is not interacting
         * with the Pull-to-Refresh function.
         */
        RESET,

        /**
         超出列表高度
         */
        beyondHeight,

        /**
         * When the UI is currently refreshing, caused by a pull gesture.
         */
        startShowPullView,

        arrivedHeight,
    }

    /**
     * 设置当前状态，派生类应该根据这个状态的变化来改变View的变化
     *
     * @param state 状态
     */
    void setState(State state);

    /**
     * 得到当前的状态
     *
     * @return 状态
     */
    State getState();

    /**
     * 得到当前Layout的内容大小，它将作为一个刷新的临界点
     *
     * @return 高度
     */
    int getContentSize();

    /**
     * 在拉动时调用
     *
     * @param offset 拉动的距离
     */
    void onPull(int offset);


}
