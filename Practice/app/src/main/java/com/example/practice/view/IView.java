package com.example.practice.view;

/**
 * @author wanlijun
 * @description  提供修改界面的接口
 * @time 2018/3/1 17:20
 */

public interface IView {
    /**
     * 开始加载
     *
     * @param loadType 加载的类型 0：第一次记载 1：下拉刷新 2：上拉加载更多
     */
    void loadStart(int loadType);

    /**
     * 加载完成
     */
    void loadComplete();

    /**
     * 加载失败
     *
     * @param message
     */
    void loadFailure(String message);
}
