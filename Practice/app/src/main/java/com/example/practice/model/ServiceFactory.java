package com.example.practice.model;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author wanlijun
 * @description  okhttp处理请求过程，retrofit负责请求数据和返回数据，rxjava负责异步
 * @time 2018/3/1 16:36
 */

public class ServiceFactory {
    private static OkHttpClient client;
    private static Retrofit retrofit;
    public static <T>T createServiceFacory(Class<T> service){
        client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.i("wanlijun",message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://xinet.duobaodai.com/lcapi/index.php/v1/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //RxJavaCallAdapterFactory适用于RxJava，RxJava2CallAdapterFactory适用于Rxjava2
                //addConverterFactory(ScalarsConverterFactory.create()) 这句顺序很重要，一定要在GsonConverterFactory之前创建，不然不起作用
                .addConverterFactory(ScalarsConverterFactory.create()) //去掉明文传输中参数的双引号，
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit.create(service);
    }
}
