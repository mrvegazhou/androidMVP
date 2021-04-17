package com.effortapp.business.widget.pullExtend;

public interface IPullToExtend {
    /**
     * 设置当前下拉刷新是否可用
     *
     * @param pullRefreshEnabled true表示可用，false表示不可用
     */
    void setPullRefreshEnabled(boolean pullRefreshEnabled);

    /**
     * 判断当前下拉刷新是否可用
     *
     * @return true如果可用，false不可用
     */
    boolean isPullRefreshEnabled();

    /**
     * 得到Header布局对象
     *
     * @return Header布局对象
     */
    ExtendLayout getHeaderExtendLayout();
}
