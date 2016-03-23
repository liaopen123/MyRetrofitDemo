package com.ziyidai.www.myretrofitdemo;


import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Pony on 2016/2/22.
 */
public class User1 {
    public String phone;
     public User1(String phone){
         this.phone = phone;
     }
}
