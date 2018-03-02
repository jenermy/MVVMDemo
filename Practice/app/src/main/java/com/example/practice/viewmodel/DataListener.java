package com.example.practice.viewmodel;

import com.example.practice.bean.BorrowBean;

import java.util.ArrayList;

/**
 * @author wanlijun
 * @description
 * @time 2018/3/1 17:06
 */

public interface DataListener {
    public void loadSuccess(ArrayList<BorrowBean> list);
    public void loadFailure(String message);
    public void loadStart();
    public void loadComplete();
}
