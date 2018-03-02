package com.example.practice.bean;

import org.json.JSONObject;

/**
 * @author wanlijun
 * @description  网络请求返回的数据
 * @time 2018/3/1 16:20
 */

public class BaseResponseBean{
    public String errorCode;
    public String errorMess;
//    public Object results;  //经测试，写成String类型报错，写成JSONObject类型获取的值为{}，写成Object类型不好转化
    public ResultBean results;
}
