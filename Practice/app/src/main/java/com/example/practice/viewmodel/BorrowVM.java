package com.example.practice.viewmodel;

import android.util.Log;
import android.widget.Toast;

import com.example.practice.bean.BorrowBean;
import com.example.practice.model.DataUtils;
import com.example.practice.view.IView;
import com.example.practice.view.MvvmActivity;

import java.util.ArrayList;

/**
 * @author wanlijun
 * @description  ViewModel初始化的时候加载第一次数据（Model处理网络请求数据，返回的结果传给ViewModel），网络请求返回数据后通过接口更新适配器(ViewModel实现接口，调用View提供的接口更新界面)
 * @time 2018/3/1 17:05
 */

public class BorrowVM implements DataListener{
    private int currentPage = 1;
    private IView iView;
    private MvvmActivity.BorrowAdapter adapter;
    public BorrowVM(IView iView, MvvmActivity.BorrowAdapter adapter){
        this.iView = iView;
        this.adapter = adapter;
        getData();
    }

    //第一次加载数据
    private void getData(){
        DataUtils.getBorrowInfoImpl(currentPage,this);
    }
    //下拉刷新
    public void loadRefreshData(){
        currentPage = 1;
        getData();
    }
    //上拉加载更多
    public void loadMoreData(){
        currentPage++;
        getData();
    }

    @Override
    public void loadStart() {
        iView.loadStart(1);
    }

    @Override
    public void loadSuccess(ArrayList<BorrowBean> list) {
        Log.i("wanlijun","loadSuccess");
        adapter.setBorrowBeanArrayList(list);
    }

    @Override
    public void loadFailure(String message) {
        if(currentPage > 1){
            currentPage --;
        }
        iView.loadFailure(message);
    }

    @Override
    public void loadComplete() {
        iView.loadComplete();
    }
}
