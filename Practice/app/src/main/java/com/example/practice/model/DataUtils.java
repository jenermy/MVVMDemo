package com.example.practice.model;

import android.util.Log;

import com.example.practice.bean.BaseResponseBean;
import com.example.practice.bean.BorrowBean;
import com.example.practice.bean.ResultBean;
import com.example.practice.viewmodel.DataListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wanlijun
 * @description  Model请求数据
 * @time 2018/3/1 16:53
 */

public class DataUtils {
    public static void getBorrowInfoImpl(int pageNum, final DataListener listener){
        MyApi myApi = ServiceFactory.createServiceFacory(MyApi.class);
        if(myApi != null){
            myApi.getBorrowList("BorrowListsNew","","","",pageNum+"","10","")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseResponseBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            listener.loadStart();
                        }

                        @Override
                        public void onNext(BaseResponseBean value) {
                            Log.i("wanlijun",value.errorCode);
                            Log.i("wanlijun",value.errorMess);
                            Log.i("wanlijun",value.results.toString());
                            Gson gson = new Gson();
                            try {
//                                ArrayList<BorrowBean> list = gson.fromJson(value.results.getString("lists"),new TypeToken<ArrayList<BorrowBean>>(){}.getType());
                                ArrayList<BorrowBean> list = value.results.lists;
                                listener.loadSuccess(list);
                            }catch (Exception e){
                                Log.i("wanlijun",e.toString());
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("wanlijun",e.toString());
                            listener.loadFailure(e.toString());
                        }

                        @Override
                        public void onComplete() {
                            listener.loadComplete();
                        }
                    });
        }
    }
}
