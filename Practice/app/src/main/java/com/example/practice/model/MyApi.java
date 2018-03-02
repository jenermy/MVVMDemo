package com.example.practice.model;

import com.example.practice.bean.BaseResponseBean;
import com.example.practice.bean.ResultBean;

import io.reactivex.Observable;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author wanlijun
 * @description  retrofit接口
 * @time 2018/3/1 16:06
 */

public interface MyApi {
    @Multipart
    @POST("borrow/newlists")
    Observable<BaseResponseBean> getBorrowList(
            @Part("cmd") String cmd,
            @Part("borrowStatus") String borrowStatus,
            @Part("borrowStyle") String borrowStyle,
            @Part("borrowType") String borrowType,
            @Part("pageNum") String pageNum,
            @Part("pageSize") String pageSize,
            @Part("userId") String userId
    );
}
