package com.ziyidai.www.myretrofitdemo;


import retrofit.Call;
import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Pony on 2016/2/22.
 */
public interface User {
    //@GET表明该请求为GET方式，除此之外还有@POST, @PUT, @DELETE, 和@HEAD,具体的作用请参考官方文档，由于篇幅原因，这里不再叙述。
    @POST("http://192.168.0.106:8082/Cxf_xzlc_app/user/checkUserPhone")
    //@Query为参数声明  Callback< String > 为回调接口，String表明返回数据结果就为String类型
    Call<TotalBean> getUsers(@Query("phone") String phone);
}
